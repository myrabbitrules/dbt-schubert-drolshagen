package de.schubert.drolshagen.bloodline;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Generates the PersonTree
 * 
 * @author Rene Drolshagen and Richard Schubert
 */
public class PersonTree {
	
	public class PersonPos {
		/**
		 * The Name of the Person
		 */
		private String name;
		
		/**
		 * The Position where the Person will stand
		 */
		private Dimension position;
		
		/**
		 * The father of the Person
		 */
		private PersonPos father;
		
		/**
		 * The mother of the Person
		 */
		private PersonPos mother;
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public Dimension getPosition() {
			return position;
		}
		
		public void setPosition(Dimension position) {
			this.position = position;
		}

		public PersonPos getFather() {
			return father;
		}

		public void setFather(PersonPos father) {
			this.father = father;
		}
		
		public PersonPos getMother() {
			return mother;
		}
		
		public void setMother(PersonPos mother) {
			this.mother = mother;
		}
		
		@Override
		public String toString() {
			return "(" + name + ", " + position + ")";
		}
	}
	
	/**
	 * The Root Person Position
	 */
	private PersonPos rootPersonPos; // tree
	private int currBottomPos = 0; 
	private Dimension elementDim;
	private Dimension gapDim;
	/**
	 * This List includes all Persons which will be drawn
	 */
	private List<PersonPos> personList;
	/**
	 * This list includes the List of Lines which will be shown
	 */
	private List<Line> linePoints;
	
	/**
	 * Generates a new PersonTree
	 * @param rootPerson The Person where the Drawing will start
	 * @param elementDim The Dimension of the Elements
	 * @param gapDim The Dimension of the Gap between the Elements
	 */
	public PersonTree(Person rootPerson, Dimension elementDim, Dimension gapDim) {		
		this.elementDim = elementDim;
		this.gapDim = gapDim;
		rootPersonPos = buildPersonPos(rootPerson, 0);
		buildPersonList(rootPersonPos);
		buildLinePoints(personList);
	}
	
	/**
	 * Calculates the Persons Position in the Area
	 * @param person The Person where the Position belongs to
	 * @param height The actual height of the Tree
	 * @return The calculated Person Position
	 */
	private PersonPos buildPersonPos(Person person, int height) {		
		PersonPos res = new PersonPos();
		res.position = new Dimension();		
		res.setName(person.getForename() + " " + person.getSirname());		
		res.position.height = height;
		if (person.hasFather() && person.hasMother()) {
			int parentHeight = height + elementDim.height + gapDim.height;
			res.setFather(buildPersonPos(person.getFather(), parentHeight));
			res.setMother(buildPersonPos(person.getMother(), parentHeight));			
			res.position.width = (res.father.position.width + res.mother.position.width) / 2;			
		}
		else {			
			res.position.width = currBottomPos * (elementDim.width + gapDim.width);;
			currBottomPos++;
		}		
		return res;
	}
	
	/**
	 * Builds the Person List which includes the Persons which will be drawn
	 * @param rootPersonPos The Person where the Building will start
	 */
	private void buildPersonList(PersonPos rootPersonPos) {
		personList = new LinkedList<PersonPos>();
		personList.add(rootPersonPos);
		int currPos = 0;
		int lastNotNullPos = currPos;
		
		while (currPos <= lastNotNullPos) {
			PersonPos currPerson = personList.get(currPos);
			if (currPerson != null) {
				personList.add(currPerson.getFather());
				personList.add(currPerson.getMother());
				if (currPerson.getMother() != null) {
					lastNotNullPos = personList.size() - 1;
				}
				else if (currPerson.getFather() != null) {
					lastNotNullPos = personList.size() - 2;
				}
			}
			else {
				personList.add(null);
				personList.add(null);
			}
			
			currPos++;
		}			
			
		boolean onlyNullElements = true;
		/**
		 * This function deletes the unnessacary null Elements in the List
		 */
		
		for (int i = personList.size() - 1; i >= personList.size() - getBottomElemCount(); i--) {
			if (personList.get(i) != null) {
				onlyNullElements = false;
			}
		}
		if (onlyNullElements) {
			int startBottomElemCount = getBottomElemCount();
			for (int i = 0; i < startBottomElemCount; i++) {
				personList.remove(personList.size() - 1);
			}
		}			
	}
	
	/**
	 * This function calculates the LinePoints which will be needed to draw them
	 * @param personList The List of Persons which will be lined together
	 */
	public void buildLinePoints(List<PersonPos> personList) {
		linePoints = new ArrayList<Line>();
		int j = 1;
		for (int i = 0; j < personList.size(); i++) {
			Dimension from = personList.get(i).position;
			
			if (personList.get(j) != null) {
				Dimension toFirst = personList.get(j).position;
				linePoints.add(new Line(from, toFirst));
			}				
			j++;
			if (personList.get(j) != null) {
				Dimension toSecond = personList.get(j).position;
				linePoints.add(new Line(from, toSecond));
			}
			j++;			
		}		
	}
		
	public List<Line> getLinePoints() {
		return linePoints;
	}


	public void setLinePoints(List<Line> linePoints) {
		this.linePoints = linePoints;
	}

	/**
	 * Calculates the needed Levels for the Tree 
	 * @return
	 */
	public int getLevelCount() {
		return (int) (Math.log(personList.size() + 1) / Math.log(2));
	}
	
	/**
	 * Calculates the Count of Bottom Elements
	 * @return
	 */
	public int getBottomElemCount() {
		return (int) (Math.pow(2, getLevelCount() - 1));
	}
	
	/**
	 * Calculates the needed Height for the Tree
	 * @return
	 */
	public int getHeight() {
		return (getLevelCount() - 1) * (elementDim.height + gapDim.height);
	}
	
	/** 
	 * Calculates the needed Width for the tree
	 * @return
	 */
	public int getWidth() {
		return (getBottomElemCount() - 1) * (elementDim.width + gapDim.width);
	}
	
	public List<PersonPos> getPersonList() {
		return personList;
	}

	public void setPersonList(List<PersonPos> personList) {
		this.personList = personList;
	}

	/**
	 * Prints the Tree
	 * @param personPos The Position of the Person where the printing will start
	 */
	public void printTree(PersonPos personPos) {
		System.out.println(personPos);
		if (personPos.father != null) {
			printTree(personPos.father);
		}
		if (personPos.mother != null) {
			printTree(personPos.mother);
		}
	}
}
