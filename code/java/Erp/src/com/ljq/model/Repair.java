package com.ljq.model;

public class Repair implements java.io.Serializable
{

	private static final long serialVersionUID = 1L;
	private String id;
	private String date;
	private String cause;
	private String household_id;
	private String state;
	private String household_name;

	public Repair(){}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getHousehold_id() {
		return household_id;
	}

	public void setHousehold_id(String household_id) {
		this.household_id = household_id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getHousehold_name() {
		return household_name;
	}

	public void setHousehold_name(String household_name) {
		this.household_name = household_name;
	}

	@Override
	public String toString() {
		return " [Repair id=" + id + ", date=" + date + ", cause=" + cause + ", household_id=" + household_id 
				+ ", state=" + state + ", household_name=" + household_name + "]  ";
	}
}