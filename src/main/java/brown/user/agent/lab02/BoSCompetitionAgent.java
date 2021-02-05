package brown.user.agent.lab02;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import brown.communication.action.IGameAction;
import brown.communication.action.library.GameAction;
import brown.communication.messages.IActionMessage;
import brown.communication.messages.IActionRequestMessage;
import brown.communication.messages.IInformationMessage;
import brown.communication.messages.ISimulationReportMessage;
import brown.communication.messages.ITypeMessage;
import brown.communication.messages.library.ActionMessage;
import brown.simulations.BoSSimulation;
import brown.system.setup.ISetup;
import brown.system.setup.library.Setup;
import brown.user.agent.IAgent;
import brown.user.agent.library.AbsAgent;
import brown.user.agent.library.AbsFsmAgent;

public class BoSCompetitionAgent extends AbsFsmAgent implements IAgent {
	protected static final String NAME = "FSMPure";  // TODO: give your agent a name
    
    private int currentState;
	
	private static final Integer STUBBORN = 0, COMPROMISE = 1;

	public BoSCompetitionAgent(String host, int port, ISetup gameSetup, String name) {
		super(host, port, gameSetup, name);
        
        this.currentState = 0;
	}
	
	/**
	 * Returns the next move for your agent based on this.currentState.
	 * @return The next move, either STUBBORN or COMPROMISE.
	 */
	@Override
	public Integer nextMove() {
		// TODO: Task 1
		// implement your finite state automaton
		if (this.currentState == 0) {
			return STUBBORN;
		} else if (this.currentState == 1) {
			return STUBBORN;
		} else if (this.currentState == 2) {
			return STUBBORN;
		}
		return STUBBORN;
	}
	
	/**
	 * Updates this.currentState based on the previous round's actions.
	 * @param myMove
	 * @param opponentMove
	 */
	@Override
	public void transitionState(Integer myMove, Integer opponentMove) {
		if (this.currentState == 0) {
			if (opponentMove.equals(STUBBORN)) {
				this.currentState = 1;
			} else {
				this.currentState = 0;
			}
		} else if (this.currentState == 1) {
			if (opponentMove.equals(STUBBORN)) {
				this.currentState = 2;
			} else {
				this.currentState = 0;
			}
		} else if (this.currentState == 2) {
			if (opponentMove.equals(STUBBORN)) {
				this.currentState = 3;
			} else {
				this.currentState = 0;
			}
		} else {
			this.currentState = 0;
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		// Run this to test that your code doesn't crash. Plays against itself.
		List<String> agents = Arrays.asList(BoSCompetitionAgent.class.getCanonicalName(), BoSCompetitionAgent.class.getCanonicalName());
        new BoSSimulation(agents, "input_configs/bos.json", 2121, "outfile", false).run();
    }
}
