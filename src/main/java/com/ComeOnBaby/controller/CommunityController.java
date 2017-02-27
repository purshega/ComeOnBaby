package com.ComeOnBaby.controller;

import com.ComeOnBaby.model.AppUser;
import com.ComeOnBaby.model.Blog;
import com.ComeOnBaby.model.Comment;
import com.ComeOnBaby.model.Preferences;
import com.ComeOnBaby.service.AppUserService;
import com.ComeOnBaby.service.BlogService;
import com.ComeOnBaby.service.CommentsService;
import com.ComeOnBaby.service.PreferencesService;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * Created by olegs on 22.02.2017.
 */

@Controller
@SessionAttributes("roles")
public class CommunityController {

    //Folder to store images
    private static final String IMAGES_DIR = "D:/images";
    static {
        File file = new File(IMAGES_DIR);
        if(!file.exists()) {
            file.mkdirs();
        }
    }

    //JSON KEYS
    private final static String OPERATION = "operation";
    private final static String RESULT = "result";
    private final static String MESSAGE = "message";
    private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";
    private final static String DATA = "data";
    private final static String AVATAR = "avatar";


    //Operations
    public static final String UPDATE_AVATAR_OPERATION = "updateavatar";
    public static final String SAVE_COMUNITY_RECORD_OPERATION = "saverecord";
    public static final String GET_COMUNITY_RECORDS_OPERATION = "getrecords";
    public static final String SAVE_COMMENT_OPERATION = "savecomment";
    public static final String GET_COMMENTS_OPERATION = "getcomments";


//    @Autowired
//    ServletContext context;

    @Autowired
    AppUserService userService;

    @Autowired
    PreferencesService prefService;

    @Autowired
    BlogService blogService;

    @Autowired
    CommentsService commentsService;

//    @RequestMapping(value = "/images/{imgName}", method = RequestMethod.GET, produces = {"image/jpg", "image/jpeg", "image/png"})
//    public void getImage(HttpServletResponse response, @PathVariable String imgName) throws IOException {
//        File imagePath = new File(IMAGES_DIR, imgName);
//        System.out.println("Get image command: " + imagePath.getAbsolutePath());
//        if(imagePath.exists()) {
//            //InputStream in = context.getResourceAsStream(imagePath.getAbsolutePath());
//            ByteArrayOutputStream imgOutStr = new ByteArrayOutputStream();
//            BufferedImage image = ImageIO.read(imagePath);
//
//            //определяем формат
//            String format = null;
//            try {
//                int dotIndex = imgName.lastIndexOf('.');
//                format = imgName.substring(dotIndex + 1);
//                if(!format.equals("jpg") || !format.equals("png")) {
//                    throw new Exception("Unknown image format");
//                }
//            } catch (Exception exc) {
//                response.sendError(HttpServletResponse.SC_NOT_FOUND);
//                return;
//            }
//
//            ImageIO.write(image, format, imgOutStr);
//            byte[] imgByte = imgOutStr.toByteArray();
//            response.setStatus(HttpServletResponse.SC_OK);
//            response.setHeader("Cache-Control", "no-store");
//            response.setHeader("Pragma", "no-cache");
//            response.setDateHeader("Expires", 0);
//            response.setContentType("image/" + format);
//            ServletOutputStream responseOutputStream = response.getOutputStream();
//            responseOutputStream.write(imgByte);
//            responseOutputStream.flush();
//            responseOutputStream.close();
//        } else {
//            response.sendError(HttpServletResponse.SC_NOT_FOUND);
//        }
//    }

