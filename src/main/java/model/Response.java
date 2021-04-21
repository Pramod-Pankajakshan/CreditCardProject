package model;

public class Response {

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Integer getCharge() {
		return charge;
	}

	public void setCharge(int charge) {
		this.charge = charge;
	}

	public boolean isInerror() {
		return inerror;
	}

	public void setInerror(boolean inerror) {
		this.inerror = inerror;
	}

	String customer;
	int charge;
	boolean inerror;

}
