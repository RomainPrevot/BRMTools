package net.collabwork.brm.tools.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import net.collabwork.brm.tools.PunchService;
import net.collabwork.brm.tools.core.model.Punch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PunchManagementModelImpl implements PunchManagementModel {

	@Autowired
	private PunchService punchService;

	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

	@Override
	public void addPropertyChangeListener(PropertyChangeListener l) {
		changeSupport.addPropertyChangeListener(l);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener l) {
		changeSupport.removePropertyChangeListener(l);
	}

	@Override
	public List<Punch> getPunches() {
		return punchService.findAll();
	}

	@Override
	public void createNewPunch() {
		Punch punch = punchService.createNewPunch();
		changeSupport.firePropertyChange("punches", -1, punch);
	}

	@Override
	public void updatePunch(Punch punch) {
		Punch original = punchService.findOne(punch.getId());
		Punch updated = punchService.updatePunch(punch);
		changeSupport.firePropertyChange("punches", original, updated);

	}

	@Override
	public void deletePunch(Punch punch) {
		Punch original = punchService.findOne(punch.getId());
		punchService.deletePunch(punch);
		changeSupport.firePropertyChange("punches", original, null);
	}

}
