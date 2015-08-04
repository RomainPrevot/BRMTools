package net.collabwork.brm.tools.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.springframework.stereotype.Component;

@Component
public class MainModelImpl implements MainModel {

	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

	private String title = "Default title";

	@Override
	public void setTitle(String title) {
		String oldVal = this.title;
		String newVal = title;

		this.title = title;
		changeSupport.firePropertyChange("title", oldVal, newVal);
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener changeListener) {
		changeSupport.addPropertyChangeListener(changeListener);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener l) {
		changeSupport.removePropertyChangeListener(l);
	}

}
