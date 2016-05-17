package com.example.services;

import java.sql.Date;
import java.sql.Time;

import javax.annotation.Resource;

import com.example.model.OrderKey;
import org.springframework.stereotype.Service;
import com.example.model.Orders;
import com.example.repositories.OrdersRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
public class OrdersServiceImpl implements OrdersService {
    @Resource
    private OrdersRepository ordersRepository;

    @Override
    @Transactional
    public Orders create(Orders orders) {
        Orders createdOrders = orders;
        return ordersRepository.save(createdOrders);
    }

    @Override
    @Transactional
    public Orders findById(OrderKey id) {
        return ordersRepository.findOne(id);
    }

    @Override
    @Transactional
    public Orders delete(Orders order) {
        ordersRepository.delete(order);
        return order;
    }


    @Override
    @Transactional
    public Orders update(Orders orders,Date date,Time time, String status) {
        int menuId = orders.getMenuid();
        int orderId = orders.getordersid();
        int userId = orders.getUserid();
        OrderKey orderKey = new OrderKey(orderId, userId, menuId);
        Orders updatedOrders = ordersRepository.findOne(orderKey);

        if (updatedOrders != null){
            updatedOrders.setSqlDate(date);
            updatedOrders.setSqlTime(time);
            updatedOrders.setStatus((status));
        }

        return null;
    }

    @Override
    @Transactional
    public Orders updateStatus(Orders orders,String status) {
        int menuId = orders.getMenuid();
        int orderId = orders.getordersid();
        int userId = orders.getUserid();
        OrderKey orderKey = new OrderKey(orderId, userId, menuId);
        Orders updatedOrders = ordersRepository.findOne(orderKey);
        if (updatedOrders != null) {
            updatedOrders.setStatus((status));
        }
        return updatedOrders;
    }

    @Override
    public Iterable<Orders> listAllOrders() {
        return ordersRepository.findAll();
    }




}
