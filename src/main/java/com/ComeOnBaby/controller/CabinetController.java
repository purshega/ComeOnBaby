package com.ComeOnBaby.controller;


import com.ComeOnBaby.model.*;

import com.ComeOnBaby.service.AppUserService;
import com.ComeOnBaby.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/cabinet")
@SessionAttributes("roles")

public class CabinetController {

   /* @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getCustomerCabinet(HttpSession httpSession) {

        System.out.println("=================cabinet");

        ModelAndView customerCabinet = new ModelAndView("personalArea");


        return customerCabinet;
    }*/

   @Autowired
   AppUserService appUserService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getCustomerCabinet(HttpSession httpSession) {

        ModelAndView modelAndView = new ModelAndView("personalArea");

        List<AppUser> appUsers = appUserService.getAllUsers();

        modelAndView.addObject("appUsersList" , appUsers);


        return modelAndView;
    }

}
