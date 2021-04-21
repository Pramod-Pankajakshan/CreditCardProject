package action;

import java.util.*;

import backend.BackEndDataProcessor;
import exception.CardException;
import model.Card;
import utils.ValidateCreditCardNumber;
import model.*;

/**
 * Implementation for ADD command. Adds a  user and card
 * @author pramod
 *
 */
public class AddCardAction implements Action {
	BackEndDataProcessor backend;
	public AddCardAction(BackEndDataProcessor be) {
		this.backend=be;	
	}
    /**
     * Adds a card after validation to system.
     */
	@Override
	public Response execute(List<String> args ) {
		System.out.println("Adding card");
		Response response= new Response();
		if(!validate(args.get(1))) {	
			System.out.println("Invalid card");
			response.setInerror(true);
			response.setCustomer(args.get(0));
			return response;
		}
		Card crd= new Card();
		
		crd.setLimit(Integer.valueOf(args.get(2).replace("$", "")));
		Customer cust= new Customer();
		cust.setLastName(args.get(0));
		
		crd.setCustomer(cust);
		crd.setCardNumber(Long.valueOf(args.get(1)));
		crd.setUuid(args.get(0));
		
		try {
			backend.create(crd);
		} catch (CardException e) {
			response.setInerror(true);
			response.setCustomer(args.get(0));
		}
		System.out.println("Added card");
		response.setCharge(0);
		response.setCustomer(args.get(0));
		return response;
	}
	/**
	 * Validate card number
	 * @param cardNumber
	 * @return true if its a valid card
	 */
	private boolean validate(String cardNumber) {
		return ValidateCreditCardNumber.isValidCreditCardNumber(cardNumber);
		 
	}
}

