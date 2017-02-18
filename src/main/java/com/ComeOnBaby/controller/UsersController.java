
package com.ComeOnBaby.controller;

import com.ComeOnBaby.model.AppUser;
import com.ComeOnBaby.model.City;
import com.ComeOnBaby.model.Preferences;
import com.ComeOnBaby.service.AppUserService;

import com.ComeOnBaby.service.PreferencesService;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.SecureRandom;

@Controller
@SessionAttributes("roles")
public class UsersController {

    private final static String LOGIN_EMAIL = "EMAIL";
    private final static String LOGIN_KAKAO = "KAKAO";
    private final static String LOGIN_FACEBOOK = "FACEBOOK";
    private final static String REG_OPERATION = "registration";
    private final static String LOGIN_OPERATION = "loginemail";
    private final static String CHPASS_OPERATION = "changepass";
    private final static String FORGET_OPERATION = "forgetpass";
    private final static String SOCIAL_OPERATION = "loginsocial";
    private final static String UPDATE_EMAIL_OPERATION = "emailupdate";
    private final static String UPDATE_PASSWORD_OPERATION = "passwordupdate";
    private final static String RESULT = "result";
    private final static String MESSAGE = "message";
    private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";
    private final static String MODEL = "model";
    private final static String USER = "user";
    private final static String DATA = "data";
    private final static String OPERATION = "operation";

    private final static String NEW_EMAIL = "newemail";
    private final static String NEW_PASSWORD = "newpassword";

    private final static String GET_PROFILE = "getprofile";
    private final static String UPDATE_PROFILE = "updateprofile";
    private final static String ADD_CALENDAR_DAY_ACTIONS = "addcalendar";



    @Autowired
    AppUserService userService;

    @Autowired
    PreferencesService prefService;

    @RequestMapping(value = "/users", method = RequestMethod.POST,  produces="application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String userAction (@RequestBody String body) {
        System.out.println("Get json: " + body.toString());
        JSONObject inJSON = new JSONObject(body);
        if(!inJSON.has(OPERATION)) throw new IllegalArgumentException("EXCEPTION! No operation.");
        if(!inJSON.has(USER)) throw new IllegalArgumentException("EXCEPTION! No user.");
        //Default response values
        JSONObject outJSON = new JSONObject();
        outJSON.put(RESULT, FAILURE);
        outJSON.put(OPERATION, inJSON.getString(OPERATION));
        outJSON.put(MESSAGE, Strings.MSG_SERVER_ERROR);

        switch (inJSON.getString(OPERATION)) {
            case REG_OPERATION: {
                emailRegister(inJSON, outJSON);
                break;
            }
            case LOGIN_OPERATION: {
                emailLogin(inJSON, outJSON);
                break;
            }
            case CHPASS_OPERATION: {
                changePassword(inJSON, outJSON);
                break;
            }
            case FORGET_OPERATION: {
                break;
            }
            case SOCIAL_OPERATION: {
                socialLogin(inJSON, outJSON);
                break;
            }
            case UPDATE_EMAIL_OPERATION: {
                emailUpdate(inJSON, outJSON);
                break;
            }
            case UPDATE_PASSWORD_OPERATION: {
                passwordUpdate(inJSON, outJSON);
                break;
            }
            case UPDATE_PROFILE: {
                updateprofile(inJSON, outJSON);
                break;
            }
            case GET_PROFILE: {
                getProfile(inJSON, outJSON);
                break;
            }
            case ADD_CALENDAR_DAY_ACTIONS: {
                getProfile(inJSON, outJSON);
                break;
            }
            default: {
                throw new IllegalArgumentException("EXCEPTION! Unknown operation.");
            }
        }
        System.out.println("Out JSON: " + outJSON.toString()  + "\n");
        return outJSON.toString();
    }

    //Make JSON from AppUser
    private JSONObject getUserJSON(AppUser appUser) {
        JSONObject outUser = new JSONObject();
        outUser.put("id", appUser.getId());
        outUser.put("email", appUser.getEmail());
        outUser.put("password", appUser.getPassword());
        outUser.put("socialID" , appUser.getSocialId());
        outUser.put("loginType", appUser.getLoginType());
        return outUser;
    }

