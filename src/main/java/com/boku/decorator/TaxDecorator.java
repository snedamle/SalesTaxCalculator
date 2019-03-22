package com.boku.decorator;

import com.boku.model.Item;
import com.boku.util.TaxUtil;

import java.math.BigDecimal;

/**
 *  This is the base decorator which has the price method which gets the base price multiplies the tax rate to it and adds it to
 *  composed decorator s price.
 */
public abstract class TaxDecorator implements Item {

    protected Item item;

    abstract BigDecimal getRate();

    public TaxDecorator(Item item) {
        this.item = item;
    }

    @Override
    public BigDecimal getPrice() {
        BigDecimal salesTax = TaxUtil.roundToNearestFivePercent(this.item.getBasicPrice().multiply(this.getRate()));
        return TaxUtil.roundUpAmount(this.item.getPrice().add(salesTax));
    }
}
