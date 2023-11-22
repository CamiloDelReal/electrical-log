package ui.panels;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Icon;
import javax.swing.JPanel;

import ui.util.LabelSelectable;

public class MenuPanel extends JPanel {
	private static final long serialVersionUID = 5922853901645643231L;
	private LabelSelectable lblslctblInicio;
	private LabelSelectable lblslctblHogares;
	private LabelSelectable lblslctblConsumos;
	private LabelSelectable lblslctblReportes;

	/**
	 * Create the panel.
	 */
	public MenuPanel() {
		setSize(new Dimension(230, 20));
		setPreferredSize(new Dimension(230, 20));
		setMinimumSize(new Dimension(230, 20));
		setMaximumSize(new Dimension(230, 20));
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 2));
		setOpaque(false);
		setVisible(false);
		
		lblslctblInicio = new LabelSelectable("Inicio", (Icon) null, (Icon) null);
		add(lblslctblInicio);
		
		lblslctblHogares = new LabelSelectable("Hogares", (Icon) null, (Icon) null);
		add(lblslctblHogares);
		
		lblslctblConsumos = new LabelSelectable("Consumos", (Icon) null, (Icon) null);
		add(lblslctblConsumos);
		
		lblslctblReportes = new LabelSelectable("Reportes", (Icon) null, (Icon) null);
		add(lblslctblReportes);

	}
	
	public LabelSelectable getHome() {
		return lblslctblInicio;
	}
	public LabelSelectable getHouses() {
		return lblslctblHogares;
	}
	public LabelSelectable getConsumptions() {
		return lblslctblConsumos;
	}
	public LabelSelectable getReports() {
		return lblslctblReportes;
	}

	public void goHome(){
		lblslctblHogares.setVisible(true);
		lblslctblConsumos.setVisible(true);
		lblslctblReportes.setVisible(true);
		this.setVisible(false);
	}
	
	public void goHouse(){
		lblslctblHogares.setVisible(false);
		lblslctblConsumos.setVisible(true);
		lblslctblReportes.setVisible(true);
		this.setVisible(true);
	}
	
	public void goConsumption(){
		lblslctblHogares.setVisible(true);
		lblslctblConsumos.setVisible(false);
		lblslctblReportes.setVisible(true);
		this.setVisible(true);
	}
	
	public void goReport(){
		lblslctblHogares.setVisible(true);
		lblslctblConsumos.setVisible(true);
		lblslctblReportes.setVisible(false);
		this.setVisible(true);
	}
}
