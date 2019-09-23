package com.cgi.smartcv.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Boiler {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private int boilerPressure;
	private float tempInside;
	private float tempOutside;
	private boolean isDoorClosed;
	private long timeMovementRecord;
	private float gasUsage;
	private long timeRecorder;
	
	public Boiler() {
		super();
	}
	
	public Boiler(int boilerPressure, float tempInside, float tempOutside, boolean isDoorClosed, long timeMovementRecord, float gasUsage, long timeRecorder) {
		super();
		this.boilerPressure = boilerPressure;
		this.tempInside = tempInside;
		this.tempOutside = tempOutside;
		this.isDoorClosed = isDoorClosed;
		this.timeMovementRecord = timeMovementRecord;
		this.gasUsage = gasUsage;
		this.timeRecorder = timeRecorder;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getBoilerPressure() {
		return boilerPressure;
	}

	public void setBoilerPressure(int boilerPressure) {
		this.boilerPressure = boilerPressure;
	}

	public float getTempInside() {
		return tempInside;
	}

	public void setTempInside(float tempInside) {
		this.tempInside = tempInside;
	}

	public float getTempOutside() {
		return tempOutside;
	}

	public void setTempOutside(float tempOutside) {
		this.tempOutside = tempOutside;
	}

	public boolean isDoorClosed() {
		return isDoorClosed;
	}

	public void setDoorClosed(boolean isDoorClosed) {
		this.isDoorClosed = isDoorClosed;
	}

	public long getTimeMovementRecord() {
		return timeMovementRecord;
	}

	public void setTimeMovementRecord(long timeMovementRecord) {
		this.timeMovementRecord = timeMovementRecord;
	}

	public float getGasUsage() {
		return gasUsage;
	}

	public void setGasUsage(float gasUsage) {
		this.gasUsage = gasUsage;
	}

	public long getTimeRecorder() {
		return timeRecorder;
	}

	public void setTimeRecorder(long timeRecorder) {
		this.timeRecorder = timeRecorder;
	}

}
