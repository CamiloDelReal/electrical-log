package ui.panels;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import ui.util.GraphicSupport;
import electricRegistry.Neighborhood;
import electricRegistry.entities.House;

public class HousesPanel extends JPanel {
	private static final long serialVersionUID = 3441598821754658214L;
	
	private House tmp;
	@SuppressWarnings("unused")
	private ConsumptionPanel consumptionPanel;
	@SuppressWarnings("unused")
	private MenuPanel menuPanel;
	
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel;
	private JButton btnNewButton;
	private JButton btnNewButton_2;
	private JButton btnVerConsumos;
	private JLabel lblFiltrarPorNumero;
	private JPanel panel_1;
	private JLabel lblNmero;
	private JLabel lblHabitantes;
	private JLabel lblDireccin;
	private JTextField textField_3;
	private JButton btnBorrar;
	private JButton btnGuardar;
	private Component verticalStrut;
	private Component verticalStrut_1;
	private JFormattedTextField formattedTextField;
	private JFormattedTextField formattedTextField_1;
	private JTextField textField;
	private JButton btnCancelar;
	private Component verticalStrut_2;

	/**
	 * Create the panel.
	 */
	public HousesPanel(final ConsumptionPanel consumptionPanel, final MenuPanel menuPanel) {
		this.consumptionPanel = consumptionPanel;
		this.menuPanel = menuPanel;
		setSize(new Dimension(778, 412));
		setPreferredSize(new Dimension(778, 412));
		setMinimumSize(new Dimension(778, 412));
		setMaximumSize(new Dimension(778, 412));
		setOpaque(false);
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 32, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 588, SpringLayout.WEST, this);
		add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("SansSerif", Font.PLAIN, 12));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setOpaque(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"", "#", "<html><body><p style=\"text-align:right\">Habitantes</p></body></html>", "Direcci\u00F3n"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -3000426297232992232L;
			@SuppressWarnings("unchecked")
			Class[] columnTypes = new Class[] {
				Boolean.class, Integer.class, Integer.class, String.class
			};
			@SuppressWarnings("unchecked")
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(16);
		table.getColumnModel().getColumn(0).setMinWidth(16);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(64);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(68);
		table.getColumnModel().getColumn(3).setPreferredWidth(335);
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(table);
		
		fillTable(null);
		
		
		panel = new JPanel();
		panel.setOpaque(false);
		springLayout.putConstraint(SpringLayout.WEST, panel, 20, SpringLayout.EAST, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, panel, -20, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 0, SpringLayout.NORTH, panel);
		springLayout.putConstraint(SpringLayout.NORTH, panel, 190, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -10, SpringLayout.SOUTH, this);
		add(panel);
		panel.setLayout(new GridLayout(7, 1, 0, 0));
		
		btnNewButton = new JButton("Modificar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean found = false;
				int row = 0;
				
				while(!found && row < table.getRowCount()){
					found = ((Boolean)table.getValueAt(row, 0)).booleanValue();
					if(!found)
						row++;
				}
				
				if(found){
					int number = ((Integer)table.getValueAt(row, 1)).intValue();
					tmp = Neighborhood.getInstance().findHouseByNumber(number);
					if(tmp != null){
						formattedTextField.setValue(new Integer(tmp.getNumber()));
						formattedTextField_1.setValue(new Integer(tmp.getPopulation()));
						textField_3.setText(tmp.getAddress());
						panel_1.setBorder(new TitledBorder(new LineBorder(new Color(171, 173, 179)), "Modificando hogar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
						btnCancelar.setEnabled(true);
					}
				}
				else
					JOptionPane.showMessageDialog(HousesPanel.this, "Debe seleccionar un hogar", "Alerta", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton.setVerticalTextPosition(SwingConstants.CENTER);
		btnNewButton.setVerticalAlignment(SwingConstants.CENTER);
		btnNewButton.setVisible(true);
		
		verticalStrut_2 = Box.createVerticalStrut(20);
		panel.add(verticalStrut_2);
		
		verticalStrut_1 = Box.createVerticalStrut(20);
		panel.add(verticalStrut_1);
		
		verticalStrut = Box.createVerticalStrut(20);
		panel.add(verticalStrut);
		btnNewButton.setVerifyInputWhenFocusTarget(true);
		btnNewButton.setRolloverEnabled(true);
		btnNewButton.setRequestFocusEnabled(true);
		btnNewButton.setOpaque(false);
		panel.add(btnNewButton);
		
		btnNewButton_2 = new JButton("Eliminar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = 0;
				boolean value = false;
				int rowDeleted = 0;
				int number = 0;
				House aux = null;
				
				//eliminar de la tabla las filas seleccionadas
				while(row < table.getRowCount()){
					value = ((Boolean)table.getValueAt(row, 0)).booleanValue();
					if(value){
						number = ((Integer)table.getValueAt(row, 1)).intValue();
						((DefaultTableModel)table.getModel()).removeRow(row);
						rowDeleted++;
						
						//eliminar de la lista de casas las seleccionadas
						aux = Neighborhood.getInstance().findHouseByNumber(number);
						Neighborhood.getInstance().getHousesList().remove(aux);
					}
					else
						row++;
				}
				
				if(rowDeleted == 0)
					JOptionPane.showMessageDialog(HousesPanel.this, "Debe seleccionar los hogares que desea eliminar", "Alerta", JOptionPane.INFORMATION_MESSAGE);
				else{
					emptyTable();
					fillTable(null);
				}
				
			}
		});
		btnNewButton_2.setOpaque(false);
		btnNewButton_2.setPreferredSize(new Dimension(63, 23));
		btnNewButton_2.setMinimumSize(new Dimension(63, 23));
		btnNewButton_2.setMaximumSize(new Dimension(63, 23));
		panel.add(btnNewButton_2);
		
		btnVerConsumos = new JButton("Ver consumos");
		btnVerConsumos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = 0;
				int number = 0;
				boolean value = false;
				House house = null;
				
				while(row < table.getRowCount() && !value){
					value = ((Boolean)table.getValueAt(row, 0)).booleanValue();
					
					if(value){
						number = ((Integer)table.getValueAt(row, 1)).intValue();
						house = Neighborhood.getInstance().findHouseByNumber(number);
					}
					
					row++;
				}
				
				if(value){
					consumptionPanel.emptyAll();
					consumptionPanel.recibeHouse(house);
					Container contentPane = HousesPanel.this.getParent();
					((CardLayout)contentPane.getLayout()).show(contentPane, "consumption");
					menuPanel.goConsumption();
				}
			}
		});
		btnVerConsumos.setOpaque(false);
		btnVerConsumos.setPreferredSize(new Dimension(63, 23));
		btnVerConsumos.setMinimumSize(new Dimension(63, 23));
		btnVerConsumos.setMaximumSize(new Dimension(63, 23));
		panel.add(btnVerConsumos);
		
		lblFiltrarPorNumero = new JLabel("Filtrar por número de casa");
		springLayout.putConstraint(SpringLayout.WEST, lblFiltrarPorNumero, 43, SpringLayout.WEST, this);
		add(lblFiltrarPorNumero);
		
		panel_1 = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, lblFiltrarPorNumero, 10, SpringLayout.SOUTH, panel_1);
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, 159, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, panel_1, 0, SpringLayout.EAST, scrollPane);
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 34, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 32, SpringLayout.WEST, this);
		panel_1.setOpaque(false);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(171, 173, 179)), "Nuevo hogar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_1);
		SpringLayout sl_panel_1 = new SpringLayout();
		panel_1.setLayout(sl_panel_1);
		
		lblNmero = new JLabel("Número");
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblNmero, 10, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblNmero, 15, SpringLayout.WEST, panel_1);
		panel_1.add(lblNmero);
		
		lblHabitantes = new JLabel("Habitantes");
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblHabitantes, 16, SpringLayout.SOUTH, lblNmero);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblHabitantes, 0, SpringLayout.WEST, lblNmero);
		panel_1.add(lblHabitantes);
		
		lblDireccin = new JLabel("Dirección");
		sl_panel_1.putConstraint(SpringLayout.WEST, lblDireccin, 141, SpringLayout.EAST, lblNmero);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, lblDireccin, 0, SpringLayout.SOUTH, lblNmero);
		panel_1.add(lblDireccin);
		
		textField_3 = new JTextField();
		sl_panel_1.putConstraint(SpringLayout.EAST, textField_3, -14, SpringLayout.EAST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, textField_3, 36, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, textField_3, 10, SpringLayout.WEST, lblDireccin);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, textField_3, 61, SpringLayout.NORTH, panel_1);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				emptyField();
			}
		});
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnBorrar, 6, SpringLayout.SOUTH, textField_3);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnBorrar, 452, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnBorrar, 0, SpringLayout.EAST, textField_3);
		btnBorrar.setOpaque(false);
		panel_1.add(btnBorrar);
		
		btnGuardar = new JButton("Guardar");
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnGuardar, 6, SpringLayout.SOUTH, textField_3);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnGuardar, 277, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnGuardar, -95, SpringLayout.WEST, btnBorrar);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(validateForm()){
					int number = ((Number)formattedTextField.getValue()).intValue();
					int population = ((Number)formattedTextField_1.getValue()).intValue();
					String address = textField_3.getText();
					
					if(tmp == null){
						House house = new House(number, population, address, 30);
						Neighborhood.getInstance().getHousesList().add(house);
						updateTable(house);
					}
					else{
						tmp.setNumber(number);
						tmp.setPopulation(population);
						tmp.setAddress(address);
						tmp = null;
						btnCancelar.setEnabled(false);
						emptyTable();
						fillTable(null);
						panel_1.setBorder(new TitledBorder(new LineBorder(new Color(171, 173, 179)), "Nuevo hogar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
						btnCancelar.setEnabled(false);
					}
					emptyField();
				}
			}
		});
		btnGuardar.setOpaque(false);
		panel_1.add(btnGuardar);
		
		NumberFormat integerFormat = NumberFormat.getIntegerInstance();
		formattedTextField = new JFormattedTextField(integerFormat);
		formattedTextField.setValue(new Integer(0));
		formattedTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		sl_panel_1.putConstraint(SpringLayout.NORTH, formattedTextField, 3, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, formattedTextField, 25, SpringLayout.EAST, lblNmero);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, formattedTextField, 28, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, formattedTextField, 100, SpringLayout.EAST, lblHabitantes);
		panel_1.add(formattedTextField);
		
		NumberFormat integerFormat_1 = NumberFormat.getIntegerInstance();
		formattedTextField_1 = new JFormattedTextField(integerFormat_1);
		formattedTextField_1.setValue(new Integer(0));
		formattedTextField_1.setHorizontalAlignment(SwingConstants.RIGHT);
		sl_panel_1.putConstraint(SpringLayout.NORTH, formattedTextField_1, 36, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, formattedTextField_1, 0, SpringLayout.WEST, formattedTextField);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, formattedTextField_1, 61, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, formattedTextField_1, 100, SpringLayout.EAST, lblHabitantes);
		panel_1.add(formattedTextField_1);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String text = textField.getText();
				emptyTable();
				if(text.isEmpty() || GraphicSupport.isEmpty(text)){
					fillTable(null);
				}
				else{
					List<House> list = Neighborhood.getInstance().findHouseCoincidence(text);
					fillTable(list);
				}
			}
		});
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		springLayout.putConstraint(SpringLayout.NORTH, textField, 4, SpringLayout.SOUTH, panel_1);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				emptyField();
				tmp = null;
				btnCancelar.setEnabled(false);
				panel_1.setBorder(new TitledBorder(new LineBorder(new Color(171, 173, 179)), "Nuevo hogar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			}
		});
		btnCancelar.setEnabled(false);
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnCancelar, 6, SpringLayout.SOUTH, textField_3);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnCancelar, 8, SpringLayout.EAST, btnGuardar);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnCancelar, -7, SpringLayout.WEST, btnBorrar);
		btnCancelar.setOpaque(false);
		panel_1.add(btnCancelar);
		springLayout.putConstraint(SpringLayout.WEST, textField, 16, SpringLayout.EAST, lblFiltrarPorNumero);
		springLayout.putConstraint(SpringLayout.SOUTH, textField, -3, SpringLayout.NORTH, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, textField, -443, SpringLayout.EAST, this);
		add(textField);
		textField.setColumns(10);


	}
	
	private boolean validateForm(){
		boolean done = true;
		String message = "";
		boolean jump = false;
		
		int value = ((Number)formattedTextField.getValue()).intValue();
		if(value < 0){
			value *= -1;
			formattedTextField.setValue(new Integer(value));
		}
		if(value == 0 ){
			message += jump ? "\nDebe de indicar el numero del hogar" : "Debe de indicar el numero del hogar";
			jump = true;
			done = false;
		}
		
		value = ((Number)formattedTextField_1.getValue()).intValue();
		if(value < 0){
			value *= -1;
			formattedTextField_1.setValue(new Integer(value));
		}
		if(value == 0 ){
			message += jump ? "\nDebe de indicar la cantidad de habitantes" : "Debe de indicar la cantidad de habitantes";
			jump = true;
			done = false;
		}
		
		String text = textField_3.getText();
		if(text == null || text.isEmpty() || GraphicSupport.isEmpty(text)){
			message += jump ? "\nDebe de indicar la dirección" : "Debe de indicar la dirección";
			done = false;
		}
		
		if(!message.isEmpty() && !GraphicSupport.isEmpty(message))
			JOptionPane.showMessageDialog(HousesPanel.this, message, "Error", JOptionPane.ERROR_MESSAGE);
		
		return done;
	}
	
	private void emptyField(){
		formattedTextField.setValue(new Integer(0));
		formattedTextField_1.setValue(new Integer(0));
		textField_3.setText("");
	}
	
	private void fillTable(List<House> list){
		if(list == null)
			for(House h : Neighborhood.getInstance().getHousesList())
				((DefaultTableModel) table.getModel()).addRow(new Object[]{
						false,
						h.getNumber(),
						h.getPopulation(),
						h.getAddress()
				});
		else
			for(House h : list)
				((DefaultTableModel) table.getModel()).addRow(new Object[]{
						false,
						h.getNumber(),
						h.getPopulation(),
						h.getAddress()
				});
	}
	
	private void emptyTable(){
		while(table.getRowCount() > 0)
			((DefaultTableModel) table.getModel()).removeRow(0);
	}
	
	private void updateTable(House h){
		if(h != null)
			((DefaultTableModel) table.getModel()).addRow(new Object[]{
					false,
					h.getNumber(),
					h.getPopulation(),
					h.getAddress()
			});
	}
	
	public void emptyAll(){
		emptyField();
		textField.setText("");
		emptyTable();
		fillTable(null);
		btnCancelar.setEnabled(false);
	}
}
