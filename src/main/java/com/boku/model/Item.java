package com.boku.model;

import java.math.BigDecimal;

/**
 *  Basic Interface for Item, which a BasicItem implements, which is also implemented by other decorators which wish to
 *  decorate the BasicItem
 */
public interface Item {

    String getName();

    BigDecimal getPrice();

    BigDecimal getBasicPrice();

    boolean isImported();

    boolean isExempted();
}
