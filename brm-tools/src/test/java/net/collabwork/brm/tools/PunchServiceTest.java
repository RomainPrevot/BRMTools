package net.collabwork.brm.tools;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;
import net.collabwork.brm.tools.config.AppConfig;
import net.collabwork.brm.tools.dao.PunchDAO;
import net.collabwork.brm.tools.model.Punch;
import net.collabwork.brm.tools.model.Solution;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class PunchServiceTest {

    @Test
    public void should_find_the_closest_punch() {
        List<Punch> punches = Arrays.asList(new Punch(1L, "p1", 1), new Punch(2L, "p2", 2), new Punch(3L, "p5", 5),
                new Punch(4L, "p10", 10));
        PunchService punchService = new PunchService();

        Assert.assertEquals(10, punchService.getClosestPunchTo(12, punches).getSize());
        Assert.assertEquals(1, punchService.getClosestPunchTo(1, punches).getSize());
        Assert.assertEquals(5, punchService.getClosestPunchTo(6, punches).getSize());
        Assert.assertEquals(2, punchService.getClosestPunchTo(4, punches).getSize());
    }

    @Test
    public void test() {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) ctx.getBeanFactory();

        List<Punch> punches = Arrays.asList(new Punch(1L, "p1", 1), new Punch(2L, "p2", 2), new Punch(3L, "p5", 5),
                new Punch(4L, "p10", 10));

        PunchDAO mockPunchDao = Mockito.mock(PunchDAO.class);
        Mockito.when(mockPunchDao.findAll()).thenReturn(punches);

        beanFactory.removeBeanDefinition("punchDAO");
        beanFactory.registerSingleton("punchDAO", mockPunchDao);

        PunchService punchService = ctx.getBean(PunchService.class);

        List<Punch> solution = punchService.compute(6L);

        Assert.assertNotNull(solution);
        System.out.println(solution);
        Assert.assertEquals(5, solution.get(0).getSize());
        Assert.assertEquals(1, solution.get(1).getSize());

//        solution = punchService.compute(28L);
//
//        Assert.assertNotNull(solution);
//        Assert.assertEquals(10, solution.get(0).getSize());
//        Assert.assertEquals(10, solution.get(1).getSize());
//        Assert.assertEquals(5, solution.get(2).getSize());
//        Assert.assertEquals(2, solution.get(3).getSize());
//        Assert.assertEquals(1, solution.get(4).getSize());

    }
}
