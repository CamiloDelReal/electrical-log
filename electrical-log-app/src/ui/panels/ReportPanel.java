package ui.panels;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import ui.util.ExtendedFileFilter;
import electricRegistry.Neighborhood;
import electricRegistry.entities.House;

public class ReportPanel extends JPanel {
	private static final long serialVersionUID = 4912177089371210078L;
	private JButton btnHogaresConConsumo;
	private JButton btnHogaresConConsumo_1;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel label;

	/**
	 * Create the panel.
	 */
	public ReportPanel() {
		setOpaque(false);
		setSize(new Dimension(778, 412));
		setPreferredSize(new Dimension(778, 412));
		setMinimumSize(new Dimension(778, 412));
		setMaximumSize(new Dimension(778, 412));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		btnHogaresConConsumo = new JButton("Hogares con consumo alterado");
		btnHogaresConConsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				emptyTable();
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("Abrir un fichero de recibos");
				ExtendedFileFilter filter = new ExtendedFileFilter("dat", new String[]{"DAT"});
				chooser.addChoosableFileFilter(filter);
				chooser.showOpenDialog(null);
				File fileReceipt = chooser.getSelectedFile();
				if(fileReceipt != null){
					try {
						List<House> list = Neighborhood.getInstance().createFileWithHighConsumptions(fileReceipt.getAbsolutePath());
						//label.setText(getClass().getResource("/reportes/ConsumoAlterado.dat").getFile());
						fillTable(list);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(ReportPanel.this, "No se pudo realizar la escritura sobre el fichero", "Error", JOptionPane.ERROR_MESSAGE);
					} catch (ClassNotFoundException e) {
						JOptionPane.showMessageDialog(ReportPanel.this, "Existe corrupción en los datos de los ficheros de datos de la aplicación", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnHogaresConConsumo.setOpaque(false);
		springLayout.putConstraint(SpringLayout.NORTH, btnHogaresConConsumo, 39, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, btnHogaresConConsumo, 21, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, btnHogaresConConsumo, 235, SpringLayout.WEST, this);
		add(btnHogaresConConsumo);
		
		btnHogaresConConsumo_1 = new JButton("Hogares con consumo más alto");
		btnHogaresConConsumo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				emptyTable();
					try {
						House[] list = Neighborhood.getInstance().createHousesTextFile();
						fillTable(list);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(ReportPanel.this, "No se pudo realizar la escritura sobre el fichero", "Error", JOptionPane.ERROR_MESSAGE);
					} catch (ClassNotFoundException e) {
						JOptionPane.showMessageDialog(ReportPanel.this, "Existe corrupción en los datos de los ficheros de datos de la aplicación", "Error", JOptionPane.ERROR_MESSAGE);
					}
			}
		});
		btnHogaresConConsumo_1.setOpaque(false);
		springLayout.putConstraint(SpringLayout.NORTH, btnHogaresConConsumo_1, 6, SpringLayout.SOUTH, btnHogaresConConsumo);
		springLayout.putConstraint(SpringLayout.WEST, btnHogaresConConsumo_1, 21, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, btnHogaresConConsumo_1, 235, SpringLayout.WEST, this);
		add(btnHogaresConConsumo_1);
		
		scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 39, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 26, SpringLayout.EAST, btnHogaresConConsumo);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 341, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 514, SpringLayout.EAST, btnHogaresConConsumo);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"#", "Habitantes", "Direcci\u00F3n"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(66);
		table.getColumnModel().getColumn(2).setPreferredWidth(197);
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(table);
		
		label = new JLabel("");
		springLayout.putConstraint(SpringLayout.WEST, label, 130, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, label, -34, SpringLayout.SOUTH, this);
		add(label);

	}
	
	private void emptyTable(){
		while(table.getRowCount() > 0)
			((DefaultTableModel)table.getModel()).removeRow(0);
	}
	
	private void fillTable(House[] list){
		for(House h : list)
			((DefaultTableModel)table.getModel()).addRow(new Object[]{
				h.getNumber(),
				h.getPopulation(),
				h.getAddress()
			});
	}
	private void fillTable(List<House> list){
		for(House h : list)
			((DefaultTableModel)table.getModel()).addRow(new Object[]{
				h.getNumber(),
				h.getPopulation(),
				h.getAddress()
			});
	}
}