    @RequestMapping(value = "/images/{imgName}", method = RequestMethod.GET, produces = {"image/jpg", "image/jpeg", "image/png"})
    public void getImage(HttpServletResponse response, @PathVariable String imgName) throws IOException {
        try {
            Path path = Paths.get(IMAGES_DIR, imgName);
            System.out.println("Get image command: " + path.toString());
            //определяем формат
            int dotIndex = imgName.lastIndexOf('.');
            String format = imgName.substring(dotIndex + 1);
            if (!format.equals("jpg") && !format.equals("png")) throw new FileNotFoundException("Unknown image format: " + format);
            if (Files.exists(path)) {
                ServletOutputStream outStream = response.getOutputStream();
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("image/" + format);
                Files.copy(path, outStream);
                outStream.flush();
                outStream.close();
                return;
            } else {
                throw new FileNotFoundException("File " + path.toString() + " not found");
            }
        } catch (Exception exc) {
            System.out.println("Get image fail: " + exc.getMessage());
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }


    @RequestMapping(value = "/putimages", method = RequestMethod.POST, produces={"application/json; charset=UTF-8"})
    public @ResponseBody String saveImages(@RequestBody ImagesUploadRequest body) {
        JSONObject js = new JSONObject();
        byte[][] images = body.getBitmaps();
        if(images == null) {
            js.put(RESULT, FAILURE);
            js.put(MESSAGE, Strings.ERR_SAVE_IMAGES);
            return js.toString();
        }
        System.out.println("Get " + images.length + " images fom user with id=" + body.getUserid());

        File[] files = null;
        try {
            files = saveImagesToStorage(images);
        } catch (Exception e) {
            js.put(RESULT, FAILURE);
            removeFiles(files);
            //js.put(MESSAGE, Strings.ERR_SAVE_IMAGES);
            js.put(MESSAGE, e.getMessage());
            return js.toString();
        }
        //Generate out JSON string
        JSONArray filenames = new JSONArray();
        for (int i = 0; i < files.length; i++) {
            filenames.put(i, files[i].getName());
        }
        js.put(RESULT, SUCCESS);
        js.put(MESSAGE, Strings.MSG_SAVE_IMAGES_SUCCESS);
        js.put(DATA, filenames.toString());
        return js.toString();
    }

    @RequestMapping(value = "/avatar", method = RequestMethod.POST, produces={"application/json; charset=UTF-8"})
    public @ResponseBody String saveImages(@RequestBody UpdateAvatarRequest body) {
        JSONObject outJSON = new JSONObject();
        outJSON.put(RESULT, FAILURE);
        outJSON.put(OPERATION, body.getOperation());
        outJSON.put(MESSAGE, Strings.ERR_SERVER_ERROR);

        Gson gson = new Gson();
        JSONObject jsuser = new JSONObject(body.getUser());
        AppUser inUser = gson.fromJson(jsuser.toString(), AppUser.class);
        byte[] byteImg = body.getBitmap();
        if(inUser == null || inUser.getId() == null || byteImg == null) {
            outJSON.put(MESSAGE, Strings.MSG_UPDATE_AVATAR_FAIL);
            return outJSON.toString();
        }
        switch (body.getOperation()) {
            case UPDATE_AVATAR_OPERATION: {
                System.out.println("Get new avatar fom user with id=" + inUser.getId());
                File newAvatar = null, oldAvatar = null;
                try {
                    Preferences pref = prefService.findById(inUser.getId());
                    if(pref == null) {
                        outJSON.put(MESSAGE, Strings.ERR_PROFILE_NOT_FOUND);
                        return outJSON.toString();
                    }
                    if (pref.getAvatar() != null && pref.getAvatar() != "") {
                        oldAvatar = new File(IMAGES_DIR, pref.getAvatar());
                    }
                    BufferedImage bi = ImageIO.read(new ByteArrayInputStream(byteImg));
                    newAvatar = genRandomFile(IMAGES_DIR, "jpg");
                    ImageIO.write(bi, "jpg", newAvatar);
                    System.out.println("Saved new avatar file: " + newAvatar.getAbsolutePath());
                    pref.setAvatar(newAvatar.getName());
                    prefService.updatePreferences(pref);

                    //Remove previous avatar file
                    if (oldAvatar != null && oldAvatar.exists()) {
                        oldAvatar.delete();
                        System.out.println("Removed old avatar file: " + newAvatar.getAbsolutePath());
                    }
                //If something wrong, rollback changes and send error message
                } catch (Exception e) {
                    e.printStackTrace();
                    if(newAvatar != null && newAvatar.exists()) {
                        newAvatar.delete();
                        System.out.println("Remove file after exception: " + newAvatar.getAbsolutePath());
                    }
                    outJSON.put(MESSAGE, e.getMessage());
                    return outJSON.toString();
                }
                outJSON.put(RESULT, SUCCESS);
                outJSON.put(MESSAGE, Strings.MSG_UPDATE_AVATAR_SUCCESS);
                outJSON.put(DATA, new JSONObject().put(AVATAR, newAvatar.getName()).toString());
                break;
            }
            default: {
                outJSON.put(MESSAGE, Strings.ERR_UNKNOWN_OPERATION);
                break;
            }
        }
        return outJSON.toString();
    }

    @RequestMapping(value = "/community", method = RequestMethod.POST, produces={"application/json; charset=UTF-8"})
    public @ResponseBody String communityOperation (@RequestBody CommunityRequest req) {
        System.out.println("Community request: " + req.toString());
        JSONObject outJSON = new JSONObject();
        outJSON.put(RESULT, FAILURE);
        outJSON.put(OPERATION, req.getOperation());
        outJSON.put(MESSAGE, Strings.ERR_SERVER_ERROR);

        if (req.getOperation() == null) throw new IllegalArgumentException(Strings.ERR_NO_OPERATION);
        if (req.getUser() == null) throw new IllegalArgumentException(Strings.ERR_NO_USER);
        JSONObject jsuser = new JSONObject(req.getUser());
        Gson gson = new Gson();
        AppUser inUser = gson.fromJson(jsuser.toString(), AppUser.class);
        AppUser bdUser = null;
        if (inUser == null || inUser.getId() != null) {
            bdUser = userService.findById(inUser.getId());
        }
        if (bdUser == null) {
            outJSON.put(MESSAGE, Strings.ERR_USER_NOT_FOUND);
            return outJSON.toString();
        }
        switch (req.getOperation()) {
            case SAVE_COMUNITY_RECORD_OPERATION: {
                saveCommunityRecord(bdUser.getId(), req, outJSON);
                break;
            }
            case GET_COMUNITY_RECORDS_OPERATION: {
                getCommunityRecordsOperation(req, outJSON);
                break;
            }
            case SAVE_COMMENT_OPERATION: {
                saveCommentOperation(bdUser, req, outJSON);
                break;
            }
            case GET_COMMENTS_OPERATION: {
                getListComments(bdUser, req, outJSON);
                break;
            }
            default: {
                outJSON.put(MESSAGE, Strings.ERR_UNKNOWN_OPERATION);
                return outJSON.toString();
            }
        }
        System.out.println("Out JSON: " + outJSON.toString()  + "\n");
        return outJSON.toString();
    }

    private void saveCommentOperation(AppUser user, CommunityRequest req, JSONObject outJSON) {
        System.out.println("INSIDE SAVE COMMENT: ");
        try {
            Comment comm = new Comment();
            comm.setId_user(user.getId());
            comm.setId_blog(req.communityID);
            comm.setText(req.getContent());
            comm.setDatetime(Calendar.getInstance().getTime());
            commentsService.addNewComments(comm);
        } catch (Exception exc) {
            exc.printStackTrace();
            outJSON.put(RESULT, FAILURE);
            outJSON.put(MESSAGE, Strings.MSG_SAVE_COMMENT_FAIL);
            return;
        }
        outJSON.put(RESULT, SUCCESS);
        outJSON.put(MESSAGE, Strings.MSG_SAVE_COMMENT_SUCCESS);
    }

    private void getListComments(AppUser user, CommunityRequest req, JSONObject outJSON) {
        List<Comment> comments = commentsService.findByBlogID(req.communityID);
        JSONArray jsarr = new JSONArray();
        for (int i = 0; i < comments.size(); i++) {
            Comment comm = comments.get(i);
            jsarr.put(getCommentJSON(comm));
        }
        outJSON.put(RESULT, SUCCESS);
        outJSON.put(MESSAGE, Strings.MSG_GET_COMMENTS_SUCCESS);
        outJSON.put(DATA, jsarr.toString());
    }

    private void getCommunityRecordsOperation(CommunityRequest req, JSONObject outJSON) {
        List<Blog> list = blogService.findBlogByType(req.getType());
        JSONArray jsarr = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            Blog blog = list.get(i);
            jsarr.put(getBlogJSON(blog));
        }
        outJSON.put(RESULT, SUCCESS);
        outJSON.put(MESSAGE, Strings.MSG_GET_COMMUNITY_RECORDS_SUCCESS);
        outJSON.put(DATA, jsarr.toString());
    }

