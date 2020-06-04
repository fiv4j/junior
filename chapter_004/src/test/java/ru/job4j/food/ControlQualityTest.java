package ru.job4j.food;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ControlQualityTest {

    Storage wareHouse, shop, trash;
    Calendar createDate, expireDate;
    ControlQuality test;


    @Before
    public void before() {
        wareHouse = new WareHouse();
        shop = new Shop();
        trash = new Trash();
        test = new ControlQuality(wareHouse, shop, trash);

        createDate = GregorianCalendar.getInstance();
        expireDate = GregorianCalendar.getInstance();
    }

    @Test
    public void whenFoodRedistributeFromWarehouseToTrash() {
        createDate.add(Calendar.MONTH, -2);
        expireDate.add(Calendar.MONTH, -1);

        Food bread = new Food("bread", createDate, expireDate, 30);
        wareHouse.add(bread);

        assertThat(wareHouse.getStorage().isEmpty(), is(false));
        assertThat(shop.getStorage().isEmpty(), is(true));
        assertThat(trash.getStorage().isEmpty(), is(true));

        test.redistribute();

        String result = trash.getStorage().get(0).getName();
        String expected = "bread";

        assertThat(result, is(expected));
        assertThat(wareHouse.getStorage().isEmpty(), is(true));
        assertThat(shop.getStorage().isEmpty(), is(true));
    }

    @Test
    public void whenFoodRedistributeFromWarehouseToShopWithNoDiscount() {
        createDate.add(Calendar.MONTH, -1);
        expireDate.add(Calendar.MONTH, 2);

        Food bread = new Food("bread", createDate, expireDate, 30);
        wareHouse.add(bread);

        assertThat(wareHouse.getStorage().isEmpty(), is(false));
        assertThat(shop.getStorage().isEmpty(), is(true));
        assertThat(trash.getStorage().isEmpty(), is(true));

        test.redistribute();

        String result = shop.getStorage().get(0).getName();
        String expected = "bread";

        assertThat(result, is(expected));
        assertThat(wareHouse.getStorage().isEmpty(), is(true));
        assertThat(trash.getStorage().isEmpty(), is(true));

        double resultDiscount = shop.getStorage().get(0).getDiscount();
        double expectedDiscount = 0;

        assertThat(resultDiscount, is(expectedDiscount));
    }

    @Test
    public void whenFoodRedistributeFromWarehouseToShopWithDiscount() {
        createDate.add(Calendar.MONTH, -6);
        expireDate.add(Calendar.MONTH, 1);

        Food bread = new Food("bread", createDate, expireDate, 30);
        wareHouse.add(bread);

        assertThat(wareHouse.getStorage().isEmpty(), is(false));
        assertThat(shop.getStorage().isEmpty(), is(true));
        assertThat(trash.getStorage().isEmpty(), is(true));

        test.redistribute();

        String result = shop.getStorage().get(0).getName();
        String expected = "bread";

        assertThat(result, is(expected));
        assertThat(wareHouse.getStorage().isEmpty(), is(true));
        assertThat(trash.getStorage().isEmpty(), is(true));

        double resultDiscount = shop.getStorage().get(0).getDiscount();
        double expectedDiscount = 10.0;

        assertThat(resultDiscount, is(expectedDiscount));
    }

    @Test
    public void whenFoodRedistributeFromShopToTrash() {
        createDate.add(Calendar.MONTH, -2);
        expireDate.add(Calendar.MONTH, -1);

        Food bread = new Food("bread", createDate, expireDate, 30);
        shop.add(bread);

        assertThat(shop.getStorage().isEmpty(), is(false));
        assertThat(wareHouse.getStorage().isEmpty(), is(true));
        assertThat(trash.getStorage().isEmpty(), is(true));

        test.redistribute();

        String result = trash.getStorage().get(0).getName();
        String expected = "bread";

        assertThat(result, is(expected));
        assertThat(wareHouse.getStorage().isEmpty(), is(true));
        assertThat(shop.getStorage().isEmpty(), is(true));
    }

    @Test
    public void whenFoodDistributeToWarehouse() {
        createDate.add(Calendar.MONTH, -1);
        expireDate.add(Calendar.MONTH, 5);
        Food bread = new Food("bread", createDate, expireDate, 30);

        assertThat(shop.getStorage().isEmpty(), is(true));
        assertThat(wareHouse.getStorage().isEmpty(), is(true));
        assertThat(trash.getStorage().isEmpty(), is(true));

        test.distribute(bread);

        String result = wareHouse.getStorage().get(0).getName();
        String expected = "bread";

        assertThat(result, is(expected));
        assertThat(shop.getStorage().isEmpty(), is(true));
        assertThat(trash.getStorage().isEmpty(), is(true));
    }

    @Test
    public void whenFoodDistributeToShopWithNoDiscount() {
        createDate.add(Calendar.MONTH, -1);
        expireDate.add(Calendar.MONTH, 2);
        Food bread = new Food("bread", createDate, expireDate, 30);

        assertThat(shop.getStorage().isEmpty(), is(true));
        assertThat(wareHouse.getStorage().isEmpty(), is(true));
        assertThat(trash.getStorage().isEmpty(), is(true));

        test.distribute(bread);

        String result = shop.getStorage().get(0).getName();
        String expected = "bread";

        assertThat(result, is(expected));
        assertThat(wareHouse.getStorage().isEmpty(), is(true));
        assertThat(trash.getStorage().isEmpty(), is(true));

        double resultDiscount = shop.getStorage().get(0).getDiscount();
        double expectedDiscount = 0.0;

        assertThat(resultDiscount, is(expectedDiscount));
    }

    @Test
    public void whenFoodDistributeToShopWithDiscount() {
        createDate.add(Calendar.MONTH, -6);
        expireDate.add(Calendar.MONTH, 1);
        Food bread = new Food("bread", createDate, expireDate, 30);

        assertThat(shop.getStorage().isEmpty(), is(true));
        assertThat(wareHouse.getStorage().isEmpty(), is(true));
        assertThat(trash.getStorage().isEmpty(), is(true));

        test.distribute(bread);

        String result = shop.getStorage().get(0).getName();
        String expected = "bread";

        assertThat(result, is(expected));
        assertThat(wareHouse.getStorage().isEmpty(), is(true));
        assertThat(trash.getStorage().isEmpty(), is(true));

        double resultDiscount = shop.getStorage().get(0).getDiscount();
        double expectedDiscount = 10.0;

        assertThat(resultDiscount, is(expectedDiscount));
    }

    @Test
    public void whenFoodDistributeToTrash() {
        createDate.add(Calendar.MONTH, -6);
        expireDate.add(Calendar.MONTH, -1);
        Food bread = new Food("bread", createDate, expireDate, 30);

        assertThat(shop.getStorage().isEmpty(), is(true));
        assertThat(wareHouse.getStorage().isEmpty(), is(true));
        assertThat(trash.getStorage().isEmpty(), is(true));

        test.distribute(bread);

        String result = trash.getStorage().get(0).getName();
        String expected = "bread";

        assertThat(result, is(expected));
        assertThat(wareHouse.getStorage().isEmpty(), is(true));
        assertThat(shop.getStorage().isEmpty(), is(true));
    }

    @Test
    public void whenFoodsDistributeToAllStorages() {
        Calendar crDateBread = GregorianCalendar.getInstance();
        Calendar exDateBread = GregorianCalendar.getInstance();
        crDateBread.add(Calendar.MONTH, -1);
        exDateBread.add(Calendar.MONTH, 5);
        Food bread = new Food("bread", crDateBread, exDateBread, 30);

        Calendar crDateButter = GregorianCalendar.getInstance();
        Calendar exDateButter = GregorianCalendar.getInstance();
        crDateButter.add(Calendar.MONTH, -1);
        exDateButter.add(Calendar.MONTH, 2);
        Food butter = new Food("butter", crDateButter, exDateButter, 120);

        Calendar crDateMilk = GregorianCalendar.getInstance();
        Calendar exDateMilk = GregorianCalendar.getInstance();
        crDateMilk.add(Calendar.MONTH, -6);
        exDateMilk.add(Calendar.MONTH, 1);
        Food milk = new Food("milk", crDateMilk, exDateMilk, 90);

        Calendar crDateTea = GregorianCalendar.getInstance();
        Calendar exDateTea = GregorianCalendar.getInstance();
        crDateTea.add(Calendar.MONTH, -6);
        exDateTea.add(Calendar.MONTH, -1);
        Food tea = new Food("tea", crDateTea, exDateTea, 250);

        List<Food> foods = List.of(bread, butter, milk, tea);

        test.distribute(foods);

        assertThat(wareHouse.getStorage().size(), is(1));
        assertThat(wareHouse.getStorage().get(0).getName(), is("bread"));

        assertThat(shop.getStorage().size(), is(2));
        assertThat(shop.getStorage().get(0).getName(), is("butter"));
        assertThat(shop.getStorage().get(0).getDiscount(), is(0.0));
        assertThat(shop.getStorage().get(1).getName(), is("milk"));
        assertThat(shop.getStorage().get(1).getDiscount(), is(10.0));

        assertThat(trash.getStorage().size(), is(1));
        assertThat(trash.getStorage().get(0).getName(), is("tea"));
    }

}