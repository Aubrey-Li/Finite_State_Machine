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
import brown.user.agent.library.AbsBoSIIAgent;

public class BoSIIAgent extends AbsBoSIIAgent implements IAgent {
    private static final String NAME = BoSCompetitionAgent.NAME;// NOTE: your agent's name will be the same as in BosCompetitionAgent

    private static final Integer GOOD_MOOD = 0, BAD_MOOD = 1;
    private static final Integer STUBBORN = 0, COMPROMISE = 1;
    private  Map<Integer, Integer>  columnCurrentState;
    private Map<Integer, Integer> columnPreviousState;
    private int rowCurrentState;
    private int rowPreviousState;

    public BoSIIAgent(String host, int port, ISetup gameSetup, String name) {
        super(host, port, gameSetup, name);

        this.columnCurrentState= new HashMap<>();
        this.columnCurrentState.put(GOOD_MOOD, COMPROMISE);
        this.columnCurrentState.put(BAD_MOOD, STUBBORN);
        this.rowCurrentState = 0;
    }

    /**
     * Returns the next move for your agent.
     *
     * @return The next move, either STUBBORN or COMPROMISE.
     */
    @Override
    public Integer nextMove() {
        // TODO: Task 1

        // One idea is breaking your strategy up into the different scenarios (see below).

        if (isRowPlayer()) {
            // ... (your row-player strategy--Alice strategy)
            Integer myNextMove = this.rowCurrentState;
            List<GameRound> gameRound = getGameHistory();
            if (gameRound.isEmpty()) {
                this.rowPreviousState = myNextMove;
                return myNextMove;
            }
            Integer myMove = gameRound.get(gameRound.size() - 1).getMyMove();
            Integer opponentMove = gameRound.get(gameRound.size() - 1).getOpponentMove();
            Integer columnPlayerMood = gameRound.get(gameRound.size() - 1).getColumnPlayerMood();
            if (this.rowPreviousState == 0) {
                if (myMove.equals(STUBBORN)) {
                    if (opponentMove.equals(STUBBORN)) {
                        if (columnPlayerMood.equals(BAD_MOOD)) { //(C,L,BAD)
                            this.rowCurrentState = 1;
                        }
                    } else if (opponentMove.equals(COMPROMISE)) {
                        if (columnPlayerMood.equals(GOOD_MOOD)) {//(C,C,GOOD)
                            this.rowCurrentState = 1;
                        }
                    }
                } else {
                    this.rowCurrentState = 0;
                }
            }
            if (this.rowPreviousState == 1) {
                if (myMove.equals(COMPROMISE)) {
                    if (opponentMove.equals(STUBBORN)) {
                        if (columnPlayerMood.equals(BAD_MOOD)) { //(L,L,BAD)
                            this.rowCurrentState = 0;
                        }
                    } else if (opponentMove.equals(COMPROMISE)) {
                        if (columnPlayerMood.equals(GOOD_MOOD)) {//(L,C,GOOD)
                            this.rowCurrentState = 0;
                        }
                    }
                } else {
                    this.rowCurrentState = 1;
                }

            }
            this.rowPreviousState = myNextMove;
            return myNextMove;
        }
        else { //column player strategy
            Map<Integer, Integer> myNextStrategy = this.columnCurrentState;
            List<GameRound> gameRound = getGameHistory();
            Integer myMood = getMood();
            if (gameRound.isEmpty()) {
                this.columnPreviousState = myNextStrategy;
                return myNextStrategy.get(myMood);
            }
            Integer myMove = gameRound.get(gameRound.size() - 1).getMyMove();
            Integer opponentMove = gameRound.get(gameRound.size() - 1).getOpponentMove();

            if (this.columnPreviousState.get(GOOD_MOOD).equals(COMPROMISE)
                    && this.columnPreviousState.get(BAD_MOOD).equals(STUBBORN)) {
                if (opponentMove.equals(STUBBORN)) {
                    this.columnCurrentState.put(GOOD_MOOD, STUBBORN);
                    this.columnCurrentState.put(BAD_MOOD, STUBBORN);
                }
                else {
                    this.columnCurrentState.put(GOOD_MOOD, COMPROMISE);
                    this.columnCurrentState.put(BAD_MOOD, COMPROMISE);
                }
            }
            else if (this.columnPreviousState.get(GOOD_MOOD).equals(STUBBORN)
                    && this.columnPreviousState.get(BAD_MOOD).equals(STUBBORN)) {
                if (opponentMove.equals(STUBBORN)) {
                    this.columnCurrentState.put(GOOD_MOOD, STUBBORN);
                }
                else {
                    this.columnCurrentState.put(GOOD_MOOD, COMPROMISE);
                }
                this.columnCurrentState.put(BAD_MOOD, STUBBORN);
            }
            else if (this.columnPreviousState.get(GOOD_MOOD).equals(COMPROMISE)
            && this.columnPreviousState.get(BAD_MOOD).equals(COMPROMISE)){
               if  (opponentMove.equals(COMPROMISE)) {
                   this.columnCurrentState.put(GOOD_MOOD, COMPROMISE);
                   this.columnCurrentState.put(BAD_MOOD, COMPROMISE);
               }
               else {
                   this.columnCurrentState.put(GOOD_MOOD, COMPROMISE);
                   this.columnCurrentState.put(BAD_MOOD, STUBBORN);
               }
            }
            this.columnPreviousState = myNextStrategy;
            return myNextStrategy.get(myMood);
        }
    }



    public static void main(String[] args) throws InterruptedException {
        // Run this to test that your code doesn't crash. Plays against itself.
        List<String> agents = Arrays.asList(BoSIIAgent.class.getCanonicalName(), BoSIIAgent.class.getCanonicalName());
        new BoSSimulation(agents, "input_configs/bos_ii.json", 2121, "outfile", false).run();
    }
}
