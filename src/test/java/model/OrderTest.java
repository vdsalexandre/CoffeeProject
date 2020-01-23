package model;

import exception.WrongOrderException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    public void createOrderWithGoodDrinkCode() throws WrongOrderException {
        Order order = new Order('T', 1, 0);
        assertEquals(true, order.isValidCode(order.getOrderCode()));
    }

    @Test
    public void createOrderWithWrongDrinkCode() throws WrongOrderException {
        assertThrows(WrongOrderException.class, () -> {
            Order order = new Order('Z', 1, 0);
            assertEquals(false, order.isValidCode(order.getOrderCode()));
        });
    }

    @Test
    public void createOrderWithWrongSugarQuantity() throws WrongOrderException {
        assertThrows(WrongOrderException.class, () -> {
            Order order = new Order('T', -1, 0);
            assertEquals(false, order.isValidSugarQuantity(order.getSugarQuantity()));
        });

        assertThrows(WrongOrderException.class, () -> {
            Order order1 = new Order('T', 0, 0);
            assertEquals(false, order1.isValidSugarQuantity(order1.getSugarQuantity()));
        });

        assertThrows(WrongOrderException.class, () -> {
            Order order2 = new Order('T', 25, 0);
            assertEquals(false, order2.isValidSugarQuantity(order2.getSugarQuantity()));
        });
    }

    @Test
    public void createOrderWithGoodSugarQuantity() throws WrongOrderException {
        Order order = new Order('T', null, 0);
        assertEquals(true, order.isValidSugarQuantity(order.getSugarQuantity()));

        Order order1 = new Order('H', 2, 0);
        assertEquals(true, order1.isValidSugarQuantity(order1.getSugarQuantity()));

        Order order2 = new Order('C', 3, 0);
        assertEquals(true, order2.isValidSugarQuantity(order2.getSugarQuantity()));
    }

    @Test
    public void createOrderWithWrongStickOrNot() throws WrongOrderException {
        assertThrows(WrongOrderException.class, () -> {
            Order order = new Order('C', null, 2);
            assertEquals(false, order.isValidStickOrNot(order.getStickOrNot()));
        });

        assertThrows(WrongOrderException.class, () -> {
            Order order1 = new Order('C', 1, 1);
            assertEquals(false, order1.isValidStickOrNot(order1.getStickOrNot()));
        });
    }

    @Test
    public void createOrderWithGoodStickOrNot() throws WrongOrderException {
        Order order = new Order('H', 1, 0);
        assertEquals(true, order.isValidStickOrNot(order.getStickOrNot()));

        Order order1 = new Order('C', 1, null);
        assertEquals(true, order1.isValidStickOrNot(order1.getStickOrNot()));
    }

    @Test
    public void createWrongOrder() throws WrongOrderException {
        assertThrows(WrongOrderException.class, () -> {
            Order order = new Order('T', null, 2);
        });
    }

    @Test
    public void createMessageOrder() throws WrongOrderException {
        Order order = new Order('M', "New message");
        assertEquals('M', order.getOrderCode());
        assertEquals("New message", order.getMessage());
    }
}