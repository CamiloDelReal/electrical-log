package electricRegistry.util;

import java.io.Serializable;

public class HouseWithRealAndNotRealConsumption extends HouseWithoutConsumptions implements Serializable{
	private static final long serialVersionUID = 3653726558576727009L;
	
	private double real;
	private double notReal;
	
	public HouseWithRealAndNotRealConsumption(int number, int population, String address, double real, double notReal){
		super(number, population, address);
		
		this.real = real;
		this.notReal = notReal;
	}
	
	public HouseWithRealAndNotRealConsumption(HouseWithoutConsumptions house, double real, double notReal){
		super(house);
		
		this.real = real;
		this.notReal = notReal;
	}

	public double getReal() {
		return real;
	}

	public void setReal(double real) {
		this.real = real;
	}

	public double getNotReal() {
		return notReal;
	}

	public void setNotReal(double notReal) {
		this.notReal = notReal;
	}

		
	
}
