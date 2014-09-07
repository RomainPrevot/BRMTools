package net.collabwork.brm.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.collabwork.brm.tools.dao.PunchDAO;
import net.collabwork.brm.tools.model.Punch;
import net.collabwork.brm.tools.model.Solution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
public class PunchService {

    @Autowired
    private PunchDAO punchDao;

    public List<Punch> compute(Long size) {
        List<Punch> solution = new ArrayList<Punch>();
        List<Punch> punches = getAll();

        long total = size;

        Punch p = getClosestPunchTo(total, punches);
        while (p != null && total > 0) {
            solution.add(p);
            total -= p.getSize();
            p = getClosestPunchTo(total, punches);
        }

        return solution;
    }

    public List<Punch> getAll() {
        List<Punch> punches = Lists.newArrayList(punchDao.findAll());
        Collections.sort(punches);
        return punches;
    }

    public Punch getClosestPunchTo(long size, List<Punch> punches) {
        Punch closest = null;
        for (Punch p : punches) {
            // punch close
            if (p.getSize() <= size) {
                // check with the old one
                if (closest != null) {
                    if (closest.compareTo(p) <= 0) {
                        closest = p;
                    }
                } else {
                    closest = p;
                }
            }
        }
        return closest;
    }

}
