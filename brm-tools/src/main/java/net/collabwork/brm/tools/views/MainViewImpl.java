package net.collabwork.brm.tools.views;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.collabwork.brm.tools.core.model.Solution;
import net.collabwork.brm.tools.presenters.MainPresenter;
import net.collabwork.brm.tools.ui.SolutionPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainViewImpl extends JFrame implements MainView {

	@Autowired
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
		JButton button = new JButton("coucou");
		JPanel toolbar = new JPanel();
		toolbar.setLayout(new BorderLayout());
		toolbar.add(textField, BorderLayout.CENTER);
		toolbar.add(button, BorderLayout.EAST);
		contentPane.add(toolbar, BorderLayout.SOUTH);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				presenter.showPunchManagementWindow();
			}
		});

		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				presenter.setTitle(textField.getText());
			}
		});
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
