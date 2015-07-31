package net.collabwork.brm.tools;

import java.util.ArrayList;
import java.util.List;

import net.collabwork.brm.tools.dao.PunchDAO;
import net.collabwork.brm.tools.model.Punch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PunchService {

    @Autowired
    private PunchDAO punchDao;

    public List<Punch> compute(Long size) {
        List<Punch> solution = new ArrayList<Punch>();

        return solution;
    }

}
