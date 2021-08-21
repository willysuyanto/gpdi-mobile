package com.ilywebhouse.gpdimobile;

import java.time.LocalTime;

public class Assignment implements Cloneable {
	private String shift;

	public Assignment(String shift) {
		this.shift = shift;
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
