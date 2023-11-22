package ui.util;
import java.util.List;

import javax.swing.SwingWorker;



public class TooltipUp extends SwingWorker<Object, Integer> {
	private PaintedLabel label;
	private int top;
	private float trans;
	private static final int LIMIT = 5;
	private static final float TRANSPARENCY = 0.1f;
	private BooleanWrapper bool;
	
	public TooltipUp(PaintedLabel label, BooleanWrapper bool){
		this.label = label;
		this.bool = bool;
		top = label.getLocation().y;
		trans = label.getTransparency();
	}
	@Override
	protected Object doInBackground() throws Exception {
		while(bool.isValue()){
			if(top >= LIMIT && !isCancelled()){
				top--;
				if(top % 10 == 0)
					trans += TRANSPARENCY;
				publish(new Integer(top));
			}
			Thread.sleep(1);
		}
		return null;
	}
	
	@Override
	protected void process(List<Integer> list) {
		if(!isCancelled()){
			int value = list.get(list.size() - 1);
			label.setTransparency(trans);
			label.setLocation(label.getX(), value);
		}
	}

}
