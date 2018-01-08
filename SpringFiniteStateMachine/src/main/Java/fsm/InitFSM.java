package fsm;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

@Component
public class InitFSM {
	@Autowired
	StateMachine<States, Events> stateMachine;
	
	@PostConstruct
	public void initFSM() throws Exception {
		System.out.println("FSM .... started....");
		stateMachine.start();
	}
	
}
