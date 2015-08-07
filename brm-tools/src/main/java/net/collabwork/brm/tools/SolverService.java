package net.collabwork.brm.tools;

import net.collabwork.brm.tools.core.Solver;
import net.collabwork.brm.tools.core.dao.PunchDAO;
import net.collabwork.brm.tools.core.model.Punch;
import net.collabwork.brm.tools.core.model.Solution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SolverService {

	@Autowired
	private PunchDAO dao;

	public Solution solveFor(Long value) {
		Solver solver = new Solver();
		Iterable<Punch> punches = dao.findAll();
		for (Punch punch : punches) {
			solver.add(punch, punch.getQuantity());
		}

		return solver.solveFor(value);
	}
}
