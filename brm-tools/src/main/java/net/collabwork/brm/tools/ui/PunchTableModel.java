package net.collabwork.brm.tools.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import net.collabwork.brm.tools.core.model.Punch;

public class PunchTableModel extends AbstractTableModel {
	private final static String[] COLUMNS = { "id", "nom", "taille", "qte" };
	private List<Punch> punches;

	public PunchTableModel() {
		punches = new ArrayList<>();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		Class<?> type;

		switch (columnIndex) {
		case 0:
			type = Integer.class;
			break;
		case 1:
			type = String.class;
			break;
		case 2:
			type = Long.class;
			break;
		case 3:
			type = Long.class;
			break;
		default:
			type = String.class;
			break;
		}

		return type;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return !(column == 0 || column == 4);
	}

	@Override
	public int getRowCount() {
		return punches.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public String getColumnName(int column) {
		return COLUMNS[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value = null;
		Punch p = getData(rowIndex);
		switch (columnIndex) {
		case 0:
			value = p.getId();
			break;
		case 1:
			value = p.getName();
			break;
		case 2:
			value = Long.valueOf(p.getSize());
			break;
		case 3:
			value = p.getQuantity();
			break;
		default:
			throw new IndexOutOfBoundsException("Column index out of bounds: " + columnIndex);
		}
		return value;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Punch p = getData(rowIndex);
		String strVal = String.valueOf(aValue);
		switch (columnIndex) {
		case 1:
			p.setName(strVal);
			break;
		case 2:
			p.setSize(Long.parseLong(strVal));
			break;
		case 3:
			p.getPunchQuantity().setQuantity(Integer.parseInt(strVal));
			break;
		default:
			throw new IndexOutOfBoundsException("Column index out of bounds: " + columnIndex);
		}

		fireTableRowsUpdated(rowIndex, rowIndex);
	}

	public Punch getData(int rowIndex) {
		return punches.get(rowIndex);
	}

	public void clear() {
		int originalSize = punches.size();
		punches.clear();
		if (originalSize > 0) {
			fireTableRowsDeleted(0, originalSize - 1);
		}
	}

	public void addRow(Punch p) {
		int start = punches.size();
		punches.add(p);
		int end = punches.size();
		fireTableRowsInserted(start, end);
	}

}
