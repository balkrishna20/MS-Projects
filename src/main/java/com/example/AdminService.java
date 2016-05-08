package com.example;

import com.example.model.Admin;

/**
 * Created by Wayne
 */
public interface AdminService {


        public Admin create(Admin admin);
        public Admin delete(int id);
        public Admin update(Admin admin);
        public Admin findById(int id);
}
