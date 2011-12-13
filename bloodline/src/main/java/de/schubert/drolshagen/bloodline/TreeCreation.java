package de.schubert.drolshagen.bloodline;

import java.awt.Dimension;
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
	
	private Person rootPerson = null;
	private int personId;
	//private List<Person> personList;
	private PersonTree personTree;
	private Dimension panelDim;
	
	private static final Dimension elementDim = new Dimension(50, 25); // in px
	private static final Dimension gapDim = new Dimension(50, 80); // in px
	
	public void getNextPerson() {
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}
	
	private void calcPanelDim() {
		int width = personTree.getBottomElemCount() * (elementDim.width + gapDim.width);
		int height = personTree.getLevelCount() * (elementDim.height + gapDim.height);
		panelDim = new Dimension(width, height);	
	}
	
	public String createTree() {
		setRoot(dataManager.getPerson(personId));
		personTree = new PersonTree();
		personTree.fill(rootPerson);
		calcPanelDim();
		
		
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
		return rootPerson;
	}

	public void setRoot(Person root) {
		this.rootPerson = root;
	}

	public Dimension getPanelDim() {
		return panelDim;
	}

	public void setPanelDim(Dimension panelDim) {
		this.panelDim = panelDim;
	}
	
}
