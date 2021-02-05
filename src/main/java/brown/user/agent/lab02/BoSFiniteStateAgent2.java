package brown.user.agent.lab02;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import brown.simulations.BoSSimulation;
import brown.system.setup.ISetup;
import brown.system.setup.library.Setup;
import brown.user.agent.IAgent;
import brown.user.agent.library.AbsAgent;
import brown.user.agent.library.AbsFsmAgent;
import brown.user.agent.library.BasicRPSAgent;

public class BoSFiniteStateAgent2 extends AbsFsmAgent implements IAgent {
	private static final int PORT = 2121;
    
    private int currentState;
	
	private static final Integer STUBBORN = 0, COMPROMISE = 1;

	public BoSFiniteStateAgent2(String host, int port, ISetup gameSetup, String name) {
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
			return COMPROMISE;
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
			this.currentState = 1;
		} else if (this.currentState == 1) {
			this.currentState = 2;
		}
		else if (this.currentState == 2) {
			this.currentState = 3;
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
        List<String> agents = Arrays.asList(BoSFiniteStateAgent2.class.getCanonicalName(), BoSPunitiveAgent.class.getCanonicalName());
        new BoSSimulation(agents, "input_configs/bos_quick.json", PORT, "outfile", false).run();
    }
}
