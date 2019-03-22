package com.boku.decorator;

import com.boku.model.Item;

import java.math.BigDecimal;

/**
 * Import duty tax of 5% will be added to the item.
 */
public class ImportDutyTaxDecorator extends TaxDecorator {
    private final BigDecimal rate = BigDecimal.valueOf(0.05);

    private Item itemToDecorate;


    public ImportDutyTaxDecorator(Item item) {
        super(item);
        this.itemToDecorate = item;
    }

    @Override
    BigDecimal getRate() {
        return this.rate;
    }

    @Override
    public String getName() {
        return this.itemToDecorate.getName();
    }

    @Override
    public BigDecimal getBasicPrice() {
        return this.itemToDecorate.getBasicPrice();
    }

    @Override
    public boolean isImported() {
        return this.itemToDecorate.isImported();
    }

    @Override
    public boolean isExempted() {
        return this.itemToDecorate.isExempted();
    }
}
