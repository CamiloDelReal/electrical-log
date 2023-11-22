package ui.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ui.util.BooleanWrapper;
import ui.util.DownMove;
import ui.util.ImageButton;
import ui.util.PaintedLabel;
import ui.util.TooltipDown;
import ui.util.TooltipUp;
import ui.util.UpMove;
import electricRegistry.Neighborhood;

public class HomePanel extends JPanel {
	private static final long serialVersionUID = -8816163710987061931L;
	private JPanel centerPanelTop;
	private JPanel centerPanelBottom;
	private PaintedLabel tipsHouse;
	private PaintedLabel tipsConsumption;
	private PaintedLabel tipsReport;
	private ImageButton buttonHouses;
	private ImageButton buttonConsumptions;
	private ImageButton buttonReports;
	private BooleanWrapper bool1, bool2, bool3;
	private UpMove upMove;
	private DownMove downMove;
	private TooltipUp tipUp;
	private TooltipDown tipDown;
	private JLabel lblHogares;
	private JLabel lblConsumos;
	private JLabel label;
	private JLabel labelDescription;
	@SuppressWarnings("unused")
	private JLabel labelLogotype;
	private JLabel labelTotalHogares;
	private JLabel labelTotalHogaresValue;
	private JLabel labeltotalConsumption;
	private JLabel labeltotalConsumptionValue;
	
	
	public ImageButton getButtonHouses() {
		return buttonHouses;
	}
	public ImageButton getButtonConsumptions() {
		return buttonConsumptions;
	}
	public ImageButton getButtonReports() {
		return buttonReports;
	}


