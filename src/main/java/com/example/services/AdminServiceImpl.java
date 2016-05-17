package com.example.services;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.example.model.Admin;
import com.example.repositories.AdminRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wayne.
 */
@Service
public class AdminServiceImpl implements AdminService{
    @Resource
    private AdminRepository adminRepository;

   
    @Transactional
    public Admin create(Admin admin) {
        Admin createdAdmin = admin;
        return adminRepository.save(createdAdmin);
    }

    
    @Transactional
    public Admin findById(int id) {
        return adminRepository.findOne(id);
    }

   
    @Transactional
    public Admin delete(int id) {
        Admin deletedAdmin = adminRepository.findOne(id);
        adminRepository.delete(deletedAdmin);
        return deletedAdmin;
    }


   
    @Transactional
    public Admin update(Admin admin) {
        Admin updatedAdmin = adminRepository.findOne(admin.getUserId());

        if (updatedAdmin == null)


        updatedAdmin.setUserName(admin.getUserName());
        return updatedAdmin;
    }

    @Override
    public Iterable<Admin> listAllAdmins() {
        return adminRepository.findAll();
    }

}
