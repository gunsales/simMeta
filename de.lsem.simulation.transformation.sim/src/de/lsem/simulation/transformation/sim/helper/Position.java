package de.lsem.simulation.transformation.sim.helper;

public class Position {
	
	public Position ( int x, int y ) {
		setX(x);
		setY(y);
	}
	
	//Used to keep coordinates from bpmn-model and use it in target-model
	private int x;
	private int y;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
