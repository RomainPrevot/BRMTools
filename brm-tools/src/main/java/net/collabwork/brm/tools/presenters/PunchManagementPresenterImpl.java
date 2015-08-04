package net.collabwork.brm.tools.presenters;

import java.beans.PropertyChangeEvent;

import net.collabwork.brm.tools.core.model.Punch;
import net.collabwork.brm.tools.models.PunchManagementModel;
import net.collabwork.brm.tools.views.PunchManagementView;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PunchManagementPresenterImpl implements PunchManagementPresenter {

	@Autowired
	private PunchManagementView view;

	private PunchManagementModel model;
	
	@Autowired
	public void setPunchManagementModel(PunchManagementModel model) {
		this.model = model;
		this.model.addPropertyChangeListener(this);
	}

	@Override
	public void display(boolean visible) {
		refreshPunches();
		view.setVisible(visible);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		refreshPunches();
	}

	private void refreshPunches() {
		view.setErrorMessage(null);
		view.setPunches(model.getPunches());
	}

	@Override
	public void createNewPunch() {
		model.createNewPunch();
	}

	@Override
	public void updatePunch(Punch punch) {
		try {
			model.updatePunch(punch);
		} catch (Exception e) {
			if (e.getCause() != null && e.getCause().getCause() != null
					&& e.getCause().getCause() instanceof ConstraintViolationException) {
				view.setErrorMessage(String.format("Le nom '%s' est déjà utilisé", punch.getName()));
			} else {
				throw e;
			}
		}
	}

	@Override
	public void deletePunch(Punch punch) {
		model.deletePunch(punch);
	}
}
