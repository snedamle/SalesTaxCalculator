package com.boku.calculator;

import com.boku.decorator.BasicTaxDecorator;
import com.boku.decorator.ImportDutyTaxDecorator;
import com.boku.model.Item;
import com.boku.util.TaxUtil;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * This is our cart class where items are added and ta is calculated for the same.
 * The tax calculation logic will be moved out of this class later to a calculator.
 */
public class Cart {

    private final Map<Item, Integer> itemMap = new HashMap();

    public void makeOrder(Item item, int quantity) {
        if(item.isImported())
            item = new ImportDutyTaxDecorator(item);
        if(!item.isExempted())
            item = new BasicTaxDecorator(item);
        Integer cnt = itemMap.get(item);
        if (cnt != null)
            quantity += cnt;
        itemMap.put(item, quantity);
    }

    public void clear () {
        this.itemMap.clear();
    }

    public void printInput() {
        System.out.println();
        System.out.println("INPUT:");
        for ( Item item : itemMap.keySet()) {
            System.out.println(itemMap.get(item) + " " + item.getName() + " at " + item.getBasicPrice());
        }
    }

    public void printOutput() {
        BigDecimal totalSalesTax = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;

        System.out.println("OUTPUT:");

        for (Item item : itemMap.keySet()) {
            BigDecimal perItemTaxedAmount = item.getPrice().multiply(BigDecimal.valueOf(itemMap.get(item)));
            BigDecimal perItemBasicAmount = item.getBasicPrice().multiply(BigDecimal.valueOf(itemMap.get(item)));

            totalSalesTax = totalSalesTax.add(perItemTaxedAmount.subtract(perItemBasicAmount));
            totalAmount = totalAmount.add(perItemTaxedAmount);

            System.out.println(itemMap.get(item) +" " + item.getName() +" : "+ perItemTaxedAmount);
        }

        System.out.println("Sales Tax : " + totalSalesTax);
        System.out.println("Total : " + TaxUtil.roundUpAmount(totalAmount));
    }
}
