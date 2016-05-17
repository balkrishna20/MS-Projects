package com.example.services;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.example.model.Profile;
import com.example.repositories.ProfileRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rominoushana on 4/2/16.
 */
@Service
public class ProfileServiceImpl implements ProfileService {
    @Resource
    private ProfileRepository profileRepository;

    @Override
    @Transactional
    public Profile create(Profile profile) {
        Profile createdProfile = profile;
        return profileRepository.save(createdProfile);
    }

    @Override
    @Transactional
    public Profile findById(int id) {
        return profileRepository.findOne(id);
    }

    @Override
    @Transactional
    public Profile delete(int id) {
        Profile deletedProfile = profileRepository.findOne(id);
        profileRepository.delete(deletedProfile);
        return deletedProfile;
    }


    @Override
    @Transactional
    public Profile update(Profile profile) {
        Profile updatedProfile = profileRepository.findOne(profile.getNumberID());

        if (updatedProfile == null)


        updatedProfile.setFirstName(profile.getFirstName());
        return updatedProfile;
    }


}
