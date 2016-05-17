package com.example.controllers;

/**
 * Created by rominoushana on 3/28/16.
 */

import com.example.model.Admin;
import com.example.model.Orders;
import com.example.model.Users;
import com.example.services.MenuitemsService;
import com.example.services.OrdersService;
import com.example.services.ProfileService;
import com.example.model.Profile;
import com.example.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    private ProfileService profileService;
    @Autowired
    private JavaMailSender javaMailSender;

    private final int MAX = 9000;

    private final int MIN = 1000;

    private UsersService userService;

    private Users newUser;

    private String code = null;

    private MenuitemsService menuitemsService;
    private OrdersService ordersService;

    public String generateCode()
    {
        int randomCode = (int)(Math.random()*MAX)+MIN;
        return String.valueOf(randomCode);
    }

    public void sendEmail(Users user) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);

            helper.setTo(user.getEmail());
            helper.setReplyTo("cmpe275rwba@gmail.com");
            helper.setFrom("cmpe275rwba@gmail.com");
            helper.setSubject("Verification Code");
            helper.setText(code);
        }
            catch (javax.mail.MessagingException e) {
                e.printStackTrace();

            }
            javaMailSender.send(mail);
        }

    @Autowired
    public void setMenuService(MenuitemsService menuitemsService) {
        this.menuitemsService = menuitemsService;
    }

    @Autowired
    public void setOrderService(OrdersService ordersService) {
        this.ordersService = ordersService;
    }


    @Autowired
    public void setProductService(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Autowired
    public void setUserService(UsersService userService){ this.userService = userService;}

    @RequestMapping("/")
    String index() {
        return "index";
    }

    @RequestMapping("/user")
    public String newUser(Model model, HttpSession session){
        newUser = new Users();
        model.addAttribute("user", newUser);
        return "signup";
    }

    @RequestMapping(value = "user", method = RequestMethod.POST)
    public String signUp(Users user){
        code = generateCode();
        user.setCode(code);
        userService.create(user);
        sendEmail(user);
        return "redirect:/user/"+ user.getNumberID();
    }

    @RequestMapping("/userLogin")
    public String userLogin(Model model){
        model.addAttribute("userLogin", new Users());
        return "customerLogin";
    }

    @RequestMapping(value = "userLogin", method = RequestMethod.POST)
    public String userLoginCheck(Users user,HttpSession session){
        for(Iterator<Users> i = userService.listAllUsers().iterator(); i.hasNext();)
        {
            Users a = i.next();

            if(a.getEmail().equals(user.getEmail()) && a.getPassword().equals(user.getPassword()) && a.getActivationStatus() == true)
            {
                session.setAttribute("id", a.getNumberID());
                session.setAttribute("username", a.getEmail());
                return "userwelcomepage";
            }
        }
        return "error";
    }

    @RequestMapping( "/user/{id}")
    public String verificationScreen(@PathVariable Integer id, Model model){
        if (userService.findById(id) == null) {
            System.out.println("you are in here");
            return "login_error";
        }
        model.addAttribute("user", userService.findById(id));
        return "verificationScreen";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    public String validation(Users user, @PathVariable Integer id, HttpSession session){
        String page = "invalidcodeScreen";
        Users userCode = userService.findById(id);
            if (userCode.getCode().equals(user.getInputCode())) {
                userCode.setActivationStatus(true);
                userService.update(userCode);
                session.setAttribute("id", id);
                return "redirect:/userwelcomepage";
            } else {
                page = "invalidcodeScreen";
            }
        return page;
    }




    @RequestMapping("/userwelcomepage")
    public String userWelcome(Model model, HttpSession session) {
        if(session.getAttribute("id")!=null) {
            return "userwelcomepage";
        }
        else
            return "login_error";
    }


    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }



/*
    @RequestMapping("/profile")
    public String newProduct(Model model, HttpSession session){
        model.addAttribute("profile", new Profile());
        System.out.println("THE USER SESSION ID IS: " + session.getAttribute("id"));
        return "new_profile";
    }

    @RequestMapping(value = "profile", method = RequestMethod.POST)
    public String saveProduct(Profile profile){
        profileService.create(profile);
        return "redirect:/profile/" + profile.getNumberID();
    }

    @RequestMapping(value="profile/{id}")
    public String showProduct(@PathVariable Integer id, Model model){
        if (profileService.findById(id) == null) {
            System.out.println("you are in here");
            return "error";
        }
        model.addAttribute("profile", profileService.findById(id));
        return "profile";
    }


    @RequestMapping(value="profile/{id}",params = {"brief"})
    public String showProductPlainText(@PathVariable Integer id, Model model,@RequestParam(value="brief") String brief){
        System.out.println("The value of ID: "+id);
        model.addAttribute("profile", profileService.findById(id));
        return "profile_plaintext";
    }


    @RequestMapping(value = "profile/delete/{id}")
    public String deleteProduct(@PathVariable Integer id){
        profileService.delete(id);
        return "redirect:/profile/";
    }

    @RequestMapping(value="profile/{id}",params = {"firstname","lastname","email","address","organization","aboutmyself"})
    public String createProductWithParameters(@PathVariable Integer id, @RequestParam(value="firstname") String firstname, @RequestParam(value="lastname") String lastname, @RequestParam(value="email") String email, @RequestParam(value="address") String address, @RequestParam(value="organization") String organization, @RequestParam(value="aboutmyself") String aboutmyself, Model model){
        Profile profile;
        if(profileService.findById(id)==null)
        {
            profile = new Profile();
        }
        else
        {
            profile = profileService.findById(id);
        }
        profile.setNumberID(id);
        profile.setFirstName(firstname);
        profile.setLastName(lastname);
        profile.setEmail(email);
        profile.setAddress(address);
        profile.setOrganization(organization);
        profile.setAboutMyself(aboutmyself);
        profileService.create(profile);
        return "redirect:/profile/" + profile.getNumberID();
    }
    */

}