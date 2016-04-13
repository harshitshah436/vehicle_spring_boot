/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harshit.vehiclespringboot.service;

import com.harshit.vehiclespringboot.model.Vehicle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toMap;
import org.springframework.stereotype.Service;

/**
 * VehicleService is a service class which provides service while the
 * application is running.
 *
 * @author Harshit
 */
@Service
public class VehicleService {

    // File to read the data from.
    public final String FILE_NAME = "vehicles.txt";

    // All vehicle details are stored in this list.
    public List<Vehicle> vehicles = new ArrayList<>();

    /**
     * Default constructor.
     */
    public VehicleService() {
        try {
            readVehicalFileIntoList();
        } catch (IOException ex) {
            Logger.getLogger(VehicleService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method reads the vehicle data and stores inside a list.
     *
     * @throws IOException
     */
    @SuppressWarnings("ConvertToTryWithResources")
    private void readVehicalFileIntoList() throws IOException {
        FileReader fr = new FileReader(FILE_NAME);
        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null) {
            String[] s_array = line.split(",");
            Vehicle vcl = new Vehicle();
            vcl.setType(s_array[0].trim());
            vcl.setVin(Integer.parseInt(s_array[1].trim()));
            vcl.setBrand(s_array[2].trim());
            vcl.setColor(s_array[3].trim());
            vcl.setEngine_type(s_array[4].trim());
            vcl.setPrice(Double.parseDouble(s_array[5].trim()));
            vcl.setYear(Integer.parseInt(s_array[6].trim()));

            vehicles.add(vcl);
        }

        br.close();
    }

    /**
     * This method returns a vehicle list sorted by price in ascending order.
     *
     * @return
     */
    public List<Vehicle> getAllVehiclesByPrice() {

        List<Vehicle> orderVehiclesByPrice = new ArrayList<>(vehicles);

        // Override this method into Vehicle class.
        Collections.sort(orderVehiclesByPrice);

        return orderVehiclesByPrice;
    }

    // All following methods are simplified by using a database and querying from database by 'GroupBy' operation. But here, I am using maps to sort, calculating average cost.
    /*
     * Get a map of avg cost by vehicle type.
     */
    public Map<String, Double> getAvgCostByType() {

        // Maps to keep a track of sum and total count.
        Map<String, Double> typePriceMap = new HashMap<>();
        Map<String, Integer> typeCountMap = new HashMap<>();

        for (Vehicle vhl : vehicles) {
            String key = vhl.getType();
            if (typePriceMap.containsKey(key)) {
                Double value = typePriceMap.get(key);
                typePriceMap.put(key, value + vhl.getPrice());
                typeCountMap.put(key, typeCountMap.get(key) + 1);
            } else {
                typePriceMap.put(key, vhl.getPrice());
                typeCountMap.put(key, 1);
            }
        }

        // Calculating average.
        for (Map.Entry<String, Double> entry : typePriceMap.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            typePriceMap.put(key, value / typeCountMap.get(key));
        }

        // works only with Java 1.8 or higher.
        Map<String, Double> sortedMap = typePriceMap.entrySet().stream()
                .sorted(Entry.comparingByValue())
                .collect(toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        return sortedMap;
    }

    /*
     * Get a map of avg cost by vehicle brand.
     */
    public Map<String, Double> getAvgCostByBrand() {

        // Maps to keep a track of sum and total count.
        Map<String, Double> brandPriceMap = new HashMap<>();
        Map<String, Integer> brandCountMap = new HashMap<>();

        for (Vehicle vhl : vehicles) {
            String key = vhl.getBrand();
            if (brandPriceMap.containsKey(key)) {
                Double value = brandPriceMap.get(key);
                brandPriceMap.put(key, value + vhl.getPrice());
                brandCountMap.put(key, brandCountMap.get(key) + 1);
            } else {
                brandPriceMap.put(key, vhl.getPrice());
                brandCountMap.put(key, 1);
            }
        }

        // Calculating average.
        for (Map.Entry<String, Double> entry : brandPriceMap.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            brandPriceMap.put(key, value / brandCountMap.get(key));
        }

        Map<String, Double> sortedMap = brandPriceMap.entrySet().stream()
                .sorted(Entry.comparingByValue())
                .collect(toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        return sortedMap;
    }

    /*
     * Get a map of avg cost by vehicle engine type.
     */
    public Map<String, Double> getAvgCostByEngineType() {

        // Maps to keep a track of sum and total count.
        Map<String, Double> enginePriceMap = new HashMap<>();
        Map<String, Integer> engineCountMap = new HashMap<>();

        for (Vehicle vhl : vehicles) {
            String key = vhl.getEngine_type();
            if (enginePriceMap.containsKey(key)) {
                Double value = enginePriceMap.get(key);
                enginePriceMap.put(key, value + vhl.getPrice());
                engineCountMap.put(key, engineCountMap.get(key) + 1);
            } else {
                enginePriceMap.put(key, vhl.getPrice());
                engineCountMap.put(key, 1);
            }
        }

        // Calculating average.
        for (Map.Entry<String, Double> entry : enginePriceMap.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            enginePriceMap.put(key, value / engineCountMap.get(key));
        }

        Map<String, Double> sortedMap = enginePriceMap.entrySet().stream()
                .sorted(Entry.comparingByValue())
                .collect(toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        return sortedMap;
    }

    /*
     * Get a map of avg cost by vehicle color.
     */
    public Map<String, Double> getAvgCostByColor() {

        // Maps to keep a track of sum and total count.
        Map<String, Double> colorPriceMap = new HashMap<>();
        Map<String, Integer> colorCountMap = new HashMap<>();

        for (Vehicle vhl : vehicles) {
            String key = vhl.getColor();
            if (colorPriceMap.containsKey(key)) {
                Double value = colorPriceMap.get(key);
                colorPriceMap.put(key, value + vhl.getPrice());
                colorCountMap.put(key, colorCountMap.get(key) + 1);
            } else {
                colorPriceMap.put(key, vhl.getPrice());
                colorCountMap.put(key, 1);
            }
        }

        // Calculating average.
        for (Map.Entry<String, Double> entry : colorPriceMap.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            colorPriceMap.put(key, value / colorCountMap.get(key));
        }

        Map<String, Double> sortedMap = colorPriceMap.entrySet().stream()
                .sorted(Entry.comparingByValue())
                .collect(toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        return sortedMap;
    }
}
