package com.example.repositories;

/**
 * Created by rominoushana on 3/29/16.
 */

import com.example.model.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProfileRepository extends CrudRepository<Profile, Integer> {


}
