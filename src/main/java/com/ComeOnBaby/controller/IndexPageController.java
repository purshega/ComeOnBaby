package com.ComeOnBaby.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.*;
import java.util.Date;


@Controller
@SessionAttributes("roles")
public class IndexPageController {

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;


    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView loginPage(@RequestParam(value = "isEstimateSuccess", required = false) Boolean isEstimateSuccess ,
                                  @RequestParam(value = "isGenerateCustomerIdSuccess" , required=false) Boolean isGenerateSuccess ,
                                  @RequestParam(value ="isSessionExpired" , required = false) Boolean isSessionExpired ) {

        if (isCurrentAuthenticationAnonymous()) {
            ModelAndView mainPage = new ModelAndView("index");

            if (isEstimateSuccess != null){
                mainPage.addObject("isEstimateSuccess" , isEstimateSuccess);
            }
            if (isGenerateSuccess !=null){
                mainPage.addObject("isGenerateCustomerIdSuccess" , isGenerateSuccess);
            }
            if (isSessionExpired !=null){
                mainPage.addObject("isSessionExpired" , isSessionExpired);
            }
            return mainPage;
        } else {
            ModelAndView modelAndView = new ModelAndView("redirect:/list");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/session_expired", method = RequestMethod.POST)
    public ModelAndView sessionExpired( ){
        ModelAndView modelAndView = new ModelAndView("redirect:/main");

        modelAndView.addObject("isSessionExpired" , new Boolean(true));

        return modelAndView;
    }

    /**
     * This method returns true if users is already authenticated [logged-in], else false.
     */
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }

}