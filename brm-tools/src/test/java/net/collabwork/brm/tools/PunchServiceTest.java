package net.collabwork.brm.tools;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;
import net.collabwork.brm.tools.config.AppConfig;
import net.collabwork.brm.tools.dao.PunchDAO;
import net.collabwork.brm.tools.model.Punch;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class PunchServiceTest {

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
        Assert.assertEquals(5, solution.get(0).getSize());
        Assert.assertEquals(1, solution.get(1).getSize());

    }
}
