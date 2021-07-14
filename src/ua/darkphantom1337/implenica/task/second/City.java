package ua.darkphantom1337.implenica.task.second;

import java.util.HashMap;

public class City {

    private Integer cityID;

    private int neighborsAmount;

    private String cityName;

    /**
     * HashMap<DestinationCityID,WayPrice>
     */
    private HashMap<Integer, Integer> waysPrices = new HashMap<>();

    public City(Integer cityID){
        this.cityID = cityID;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName(){
        return this.cityName;
    }

    public void setNeighborsAmount(int neighborsAmount) {
        this.neighborsAmount = neighborsAmount;
    }

    public void addWay(Integer destinationCityID, Integer wayPrice){
        waysPrices.put(destinationCityID, wayPrice);
    }

    public HashMap<Integer, Integer> getWaysPrices() {
        return waysPrices;
    }
}
