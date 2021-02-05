package brown.user.agent.lab02;

import brown.system.setup.library.Setup;

public class CompetitionRunner {
	public static void main(String[] args) {
		String NAME = BoSCompetitionAgent.NAME;
		String HOST = "cslab1a";
        int PORT = 2121;
        int mode = 1;

        if (args.length >= 3) {
            HOST = args[0];
            PORT = Integer.parseInt(args[1]);
            mode = Integer.parseInt(args[2]);
        }

		if (mode == 1) {
			new BoSCompetitionAgent(HOST, PORT, new Setup(), NAME);
		} else {
			new BoSIIAgent(HOST, PORT, new Setup(), NAME);
		}
		while (true) {}
	}
}
