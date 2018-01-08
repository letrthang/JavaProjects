/**
 * 
 */
package controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fsm.Events;
import fsm.States;

/**
 * @author Thang Le
 *
 */

@Controller
public class MainController {

	@Autowired
	StateMachine<States, Events> stateMachine;

	public static States curState;
	public static String curAction;

	public MainController() {
		System.out.println("MainController... inited....");
	}

	/**
	 * process for user login
	 * 
	 * @param map
	 * @param submitEventButton
	 * @param password
	 * @param userName
	 * @param urlAction
	 * @return
	 */

	@RequestMapping(value = "/{urlAction}", method = { RequestMethod.GET, RequestMethod.POST })
	public String handlerEventTrigger(Model model,
			@RequestParam(value = "submitEventButton", required = false) String submitEventButton,
			@RequestParam(value = "selectedEvent", required = false) String selectedEvent,
			@PathVariable String urlAction) {

		System.out.println("A event submited to server! Event = " + selectedEvent);

		// for user-login process
		if (urlAction != null && urlAction.equals("EventTrigger")) {
			if ((selectedEvent == null) || (selectedEvent == null) || (submitEventButton == null)) {
				return "index";
			} else if (selectedEvent.equals("event1")) {
				stateMachine.sendEvent(Events.EVENT1);
				model.addAttribute("currentState", curState);
				model.addAttribute("currentAction", curAction);
				return "index";
			} else if (selectedEvent.equals("event2")) {
				stateMachine.sendEvent(Events.EVENT2);
				model.addAttribute("currentState", curState);
				model.addAttribute("currentAction", curAction);
				return "index";
			} else if (selectedEvent.equals("event3")) {
				stateMachine.sendEvent(Events.EVENT3);
				model.addAttribute("currentState", curState);
				model.addAttribute("currentAction", curAction);
				return "index";
			} else if (selectedEvent.equals("event4")) {
				stateMachine.sendEvent(Events.EVENT4);
				model.addAttribute("currentState", curState);
				model.addAttribute("currentAction", curAction);
				return "index";
			}
		}

		return "index";
	}

}
