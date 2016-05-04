package entry;

import interfaces.Requester;
import registry.Registry;
import registry.RegistryJson;

/**
 * Created on 4/25/16.
 */
public class EntryPoint {

    public static final String REGISTRY_HOST = "REGISTRY_HOST";

    public static void main(String[] args) throws Exception {
        if (System.getenv(REGISTRY_HOST) == null) throw new Exception(REGISTRY_HOST + " environment variable is mandatory");
        String endpoint = "tcp://" + System.getenv(REGISTRY_HOST) + ":3002";
        Registry registry = new RegistryJson(endpoint);
        Requester requester = new Requester(registry);
        requester.run();
    }
}
