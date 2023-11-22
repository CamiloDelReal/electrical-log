package ui.util;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;

public class ImageButton_2 extends JButton {
	private static final long serialVersionUID = -4171198257060283636L;

	/**
	 * Create the panel.
	 */
	public ImageButton_2() {
		setSize(new Dimension(16,16));
		setPreferredSize(new Dimension(16,16));
		setMaximumSize(new Dimension(16,16));
		setMinimumSize(new Dimension(16,16));
		setContentAreaFilled(false);
		setFocusable(false);
		setBorder(null);
		setBorderPainted(false);
		setUI(new BasicButtonUI());
	}

}
