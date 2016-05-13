package com.example.model;

import javax.persistence.*;

/**
 * Created by Wayne.
 */
@Entity
public class Menuitems {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "menuid")
    public Integer menuid;

    @Column(name = "category")
    public String category;
    @Column(name = "name")
    public String name;
    @Column(name = "picture")
    public String picture;
    @Column(name = "unitprice")
    public float unitprice;
    @Column(name = "calories")
    private int calories;
    @Column(name = "preparationtime")
    private int preparationtime;

	public Integer getMenuid() {
		return menuid;
	}
	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}

	public float getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(float unitprice) {
		this.unitprice = unitprice;
	}

	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}

	public int getPreparationtime() {
		return preparationtime;
	}
	public void setPreparationtime(int preparationtime) {
		this.preparationtime = preparationtime;
	}


    
}

