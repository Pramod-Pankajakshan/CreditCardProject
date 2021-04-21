package action;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.*;

import org.junit.Test;

import backend.BackEndDataProcessor;
import exception.CardException;
import model.Card;
import model.Response;


public class ChargeCardTest {

	@Test
	public void cardTest() {
		BackEndDataProcessor backend = new BackEndDataProcessor();
		AddCardAction add= new AddCardAction(backend);
		List<String> input = new ArrayList<>();
		input.add("Tom");
		input.add("4111111111111111");
		input.add("$1000");
		add.execute(input);
		ChargeCardAction charge= new ChargeCardAction(backend);
		List<String> input2 = new ArrayList<>();
		input2.add("Tom");
		input2.add("$1000");
		Response res=charge.execute(input2);
		assertTrue(backend.getCardCache().get("Tom").getCharge()==1000);
		assertTrue(!res.isInerror());
		assertTrue(res.getCharge()==1000);
	}
	
	@Test
	public void cardTestError() {
		BackEndDataProcessor backend = new BackEndDataProcessor();
		AddCardAction add= new AddCardAction(backend);
		List<String> input = new ArrayList<>();
		input.add("Tom");
		input.add("411111111111111");
		input.add("$1000");
		add.execute(input);
		ChargeCardAction charge= new ChargeCardAction(backend);
		List<String> input2 = new ArrayList<>();
		input2.add("Tom");
		input2.add("$1000");
		Response resp=charge.execute(input2);
		assertTrue(backend.getCardCache().get("Tom")==null);
		assertTrue(resp.isInerror());
	}
}

