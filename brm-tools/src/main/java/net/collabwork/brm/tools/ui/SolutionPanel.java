package net.collabwork.brm.tools.ui;

import java.awt.BorderLayout;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.collabwork.brm.tools.model.Punch;
import net.collabwork.brm.tools.model.Solution;

public class SolutionPanel extends JPanel {

	private static class DisabledSelectionModel extends DefaultListSelectionModel {
		@Override
		public void setSelectionInterval(int index0, int index1) {
			super.setSelectionInterval(-1, -1);
		}
	}

	private Solution solution;
	private DefaultListModel<String> listModel;
	private JLabel titleLabel;

	public SolutionPanel() {
		setLayout(new BorderLayout());
		listModel = new DefaultListModel<>();
		JList<String> list = new JList<>(listModel);
		list.setSelectionModel(new DisabledSelectionModel());
		JScrollPane scrollPane = new JScrollPane(list);
		add(scrollPane, BorderLayout.CENTER);

		titleLabel = new JLabel();
		add(titleLabel, BorderLayout.NORTH);
	}

	public void setSolution(Solution solution) {
		listModel.clear();
		titleLabel.setText("");

		this.solution = solution;
		if (solution != null) {
			String title = "Composition pour " + solution.getObjective();
			if (solution.isPartial()) {
				title += " Partiel";
			}
			titleLabel.setText(title);
			Map<Punch, Integer> punches = solution.getPunches();
			for (Punch p : punches.keySet()) {
				listModel.addElement(String.format("%s x %s", punches.get(p), p.getName()));
			}
		}
	}
}