    //NEW
    //Email update operation
    private JSONObject emailUpdate(JSONObject inJSON, JSONObject outJSON) {
        Gson gson = new Gson();
        JSONObject data = new JSONObject(inJSON.getString(DATA));
        JSONObject jsonuser = new JSONObject(inJSON.getString(USER));
        AppUser inUser = gson.fromJson(jsonuser.toString(), AppUser.class);
        String newEmail = data.getString(NEW_EMAIL);
        AppUser idUser = userService.findById(inUser.getId());
        AppUser emailUser = userService.findByEmail(newEmail);
        //Если пользователя с этим мылом нет
        if(idUser != null && emailUser == null) {
            idUser.setEmail(newEmail);
            userService.updateUser(idUser);
            outJSON.put(RESULT, SUCCESS);
            outJSON.put(MESSAGE, Strings.MSG_EMAIL_UPDATE_SUCCESS);
            outJSON.put(USER, getUserJSON(idUser).toString());
            outJSON.put(DATA, data.toString());
        //Если мыло не изменилось
        } else if (idUser.getId() == emailUser.getId()){
            outJSON.put(RESULT, SUCCESS);
            outJSON.put(MESSAGE, Strings.MSG_EMAIL_UPDATE_SUCCESS);
            outJSON.put(USER, getUserJSON(idUser).toString());
            outJSON.put(DATA, data.toString());
        //Если такое мыло уже у другого пользователя
        } else {
            outJSON.put(MESSAGE, Strings.MSG_EMAIL_UPDATE_FAIL);
        }
        return outJSON;
    }

    //NEW
    //Password update operation
    private JSONObject passwordUpdate(JSONObject inJSON, JSONObject outJSON) {
        Gson gson = new Gson();
        JSONObject data = new JSONObject(inJSON.getString(DATA));
        JSONObject jsonuser = new JSONObject(inJSON.getString(USER));
        AppUser inUser = gson.fromJson(jsonuser.toString(), AppUser.class);
        String newPassword = data.getString(NEW_PASSWORD);
        AppUser idUser = userService.findById(inUser.getId());
        //Если пользователя с этим мылом нет
        if(idUser != null) {
            idUser.setPassword(newPassword);
            userService.updateUser(idUser);
            outJSON.put(RESULT, SUCCESS);
            outJSON.put(MESSAGE, Strings.MSG_PASSWORD_UPDATE_SUCCESS);
            outJSON.put(USER, getUserJSON(idUser).toString());
            outJSON.put(DATA, data.toString());
        } else {
            outJSON.put(MESSAGE, Strings.MSG_PASSWORD_UPDATE_FAIL);
        }
        return outJSON;
    }

    //NEW
    //Register new user via EMAIL
    private JSONObject emailRegister(JSONObject inJSON, JSONObject outJSON) {
        Gson gson = new Gson();
        JSONObject jsonuser = new JSONObject(inJSON.getString(USER));
        AppUser inUser = gson.fromJson(jsonuser.toString(), AppUser.class);
        String email = inUser.getEmail();
        String password = inUser.getPassword();
        String loginType = inUser.getLoginType();
        if(email == null || password == null || loginType == null || !loginType.equals(LOGIN_EMAIL)) {
            throw new IllegalArgumentException("Server error. Email registration failure");
        }
        //Check if user with specified email allready exists in BD
        AppUser bdUser = userService.findByEmail(email);
        if(bdUser != null) {
            outJSON.put(MESSAGE, Strings.MSG_REGISTER_USER_FAIL);
        } else {
            Long userid = userService.addNewUser(inUser);
            AppUser newUser = userService.findById(userid);
            outJSON.put(RESULT, SUCCESS);
            outJSON.put(MESSAGE, Strings.MSG_REGISTER_USER_SUCCESS);
            outJSON.put(USER, getUserJSON(newUser).toString());
            //создаем настройки юзера
            Preferences preferences = new Preferences();
            preferences.setId(userid);
            prefService.addNewPreferences(preferences);
        }
        return outJSON;
    }

    //NEW
    //Login via EMAIL operation
    private JSONObject emailLogin(JSONObject inJSON, JSONObject outJSON) {
        Gson gson = new Gson();
        JSONObject jsonuser = new JSONObject(inJSON.getString(USER));
        AppUser inUser = gson.fromJson(jsonuser.toString(), AppUser.class);
        String email = inUser.getEmail();
        String password = inUser.getPassword();
        String loginType = inUser.getLoginType();
        if(email == null || password == null || loginType == null) {
            throw new IllegalArgumentException("Server error. Email login failure");
        }
        //Check if user with specified email exists in BD
        AppUser bdUser = userService.findByEmail(email);
        if(bdUser == null) {
            outJSON.put(MESSAGE, Strings.MSG_LOGIN_EMAIL_FAIL);
        } else {
            if(bdUser.getPassword() == null || !bdUser.getPassword().equals(password)) {
                outJSON.put(MESSAGE, Strings.MSG_LOGIN_EMAIL_FAIL);
            } else {
                outJSON.put(RESULT, SUCCESS);
                outJSON.put(MESSAGE, Strings.MSG_LOGIN_EMAIL_SUCCESS);
                outJSON.put(USER, getUserJSON(bdUser).toString());
            }
        }
        return outJSON;
    }

