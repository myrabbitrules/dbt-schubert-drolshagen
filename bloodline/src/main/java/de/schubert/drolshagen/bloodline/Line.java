package de.schubert.drolshagen.bloodline;

import java.awt.Dimension;

public class Line {
	private Dimension from;
	private Dimension to;
	public Dimension getFrom() {
		return from;
	}
	public void setFrom(Dimension from) {
		this.from = from;
	}
	public Dimension getTo() {
		return to;
	}
	public void setTo(Dimension to) {
		this.to = to;
	}
	public Line(Dimension from, Dimension to) {
		super();
		this.from = from;
		this.to = to;
	}
	
	@Override
	public boolean equals(Object other) {
		if (! (other instanceof Line)) {
			return false;
		}
		Line otherLine = (Line) other;
		return otherLine.from.equals(from) && otherLine.to.equals(to);
	}
	
}
