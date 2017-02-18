package com.ComeOnBaby.controller;

import com.ComeOnBaby.model.City;
import com.ComeOnBaby.service.CityService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by olegs on 17.02.2017.
 */
@Controller
@SessionAttributes("roles")
public class CitiesController {
    private final static String RESULT = "result";
    private final static String MESSAGE = "message";
    private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";
    private final static String DATA = "data";
    private final static String OPERATION = "operation";
    private final static String LIST_CITIES_OPERATION = "listcities";
    private final static String CITIES_ARRAY = "cities";

    @Autowired
    CityService cityService;

    @RequestMapping(value = "/cities", method = RequestMethod.POST,  produces="application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String cityAction (@RequestBody String body) {
        System.out.println("Get json: " + body.toString());
        JSONObject inJSON = new JSONObject(body);
        if(!inJSON.has(OPERATION)) throw new IllegalArgumentException("EXCEPTION! No operation.");

        JSONObject outJSON = new JSONObject();
        outJSON.put(RESULT, FAILURE);
        outJSON.put(OPERATION, inJSON.getString(OPERATION));
        outJSON.put(MESSAGE, Strings.ERR_SERVER_ERROR);

        switch (inJSON.getString(OPERATION)) {
            case LIST_CITIES_OPERATION: {
                listCities(inJSON, outJSON);
                break;
            }
            default:{
                break;
            }
        }
        System.out.println("Out JSON: " + outJSON.toString()  + "\n");
        return outJSON.toString();
    }

    private void listCities(JSONObject inJSON, JSONObject outJSON) {
        List<City> list = cityService.getAllCities();
        if(list != null) {
            outJSON.put(RESULT, SUCCESS);
            outJSON.put(MESSAGE, Strings.MSG_LIST_CITY_SUCCESS);
            JSONArray citiesArray = new JSONArray(list.toArray());
            JSONObject data = new JSONObject();
            data.put(CITIES_ARRAY, citiesArray);
            outJSON.put(DATA, data.toString());
        } else {
            outJSON.put(MESSAGE, Strings.MSG_LIST_CITY_FAIL);
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
