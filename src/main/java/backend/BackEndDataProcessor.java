package backend;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import exception.CardException;
import model.Card;

public class BackEndDataProcessor {

	Map<String, Card> nameToCardInfo = new ConcurrentHashMap<>();

	public void create(Card card) throws CardException {
		try {
			nameToCardInfo.put(card.getUuid(), card);
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		} finally {
			System.out.println("Added to data Store");
		}

	}

	public Map<String, Card> getCardCache() {
		return this.nameToCardInfo;
	}

	public void setCardCache(Map<String, Card> cards) {
		this.nameToCardInfo = cards;

		return;
	}

}
