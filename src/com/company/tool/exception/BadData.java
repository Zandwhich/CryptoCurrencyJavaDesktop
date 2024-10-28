package com.company.tool.exception;

import com.company.api_call.AbstractAPICaller;

public final class BadData extends Exception {

    /* ************ *
     *    Fields    *
     * ************ */

    /**
     * The API that was called that received the bad data
     */
    private final AbstractAPICaller caller;


    /* ************ *
     * Constructors *
     * ************ */

    /**
     * The constructor for the BadData exception
     * @param parentException The underlying exception that was thrown
     * @param caller The API caller in which the exception was thrown
     */
    public BadData(final Exception parentException, final AbstractAPICaller caller) {
        super(parentException.getMessage(), parentException.getCause());
        this.caller = caller;
    }


    /* ************ *
     *   Methods    *
     * ************ */

    /**
     * Returns the API caller that threw the exception
     * @return The API caller that threw the exception
     */
    public AbstractAPICaller getCaller() {
        return this.caller;
    }
}
