package net.collabwork.brm.tools.model;

import javax.persistence.Embeddable;

@Embeddable
public class PunchQuantity {

	private int quantity;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