	/**
	 * Create the panel.
	 */
	public HomePanel() {
		setOpaque(false);
		setSize(new Dimension(778, 412));
		setPreferredSize(new Dimension(778, 412));
		setMinimumSize(new Dimension(778, 412));
		setMaximumSize(new Dimension(778, 412));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		bool1 = new BooleanWrapper(true);
		bool2 = new BooleanWrapper(true);
		bool3 = new BooleanWrapper(true);
		
		centerPanelTop = new JPanel(){
			private static final long serialVersionUID = 4625648717363994030L;

			public void paint(Graphics g){
				ImageIcon in = new ImageIcon("img/home_center_1.png");
				g.drawImage(in.getImage(), 0, 0, 778, 285, null);
				super.paintComponents(g);
			}
		};
		centerPanelTop.setSize(new Dimension(778, 285));
		centerPanelTop.setPreferredSize(new Dimension(778, 285));
		centerPanelTop.setMinimumSize(new Dimension(778, 285));
		centerPanelTop.setMaximumSize(new Dimension(778, 285));
		centerPanelTop.setBackground(Color.MAGENTA);
		centerPanelTop.setLayout(null);
		add(centerPanelTop);
		
		tipsHouse = new PaintedLabel("Añada, modifique o elimine hogares del registro");
		tipsHouse.setBounds(44, 110, 190, 100);
		tipsConsumption = new PaintedLabel("Tenga completo acceso a los consumos registrados en cada hogar");
		tipsConsumption.setBounds(294, 110, 190, 100);
		tipsReport = new PaintedLabel("Genere informes a partir de los reportes más comunes");
		tipsReport.setBounds(544, 110, 190, 100);
		
		
		
		buttonHouses = new ImageButton(new ImageIcon("img/button_houses.png"));
		buttonHouses.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				bool1.setTrue();
				upMove = new UpMove(buttonHouses, bool1);
				tipUp = new TooltipUp(tipsHouse, bool1);
				tipUp.execute();
				upMove.execute();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				bool1.setFalse();
				downMove = new DownMove(buttonHouses, bool1);
				tipDown = new TooltipDown(tipsHouse, bool1);
				tipDown.execute();
				downMove.execute();
			}
		});
		buttonHouses.setText("");
		buttonHouses.setBounds(49, 115, 180, 125);
		centerPanelTop.add(buttonHouses);
		
		buttonConsumptions = new ImageButton(new ImageIcon("img/button_consumptions.png"));
		buttonConsumptions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				bool2.setTrue();
				upMove = new UpMove(buttonConsumptions, bool2);
				tipUp = new TooltipUp(tipsConsumption, bool2);
				tipUp.execute();
				upMove.execute();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				bool2.setFalse();
				downMove = new DownMove(buttonConsumptions, bool2);
				tipDown = new TooltipDown(tipsConsumption, bool2);
				tipDown.execute();
				downMove.execute();
			}
		});
		buttonConsumptions.setText("");
		buttonConsumptions.setBounds(299, 115, 180, 125);
		centerPanelTop.add(buttonConsumptions);
		
		buttonReports = new ImageButton(new ImageIcon("img/button_reports.png"));
		buttonReports.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				bool3.setTrue();
				upMove = new UpMove(buttonReports, bool3);
				tipUp = new TooltipUp(tipsReport, bool3);
				tipUp.execute();
				upMove.execute();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				bool3.setFalse();
				downMove = new DownMove(buttonReports, bool3);
				tipDown = new TooltipDown(tipsReport, bool3);
				tipDown.execute();
				downMove.execute();
			}
		});
		buttonReports.setText("");
		buttonReports.setBounds(549, 115, 180, 125);
		centerPanelTop.add(buttonReports);
		
		centerPanelTop.add(tipsHouse);
		centerPanelTop.add(tipsConsumption);
		centerPanelTop.add(tipsReport);
		
		lblHogares = new JLabel("<html>\r\n<body style=\"color:#ffffff;\ttext-align:center;font-family:Arial, Helvetica, sans-serif;font-size:22px;font-weight:lighter;\">\r\nHogares\r\n</body>\r\n</html>");
		lblHogares.setHorizontalTextPosition(SwingConstants.CENTER);
		lblHogares.setHorizontalAlignment(SwingConstants.CENTER);
		lblHogares.setVerticalTextPosition(SwingConstants.TOP);
		lblHogares.setVerticalAlignment(SwingConstants.TOP);
		lblHogares.setBounds(49, 240, 180, 40);
		centerPanelTop.add(lblHogares);
		
		lblConsumos = new JLabel("<html>\r\n<body style=\"color:#ffffff;\ttext-align:center;font-family:Arial, Helvetica, sans-serif;font-size:22px;font-weight:lighter;\">\r\nConsumos\r\n</body>\r\n</html>");
		lblConsumos.setVerticalTextPosition(SwingConstants.TOP);
		lblConsumos.setVerticalAlignment(SwingConstants.TOP);
		lblConsumos.setHorizontalTextPosition(SwingConstants.CENTER);
		lblConsumos.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsumos.setBounds(299, 240, 180, 40);
		centerPanelTop.add(lblConsumos);
		
		label = new JLabel("<html>\r\n<body style=\"color:#ffffff;\ttext-align:center;font-family:Arial, Helvetica, sans-serif;font-size:22px;font-weight:lighter;\">\r\nReportes\r\n</body>\r\n</html>");
		label.setVerticalTextPosition(SwingConstants.TOP);
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(549, 240, 180, 40);
		centerPanelTop.add(label);
		
		
		centerPanelBottom = new JPanel(){
			private static final long serialVersionUID = 4625648717363994030L;

			public void paint(Graphics g){
				ImageIcon in = new ImageIcon("img/home_center_2.png");
				g.drawImage(in.getImage(), 0, 0, 778, 127, null);
				super.paintComponents(g);
			}
		};
		centerPanelBottom.setBackground(Color.CYAN);
		centerPanelBottom.setSize(new Dimension(778, 127));
		centerPanelBottom.setPreferredSize(new Dimension(778, 127));
		centerPanelBottom.setMinimumSize(new Dimension(778, 127));
		centerPanelBottom.setMaximumSize(new Dimension(778, 127));
		centerPanelBottom.setLayout(null);
		
		labelDescription = new JLabel("<html><body><p stype=\"font:15px Calibri;\">Aplicación dedicada al control<br> de los consumos eléctricos</p></body></html>");
		labelDescription.setBounds(70, 35, 300, 34);
		
		/*labelLogotype = new JLabel(new ImageIcon("img/logotype.png"));
		labelLogotype.setBounds(10, 95, 32, 32);*/
		
		labelTotalHogares = new JLabel("Total de hogares registrados");
		labelTotalHogares.setBounds(350, 25, 165, 17);
		labelTotalHogaresValue = new JLabel();
		labelTotalHogaresValue.setHorizontalAlignment(JLabel.RIGHT);
		labelTotalHogaresValue.setBounds(555, 25, 65, 17);
		
		labeltotalConsumption = new JLabel("Consumo total del vecindario");
		labeltotalConsumption.setBounds(350, 45, 165, 17);
		labeltotalConsumptionValue = new JLabel();
		labeltotalConsumptionValue.setHorizontalAlignment(JLabel.RIGHT);
		labeltotalConsumptionValue.setBounds(555, 45, 65, 17);
		
		updateLabels();
		
		//centerPanelBottom.add(labelLogotype);
		centerPanelBottom.add(labelDescription);
		centerPanelBottom.add(labelTotalHogares);
		centerPanelBottom.add(labelTotalHogaresValue);
		centerPanelBottom.add(labeltotalConsumption);
		centerPanelBottom.add(labeltotalConsumptionValue);
		add(centerPanelBottom);

	}
	
	public void updateLabels(){
		labelTotalHogaresValue.setText(String.valueOf(Neighborhood.getInstance().getHousesList().size()));
		labeltotalConsumptionValue.setText(String.valueOf(Neighborhood.getInstance().totalConsumption()));
	}
}
