package model;

import exception.WrongOrderException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
}