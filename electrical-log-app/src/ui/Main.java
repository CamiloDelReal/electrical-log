package ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.lang.reflect.Method;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import ui.dialog.AboutDialog;
import ui.panels.ConsumptionPanel;
import ui.panels.HomePanel;
import ui.panels.HousesPanel;
import ui.panels.MenuPanel;
import ui.panels.ReportPanel;
import ui.util.ImageButton_2;
import ui.util.LabelSelectable;
import ui.util.MoveComponent;
import electricRegistry.Neighborhood;

public class Main {

	private JFrame frame;
	private JPanel topPanel;
	private JPanel centerPanel;
	private JPanel bottomPanel;
	private JLabel lblGg;
	private ImageButton_2 imageButton;
	private ImageButton_2 imageButton_1;
	private LabelSelectable labelSelectable;
	private JPopupMenu popup;
	private MenuPanel menuPanel;
	private HousesPanel housePanel;
	private ConsumptionPanel consumptionPanel;
	private ReportPanel reportPanel;
	private HomePanel homePanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//UIManager.setLookAndFeel(new SubstanceMistSilverLookAndFeel());
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					JFrame.setDefaultLookAndFeelDecorated(true);
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		cargarDatos();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 800, 522);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try{
			@SuppressWarnings("unchecked")
			Class classObtained = Class.forName("com.sun.awt.AWTUtilities");
			@SuppressWarnings("unchecked")
			Method methodObtained = classObtained.getMethod("setWindowOpaque", java.awt.Window.class, Boolean.TYPE);
			methodObtained.invoke(classObtained, this.frame, false);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(frame, "No se pudo personalizar el marco de la ventana", "Alerta", JOptionPane.ERROR_MESSAGE);
		}
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		
		topPanel = new JPanel(){
			private static final long serialVersionUID = 8357032354511871941L;
			
			public void paint(Graphics g){
				ImageIcon in = new ImageIcon("img/main_top.png");
				g.drawImage(in.getImage(), 0, 0, 778, 44, null);
				super.paintComponents(g);
			}
		};
		topPanel.setSize(new Dimension(778, 44));
		topPanel.setPreferredSize(new Dimension(778, 44));
		topPanel.setMinimumSize(new Dimension(778, 44));
		topPanel.setBackground(Color.PINK);
		topPanel.setMaximumSize(new Dimension(778, 44));
		MoveComponent mc = new MoveComponent(topPanel);
		topPanel.addMouseListener(mc);
		topPanel.addMouseMotionListener(mc);
		frame.getContentPane().add(topPanel);
		SpringLayout sl_topPanel = new SpringLayout();
		topPanel.setLayout(sl_topPanel);
		
		lblGg = new JLabel("Gestión de Consumos Eléctricos");
		sl_topPanel.putConstraint(SpringLayout.NORTH, lblGg, 12, SpringLayout.NORTH, topPanel);
		sl_topPanel.putConstraint(SpringLayout.WEST, lblGg, 20, SpringLayout.WEST, topPanel);
		lblGg.setFont(new Font("SansSerif", Font.PLAIN, 22));
		topPanel.add(lblGg);
		
		imageButton = new ImageButton_2();
		sl_topPanel.putConstraint(SpringLayout.NORTH, imageButton, 3, SpringLayout.NORTH, lblGg);
		sl_topPanel.putConstraint(SpringLayout.EAST, imageButton, -16, SpringLayout.EAST, topPanel);
		imageButton.setIcon(new ImageIcon("img\\Close_Normal.png"));
		imageButton.setRolloverIcon(new ImageIcon("img/Close_Move.png"));
		imageButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				guardarDatos();
				System.exit(0);
			}
		});
		topPanel.add(imageButton);
		
		imageButton_1 = new ImageButton_2();
		imageButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.this.frame.setExtendedState(JFrame.ICONIFIED);
			}
		});
		imageButton_1.setIcon(new ImageIcon("img\\Min_Normal.png"));
		imageButton_1.setRolloverIcon(new ImageIcon("img/Min_Move.png"));
		sl_topPanel.putConstraint(SpringLayout.SOUTH, imageButton_1, 0, SpringLayout.SOUTH, imageButton);
		sl_topPanel.putConstraint(SpringLayout.EAST, imageButton_1, -6, SpringLayout.WEST, imageButton);
		topPanel.add(imageButton_1);
		
		popup = new JPopupMenu();
		JMenuItem item = new JMenuItem("Guardar datos");
		item.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				guardarDatos();
				JOptionPane.showMessageDialog(Main.this.frame, "Datos guardados correctamente", "!!!!!", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		popup.add(item);
		popup.addSeparator();
		item = new JMenuItem("Acerca de...");
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AboutDialog ad = new AboutDialog();
				ad.setVisible(true);
			}
		});
		popup.add(item);
		
		labelSelectable = new LabelSelectable("Más", new ImageIcon("img/triangulo.png"), new ImageIcon("img/triangulo_hover.png"));
		labelSelectable.setHorizontalTextPosition(SwingConstants.LEFT);
		labelSelectable.setComponentPopupMenu(popup);
		sl_topPanel.putConstraint(SpringLayout.NORTH, labelSelectable, 0, SpringLayout.NORTH, imageButton);
		sl_topPanel.putConstraint(SpringLayout.EAST, labelSelectable, -24, SpringLayout.WEST, imageButton_1);
		labelSelectable.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				popup.show(labelSelectable, e.getX(), e.getY());
				popup.setVisible(true);
			}
		});
		topPanel.add(labelSelectable);
		
		centerPanel = new JPanel(new CardLayout()){
			private static final long serialVersionUID = -688998937131308754L;

			public void paint(Graphics g){
				ImageIcon in = new ImageIcon("img/common_center.png");
				g.drawImage(in.getImage(), 0, 0, 778, 412, null);
				super.paintComponents(g);
			}
		};
		centerPanel.setSize(new Dimension(778, 412));
		centerPanel.setPreferredSize(new Dimension(778, 412));
		centerPanel.setMinimumSize(new Dimension(778, 412));
		centerPanel.setMaximumSize(new Dimension(778, 412));
		centerPanel.setBackground(Color.ORANGE);
		homePanel = new HomePanel();
		centerPanel.add(homePanel, "home");
		homePanel.getButtonHouses().addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				((CardLayout)centerPanel.getLayout()).show(centerPanel, "house");
				housePanel.emptyAll();
				menuPanel.goHouse();
			}
		});
		homePanel.getButtonConsumptions().addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				((CardLayout)centerPanel.getLayout()).show(centerPanel, "consumption");
				consumptionPanel.emptyAll();
				menuPanel.goConsumption();
			}
		});
		homePanel.getButtonReports().addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				((CardLayout)centerPanel.getLayout()).show(centerPanel, "report");
				menuPanel.goReport();
			}
		});
		
		menuPanel = new MenuPanel();
		
		consumptionPanel = new ConsumptionPanel();
		centerPanel.add(consumptionPanel, "consumption");
		
		housePanel = new HousesPanel(consumptionPanel, menuPanel);
		centerPanel.add(housePanel, "house");		
		
		
		reportPanel = new ReportPanel();
		centerPanel.add(reportPanel, "report");
		
		frame.getContentPane().add(centerPanel);
		
		bottomPanel = new JPanel(){
			private static final long serialVersionUID = -1119664688761930738L;
			
			public void paint(Graphics g){
				ImageIcon in = new ImageIcon("img/main_bottom.png");
				g.drawImage(in.getImage(), 0, 0, 778, 42, null);
				super.paintComponents(g);
			}
		};
		bottomPanel.setSize(new Dimension(778, 42));
		bottomPanel.setPreferredSize(new Dimension(778, 42));
		bottomPanel.setMinimumSize(new Dimension(778, 42));
		bottomPanel.setMaximumSize(new Dimension(778, 42));
		bottomPanel.setBackground(new Color(100, 149, 237));
		frame.getContentPane().add(bottomPanel);
		SpringLayout sl_bottomPanel = new SpringLayout();
		bottomPanel.setLayout(sl_bottomPanel);
		
		
		sl_bottomPanel.putConstraint(SpringLayout.NORTH, menuPanel, 10, SpringLayout.NORTH, bottomPanel);
		sl_bottomPanel.putConstraint(SpringLayout.WEST, menuPanel, 20, SpringLayout.WEST, bottomPanel);
		menuPanel.getHome().addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				((CardLayout)centerPanel.getLayout()).show(centerPanel, "home");
				homePanel.updateLabels();
				menuPanel.goHome();
			}
		});
		menuPanel.getHouses().addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				((CardLayout)centerPanel.getLayout()).show(centerPanel, "house");
				housePanel.emptyAll();
				menuPanel.goHouse();
			}
		});
		menuPanel.getConsumptions().addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				((CardLayout)centerPanel.getLayout()).show(centerPanel, "consumption");
				consumptionPanel.emptyAll();
				menuPanel.goConsumption();
			}
		});
		menuPanel.getReports().addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				((CardLayout)centerPanel.getLayout()).show(centerPanel, "report");
				menuPanel.goReport();
			}
		});
		bottomPanel.add(menuPanel);
	}
	
	private void cargarDatos(){
		try {
			Neighborhood.getInstance().loadHouseData();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(Main.this.frame, "No se pudo leer correctamente el fichero \"house.dat\".\nLa aplicación se ejecutará sin datos inicializados referentes a los hogares", "Alerta", JOptionPane.INFORMATION_MESSAGE);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(Main.this.frame, "No se pudo leer correctamente el fichero \"house.dat\".\nEs posible un fallo de integridad en los datos", "Alerta", JOptionPane.INFORMATION_MESSAGE);
		}
		try {
			Neighborhood.getInstance().loadConsumptionData();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(Main.this.frame, "No se pudo leer correctamente el fichero \"consumptions.dat\".\nLa aplicación se ejecutará sin datos inicializados referentes a los consumos", "Alerta", JOptionPane.INFORMATION_MESSAGE);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(Main.this.frame, "No se pudo leer correctamente el fichero \"consumptions.dat\".\nEs posible un fallo de integridad en los datos", "Alerta", JOptionPane.INFORMATION_MESSAGE);
		}
		
		//JOptionPane.showMessageDialog(Main.this.frame, "Datos cargados correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void guardarDatos(){
		try {
			Neighborhood.getInstance().createHouseFile();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(Main.this.frame, "No pudieron ser salvados los datos de los hogares", "Error", JOptionPane.ERROR_MESSAGE);
		}
		try {
			Neighborhood.getInstance().createConsumptionsFile();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(Main.this.frame, "No pudieron ser salvados los consumos de los hogares", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		//JOptionPane.showMessageDialog(Main.this.frame, "Datos guardados correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
	}
}
