package model;

import exception.WrongOrderException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DrinkOrderTest {

    @Test
    public void createDrinkOrder() throws WrongOrderException {
        DrinkOrder drinkOrder = new DrinkOrder(new Order('C', 1, 0));
        assertEquals("C:1:0", drinkOrder.generateDrinkMakerCommand());
    }

    @Test
    public void createWrongDrinkOrder() throws WrongOrderException {
        assertThrows(WrongOrderException.class, () -> {
            DrinkOrder drinkOrder = new DrinkOrder(null);
        });
    }

    @Test
    public void createDrinkOrderNoSugar() throws WrongOrderException {
        DrinkOrder drinkOrder = new DrinkOrder(new Order('H', null, 0));
        assertEquals("H::0", drinkOrder.generateDrinkMakerCommand());
    }

    @Test
    public void createDrinkOrderWithSugar() throws WrongOrderException {
        DrinkOrder drinkOrder = new DrinkOrder(new Order('T', 2, 0));
        assertEquals("T:2:0", drinkOrder.generateDrinkMakerCommand());
    }

    @Test
    public void createDrinkOrderNoStick() throws WrongOrderException {
        DrinkOrder drinkOrder = new DrinkOrder(new Order('C', null, null));
        assertEquals("C::", drinkOrder.generateDrinkMakerCommand());
    }

    @Test
    public void createDrinkOrderWithStick() throws WrongOrderException {
        DrinkOrder drinkOrder = new DrinkOrder(new Order('C', 2, 0));
        assertEquals("C:2:0", drinkOrder.generateDrinkMakerCommand());
    }

    @Test
    public void createDrinkOrderMessage() throws WrongOrderException {
        DrinkOrder drinkOrder = new DrinkOrder(new Order('M', "Need more sugar"));
        assertEquals("M:" + drinkOrder.getOrder().getMessage(), drinkOrder.generateDrinkMakerMessage());
    }

    @Test
    public void createWrongDrinkOrderMessage() throws WrongOrderException {
        assertThrows(WrongOrderException.class, () -> {
           DrinkOrder drinkOrder = new DrinkOrder(new Order('C', "wrong message because of wrong code"));
        });
    }

    @Test
    public void createDrinkOrderWithPay() throws WrongOrderException {
        DrinkOrder drinkOrder = new DrinkOrder(new Order('T', 1, 0));
        drinkOrder.setCashForDrinkOrder(2);
        assertEquals(true, drinkOrder.isDrinkOrderPaid());
        assertEquals(1.6, drinkOrder.getCashToReturnToClient());
        System.out.println(drinkOrder.generateDrinkMakerMessageOrderPaid());
    }

    @Test
    public void createDrinkOrderWithNoPay() throws WrongOrderException {
        DrinkOrder drinkOrder = new DrinkOrder(new Order('C', null, 0));
        assertEquals(false, drinkOrder.isDrinkOrderPaid());
        assertEquals(0.6, drinkOrder.getCashNeededToPayOrder());
        System.out.println(drinkOrder.generateDrinkMakerMessageOrderNotPaid());

        DrinkOrder drinkOrder2 = new DrinkOrder(new Order('H', 1, 0));
        drinkOrder2.setCashForDrinkOrder(0.3);
        assertEquals(false, drinkOrder2.isDrinkOrderPaid());
        assertEquals(0.2, drinkOrder2.getCashNeededToPayOrder());
        System.out.println(drinkOrder2.generateDrinkMakerMessageOrderNotPaid());
    }

    @Test
    public void createDrinkOrderWithNewDrink() throws WrongOrderException {
        DrinkOrder drinkOrder = new DrinkOrder(new Order('O', null, null));
        assertEquals("O::", drinkOrder.generateDrinkMakerCommand());
        drinkOrder.setCashForDrinkOrder(1);
        assertEquals(true, drinkOrder.isDrinkOrderPaid());
        assertEquals(0.4, drinkOrder.getCashToReturnToClient());
    }

    @Test
    public void createDrinkOrderWithExtraHotDrink() throws WrongOrderException {
        DrinkOrder drinkOrder = new DrinkOrder(new Order('C', 3, 0, true));
        assertEquals("Ch:3:0", drinkOrder.generateDrinkMakerCommand());

        DrinkOrder drinkOrder1 = new DrinkOrder(new Order('H', null, 0, true));
        assertEquals("Hh::0", drinkOrder1.generateDrinkMakerCommand());

        DrinkOrder drinkOrder2 = new DrinkOrder(new Order('T', 1, null, false));
        assertEquals("T:1:", drinkOrder2.generateDrinkMakerCommand());
    }
}