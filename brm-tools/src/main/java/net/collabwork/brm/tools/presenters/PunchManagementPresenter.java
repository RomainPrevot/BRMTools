package net.collabwork.brm.tools.presenters;

import java.beans.PropertyChangeListener;

import net.collabwork.brm.tools.core.model.Punch;

public interface PunchManagementPresenter extends PropertyChangeListener {

	void display(boolean visible);

	void createNewPunch();

	void updatePunch(Punch punch);

	void deletePunch(Punch selected);

}
