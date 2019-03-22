package com.boku.decorator;

import com.boku.model.BasicItem;
import com.boku.model.Item;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class TaxDecoratorTest {

    @Test
    public void getImportedPrice() {
        Item item = new BasicItem("chocolate", BigDecimal.TEN);
        item = new ImportDutyTaxDecorator(item);
        Assert.assertEquals(BigDecimal.valueOf(10.50).setScale(2) , item.getPrice());
    }

    @Test
    public void getImportedAndBasicPrice() {
        Item item = new BasicItem("perfume", BigDecimal.valueOf(47.50));
        item = new ImportDutyTaxDecorator(item);
        item = new BasicTaxDecorator(item);
        Assert.assertEquals(BigDecimal.valueOf(54.65).setScale(2) , item.getPrice());
    }
}