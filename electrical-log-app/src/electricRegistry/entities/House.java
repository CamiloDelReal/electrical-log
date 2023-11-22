package electricRegistry.entities;

import java.util.ArrayList;
import java.util.List;

import ui.util.GraphicSupport;

import electricRegistry.util.HouseWithoutConsumptions;

public class House extends HouseWithoutConsumptions implements Comparable<House>{
	private static final long serialVersionUID = 6750835390406855670L;

	private static boolean sortByPopulation = true;

	private List<DailyConsumption> consumptions;
	
	public House(int number, int population, String address, int numberOfDays){
		super(number, population, address);
		
		if(numberOfDays > 0 && numberOfDays < 32)
			consumptions = new ArrayList<DailyConsumption>(numberOfDays);
		else
			consumptions = new ArrayList<DailyConsumption>(30);
	}
	
	public House(HouseWithoutConsumptions house){
		super(house.getNumber(), house.getPopulation(), house.getAddress());
		
		consumptions = new ArrayList<DailyConsumption>(30);
	}

	public void addConsumption(DailyConsumption dc){
		if(consumptions.isEmpty())
			consumptions.add(dc);
		else{
			int index = 0;
			while(index < consumptions.size() && GraphicSupport.getStringOfDate(dc.getDate()).compareTo(GraphicSupport.getStringOfDate(consumptions.get(index).getDate())) > 0)
				index++;
			//if(index < consumptions.size())
				consumptions.add(index, dc);
		}			
	}

	public List<DailyConsumption> getConsumptions() {
		return consumptions;
	}

	public void setConsumptions(List<DailyConsumption> consumptions) {
		this.consumptions = consumptions;
	}
	
	public int totalConsumption(){
		int total = 0;
		
		for(DailyConsumption dc: consumptions)
			total += (dc.getFinale() - dc.getInitial());
		
		return total;
	}

	
	public double valueOfConsumptions(){
		return totalConsumption() * 0.15;
	}
	
	public static void sortByNumber(){
		sortByPopulation = false;
	}
	public static void sortBypopulation(){
		sortByPopulation = true;
	}

	public int compareTo(House other) {
		int comparation = 0;
		
		if(sortByPopulation){
			if(population < other.getPopulation())
				comparation = 1;
			else if(population > other.getPopulation())
				comparation = -1;
		}
		else{
			if(number < other.getNumber())
				comparation = -1;
			else if(number > other.getNumber())
				comparation = 1;
		}
		return comparation;
	}
	
	public String toString(){
		return String.valueOf(number);
	}
}
