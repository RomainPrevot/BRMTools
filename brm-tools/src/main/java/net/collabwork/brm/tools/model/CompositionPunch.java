package net.collabwork.brm.tools.model;

public class CompositionPunch {

    private int quantity;
    private Punch punch;

    public CompositionPunch(Punch punch) {
        this(1, punch);
    }

    public CompositionPunch(int quantity, Punch punch) {
        super();
        this.quantity = quantity;
        this.punch = punch;
    }

    public Punch getPunch() {
        return punch;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addPunch(Punch punch2) {
        quantity++;
    }
    
    
}
