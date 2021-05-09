package ir.ac.kntu.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class FoodMenu {
    private Set<Food> foods;

    public FoodMenu(Set<Food> foods) {
        this.foods = foods;
    }

    public Set<Food> getFoods() {
        return new HashSet<>(foods);
    }

    public void setFoods(Set<Food> foods) {
        this.foods = foods;
    }

    public void addFood(Food food){
        foods.add(food);
    }

    public boolean removeFood(Food food){
       return foods.remove(food);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        FoodMenu foodMenu = (FoodMenu) o;
        return foods.equals(foodMenu.foods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foods);
    }

    @Override
    public String toString() {
        return "foods=" + foods;
    }
}
