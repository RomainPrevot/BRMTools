package net.collabwork.brm.tools.presenters;

import java.beans.PropertyChangeListener;

public interface MainPresenter extends PropertyChangeListener {

	void display(boolean visible);

	void refreshView();
	
	void showPunchManagementWindow();

	void solveFor(Long value);

}
