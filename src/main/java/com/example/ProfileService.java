package com.example;

import com.example.model.Profile;

/**
 * Created by rominoushana on 4/2/16.
 */
public interface ProfileService {


        public Profile create(Profile profile);
        public Profile delete(int id);
        public Profile update(Profile profile);
        public Profile findById(int id);
}
