package com.ComeOnBaby.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.Set;


@Controller
@SessionAttributes("roles")
public class UserCabinetController {

    @Autowired
    CustomerInfoService customerInfoService;

    @RequestMapping(value = "/cabinet", method = RequestMethod.GET)
    public ModelAndView cabinet(Locale locale , HttpSession httpSession) {

         System.out.println("cabinet");


        ModelAndView customerCabinet = new ModelAndView("personalArea");

        Long userId = new Long((Integer)httpSession.getAttribute("UserId"));

        CustomerInfo customerInfo = customerInfoService.getCustomerInfoById(userId);




        Set<Project> projects = customerInfo.getProjects();

   /*     if (projects != null){
            ArrayList<String> projectNames = new ArrayList<>();
            Iterator iterator = projects.iterator();
            while (iterator.hasNext()){
                Project project =(Project) iterator.next();
                projectNames.add(project.ge);
            }
        }
*/




        if (projects != null){
            System.out.println("add projects");
            customerCabinet.addObject("projects" , projects);
        }

        System.out.print(httpSession.getAttribute("UserRole"));

        return customerCabinet;
    }


}
