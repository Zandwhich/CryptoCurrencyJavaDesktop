package com.company;

import com.oracle.javafx.jmx.json.JSONReader;

import java.io.InputStream;
import java.net.URL;

/**
 * TODO: Fill this out
 */
public abstract class AbstractJSONCaller extends AbstractAPICaller {

    /****************
     *    Fields    *
     ****************/


    /****************
     * Constructors *
     ****************/

    /**
     * A constructor for the Abstract JSON caller
     * @param currency
     */
    public AbstractJSONCaller(String currency, String name) {
        super(currency, name);
    }//end AbstractJSONCaller()

    /****************
     *   Methods    *
     ****************/

    /**
     * TODO: Fill this out
     * @return
     */
    @Override
    protected double getNewPrice() {
        // TODO: Figure out how to do the JSON (JSON.SIMPLE or whatever it's called)
        try {
            /*InputStream is = url.openStream();
            JSONReader reader = ;
            JSON*/

        }
        catch (Exception e) {
            // If we can't get the new price, return the price that we have
            // TODO: Do something in the future to notify there was a failure in getting the updated price
            return this.getPrice();
        }
        // TODO: Change the return statement once everything is figured out
        return 0;
    }//end getNewPrice()
}//end AbstractJSONCaller
