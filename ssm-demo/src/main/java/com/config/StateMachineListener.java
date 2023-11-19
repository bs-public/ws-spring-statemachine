package com.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import com.events.Events;
import com.states.States;

public class StateMachineListener extends StateMachineListenerAdapter<States, Events> {

	private final Logger logger = LoggerFactory.getLogger(StateMachineListener.class);

	@Override
	public void stateChanged(State<States, Events> from, State<States, Events> to) {
		String fromState = (from == null) ? States.START.toString() : from.getId().toString();
		logger.info("State change from {} to {}", fromState, to.getId());
	}

}
