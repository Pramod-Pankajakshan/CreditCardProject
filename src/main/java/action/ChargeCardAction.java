package action;

import java.util.*;

import backend.BackEndDataProcessor;
import model.Card;
import model.Response;

/**
 * Implementation for charging a card.
 * @author pramod
 *
 */
public class ChargeCardAction implements Action {

	BackEndDataProcessor backend;
	public ChargeCardAction(BackEndDataProcessor be) {
		this.backend=be;	
	}
	/**
	 * Charges a card and update backend
	 */
	@Override
	public Response execute(List<String> args ) {
		System.out.println("Adding charge");
		Response response= new Response();
		String name=args.get(0);
		response.setCustomer(name);
		try {
		Map<String,Card> cards=backend.getCardCache();
		   Card card=cards.get(name);
		   int val=Integer.valueOf(args.get(1).replace("$", ""));
		   if(card.getCharge()+val>card.getLimit()){
			   response.setCharge(card.getCharge());
			   return response;// todo
		   }
		   card.setCharge(card.getCharge()+val);
		   cards.put(name, card);
		   response.setCharge(card.getCharge());
		}catch(Exception e) {
			System.out.println("Error Adding charge");
			 response= new Response();
			 name=args.get(0);
			response.setCustomer(name);
			response.setInerror(true);
		}
		System.out.println("Added charge");
		return response;
	}
}

