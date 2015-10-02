package net.collabwork.brm.tools.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.collabwork.brm.tools.core.model.Solution;
import net.collabwork.brm.tools.presenters.MainPresenter;
import net.collabwork.brm.tools.ui.SolutionPanel;
import net.collabwork.brm.tools.views.actions.NewComputeAction;
import net.collabwork.brm.tools.views.actions.ShowAboutWindowAction;
import net.collabwork.brm.tools.views.actions.ShowPunchManagementWindowAction;
import net.miginfocom.swing.MigLayout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
// @DependsOn("showPunchManagementWindowAction")
public class MainViewImpl extends JFrame implements MainView {

	private static final Font BOLD = new Font("Tahoma", Font.BOLD, 25);

	private MainPresenter presenter;

	private ShowPunchManagementWindowAction showPunchManagementWindowAction;

	private SolutionPanel solutionPanel;

	private DefaultListModel<Solution> historyListModel;

	private ShowAboutWindowAction showAboutWindowsAction;

	private JTextField computeField;

	private NewComputeAction newComputeAction;

	public MainViewImpl() {
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		setTitle("BRM-Tools - Calcul poinçonnage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(800, 600));
		center();

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu computeMenu = new JMenu("Calculs");
		menuBar.add(computeMenu);

		newComputeAction = new NewComputeAction();
		JMenuItem newComputation = new JMenuItem(newComputeAction);
		computeMenu.add(newComputation);
		computeMenu.addSeparator();
		JMenuItem clearHistory = new JMenuItem("Vider historique");
		computeMenu.add(clearHistory);

		JMenu punchMgmtMenu = new JMenu("Poinçons");
		menuBar.add(punchMgmtMenu);

		showPunchManagementWindowAction = new ShowPunchManagementWindowAction();
		JMenuItem showPunchMgmt = new JMenuItem(showPunchManagementWindowAction);
		punchMgmtMenu.add(showPunchMgmt);

		JMenu aboutMenu = new JMenu("?");
		menuBar.add(aboutMenu);

		showAboutWindowsAction = new ShowAboutWindowAction();
		JMenuItem showAboutWindows = new JMenuItem(showAboutWindowsAction);
		aboutMenu.add(showAboutWindows);

		historyListModel = new DefaultListModel<>();
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		getContentPane().add(contentPane);

		JPanel computePanel = new JPanel();
		computePanel.setLayout(new BorderLayout());
		computeField = new JTextField();
		computeField.setFont(BOLD);
		computeField.setPreferredSize(new Dimension(150, 20));
		JLabel lblCompute = new JLabel("Calculer:");
		lblCompute.setFont(BOLD);
		computePanel.add(lblCompute, BorderLayout.WEST);
		computePanel.add(computeField, BorderLayout.CENTER);
		JButton btnOk = new JButton("ok");
		btnOk.setFont(BOLD);
		btnOk.addActionListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Long value = Long.valueOf(computeField.getText());
				presenter.solveFor(value);
			}
		});
		contentPane.setLayout(new MigLayout("", "[350px][350px]", "[][]"));
		computePanel.add(btnOk, BorderLayout.EAST);
		contentPane.add(computePanel, "cell 0 0,grow");

		solutionPanel = new SolutionPanel();
		solutionPanel.setPreferredSize(new Dimension(350, 600));
		contentPane.add(solutionPanel, "cell 1 0 1 2,grow");
		JList<Solution> history = new JList<Solution>(historyListModel);
		history.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contentPane.add(history, "cell 0 1,growy");
		history.setPreferredSize(new Dimension(350, 200));
		history.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				System.out.println(e);
				if (!e.getValueIsAdjusting()) {
					Solution solution = null;
					System.out.println(e.getFirstIndex());
					if (historyListModel.size() > e.getLastIndex()) {
						solution = historyListModel.get(e.getLastIndex());
					}
					setSolution(solution);
				}
			}
		});
	}

	@Autowired
	public void setPresenter(MainPresenter presenter) {
		this.presenter = presenter;
		showPunchManagementWindowAction.setPresenter(presenter);
		showAboutWindowsAction.setPresenter(presenter);
		newComputeAction.setPresenter(presenter);
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

	@Override
	public void clearComputeField() {
		computeField.setText("");
	}

	@Override
	public void focusComputeField() {
		computeField.requestFocus();
	}

}
