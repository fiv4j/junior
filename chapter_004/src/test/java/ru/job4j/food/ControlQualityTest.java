package ru.job4j.food;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ControlQualityTest {

    Storage wareHouse, shop, trash;
    Calendar createDate, expireDate;

    @Before
    public void before() {
        wareHouse = new WareHouse();
        shop = new Shop();
        trash = new Trash();
        createDate = GregorianCalendar.getInstance();
        expireDate = GregorianCalendar.getInstance();
    }

    @Test
    public void whenFoodDistributeFromWarehouseToTrash() {
        ControlQuality test = new ControlQuality(wareHouse, shop, trash);

        createDate.add(Calendar.MONTH, -2);
        expireDate.add(Calendar.MONTH, -1);

        Food bread = new Food("bread", createDate, expireDate, 30);
        wareHouse.add(bread);

        assertThat(wareHouse.getStorage().isEmpty(), is(false));
        assertThat(shop.getStorage().isEmpty(), is(true));
        assertThat(trash.getStorage().isEmpty(), is(true));

        test.distribute();

        String result = trash.getStorage().get(0).getName();
        String expected = "bread";

        assertThat(result, is(expected));
        assertThat(wareHouse.getStorage().isEmpty(), is(true));
        assertThat(shop.getStorage().isEmpty(), is(true));
    }

    @Test
    public void whenFoodDistributeFromWarehouseToShopWithNoDiscount() {
        ControlQuality test = new ControlQuality(wareHouse, shop, trash);

        createDate.add(Calendar.MONTH, -1);
        expireDate.add(Calendar.MONTH, 2);

        Food bread = new Food("bread", createDate, expireDate, 30);
        wareHouse.add(bread);

        assertThat(wareHouse.getStorage().isEmpty(), is(false));
        assertThat(shop.getStorage().isEmpty(), is(true));
        assertThat(trash.getStorage().isEmpty(), is(true));

        test.distribute();

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
    public void whenFoodDistributeFromWarehouseToShopWithDiscount() {
        ControlQuality test = new ControlQuality(wareHouse, shop, trash);

        createDate.add(Calendar.MONTH, -6);
        expireDate.add(Calendar.MONTH, 1);

        Food bread = new Food("bread", createDate, expireDate, 30);
        wareHouse.add(bread);

        assertThat(wareHouse.getStorage().isEmpty(), is(false));
        assertThat(shop.getStorage().isEmpty(), is(true));
        assertThat(trash.getStorage().isEmpty(), is(true));

        test.distribute();

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
    public void whenFoodDistributeFromShopToTrash() {
        ControlQuality test = new ControlQuality(wareHouse, shop, trash);

        createDate.add(Calendar.MONTH, -2);
        expireDate.add(Calendar.MONTH, -1);

        Food bread = new Food("bread", createDate, expireDate, 30);
        shop.add(bread);

        assertThat(shop.getStorage().isEmpty(), is(false));
        assertThat(wareHouse.getStorage().isEmpty(), is(true));
        assertThat(trash.getStorage().isEmpty(), is(true));

        test.distribute();

        String result = trash.getStorage().get(0).getName();
        String expected = "bread";

        assertThat(result, is(expected));
        assertThat(wareHouse.getStorage().isEmpty(), is(true));
        assertThat(shop.getStorage().isEmpty(), is(true));
    }

}