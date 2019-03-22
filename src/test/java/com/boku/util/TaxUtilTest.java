package com.boku.util;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class TaxUtilTest {

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

    @Test
    public void isExempted() {
        for(String s : exemptItems) {
            Assert.assertTrue(TaxUtil.isExempted(s));
        }
        Assert.assertFalse(TaxUtil.isExempted("motor"));

    }

    @Test
    public void roundUpAmount() {
        Assert.assertEquals(BigDecimal.valueOf(12.68), TaxUtil.roundUpAmount(BigDecimal.valueOf(12.675)));
        Assert.assertEquals(BigDecimal.valueOf(12.68), TaxUtil.roundUpAmount(BigDecimal.valueOf(12.676)));
        Assert.assertEquals(BigDecimal.valueOf(12.67), TaxUtil.roundUpAmount(BigDecimal.valueOf(12.674)));
        Assert.assertEquals(BigDecimal.valueOf(12.67), TaxUtil.roundUpAmount(BigDecimal.valueOf(12.67)));
    }

    @Test
    public void roundToNearestFivePercent() {
        Assert.assertEquals(BigDecimal.valueOf(990.50).setScale(2), TaxUtil.roundToNearestFivePercent(BigDecimal.valueOf(990.49)));
        Assert.assertEquals(BigDecimal.valueOf(990.40).setScale(2), TaxUtil.roundToNearestFivePercent(BigDecimal.valueOf(990.40)));
        Assert.assertEquals(BigDecimal.valueOf(990.45), TaxUtil.roundToNearestFivePercent(BigDecimal.valueOf(990.44)));
        Assert.assertEquals(BigDecimal.valueOf(990.40).setScale(2), TaxUtil.roundToNearestFivePercent(BigDecimal.valueOf(990.42)));
    }
}