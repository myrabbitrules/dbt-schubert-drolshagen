package de.schubert.drolshagen.bloodline;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.JFrame;
import javax.swing.JPanel;

@RequestScoped
@Named
public class TreeCreation {
	
	@Inject
	private DataManager dataManager;
	
	private Person root = null;
	private int personId;
	private List<String> personList = new ArrayList<String>();
	
	public String getNextPerson() {
		personList.add("Hans");
		personList.add("Hermine");
		String result =  personList.get(0);
		personList.remove(0);
		return result;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}
	
	public String createTree() {
		setRoot(dataManager.getPerson(personId));
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
