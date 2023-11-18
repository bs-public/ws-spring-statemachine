package com.config;

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
		stateMachine.sendEvent(Events.E1);
		stateMachine.sendEvent(Events.E2);
	}

}
