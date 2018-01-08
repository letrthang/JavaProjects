package fsm;

import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import controller.MainController;

@Component
public class FSMInternalActions {

	@Bean
	public State1Action state1Action() {
		return new State1Action();
	}

	@Bean
	public State2Action stat2Action() {
		return new State2Action();
	}

	private static class State1Action implements Action<States, Events> {

		@Override
		public void execute(StateContext<States, Events> arg0) {
			MainController.curState = States.STATE1;
			MainController.curAction = "State1Action";
			System.out.println("State1Action");
		}

	}

	private static class State2Action implements Action<States, Events> {

		@Override
		public void execute(StateContext<States, Events> arg0) {
			MainController.curState = States.STATE2;
			MainController.curAction = "State2Action";
			System.out.println("State2Action");
		}

	}
}
