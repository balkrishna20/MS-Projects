package com.example.controllers;


import com.example.services.MenuitemsService;
import com.example.model.Menuitems;
import com.sun.glass.ui.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class MenuitemsController {

    private MenuitemsService menuitemsService;

    @Autowired
    public void setProductService(MenuitemsService menuitemsService) {
        this.menuitemsService = menuitemsService;
    }


    @RequestMapping("/menuitems")
    public String menuItems(Model model, HttpSession session){
        if(session.getAttribute("adminId")!=null) {
            model.addAttribute("Menuitems", menuitemsService.listAllMenuitems());
            Menuitems a = new Menuitems();
            model.addAttribute("newMenuItem", a);
            return "admin_menu_items";
        }
        else
            return "login_error";
    }

//    @RequestMapping(value = "menuitems", method = RequestMethod.GET)
//    public String getMenuItem(Menuitems newMenuItem){
//
//        return "redirect:/menuitems";
//    }


    @RequestMapping(value = "menuitems/add", method = RequestMethod.POST)
    public String saveMenuItem(Menuitems newMenuItem){
        newMenuItem.setActive(true);
        menuitemsService.create(newMenuItem);
        return "redirect:/menuitems";
    }

    @RequestMapping(value = "menuitems/enable/{id}", method = RequestMethod.POST)
    public String enableProduct(@PathVariable Integer id){
        System.out.println("ID enable: "+id);
        Menuitems menu = menuitemsService.findById(id);
        menu.setActive(true);
        menuitemsService.update(menu);
        return "redirect:/menuitems";
    }

    @RequestMapping(value = "menuitems/disable/{id}", method = RequestMethod.POST)
    public String disableProduct(@PathVariable Integer id){
        System.out.println("ID disable: "+id);
        Menuitems menu = menuitemsService.findById(id);
        menu.setActive(false);
        menuitemsService.update(menu);
        return "redirect:/menuitems";
    }

}