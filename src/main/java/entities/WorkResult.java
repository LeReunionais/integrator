package entities;

import java.util.UUID;

/**
 * Created on 5/11/16.
 */
public class WorkResult {
    private Particle particle;
    private UUID workId;

    public WorkResult(Particle particle_updated, UUID workId) {
        this.particle = particle_updated;
        this.workId = workId;
    }
}
