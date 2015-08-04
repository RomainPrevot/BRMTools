package net.collabwork.brm.tools.models;

import java.beans.PropertyChangeListener;
import java.util.List;

import net.collabwork.brm.tools.core.model.Punch;

public interface PunchManagementModel {

	void addPropertyChangeListener(PropertyChangeListener l);

	void removePropertyChangeListener(PropertyChangeListener l);

	List<Punch> getPunches();

	void createNewPunch();

	void updatePunch(Punch punch);

	void deletePunch(Punch punch);
}
