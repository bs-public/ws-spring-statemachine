package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

import com.events.BookEvents;
import com.states.BookStates;

/**
 * @EnableStateMachine This annotation creates a default state machine when the
 *                     application starts
 */
@SpringBootApplication
public class App implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(App.class);

	private final StateMachine<BookStates, BookEvents> stateMachine;

	public App(StateMachine<BookStates, BookEvents> stateMachine) {
		this.stateMachine = stateMachine;
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Currently state machine is in available state therefore this event would not
		// be accepted and machine stayed in the AVAILABLE state
		boolean returnAccepted = stateMachine.sendEvent(BookEvents.RETURN);
		logger.info("return accepted: {}", returnAccepted);

		// Currently state machine is in available state therefore this event (BORROW)
		// would be accepted
		boolean borrowAccepted = stateMachine.sendEvent(BookEvents.BORROW);
		logger.info("borrow accepted: {}", borrowAccepted);
	}

}
