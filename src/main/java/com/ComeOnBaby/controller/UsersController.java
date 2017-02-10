
package com.ComeOnBaby.controller;

import com.ComeOnBaby.model.AppUser;
import com.ComeOnBaby.service.AppUserService;

import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    private final static String NEW_PASSWORD = "newpass";
    private final static String RESULT = "result";
    private final static String MESSAGE = "message";
    private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";
    private final static String USER = "user";
    private final static String OPERATION = "operation";

    @Autowired
    AppUserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.POST,  produces="application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String userAction (@RequestBody String body) {
        System.out.println("Get json: " + body.toString());
        JSONObject inJSON = new JSONObject(body);
        JSONObject outJSON = new JSONObject();

        if(!inJSON.has(OPERATION)) throw new IllegalArgumentException("EXCEPTION! No operation.");
        if(!inJSON.has(USER)) throw new IllegalArgumentException("EXCEPTION! No user.");
        switch (inJSON.getString(OPERATION)) {
            case REG_OPERATION: {
                outJSON = emailRegistration(inJSON);
                break;
            }
            case LOGIN_OPERATION: {
                outJSON = emailLogin(inJSON);
                break;
            }
            case CHPASS_OPERATION: {
                outJSON = changePassword(inJSON);
                break;
            }
            case FORGET_OPERATION: {
                break;
            }
            case SOCIAL_OPERATION: {
                outJSON = socialLogin(inJSON);
                break;
            }
            default: {
                throw new IllegalArgumentException("EXCEPTION! Unknown operation.");
            }
        }
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

    //Registration via EMAIL operation
    private JSONObject emailRegistration(JSONObject inJSON) {
        Gson gson = new Gson();
        AppUser inUser = gson.fromJson(inJSON.getJSONObject(USER).toString(), AppUser.class);
        String email = inUser.getEmail();
        String password = inUser.getPassword();
        String loginType = inUser.getLoginType();
        if(email == null || password == null || loginType == null || !loginType.equals(LOGIN_EMAIL)) {
            throw new IllegalArgumentException("EXCEPTION! Data error. Email registration failure");
        }
        JSONObject outJSON = new JSONObject();
        outJSON.put(OPERATION, REG_OPERATION);
        //Check if user with specified email allready exists in BD
        AppUser bdUser = userService.findByEmail(email);
        if(bdUser != null) {
            outJSON.put(RESULT, FAILURE);
            outJSON.put(MESSAGE, "Error. User with this email allready exists");
        } else {
            Long userid = userService.addNewUser(inUser);
            AppUser newUser = userService.findById(userid);
            outJSON.put(RESULT, SUCCESS);
            outJSON.put(MESSAGE, "Success. New user registered");
            outJSON.put(USER, getUserJSON(newUser));
        }
        return outJSON;
    }

    //Login via EMAIL operation
    private JSONObject emailLogin(JSONObject inJSON) {
        Gson gson = new Gson();
        AppUser inUser = gson.fromJson(inJSON.getJSONObject(USER).toString(), AppUser.class);
        String email = inUser.getEmail();
        String password = inUser.getPassword();
        String loginType = inUser.getLoginType();
        if(email == null || password == null || loginType == null || !loginType.equals(LOGIN_EMAIL)) {
            throw new IllegalArgumentException("EXCEPTION! Data error. Email login failure");
        }
        JSONObject outJSON = new JSONObject();
        outJSON.put(OPERATION, LOGIN_OPERATION);
        //Check if user with specified email exists in BD
        AppUser bdUser = userService.findByEmail(email);
        if(bdUser == null) {
            outJSON.put(RESULT, FAILURE);
            outJSON.put(MESSAGE, "Error. User with this email not exists");
        } else {
            if(bdUser.getPassword() == null || !bdUser.getPassword().equals(password)) {
                outJSON.put(RESULT, FAILURE);
                outJSON.put(MESSAGE, "Error. No password or password incorrect for this user");
            } else {
                outJSON.put(RESULT, SUCCESS);
                outJSON.put(MESSAGE, "Success. User verified");
                outJSON.put(USER, getUserJSON(bdUser));
            }
        }
        return outJSON;
    }

    //Change password operation
    private JSONObject changePassword(JSONObject inJSON) {
        //Check required key NEW_PASSWORD in json
        if(!inJSON.has(NEW_PASSWORD)) {
            throw new IllegalArgumentException("EXCEPTION! No new password argument");
        }
        Gson gson = new Gson();
        AppUser inUser = gson.fromJson(inJSON.getJSONObject(USER).toString(), AppUser.class);
        String newPassword = inJSON.getString(NEW_PASSWORD);
        String oldPassword = inUser.getPassword();
        String email = inUser.getEmail();
        //Check required fields not null
        if(email == null || oldPassword == null || newPassword == null) {
            throw new IllegalArgumentException("EXCEPTION! Data error. Change password failure");
        }
        JSONObject outJSON = new JSONObject();
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
            outJSON.put(USER, getUserJSON(bdUser));
        }
        return outJSON;
    }

    //Login or register user with socialID operation
    private JSONObject socialLogin(JSONObject inJSON) {
        Gson gson = new Gson();
        AppUser inUser = gson.fromJson(inJSON.getJSONObject(USER).toString(), AppUser.class);
        Long socialID = inUser.getSocialId();
        String loginType = inUser.getLoginType();
        if(socialID == null || loginType == null ||
                !(loginType.equals(LOGIN_KAKAO) || loginType.equals(LOGIN_FACEBOOK))) {
            throw new IllegalArgumentException("EXCEPTION! Data error. Social registration failure");
        }
        JSONObject outJSON = new JSONObject();
        outJSON.put(OPERATION, SOCIAL_OPERATION);
        AppUser bdUser = userService.findBySocialID(loginType, socialID);
        if(bdUser == null) {
            inUser.setPassword(null); inUser.setEmail(null);  //TODO дублирование мыла
            Long userid = userService.addNewUser(inUser);
            bdUser = userService.findById(userid);
            outJSON.put(MESSAGE, "Success. New user registered");
        } else {
            outJSON.put(MESSAGE, "Success. User verified");
        }
        outJSON.put(RESULT, SUCCESS);
        outJSON.put(USER, getUserJSON(bdUser));
        return outJSON;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST,  produces="application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String login(@RequestBody String body) {
        JSONObject json = new JSONObject(body);
        Gson gson = new Gson();
        AppUser user = null;
        if(json.has("user")) {
            try {
                JSONObject jsonUser = json.getJSONObject("user");
                user = gson.fromJson(jsonUser.toString(), AppUser.class);
            } catch (Exception exc) {
                exc.printStackTrace();
            }
            if(user == null) {
                throw new IllegalArgumentException("ERROR PARSING USER FROM JSON");
            }
        } else {
            throw new IllegalArgumentException("ERROR PARSING USER FROM JSON");
        }

        System.out.println("USER FROM JSON: " + user.toString());

        AppUser bdUser = null;
        switch (user.getLoginType()) {
            case LOGIN_KAKAO: {
                System.out.println("KAKAO");
                if (user.getSocialId() != null) {
                    bdUser = userService.findBySocialID(LOGIN_KAKAO, user.getSocialId());
                } else throw new IllegalArgumentException("ERROR! NO SOCIAL ID FOR KAKAO LOGIN TYPE");
            }
            case LOGIN_FACEBOOK: {
                System.out.println("FACEBOOK");
                if (user.getSocialId() != null) {
                    bdUser = userService.findBySocialID(LOGIN_FACEBOOK, user.getSocialId());
                } else throw new IllegalArgumentException("ERROR! NO SOCIAL ID FOR FACEBOOK LOGIN TYPE");
                break;
            }
            case LOGIN_EMAIL: {
                System.out.println("EMAIL");
                if (user.getEmail() != null && user.getPassword() != null) {
                    bdUser = userService.findByEmail(user.getEmail());
                } else throw new IllegalArgumentException("ERROR! NO EMAIL OR PASSWORD FOR EMAIL LOGIN TYPE");
                break;
            }
            default: {
                throw new IllegalArgumentException("ERROR! NO LOGINTYPE FOR USER");
            }
        }
        if(bdUser != null) {
            System.out.println("USER EXIST");
            if(user.getLoginType().equals(LOGIN_EMAIL)) {
                if(!loginEmail(user, bdUser)) {
                    throw new IllegalArgumentException("ERROR! PASSWORD NOT EQUALS FOR EMAIL LOGIN");
                }
            }
            JSONObject result = new JSONObject();
            result.put(RESULT, SUCCESS);
            result.put(MESSAGE, "User Already Registered!");
            result.put(USER, gson.toJson(bdUser));
            return result.toString();
        }
        else {
            System.out.println("NEW USER");
            Long userid = userService.addNewUser(user);
            AppUser newUser = userService.findById(userid);
            JSONObject result = new JSONObject();
            result.put(RESULT, SUCCESS);
            result.put(MESSAGE, "User Registered Successfully !");
            result.put(USER, gson.toJson(newUser));
            return result.toString();
        }
    }

    private boolean loginEmail (AppUser jsonUser, AppUser bdUser) {
        if(jsonUser.getPassword() != null && jsonUser.getPassword().equals(bdUser.getPassword())) return true;
        return false;
    }

    //EXCEPTION
    @ExceptionHandler({IllegalArgumentException.class, JSONException.class})
    public @ResponseBody String illegalArgument(Exception exc) {
        exc.printStackTrace();
        JSONObject result = new JSONObject();
        result.put(RESULT, FAILURE);
        result.put(MESSAGE, exc.getMessage());
        return result.toString();
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
//        // CREATE USER WITH ROLE CUSTOMER
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

