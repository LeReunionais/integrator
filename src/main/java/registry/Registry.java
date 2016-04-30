package registry;

import entry.EntryPoint;

/**
 * Created on 4/30/16.
 */
public interface Registry {
    Service whereis (String service_name);
}
