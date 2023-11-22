package ui.util;


import java.awt.Container;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MoveComponent implements MouseListener, MouseMotionListener {
	private JComponent target;
	private Point startDrag;
	private Point startLocation;
	
	public MoveComponent(JComponent target){
		this.target = target;
	}
	
	private JFrame getParentFrame(Container target){
		JFrame frame;
		if(target instanceof JFrame)
			frame = (JFrame)target;
		else
			frame = getParentFrame(target.getParent());
		return frame;
	}
	
	private Point getCursorLocationOnScreen(MouseEvent e){
		Point cursorLocationOnTarget = e.getPoint();
		Point targetLocation = target.getLocationOnScreen();
		return new Point((int)(cursorLocationOnTarget.getX() + targetLocation.getX()),
						 (int)(cursorLocationOnTarget.getY() + targetLocation.getY()));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e)){
			target.setCursor(new Cursor(Cursor.MOVE_CURSOR));
			startDrag = getCursorLocationOnScreen(e);
			startLocation = getParentFrame(target).getLocation();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		target.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e)){
			Point currentLocation = getCursorLocationOnScreen(e);
			int xLocationToUpdate = (int) (currentLocation.getX() - startDrag.getX());
			//if(xLocationToUpdate < 0)
			//xLocationToUpdate *= -1;
			int yLocationToUpdate = (int) (currentLocation.getY() - startDrag.getY());
			//if(yLocationToUpdate < 0)
			//	yLocationToUpdate *= -1;
			JFrame frameToMove = getParentFrame(target);
			Point locationUpdated = new Point((int)(startLocation.getX() + xLocationToUpdate),
										  	(int)(startLocation.getY() + yLocationToUpdate));
			frameToMove.setLocation(locationUpdated);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}

}
