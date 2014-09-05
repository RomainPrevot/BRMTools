package net.collabwork.brm.tools;

import net.collabwork.brm.tools.dao.PunchDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppService {

	@Autowired
	private PunchDAO punchDao;

	public String sayHello() {
		return "hello world " + punchDao.count();
	}
}
