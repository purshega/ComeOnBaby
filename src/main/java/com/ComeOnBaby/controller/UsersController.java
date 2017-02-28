
package com.ComeOnBaby.controller;

import com.ComeOnBaby.model.*;
import com.ComeOnBaby.service.*;

import com.ComeOnBaby.service.NoteService;
import com.ComeOnBaby.service.PreferencesService;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.MultipartConfig;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
        maxFileSize=1024*1024*10,      // 10MB
        maxRequestSize=1024*1024*50)
@SessionAttributes("roles")
public class UsersController {

    //Login types
    private final static String LOGIN_EMAIL = "EMAIL";
    private final static String LOGIN_KAKAO = "KAKAO";
    private final static String LOGIN_FACEBOOK = "FACEBOOK";

    //Operations
    private final static String REG_EMAIL_OPERATION = "registration";
    private final static String LOGIN_EMAIL_OPERATION = "loginemail";
//    private final static String CHANGE_PASS_OPERATION = "changepass";
    private final static String FORGET_PASS_OPERATION = "forgetpass";
    private final static String SOCIAL_LOGIN_OPERATION = "loginsocial";
    private final static String UPDATE_EMAIL_OPERATION = "emailupdate";
    private final static String UPDATE_PASSWORD_OPERATION = "passwordupdate";
    private final static String GET_PROFILE_OPERATION = "getprofile";
    private final static String UPDATE_PROFILE_OPERATION = "updateprofile";
    private final static String ADD_CALENDAR_DAY_ACTIONS = "addcalendar";
    private final static String SAVE_NOTE_OPERATION = "savenote";
    private final static String GET_GUIDE_OPERATION = "getguide";
    private final static String GET_RECIPE_OPERATION = "getrecipe";
    private final static String GET_USER_NOTES_OPERATION = "getnotes";


    //JSON KEYS
    private final static String OPERATION = "operation";
    private final static String RESULT = "result";
    private final static String MESSAGE = "message";
    private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";
    //private final static String MODEL = "model";
    private final static String USER = "user";
    private final static String GUIDE = "guide";
    private final static String PROFILE = "profile";
    private final static String DATA = "data";
    private final static String NEW_EMAIL = "newemail";
    private final static String NEW_PASSWORD = "newpassword";
    private final static String NICKNAME = "nickname";
    private final static String YEAR = "year";
    private final static String MONTH = "month";

    @Autowired
    AppUserService userService;

    @Autowired
    PreferencesService prefService;

    @Autowired
    NoteService noteService;

    @Autowired
    Fertilization_guideService guideService;

    @Autowired
    Recipe_guideService recipeService;


