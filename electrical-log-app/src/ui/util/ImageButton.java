package ui.util;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageButton extends JLabel implements MouseListener{
	private static final long serialVersionUID = -4580928625385283801L;

	/**
	 * Create the panel.
	 */
	public ImageButton(ImageIcon icon) {
		super(icon);
		setText(null);
		setOpaque(false);
		setSize(new Dimension(180, 125));
		setPreferredSize(new Dimension(180, 125));
		setMaximumSize(new Dimension(180, 125));
		setMinimumSize(new Dimension(180, 125));
		addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {;}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent arg0) {;}

	@Override
	public void mousePressed(MouseEvent arg0) {;}

	@Override
	public void mouseReleased(MouseEvent arg0) {;}

}
