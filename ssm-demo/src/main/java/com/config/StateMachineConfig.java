package com.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import com.events.Events;
import com.states.States;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

	private final Logger logger = LoggerFactory.getLogger(StateMachineConfig.class);

	// @formatter:off
	@Override
	public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
		config.withConfiguration()
            .autoStartup(true)
            .listener(listener());
	}

	@Override
	public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
		states.withStates()
            .initial(States.START)
            .state(States.STATE1)
            .state(States.STATE2)
			.end(States.END);
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
		transitions
        	.withExternal()
            	.source(States.START)
            	.target(States.STATE1)
            	.event(Events.EVENT1)
            .and()
            .withExternal()
            	.source(States.STATE1)
            	.target(States.STATE2)
            	.event(Events.EVENT2)
			.and()
			.withExternal()
				.source(States.STATE2)
				.target(States.END)
				.event(Events.EVENT3);
	}

	@Bean
	public StateMachineListener<States, Events> listener() {
		return new StateMachineListenerAdapter<States, Events>() {
			@Override
			public void stateChanged(State<States, Events> from, State<States, Events> to) {
				String fromState = (from == null) ? States.START.toString() : from.getId().toString();
				logger.info("State change from {} to {} with event {}", fromState, to.getId());
			}
		};
	}
	// @formatter:on
}
