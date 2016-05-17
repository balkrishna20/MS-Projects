package com.example.model;

import java.io.Serializable;

/**
 * Created by 212556710 on 5/11/16.
 */
public class OrderKey implements Serializable {

    public Integer ordersid;
    public Integer userid;
    public Integer menuid;

    public OrderKey(){}

    public OrderKey(int ordersid, int userid, int menuid) {
        super();
        this.ordersid = ordersid;
        this.userid = userid;
        this.menuid = menuid;
    }

}
