/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harshit.vehiclespringboot.controller;

import com.harshit.vehiclespringboot.model.Vehicle;
import com.harshit.vehiclespringboot.service.VehicleService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * VehicleController class is a rest controller class that provides rest
 * services to clients according to their requests.
 *
 * @author Harshit
 */
@RestController
public class VehicleController {

    @Autowired
    VehicleService vs;

    @RequestMapping("/all")
    public List<Vehicle> getAllVehicles() {
        return vs.vehicles;
    }

    @RequestMapping("/price")
    public List<Vehicle> getAllVehiclesByPrice() {
        return vs.getAllVehiclesByPrice();
    }

    @RequestMapping("/type")
    public Map<String, Double> getAvgCostByType() {
        return vs.getAvgCostByType();
    }

    @RequestMapping("/brand")
    public Map<String, Double> getAvgCostByBrand() {
        return vs.getAvgCostByBrand();
    }

    @RequestMapping("/enginetype")
    public Map<String, Double> getAvgCostByEngineType() {
        return vs.getAvgCostByEngineType();
    }

    @RequestMapping("/color")
    public Map<String, Double> getAvgCostByColor() {
        return vs.getAvgCostByColor();
    }
}
