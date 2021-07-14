package ua.darkphantom1337.implenica.task.second;

import java.util.*;

public class SecondTaskMain {

    private static String prefix = "[Task-2] -> ";

    private static int cityAmount;
    /**
     * CityID, City
     */
    private static HashMap<Integer, City> allCity = new HashMap<>();
    /**
     * CityName,CityID
     */
    private static HashMap<String, Integer> citiIDforCityName = new HashMap<>();

    public static void main(String[] args) {
        cityAmount = 4;
        for (int currentCityID = 1; currentCityID <= cityAmount; currentCityID++) {
            /**
             * Entering data for the city, neighbors and prices for them.
             */
            System.out.print(prefix + "Write city name ");
            String cityName = new Scanner(System.in).nextLine();
            System.out.print(prefix + "Write neighbors amount ");
            String neighborsAmountS = new Scanner(System.in).nextLine();
            City city = new City(currentCityID);
            city.setCityName(cityName);
            citiIDforCityName.put(cityName, currentCityID);
            int neighborsAmount = 0;
            while (true) {
                try {
                    neighborsAmount = Integer.parseInt(neighborsAmountS);
                    if (neighborsAmount > cityAmount)
                        throw new NumberFormatException();
                    break;
                } catch (NumberFormatException e) {
                    System.out.print(prefix + " [ERROR] Write neighbors amount: ");
                    neighborsAmountS = new Scanner(System.in).nextLine();
                }
            }
            city.setNeighborsAmount(neighborsAmount);
            /**
             * Entering neighboring cities and fares to them
             */
            for (int i = 0; i < neighborsAmount; i++) {
                System.out.println(prefix + "Write CityID and price way. Example: 2 4 ");
                String[] wayAndPrice = new Scanner(System.in).nextLine().split(" ");
                int neighborCityID = currentCityID, priceToNeighborCity = 1;
                while (true) {
                    try {
                        neighborCityID = Integer.parseInt(wayAndPrice[0]);
                        priceToNeighborCity = Integer.parseInt(wayAndPrice[1]);
                        break;
                    } catch (Exception e) {
                        System.out.println(prefix + " [ERROR] Write CityID and price way. Example: 2 4  ");
                        wayAndPrice = new Scanner(System.in).nextLine().split(" ");
                    }
                }
                city.addWay(neighborCityID, priceToNeighborCity);
            }
            System.out.println();
            allCity.put(currentCityID, city);
        }
        for (Integer cityID : allCity.keySet()) {
            /**
             * Print all ways for all City
             */
            City city = allCity.get(cityID);
            HashMap<Integer, Integer> wayPrice = city.getWaysPrices();
            System.out.println("CityID: " + cityID + " CityName: " + city.getCityName() + " Ways: " + wayPrice.size());
            for (Integer destinationCityID : wayPrice.keySet())
                System.out.println("Way: " + city.getCityName() + " - " + allCity.get(destinationCityID).getCityName() + " Price: " + wayPrice.get(destinationCityID));
            System.out.println();
        }
        System.out.print("Write ways amount ");
        int marsh = new Scanner(System.in).nextInt();
        for (int i = 0; i <= marsh; i++) {
            /**
             * Writing ways
             */
            System.out.print("Write way: Example  gdansk warshawa : ");
            String[] way = new Scanner(System.in).nextLine().split(" ");
            List<Integer> visited = getTotalPrice(citiIDforCityName.get(way[0]), citiIDforCityName.get(way[1]), new ArrayList<Integer>(Arrays.asList(citiIDforCityName.get(way[0]))), 0);
            System.out.println("Total Way -> Start: " + allCity.get(visited.get(0)).getCityName()
                    + " Destination: " + allCity.get(visited.get(visited.size() - 2)).getCityName() + " Price: " + visited.get(visited.size() - 1));
        }
    }

    public static List<Integer> getTotalPrice(Integer startCityID, Integer destinationCityID, List<Integer> visited, Integer price) {
        City startCity = allCity.get(startCityID);
        int minprice = Integer.MAX_VALUE, mincityID = 0;
        /**
         * We are looking for the minimum cost of the path and call
         * the same method to find the minimum point again, and so on until we find the point of arrival.
         */
        for (Integer city : startCity.getWaysPrices().keySet()) {
            if (!visited.contains(city))
                if (startCity.getWaysPrices().get(city) < minprice) {
                    minprice = startCity.getWaysPrices().get(city);
                    mincityID = city;
                }
        }
        System.out.println(startCity.getCityName() + " - " + allCity.get(mincityID).getCityName() + " Price : " + minprice);
        visited.add(mincityID);
        price += minprice;
        if (visited.contains(destinationCityID)) {
            visited.add(price);
            return visited;
        }
        return getTotalPrice(mincityID, destinationCityID, visited, price);
    }

}
