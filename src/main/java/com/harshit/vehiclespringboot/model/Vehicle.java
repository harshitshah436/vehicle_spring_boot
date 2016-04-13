/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harshit.vehiclespringboot.model;

import java.util.Comparator;

/**
 * Vehicle class is a POJO class which represents vehicle model. It also
 * overrides Collection.sort and toString methods.
 *
 * @author Harshit
 */
public class Vehicle implements Comparable<Vehicle> {

    // Posibble attributes of vehicle.
    private String type;
    private int vin;
    private String brand;
    private String color;
    private String engine_type;
    private double price;
    private int year;

    // getters and setters.
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEngine_type() {
        return engine_type;
    }

    public void setEngine_type(String engine_type) {
        this.engine_type = engine_type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Override toString method to display vehicle object.
     *
     * @return
     */
    @Override
    public String toString() {
        return "Vehicle{"
                + "type=" + type
                + ", vin=" + vin + '\''
                + ", brand='" + brand + '\''
                + ", color='" + color + '\''
                + ", engine_type='" + engine_type + '\''
                + ", price='" + price + '\''
                + ", year='" + year + '\''
                + '}';
    }

    /**
     * This method implements comparison of two vehicles based on price and
     * returns in ascending order.
     *
     * @param o vehicle object
     * @return
     */
    @Override
    public int compareTo(Vehicle o) {
        return Comparators.PRICE.compare(this, o);
    }

    public static class Comparators {

        // This type of comparision will applied from java 1.8.
        public static final Comparator<Vehicle> PRICE = (Vehicle o1, Vehicle o2) -> Double.compare(o1.price, o2.price);
    }
}
