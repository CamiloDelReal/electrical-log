package taxcollector;

import java.io.Serializable;

public class Receipt implements Serializable{
	private static final long serialVersionUID = 4601100396234903844L;
	
	private int houseNumber;
	private double payment;
	
	public Receipt(int houseNumber, double payment) {
		this.houseNumber = houseNumber;
		this.payment = payment;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}
	
	

}
