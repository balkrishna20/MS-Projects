package com.example.services;



import com.example.model.Users;

/**
 * Created by bal.krishna on 4/29/16.
 */
public interface UsersService {


        public Users create(Users user);
        public Users delete(int id);
        public Users update(Users user);
        public Users findById(int id);
        public Iterable<Users> listAllUsers();
        
}
