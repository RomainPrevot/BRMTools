package net.collabwork.brm.tools;

import java.beans.PropertyChangeListener;

public interface MainPresenter extends PropertyChangeListener {

	void setView(MainView view);

	void setModel(MainModel model);

	void display(boolean visible);

	void refreshView();

	void setTitle(String string);

}
