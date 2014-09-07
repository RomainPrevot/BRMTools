package net.collabwork.brm.tools;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import junit.framework.Assert;
import net.collabwork.brm.tools.model.Punch;

import org.junit.Test;

public class PunchTest {

    @Test
    public void should_be_organize_desc() {
        List<Punch> punches = Arrays.asList(new Punch(3L, "p5", 5), new Punch(2L, "p2", 2), new Punch(1L, "p1", 1),
                new Punch(4L, "p10", 10));

        Collections.sort(punches);

        Assert.assertEquals(punches.get(3).getSize(), 10);
        Assert.assertEquals(punches.get(2).getSize(), 5);
        Assert.assertEquals(punches.get(1).getSize(), 2);
        Assert.assertEquals(punches.get(0).getSize(), 1);
    }
}
