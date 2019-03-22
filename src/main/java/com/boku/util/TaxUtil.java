package com.boku.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

/**
 * Utility class having static helper methods for rounding up to nearest 0.05.
 */
public class TaxUtil {
    private static Set<String> exemptItems = null;

    static {
        exemptItems = new HashSet();
        exemptItems.add("book");
        exemptItems.add("box of imported chocolates");
        exemptItems.add("imported box of chocolates");
        exemptItems.add("box of chocolates");
        exemptItems.add("chocolate");
        exemptItems.add("chocolate bar");
        exemptItems.add("pills");
        exemptItems.add("packet of headache pills");
    }

    private TaxUtil() {}

    public static boolean isExempted(String itemName) {
        return exemptItems.contains(itemName);
    }

    public static BigDecimal roundUpAmount(BigDecimal totalAmount) {
        return totalAmount.setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal roundToNearestFivePercent(BigDecimal amount) {
        BigDecimal twenty = new BigDecimal(20);

        // To round to the nearest .05, multiply by 20, round to the nearest integer, then divide by 20
        // 𝑓′=1/20int[20𝑓+0.5]

        return new BigDecimal(amount.multiply(twenty)
                .add(new BigDecimal("0.5"))
                .toBigInteger()).divide(twenty).setScale(2);
    }
}