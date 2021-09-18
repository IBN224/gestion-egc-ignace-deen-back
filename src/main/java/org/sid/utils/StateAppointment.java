package org.sid.utils;

public enum StateAppointment {

	AVAILABLE("AVAILABLE"),
	PASSED("PASSED"),
	FULL("FULL");
	
	private String state;
	
	private StateAppointment(String state) {
		// TODO Auto-generated constructor stub
		this.state = state;
	}
	
	public String get() {
		return this.state;
	}
	
}
