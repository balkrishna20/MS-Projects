package com.example.services;

import com.example.model.Schedule;
import com.example.model.Users;
import com.example.repositories.ScheduleRepository;
import com.example.repositories.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by bal.krishna on 4/29/16.
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Resource
    private ScheduleRepository scheduleRepository;
    
   
    
    

    @Override
    @Transactional
    public Schedule create(Schedule schedule) {
        Schedule createdSchedule = schedule;
        return scheduleRepository.save(createdSchedule);
    }

    @Override
    @Transactional
    public Schedule findById(int id) {
        return scheduleRepository.findOne(id);
    }

    @Override
    @Transactional
    public Schedule delete(Schedule schedule) {
        scheduleRepository.delete(schedule);
        return schedule;
    }


    @Override
    @Transactional
    public Schedule update(Schedule updatedProfile) {
       updatedProfile = scheduleRepository.findOne(updatedProfile.getScheduleID());

        return updatedProfile;
    }

    @Override
    public Iterable<Schedule> listSchedule() {
        return scheduleRepository.findAll();
    }



}
