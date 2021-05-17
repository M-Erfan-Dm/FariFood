package ir.ac.kntu.menu.restaurant;

public enum RestaurantInfoOption {
    GENERAL,
    FOOD,
    COURIER,
    ORDER,
    FEEDBACK;

    public static void printOptions(){
        System.out.println(
                "1.General info\n" +
                        "2.Food\n" +
                        "3.Courier\n" +
                        "4.Order\n" +
                        "5.Feedback\n"
        );
    }
}
