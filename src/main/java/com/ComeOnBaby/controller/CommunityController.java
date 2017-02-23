package com.ComeOnBaby.controller;

import com.ComeOnBaby.model.AppUser;
import com.ComeOnBaby.model.Preferences;
import com.ComeOnBaby.service.AppUserService;
import com.ComeOnBaby.service.PreferencesService;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.UUID;

/**
 * Created by olegs on 22.02.2017.
 */

@Controller
@SessionAttributes("roles")
public class CommunityController {

    //Folder to store images
    private static final String IMAGES_DIR = "D:/images";

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

//    @Autowired
//    ServletContext context;

    @Autowired
    AppUserService userService;

    @Autowired
    PreferencesService prefService;

    @RequestMapping(value = "/images/{imgName}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public void getImage(HttpServletResponse response, @PathVariable String imgName) throws IOException {
        File imagePath = new File(IMAGES_DIR, imgName);
        System.out.println("Get image command: " + imagePath.getAbsolutePath());
        if(imagePath.exists()) {
            //InputStream in = context.getResourceAsStream(imagePath.getAbsolutePath());
            ByteArrayOutputStream pngOutStr = new ByteArrayOutputStream();
            BufferedImage image = ImageIO.read(imagePath);
            ImageIO.write(image, "png", pngOutStr);
            byte[] imgByte = pngOutStr.toByteArray();
            response.setStatus(HttpServletResponse.SC_OK);
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/png");
            ServletOutputStream responseOutputStream = response.getOutputStream();
            responseOutputStream.write(imgByte);
            responseOutputStream.flush();
            responseOutputStream.close();
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @RequestMapping(value = "/putimages", method = RequestMethod.POST, produces="application/json")
    public @ResponseBody String saveImages(@RequestBody ImagesUploadRequest body) {
        JSONObject js = new JSONObject();
        byte[][] images = body.getBitmaps();
        if(images == null) {
            js.put(RESULT, FAILURE);
            js.put(MESSAGE, Strings.ERR_SAVE_IMAGES);
            return js.toString();
        }
        System.out.println("Get " + images.length + " images fom user with id=" + body.getUserid());

        File[] files = new File[images.length];
        //Try to compress bitmaps and save files to storage
        try {
            File file;
            for (int i = 0; i < images.length; i++) {
                byte[] img = images[i];
                BufferedImage bi = ImageIO.read(new ByteArrayInputStream(img));
                file = genRandomFile(IMAGES_DIR, "png");
                files[i] = file;
                ImageIO.write(bi, "png", file);
                System.out.println("Saved file: " + file.getAbsolutePath());
            }
        //If something wrong, rollback changes and send error message
        } catch (Exception e) {
            e.printStackTrace();
            for (File file : files) {
                if(file != null && file.exists()) {
                    file.delete();
                    System.out.println("Remove file after exception: " + file.getAbsolutePath());
                }
            }
            js.put(RESULT, FAILURE);
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

    @RequestMapping(value = "/avatar", method = RequestMethod.POST, produces="application/json")
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
                    newAvatar = genRandomFile(IMAGES_DIR, "png");
                    ImageIO.write(bi, "png", newAvatar);
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

    class ImagesUploadRequest {
        private Long userid;
        private byte[][] bitmaps;

        public ImagesUploadRequest(Long userid, byte[][] bitmaps) {
            this.userid = userid;
            this.bitmaps = bitmaps;
        }

        public Long getUserid() {return userid;}
        public void setUserid(Long userid) {this.userid = userid;}
        public byte[][] getBitmaps() {return bitmaps;}
        public void setBitmaps(byte[][] bitmaps) {this.bitmaps = bitmaps;}
    }

    public class UpdateAvatarRequest {
        private String operation;
        private String user;
        private byte[] bitmap;

        public UpdateAvatarRequest(String operation, String user, byte[] bitmap) {
            this.operation = operation;
            this.user = user;
            this.bitmap = bitmap;
        }

        public String getOperation() {return operation;}
        public void setOperation(String operation) {this.operation = operation;}

        public String getUser() {return user;}
        public void setUser(String user) {this.user = user;}

        public byte[] getBitmap() {return bitmap;}
        public void setBitmap(byte[] bitmap) {this.bitmap = bitmap;}
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
