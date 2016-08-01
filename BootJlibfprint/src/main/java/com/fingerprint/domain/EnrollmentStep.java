package com.fingerprint.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;


//says that this class is a JPA persistent model
@Entity
public class EnrollmentStep {

	//set id as primary key and auto-generate values
	@Id
	@GeneratedValue
	private int id;
	
	private int step;
	
	@Lob
	@Column
	private String message;
	
	private String resultCode;
	
	private LocalDateTime timestamp;
	
	
	
	//Constructor without args for JPA
	public EnrollmentStep(){
		
	}
	
	public int getStep() {
		return step;
	}
	
	public void setStep(int step) {
		this.step = step;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getResultCode() {
		return resultCode;
	}
	
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return String.format("Step %d, %s, Result code %s, Timestamp %s", this.step, this.message, this.resultCode, this.timestamp.toString());
	}
	
	
}