    private final static String BLOGID = "blog_id";
    private final static String USERID = "user_id";
    private final static String USERNICKNAME = "nickname";
    private final static String USERAVATAR = "avatar";
    private final static String BLOGTYPE = "type";
    private final static String BLOGTITLE = "title";
    private final static String BLOGTEXT = "text";
    private final static String BLOGIMAGES = "images";
    private final static String BLOGDATE = "date";
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

    private JSONObject getBlogJSON(Blog blog) {
        JSONObject json = new JSONObject();
        json.put(BLOGID, blog.getId());
        json.put(USERID, blog.getId_user());
        Preferences pr = prefService.findById(blog.getId_user());
        if(pr != null) {
            if (pr.getAvatar() != null) json.put(USERAVATAR, pr.getAvatar());
            if (pr.getNickname() != null) json.put(USERNICKNAME, pr.getNickname());
        }
        json.put(BLOGTYPE, blog.getType());
        json.put(BLOGTITLE, blog.getTitle());
        json.put(BLOGTEXT, blog.getText());
        json.put(BLOGDATE, dateFormat.format(blog.getDatetime()));
        if(blog.getImages() != null) json.put(BLOGIMAGES, blog.getImages());
        return json;
    }

    private final static String COMMID = "comm_id";

    private JSONObject getCommentJSON(Comment comm) {
        JSONObject json = new JSONObject();
        json.put(COMMID, comm.getId());
        json.put(BLOGID, comm.getId_blog());
        json.put(USERID, comm.getId_user());
        json.put(BLOGTEXT, comm.getText());
        Preferences pr = prefService.findById(comm.getId_user());
        if(pr != null) {
            if (pr.getAvatar() != null) json.put(USERAVATAR, pr.getAvatar());
            if (pr.getNickname() != null) json.put(USERNICKNAME, pr.getNickname());
        }
        json.put(BLOGDATE, dateFormat.format(comm.getDatetime()));
        return json;
    }

