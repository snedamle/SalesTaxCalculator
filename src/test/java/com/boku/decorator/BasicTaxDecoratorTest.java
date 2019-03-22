package com.boku.decorator;

import com.boku.model.BasicItem;
import com.boku.model.Item;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BasicTaxDecoratorTest {

    @Test
    public void getRate() {
        Item item = new BasicItem("test", BigDecimal.valueOf(100));
        BasicTaxDecorator basicTaxDecorator = new BasicTaxDecorator(item);
        Assert.assertEquals(BigDecimal.valueOf(0.1), basicTaxDecorator.getRate());
    }
}