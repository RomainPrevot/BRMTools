package net.collabwork.brm.tools.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ComputationException;

/**
 * Not an entity
 * 
 * @author Romain PREVOT
 * 
 */
public class Solution {

    private List<CompositionPunch> compositionPunches = new ArrayList<CompositionPunch>();

    public Solution() {
        super();
    }

    public void add(Punch punch) {
        CompositionPunch compPunch = getCompositionPunchFor(punch);
        if (compPunch == null) {
            compPunch = new CompositionPunch(1, punch);
        } else {
            compPunch.addPunch(punch);
        }
        compositionPunches.add(compPunch);
    }

    public int size() {
        return compositionPunches.size();
    }

    public List<CompositionPunch> getCompositionPunches() {
        return compositionPunches;
    }

    public List<Punch> getPunches() {
        List<Punch> punches = new ArrayList<Punch>();
        for (CompositionPunch cp : compositionPunches) {
            Punch punch = cp.getPunch();
            if (!punches.contains(punch)) {
                punches.add(punch);
            }
        }
        return punches;
    }

    private CompositionPunch getCompositionPunchFor(Punch punch) {
        for (CompositionPunch compPunch : compositionPunches) {
            if (compPunch.getPunch().equals(punch)) {
                return compPunch;
            }
        }

        return null;
    }
}
