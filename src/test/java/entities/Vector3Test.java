package entities;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created on 4/24/16.
 */
public class Vector3Test {
    class CaseAdd {
        private final Vector3 operand_1;
        private final Vector3 operand_2;
        private final Vector3 expected;

        public CaseAdd(Vector3 operand_1, Vector3 operand_2, Vector3 expected) {
            this.operand_1 = operand_1;
            this.operand_2 = operand_2;
            this.expected = expected;
        }
    }

    class CaseMultiply {
        private final Vector3 operand_1;
        private final double operand_2;
        private final Vector3 expected;

        public CaseMultiply(Vector3 operand_1, double operand_2, Vector3 expected) {
            this.operand_1 = operand_1;
            this.operand_2 = operand_2;
            this.expected = expected;
        }
    }

    @Test
    public void add() throws Exception {
        Collection<CaseAdd> cases = Arrays.asList(
                new CaseAdd(Vector3.ZERO, Vector3.ZERO, Vector3.ZERO),
                new CaseAdd(Vector3.ZERO, Vector3.ONE, Vector3.ONE),
                new CaseAdd(Vector3.ONE, Vector3.ONE, new Vector3(2.0, 0.0, 0.0)),
                new CaseAdd(Vector3.ONE, Vector3.ZERO, Vector3.ONE)
        );

        for (CaseAdd c : cases) {
            assertEquals(c.operand_1.add(c.operand_2), c.expected);
        }
    }

    @Test
    public void multiply() {
        Collection<CaseMultiply> cases = Arrays.asList(
                new CaseMultiply(Vector3.ZERO, 0.0, Vector3.ZERO),
                new CaseMultiply(Vector3.ZERO, 1.0, Vector3.ZERO),
                new CaseMultiply(Vector3.ONE, 1.0, new Vector3(1.0, 0.0, 0.0)),
                new CaseMultiply(Vector3.ONE, 2.0, new Vector3(2.0, 0.0, 0.0)),
                new CaseMultiply(Vector3.ONE, 0.0, Vector3.ZERO)
        );

        for (CaseMultiply c : cases) {
            assertEquals(c.operand_1.multiply(c.operand_2), c.expected);
        }
    }

}