    @RequestMapping(value = "/users", method = RequestMethod.POST, produces={"application/json; charset=UTF-8"})
    @ResponseStatus(value = HttpStatus.OK )
    public @ResponseBody String userAction (@RequestBody String body) {

        System.out.println("Get json: " + body.toString());
        JSONObject inJSON = new JSONObject(body);

        if(!inJSON.has(OPERATION)) throw new IllegalArgumentException(Strings.ERR_NO_OPERATION);
        if(!inJSON.has(USER)) throw new IllegalArgumentException(Strings.ERR_NO_USER);
        //Default response values
        JSONObject outJSON = new JSONObject();
        outJSON.put(RESULT, FAILURE);
        outJSON.put(OPERATION, inJSON.getString(OPERATION));
        outJSON.put(MESSAGE, Strings.ERR_SERVER_ERROR);

        switch (inJSON.getString(OPERATION)) {
            case REG_EMAIL_OPERATION: {
                emailRegister(inJSON, outJSON);
                break;
            }
            case LOGIN_EMAIL_OPERATION: {
                emailLogin(inJSON, outJSON);
                break;
            }
//            case CHANGE_PASS_OPERATION: {
//                changePassword(inJSON, outJSON);
//                break;
//            }
            case FORGET_PASS_OPERATION: {
                break;
            }
            case SOCIAL_LOGIN_OPERATION: {
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
            case UPDATE_PROFILE_OPERATION: {
                updateprofile(inJSON, outJSON);
                break;
            }
            case GET_PROFILE_OPERATION: {
                getProfile(inJSON, outJSON);
                break;
            }
            case SAVE_NOTE_OPERATION: {
                saveNote(inJSON, outJSON);
                break;
            }
            case GET_USER_NOTES_OPERATION: {
                getUserNotes(inJSON, outJSON);
                break;
            }
            case ADD_CALENDAR_DAY_ACTIONS: {
                getProfile(inJSON, outJSON);
                break;
            }
            case GET_GUIDE_OPERATION: {
                getGuide(inJSON, outJSON);
                break;
            }

            case GET_RECIPE_OPERATION: {
                getRecipe(inJSON, outJSON);
                break;
            }

            default: {
                throw new IllegalArgumentException(Strings.ERR_UNKNOWN_OPERATION);
            }
        }
        System.out.println("Out JSON: " + outJSON.toString()  + "\n");
       /* String respond = new String(outJSON.toString() , "UTF-8" );*/
        return outJSON.toString();
    }

    //Save note operation
    private JSONObject saveNote(JSONObject inJSON, JSONObject outJSON) {
        Gson gson = new Gson();
        JSONObject jsuser = new JSONObject(inJSON.getString(USER));
        AppUser inUser = gson.fromJson(jsuser.toString(), AppUser.class);
        JSONObject jsnote = new JSONObject(inJSON.getString(DATA));
        Note note = parseNoteFromJson(jsnote.toString());
        if(inUser.getId() == null || note == null) {
            outJSON.put(MESSAGE, Strings.ERR_SAVE_NOTE);
            return outJSON;
        }
        note.setUser_id(inUser.getId());
        Note bdNote = noteService.findByUserDate(inUser, note.getDate());
        if(bdNote == null) {
            noteService.addNewNote(note);
            System.out.println("Add new note");
        } else {
            System.out.println("Update note");
            note.setId(bdNote.getId());
            noteService.updateNote(note);
        }
        outJSON.put(RESULT, SUCCESS);
        outJSON.put(MESSAGE, Strings.MSG_NOTE_SAVE_SUCCESS);
        return outJSON;
    }

    //Get user notes operation
    private JSONObject getUserNotes(JSONObject inJSON, JSONObject outJSON) {
        Gson gson = new Gson();
        JSONObject jsdata = new JSONObject(inJSON.getString(DATA));
        JSONObject jsonuser = new JSONObject(inJSON.getString(USER));
        AppUser inUser = gson.fromJson(jsonuser.toString(), AppUser.class);
        if(inUser.getId() == null || userService.findById(inUser.getId()) == null) {
            outJSON.put(MESSAGE, Strings.ERR_GET_NOTES);
            return outJSON;
        }
        Integer year = null, month = null;
        if(jsdata != null) {
            if(jsdata.has(YEAR)) year = jsdata.getInt(YEAR);
            if(jsdata.has(MONTH)) year = jsdata.getInt(MONTH);
        }
        List<Note> listNotes;
        Date startDate, endDate;
        //Set user notes for month
        if(year != null && month != null) {
            Calendar cal = new GregorianCalendar(year, month-1, 1);
            System.out.println(cal.toString());
            startDate = cal.getTime();
            cal.add(Calendar.MONTH, 1);
            endDate = cal.getTime();
            listNotes = noteService.findUserNotesInterval(inUser, startDate, endDate);
        }
        //Get user notes for year
        else if (year != null) {
            Calendar cal = new GregorianCalendar(year, 0, 1);
            startDate = cal.getTime();
            cal.add(Calendar.YEAR, 1);
            endDate = cal.getTime();
            listNotes = noteService.findUserNotesInterval(inUser, startDate, endDate);
        }
        //Get all user notes
        else {
            listNotes = noteService.findUserNotes(inUser);
        }
        if(listNotes == null) {
            outJSON.put(MESSAGE, Strings.ERR_GET_NOTES);
            return outJSON;
        }
        JSONArray notesarr = new JSONArray();
        for (int i = 0; i < listNotes.size(); i++) {
            Note note = listNotes.get(i);
            notesarr.put(i, getNoteJSON(note));
        }
        outJSON.put(DATA, notesarr.toString());
        outJSON.put(RESULT, SUCCESS);
        outJSON.put(MESSAGE, Strings.MSG_GET_NOTES_SUCCESS);
        return outJSON;
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
        //Если пользователя с таким ID нет, тогда ошибка
        if(idUser == null) {
            outJSON.put(MESSAGE, Strings.ERR_USER_NOT_FOUND);
            return outJSON;
        }
        AppUser emailUser = userService.findByEmail(newEmail);
        //Если пользователя с этим мылом нет
        if(emailUser == null) {
            idUser.setEmail(newEmail);
            userService.updateUser(idUser);
            outJSON.put(RESULT, SUCCESS);
            outJSON.put(MESSAGE, Strings.MSG_EMAIL_UPDATE_SUCCESS);
            outJSON.put(USER, getUserJSON(idUser).toString());
            outJSON.put(DATA, data.toString());
        //Если мыло не изменилось
        } else if (idUser.getId() == emailUser.getId()) {
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
        //Если пользователя с таким ID нет, тогда ошибка
        if(idUser == null) {
            outJSON.put(MESSAGE, Strings.ERR_USER_NOT_FOUND);
        } else {
            idUser.setPassword(newPassword);
            userService.updateUser(idUser);
            outJSON.put(RESULT, SUCCESS);
            outJSON.put(MESSAGE, Strings.MSG_PASSWORD_UPDATE_SUCCESS);
            outJSON.put(USER, getUserJSON(idUser).toString());
        }
        return outJSON;
    }

    //NEW
    //Register new user via EMAIL
    private JSONObject emailRegister(JSONObject inJSON, JSONObject outJSON) {
        Gson gson = new Gson();
        JSONObject jsonuser = new JSONObject(inJSON.getString(USER));
        JSONObject jsdata = new JSONObject(inJSON.getString(DATA));
        AppUser inUser = gson.fromJson(jsonuser.toString(), AppUser.class);
        String email = inUser.getEmail();
        String password = inUser.getPassword();
        String loginType = inUser.getLoginType();
        String nickname = null;
        if(jsdata.has(NICKNAME)) nickname = jsdata.getString(NICKNAME);
        if(email == null || password == null || loginType == null || !loginType.equals(LOGIN_EMAIL)) {
            throw new IllegalArgumentException(Strings.ERR_ILLEGAL_ARGUMENT);
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
            preferences.setIs_agreement(true);
            if(nickname != null) preferences.setNickname(nickname);
            prefService.addNewPreferences(preferences);
            JSONObject profile = getPreferencesJSON(prefService.findById(userid));
            outJSON.put(DATA, profile.toString());
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
            throw new IllegalArgumentException(Strings.ERR_ILLEGAL_ARGUMENT);
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

                //JSONObject profile = getPreferencesJSON(prefService.findById(bdUser.getId()));
                JSONObject profile = getPreferencesJSON(bdUser.getPreferences());
                outJSON.put(DATA, profile.toString());

            }
        }
        return outJSON;
    }

//    //Change password operation
//    private JSONObject changePassword(JSONObject inJSON, JSONObject outJSON) {
//        //Check required key NEW_PASSWORD in json
//        if(!inJSON.has(NEW_PASSWORD)) {
//            throw new IllegalArgumentException(Strings.ERR_ILLEGAL_ARGUMENT);
//        }
//        Gson gson = new Gson();
//        AppUser inUser = gson.fromJson(inJSON.getJSONObject(MODEL).toString(), AppUser.class);
//        String newPassword = inJSON.getString(NEW_PASSWORD);
//        String oldPassword = inUser.getPassword();
//        String email = inUser.getEmail();
//        //Check required fields not null
//        if(email == null || oldPassword == null || newPassword == null) {
//            throw new IllegalArgumentException(Strings.ERR_ILLEGAL_ARGUMENT);
//        }
//        outJSON.put(OPERATION, CHANGE_PASS_OPERATION);
//        //Check if user with specified email exists in BD
//        AppUser bdUser = userService.findByEmail(email);
//        if(bdUser == null) {                                        //no such user email in BD
//            outJSON.put(RESULT, FAILURE);
//            outJSON.put(MESSAGE, Strings.ERR_USER_NOT_FOUND);
//        } else if (!bdUser.getPassword().equals(oldPassword)) {     //old password incorrect
//            outJSON.put(RESULT, FAILURE);
//            outJSON.put(MESSAGE, Strings.MSG_PASSWORD_UPDATE_FAIL);
//        } else {                                                    //all good
//            bdUser.setPassword(newPassword);
//            userService.updateUser(bdUser);
//            outJSON.put(RESULT, SUCCESS);
//            outJSON.put(MESSAGE, Strings.MSG_PASSWORD_UPDATE_SUCCESS);
//            outJSON.put(MODEL, getUserJSON(bdUser));
//        }
//        return outJSON;
//    }

    //Login or register user with socialID operation
    private JSONObject socialLogin(JSONObject inJSON, JSONObject outJSON) {
        Gson gson = new Gson();
        JSONObject jsonuser = new JSONObject(inJSON.getString(USER));
        AppUser inUser = gson.fromJson(jsonuser.toString(), AppUser.class);
        Long socialID = inUser.getSocialId();
        String loginType = inUser.getLoginType();
        String email = inUser.getEmail();
        if(socialID == null || loginType == null || !(loginType.equals(LOGIN_KAKAO) || loginType.equals(LOGIN_FACEBOOK))) {
            throw new IllegalArgumentException(Strings.ERR_ILLEGAL_ARGUMENT);
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
        JSONObject profile = getPreferencesJSON(prefService.findById(bdIdUser.getId()));
        outJSON.put(RESULT, SUCCESS);
        outJSON.put(USER, getUserJSON(bdIdUser).toString());
        outJSON.put(DATA, profile.toString());
        return outJSON;
    }

    private void setRandomUserPassword(AppUser user) {
        SecureRandom random = new SecureRandom();
        user.setPassword(new BigInteger(32, random).toString(32));
    }

    //set Profile data to server
    private JSONObject updateprofile(JSONObject inJSON, JSONObject outJSON){
        Gson gson = new Gson();
        JSONObject data = new JSONObject(inJSON.getString(DATA));
        JSONObject jsonuser = new JSONObject(inJSON.getString(USER));
        AppUser inUser = gson.fromJson(jsonuser.toString(), AppUser.class);
        Long userID = inUser.getId();

        if (userID == null) {
            throw new IllegalArgumentException(Strings.ERR_USER_NOT_FOUND);
        } else {
            Preferences pref = gson.fromJson(data.toString(), Preferences.class);
            pref.setId(userID);
            if (prefService.findById(userID) == null) {
                prefService.addNewPreferences(pref);
            } else {
                prefService.updatePreferences(pref);
            }
            outJSON.put(RESULT, SUCCESS);
            outJSON.put(MESSAGE, Strings.MSG_PROFILE_UPDATE_SUCCESS);
            outJSON.put(USER, getUserJSON(inUser).toString());
            outJSON.put(DATA, getPreferencesJSON(pref).toString());
        }
        return outJSON;
    }

    //get Profile data to server
    private JSONObject getProfile(JSONObject inJSON, JSONObject outJSON){
        Gson gson = new Gson();
        JSONObject jsonuser = new JSONObject(inJSON.getString(USER));
        AppUser inUser = gson.fromJson(jsonuser.toString(), AppUser.class);
        Long id_user = inUser.getId();

        Preferences pref = prefService.findById(id_user);
        if (pref==null) {
            outJSON.put(MESSAGE, Strings.ERR_PROFILE_NOT_FOUND);
        } else {
            outJSON.put(RESULT, SUCCESS);
            outJSON.put(MESSAGE, Strings.MSG_PROFILE_UPDATE_SUCCESS);
            outJSON.put(USER, getUserJSON(inUser).toString());
            outJSON.put(DATA, getPreferencesJSON(pref).toString());
        }
        return outJSON;
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

    private JSONObject getNoteJSON(Note note) {
        JSONObject js = new JSONObject();
        try {
            Calendar cal = new GregorianCalendar();
            cal.setTime(note.getDate());
            js.put("year", cal.get(Calendar.YEAR));
            js.put("month", cal.get(Calendar.MONTH) + 1);
            js.put("day", cal.get(Calendar.DAY_OF_MONTH));
            if(note.getBbt() != null) js.put("bbt", String.valueOf(note.getBbt()));
            if(note.getRecommended_food() != null) js.put("recommended_foods", note.getRecommended_food());
            if(note.getHas_nuts() != null) js.put("has_nut", String.valueOf(note.getHas_nuts()));
            if(note.getRecommended_nuts() != null) js.put("recommended_nuts", note.getRecommended_nuts());
            if(note.getHas_tea() != null) js.put("has_tea", String.valueOf(note.getHas_tea()));
            if(note.getRecommended_tea() != null) js.put("recommended_teas", note.getRecommended_tea());
            if(note.getHas_exercise() != null) js.put("has_exercise", String.valueOf(note.getHas_exercise()));
            if(note.getRecommended_exercise() != null) js.put("recommended_exercise", note.getRecommended_exercise());
            if(note.getGoing_to_bed_from() != null) js.put("going_to_bed_from", note.getGoing_to_bed_from());
            if(note.getGoing_to_bed_to() != null) js.put("going_to_bed_to", note.getGoing_to_bed_to());
            if(note.getWater_intake() != null) js.put("water_intake", String.valueOf(note.getWater_intake()));
            if(note.getHeating_bathing() != null) js.put("hip_bath", String.valueOf(note.getHeating_bathing()));
            if(note.getVitamin() != null) js.put("vitamin", String.valueOf(note.getVitamin()));
            if(note.getFolic_acid() != null) js.put("folate", String.valueOf(note.getFolic_acid()));
            if(note.getCoffee_intake() != null) js.put("coffee_intake", String.valueOf(note.getCoffee_intake()));
            if(note.getAlcohol_intake() != null) js.put("alcohol_consumption", String.valueOf(note.getAlcohol_intake()));
            if(note.getSmoking() != null) js.put("smoking", String.valueOf(note.getSmoking()));
            if(note.getEmotional_state() != null) js.put("emotional_state", String.valueOf(note.getEmotional_state()));
            if(note.getBmi() != null) js.put("bmi", String.valueOf(note.getBmi()));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return js;
    }

    public Note parseNoteFromJson(String data) {
        Note note = new Note();
        try {
            JSONObject js = new JSONObject(data);
            if (js.has("year") && js.has("month") && js.has("day")) {
                int year = js.getInt("year");
                int month = js.getInt("month");
                int day = js.getInt("day");
                Calendar cal = new GregorianCalendar(year, month-1, day);
                note.setDate(cal.getTime());
            } else {
                System.out.println("NoteDTO: Error parsing from JSON - invalid date");
                return null;
            }
            if (js.has("bbt")) {
                String bbt = checkNoJsonData(js.getString("bbt"));
                if(bbt != null) note.setBbt(Float.valueOf(bbt));
            }
            if (js.has("recommended_foods")) {
                String recommended_foods = checkNoJsonData(js.getString("recommended_foods"));
                if(recommended_foods != null) note.setRecommended_food(recommended_foods);
            }
            if (js.has("has_nut")) {
                String has_nut = checkNoJsonData(js.getString("has_nut"));
                if(has_nut != null) note.setHas_nuts(Boolean.valueOf(has_nut));
            }
            if (js.has("recommended_nuts")) {
                String recommended_nuts = checkNoJsonData(js.getString("recommended_nuts"));
                if(recommended_nuts != null) note.setRecommended_nuts(recommended_nuts);
            }
            if (js.has("has_tea")) {
                String has_tea = checkNoJsonData(js.getString("has_tea"));
                if(has_tea != null) note.setHas_tea(Boolean.valueOf(has_tea));
            }
            if (js.has("recommended_teas")) {
                String recommended_teas = checkNoJsonData(js.getString("recommended_teas"));
                if(recommended_teas != null) note.setRecommended_tea(recommended_teas);
            }
            if (js.has("has_exercise")) {
                String has_exercise = checkNoJsonData(js.getString("has_exercise"));
                if(has_exercise != null) note.setHas_exercise(Boolean.valueOf(has_exercise));
            }
            if (js.has("recommended_exercise")) {
                String recommended_exercise = checkNoJsonData(js.getString("recommended_exercise"));
                if(recommended_exercise != null) note.setRecommended_exercise(recommended_exercise);
            }
            if (js.has("going_to_bed_from") && js.has("going_to_bed_to")) {
                String going_to_bed_from = checkNoJsonData(js.getString("going_to_bed_from"));
                String going_to_bed_to = checkNoJsonData(js.getString("going_to_bed_to"));
                if(going_to_bed_from != null && going_to_bed_to != null) {
                    note.setGoing_to_bed_from(going_to_bed_from);
                    note.setGoing_to_bed_to(going_to_bed_to);
                }
            }
            if (js.has("water_intake")) {
                String water_intake = checkNoJsonData(js.getString("water_intake"));
                if(water_intake != null) note.setWater_intake(Double.parseDouble(water_intake));
            }
            if (js.has("hip_bath")) {
                String hip_bath = checkNoJsonData(js.getString("hip_bath"));
                if(hip_bath != null) note.setHeating_bathing(Integer.valueOf(hip_bath));
            }
            if (js.has("vitamin")) {
                String vitamin = checkNoJsonData(js.getString("vitamin"));
                if(vitamin != null) note.setVitamin(Boolean.valueOf(vitamin));
            }
            if (js.has("folate")) {
                String folate = checkNoJsonData(js.getString("folate"));
                if(folate != null) note.setFolic_acid(Boolean.valueOf(folate));
            }
            if (js.has("coffee_intake")) {
                String coffee_intake = checkNoJsonData(js.getString("coffee_intake"));
                if(coffee_intake != null) note.setCoffee_intake(Integer.parseInt(coffee_intake));
            }
            if (js.has("alcohol_consumption")) {
                String alcohol_consumption = checkNoJsonData(js.getString("alcohol_consumption"));
                if(alcohol_consumption != null) note.setAlcohol_intake(Integer.parseInt(alcohol_consumption));
            }
            if (js.has("smoking")) {
                String smoking = checkNoJsonData(js.getString("smoking"));
                if(smoking != null) note.setSmoking(Boolean.valueOf(smoking));
            }
            if (js.has("emotional_state")) {
                String emotional_state = checkNoJsonData(js.getString("emotional_state"));
                if(emotional_state != null) note.setEmotional_state(Integer.parseInt(emotional_state));
            }
            if (js.has("bmi")) {
                String bmi = checkNoJsonData(js.getString("bmi"));
                if(bmi != null) note.setBmi(Float.parseFloat(bmi));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return note;
    }

    private String checkNoJsonData(String str) {
        if(str.isEmpty() || str.equals("null")) {
            return null;
        } else {
            return str;
        }
    }

    private JSONObject getGuide(JSONObject inJSON, JSONObject outJSON) {
        List<Fertilization_guide> guideList = guideService.getAllFertilization_guide();


        outJSON.put(RESULT, SUCCESS);
        outJSON.put(MESSAGE, Strings.MSG_GUIDE_DOWNLOAD_SUCCESS);

        JSONArray jsonArray = new JSONArray();

        for (Fertilization_guide guide : guideList) {
            System.out.println("TO STRING GUIDE   : " + guide.toString());
            jsonArray.put(getGuideJSON(guide));
        }
        outJSON.put(DATA,jsonArray.toString());
        return outJSON;
    }

    //Make JSON from Fertilization_guide
    private JSONObject getGuideJSON(Fertilization_guide guide) {
        JSONObject outGuide = new JSONObject();
        outGuide.put("id", guide.getId());
        outGuide.put("title", guide.getTitle());
        outGuide.put("date", guide.getDateFormat());
        outGuide.put("image" , guide.getImage());
        return outGuide;
    }

    private JSONObject getRecipe(JSONObject inJSON, JSONObject outJSON) {

        List<Recipe_guide> recipeList = recipeService.getAllRecipe_guide();


        outJSON.put(RESULT, SUCCESS);
        outJSON.put(MESSAGE, Strings.MSG_RECIPE_DOWNLOAD_SUCCESS);

        JSONArray jsonArray = new JSONArray();

        for (Recipe_guide recipe : recipeList) {
            jsonArray.put(getRecipeJSON(recipe));
        }
        outJSON.put(DATA,jsonArray.toString());
        return outJSON;
    }

    //Make JSON from Recipe_guide
    private JSONObject getRecipeJSON(Recipe_guide recipe) {
        JSONObject outGuide = new JSONObject();
        outGuide.put("id", recipe.getId());
        outGuide.put("title", recipe.getTitle());
        outGuide.put("date", recipe.getDateFormat());
        outGuide.put("image_thumbnail" , recipe.getImage_thumbnail());
        outGuide.put("url_naver" , recipe.getUrl_naver());
        return outGuide;
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

