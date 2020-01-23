package model;

import exception.WrongOrderException;

public class DrinkOrder {
    private final static String COMMAND_SEPARATOR = ":";
    private Order order;
    private double cashForDrinkOrder;

    public Order getOrder() {
        return order;
    }

    public DrinkOrder(Order order) throws WrongOrderException {
        if (order != null) {
            this.order = order;
            this.cashForDrinkOrder = 0;
        }
        else
            throw new WrongOrderException("Null order ...");
    }

    public String generateDrinkMakerCommand() {
        return order.getOrderCode() + order.isStringExtraHot() + COMMAND_SEPARATOR +
                                order.getStringSugarQuantity() + COMMAND_SEPARATOR +
                                order.getStringStickOrNot();
    }

    public String generateDrinkMakerMessage() {
        return order.getOrderCode() + COMMAND_SEPARATOR +
                                order.getMessage();
    }

    public String generateDrinkMakerMessageOrderPaid() {
        return "M" + COMMAND_SEPARATOR +
                                "DrinkOrder paid, money to return to client = " + getCashToReturnToClient() + "€";
    }

    public String generateDrinkMakerMessageOrderNotPaid() {
        return "M" + COMMAND_SEPARATOR +
                                "DrinkOrder not paid, not enough money given, needed = " + getCashNeededToPayOrder() + "€";
    }

    public double getCashForDrinkOrder() {
        return cashForDrinkOrder;
    }

    public void setCashForDrinkOrder(double cashForDrinkOrder) {
        this.cashForDrinkOrder = cashForDrinkOrder;
    }

    public double getCashToReturnToClient() {
        if (isDrinkOrderPaid()) {
            return cashForDrinkOrder - Drinks.valueOf(order.getDrinkName().toUpperCase()).getPrice();
        }
        else
            return 0;
    }

    public double getCashNeededToPayOrder() {
        if (!isDrinkOrderPaid()) {
            return Drinks.valueOf(order.getDrinkName().toUpperCase()).getPrice() - cashForDrinkOrder;
        }
        return 0;
    }

    public boolean isDrinkOrderPaid() {
        if (cashForDrinkOrder >= Drinks.valueOf(order.getDrinkName().toUpperCase()).getPrice())
            return true;

        return false;
    }
}
