package com.example.repositories;

/**
 * Created by wayne
 */

import com.example.model.Menuitems;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MenuitemsRepository extends CrudRepository<Menuitems, Integer> {


}
