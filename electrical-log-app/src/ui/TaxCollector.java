package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import taxcollector.Receipt;
import taxcollector.TaxCollectorBussines;
import taxcollector.exception.NotDeclaredNeighborhood;
import ui.util.ExtendedFileFilter;
import electricRegistry.util.HouseWithoutConsumptions;

public class TaxCollector {

	private Receipt receipt = null;
	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu mnTareas;
	private JMenuItem mntmCargarVecindario;
	private JMenuItem mntmGenerarRecibos;
	private JSeparator separator;
	private JMenuItem mntmSalir;
	private JScrollPane scrollPane;
	private JLabel lblHogares;
	private JList list;
	private JScrollPane scrollPane_1;
	private JTable table;
	private JPanel panel;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JFormattedTextField formattedTextField;
	private JButton button;
	private JMenuItem mntmCargarRecibo;
	private JSeparator separator_1;
	private JButton btnEliminar;
	private JButton btnModificar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					TaxCollector window = new TaxCollector();
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
	public TaxCollector() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 644, 300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnTareas = new JMenu("Tareas");
		menuBar.add(mnTareas);
		
		mntmCargarVecindario = new JMenuItem("Cargar vecindario");
		mntmCargarVecindario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				ExtendedFileFilter filter = new ExtendedFileFilter("dat", new String[]{"DAT"});
				chooser.addChoosableFileFilter(filter);
				chooser.showOpenDialog(null);
				if(chooser.getSelectedFile() != null){
					try {
						TaxCollectorBussines.getInstance().loadDataHouses(chooser.getSelectedFile());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(TaxCollector.this.frame, "No se pudo leer el fichero", "Error", JOptionPane.ERROR_MESSAGE);
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(TaxCollector.this.frame, "Existen incoherencias de datos en el fichero", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					fillList();
				}
			}
		});
		mnTareas.add(mntmCargarVecindario);
		
		mntmGenerarRecibos = new JMenuItem("Generar recibo");
		mntmGenerarRecibos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					TaxCollectorBussines.getInstance().getReceipts();//para comprobar que hay datos
					
					JFileChooser chooser = new JFileChooser();
					ExtendedFileFilter filter = new ExtendedFileFilter("dat", new String[]{"DAT"});
					chooser.addChoosableFileFilter(filter);
					chooser.showSaveDialog(null);

					if(chooser.getSelectedFile() != null){
						try {
							TaxCollectorBussines.getInstance().generateReceipts(chooser.getSelectedFile());
						} catch (IOException e) {
							JOptionPane.showMessageDialog(TaxCollector.this.frame, "No se pudo guardar el fichero", "Error", JOptionPane.ERROR_MESSAGE);
						} catch (NotDeclaredNeighborhood e) {
							JOptionPane.showMessageDialog(TaxCollector.this.frame, "Debe cargar los datos de un vecindario", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (NotDeclaredNeighborhood e1) {
					JOptionPane.showMessageDialog(TaxCollector.this.frame, "Debe cargar los datos de un vecindario", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		separator_1 = new JSeparator();
		mnTareas.add(separator_1);
		
		mntmCargarRecibo = new JMenuItem("Cargar recibo");
		mntmCargarRecibo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				ExtendedFileFilter filter = new ExtendedFileFilter("dat", new String[]{"DAT"});
				chooser.addChoosableFileFilter(filter);
				chooser.showOpenDialog(null);
				if(chooser.getSelectedFile() != null){
					try {
						TaxCollectorBussines.getInstance().loadReceipt(chooser.getSelectedFile());
						emptyTable();
						fillTable();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(TaxCollector.this.frame, "No se pudo leer el fichero", "Error", JOptionPane.ERROR_MESSAGE);
					} catch (NotDeclaredNeighborhood e) {
						JOptionPane.showMessageDialog(TaxCollector.this.frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					} catch (ClassNotFoundException e) {
						JOptionPane.showMessageDialog(TaxCollector.this.frame, "El fichero contiene datos corruptos", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		mnTareas.add(mntmCargarRecibo);
		mnTareas.add(mntmGenerarRecibos);
		
		separator = new JSeparator();
		mnTareas.add(separator);
		
		mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnTareas.add(mntmSalir);
		frame.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 90, 201);
		scrollPane.setOpaque(false);
		frame.getContentPane().add(scrollPane);
		
		lblHogares = new JLabel("Hogares");
		lblHogares.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblHogares);
		
		list = new JList(new DefaultListModel());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				HouseWithoutConsumptions hw = (HouseWithoutConsumptions)list.getSelectedValue();
				if(hw != null){
					label_1.setText(String.valueOf(hw.getNumber()));
				}
			}
		});
		scrollPane.setViewportView(list);
		
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setOpaque(false);
		scrollPane_1.setBounds(299, 14, 197, 201);
		frame.getContentPane().add(scrollPane_1);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"", "Hogar", "Consumo"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -5236003672400733830L;
			@SuppressWarnings("unchecked")
			Class[] columnTypes = new Class[] {
				Boolean.class, Integer.class, Double.class
			};
			@SuppressWarnings("unchecked")
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(16);
		table.getColumnModel().getColumn(1).setPreferredWidth(78);
		scrollPane_1.setViewportView(table);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EmptyBorder(0, 0, 0, 0), "Nuevo recibo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(110, 83, 179, 132);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		label = new JLabel("Hogar");
		label.setBounds(10, 25, 45, 14);
		panel.add(label);
		
		label_1 = new JLabel("0");
		label_1.setBounds(65, 25, 16, 14);
		panel.add(label_1);
		
		label_2 = new JLabel("Pago");
		label_2.setBounds(10, 61, 45, 14);
		panel.add(label_2);
		
		NumberFormat decimalFormat = NumberFormat.getNumberInstance();
		formattedTextField = new JFormattedTextField(decimalFormat);
		formattedTextField.setValue(new Double(0.0));
		formattedTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		formattedTextField.setBounds(65, 54, 83, 29);
		panel.add(formattedTextField);
		
		button = new JButton("Guardar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(receipt == null){
					HouseWithoutConsumptions hg = (HouseWithoutConsumptions)list.getSelectedValue();
					if(hg != null){
						double value = ((Number)formattedTextField.getValue()).doubleValue();
						Receipt rc = new Receipt(hg.getNumber(), value);
						try {
							TaxCollectorBussines.getInstance().getReceipts().add(rc);
						} catch (NotDeclaredNeighborhood e) {
							JOptionPane.showMessageDialog(TaxCollector.this.frame, "Debe cargar un vecindario", "Alerta", JOptionPane.INFORMATION_MESSAGE);
						}
						label_1.setText("");
						formattedTextField.setValue(new Double(0.0));
						emptyTable();
						fillTable();
					}
					else
						JOptionPane.showMessageDialog(TaxCollector.this.frame, "Debe seleccionar un hogar", "Alerta", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					double payment = ((Number)formattedTextField.getValue()).doubleValue();
					receipt.setPayment(payment);
					emptyTable();
					fillTable();
					label_1.setText("");
					formattedTextField.setValue(new Double(0.0));
					panel.setBorder(new TitledBorder(new EmptyBorder(0, 0, 0, 0), "Nuevo recibo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				}
			}
		});
		button.setBounds(10, 95, 118, 26);
		panel.add(button);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = 0;
				boolean value = false;
				int rowDeleted = 0;
				
				while(row < table.getRowCount()){
					value = ((Boolean)table.getValueAt(row, 0)).booleanValue();
					if(value){
						int number = ((Integer)table.getValueAt(row, 1)).intValue();
						((DefaultTableModel)table.getModel()).removeRow(row);
						rowDeleted++;
						try {
							TaxCollectorBussines.getInstance().deleteReceipt(number);
						} catch (NotDeclaredNeighborhood e) {
							JOptionPane.showMessageDialog(TaxCollector.this.frame, e.getMessage(), "Alerta", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else
						row++;
				}
			}
		});
		btnEliminar.setBounds(506, 175, 89, 23);
		frame.getContentPane().add(btnEliminar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = 0;
				boolean value = false;
				
				while(row < table.getRowCount() && !value){
					value = ((Boolean)table.getValueAt(row, 0)).booleanValue();
					if(value){
						int number = ((Integer)table.getValueAt(row, 1)).intValue();
						receipt = TaxCollectorBussines.getInstance().findReceipt(number);
						if(receipt != null){
							label_1.setText(String.valueOf(number));
							double payment =  ((Double)table.getValueAt(row, 2)).doubleValue();
							formattedTextField.setValue(new Double(payment));
							panel.setBorder(new TitledBorder(new EmptyBorder(0, 0, 0, 0), "Modificando recibo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
						}
					}
					
					row++;
				}
			}
		});
		btnModificar.setBounds(506, 141, 89, 23);
		frame.getContentPane().add(btnModificar);
	}
	
	private void fillList(){
		try {
			List<HouseWithoutConsumptions> listHouse = TaxCollectorBussines.getInstance().getHouses();
			if(listHouse != null)
				for(HouseWithoutConsumptions h : listHouse)
					((DefaultListModel) list.getModel()).addElement(h);
		} catch (NotDeclaredNeighborhood e) {
			JOptionPane.showMessageDialog(TaxCollector.this.frame, "No ha sido cargado un vecindario", "Alerta", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private void emptyTable(){
		while(table.getRowCount() > 0)
			((DefaultTableModel)table.getModel()).removeRow(0);
	}
	
	private void fillTable(){
		try {
			for(Receipt rc : TaxCollectorBussines.getInstance().getReceipts())
				((DefaultTableModel)table.getModel()).addRow(new Object[]{
						false, rc.getHouseNumber(), rc.getPayment()
				});
		} catch (NotDeclaredNeighborhood e) {
			JOptionPane.showMessageDialog(TaxCollector.this.frame, "Debe cargar un vecindario", "Alerta", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
