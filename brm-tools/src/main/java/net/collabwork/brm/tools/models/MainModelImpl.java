package net.collabwork.brm.tools.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import net.collabwork.brm.tools.SolverService;
import net.collabwork.brm.tools.core.model.Solution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainModelImpl implements MainModel {

	@Autowired
	private SolverService solverService;

	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

	private Solution currentSolution;
	private Stack<Solution> solutions = new Stack<>();

	@Override
	public void addPropertyChangeListener(PropertyChangeListener changeListener) {
		changeSupport.addPropertyChangeListener(changeListener);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener l) {
		changeSupport.removePropertyChangeListener(l);
	}

	@Override
	public Solution getSolution() {
		return currentSolution;
	}

	@Override
	public List<Solution> getSolutionHistory() {
		System.out.println("history: " + solutions);
		return new ArrayList<Solution>(solutions);
	}

	@Override
	public void solveFor(Long value) {
		Solution solution = solverService.solveFor(value);

		solutions.push(solution);

		currentSolution = solution;
		changeSupport.firePropertyChange("solution", null, currentSolution);
	}

}
