package com.boku.parser;

import com.boku.model.BasicItem;
import com.boku.model.Item;
import com.boku.util.TaxUtil;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regular expression for input
 * \d - for any digit 0-9
 * \s - for whitespace character
 * \w - word character a-zA-Z_0-9
 *
 * Parser parses the input and gives us the item objects in the input.
 */
public class FileParser {

    private static final String INPUT_REGEX = "(\\d+)\\s((\\w+\\s)+)at\\s(\\d+.\\d+)";

    private FileParser() {}

    public static Item getItemFromInput(String input) {
        Matcher matcher = parse(input);
        String name = matcher.group(2).trim();
        BigDecimal basicPrice = new BigDecimal(matcher.group(4));
        BasicItem item = new BasicItem(name, basicPrice);
        if (name.contains("imported"))
            item.setImported(true);
        if (TaxUtil.isExempted(name))
            item.setExempted(true);
        return item;
    }

    public static boolean matches(String description) {
        return Pattern.matches(INPUT_REGEX, description);
    }

    public static Matcher parse(String description) {
        Pattern pattern = Pattern.compile(INPUT_REGEX);
        Matcher matcher = pattern.matcher(description);
        matcher.find();
        return matcher;
    }

    public static int getQuantity (String input) {
        return Integer.valueOf(parse(input).group(1));
    }

}
