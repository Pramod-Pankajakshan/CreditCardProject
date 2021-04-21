package action;

import java.util.*;

import backend.BackEndDataProcessor;
import exception.CardException;
import model.Card;
import model.Response;

/**
 * Implementation for adding credit to a card.
 * @author pramod
 *
 */

public class CreditAction implements Action {
	BackEndDataProcessor backend;
	public CreditAction(BackEndDataProcessor be) {
		this.backend=be;	
	}

	@Override
	public Response execute(List<String> args ) {
		System.out.println("Adding credit");
		Response response= new Response();
		String name=args.get(0);
		response.setCustomer(name);
		int credit=Integer.valueOf(args.get(1).replace("$", ""));;
		try {
		Map<String,Card> cards=backend.getCardCache();
		if(cards==null) throw new CardException();
		   Card card=cards.get(name);	   
		   card.setCharge(card.getCharge()-credit);
		   cards.put(name, card);
		   response.setCharge(card.getCharge());
		}catch(Exception ex) {
			System.out.println("Adding credit - Error");
			response.setInerror(true);
		}
		System.out.println("Added credit");
		return response;
	}
}

