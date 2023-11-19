package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

import com.events.BookEvents;
import com.states.BookStates;

@Component
public class AppStartupRunner implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(AppStartupRunner.class);

	private final StateMachine<BookStates, BookEvents> stateMachine;

	public AppStartupRunner(StateMachine<BookStates, BookEvents> stateMachine) {
		this.stateMachine = stateMachine;
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Run method of Command Line Runner is called.");
		// Currently state machine is in available state
		// therefore this event would not be accepted and machine stayed in the
		// AVAILABLE state
		boolean returnAccepted = stateMachine.sendEvent(BookEvents.RETURN);
		logger.info("return accepted: {}", returnAccepted);

		// Currently state machine is in available state
		// therefore this event (BORROW) would be accepted
		boolean borrowAccepted = stateMachine.sendEvent(BookEvents.BORROW);
		logger.info("borrow accepted: {}", borrowAccepted);
	}

}
