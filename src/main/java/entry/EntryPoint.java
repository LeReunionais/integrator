package entry;

import interfaces.Requester;

/**
 * Created on 4/25/16.
 */
public class EntryPoint {
    public static void main(String[] args) {
        Requester requester = new Requester();
        requester.run();
    }
}
