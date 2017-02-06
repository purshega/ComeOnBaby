package com.ComeOnBaby.controller;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

/**
 * Created by Alex on 1/12/2017.
 */
@Controller
@SessionAttributes("roles")
public class IndexPageController {
    /**
     * This method handles login GET requests.
     * If users is already logged-in and tries to goto login page again, will be redirected to list page.
     */


    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;


    @Autowired
    MailService mailService;

    @Autowired
    EstimateService estimateService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(Locale locale, Model model  ) {



	/*	// add parametrized message from controller
        String welcome = messageSource.getMessage("welcome.message", new Object[]{"John Doe"}, locale);
		model.addAttribute("message", welcome);*/

        // obtain locale from LocaleContextHolder
        Locale currentLocale = LocaleContextHolder.getLocale();
        model.addAttribute("locale", currentLocale);


        if (isCurrentAuthenticationAnonymous()) {
            return new ModelAndView("index");
        } else {
            ModelAndView modelAndView = new ModelAndView("redirect:/list");
            return modelAndView;
        }
    }






    @ResponseBody
    @RequestMapping(value = "/estimate", method = RequestMethod.POST)
    public String estimateWindow(@RequestParam("name") String recipientName, @RequestParam("email") String recipientMail, @RequestParam("message") String recipientRequestText, Model model) {

        System.out.print("name " + recipientName + " email " + recipientMail + " text " + recipientRequestText);


        JSONObject myJsonObj = new JSONObject();

/*        if (estimateService == null) {
            System.out.print("estimate service null");
        }

        Estimate estimate = new Estimate(recipientName, recipientMail, recipientRequestText, 0);
        System.out.print("domain created ");

        estimateService.addNewEstimate(estimate);
*/


        mailService.sendEmail(recipientMail, recipientName);

        myJsonObj.append("response", "ok");
        return myJsonObj.toString();
    }


    /**
     * This method returns true if users is already authenticated [logged-in], else false.
     */
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }




}
