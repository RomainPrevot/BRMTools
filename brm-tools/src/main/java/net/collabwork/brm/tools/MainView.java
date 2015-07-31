package net.collabwork.brm.tools;

import net.collabwork.brm.tools.model.Solution;

public interface MainView {

	void setPresenter(MainPresenter presenter);

	void setVisible(boolean visible);

	void setTitle(String title);

	void setSolution(Solution solution);

}
