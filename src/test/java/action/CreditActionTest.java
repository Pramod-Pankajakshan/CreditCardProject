package action;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.*;

import org.junit.Test;

import backend.BackEndDataProcessor;
import exception.CardException;
import model.Card;


public class CreditActionTest {

	@Test
	public void cardTest() {
		BackEndDataProcessor backend = new BackEndDataProcessor();
		AddCardAction add= new AddCardAction(backend);
		List<String> input = new ArrayList<>();
		input.add("Tom");
		input.add("4111111111111111");
		input.add("$1000");
		add.execute(input);
		CreditAction credit= new CreditAction(backend);
		List<String> input2 = new ArrayList<>();
		input2.add("Tom");
		input2.add("$999");
		credit.execute(input2);
		assertTrue(backend.getCardCache().get("Tom").getCharge()==-999);
	}
	@Test
	public void cardTestAll() {
		BackEndDataProcessor backend = new BackEndDataProcessor();
		AddCardAction add= new AddCardAction(backend);
		List<String> input = new ArrayList<>();
		input.add("Tom");
		input.add("4111111111111111");
		input.add("$1000");
		add.execute(input);
		CreditAction credit= new CreditAction(backend);
		List<String> input2 = new ArrayList<>();
		input2.add("Tom");
		input2.add("$999");
		credit.execute(input2);
		assertTrue(backend.getCardCache().get("Tom").getCharge()==-999);
		ChargeCardAction charge= new ChargeCardAction(backend);
		List<String> input3 = new ArrayList<>();
		input3.add("Tom");
		input3.add("$1000");
		charge.execute(input3);
		assertTrue(backend.getCardCache().get("Tom").getCharge()==1);
		
		List<String> input4 = new ArrayList<>();
		input4.add("Tom");
		input4.add("$100");
		charge.execute(input4);
		System.out.println(backend.getCardCache().get("Tom").getCharge());
		assertTrue(backend.getCardCache().get("Tom").getCharge()==101);
	}
	/**
	 * Mix credit charge to cross the limit of card
	 */
	@Test
	public void cardTestMix() {
		BackEndDataProcessor backend = new BackEndDataProcessor();
		AddCardAction add= new AddCardAction(backend);
		List<String> input = new ArrayList<>();
		input.add("Tom");
		input.add("4111111111111111");
		input.add("$1000");
		add.execute(input);
		CreditAction credit= new CreditAction(backend);
		List<String> input2 = new ArrayList<>();
		input2.add("Tom");
		input2.add("$999");
		credit.execute(input2);
		assertTrue(backend.getCardCache().get("Tom").getCharge()==-999);
		ChargeCardAction charge= new ChargeCardAction(backend);
		List<String> input3 = new ArrayList<>();
		input3.add("Tom");
		input3.add("$1000");
		charge.execute(input3);
		assertTrue(backend.getCardCache().get("Tom").getCharge()==1);
		
		List<String> input4 = new ArrayList<>();
		input4.add("Tom");
		input4.add("$1000");
		charge.execute(input4);
		System.out.println(backend.getCardCache().get("Tom").getCharge());
		assertTrue(backend.getCardCache().get("Tom").getCharge()==1);
	}
	
	/**
	 * Mix credit charge to cross the limit of card
	 */
	@Test
	public void cardTestMixAddCreditAndCheck() {
		BackEndDataProcessor backend = new BackEndDataProcessor();
		AddCardAction add= new AddCardAction(backend);
		List<String> input = new ArrayList<>();
		input.add("Tom");
		input.add("4111111111111111");
		input.add("$1000");
		add.execute(input);
		CreditAction credit= new CreditAction(backend);
		List<String> input2 = new ArrayList<>();
		input2.add("Tom");
		input2.add("$999");
		credit.execute(input2);
		assertTrue(backend.getCardCache().get("Tom").getCharge()==-999);
		ChargeCardAction charge= new ChargeCardAction(backend);
		List<String> input3 = new ArrayList<>();
		input3.add("Tom");
		input3.add("$1000");
		charge.execute(input3);
		assertTrue(backend.getCardCache().get("Tom").getCharge()==1);
		
		List<String> input4 = new ArrayList<>();
		input4.add("Tom");
		input4.add("$1000");
		charge.execute(input4);
		System.out.println(backend.getCardCache().get("Tom").getCharge());
		assertTrue(backend.getCardCache().get("Tom").getCharge()==1);
		List<String> input5 = new ArrayList<>();
		input5.add("Tom");
		input5.add("$1001");
		credit.execute(input5);
		System.out.println(backend.getCardCache().get("Tom").getCharge());
		assertTrue(backend.getCardCache().get("Tom").getCharge()==-1000);
	}
}

