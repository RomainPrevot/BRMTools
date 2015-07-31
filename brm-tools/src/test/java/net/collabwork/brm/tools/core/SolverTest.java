package net.collabwork.brm.tools.core;

import junit.framework.Assert;
import net.collabwork.brm.tools.core.Solver;
import net.collabwork.brm.tools.model.Punch;
import net.collabwork.brm.tools.model.Solution;

import org.junit.Test;

public class SolverTest {

	@Test
	public void should_return_correct_solution_for_3() {

		Solver solver = new Solver();

		solver.add(createPunch(1), 3);
		solver.add(createPunch(2), 0);
		solver.add(createPunch(5), 4);
		solver.add(createPunch(10), 2);

		Solution solution = solver.solveFor(3L);

		Solution expected = new Solution();
		addPunches(3, 1, expected);

		Assert.assertEquals(expected, solution);
	}

	@Test
	public void should_return_correct_solution_for_37() {

		Solver solver = new Solver();

		solver.add(createPunch(1), 30);
		solver.add(createPunch(2), 10);
		solver.add(createPunch(5), 40);
		solver.add(createPunch(10), 20);

		Solution solution = solver.solveFor(37L);

		Solution expected = new Solution();
		addPunches(3, 10, expected);
		addPunches(1, 5, expected);
		addPunches(1, 2, expected);

		Assert.assertEquals(expected, solution);
	}

	private void addPunches(int qty, int size, Solution expected) {
		Punch p = createPunch(size);
		for (int i = 0; i < qty; i++) {
			expected.add(p);
		}
	}

	private Punch createPunch(int i) {
		Punch punch = new Punch();
		punch.setId(new Long(i));
		punch.setName("punch " + i);
		punch.setSize(i);
		return punch;
	}
}
