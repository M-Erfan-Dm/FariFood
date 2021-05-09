package ir.ac.kntu.utils;

public class RestaurantIdGenerator {
    private static int id = 101;

    private RestaurantIdGenerator(){}


    public static int generateNewId(){
        return id++;
    }
}
