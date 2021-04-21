package backend;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import exception.CardException;
import model.Card;


public class BackEndDataProcessorTest {



	@Test
	public void cardTest() {
		BackEndDataProcessor be= new BackEndDataProcessor();
		Card card= new Card();
		card.setUuid("test");
		card.setCardNumber(1234);
		try {
			be.create(card);
		} catch (CardException e) {
			fail("Exception caught");	
		}
		assertTrue(be.getCardCache().get("test")!=null);
	}
}

