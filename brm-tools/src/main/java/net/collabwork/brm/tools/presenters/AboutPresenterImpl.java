package net.collabwork.brm.tools.presenters;

import net.collabwork.brm.tools.views.AboutView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AboutPresenterImpl implements AboutPresenter {

	@Autowired
	private AboutView view;

	@Override
	public void display(boolean b) {
		view.display(b);
	}

}
