package com.example.services;

import java.sql.Time;
import java.sql.Date;

import com.example.model.OrderKey;
import com.example.model.Orders;

public interface OrdersService {


        public Orders create(Orders orders);
        public Orders delete(Orders order);
        public Orders update(Orders orders, Date date, Time time, String status);
        public Orders updateStatus(Orders orders, String status);
        public Orders findById(OrderKey id);
        public Iterable<Orders> listAllOrders();
}
