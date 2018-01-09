package fsm;

import org.springframework.statemachine.annotation.OnStateEntry;
import org.springframework.statemachine.annotation.OnStateExit;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

import controller.MainController;

@WithStateMachine
public class FSMExternalActions {

	@OnTransition(source = "STATE2", target = "STATE1")
	public void toState1() {

		MainController.curState = States.STATE1;
		MainController.curAction = MainController.curAction + ". " + "toState1";
		System.out.println("toState1");
	}

	@OnTransition(source = "STATE1", target = "STATE2")
	public void toState2() {

		MainController.curState = States.STATE2;
		MainController.curAction = MainController.curAction + ". " + "toState2";
		System.out.println("toState2");
	}

	@OnStateEntry(target = "STATE1")
	public void state1Entry() {

		MainController.curState = States.STATE1;
		MainController.curAction = MainController.curAction + ". " + "state1Entry";
		System.out.println("state1Entry");
	}

	@OnStateEntry(target = "STATE2")
	public void state2Entry() {

		MainController.curState = States.STATE2;
		MainController.curAction = MainController.curAction + ". " + "state2Entry";
		System.out.println("state2Entry");
	}

	@OnStateExit(source = "STATE1")
	public void state1Exit() {
		MainController.curAction = MainController.curAction + ". " + "state1Exit";
		System.out.println("state1Exit");
	}

	@OnStateExit(source = "STATE2")
	public void state2Exit() {
		MainController.curAction = MainController.curAction + ". " + "state2Exit";
		System.out.println("state2Exit");
	}

}
