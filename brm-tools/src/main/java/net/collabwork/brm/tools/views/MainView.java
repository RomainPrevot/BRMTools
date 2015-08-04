package net.collabwork.brm.tools.views;

import net.collabwork.brm.tools.core.model.Solution;

public interface MainView {

	void setVisible(boolean visible);

	void setTitle(String title);

	void setSolution(Solution solution);

}
