package ui.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXDatePicker;

import ui.util.GraphicSupport;
import electricRegistry.Neighborhood;
import electricRegistry.entities.DailyConsumption;
import electricRegistry.entities.House;

public class ConsumptionPanel extends JPanel {
	private static final long serialVersionUID = 3640104297962342782L;
	
	private DailyConsumption tmp = null;
	private JLabel lblFiltrar;
	private JLabel lblHogares;
	private JScrollPane scrollPane;
	private JList list;
	private JScrollPane scrollPane_1;
	private JPanel panel;
	private JButton btnNewButton;
	private JButton btnEliminar;
	private Component verticalStrut;
	private Component verticalStrut_1;
	private Component verticalStrut_2;
	private JPanel panel_4;
	private JLabel lblFecha;
	private JXDatePicker datePicker;
	private JButton button;
	private JButton button_1;
	private JLabel lblConsumo;
	private JTextField textField;
	private JFormattedTextField formattedTextField;
	private JLabel label;
	private JFormattedTextField formattedTextField_1;
	private JTable table;
	private JButton btnCancelar;
	private JLabel lblConsumoTotal;
	private JLabel label_1;
	private JLabel lblPagoTotal;
	private JLabel label_2;
	private Component verticalStrut_3;

	/**
	 * Create the panel.
	 */
	public ConsumptionPanel() {
		setOpaque(false);
		setSize(new Dimension(778, 412));
		setPreferredSize(new Dimension(778, 412));
		setMinimumSize(new Dimension(778, 412));
		setMaximumSize(new Dimension(778, 412));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		lblFiltrar = new JLabel("Filtrar");
		springLayout.putConstraint(SpringLayout.NORTH, lblFiltrar, 27, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblFiltrar, 20, SpringLayout.WEST, this);
		add(lblFiltrar);
		
		lblHogares = new JLabel("Hogares");
		springLayout.putConstraint(SpringLayout.NORTH, lblHogares, 41, SpringLayout.SOUTH, lblFiltrar);
		springLayout.putConstraint(SpringLayout.WEST, lblHogares, 20, SpringLayout.WEST, this);
		add(lblHogares);
		
		scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 6, SpringLayout.SOUTH, lblHogares);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 30, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 291, SpringLayout.SOUTH, lblHogares);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 120, SpringLayout.WEST, this);
		add(scrollPane);
		
		DefaultListModel modelList = new DefaultListModel();
		list = new JList(modelList);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				House house = (House) list.getSelectedValue();
				if(house != null){
					emptyTables();
					fillTables(house);
					fillLabelTotal(house);
				}
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setOpaque(false);
		scrollPane.setViewportView(list);
		
		fillList(null);
		
		scrollPane_1 = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane_1, 0, SpringLayout.NORTH, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane_1, 42, SpringLayout.EAST, scrollPane);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane_1, 0, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane_1, 482, SpringLayout.WEST, this);
		add(scrollPane_1);
		
		panel = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, panel, 122, SpringLayout.EAST, scrollPane_1);
		springLayout.putConstraint(SpringLayout.EAST, panel, -20, SpringLayout.EAST, this);
		panel.setOpaque(false);
		springLayout.putConstraint(SpringLayout.NORTH, panel, -214, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -25, SpringLayout.SOUTH, this);
		add(panel);
		panel.setLayout(new GridLayout(7, 1, 0, 0));
		
		verticalStrut_3 = Box.createVerticalStrut(20);
		panel.add(verticalStrut_3);
		
		verticalStrut_2 = Box.createVerticalStrut(20);
		panel.add(verticalStrut_2);
		
		verticalStrut_1 = Box.createVerticalStrut(20);
		panel.add(verticalStrut_1);
		
		verticalStrut = Box.createVerticalStrut(20);
		panel.add(verticalStrut);
		
		btnNewButton = new JButton("Modificar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean found = false;
				int row = 0;
				House house = (House)list.getSelectedValue();
				if(house == null){
					JOptionPane.showMessageDialog(ConsumptionPanel.this, "Debe seleccionar un hogar", "Alerta", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					
					while(!found && row < table.getRowCount()){
						found = ((Boolean)table.getValueAt(row, 0)).booleanValue();
						if(!found)
							row++;
					}
					
					if(found){
						tmp = house.getConsumptions().get(row);
						datePicker.setDate(tmp.getDate().getTime());
						formattedTextField.setValue(new Integer(tmp.getInitial()));
						formattedTextField_1.setValue(new Integer(tmp.getFinale()));
						panel_4.setBorder(new TitledBorder(new LineBorder(new Color(171, 173, 179)), "Modificando consumo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
						btnCancelar.setEnabled(true);
					}
					else{
						JOptionPane.showMessageDialog(ConsumptionPanel.this, "Debe seleccionar un registro de consumo", "Alerta", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnNewButton.setOpaque(false);
		panel.add(btnNewButton);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = 0;
				boolean value = false;
				int rowDeleted = 0;
				House house = (House)list.getSelectedValue();
				
				if(house != null){				
					while(row < table.getRowCount()){
						value = ((Boolean)table.getValueAt(row, 0)).booleanValue();
						if(value){
							house.getConsumptions().remove(row);
							((DefaultTableModel)table.getModel()).removeRow(row);
							rowDeleted++;
						}
						else
							row++;
					}
					
					if(rowDeleted == 0)
						JOptionPane.showMessageDialog(ConsumptionPanel.this, "Debe seleccionar los registros de consumo que desee eliminar", "Alerta", JOptionPane.INFORMATION_MESSAGE);
					else{
						emptyField();
						fillTables(null);
					}
				}
				else
					JOptionPane.showMessageDialog(ConsumptionPanel.this, "Debe seleccionar un hogar", "Alerta", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnEliminar.setOpaque(false);
		panel.add(btnEliminar);
		
		panel_4 = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_4, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, panel_4, 103, SpringLayout.EAST, lblHogares);
		springLayout.putConstraint(SpringLayout.SOUTH, panel_4, -5, SpringLayout.NORTH, scrollPane_1);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][]{
			},
			new String[] {
				"", "Fecha", "C. inicial", "C. final", "C. total"
			}
		) {
			private static final long serialVersionUID = -7951050859394694849L;
			@SuppressWarnings("unchecked")
			Class[] columnTypes = new Class[] {
				Boolean.class, String.class, Integer.class, Integer.class, Integer.class
			};
			@SuppressWarnings("unchecked")
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(16);
		table.getColumnModel().getColumn(0).setMinWidth(16);
		table.getColumnModel().getColumn(0).setMaxWidth(16);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setMinWidth(75);
		table.getColumnModel().getColumn(1).setMaxWidth(75);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(65);
		table.getColumnModel().getColumn(2).setMinWidth(65);
		table.getColumnModel().getColumn(2).setMaxWidth(65);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(65);
		table.getColumnModel().getColumn(3).setMinWidth(65);
		table.getColumnModel().getColumn(3).setMaxWidth(65);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setMinWidth(75);
		table.getColumnModel().getColumn(4).setMaxWidth(75);
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane_1.setViewportView(table);
		springLayout.putConstraint(SpringLayout.EAST, panel_4, -51, SpringLayout.EAST, this);
		panel_4.setBorder(new TitledBorder(new LineBorder(new Color(171, 173, 179)), "Nuevo consumo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setOpaque(false);
		add(panel_4);
		SpringLayout sl_panel_4 = new SpringLayout();
		panel_4.setLayout(sl_panel_4);
		
		lblFecha = new JLabel("Fecha");
		sl_panel_4.putConstraint(SpringLayout.NORTH, lblFecha, 10, SpringLayout.NORTH, panel_4);
		sl_panel_4.putConstraint(SpringLayout.WEST, lblFecha, 10, SpringLayout.WEST, panel_4);
		panel_4.add(lblFecha);
		
		datePicker = new JXDatePicker();
		datePicker.setDate(null);
		sl_panel_4.putConstraint(SpringLayout.NORTH, datePicker, 6, SpringLayout.NORTH, panel_4);
		sl_panel_4.putConstraint(SpringLayout.WEST, datePicker, 11, SpringLayout.EAST, lblFecha);
		sl_panel_4.putConstraint(SpringLayout.SOUTH, datePicker, 30, SpringLayout.NORTH, panel_4);
		sl_panel_4.putConstraint(SpringLayout.EAST, datePicker, 170, SpringLayout.EAST, lblFecha);
		panel_4.add(datePicker);
		
		button = new JButton("Borrar");
		sl_panel_4.putConstraint(SpringLayout.WEST, button, 448, SpringLayout.WEST, panel_4);
		sl_panel_4.putConstraint(SpringLayout.EAST, button, -10, SpringLayout.EAST, panel_4);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				emptyField();
			}
		});
		sl_panel_4.putConstraint(SpringLayout.NORTH, button, 34, SpringLayout.NORTH, panel_4);
		sl_panel_4.putConstraint(SpringLayout.SOUTH, button, -2, SpringLayout.SOUTH, panel_4);
		button.setOpaque(false);
		panel_4.add(button);
		
		button_1 = new JButton("Guardar");
		sl_panel_4.putConstraint(SpringLayout.NORTH, button_1, 0, SpringLayout.NORTH, button);
		sl_panel_4.putConstraint(SpringLayout.WEST, button_1, 259, SpringLayout.WEST, panel_4);
		sl_panel_4.putConstraint(SpringLayout.SOUTH, button_1, -2, SpringLayout.SOUTH, panel_4);
		sl_panel_4.putConstraint(SpringLayout.EAST, button_1, -98, SpringLayout.WEST, button);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(validateForm()){
					House house = (House) list.getSelectedValue();
					Date date = datePicker.getDate();
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);
					int initial = ((Number)formattedTextField.getValue()).intValue();
					int finale = ((Number)formattedTextField_1.getValue()).intValue();
					
					if(tmp == null){
						DailyConsumption dc = new DailyConsumption(initial, finale, cal);
						//house.getConsumptions().add(dc);
						house.addConsumption(dc);
						emptyTables();
						fillTables(house);
					}
					else{
						tmp.setDate(cal);
						tmp.setInitial(initial);
						tmp.setFinal(finale);
						tmp = null;
						emptyTables();
						fillTables(house);
						panel_4.setBorder(new TitledBorder(new LineBorder(new Color(171, 173, 179)), "Nuevo consumo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
						btnCancelar.setEnabled(false);
					}
					emptyLabelTotal();
					fillLabelTotal(house);
					emptyField();
				}
			}
		});
		button_1.setOpaque(false);
		panel_4.add(button_1);
		
		lblConsumo = new JLabel("Consumo inicial");
		sl_panel_4.putConstraint(SpringLayout.WEST, lblConsumo, 19, SpringLayout.EAST, datePicker);
		sl_panel_4.putConstraint(SpringLayout.SOUTH, lblConsumo, 0, SpringLayout.SOUTH, lblFecha);
		panel_4.add(lblConsumo);
		
		NumberFormat integerFormat = NumberFormat.getIntegerInstance();
		formattedTextField = new JFormattedTextField(integerFormat);
		sl_panel_4.putConstraint(SpringLayout.WEST, formattedTextField, 7, SpringLayout.EAST, lblConsumo);
		sl_panel_4.putConstraint(SpringLayout.EAST, formattedTextField, -167, SpringLayout.EAST, panel_4);
		formattedTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		formattedTextField.setValue(new Integer(0));
		sl_panel_4.putConstraint(SpringLayout.NORTH, formattedTextField, 4, SpringLayout.NORTH, panel_4);
		sl_panel_4.putConstraint(SpringLayout.SOUTH, formattedTextField, 28, SpringLayout.NORTH, panel_4);
		panel_4.add(formattedTextField);
		
		label = new JLabel("Consumo final");
		sl_panel_4.putConstraint(SpringLayout.NORTH, label, 0, SpringLayout.NORTH, lblFecha);
		sl_panel_4.putConstraint(SpringLayout.WEST, label, 7, SpringLayout.EAST, formattedTextField);
		panel_4.add(label);
		
		NumberFormat integerFormat_1 = NumberFormat.getIntegerInstance();
		formattedTextField_1 = new JFormattedTextField(integerFormat_1);
		sl_panel_4.putConstraint(SpringLayout.WEST, formattedTextField_1, 7, SpringLayout.EAST, label);
		sl_panel_4.putConstraint(SpringLayout.EAST, formattedTextField_1, -12, SpringLayout.EAST, panel_4);
		formattedTextField_1.setHorizontalAlignment(SwingConstants.RIGHT);
		formattedTextField_1.setValue(new Integer(0));
		sl_panel_4.putConstraint(SpringLayout.NORTH, formattedTextField_1, 4, SpringLayout.NORTH, panel_4);
		sl_panel_4.putConstraint(SpringLayout.SOUTH, formattedTextField_1, 28, SpringLayout.NORTH, panel_4);
		panel_4.add(formattedTextField_1);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				emptyField();
				tmp = null;
				btnCancelar.setEnabled(false);
				panel_4.setBorder(new TitledBorder(new LineBorder(new Color(171, 173, 179)), "Nuevo consumo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			}
		});
		btnCancelar.setEnabled(false);
		sl_panel_4.putConstraint(SpringLayout.NORTH, btnCancelar, 0, SpringLayout.NORTH, button);
		sl_panel_4.putConstraint(SpringLayout.WEST, btnCancelar, 6, SpringLayout.EAST, button_1);
		sl_panel_4.putConstraint(SpringLayout.SOUTH, btnCancelar, 0, SpringLayout.SOUTH, button_1);
		sl_panel_4.putConstraint(SpringLayout.EAST, btnCancelar, -6, SpringLayout.WEST, button);
		btnCancelar.setOpaque(false);
		panel_4.add(btnCancelar);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String text = textField.getText();
				((DefaultListModel)list.getModel()).clear();
				if(text.isEmpty() || GraphicSupport.isEmpty(text)){
					fillList(null);
				}
				else{
					List<House> list = Neighborhood.getInstance().findHouseCoincidence(text);
					fillList(list);
				}
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, textField, 6, SpringLayout.SOUTH, lblFiltrar);
		springLayout.putConstraint(SpringLayout.WEST, textField, 30, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, textField, 30, SpringLayout.SOUTH, lblFiltrar);
		springLayout.putConstraint(SpringLayout.EAST, textField, 120, SpringLayout.WEST, this);
		add(textField);
		textField.setColumns(10);
		
		lblConsumoTotal = new JLabel("Consumo total");
		springLayout.putConstraint(SpringLayout.NORTH, lblConsumoTotal, 20, SpringLayout.SOUTH, panel_4);
		springLayout.putConstraint(SpringLayout.WEST, lblConsumoTotal, 30, SpringLayout.EAST, scrollPane_1);
		add(lblConsumoTotal);
		
		label_1 = new JLabel("0");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		springLayout.putConstraint(SpringLayout.NORTH, label_1, 20, SpringLayout.SOUTH, panel_4);
		springLayout.putConstraint(SpringLayout.WEST, label_1, 12, SpringLayout.EAST, lblConsumoTotal);
		springLayout.putConstraint(SpringLayout.EAST, label_1, 70, SpringLayout.EAST, lblConsumoTotal);
		add(label_1);
		
		lblPagoTotal = new JLabel("Pago total");
		springLayout.putConstraint(SpringLayout.NORTH, lblPagoTotal, 6, SpringLayout.SOUTH, lblConsumoTotal);
		springLayout.putConstraint(SpringLayout.WEST, lblPagoTotal, 0, SpringLayout.WEST, lblConsumoTotal);
		add(lblPagoTotal);
		
		label_2 = new JLabel("0.0");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		springLayout.putConstraint(SpringLayout.NORTH, label_2, 6, SpringLayout.SOUTH, label_1);
		springLayout.putConstraint(SpringLayout.WEST, label_2, 0, SpringLayout.WEST, label_1);
		springLayout.putConstraint(SpringLayout.EAST, label_2, 70, SpringLayout.EAST, lblConsumoTotal);
		add(label_2);

	}
	
	private void fillList(List<House> listHouse){
		if(listHouse == null)
			for(House h : Neighborhood.getInstance().getHousesList())
				((DefaultListModel)list.getModel()).addElement(h);
		else
			for(House h : listHouse)
				((DefaultListModel)list.getModel()).addElement(h);
		
		list.setSelectedValue(null, true);
	}
	
	private boolean validateForm(){
		boolean done = true;
		String message = "";
		boolean jump = false;
		
		House house  = (House) list.getSelectedValue();
		if(house == null){
			done = false;
			message += jump ? "\nDebe de seleccionar el hogar" : "Debe de seleccionar el hogar";
			jump = true;
		}
		else if(house.getConsumptions().size() == 32){
			done = false;
			message += jump ? "\nEl hogar seleccionado tiene demasiados registros" : "El hogar seleccionado tiene demasiados registros";
			jump = true;
		}
		
		
		Date date = datePicker.getDate();
		if(date == null){
			done = false;
			message += jump ? "\nDebe de indicar la fecha en que se tomo el registro" : "Debe de indicar la fecha en que se tomo el registro";
			jump = true;
		}
		
		int value = ((Number)formattedTextField.getValue()).intValue();
		if(value < 0){
			value *= -1;
			formattedTextField.setValue(new Integer(value));
		}
		if(value == 0 ){
			message += jump ? "\nDebe de indicar el consumo inicial" : "Debe de indicar el consumo inicial";
			jump = true;
			done = false;
		}
		
		int value2 = ((Number)formattedTextField_1.getValue()).intValue();
		if(value2 < 0){
			value2 *= -1;
			formattedTextField_1.setValue(new Integer(value2));
		}
		if(value2 == 0 ){
			message += jump ? "\nDebe de indicar el consumo final" : "Debe de indicar el consumo final";
			jump = true;
			done = false;
		}
		
		if(value >= value2){
			message += jump ? "\nEl consumo final debe ser mayor al inicial" : "El consumo final debe ser mayor al inicial";
			jump = true;
			done = false;
		}
		
		if(!message.isEmpty() && !GraphicSupport.isEmpty(message))
			JOptionPane.showMessageDialog(ConsumptionPanel.this, message, "Error", JOptionPane.ERROR_MESSAGE);
		
		return done;
	}
	
	private void emptyTables(){
		while(table.getRowCount() > 0)
			((DefaultTableModel)table.getModel()).removeRow(0);
	}
	
	private void emptyLabelTotal(){
		label_1.setText("0");	//Label con el total de consumos;
		label_2.setText("0.0");	//Label con el total a pagar
	}
	
	private void fillTables(House house){
		if(house != null){
			List<DailyConsumption> dcList = house.getConsumptions();
			for(DailyConsumption dc : dcList)
				((DefaultTableModel)table.getModel()).addRow(new Object[]{
					false, GraphicSupport.getStringOfDate(dc.getDate()),
					dc.getInitial(), dc.getFinale(), dc.getDifference()
				});
		}
	}
	
	private void fillLabelTotal(House house){
		if(house != null){
			label_1.setText(String.valueOf(house.totalConsumption()));
			double value = house.valueOfConsumptions();
			double round = GraphicSupport.roundTwoDecimal(value);
			label_2.setText(String.valueOf(round));
		}
	}
	
	private void emptyField(){
		datePicker.setDate(null);
		formattedTextField.setValue(new Integer(0));
		formattedTextField_1.setValue(new Integer(0));
	}
	
	public void emptyAll(){
		emptyField();
		textField.setText("");
		emptyTables();
		emptyLabelTotal();
		((DefaultListModel)list.getModel()).clear();
		fillList(null);
		btnCancelar.setEnabled(false);
	}
	
	public void recibeHouse(House house){
		list.setSelectedValue(house, true);
		fillTables(house);
		fillLabelTotal(house);
	}
}
