package com.example.controllers;

/**
 * Created by Wayne.
 */

import com.example.model.Menuitems;
import com.example.model.Orders;
import java.sql.Time;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.example.model.Schedule;
import com.example.model.Users;
import com.example.services.MenuitemsService;
import com.example.services.OrdersService;
import com.example.services.ScheduleService;
import com.example.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class OrdersController {

    private MenuitemsService menuitemsService;
    private OrdersService ordersService;
    private UsersService userService;
    private ScheduleService scheduleService;


    //store global orderid for query this person's order
    private int globalorderid = 1;
    //first time, orderid will be 1, when we save to database it will increase 1 on @RequestMapping(value = "checkout", method = RequestMethod.POST)

    private int orderid;

    private Orders orderTemp;

    private int preparationTime;

    private Time earliestPrepareTime;

    @Autowired
    public void setProductService(MenuitemsService menuitemsService) {
        this.menuitemsService = menuitemsService;
    }

    @Autowired
    public void setOrderService(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @Autowired
    public void setUserService(UsersService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    int count;

    public boolean checkCookHours(Date d) {

        String string1 = "5:00";
        String string2 = "21:00";
        Date time1 = null;
        Date time2 = null;
        try {
            time1 = new SimpleDateFormat("HH:mm").parse(string1);
            time2 = new SimpleDateFormat("HH:mm").parse(string2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(time1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(time2);

        Calendar calendar3 = Calendar.getInstance();
        calendar3.setTime(d);


        if (calendar3.getTime().after(calendar1.getTime()) && calendar3.getTime().before(calendar2.getTime())) {
            return true;
        } else {
            return false;
        }
    }


    public boolean checkOfficeHours(Date d) {

        String string1 = "6:00";
        String string2 = "21:00";
        Date time1 = null;
        Date time2 = null;
        try {
            time1 = new SimpleDateFormat("HH:mm").parse(string1);
            time2 = new SimpleDateFormat("HH:mm").parse(string2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(time1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(time2);

        Calendar calendar3 = Calendar.getInstance();
        calendar3.setTime(d);


        if (calendar3.getTime().after(calendar1.getTime()) && calendar3.getTime().before(calendar2.getTime())) {
            System.out.println(d);
            System.out.println(d.getTime());
            System.out.println("\nITS BETWEEN THE TIME");
            return true;
        } else {
            System.out.println("\nNO ITS NOT BETWEEN THE TIME");
            return false;
        }
    }

    public Calendar convertIntoOneDateObject(Time t, Date d) {
        Calendar calendarA = Calendar.getInstance();
        calendarA.setTime(d);

        Calendar calendarB = Calendar.getInstance();
        calendarB.setTime(t);

        calendarA.set(Calendar.HOUR_OF_DAY, calendarB.get(Calendar.HOUR_OF_DAY));
        calendarA.set(Calendar.MINUTE, calendarB.get(Calendar.MINUTE));
        calendarA.set(Calendar.SECOND, calendarB.get(Calendar.SECOND));

        return calendarA;

    }

    public boolean checkAvailableChef(Calendar specifiedDate, Calendar earliestspecifiedDate) {

        count = 1;
        boolean done = false;
        boolean addIntoSchedule = true;

        while (count < 4 && !done) {
            Iterable<Schedule> schedule = scheduleService.listSchedule();
            ArrayList<Schedule> singleChefList = new ArrayList<>();
            Iterator<Schedule> scheduleIterator = schedule.iterator();
            addIntoSchedule = true;

            while (scheduleIterator.hasNext()) {
                Schedule sh = scheduleIterator.next();
                if (sh.getChefID() == count) {
                    singleChefList.add(sh);
                }
            }

            Iterator<Schedule> singleChefIterator = singleChefList.iterator();
            if (!singleChefIterator.hasNext()) {
                System.out.println("HERE HERE HERE HERE");
                return true;
            }
            while (singleChefIterator.hasNext() && addIntoSchedule) {
                Schedule sh = singleChefIterator.next();

                Calendar start = Calendar.getInstance();
                start.setTime(sh.getStartTime());

                Calendar end = Calendar.getInstance();
                end.setTime(sh.getEndTime());

                //if ((specifiedDate.after(start) && specifiedDate.before(end)) || (earliestspecifiedDate.after(start) && earliestspecifiedDate.before(end))) {
                if ((specifiedDate.after(start) && earliestspecifiedDate.before(end))) {
                    System.out.println("Found that Chef " + count + " cant fufill");
                    addIntoSchedule = false;
                    done = false;
                } else {
                    done = true;
                }

            }
            if (!done)
                count++;
        }
        return addIntoSchedule;
    }

    public boolean checkTimeAppropriate(Time t, Date d, HttpSession session) {
        Calendar specifiedDate = convertIntoOneDateObject(t, d);
        System.out.println("Specified Date: " + specifiedDate.getTime());

        Calendar earliestspecifiedDate = convertIntoOneDateObject(t, d);
        earliestspecifiedDate.add(Calendar.MINUTE, -preparationTime);
        System.out.println("Earliest Specified Date: " + earliestspecifiedDate.getTime());


        Calendar earliestPickUpTime = Calendar.getInstance();
        Time timeForMethod = new Time(earliestPickUpTime.get(Calendar.HOUR_OF_DAY), earliestPickUpTime.get(Calendar.MINUTE), earliestPickUpTime.get(Calendar.SECOND));
        earliestPickUpTime.add(Calendar.MINUTE, preparationTime);

        while (!checkCookHours(timeForMethod)){
            earliestPickUpTime.add(Calendar.MINUTE, preparationTime);
            timeForMethod = new Time(earliestPickUpTime.get(Calendar.HOUR_OF_DAY), earliestPickUpTime.get(Calendar.MINUTE), earliestPickUpTime.get(Calendar.SECOND));
        }
        System.out.println("earliestPickUpTime: " + earliestPickUpTime.getTime());

        if (specifiedDate.after(earliestPickUpTime)) {
            System.out.println("ITS AFTER :)");
            if(checkCookHours(earliestspecifiedDate.getTime())) {
                if (checkAvailableChef(specifiedDate, earliestspecifiedDate)) {
                    Schedule newSchedule = new Schedule();

                    java.sql.Timestamp sqlStartTime = new java.sql.Timestamp(earliestspecifiedDate.getTimeInMillis());
                    System.out.println("I want to check: " + sqlStartTime);
                    newSchedule.setStartTime(sqlStartTime);

                    java.sql.Timestamp sqlEndTime = new java.sql.Timestamp(specifiedDate.getTimeInMillis());
                    newSchedule.setEndTime(sqlEndTime);

                    newSchedule.setChefID(count);
                    newSchedule.setOrdersid(globalorderid);
                    newSchedule.setUserid((Integer) session.getAttribute("id"));
                    scheduleService.create(newSchedule);
                    System.out.println("Added to schedule!");
                    return true;
                }
            }
            else if (checkCookHours(timeForMethod)) {
                if (checkAvailableChef(specifiedDate, earliestPickUpTime)) {
                    Schedule newSchedule = new Schedule();

                    java.sql.Timestamp sqlStartTime = new java.sql.Timestamp(earliestspecifiedDate.getTimeInMillis());
                    newSchedule.setStartTime(sqlStartTime);

                    java.sql.Timestamp sqlEndTime = new java.sql.Timestamp(specifiedDate.getTimeInMillis());
                    newSchedule.setEndTime(sqlEndTime);

                    newSchedule.setChefID(count);
                    newSchedule.setOrdersid(globalorderid);
                    newSchedule.setUserid((Integer) session.getAttribute("id"));
                    scheduleService.create(newSchedule);
                    return true;
                }
            }
            else {
                return false;
            }


        } else
            System.out.println("ITS BEFORE :(");
        return false;

    }

    public Date findEarliestTime(Time t, Date d) {
        Calendar earliestPickUpTime = Calendar.getInstance();
        earliestPickUpTime.add(Calendar.MINUTE, preparationTime);

        Calendar currentDate = Calendar.getInstance();

        boolean doneCheck = false;
        while (!doneCheck) {

            Time timeForMethod = new Time(earliestPickUpTime.get(Calendar.HOUR_OF_DAY), earliestPickUpTime.get(Calendar.MINUTE), earliestPickUpTime.get(Calendar.SECOND));
            Time timeForMethod2 = new Time(currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), currentDate.get(Calendar.SECOND));

            //Calendar timeForMethod = Calendar.getInstance();
            //timeForMethod.setTime(test);
            if (checkOfficeHours(timeForMethod)) {
                if(checkCookHours(timeForMethod2)) {

                    if (checkAvailableChef(earliestPickUpTime, currentDate)) {
                        return earliestPickUpTime.getTime();
                    }
                }
            }
            earliestPickUpTime.add(Calendar.MINUTE, preparationTime);
            currentDate.add(Calendar.MINUTE, preparationTime);
        }
        return earliestPickUpTime.getTime();
    }


    @RequestMapping("/orders")
    public String newProduct(Model model, HttpSession session) {
        if (session.getAttribute("id") != null) {


            Iterable<Menuitems> menuList = menuitemsService.listAllMenuitems();
            Iterator<Menuitems> menuListIterator = menuList.iterator();
            ArrayList<Menuitems> singleUserOrderList = new ArrayList<>();


            while (menuListIterator.hasNext()) {
                Menuitems menus = menuListIterator.next();
                if (menus.getActive() == true) {
                    singleUserOrderList.add(menus);
                }
            }
            model.addAttribute("menuitems", singleUserOrderList);

            orderTemp = new Orders();
            orderTemp.setordersid(globalorderid);

            model.addAttribute("orders", orderTemp);
            return "orders";
        } else
            return "login_error";
    }


    @RequestMapping("orders/{id}/{prepTime}")
    public String addMenuItemOrder(@PathVariable Integer id, @PathVariable Integer prepTime, Orders weborders, HttpSession session, Model model) {
        if (session.getAttribute("id") != null) {
            model.addAttribute("orders", orderTemp);
            System.out.println("order: " + weborders.getordersid());
            orderid = weborders.getordersid();
            System.out.println("ordersID: " + orderid);
            weborders.setMenuid(id);
            int multiply = weborders.getQuantity();
            preparationTime= preparationTime+ (prepTime*multiply);
            weborders.setUserid((Integer) session.getAttribute("id"));
            weborders.setQuantity(weborders.getQuantity());
            weborders.setStatus("Added to Order Shopping Cart");
            ordersService.create(weborders);
            return "redirect:/orders";
        } else
            return "login_error";
    }

    @RequestMapping("picktime")
    public String picktime(Model model, HttpSession session) {
        if (session.getAttribute("id") != null) {
            model.addAttribute("orders", new Orders());
            return "picktime";
        } else
            return "login_error";
    }


    //place to write the logic for whether user can pick the item or not
    @RequestMapping(value = "checkout", method = RequestMethod.POST)
    public ModelAndView saveOrder(Orders orders, HttpSession session) {

        int userid = (Integer) session.getAttribute("id");
        Users user = userService.findById(userid);


        Iterable<Orders> orderList = ordersService.listAllOrders();
        Iterator<Orders> orderListIterator = orderList.iterator();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss", Locale.US);

        Date date = null;

        Long time = null;


        try {
            date = dateFormat.parse(orders.getDate());
            time = timeFormat.parse(orders.getTime()).getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        Time sqlTime = new Time(time);
        boolean foundEarliestTime = false;
        if (checkOfficeHours(sqlTime)) {
            if (checkTimeAppropriate(sqlTime, sqlDate, session)) {
                while (orderListIterator.hasNext()) {
                    Orders singleorders = orderListIterator.next();

                    if (singleorders.ordersid == orderid && singleorders.getUserid() == userid) {
                        orders.setUserid(userid);
                        orders.setSqlDate(sqlDate);
                        orders.setSqlTime(sqlTime);
                        orders.setStatus("Order Placed");
                        ordersService.update(singleorders, orders.getSqlDate(), orders.getSqlTime(), orders.getStatus());
                    }
                }
                System.out.println("Total prep time: " + preparationTime);
                globalorderid++;
                preparationTime = 0;
                return new ModelAndView("thankyou");

            } else {
                Date early = findEarliestTime(sqlTime, sqlDate);
                SimpleDateFormat formatString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String earlyTime = formatString.format(early);
                return new ModelAndView("picktime", "error", "Pick up time not available. Earliest pickup time is: " + earlyTime);
            }
        } else {
            Date early = findEarliestTime(sqlTime, sqlDate);
            SimpleDateFormat formatString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String earlyTime = formatString.format(early);
            return new ModelAndView("picktime", "error", "Pick up time not within office hours. Please choose between 6am-9pm. Earliest pickup time is: " + earlyTime);
        }
    }


    @RequestMapping("/uservieworders")
    public String userVieworders(Model model, HttpSession session) {
        if (session.getAttribute("id") != null) {
            int userid = (Integer) session.getAttribute("id");
            Iterable<Orders> orderList = ordersService.listAllOrders();
            Iterator<Orders> orderListIterator = orderList.iterator();
            ArrayList<Orders> singleUserOrderList = new ArrayList<>();


            while (orderListIterator.hasNext()) {
                Orders singleorders = orderListIterator.next();

                if (singleorders.getUserid() == userid) {

                    singleUserOrderList.add(singleorders);
                    System.out.println("Added");

                }
            }
            model.addAttribute("singleUserOrders", singleUserOrderList);

            return "uservieworders";
        } else
            return "login_error";
    }

    @RequestMapping(value = "/deleteOrder/{id}", method = RequestMethod.POST)
    public String deleteOrder(Model model, HttpSession session, @PathVariable Integer id) {
        if (session.getAttribute("id") != null) {
            int userid = (Integer) session.getAttribute("id");
            Iterable<Orders> orderList = ordersService.listAllOrders();
            Iterator<Orders> orderListIterator = orderList.iterator();
            ArrayList<Orders> singleUserOrderList = new ArrayList<>();

            while (orderListIterator.hasNext()) {
                Orders singleorders = orderListIterator.next();

                if (singleorders.getordersid() == id && singleorders.getUserid()==userid) {
                    singleUserOrderList.add(singleorders);
                }
            }
            Iterator<Orders> deleteIterator = singleUserOrderList.iterator();

            while (deleteIterator.hasNext()) {
                Orders singleorders = deleteIterator.next();
                ordersService.delete(singleorders);
            }

            Iterable<Schedule> schedule = scheduleService.listSchedule();
            Iterator<Schedule> scheduleListIterator = schedule.iterator();
            ArrayList<Schedule> singleScheduleOrderList = new ArrayList<>();

            while (scheduleListIterator.hasNext()) {
                Schedule singleorders = scheduleListIterator.next();

                if (singleorders.getOrdersid() == id && singleorders.getUserid()==userid) {
                    singleScheduleOrderList.add(singleorders);
                }
            }
            Iterator<Schedule> deleteScheduleIterator = singleScheduleOrderList.iterator();

            while (deleteScheduleIterator.hasNext()) {
                Schedule singleorders = deleteScheduleIterator.next();
                scheduleService.delete(singleorders);
            }

            return "redirect:/uservieworders";
        } else
            return "login_error";
    }


    @Scheduled(fixedRate = 20000)
    public void changeStatus() {
        Iterable<Schedule> schedule = scheduleService.listSchedule();
        Iterator<Schedule> scheduleListIterator = schedule.iterator();
        boolean done = false;
        while (scheduleListIterator.hasNext()&&!done) {
            Schedule singleorders = scheduleListIterator.next();
            Calendar calendarA = Calendar.getInstance();
            Calendar calendarB = Calendar.getInstance();
            calendarB.setTime(singleorders.getEndTime());
            if (calendarA.getTime().after(calendarB.getTime())) {

                Iterable<Orders> orderList = ordersService.listAllOrders();
                Iterator<Orders> orderListIterator = orderList.iterator();

                while (orderListIterator.hasNext() &&!done) {
                    Orders od = orderListIterator.next();

                    if (od.getordersid()==singleorders.getOrdersid() && od.getStatus().equals("Processing")) {
                        ordersService.updateStatus(od,"Ready to pickup");
                        done=true;
                        System.out.println("Status set: Ready to pick up! Order: " + od.getordersid());
                    }
                }
            }

            else if (calendarA.getTime().after(singleorders.getStartTime())) {

                Iterable<Orders> orderList = ordersService.listAllOrders();
                Iterator<Orders> orderListIterator = orderList.iterator();

                while (orderListIterator.hasNext() && !done) {
                    Orders od = orderListIterator.next();

                    if (od.getordersid()==singleorders.getOrdersid() && od.getStatus().equals("Order Placed")) {
                        ordersService.updateStatus(od,"Processing");
                        done=true;
                        System.out.println("Status set: Processing! Order: " + od.getordersid());

                    }
                }
            }



        }

    }
}