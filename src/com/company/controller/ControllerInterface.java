package com.company.controller;

/**
 * The general interface for all controllers
 */
public interface ControllerInterface {

    /**
     * Checks the internet connection and returns if it is connected or not
     * TODO: Should I move this to MainController?
     * @return If there is a connection to the internet
     */
    boolean checkConnection();

}