    //Change password operation
    private JSONObject changePassword(JSONObject inJSON, JSONObject outJSON) {
        //Check required key NEW_PASSWORD in json
        if(!inJSON.has(NEW_PASSWORD)) {
            throw new IllegalArgumentException("EXCEPTION! No new password argument");
        }
        Gson gson = new Gson();
        AppUser inUser = gson.fromJson(inJSON.getJSONObject(MODEL).toString(), AppUser.class);
        String newPassword = inJSON.getString(NEW_PASSWORD);
        String oldPassword = inUser.getPassword();
        String email = inUser.getEmail();
        //Check required fields not null
        if(email == null || oldPassword == null || newPassword == null) {
            throw new IllegalArgumentException("Server error. Change password failure");
        }
        outJSON.put(OPERATION, CHPASS_OPERATION);
        //Check if user with specified email exists in BD
        AppUser bdUser = userService.findByEmail(email);
        if(bdUser == null) {                                        //no such user email in BD
            outJSON.put(RESULT, FAILURE);
            outJSON.put(MESSAGE, "Error. User with this email not exists");
        } else if (!bdUser.getPassword().equals(oldPassword)) {     //old password incorrect
            outJSON.put(RESULT, FAILURE);
            outJSON.put(MESSAGE, "Error. Old password incorrect");
        } else {                                                    //all good
            bdUser.setPassword(newPassword);
            userService.updateUser(bdUser);
            outJSON.put(RESULT, SUCCESS);
            outJSON.put(MESSAGE, "Success. User password changed");
            outJSON.put(MODEL, getUserJSON(bdUser));
        }
        return outJSON;
    }

    //Login or register user with socialID operation
    private JSONObject socialLogin(JSONObject inJSON, JSONObject outJSON) {
        Gson gson = new Gson();
        JSONObject jsonuser = new JSONObject(inJSON.getString(USER));
        AppUser inUser = gson.fromJson(jsonuser.toString(), AppUser.class);
        Long socialID = inUser.getSocialId();
        String loginType = inUser.getLoginType();
        String email = inUser.getEmail();
        if(socialID == null || loginType == null || !(loginType.equals(LOGIN_KAKAO) || loginType.equals(LOGIN_FACEBOOK))) {
            throw new IllegalArgumentException("Server error. Social registration failure");
        }
        //Ищем пользователя с таким socialID в БД
        AppUser bdIdUser = userService.findBySocialID(loginType, socialID);
        //Если указан email, ищем пользователя с таким мылом в БД
        AppUser bdEmailUser = null;
        if(email != null) {
            bdEmailUser = userService.findByEmail(email);
        }

        //Если пользователя с таким socialID нет, то создаем его
        if(bdIdUser == null) {
            //Если пользователя с таким мылом нет, то используем его и генерируем пароль
            if(bdEmailUser == null && email != null) {
                setRandomUserPassword(inUser);
            //Если пользователь с такой почтой существует, то не используем её
            } else {
                inUser.setEmail(null);
                inUser.setPassword(null);
            }
            //Создаем пользователя
            Long userid = userService.addNewUser(inUser);
            bdIdUser = userService.findById(userid);
            outJSON.put(MESSAGE, Strings.MSG_REGISTER_SOCIAL_SUCCESS);
            //создаем настройки юзера
            Preferences preferences = new Preferences();
            preferences.setId(userid);
            prefService.addNewPreferences(preferences);
        //Если пользователь с таким socialID уже есть
        } else {
            outJSON.put(MESSAGE, Strings.MSG_LOGIN_SOCIAL_SUCCESS);
        }
        outJSON.put(RESULT, SUCCESS);
        outJSON.put(USER, getUserJSON(bdIdUser).toString());
        return outJSON;
    }

