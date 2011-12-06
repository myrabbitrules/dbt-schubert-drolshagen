package de.schubert.drolshagen.bloodline;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.enterprise.context.RequestScoped;
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
	
	private int personId;

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}
	
	public void createTree() {
		Person person = dataManager.getPerson(personId);
		JFrame f = new JFrame();
		JPanel p = new TreePanel(person);
		f.add(p);
		f.pack();
		//f.setVisible(true);
		try {
			saveToHDD(p, "datei");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public static void saveToHDD(JPanel p, String Dateiname) throws IOException {
		BufferedImage img = new BufferedImage(p.getWidth(),p.getHeight(), BufferedImage.TYPE_INT_RGB);
		p.paint(img.createGraphics());
		ImageIO.write(img, "GIF", new File("C:/Users/Richard Schubert/git/dbt-schubert-drolshagen/bloodline/src/main/webapp/resources/images/" + Dateiname + ".gif"));
	}
	
}
