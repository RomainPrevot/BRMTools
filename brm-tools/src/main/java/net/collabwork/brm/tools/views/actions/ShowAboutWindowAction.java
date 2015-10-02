package net.collabwork.brm.tools.views.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import net.collabwork.brm.tools.presenters.MainPresenter;

import org.springframework.beans.factory.annotation.Autowired;

public class ShowAboutWindowAction extends AbstractAction {

	private MainPresenter presenter;

	public ShowAboutWindowAction() {
		super("A propos...");
	}

	@Autowired(required = false)
	public void setPresenter(MainPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		presenter.showAboutWindow();
	}

}
