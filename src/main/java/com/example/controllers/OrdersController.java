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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrdersController {

	private MenuitemsService menuitemsService;
	private OrdersService ordersService;
	// store global orderid for query this person's order
	private int globalorderid;
	// first time, orderid will be 1, when we save to database it will increase
	// 1 on @RequestMapping(value = "checkout", method = RequestMethod.POST)
	// public String saveProduct(Orders orders){} method
	private int currentOrderid = 1;
	//hardcodeuserid wait for session code complete
			int currentUserid=1;

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
	


	
	

	@RequestMapping("/orders")
	public String newProduct(Model model,HttpSession session) {
		HttpSession sessions=session;
		session.setAttribute("tom", 1);
		
		
		ModelAndView mv =new ModelAndView();
		model.addAttribute("menuitems", menuitemsService.listAllMenuitems());
		model.addAttribute("webmenuitems", new Menuitems());
		model.addAttribute("orders", new Orders());

		return "orders";
	}

	@RequestMapping("/adminwelcomepage")
	public String newProduct() {

		return "adminwelcomepage";
	}
	
	@RequestMapping("/admin_menu_items")
	public String adminmenu(Model model) {
		model.addAttribute("newMenuItem", menuitemsService.listAllMenuitems());

	
		return "admin_menu_items";
	}

	@RequestMapping("/orderstatus")
	public String newOrder(Model model) {
		model.addAttribute("orders", ordersService.listAllOrders());

		System.out.println("test");

		return "orderstatus";
	}

	@RequestMapping("orders/{id}")
	public String addMenuItemOrder(@PathVariable Integer id, Orders weborders) {
		System.out.println("ID:" + id);

		Orders orders = new Orders();
		orders.setOrdersid(currentOrderid);
		orders.setMenuid(id);
		orders.setUserid(1);
		orders.setQuantity(weborders.getQuantity());
		ordersService.create(orders);
		return "redirect:/orders";

	}

	@RequestMapping("picktime")
	public String picktime(Model model) {

		model.addAttribute("orders", new Orders());
		return "picktime";

	}

	// place to write the logic for whether user can pick the item or not
	@RequestMapping(value = "checkout", method = RequestMethod.POST)
	public String saveProduct(Orders orders) {
		// hardcode userid
		int userid = 1;
		System.out.println(orders.getTime());
		System.out.println(orders.getDate());

		Iterable<Orders> orderList = ordersService.listAllOrders();
		Iterator<Orders> orerListIterator = orderList.iterator();
		while (orerListIterator.hasNext()) {
			System.out.println("a");
			Orders singleorders = orerListIterator.next();
			System.out.println("orderid" + singleorders.getUserid());
			if (singleorders.ordersid == userid) {

				ordersService.update(singleorders, orders.getDate(), orders.getTime(),"in Progress");

			}
		}

		currentOrderid++;

		return "thankyou";
	}

	@RequestMapping("userwelcomepage")
	public String userWelcome(Model model) {

		return "userwelcomepage";

	}
	
	//use vieworders
	@RequestMapping("uservieworders")
	public String userVieworders(Model model) {
		
		Iterable<Orders> orderList = ordersService.listAllOrders();
		Iterator<Orders> orderListIterator = orderList.iterator();
		ArrayList<Orders> singleUserOrderList = new ArrayList<Orders>();
		
		
		while (orderListIterator.hasNext()) {
			System.out.println("a");
			Orders singleorders = orderListIterator.next();
			System.out.println("orderid" + singleorders.getUserid());
			if (singleorders.ordersid == currentOrderid && singleorders.userid==currentUserid) {

				singleUserOrderList.add(singleorders);

			}
		}
		
		model.addAttribute("singleUserOrders",singleUserOrderList);
		
		
		
//		model.addAttribute("orders", ordersService.listAllOrders());
//		model.addAttribute("menuitems", menuitemsService.listAllMenuitems());

		return "uservieworders";

	}

}