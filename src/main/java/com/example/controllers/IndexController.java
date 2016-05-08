package com.example.controllers;

/**
 * Created by rominoushana on 3/28/16.
 */

import com.example.ProfileService;
import com.example.model.Menuitems;
import com.example.model.Profile;
import com.example.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    private ProfileService profileService;

    @Autowired
    public void setProductService(ProfileService profileService) {
        this.profileService = profileService;
    }
    @RequestMapping("/")
    String index() {
        return "index";
    }
    @RequestMapping("/o")
    String ordering() {
        return "o";
    }

    @RequestMapping("/profile")
    public String newProduct(Model model){
        model.addAttribute("profile", new Profile());
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
//        model.addAttribute("profile", profileService.findById(id));
//       System.out.println("The value of brief: "+brief);
//        if(brief=="true")
//        {
//            model.addAttribute("profile", profileService.findById(id));
//            return "profile_plaintext";
//        }
//        else
//        {
//            model.addAttribute("profile", profileService.findById(id));
//            return "profile";
//        }
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
//        Menuitems menuitems;
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




}