    private void saveCommunityRecord(Long userID, CommunityRequest req, JSONObject outJSON) {
        Blog blog = new Blog();
        File[] images = null;
            try {
                images = saveImagesToStorage(req.getBitmaps());
                blog.setImages(getStringFileNames(images, ','));
                blog.setId_user(userID);
                blog.setTitle(req.getTitle());
                blog.setText(req.getContent());
                blog.setType(req.getType());
                blog.setDatetime(Calendar.getInstance().getTime());
                blogService.addNewBlog(blog);
            } catch (Exception exc) {
                exc.printStackTrace();
                removeFiles(images);
                //outJSON.put(MESSAGE, Strings.MSG_SAVE_COMUNITY_RECORD_FAIL);
                outJSON.put(MESSAGE, exc.getMessage());
                return;
            }
        outJSON.put(RESULT, SUCCESS);
        outJSON.put(MESSAGE, Strings.MSG_SAVE_COMUNITY_RECORD_SUCCESS);
    }

    //Get String with filenames separated by separator
    private String getStringFileNames(File[] files, char separator) {
        String names = null;
        for(File file : files) {
            if(names == null) names = file.getName();
            else names = names + separator + file.getName();
        }
        return names;
    }

    //Remove files
    private void removeFiles(File[] files) {
        if(files != null) {
            for (File file : files) {
                if (file != null && file.exists()) {
                    file.delete();
                    System.out.println("Remove file: " + file.getAbsolutePath());
                }
            }
        }
    }

