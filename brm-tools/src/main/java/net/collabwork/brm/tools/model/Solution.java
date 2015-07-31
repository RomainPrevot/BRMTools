package net.collabwork.brm.tools.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Not an entity
 * 
 * @author Romain PREVOT
 * 
 */
public class Solution {
	private Long objective;

	private Map<Punch, Integer> punches;

	public Solution() {
		punches = new HashMap<Punch, Integer>();
	}

	public Long getObjective() {
		return objective;
	}

	public void setObjective(Long objective) {
		this.objective = objective;
	}

	public Map<Punch, Integer> getPunches() {
		return punches;
	}

	public void setPunches(Map<Punch, Integer> punches) {
		this.punches = punches;
	}

	public void add(Punch punch) {
		if (!punches.containsKey(punch)) {
			punches.put(punch, 0);
		}
		Integer oldQty = punches.get(punch);
		punches.put(punch, oldQty + 1);
	}

	public boolean isPartial() {
		if (objective == null) {
			return false;
		}
		Long total = 0L;
		for (Punch p : punches.keySet()) {
			total += p.getSize() * punches.get(p);
		}
		return objective != total;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((punches == null) ? 0 : punches.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Solution)) {
			return false;
		}
		Solution other = (Solution) obj;
		if (punches == null) {
			if (other.punches != null) {
				return false;
			}
		} else if (!punches.equals(other.punches)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Solution [");
		for (Punch p : punches.keySet()) {
			builder.append(punches.get(p)).append("x").append(p.getName()).append(", ");
		}
		builder.append("]");
		return builder.toString();
	}

}
