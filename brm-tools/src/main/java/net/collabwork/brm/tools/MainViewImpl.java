package net.collabwork.brm.tools;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.collabwork.brm.tools.model.Solution;
import net.collabwork.brm.tools.ui.SolutionPanel;

public class MainViewImpl extends JFrame implements MainView {

	private MainPresenter presenter;
	private SolutionPanel solutionPanel;

	public MainViewImpl() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(800, 600));
		center();
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		solutionPanel = new SolutionPanel();
		contentPane.add(solutionPanel, BorderLayout.CENTER);

		JLabel label = new JLabel("Hello world");
		contentPane.add(label, BorderLayout.NORTH);
		final JTextField textField = new JTextField();
		contentPane.add(textField, BorderLayout.SOUTH);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				presenter.setTitle(textField.getText());
			}
		});
	}

	@Override
	public void setPresenter(MainPresenter presenter) {
		this.presenter = presenter;
	}

	private void center() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
	}

	@Override
	public void setSolution(Solution solution) {
		System.out.println("setting solution");
		solutionPanel.setSolution(solution);
	}

}
