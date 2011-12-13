package de.schubert.drolshagen.bloodline;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import javax.enterprise.context.RequestScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.JPanel;

@RequestScoped
@Named
public class TreeCreation {
	
	@Inject
	private DataManager dataManager;
	
	private Person root = null;
	private int personId;
	private List<Person> personList;
		
	public void getNextPerson() {
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}
	
	private void fillPersonList() {
		personList.add(root);
		int currPos = 0;
		int lastNotNullPos = currPos;
		
		while (currPos <= lastNotNullPos) {
			Person currPerson = personList.get(currPos);
			personList.add(currPerson.getFather());
			personList.add(currPerson.getMother());
			if (currPerson.hasMother()) {
				lastNotNullPos = personList.size() - 1;
			}
			else if (currPerson.hasFather()) {
				lastNotNullPos = personList.size() - 2;
			}
			currPos++;
		}
		
		for (Person person : personList) {
			System.out.println(person + ", ");
		}
		
	}
	
	public String createTree() {
		setRoot(dataManager.getPerson(personId));
		personList = new LinkedList<Person>();
		fillPersonList();
		
		
	/*	JFrame f = new JFrame();
		JPanel p = new TreePanel(person);
		f.add(p);
		f.pack();
		//f.setVisible(true);
		try {
			saveToHDD(p, "datei");
		} catch (IOException e) {
			e.printStackTrace();
		} */
		return "treeDisplay.jsf";
	}	
	
	public static void saveToHDD(JPanel p, String Dateiname) throws IOException {
		BufferedImage img = new BufferedImage(p.getWidth(),p.getHeight(), BufferedImage.TYPE_INT_RGB);
		p.paint(img.createGraphics());
		ImageIO.write(img, "GIF", new File("C:/Users/Richard Schubert/git/dbt-schubert-drolshagen/bloodline/src/main/webapp/resources/images/" + Dateiname + ".gif"));
	}

	public Person getRoot() {
		return root;
	}

	public void setRoot(Person root) {
		this.root = root;
	}
	
}
