package com.example.demo.event;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "event")
@NoArgsConstructor
@AllArgsConstructor
public class Event {
	
	public enum EventType{
		NOTIFICATION, EMAIL, ALERT, POPUP;
	}
	@Id
	@Column(name = "job_id")
	private String jobId;
	
	@Column(name = "cron_expression")
	private String cronExpression;
	
	@Column(name = "data")
	private String data;
	
	@Column(name = "event_type")
	private EventType eventType;

}
