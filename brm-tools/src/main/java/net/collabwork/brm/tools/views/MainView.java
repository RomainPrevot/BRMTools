package net.collabwork.brm.tools.views;

import java.util.List;

import net.collabwork.brm.tools.core.model.Solution;

public interface MainView {

	void setVisible(boolean visible);

	void setTitle(String title);

	void setSolution(Solution solution);

	void setSolutionHistory(List<Solution> solutions);

}
