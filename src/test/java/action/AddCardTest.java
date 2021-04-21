package action;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import backend.BackEndDataProcessor;
import model.Response;


public class AddCardTest {

	@Test
	public void cardTest() {
		BackEndDataProcessor backend = new BackEndDataProcessor();
		Action add= new AddCardAction(backend);
		List<String> input = new ArrayList<>();
		input.add("Tom");
		input.add("4111111111111111");
		input.add("$1000");
		Response resp=add.execute(input);
		assertTrue(backend.getCardCache().get("Tom")!=null);
		assertTrue(!resp.isInerror());
		assertTrue(resp.getCharge()==0);
	}
	
	@Test
	public void cardTestInvalid() {
		BackEndDataProcessor backend = new BackEndDataProcessor();
		Action add= new AddCardAction(backend);
		List<String> input = new ArrayList<>();
		input.add("Tom");
		input.add("411111111111111");
		input.add("$1000");
		Response resp=add.execute(input);
		assertTrue(backend.getCardCache().get("Tom")==null);
		assertTrue(resp.isInerror());
	}
}

