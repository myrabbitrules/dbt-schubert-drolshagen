package de.schubert.drolshagen.bloodline;

import java.util.LinkedList;
import java.util.List;

public class PersonTree {
	
	public PersonTree() {
		persons = new LinkedList<Person>();
	}

	private List<Person> persons;
	
	public int getLevelCount() {
		return (int) (Math.log(persons.size() + 1) / Math.log(2));
	}
	
	public int getBottomElemCount() {
		return (int) (Math.pow(2, getLevelCount() - 1));
	}
	
	public void fill(Person root) {
		persons.add(root);
		int currPos = 0;
		int lastNotNullPos = currPos;
		
		while (currPos <= lastNotNullPos) {
			Person currPerson = persons.get(currPos);
			if (currPerson != null) {
				persons.add(currPerson.getFather());
				persons.add(currPerson.getMother());
				if (currPerson.hasMother()) {
					lastNotNullPos = persons.size() - 1;
				}
				else if (currPerson.hasFather()) {
					lastNotNullPos = persons.size() - 2;
				}
			}
			else {
				persons.add(null);
				persons.add(null);
			}
			
			currPos++;
		}			
		
		/* remove unnecessary null elements */
		
		boolean onlyNullElements = true;
		for (int i = persons.size() - 1; i > persons.size() - getBottomElemCount(); i--) {
			if (persons.get(i) != null) {
				onlyNullElements = false;
			}
		}
		if (onlyNullElements) {
			for (int i = 0; i < getBottomElemCount(); i++) {
				persons.remove(persons.size() - 1);
			}
		}			
	}
}
