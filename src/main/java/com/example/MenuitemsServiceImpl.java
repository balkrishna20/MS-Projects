package com.example;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.example.model.Menuitems;
import com.example.repositories.MenuitemsRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by rominoushana on 4/2/16.
 */
@Service
public class MenuitemsServiceImpl implements MenuitemsService {
    @Resource
    private MenuitemsRepository menuitemsRepository;
            

    @Override
    @Transactional
    public Menuitems create(Menuitems menuitems) {
        Menuitems createdMenuitems = menuitems;
        return menuitemsRepository.save(createdMenuitems);
    }

    @Override
    @Transactional
    public Menuitems findById(int id) {
        return menuitemsRepository.findOne(id);
    }

    @Override
    @Transactional
    public Menuitems delete(int id) {
        Menuitems deletedmenuitems = menuitemsRepository.findOne(id);
        menuitemsRepository.delete(deletedmenuitems);
        return deletedmenuitems;
    }


    @Override
    @Transactional
    public Menuitems update(Menuitems menuitems) {
        Menuitems updatedMenuitems = menuitemsRepository.findOne(menuitems.getMenuid());

        if (updatedMenuitems == null)


        updatedMenuitems.setName(menuitems.getName());
        return updatedMenuitems;
    }
    
    @Override
    public Iterable<Menuitems> listAllMenuitems() {
        return menuitemsRepository.findAll();
    }


}
