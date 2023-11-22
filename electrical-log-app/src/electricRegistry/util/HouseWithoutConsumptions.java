package electricRegistry.util;

import java.io.Serializable;

public class HouseWithoutConsumptions implements Serializable{
	private static final long serialVersionUID = -5564739706626305741L;
	
	protected int number;
	protected int population;
	protected String address;
	
	public HouseWithoutConsumptions(int number, int population, String address) {
		this.number = number;
		this.population = population;
		this.address = address;
	}
	
	public HouseWithoutConsumptions(HouseWithoutConsumptions house){
		this.number = house.getNumber();
		this.population = house.getPopulation();
		this.address = house.getAddress();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}
	
	public String toString(){
		return String.valueOf(number);
	}

}
