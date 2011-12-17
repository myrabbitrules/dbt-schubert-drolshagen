package de.schubert.drolshagen.bloodline;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PersonTree {
	
	public class PersonPos {
		private String name;
		private Dimension position;
		private PersonPos father;
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
	
	private PersonPos rootPersonPos; // tree
	private int currBottomPos = 0; 
	private Dimension elementDim;
	private Dimension gapDim;
	private List<PersonPos> personList;
	private List<Line> linePoints;
	
	public PersonTree(Person rootPerson, Dimension elementDim, Dimension gapDim) {		
		this.elementDim = elementDim;
		this.gapDim = gapDim;
		rootPersonPos = buildPersonPos(rootPerson, 0);
		buildPersonList(rootPersonPos);
		buildLinePoints(personList);
	}
	
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
				
		/* remove unnecessary null elements */		
		
		boolean onlyNullElements = true;
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

	public int getLevelCount() {
		return (int) (Math.log(personList.size() + 1) / Math.log(2));
	}
	
	public int getBottomElemCount() {
		return (int) (Math.pow(2, getLevelCount() - 1));
	}
	
	public int getHeight() {
		return (getLevelCount() - 1) * (elementDim.height + gapDim.height);
	}
	
	public int getWidth() {
		return (getBottomElemCount() - 1) * (elementDim.width + gapDim.width);
	}
	
	public List<PersonPos> getPersonList() {
		return personList;
	}

	public void setPersonList(List<PersonPos> personList) {
		this.personList = personList;
	}

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
