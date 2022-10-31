package com.example.demo.event;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

@Service
public class EventJobScheduling {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private TaskScheduler taskScheduler;
	
	private Map<String, ScheduledFuture<?>> jobsMap;

	public String addAnEvent(Event event) {
		String jobId = UUID.randomUUID().toString();
		event.setJobId(jobId);
		EventJob eventJob = new EventJob(event);
		ScheduledFuture<?> scheduledFuture= taskScheduler.schedule(eventJob, new CronTrigger(event.getCronExpression()));
		jobsMap.put(jobId, scheduledFuture);
		eventRepository.save(event);
		return jobId;
	}
	
	public boolean deleteAnEvent(String jobId) {
		eventRepository.deleteById(jobId);
		jobsMap.get(jobId).cancel(true);
		jobsMap.remove(jobId);
		return true;
	}

	public Map<String, ScheduledFuture<?>> getJobsMap() {
		return jobsMap;
	}

	public void setJobsMap(Map<String, ScheduledFuture<?>> jobsMap) {
		this.jobsMap = jobsMap;
	}
}
