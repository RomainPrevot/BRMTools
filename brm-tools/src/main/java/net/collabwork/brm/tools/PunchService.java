package net.collabwork.brm.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.collabwork.brm.tools.core.dao.PunchDAO;
import net.collabwork.brm.tools.core.model.Punch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PunchService {

	@Autowired
	private PunchDAO punchDao;

	public List<Punch> compute(Long size) {
		List<Punch> solution = new ArrayList<Punch>();

		return solution;
	}

	public List<Punch> findAll() {
		Iterable<Punch> entities = punchDao.findAll();
		List<Punch> punches = new ArrayList<>();
		for (Punch p : entities) {
			punches.add(p);
		}
		Collections.sort(punches);
		return punches;
	}

	public Punch findOne(Long id) {
		return punchDao.findOne(id);
	}

	@Transactional
	public Punch createNewPunch() {
		return punchDao.save(new Punch());
	}

	@Transactional
	public Punch updatePunch(Punch punch) {
		return punchDao.save(punch);
	}

	@Transactional
	public void deletePunch(Punch punch) {
		punchDao.delete(punch);
	}

}
