package entities;

import java.util.UUID;

/**
 * Created on 4/24/16.
 */
public class Work {
    private Particle particle;
    private double delta;
    private UUID workId;

    public Particle getParticle() {
        return particle;
    }

    public double getDelta() {
        return delta;
    }

    public UUID getWorkId() {
        return workId;
    }
}
