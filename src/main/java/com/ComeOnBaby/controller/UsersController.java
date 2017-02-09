
package com.ComeOnBaby.controller;

import com.ComeOnBaby.model.AppUser;
import com.ComeOnBaby.service.AppUserService;

import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@SessionAttributes("roles")
public class UsersController {

    private final static String LOGIN_EMAIL = "EMAIL";
    private final static String LOGIN_KAKAO = "KAKAO";
    private final static String LOGIN_FACEBOOK = "FACEBOOK";
    private static final String RESULT = "result";
    private static final String MESSAGE = "message";
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";
    private static final String USER = "user";

    @Autowired
    AppUserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST,  produces="application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String login(@RequestBody String body) {
        System.out.println("ISIDE CONTROLLER");
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
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }

        System.out.println("GET USER: " + user.toString());

        AppUser bdUser = null;
        switch (user.getLoginType()) {
            case LOGIN_KAKAO: {
                System.out.println("KAKAO");
                if (user.getSocialId() != null) {
                    System.out.println("KAKAO1");
                    bdUser = userService.findBySocialID(LOGIN_KAKAO, user.getSocialId());
                }
            }
            case LOGIN_FACEBOOK: {
                System.out.println("FACEBOOK");
                if (user.getSocialId() != null) {
                    System.out.println("FACEBOOK1");
                    bdUser = userService.findBySocialID(LOGIN_FACEBOOK, user.getSocialId());
                }
                break;
            }
            case LOGIN_EMAIL: {
                System.out.println("EMAIL");
                if (user.getEmail() != null) {
                    System.out.println("EMAIL1");
                    bdUser = userService.findByEmail(user.getEmail());
                }
                break;
            }
            default: {
                throw new IllegalArgumentException("ERROR! NO LOGINTYPE FOR USER");
            }
        }
        if(bdUser != null) {
            System.out.println("USER EXIST");
            JSONObject result = new JSONObject();
            result.put(RESULT, FAILURE);
            result.put(MESSAGE, "User Already Registered !");
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

    private AppUser registerUser(AppUser user) {
        userService.addNewUser(user);

        Long id = userService.addNewUser(user);

        return user;
    }

    private AppUser loginUser(AppUser user) {
        return user;
    }

    //EXCEPTION
    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Illegal Argument Exception")
    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalArgument() {
        System.out.println("Illegal Argument Exception");
        return "Illegal Argument Exception";
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

