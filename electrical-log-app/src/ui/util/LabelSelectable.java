package ui.util;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JLabel;

public class LabelSelectable extends JLabel implements MouseListener{
	private static final long serialVersionUID = 8102442537383794682L;
	private static final String initialText = "<html><body style=\"text-decoration:underline; color:#57ace2;\">";
	private static final String enddingText = "</body></html>";
	private String textPure;
	private Icon normal;
	private Icon hover;

	/**
	 * Create the panel.
	 */
	public LabelSelectable(String text, Icon normal, Icon hover) {
		super(text);
		textPure = text;
		setIcon(normal);
		this.normal = normal;
		this.hover = hover;
		setFont(new Font("SansSerif", Font.PLAIN, 12));
		addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {;}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		setText(initialText+textPure+enddingText);
		setIcon(hover);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		setText(textPure);
		setIcon(normal);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {;}

	@Override
	public void mouseReleased(MouseEvent arg0) {;}

}
