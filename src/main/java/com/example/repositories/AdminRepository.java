package com.example.repositories;

/**
 * Created by Wayne
 */

import com.example.model.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AdminRepository extends CrudRepository<Admin, Integer> {


}