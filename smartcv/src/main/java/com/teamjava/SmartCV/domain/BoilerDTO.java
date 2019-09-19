package com.teamjava.SmartCV.domain;

public class BoilerDTO {
	private int boilerPressure;
	private float tempInside;
	private float tempOutside;
	private boolean isDoorClosed;
	private long timeMovementRecord;
	private float gasUsage;
	private long timeRecorder;

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
