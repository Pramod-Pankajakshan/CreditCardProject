
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import action.Action;
import action.AddCardAction;
import action.ChargeCardAction;
import action.CreditAction;
import backend.BackEndDataProcessor;
import model.Response;

public class Processor {
	public enum COMMAND {
		Add, Charge, Credit
	};

	private static Map<String, Action> COMMANDS = new HashMap<>();
	static {
		BackEndDataProcessor backend = new BackEndDataProcessor();
		COMMANDS.put(COMMAND.Add.toString(), new AddCardAction(backend));
		COMMANDS.put(COMMAND.Charge.toString(), new ChargeCardAction(backend));
		COMMANDS.put(COMMAND.Credit.toString(), new CreditAction(backend));
	}

	Map<String, String> summary = new TreeMap<>();

	/**
	 * Processes a single command for credit card process
	 * 
	 * @param line
	 */
	public void process_command(String line) {
		String[] arguments = line.split("[ ]+");
		Action cmd = COMMANDS.get(arguments[0]);
		if (cmd == null)
			throw new IllegalArgumentException("Unknown command " + line);

		List<String> args = new LinkedList<String>(Arrays.asList(arguments));
		args.remove(0);
		Response success = cmd.execute(args);
		summary.put(success.getCustomer(), success.isInerror() ? "error" : success.getCharge().toString());

	}

	/**
	 * Returns a summary for the given set of actions in a session
	 * 
	 * @return
	 */
	public String summary() {
		StringBuilder res = new StringBuilder();
		summary.entrySet().stream().forEach(
				e -> res.append((e.getKey() + ":" + (e.getValue().equals("error") ? "" : "$") + e.getValue()) + "\n"));
		return res.toString() + "END";
	}

}
