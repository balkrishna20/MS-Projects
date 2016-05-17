package com.example.repositories;


import com.example.model.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ScheduleRepository extends CrudRepository<Schedule, Integer> {


}
