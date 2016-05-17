package com.example.services;



import com.example.model.Schedule;

public interface ScheduleService {


        public Schedule create(Schedule schedule);
        public Schedule delete(Schedule schedule);
        public Schedule update(Schedule schedule);
        public Schedule findById(int id);
        public Iterable<Schedule> listSchedule();
        
}
