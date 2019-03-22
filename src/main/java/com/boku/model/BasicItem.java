package com.boku.model;

import java.math.BigDecimal;

/**
 *  This is the basic concrete item class having a name and cost price.
 *  This item will be decorated by composing into other decorators as required.
 */
public class BasicItem implements Item {

    private String name;
    private boolean isImported = false;
    private boolean isExempted = false;
    private BigDecimal basicPrice;

    public BasicItem(String name, BigDecimal basicPrice) {
        this.name = name;
        this.basicPrice = basicPrice;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public BigDecimal getPrice() {
        return this.basicPrice;
    }

    @Override
    public BigDecimal getBasicPrice() {
        return this.basicPrice;
    }

    @Override
    public boolean isImported() {
        return this.isImported;
    }

    @Override
    public boolean isExempted() {
        return this.isExempted;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImported(boolean imported) {
        this.isImported = imported;
    }

    public void setExempted(boolean exempted) {
        this.isExempted = exempted;
    }

    public void setBasicPrice(BigDecimal basicPrice) {
        this.basicPrice = basicPrice;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + this.basicPrice.intValue();
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        } else if (obj instanceof Item) {
            return (obj.hashCode() == this.hashCode());
        } else
            return false;
    }
}
