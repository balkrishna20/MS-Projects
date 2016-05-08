package com.example;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.example.model.Orders;
import com.example.repositories.OrdersRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Wayne
 */
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
    public Orders findById(int id) {
        return ordersRepository.findOne(id);
    }

    @Override
    @Transactional
    public Orders delete(int id) {
        Orders deletedOrders = ordersRepository.findOne(id);
        ordersRepository.delete(deletedOrders);
        return deletedOrders;
    }


    @Override
    @Transactional
    public Orders update(Orders orders) {
//        Orders updatedOrders = ordersRepository.findOne(orders.getNumberID());
//
//        if (updatedProfile == null)
//
//
//        updatedProfile.setFirstName(profile.getFirstName());
     return null;
    }


}
