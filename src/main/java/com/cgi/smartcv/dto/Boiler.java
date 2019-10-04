package com.cgi.smartcv.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value = "Boiler", description = "All details about the Boiler.")
public class Boiler {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "The database generates boiler ID")
	private long id;

	@ApiModelProperty(notes = "Pressure of the Boiler")
	private int boilerPressure;

	@ApiModelProperty(notes = "Temperature inside of the house")
	private float tempInside;

	@ApiModelProperty(notes = "Temperature outside of the house")
	private float tempOutside;

	@ApiModelProperty(notes = "Confirmation if the door is closed")
	private boolean isDoorClosed;

	@ApiModelProperty(notes = "Time recodered, lastest in the room")
	private long timeMovementRecord;

	@ApiModelProperty(notes = "Gas usage Boiler")
	private float gasUsage;

	@ApiModelProperty(notes = "Time recorder")
	private long timeRecorder;

	public Boiler() {
	}

	public Boiler(int boilerPressure, float tempInside, float tempOutside, boolean isDoorClosed,
			long timeMovementRecord, float gasUsage, long timeRecorder) {
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

	@Override
	public String toString() {
		return "Boiler{" + "id=" + id + ", boilerPressure=" + boilerPressure + ", tempInside=" + tempInside
				+ ", tempOutside=" + tempOutside + ", isDoorClosed=" + isDoorClosed + ", timeMovementRecord="
				+ timeMovementRecord + ", gasUsage=" + gasUsage + ", timeRecorder=" + timeRecorder + '}';
	}
}
