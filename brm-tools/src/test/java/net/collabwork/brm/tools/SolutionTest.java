package net.collabwork.brm.tools;

import static junit.framework.Assert.assertEquals;

import java.util.List;

import net.collabwork.brm.tools.model.CompositionPunch;
import net.collabwork.brm.tools.model.Punch;
import net.collabwork.brm.tools.model.Solution;

import org.junit.Test;

public class SolutionTest {

    @Test
    public void should_construct_correct_solution_with_one_punch() {
        Solution solution1 = new Solution();
        Solution solution5 = new Solution();

        solution1.add(new Punch(1L, "p1", 1));
        solution5.add(new Punch(3L, "p5", 5));

        assertEquals(1, solution1.size());
        assertEquals(1, solution5.size());

        List<CompositionPunch> compPunches1 = solution1.getCompositionPunches();
        List<CompositionPunch> compPunches5 = solution5.getCompositionPunches();

        assertEquals(1, compPunches1.size());
        assertEquals(1, compPunches5.size());

        assertEquals(1, compPunches1.get(0).getPunch().getSize());
        assertEquals(5, compPunches5.get(0).getPunch().getSize());
    }

    @Test
    public void should_concat_composition_punches() {
        Solution solution1 = new Solution();

        solution1.add(new Punch(1L, "p1", 1));
        solution1.add(new Punch(1L, "p1", 1));

        assertEquals(2, solution1.size());
        assertEquals(2, solution1.getCompositionPunches().get(0).getQuantity());

        assertEquals(1, solution1.getPunches().size());
    }
}
