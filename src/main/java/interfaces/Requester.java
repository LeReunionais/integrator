package interfaces;

import com.google.gson.Gson;
import entities.Particle;
import entities.Work;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import registry.Registry;
import registry.RegistryJson;
import registry.Service;
import updator.SimpleUpdator;

import java.util.UUID;

/**
 * Created on 4/23/16.
 */
public class Requester {
    private final Gson gson = new Gson();
    private final Registry registry = new RegistryJson("tcp://192.168.0.103:3002");

    private class ReadyRequest {
        private final String jsonrpc = "2.0";
        private final String method = "ready";
        private final String params;
        private final UUID id;

        ReadyRequest(String name) {
            this.params = name;
            this.id = UUID.randomUUID();
        }
    }

    private class ResultRequest {
        private final String jsonrpc = "2.0";
        private final String method = "result";
        private Particle params;
        private UUID id;

        public ResultRequest(Particle params, UUID id) {
            this.params = params;
            this.id = id;
        }
    }

    private class ReplyMessage {
        private final String jsonrpc = "2.0";
        private Work result;
        private UUID id;

        public Work getWork() {
            return result;
        }
    }

    public void run() {
        ZContext context = new ZContext();

        String ready_json = gson.toJson(new ReadyRequest("George"));
        SimpleUpdator simpleUpdator = new SimpleUpdator();

        ZMQ.Socket socket = context.createSocket(ZMQ.REQ);
        Service integrator = registry.whereis("integrator");
        socket.connect(integrator.getEndpoint());

        while (true) {
            System.out.println("Sending message");
            socket.send(ready_json, 0);
            System.out.println("Waiting for reply");
            String message = socket.recvStr(0);
            System.out.println(message);
            ReplyMessage reply_message = gson.fromJson(message, ReplyMessage.class);
            System.out.println(reply_message);

            Particle particle_updated = simpleUpdator.update(reply_message.getWork());
            ResultRequest result_request = new ResultRequest(particle_updated, reply_message.id);
            String result_request_json = gson.toJson(result_request);
            System.out.println("Sending result");
            socket.send(result_request_json, 0);
            System.out.println("Waiting result");
            String result_reply = socket.recvStr(0);
            System.out.println(result_reply);
        }
    }
}