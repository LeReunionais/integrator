package updator;

import entities.Particle;
import entities.Vector3;
import entities.Work;

/**
 * Created on 4/24/16.
 */
public class SimpleUpdator implements Updator{
    @Override
    public Particle update(Work work) {
        Particle particle_to_update = work.getParticle();

        Vector3 position = particle_to_update.getPosition()
                .add(particle_to_update.getVelocity().multiply(work.getDelta()));

        Vector3 velocity = particle_to_update.getVelocity()
                .add(particle_to_update.acceleration().multiply(work.getDelta()))
                .multiply(0.999)
                ;

        return new Particle(
                position,
                velocity,
                Vector3.ZERO,
                particle_to_update.getInverseMass()
        );
    }
}
