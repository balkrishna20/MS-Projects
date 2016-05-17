package com.example.services;

import javax.annotation.Resource;
import com.example.model.Users;
import org.springframework.stereotype.Service;
import com.example.repositories.UsersRepository;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UsersServiceImpl implements UsersService {
    @Resource
    private UsersRepository usersRepository;
    
   
    
    

    @Override
    @Transactional
    public Users create(Users profile) {
        Users createdProfile = profile;
        return usersRepository.save(createdProfile);
    }

    @Override
    @Transactional
    public Users findById(int id) {
        return usersRepository.findOne(id);
    }

    @Override
    @Transactional
    public Users delete(int id) {
        Users deletedProfile = usersRepository.findOne(id);
        usersRepository.delete(deletedProfile);
        return deletedProfile;
    }


    @Override
    @Transactional
    public Users update(Users profile) {
        Users updatedProfile = usersRepository.findOne(profile.getNumberID());

        if (updatedProfile == null)


        updatedProfile.setFirstName(profile.getFirstName());
        return updatedProfile;
    }

    @Override
    public Iterable<Users> listAllUsers() {
        return usersRepository.findAll();
    }



}
