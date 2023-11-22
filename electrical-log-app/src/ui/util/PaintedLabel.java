package ui.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PaintedLabel extends JLabel {
	private static final long serialVersionUID = 2770770946848973519L;
	private float transparency;
	private static final String initialText = "<html><body style=\"font:Calibri 13px;padding:10px 16px 10px 16px;text-align:center;\">";
	private static final String enddingText = "</body></html>";
	
	public PaintedLabel(String text) {
		setText(initialText+text+enddingText);
		setOpaque(false);
		setBackground(Color.BLUE);
		setSize(new Dimension(180, 125));
		setPreferredSize(new Dimension(180, 125));
		setMaximumSize(new Dimension(180, 125));
		setMinimumSize(new Dimension(180, 125));
		setVerticalAlignment(JLabel.TOP);
		setVerticalTextPosition(JLabel.TOP);
		
		transparency = 0.0f;
	}
	
	public void setTransparency(float transparency){
		this.transparency = transparency;
	}
	public float getTransparency(){
		return transparency;
	}
	
	public void paint(Graphics g){
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		AlphaComposite old = (AlphaComposite) g2.getComposite();
		if(transparency > 1.0f)
			transparency = 1.0f;
		else if(transparency < 0.0f)
			transparency = 0.0f;
		g2.setComposite(AlphaComposite.SrcOver.derive(transparency));
		
		ImageIcon in = new ImageIcon("img/tooltips.png");
		g.drawImage(in.getImage(), 0, 0, 190, 100, null);
		
		super.paintComponent(g);
		g2.setComposite(old);
	}

}
