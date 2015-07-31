package net.collabwork.brm.tools;

import java.beans.PropertyChangeListener;

public interface MainModel {

	void setTitle(String title);

	String getTitle();

	void addPropertyChangeListener(PropertyChangeListener l);

	void removePropertyChangeListener(PropertyChangeListener l);

}
