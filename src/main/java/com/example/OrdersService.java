package com.example;

import com.example.model.Orders;

/**
 * Created by Wayne
 */
public interface OrdersService {


        public Orders create(Orders orders);
        public Orders delete(int id);
        public Orders update(Orders orders);
        public Orders findById(int id);
}
