package net.collabwork.brm.tools.models;

import java.beans.PropertyChangeListener;
import java.util.List;

import net.collabwork.brm.tools.core.model.Solution;

public interface MainModel {

	void addPropertyChangeListener(PropertyChangeListener l);

	void removePropertyChangeListener(PropertyChangeListener l);

	Solution getSolution();

	List<Solution> getSolutionHistory();

	void solveFor(Long value);

}
