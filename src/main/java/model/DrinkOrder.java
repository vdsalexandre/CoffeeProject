package model;

import exception.WrongOrderException;

public class DrinkOrder {
    private final static String COMMAND_SEPARATOR = ":";
    private Order order;

    public DrinkOrder(Order order) throws WrongOrderException {
        if (order != null)
            this.order = order;
        else
            throw new WrongOrderException("Null order ...");
    }

    public String generateDrinkMakerCommand() {
        return order.getOrderCode() + COMMAND_SEPARATOR +
                                order.getStringSugarQuantity() + COMMAND_SEPARATOR +
                                order.getStringStickOrNot();
    }
}
