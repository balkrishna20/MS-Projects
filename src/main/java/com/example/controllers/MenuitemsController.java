package com.example.controllers;

/**
 * Created by Wayne.
 */

import com.example.MenuitemsService;
import com.example.OrdersService;
import com.example.model.Menuitems;
import com.example.model.Orders;
import com.example.model.Profile;
import com.example.repositories.MenuitemsRepository;
import com.example.repositories.OrdersRepository;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MenuitemsController {

    private MenuitemsService menuitemsService;
    private OrdersService ordersService;
    //store global orderid for query this person's order
    private int globalorderid;
    //first time, orderid will be 1, when we save to database it will increase 1 on @RequestMapping(value = "checkout", method = RequestMethod.POST)
   // public String saveProduct(Orders orders){} method
    
    private int orderid=1;
  

    public int getOrderid() {
		return globalorderid;
	}

	public void setOrderid(int orderid) {
		this.globalorderid = orderid;
	}

	@Autowired
    public void setProductService(MenuitemsService menuitemsService) {
        this.menuitemsService = menuitemsService;
    }
    
    @Autowired
    public void setOrderService(OrdersService ordersService) {
        this.ordersService = ordersService;
    }


    @RequestMapping("/menuitems")
    public String newProduct(Model model){
        model.addAttribute("menuitems", menuitemsService.listAllMenuitems());
        model.addAttribute("webmenuitems",new Menuitems());
        model.addAttribute("orders",new Orders());
        System.out.println("test");
        return "menuitems";
    }
    
    @RequestMapping(value = "menuitems", method = RequestMethod.POST)
    public String saveProduct(Menuitems menuitems){
//    	System.out.println("TEST: "+ menuitems.menuid);
//    	orders.setMenuid(menuitems.menuid);
//    	orders.setUserid(1);;
//    	ordersService.create(orders);
//    	
    	
        return "checkout";
    }
    
    @RequestMapping("menuitems/{id}")
    public String addMenuItemOrder(@PathVariable Integer id,Orders weborders){
    	System.out.println("ID:" + id);
    	
    	
    	Orders orders = new Orders();
    	orders.setOrdersid(orderid);
    	orders.setMenuid(id);
    	orders.setUserid(1);
    	orders.setQuantity(weborders.getQuantity());
    	java.util.Date utilDate = new java.util.Date();
//    	Date myDate = new Date(utilDate.getTime());
    	
    	DateFormat time = new SimpleDateFormat("HHmm");
//    	System.out.println(time.format(myDate));
    	
//    	orders.setDate(myDate);
//    	orders.setTime(time.format(myDate));
    	
    	
    	ordersService.create(orders);
    	
    	
    	
   	    return "redirect:/menuitems";
    	
    }
   
    @RequestMapping("picktime")
    public String picktime(Model model){
    	
   	   
    	 model.addAttribute("orders",new Orders());
    	 
    	
    	
    	
    	
    	
    	
    	return "picktime";
    	
    }
    //place to write the logic for whether user can pick the item or not
    @RequestMapping(value = "checkout", method = RequestMethod.POST)
    public String saveProduct(Orders orders){
       //hardcode userid
    	System.out.println("checkouttest");
    	int userid =1;
    	System.out.println(orders.getTime());
    	System.out.println(orders.getDate());
    
    	Iterable<Orders> orderList=ordersService.listAllOrders() ;
          Iterator<Orders> orerListIterator= orderList.iterator();
          while( orerListIterator.hasNext()){
        	  System.out.println("a");
        	  Orders singleorders=orerListIterator.next();
        	  System.out.println("orderid"+singleorders.getUserid());
        	  if(singleorders.ordersid==userid){
        		  
        		 ordersService.update(singleorders, orders.getDate(), orders.getTime());
		  
        	  }
          }
    	
    	
        
    	
    	
    	
//    	orders.setOrdersid(4);
//    	orders.setMenuid(id);
//    	orders.setUserid(1);
//    	orders.setQuantity(weborders.getQuantity());
//    	java.util.Date utilDate = new java.util.Date();
//    	Date myDate = new Date(utilDate.getTime());
//    	
//    	DateFormat time = new SimpleDateFormat("HHmm");
//    	System.out.println(time.format(myDate));
//    	
//    	orders.setDate(myDate);
//    	orders.setTime(time.format(myDate));
//    	ordersService.create(orders);
//   	    return "redirect:/menuitems";
       
        //next order will increase 1
      	orderid++;
    	 
        return "thankyou";
    }

    

    
    
    
    
    
    
    
    


//    @RequestMapping(value = "menuitems/add", method = RequestMethod.POST)
////    public String addOrderList(Orders orders){
////        
//////    	this.orders.getMenuitemsList().add(orders);
//////    	System.out.println("test");
//////        return "o";
////    }
//    

    
   
    
   

//    @RequestMapping(value = "menuitems", method = RequestMethod.POST)
//    public String saveProduct(Orders orders){
//        ordersService.create(orders);
//        return "menuitems";
//    }
//  public String saveProduct(){
// System.out.println();
//  return "checkout";
}

// @RequestMapping(value="profile/{id}")
// public String showProduct(@PathVariable Integer id, Model model){
// if (profileService.findById(id) == null) {
// System.out.println("you are in here");
// return "error";
// }
// model.addAttribute("profile", profileService.findById(id));
// return "profile";
// }
//
//
// @RequestMapping(value="profile/{id}",params = {"brief"})
// public String showProductPlainText(@PathVariable Integer id, Model
// model,@RequestParam(value="brief") String brief){
//// model.addAttribute("profile", profileService.findById(id));
//// System.out.println("The value of brief: "+brief);
//// if(brief=="true")
//// {
//// model.addAttribute("profile", profileService.findById(id));
//// return "profile_plaintext";
//// }
//// else
//// {
//// model.addAttribute("profile", profileService.findById(id));
//// return "profile";
//// }
// System.out.println("The value of ID: "+id);
// model.addAttribute("profile", profileService.findById(id));
// return "profile_plaintext";
// }
//
//
// @RequestMapping(value = "profile/delete/{id}")
// public String deleteProduct(@PathVariable Integer id){
// profileService.delete(id);
// return "redirect:/profile/";
// }
//
// @RequestMapping(value="profile/{id}",params =
// {"firstname","lastname","email","address","organization","aboutmyself"})
// public String createProductWithParameters(@PathVariable Integer id,
// @RequestParam(value="firstname") String firstname,
// @RequestParam(value="lastname") String lastname, @RequestParam(value="email")
// String email, @RequestParam(value="address") String address,
// @RequestParam(value="organization") String organization,
// @RequestParam(value="aboutmyself") String aboutmyself, Model model){
// Profile profile;
// if(profileService.findById(id)==null)
// {
// profile = new Profile();
// }
// else
// {
// profile = profileService.findById(id);
// }
// profile.setNumberID(id);
// profile.setFirstName(firstname);
// profile.setLastName(lastname);
// profile.setEmail(email);
// profile.setAddress(address);
// profile.setOrganization(organization);
// profile.setAboutMyself(aboutmyself);
// profileService.create(profile);
// return "redirect:/profile/" + profile.getNumberID();
// }
