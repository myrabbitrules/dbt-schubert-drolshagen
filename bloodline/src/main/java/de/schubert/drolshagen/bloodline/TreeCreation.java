package de.schubert.drolshagen.bloodline;

import java.awt.Dimension;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
@RequestScoped
@Named
/**
 * @author Rene Drolshagen and Richard Schubert
 *
 * This class is made to save or show the tree
 */

public class TreeCreation {
	
	@Inject
	private DataManager dataManager;
	
	/**
	 * The rootPerson which is used to draw the tree
	 */
	private Person rootPerson = null;

	/***
	 * The personID which is needed to draw
	 */
	private int personId;
	
	/**
	 * The PersonTree which represents the tree
	 */
	private PersonTree personTree;
	
	private static final Dimension elementDim = new Dimension(50, 25); // in px
	private static final Dimension gapDim = new Dimension(50, 80); // in px
	private static final Dimension panelBorder = new Dimension(50, 50);
	
	/**
	 * Gets the perfect PanelDimension for our Tree
	 * @return Perfect PanelDimension
	 */
	public Dimension getPanelDim() {
		Dimension res = new Dimension();
		res.width = personTree.getWidth() + panelBorder.width;
		res.height = personTree.getHeight() + panelBorder.height;
		return res;
	}
	
	/**
	 * Gets the Element Dimension
	 * @return The Element Dimension
	 */
	public Dimension getElementdim() {
		return elementDim;
	}

	/**
	 * Gets the Gap Dimension
	 * @return The Gap Dimension
	 */
	public Dimension getGapdim() {
		return gapDim;
	}

	/**
	 * Gets the Next Person for Drawing
	 * @return The Next Person
	 */
	public void getNextPerson() {
	}

	/**
	 * Returns the saved PersonID
	 * @return The PersonID which is stored in the class
	 */
	public int getPersonId() {
		return personId;
	}

	/**
	 * Sets a new PersonID
	 * @param personId The personID which will be saved
	 */
	public void setPersonId(int personId) {
		this.personId = personId;
	}	
	
	/**
	 * Returns the saved PersonTree
	 * @return The PersonTree which is saved in this class
	 */
	public PersonTree getPersonTree() {
		return personTree;
	}

	/**
	 * Sets a new personTree instead of the old one
	 * @param personTree The personTree which will be set
	 */
	public void setPersonTree(PersonTree personTree) {
		this.personTree = personTree;
	}

	/**
	 * Returns the saved PanelBorder Dimension
	 * @return The saved PanelBorder
	 */
	public Dimension getPanelborder() {
		return panelBorder;
	}

	/**
	 * Creates the Family Tree
	 * @return The Page where the tree will be shown
	 */
	public String createTree() {
		setRoot(dataManager.getPerson(personId));
		personTree = new PersonTree(rootPerson, elementDim, gapDim);
		
		return "treeDisplay.jsf";
	}	
	
	/**
	 * The Person which is saved as root
	 * @return The saved Person
	 */
	public Person getRoot() {
		return rootPerson;
	}

	/**
	 * Overrides the old person with the given one
	 * @param root The new person
	 */
	public void setRoot(Person root) {
		this.rootPerson = root;
	}
	
}
