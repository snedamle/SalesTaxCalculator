package com.boku;

import com.boku.calculator.Cart;
import com.boku.exception.SalesTaxException;
import com.boku.parser.FileParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This is the entry point of this app.
 * Reads the file, makes order and calls for printing the output.
 * This functionality should be broken down more - reader - will read from file, manager - to make order, printer - to print output.
 */
public class SalesTaxCalculator
{
    public static void main( String[] args) throws SalesTaxException {
        if(args.length == 0)
        {
            System.out.println("Please specify the inout file name to parse: java -jar salestaxcalculator filenames");
            System.exit(0);
        }

        Cart cart = new Cart();
        for (String fileName: args) {
            cart.clear();
            BufferedReader in = null;
            try {
                in = new BufferedReader(new FileReader(fileName));
                String str;
                while ((str = in.readLine()) != null) {
                    if (FileParser.matches(str) && !str.isEmpty())
                        cart.makeOrder(FileParser.getItemFromInput(str), FileParser.getQuantity(str));
                    else if (!str.isEmpty()){
                        System.out.println("the input does not look to be correct." + str);
                        throw new SalesTaxException("Please enter correct input");
                    }

                }
                in.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                //TODO : logger to log the exception
                throw new SalesTaxException("The specified file not found. Please enter correct filename");
            } catch (IOException e) {
                e.printStackTrace();
                //TODO : logger to log the exception
                throw new SalesTaxException("Problem while reading from file. Please contact the administrator");
            } catch (Exception e){
                throw new SalesTaxException("Something went wrong. Please contact the administrator." + e.getMessage());
            }

            cart.printInput();
            cart.printOutput();
        }

    }
}
