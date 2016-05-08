package com.example.controllers;
import com.example.AdminService;
import com.example.model.Admin;
import com.example.model.Profile;
import com.example.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Wayne
 */


@Controller
public class AdminController {

    private AdminService adminService;

    @Autowired
    public void setProductService(AdminService adminService) {
        this.adminService = adminService;
    }
    @RequestMapping("/admin")
    String index(Model model) {
    	model.addAttribute("admin", new Admin());
        return "adminIndex";
    }
    @RequestMapping(value = "admin", method = RequestMethod.POST)
    public String saveProduct(Admin admin){
        adminService.create(admin);
        return "adminIndex";

    }
//    @RequestMapping(value="admin/{id}")
//    public String showProduct(@PathVariable Integer id, Model model){
//        if (adminService.findById(id) == null) {
//            System.out.println("you are in here");
//            return "error";
//        }
//        model.addAttribute("admin",adminService.findById(id));
//        return "admin";
//    }
    
//    @RequestMapping("/profile")
//    public String newProduct(Model model){
//        model.addAttribute("profile", new Profile());
//        return "new_profile";
//    }
//
//    @RequestMapping(value = "profile", method = RequestMethod.POST)
//    public String saveProduct(Profile profile){
//        profileService.create(profile);
//        return "redirect:/profile/" + profile.getNumberID();
//    }




}