package net.collabwork.brm.tools.presenters;

import java.beans.PropertyChangeEvent;

import net.collabwork.brm.tools.models.MainModel;
import net.collabwork.brm.tools.views.MainView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainPresenterImpl implements MainPresenter {

	@Autowired
	private MainView view;

	@Autowired
	private PunchManagementPresenter punchPresenter;

	@Autowired
	private AboutPresenter aboutPresenter;

	// Autowired see #setModel
	private MainModel model;

	@Autowired
	public void setModel(MainModel model) {
		this.model = model;
		model.addPropertyChangeListener(this);
	}

	@Override
	public void display(boolean visible) {
		refreshView();
		view.setVisible(visible);
	}

	@Override
	public void refreshView() {
		view.setSolution(model.getSolution());
		view.setSolutionHistory(model.getSolutionHistory());
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		refreshView();
	}

	@Override
	public void showPunchManagementWindow() {
		punchPresenter.display(true);
	}

	@Override
	public void solveFor(Long value) {
		model.solveFor(value);
	}

	@Override
	public void showAboutWindow() {
		aboutPresenter.display(true);
	}

	@Override
	public void clearAndFocusComputeField() {
		view.clearComputeField();
		view.focusComputeField();
	}

}
