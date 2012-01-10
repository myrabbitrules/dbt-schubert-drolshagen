package de.schubert.drolshagen.bloodline;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * @author Rene Drolshagen and Richard Schubert
 *
 * This class generates a Picture with the family tree
 */
public class TreePanel extends JPanel {
	private static final long serialVersionUID = 2075169297908070725L;
	/**
	 * The root Person where the Tree Drawing will start
	 */
	private Person root;
	/**
	 * The specific height between the branches of a tree
	 */
	private int h = 50;
	
	/**
	 * Creates a new JPanel with the specific properties
	 * @param root The root Person where the tree will start
	 */
	public TreePanel(Person root) {		
		super();
	    setOpaque(false); 
	    //setLayout(new BorderLayout());
	    setBorder(BorderFactory.createLineBorder(Color.black));
	    this.root = root;
	}
	
	/**
	 * Calculates the perfect size for the picture
	 */
	 public Dimension getPreferredSize() {
		 Dimension layoutSize = super.getPreferredSize();
         int max = Math.max(layoutSize.width, layoutSize.height);
		 int depth = root.calcDepth();
         return new Dimension(max + (depth*125), max + (depth*50)); 
     }
	 
	 /**
	  * Starts painting the Tree
	  */
	 @Override
     protected void paintComponent(final Graphics g) {
		 super.paintComponent(g);
		 g.setColor(Color.white);
		 g.fillRect(0, 0, getWidth(), getHeight());
		 g.setColor(Color.black);
         if (root != null) {
             paintPerson(g, root, 0, this.getWidth() / 2);
         }
    
     }

	 /**
	  * Paints the tree recursiv
	  * @param g The Graphic where you will paint on
	  * @param person The Person where the drawing will start
	  * @param level The level of the tree where you are at
	  * @param x The Startpoint of the Drawing
	  */
     private void paintPerson(Graphics g, Person person, int level, int x) {
         g.fillOval(x, 10 + level * h, 10, 10);
         g.drawString(person.getForename(), x + 15, 20 + level * h);
         if (person.getFather() != null) {
             g.drawLine(x + 5, 15 + level * h, x - (4 - level) * 20 + 5, 15 + (level + 1) * h);
             paintPerson(g, person.getFather(), level + 1, x - (4 - level) * 20);
         }
         if (person.getMother() != null) {
             g.drawLine(x + 5, 15 + level * h, x + (4 - level) * 20 + 5, 15 + (level + 1) * h);
             paintPerson(g, person.getMother(), level + 1, x + (4 - level) * 20);
         }
     }
     
}
