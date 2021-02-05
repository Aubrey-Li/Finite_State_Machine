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

public class BoSFiniteStateAgent1 extends AbsFsmAgent implements IAgent {
    private static final int PORT = 2121;

    private int currentState;

    private static final Integer STUBBORN = 0, COMPROMISE = 1;

    public BoSFiniteStateAgent1(String host, int port, ISetup gameSetup, String name) {
        super(host, port, gameSetup, name);

        this.currentState = 0;
    }

    /**
     * Returns the next move for your agent based on this.currentState.
     *
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
     *
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
        List<String> agents = Arrays.asList(BoSFiniteStateAgent1.class.getCanonicalName(), BoSReluctantAgent.class.getCanonicalName());
        new BoSSimulation(agents, "input_configs/bos_quick.json", PORT, "outfile", false).run();
    }
}
