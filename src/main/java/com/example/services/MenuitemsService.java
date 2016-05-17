package com.example.services;

import com.example.model.Menuitems;

/**
 * Created by Wayne.
 */
public interface MenuitemsService {


        public Menuitems create(Menuitems profile);
        public Menuitems delete(int id);
        public Menuitems update(Menuitems profile);
        public Menuitems findById(int id);
        Iterable<Menuitems> listAllMenuitems();
}
