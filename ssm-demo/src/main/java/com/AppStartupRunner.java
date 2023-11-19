package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

import com.events.Events;
import com.states.States;

@Component
public class AppStartupRunner implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(AppStartupRunner.class);

	@Autowired
	private StateMachine<States, Events> stateMachine;

	@Override
	public void run(String... args) throws Exception {
		logger.info("Run method of Command Line Runner is called.");
		stateMachine.sendEvent(Events.EVENT1);
		stateMachine.sendEvent(Events.EVENT2);
		stateMachine.sendEvent(Events.EVENT3);	
	}

}
