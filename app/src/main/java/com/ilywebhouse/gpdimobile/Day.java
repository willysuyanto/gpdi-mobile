package com.ilywebhouse.gpdimobile;

public class Day implements Cloneable {
	private Assignment assignment;

	public Day() {
	}

	public Day(Assignment assignment) {
		this.assignment = assignment;
	}

	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

	@Override
	public String toString() {
		return assignment != null ? "Masuk": "Off";
	}

	@Override
	protected Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
