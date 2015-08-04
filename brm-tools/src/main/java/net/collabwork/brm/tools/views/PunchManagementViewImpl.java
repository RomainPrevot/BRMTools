package net.collabwork.brm.tools.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;

import net.collabwork.brm.tools.core.model.Punch;
import net.collabwork.brm.tools.presenters.PunchManagementPresenter;
import net.collabwork.brm.tools.ui.PunchTableModel;
import net.collabwork.brm.tools.ui.resources.ImageBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PunchManagementViewImpl extends JDialog implements PunchManagementView, ListSelectionListener {
	@Autowired
	private PunchManagementPresenter presenter;

	private PunchTableModel tableModel;
	private JButton addBtn;
	private JButton removeBtn;
	private JTable table;

	private Punch selected;

	private JLabel errorLabel;

	public PunchManagementViewImpl() {
		setTitle("Gestion des poinçons");
		setPreferredSize(new Dimension(400, 300));
		setModal(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		errorLabel = new JLabel();
		errorLabel.setForeground(Color.RED);
		add(errorLabel, BorderLayout.NORTH);

		tableModel = new PunchTableModel();
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		table.getSelectionModel().addListSelectionListener(this);

		TableColumn column = table.getColumn("id");
		column.setPreferredWidth(20);
		column.setMaxWidth(70);
		column.setMinWidth(20);

		add(scrollPane, BorderLayout.CENTER);

		Action createAction = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				presenter.createNewPunch();
			}
		};

		Action deleteAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String value = selected.getName() == null ? "#" + selected.getId() : selected.getName();
				int response = JOptionPane.showConfirmDialog(null, String.format("Supprimer %s ?", value),
						"Suppression", JOptionPane.YES_NO_OPTION);

				if (response == JOptionPane.YES_OPTION) {
					presenter.deletePunch(selected);
					setSelectedPunch(null);
				}
			}
		};

		JPanel actionPanel = new JPanel();
		actionPanel.setLayout(new FlowLayout());

		addBtn = new JButton("Ajouter", ImageBundle.add());
		actionPanel.add(addBtn);
		removeBtn = new JButton("Supprimer", ImageBundle.remove());
		actionPanel.add(removeBtn);
		removeBtn.setEnabled(false);

		add(actionPanel, BorderLayout.SOUTH);

		addBtn.addActionListener(createAction);
		removeBtn.addActionListener(deleteAction);

		tableModel.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					Punch updatedPunch = tableModel.getData(e.getFirstRow());
					presenter.updatePunch(updatedPunch);
				}
			}
		});

		pack();
	}

	@Override
	public void setVisible(final boolean visible) {
		super.setVisible(visible);
	}

	@Override
	public void setPunches(List<Punch> punches) {
		tableModel.clear();
		for (Punch p : punches) {
			tableModel.addRow(p);
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		Punch sPunch = null;
		if (!lsm.isSelectionEmpty()) {
			int selectedRow = lsm.getMinSelectionIndex();
			sPunch = tableModel.getData(selectedRow);
		}

		setSelectedPunch(sPunch);
	}

	private void setSelectedPunch(Punch selected) {
		this.selected = selected;

		removeBtn.setEnabled(selected != null);
	}

	@Override
	public void setErrorMessage(String errorMessage) {
		errorLabel.setText(errorMessage);
	}

}
