package model;

import exception.WrongOrderException;

public class Order {
    private char orderCode;
    private Integer sugarQuantity;
    private Integer stickOrNot;
    private String message;
    private boolean extraHot;

    public Order(char orderCode, Integer sugarQuantity, Integer stickOrNot) throws WrongOrderException {
        if (isValidOrder(orderCode, sugarQuantity, stickOrNot)) {
            this.orderCode = orderCode;
            this.sugarQuantity = sugarQuantity;
            this.stickOrNot = stickOrNot;
        }
        else
            throw new WrongOrderException("Wrong order ...");
    }

    public Order(char orderCode, String message) throws WrongOrderException {
        this.orderCode = orderCode;

        if (isMessageOrder())
            this.message = message;
        else
            throw new WrongOrderException("Wrong message ...");
    }

    public Order(char orderCode, Integer sugarQuantity, Integer stickOrNot, boolean extraHot) throws WrongOrderException {
        if (isValidOrder(orderCode, sugarQuantity, stickOrNot)) {
            this.orderCode = orderCode;
            this.sugarQuantity = sugarQuantity;
            this.stickOrNot = stickOrNot;
            this.extraHot = extraHot;
        }
        else
            throw new WrongOrderException("Wrong order ...");
    }

    public char getOrderCode() {
        return orderCode;
    }

    public Integer getSugarQuantity() {
        return sugarQuantity;
    }

    public String getStringSugarQuantity() {
        return sugarQuantity != null ? String.valueOf(sugarQuantity) : "";
    }

    public Integer getStickOrNot() {
        return stickOrNot;
    }

    public String getStringStickOrNot() {
        return stickOrNot != null ? String.valueOf((stickOrNot)) : "";
    }

    public String getMessage() {
        return message;
    }

    public boolean isExtraHot() {
        return extraHot;
    }

    public String isStringExtraHot() {
        return extraHot ? "h" : "";
    }

    public String getDrinkName() {
        for (Drinks drink : Drinks.values()) {
            if (drink.getCode() == orderCode)
                return drink.getName();
        }
        return "";
    }

    @Override
    public String toString() {
        if (isMessageOrder()) {
            return "Order { " + "\n\t" +
                        "orderCode= " + orderCode + ",\n\t" +
                        "message= " + message + "\n" +
                    "}";
        }
        else {
            return "Order { " + ",\n\t" +
                    "orderCode= " + orderCode + ",\n\t" +
                    "extraHot= " + extraHot + ",\n\t" +
                    "sugarQuantity= " + sugarQuantity + ",\n\t" +
                    "stickOrNot= " + stickOrNot + ",\n" +
                    '}';
        }
    }

    public boolean isValidCode(char code) {
        for (Drinks drink : Drinks.values()) {
            if (drink.getCode() == code)
                return true;
        }

        if (isMessageOrder())
            return true;

        return false;
    }

    public boolean isValidSugarQuantity(Integer quantity) {
        try {
            if (quantity < 1 || quantity > 10)
                return false;
            else
                return true;
        } catch (Exception ex) {
            return true;
        }
    }

    public boolean isValidStickOrNot(Integer stickOrNot) {
        try {
            if (stickOrNot == 0)
                return true;
            else
                return false;
        } catch (Exception ex) {
            return true;
        }
    }

    public boolean isValidOrder(char code, Integer sugarQuantity, Integer stickOrNot) {
        if (!isValidCode(code)) return false;

        if (!isValidSugarQuantity(sugarQuantity)) return false;

        if (!isValidStickOrNot(stickOrNot)) return false;

        return true;
    }

    private boolean isMessageOrder() {
        return orderCode == 'M';
    }
}
