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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import net.miginfocom.swing.MigLayout;

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

        historyListModel = new DefaultListModel<>();
        getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getContentPane().add(contentPane);

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
        contentPane.setLayout(new MigLayout("", "[350px][350px]", "[][]"));
        computePanel.add(btnOk, BorderLayout.EAST);
        contentPane.add(computePanel, "cell 0 0,grow");

        solutionPanel = new SolutionPanel();
        solutionPanel.setPreferredSize(new Dimension(350, 600));
        contentPane.add(solutionPanel, "cell 1 0 1 2,grow");
        JList<Solution> history = new JList<Solution>(historyListModel);
        contentPane.add(history, "cell 0 1,growy");
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
