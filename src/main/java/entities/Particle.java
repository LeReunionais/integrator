package entities;

/**
 * Created on 4/23/16.
 */
public class Particle {
    private Vector3 position;
    private Vector3 velocity;
    private Vector3 force;
    private double inverseMass;

    public Particle(Vector3 position, Vector3 velocity, Vector3 force, double inverseMass) {
        this.position = position;
        this.velocity = velocity;
        this.force = force;
        this.inverseMass = inverseMass;
    }

    public static Particle ZERO = new Particle(Vector3.ZERO, Vector3.ZERO, Vector3.ZERO,1.0);
    public static Particle ONE = new Particle(Vector3.ONE, Vector3.ZERO, Vector3.ZERO, 1.0);

    public Vector3 getVelocity() {
        return velocity;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 acceleration() {
        return force.multiply(inverseMass);
    }

    public double getInverseMass() {
        return inverseMass;
    }
}
