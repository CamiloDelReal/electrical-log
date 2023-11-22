package ui.util;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.util.Calendar;

public class GraphicSupport {
	
	public static void centerWindow(Window wnd){
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		wnd.setLocation((size.width - wnd.getWidth()) / 2, (size.height - wnd.getHeight()) / 2);
	}
	
	public static boolean isEmpty(String text){
		int i = 0;
		
		while(i < text.length() && text.charAt(i) == ' ')
			i++;
		
		return i == text.length() ? true : false;
	}
	
	public static String getStringOfDate(Calendar date){
		String cad = "";
		
		int day = date.get(Calendar.DAY_OF_MONTH);
		if(day < 10)
			cad += "0" + day + "/";
		else
			cad += day + "/";
		
		int month = (date.get(Calendar.MONTH) + 1);
		if(month < 10)
			cad += "0" + month +"/";
		else
			cad += month +"/";
		cad += date.get(Calendar.YEAR);
		return cad;
	}
	
	public static void toLower(String array[]) {
		for (int i=0, n=array.length; i<n; i++) {
			array[i] = array[i].toLowerCase();
		}
	}
	
	public static double roundTwoDecimal(double value){
		return Math.rint(value * 100) / 100;
	}
	
}
