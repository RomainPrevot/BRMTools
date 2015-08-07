package net.collabwork.brm.tools.views.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import net.collabwork.brm.tools.presenters.MainPresenter;
import net.collabwork.brm.tools.ui.resources.ImageBundle;

import org.springframework.beans.factory.annotation.Autowired;

public class ShowPunchManagementWindowAction extends AbstractAction {

	private MainPresenter presenter;

	public ShowPunchManagementWindowAction() {
		super("Gestion des poinçons", ImageBundle.punchManagementIcon());
	}

	@Autowired(required = false)
	public void setPresenter(MainPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		presenter.showPunchManagementWindow();
	}

}
