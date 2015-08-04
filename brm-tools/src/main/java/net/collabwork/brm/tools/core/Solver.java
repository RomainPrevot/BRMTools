package net.collabwork.brm.tools.core;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import net.collabwork.brm.tools.core.model.Punch;
import net.collabwork.brm.tools.core.model.Solution;

public class Solver {

	private Map<Punch, Integer> punches;

	public Solver() {
		this.punches = new HashMap<Punch, Integer>();
	}

	public void add(Punch punch, Integer quantity) {
		if(!punches.containsKey(punch)) {
			punches.put(punch, 0);
		}
		Integer oldQty = punches.get(punch);
		punches.put(punch, oldQty + quantity);
	}

	public synchronized Solution solveFor(Long i) {

		Comparator<Punch> comparator = new Comparator<Punch>() {
			@Override
			public int compare(Punch o1, Punch o2) {
				return Long.compare(o1.getSize(), o2.getSize());
			}
		};
		SortedSet<Punch> set = new TreeSet<Punch>(comparator);
		set.addAll(punches.keySet());

		SolverStack lastStack = null;
		for (Punch p : set) {
			SolverStack s = new SolverStack(p, punches.get(p));
			
			s.setNext(lastStack);
			lastStack = s;
		}

		Solution solution = new Solution();
		solution.setObjective(i);
		lastStack.solveFor(i, solution);

		return solution;
	}

}
