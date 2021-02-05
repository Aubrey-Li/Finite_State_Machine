package brown.user.agent.lab02;

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
import brown.platform.accounting.IAccountUpdate;
import brown.system.setup.ISetup;
import brown.system.setup.library.Setup;
import brown.user.agent.IAgent;
import brown.user.agent.library.AbsAgent;
import brown.user.agent.library.AbsFsmAgent;

public class BoSPunitiveAgent extends AbsFsmAgent implements IAgent {
    private int currentState;
	
	private static final Integer STUBBORN = 0, COMPROMISE = 1;

	public BoSPunitiveAgent(String host, int port, ISetup gameSetup, String name) {
		super(host, port, gameSetup, name);
        
        this.currentState = 0;
	}
	
	/**
	 * Returns the next move for your agent based on this.currentState.
	 * @return The next move, either STUBBORN or COMPROMISE.
	 */
	@Override
	public Integer nextMove() {
		if (this.currentState == 0) {
			return STUBBORN;
		} else if (this.currentState == 1) {
			return COMPROMISE;
		} else if (this.currentState == 2) {
			return STUBBORN;
		} else if (this.currentState == 3) {
			return COMPROMISE;
		} else {
			return STUBBORN;
		}
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
			this.currentState = 2;
		} else if (this.currentState == 2) {
			if (opponentMove.equals(STUBBORN)) {
				this.currentState = 3;
			} else {
				this.currentState = 2;
			}
		} else if (this.currentState == 3) {
			this.currentState = 4;
		} else {
			this.currentState = 4;
		}
	}
}
