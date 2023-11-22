package ui.util;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingWorker;


public class UpMove extends SwingWorker<Object, Integer> {
	private JLabel label;
	private int top;
	private static final int LIMIT = 100;
	private BooleanWrapper bool;
	
	public UpMove(JLabel label, BooleanWrapper bool){
		this.label = label;
		this.bool = bool;
		top = label.getLocation().y;
	}
	@Override
	protected Object doInBackground() throws Exception {
		while(bool.isValue()){
			if(top >= LIMIT && !isCancelled()){
				top--;
				publish(new Integer(top));
			}
			Thread.sleep(5);
		}
		return null;
	}
	
	@Override
	protected void process(List<Integer> list) {
		if(!isCancelled()){
			int value = list.get(list.size() - 1);
			label.setLocation(label.getX(), value);
		}
	}

}
