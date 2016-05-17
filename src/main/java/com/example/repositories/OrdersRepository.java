package com.example.repositories;

/**
 * Created by Wayne on 5/6/16.
 */

import com.example.model.OrderKey;
import com.example.model.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OrdersRepository extends CrudRepository<Orders, OrderKey> {


}
