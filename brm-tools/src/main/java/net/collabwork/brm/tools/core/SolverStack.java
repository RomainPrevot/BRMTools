package net.collabwork.brm.tools.core;

import net.collabwork.brm.tools.core.model.Punch;
import net.collabwork.brm.tools.core.model.Solution;

public class SolverStack {
	private Punch punch;
	private Integer quantity;
	private SolverStack next;

	/**
	 * @param punch
	 * @param quantity
	 */
	public SolverStack(Punch punch, Integer quantity) {
		super();
		this.punch = punch;
		this.quantity = quantity;
	}

	public Punch getPunch() {
		return punch;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setNext(SolverStack next) {
		this.next = next;
	}

	public SolverStack getNext() {
		return next;
	}

	public void solveFor(Long total, Solution solution) {
		while (total >= punch.getSize() && quantity > 0) {
			System.out.println("removing " + punch.getSize() + " from total of " + total);
			total -= punch.getSize();
			solution.add(punch);
			quantity--;
		}
		if (next != null) {
			next.solveFor(total, solution);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SolverStack [punch=");
		builder.append(punch.getName());
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", next=");
		builder.append(next);
		builder.append("]");
		return builder.toString();
	}

}
