package com.example.controllers;
import com.example.services.AdminService;
import com.example.model.Admin;
import com.example.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Controller
public class AdminController {

    private AdminService adminService;
    private OrdersService ordersService;


    @Autowired
    public void setOrderService(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @Autowired
    public void setProductService(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping("/admin")
    String index(Model model, HttpSession session) {
        if(session.getAttribute("adminId")==null) {
            model.addAttribute("admin", new Admin());
            return "adminLogin";
        }
        else
            return "adminwelcomepage";
    }
    @RequestMapping(value = "admin", method = RequestMethod.POST)
    public String checkAdminLogin(Admin admin, HttpSession session){
        for(Iterator<Admin> i = adminService.listAllAdmins().iterator(); i.hasNext();)
        {
            Admin a = i.next();
            if(a.getUserName().equals(admin.getUserName()) && a.getPassword().equals(admin.getPassword()))
            {
                session.setAttribute("adminId", a.getUserId());
                return "adminwelcomepage";
            }
        }
        return "error";

    }


    @RequestMapping("/orderstatus")
    public String newOrder(Model model, HttpSession session) {
        if(session.getAttribute("adminId")!=null) {
            model.addAttribute("orders", ordersService.listAllOrders());
            return "orderstatus";
        }
        else
        {
            return "login_error";
        }
    }



}