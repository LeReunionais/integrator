package service;

import interfaces.Requester;

/**
 * Created on 4/25/16.
 */
public class Service {
    public static void main(String[] args) {
        Requester requester = new Requester();
        requester.run();
    }
}
