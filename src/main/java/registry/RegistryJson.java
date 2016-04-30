package registry;

import com.google.gson.Gson;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.util.UUID;

/**
 * Created on 4/30/16.
 */
public class RegistryJson implements Registry {
    private final String endpoint;
    private final Gson gson = new Gson();
    public RegistryJson(String registry_endpoint) {
        endpoint = registry_endpoint;
    }

    static class FindRequest {
        private final String jsonrpc = "2.0";
        private final String method = "find";
        private final Wrapper params;
        private final UUID id;

        class Wrapper {
            public final Service service;

            public Wrapper(Service service) {
                this.service = service;
            }
        }

        class Service {
            public final String name;

            Service(String name) {
                this.name = name;
            }

        }

        FindRequest(String service_to_find) {
            this.params = new Wrapper(new Service(service_to_find));
            this.id = UUID.randomUUID();
        }

        public String getName() {
            return this.params.service.name;
        }
        public UUID getId() {
            return id;
        }
    }
    static class FindResponse {
        private final String jsonrpc = "2.0";
        private final Service params;
        private final UUID id;

        public Service getParams() {
            return params;
        }

        FindResponse(Service service, UUID id) {
            this.params = service;
            this.id = id;
        }
    }

    @Override
    public Service whereis(String service_name) {
        ZContext context = new ZContext();
        ZMQ.Socket socket = context.createSocket(ZMQ.REQ);
        socket.connect(endpoint);

        String request_json = gson.toJson(new FindRequest(service_name));
        socket.send(request_json, 0);
        String replyStr = socket.recvStr(0);
        System.out.println(replyStr);

        Service response  = gson.fromJson(replyStr, Service.class);

        socket.close();
        return response;
    }
}
