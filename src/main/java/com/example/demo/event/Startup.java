package com.example.demo.event;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@Component
public class Startup {

	@Autowired
	private TaskScheduler taskScheduler;

	private Map<String, ScheduledFuture<?>> jobsMap = new HashMap<>();

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private EventJobScheduling jobScheduling;

	@EventListener(classes = ApplicationReadyEvent.class)
	public void collectAllEvent() {
		Iterable<Event> events = eventRepository.findAll();
		Iterator<Event> itr = events.iterator();
		while (itr.hasNext()) {
			Event event = itr.next();
			EventJob eventJob = new EventJob(event);
			ScheduledFuture<?> scheduledFuture = taskScheduler.schedule(eventJob,
					new CronTrigger(event.getCronExpression()));
			jobsMap.put(event.getJobId(), scheduledFuture);
		}
		
		jobScheduling.setJobsMap(jobsMap);

	}

}
