package com.ComeOnBaby.controller;


import com.ComeOnBaby.model.*;

import com.ComeOnBaby.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpSession;
import java.util.*;

/*
* customer1@mail.com - 123
* */
@Controller
@RequestMapping("/cabinet")
@SessionAttributes("roles")
public class CabinetController {



    @Autowired
    AppUserService appUserService;
    @Autowired
    PreferencesService preferencesService;
    @Autowired
    BlogService blogService;
    @Autowired
    NoticeService noticeService;

    private String[] menuUrls = new String[]{"cabinet/", "#", "#", "cabinet/notice/", "cabinet/success/", "cabinet/recipe/","cabinet/husband/", "#", "#", "last"};

    static List<String> listMenuUrls = new ArrayList<String>();

    private List<String> getMenuUrls(){
        if(listMenuUrls.size()==0){
            for(String menu: menuUrls){
                listMenuUrls.add(menu);
            }
        }
        return listMenuUrls;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getListUsers(HttpSession httpSession) {

        ModelAndView modelAndView = new ModelAndView("personalsArea");

        List<AppUser> appUsers = appUserService.getAllUsers();
        //
        List<String> menuUrlsList = new ArrayList<>(Arrays.asList(menuUrls));
        //
        List<String> fields = new ArrayList<>();
        fields.add("ID");
        fields.add("Email");
        fields.add("Nickname");
        fields.add("Birthyear");
        fields.add("Login Type");
        fields.add("Gender");


        List<String> values = new ArrayList<>();
        for(AppUser user: appUsers) {
            values.add(user.getStringToTable());
        }

        System.out.println("/cabinet/");
        fields.forEach(a-> System.out.println(a));
        values.forEach(a-> System.out.println(a));
        System.out.println("/cabinet/");

        modelAndView.addObject("fields" , fields);
        modelAndView.addObject("values" , values);
        modelAndView.addObject("namePage" , "Users table");
        modelAndView.addObject("nameTitle" , "Users of ComeOnBaby <small> list users</small>");
        modelAndView.addObject("menuUrls" , getMenuUrls());
        modelAndView.addObject("addPath" , "../");//"/ComeOnBaby/"

        return modelAndView;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ModelAndView getShowUser(@PathVariable Long id, HttpSession httpSession) {

        ModelAndView modelAndView = new ModelAndView("personalArea");

        AppUser appUser = appUserService.findById(id);

        Preferences preferences = null;
        if(appUser!=null) preferences = preferencesService.findFirstByUserId(appUser.getId());

        List<String> fields = new ArrayList<>();
        fields.add("Email");
        fields.add("City");
        fields.add("Nickname");
        fields.add("Gender");
        fields.add("Birthday");
        fields.add("Adress");
        fields.add("Menstrual cycle");
        fields.add("Red days");
        fields.add("Last menstrual start day");
        fields.add("Weight");
        fields.add("Height");
        fields.add("Avatar");

        String[] types = new String[12];
        Arrays.fill(types, "");
        types[11] = "img";

        String[] values = new String[12];
        Arrays.fill(values, "");
        if(appUser!=null) {
            values[0] = appUser.getEmail();
        }
        if(preferences!=null){
            //if(preferences.getCity()!=null) values[1] = preferences.getCity().getName();
            values[2] = preferences.getNickname();
            values[3] = preferences.getNickname();
            values[4] = preferences.getBirth_year()+"";
            values[5] = preferences.getAddress();
            values[6] = preferences.getMenstrual_cycle()+"";
            values[7] = preferences.getRed_days()+"";
            values[8] = preferences.getLast_cycle();
            values[9] = preferences.getWeight()+"";
            values[10] = preferences.getHeight()+"";
            values[11] = preferences.getAvatar();
        }


        modelAndView.addObject("fields" , fields);
        modelAndView.addObject("values" , values);
        modelAndView.addObject("namePage" , "User information");
        modelAndView.addObject("nameTitle" , "Show information of User ComeOnBaby");
        modelAndView.addObject("menuUrls" , getMenuUrls());
        modelAndView.addObject("addPath" , "../../");

        return modelAndView;
    }

    @RequestMapping(value = "/success/", method = RequestMethod.GET)
    public ModelAndView getSuccessStory(HttpSession httpSession) {

        ModelAndView modelAndView = new ModelAndView("personalsArea");

        List<Blog> blogs = blogService.findBlogByType(3);

        List<String> fields = new ArrayList<>();
        fields.add("ID");
        fields.add("Email");
        fields.add("Nickname");
        fields.add("Date");
        fields.add("Title");
        fields.add("Text");

        List<String> values = new ArrayList<>();
        for(Blog blog: blogs) {
            values.add(blog.getStringToTable());
        }

        modelAndView.addObject("fields" , fields);
        modelAndView.addObject("values" , values);
        modelAndView.addObject("namePage" , "Success story");
        modelAndView.addObject("nameTitle" , "Stories table");
        modelAndView.addObject("menuUrls" , getMenuUrls());
        modelAndView.addObject("addPath" , "../../");

        return modelAndView;
    }
    @RequestMapping(value = "/recipe/", method = RequestMethod.GET)
    public ModelAndView getRecipeStory(HttpSession httpSession) {

        ModelAndView modelAndView = new ModelAndView("personalsArea");

        List<Blog> blogs = blogService.findBlogByType(2);

        List<String> fields = new ArrayList<>();
        fields.add("ID");
        fields.add("Email");
        fields.add("Nickname");
        fields.add("Date");
        fields.add("Title");
        fields.add("Text");

        List<String> values = new ArrayList<>();
        for(Blog blog: blogs) {
            values.add(blog.getStringToTable());
        }

        modelAndView.addObject("fields" , fields);
        modelAndView.addObject("values" , values);
        modelAndView.addObject("namePage" , "Recipe story");
        modelAndView.addObject("nameTitle" , "Stories table");
        modelAndView.addObject("menuUrls" , getMenuUrls());
        modelAndView.addObject("addPath" , "../../");

        return modelAndView;
    }
    @RequestMapping(value = "/husband/", method = RequestMethod.GET)
    public ModelAndView getHusbandStory(HttpSession httpSession) {

        ModelAndView modelAndView = new ModelAndView("personalsArea");

        List<Blog> blogs = blogService.findBlogByType(4);

        List<String> fields = new ArrayList<>();
        fields.add("ID");
        fields.add("Email");
        fields.add("Nickname");
        fields.add("Date");
        fields.add("Title");
        fields.add("Text");

        List<String> values = new ArrayList<>();
        for(Blog blog: blogs) {
            values.add(blog.getStringToTable());
        }

        modelAndView.addObject("fields" , fields);
        modelAndView.addObject("values" , values);
        modelAndView.addObject("namePage" , "Husband story");
        modelAndView.addObject("nameTitle" , "Stories table");
        modelAndView.addObject("menuUrls" , getMenuUrls());
        modelAndView.addObject("addPath" , "../../");

        return modelAndView;
    }
    //List<Notice> notices = new ArrayList<>();

    @RequestMapping(value = "/notice/", method = RequestMethod.POST)
    public ModelAndView getPostNotice(@RequestParam("textAddNewNotice") String textAddNewNotice, HttpSession httpSession) {
        System.out.println("notice_POST, textAddNewNotice="+textAddNewNotice);
        Notice notice = null;
        try {
            notice = new Notice(Calendar.getInstance().getTime(), textAddNewNotice, "");
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("notice="+notice);
        noticeService.addNewNotice(notice);
        System.out.println("notice_POST, after notice create");
        return getNotice(httpSession);
    }

    @RequestMapping(value = "/notice/", method = RequestMethod.GET)
    public ModelAndView getNotice(HttpSession httpSession) {
        System.out.println("notice_GET");
        //
        ModelAndView modelAndView = new ModelAndView("personalsArea");

        List<Notice> notices = noticeService.getAllNotice();
        System.out.println("notices.size()="+notices.size());

        //modelAndView.addObject("fields" , fields);
        //modelAndView.addObject("values" , values);
        modelAndView.addObject("blocks" , notices);
        modelAndView.addObject("namePage" , "Notice story");
        modelAndView.addObject("nameTitle" , "Notices table");
        modelAndView.addObject("menuUrls" , getMenuUrls());
        modelAndView.addObject("addPath" , "../../");

        return modelAndView;
    }
}
