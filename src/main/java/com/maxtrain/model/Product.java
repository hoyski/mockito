package com.maxtrain.model;

import java.math.BigDecimal;

public class Product {
    private int id;
    private String name;
    private String description;
    private BigDecimal cost;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public BigDecimal getCost() {
	return cost;
    }

    public void setCost(BigDecimal cost) {
	this.cost = cost;
    }

}
