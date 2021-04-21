package action;


import java.util.*;

import model.Card;
import model.Response;

/**
 * Action is the contract/implementation for different  commands  defined for  credit card process.
 * @author pramod
 *
 */
public interface Action {

	/**
	 * Performs the required steps to handle this action.
	 * @param args
	 * @return
	 */
	public Response execute(List<String> args);
	
	

}


