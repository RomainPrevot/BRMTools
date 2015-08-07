package net.collabwork.brm.tools.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.collabwork.brm.tools.core.model.Solution;
import net.collabwork.brm.tools.presenters.MainPresenter;
import net.collabwork.brm.tools.ui.SolutionPanel;
import net.collabwork.brm.tools.views.actions.ShowPunchManagementWindowAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
// @DependsOn("showPunchManagementWindowAction")
public class MainViewImpl extends JFrame implements MainView {

	private MainPresenter presenter;

	private ShowPunchManagementWindowAction showPunchManagementWindowAction;

	private SolutionPanel solutionPanel;

	private DefaultListModel<Solution> historyListModel;

	public MainViewImpl() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(800, 600));
		center();

		JPanel contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		getContentPane().add(contentPane);
		contentPane.setLayout(new BorderLayout(5, 5));

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu computeMenu = new JMenu("Calculs");
		menuBar.add(computeMenu);

		JMenuItem newComputation = new JMenuItem("Nouveau");
		computeMenu.add(newComputation);
		computeMenu.addSeparator();
		JMenuItem clearHistory = new JMenuItem("Vider historique");
		computeMenu.add(clearHistory);

		JMenu punchMgmtMenu = new JMenu("Poinçons");
		menuBar.add(punchMgmtMenu);

		showPunchManagementWindowAction = new ShowPunchManagementWindowAction();
		JMenuItem showPunchMgmt = new JMenuItem(showPunchManagementWindowAction);
		punchMgmtMenu.add(showPunchMgmt);

		JPanel computePanel = new JPanel();
		computePanel.setLayout(new BorderLayout());
		final JTextField txtValue = new JTextField();
		txtValue.setFont(txtValue.getFont().deriveFont(35));
		txtValue.setPreferredSize(new Dimension(150, 20));
		computePanel.add(new JLabel("Calculer:"), BorderLayout.WEST);
		computePanel.add(txtValue, BorderLayout.CENTER);
		JButton btnOk = new JButton("ok");
		btnOk.addActionListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Long value = Long.valueOf(txtValue.getText());
				presenter.solveFor(value);
			}
		});
		computePanel.add(btnOk, BorderLayout.EAST);
		contentPane.add(computePanel, BorderLayout.WEST);

		solutionPanel = new SolutionPanel();
		solutionPanel.setPreferredSize(new Dimension(350, 600));
		contentPane.add(solutionPanel, BorderLayout.EAST);

		historyListModel = new DefaultListModel<>();
		JList<Solution> history = new JList<Solution>(historyListModel);
		history.setPreferredSize(new Dimension(350, 200));
		history.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				Solution solution = null;

				if (historyListModel.size() > e.getFirstIndex()) {
					solution = historyListModel.get(e.getFirstIndex());
				}
				setSolution(solution);
			}
		});
		computePanel.add(history, BorderLayout.SOUTH);
	}

	@Autowired
	public void setPresenter(MainPresenter presenter) {
		this.presenter = presenter;
		showPunchManagementWindowAction.setPresenter(presenter);
	}

	private void center() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
	}

	@Override
	public void setSolution(Solution solution) {
		solutionPanel.setSolution(solution);
	}

	@Override
	public void setSolutionHistory(List<Solution> solutions) {
		historyListModel.clear();
		for (Solution s : solutions) {
			historyListModel.addElement(s);
		}
	}

}
