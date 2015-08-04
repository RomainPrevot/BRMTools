package net.collabwork.brm.tools.presenters;

import java.beans.PropertyChangeEvent;
import java.util.Random;

import net.collabwork.brm.tools.core.model.Punch;
import net.collabwork.brm.tools.core.model.Solution;
import net.collabwork.brm.tools.models.MainModel;
import net.collabwork.brm.tools.views.MainView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainPresenterImpl implements MainPresenter {

	@Autowired
	private MainView view;

	private MainModel model;

	@Autowired
	private PunchManagementPresenter punchPresenter;

	@Autowired
	public void setModel(MainModel model) {
		this.model = model;
		model.addPropertyChangeListener(this);
	}

	@Override
	public void display(boolean visible) {
		view.setVisible(visible);
	}

	@Override
	public void refreshView() {
		view.setTitle(model.getTitle());
	}

	@Override
	public void setTitle(String string) {
		model.setTitle(string);
		Solution solution = new Solution();
		long total = 0L;

		for (int i = 0; i < randInt(0, 10); i++) {
			total += 1;
			solution.add(new Punch(1L, "punch 1", 1));
		}
		for (int i = 0; i < randInt(0, 10); i++) {
			total += 2;
			solution.add(new Punch(2L, "punch 2", 2));
		}
		for (int i = 0; i < randInt(0, 10); i++) {
			total += 5;
			solution.add(new Punch(5L, "punch 5", 5));
		}
		for (int i = 0; i < randInt(0, 10); i++) {
			total += 10;
			solution.add(new Punch(10L, "punch 10", 10));
		}
		solution.setObjective(total);
		
		view.setSolution(solution);
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		refreshView();
	}

	public static int randInt(int min, int max) {

		Random rand = new Random();

		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	@Override
	public void showPunchManagementWindow() {
		punchPresenter.display(true);
	}

}
