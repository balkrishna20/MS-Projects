package com.example.repositories;

/**
 * Created by rominoushana on 3/29/16.
 */

import com.example.domain.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, String> {


}
