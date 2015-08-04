package net.collabwork.brm.tools.views;

import java.util.List;

import net.collabwork.brm.tools.core.model.Punch;

public interface PunchManagementView {

	void setVisible(boolean visible);

	void setPunches(List<Punch> set);

	void setErrorMessage(String errorMessage);

}
