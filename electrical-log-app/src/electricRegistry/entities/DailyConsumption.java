package electricRegistry.entities;

import java.io.Serializable;
import java.util.Calendar;

public class DailyConsumption implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int initial;
	private int finale;
	private Calendar date;
	
	public DailyConsumption(int initial, int finale, Calendar date) {
		this.initial = initial;
		this.finale = finale;
		this.date = date;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public int getFinale() {
		return finale;
	}

	public void setFinal(int finale) {
		this.finale = finale;
	}

	public int getInitial() {
		return initial;
	}

	public void setInitial(int initial) {
		this.initial = initial;
	}
	
	public int getDifference(){
		return finale - initial;
	}
	
}