    private void setRandomUserPassword(AppUser user) {
        SecureRandom random = new SecureRandom();
        user.setPassword(new BigInteger(32, random).toString(32));
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

    //set Profile data to server
    private JSONObject updateprofile(JSONObject inJSON, JSONObject outJSON){
        Gson gson = new Gson();
        JSONObject data = new JSONObject(inJSON.getString(DATA));
        JSONObject jsonuser = new JSONObject(inJSON.getString(USER));
        AppUser inUser = gson.fromJson(jsonuser.toString(), AppUser.class);
        Long id_user = inUser.getId();

        Preferences preferences = new Preferences();
        preferences.setId(id_user);
        if (prefService.findById(id_user)!=null) {
            prefService.deletePreferences(preferences);
        }
        if (id_user==null) {
            throw new IllegalArgumentException("Error. User not found");
        } else {
            Preferences pref = gson.fromJson(data.toString(), Preferences.class);
            pref.setId(id_user);
            prefService.addNewPreferences(pref);
            outJSON.put(RESULT, SUCCESS);
            outJSON.put(MESSAGE, Strings.MSG_PASSWORD_UPDATE_SUCCESS);
            outJSON.put(USER, getUserJSON(inUser).toString());

        }
        return outJSON;
    }

    //get Profile data to server
    private JSONObject getProfile(JSONObject inJSON, JSONObject outJSON){
        Gson gson = new Gson();
        JSONObject jsonuser = new JSONObject(inJSON.getString(USER));
        AppUser inUser = gson.fromJson(jsonuser.toString(), AppUser.class);
        Long id_user = inUser.getId();

        Preferences pref = new Preferences();
        pref = prefService.findById(id_user);
        if (pref==null) {
            outJSON.put(MESSAGE, Strings.MSG_PROFILE_NOT_FOUND);
        } else {
            outJSON.put(RESULT, SUCCESS);
            outJSON.put(MESSAGE, Strings.MSG_PASSWORD_UPDATE_SUCCESS);
            outJSON.put(USER, getUserJSON(inUser).toString());
            outJSON.put(DATA, getPreferencesJSON(pref).toString());
        }
        return outJSON;
    }

    //Make JSON from Preferences
    private JSONObject getPreferencesJSON(Preferences pref) {
        JSONObject outPreferences = new JSONObject();
        outPreferences.put("id", pref.getId());
        outPreferences.put("city", pref.getCity());
        outPreferences.put("nickname", pref.getNickname());
        outPreferences.put("gender", pref.getGender());
        outPreferences.put("birth_year", pref.getBirth_year());
        outPreferences.put("address", pref.getAddress());
        outPreferences.put("menstrual_cycle", pref.getMenstrual_cycle());
        outPreferences.put("red_days", pref.getRed_days());
        outPreferences.put("last_cycle", pref.getLast_cycle());
        outPreferences.put("weight", pref.getWeight());
        outPreferences.put("height", pref.getHeight());
        outPreferences.put("avatar", pref.getAvatar());
        outPreferences.put("is_agreement", pref.getIs_agreement());
        outPreferences.put("is_finish_question", pref.getIs_finish_question());

        return outPreferences;
    }

    private JSONObject addCalendarDayActions(JSONObject inJSON, JSONObject outJSON){


        return outJSON;
    }


  /*  @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public ModelAndView test1() {
        System.out.println("test1");
        List<CustomerInfo> customerInfos = customerInfoService.getAllCustomerInfos();
        CustomerInfo customerInfo = customerInfos.get(0);
        Set<Project> projects = customerInfo.getProjects();
        if (projects == null) {
            System.out.println("projects null");
        }
        Iterator<Project> iterator = projects.iterator();
        while (iterator.hasNext()) {
            Project project = iterator.next();
            System.out.println(project.getProjectName());
            System.out.println(project.getCustomerInfo().getUserId());
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        return modelAndView;
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public ModelAndView test2() {
        System.out.println("============test2");
        List<CustomerInfo> customerInfos = customerInfoService.getAllCustomerInfos();
        CustomerInfo customerInfo = customerInfos.get(1);
        System.out.println("======customerInfo name: " + customerInfo.getFirstName());
        System.out.println("====== cu");
        Set<Project> projects = customerInfo.getProjects();
        if (projects == null) {
            System.out.println("projects null");
        }
        Iterator<Project> iterator = projects.iterator();
        while (iterator.hasNext()) {
            Project project = iterator.next();
            System.out.println(project.getProjectName());
            // System.out.println(project.getCases().get);
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        return modelAndView;
    }
*/

//    @Autowired
//    UserService userService;
//
//    @RequestMapping(value = "/createCustomer", method = RequestMethod.GET)
//    public ModelAndView createCustomer() {
//
//        // CREATE MODEL WITH ROLE CUSTOMER
//
//        String emailSSO = "test22@mail.com";
//        String password = "1111";
//
//        User user = new User();
//
//        user.setPassword(password);
//        user.setEmail(emailSSO);
//        user.setSsoId(emailSSO);
//
//        UserProfile userProfile = new UserProfile();
//        userProfile.setId(1);
//        userProfile.setType("CUSTOMER");
//
//        Set<UserProfile> userProfiles = new HashSet<>();
//        userProfiles.add(userProfile);
//
//        user.setUserProfiles(userProfiles);
//
//        userService.saveUser(user);
//
//        // CREATE CUSTOMER PROFILE
//
//        User userAfterSave = userService.findBySSO(emailSSO);
//
//        Long userId = new Long(userAfterSave.getId());
//        String firstName = "test";
//        String lastName = "test";
//        String company = "test";
//        String avatar = "test";
//
//
//        //CREATE FINAL NEW CUSTOMER
//        Set<Project> projects = new HashSet<>();
//
//        CustomerInfo customerInfo = new CustomerInfo(userId, firstName, lastName, company, avatar, projects);
//        customerInfoService.addNewCustomerInfo(customerInfo);
//
//
//        CustomerInfo customerInfoCreated = customerInfoService.getCustomerInfoById(userId);
//
//
//        //CREATE #$GENERAL PROJECT FOR CUSTOMER
//        Date projectCreationDate = new Date(1990, 12, 13);
//
//        Set<Case> cases = new HashSet<>();
//
//
//        Project project = new Project("#$GENERAL" , projectCreationDate , StatusEnum.OPEN.toString() ,customerInfo,  cases ,"test" );
//
//        Set<Project> projectsToAdd = new HashSet<>();
//        projectsToAdd.add(project);
//        customerInfoCreated.setProjects(projectsToAdd);
//
//
//        customerInfoService.updateCustomerInfo(customerInfoCreated);
//
//        return new ModelAndView("index");
//    }
//
//
//    @RequestMapping(value = "/createAdmin", method = RequestMethod.GET)
//    public void createAdmin(){
//        String emailSSO = randomeSSO();
//        String password = "123";
//
//        if (checkSSo(emailSSO)){
//            User user = new User();
//            user.setPassword(password);
//            user.setEmail(emailSSO);
//            user.setSsoId(emailSSO);
//
//            UserProfile userProfile = new UserProfile();
//            userProfile.setType(RoleEnum.ADMIN.toString());
//            userProfile.setId(getTypeID(userProfile.getType()));
//
//            Set<UserProfile> userProfiles = new HashSet<>();
//            userProfiles.add(userProfile);
//
//            user.setUserProfiles(userProfiles);
//
//            userService.saveUser(user);
//        } else {
//            createAdmin();
//        }
//    }
//
//    public void createManager(){
//        String emailSSO = randomeSSO();
//        String password = "123";
//
//        if (checkSSo(emailSSO)){
//            User user = new User();
//            user.setPassword(password);
//            user.setEmail(emailSSO);
//            user.setSsoId(emailSSO);
//
//            UserProfile userProfile = new UserProfile();
//            userProfile.setType(RoleEnum.MANAGER.toString());
//            userProfile.setId(getTypeID(userProfile.getType()));
//
//            Set<UserProfile> userProfiles = new HashSet<>();
//            userProfiles.add(userProfile);
//            user.setUserProfiles(userProfiles);
//
//            userService.saveUser(user);
//
//            User userAfterSave = userService.findBySSO(emailSSO);
//
//            Long userId = new Long(userAfterSave.getId());
//            String firstName = randomeSSO();
//            String lastName = randomeSSO();
//
//            //code to create manager must be here
//
//
//            CustomerInfo customerInfoCreated = customerInfoService.getCustomerInfoById(userId);
//
//
//        } else {
//            createManager();
//        }
//    }
//
//    private String randomeSSO(){
//        System.out.println("==========random SSO");
//        String str = "qwertyuiopasdfghjklzxcvbnm";
//        String strSso = "";
//        for(int i = 0; i < 4; i++){
//            int value = (int) (Math.random()*str.length()-1);
//            strSso+= str.charAt(value);
//        }
//        return strSso+"@mail.com";
//    }
//
//    private boolean checkSSo(String nameSSO){
//        System.out.println("==========check SSO");
//            if(userService.findBySSO(nameSSO)== null){
//                return true;
//            }
//        return false;
//    }
//
//    private int getTypeID(String str){
//        System.out.println("==========find TypeID");
//        switch (str){
//            case "ADMIN": return 2;
//            case "CUSTOMER": return 1;
//            case "MANAGER": return 3;
//        }
//        return -1;
//    }
//
//    @RequestMapping(value = "/q", method = RequestMethod.GET)
//    public void test3(){
//        System.out.println("==============test sort by status");
//        List<Case> aCases = caseService.getAllCases();
//        for (Case aCase:aCases){
//            if(aCase.getStatus().equals(StatusEnum.OPEN.toString())){
//                aCases.add(0, aCase);
//            }
//        }
//        System.out.println("========Sort by status==========="+aCases);
//    }
}

