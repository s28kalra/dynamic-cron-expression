package com.example.demo.event;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventJob implements Runnable {

	private Event event;

	@Override
	public void run() {
		System.out.println("execution Event : {" + event + " } at time : " + LocalDateTime.now());
	}

}