    //Save byte array bitmaps to storage and return saved File objects
    private File[] saveImagesToStorage(byte[][] images) throws Exception {
        int numOfImages = images != null ? images.length : 0;
        File[] files = new File[numOfImages];
        try {
            File file;
            for (int i = 0; i < numOfImages; i++) {
                byte[] img = images[i];
                BufferedImage bi = ImageIO.read(new ByteArrayInputStream(img));
                file = genRandomFile(IMAGES_DIR, "jpg");
                files[i] = file;
                ImageIO.write(bi, "jpg", file);
                System.out.println("Saved file: " + file.getAbsolutePath());
            }
        //If something wrong, remove files and send error message
        } catch (Exception exc) {
            removeFiles(files);
            throw exc;
        }
        return files;
    }

    //Random file name generator
    //Folder - containing folder, suffix - file extension
    private static File genRandomFile(String folder, String suffix) {
        File file;
        do {
            String imgName = UUID.randomUUID().toString() + "." + suffix;                 //рандомное имя файла
            file = new File(folder, imgName);
        } while (file.exists());
        return file;
    }

    private class ImagesUploadRequest {
        private Long userid;
        private byte[][] bitmaps;

        public Long getUserid() {return userid;}
        public void setUserid(Long userid) {this.userid = userid;}
        public byte[][] getBitmaps() {return bitmaps;}
        public void setBitmaps(byte[][] bitmaps) {this.bitmaps = bitmaps;}
    }

    private class UpdateAvatarRequest {
        private String operation;
        private String user;
        private byte[] bitmap;

        public String getOperation() {return operation;}
        public void setOperation(String operation) {this.operation = operation;}
        public String getUser() {return user;}
        public void setUser(String user) {this.user = user;}
        public byte[] getBitmap() {return bitmap;}
        public void setBitmap(byte[] bitmap) {this.bitmap = bitmap;}
    }

    private class CommunityRequest {
        private String operation;
        private String user;
        private String title;
        private String content;
        private String data;
        private int type;
        private long communityID;
        private byte[][] bitmaps;

        public String getOperation() {return operation;}
        public void setOperation(String operation) {this.operation = operation;}
        public String getUser() {return user;}
        public void setUser(String user) {this.user = user;}
        public String getTitle() {return title;}
        public void setTitle(String title) {this.title = title;}
        public String getContent() {return content;}
        public void setContent(String content) {this.content = content;}
        public String getData() {return data;}
        public void setData(String data) {this.data = data;}
        public int getType() {return type;}
        public void setType(int type) {this.type = type;}
        public long getCommunityID() {return communityID;}
        public void setCommunityID(long communityID) {this.communityID = communityID;}
        public byte[][] getBitmaps() {return bitmaps;}
        public void setBitmaps(byte[][] bitmaps) {this.bitmaps = bitmaps;}

        @Override
        public String toString() {
            return "operation=" + operation + ", user=" + user + ", title=" + title + ", content=" + content +
                    ", data=" + data + ", type=" + type + ", bitmaps=" + (bitmaps!=null ? "" + bitmaps.length : "null"
            + ", communityID=" + communityID);
        }
    }

    //EXCEPTION
    @ExceptionHandler(Exception.class)
    public @ResponseBody String exception(Exception exc) {
        exc.printStackTrace();
        JSONObject result = new JSONObject();
        result.put(RESULT, FAILURE);
        result.put(MESSAGE, exc.getMessage());
        System.out.println("Out JSON: " + result.toString() + "\n");
        return result.toString();
    }
}
