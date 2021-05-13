package ir.ac.kntu.models;

import java.util.Objects;

public class Settings {
    private RestaurantsListingStrategy restaurantsListingStrategy;

    public Settings(RestaurantsListingStrategy restaurantsListingStrategy) {
        this.restaurantsListingStrategy = restaurantsListingStrategy;
    }

    public RestaurantsListingStrategy getRestaurantsListingStrategy() {
        return restaurantsListingStrategy;
    }

    public void setRestaurantsListingStrategy(RestaurantsListingStrategy restaurantsListingStrategy) {
        this.restaurantsListingStrategy = restaurantsListingStrategy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Settings settings = (Settings) o;
        return restaurantsListingStrategy == settings.restaurantsListingStrategy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurantsListingStrategy);
    }

    @Override
    public String toString() {
        return "restaurantsListingStrategy=" + restaurantsListingStrategy;
    }
}
