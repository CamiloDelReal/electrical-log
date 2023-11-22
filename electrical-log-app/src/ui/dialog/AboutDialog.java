package ui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.SwingConstants;

import ui.util.GraphicSupport;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AboutDialog extends JDialog {
	private static final long serialVersionUID = 3123848898560083762L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AboutDialog dialog = new AboutDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AboutDialog() {
		setResizable(false);
		setModal(true);
		setTitle("Acerca de...");
		setBounds(100, 100, 320, 178);
		GraphicSupport.centerWindow(this);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblNewLabel = new JLabel(new ImageIcon("img/logotype.png"));
		lblNewLabel.setSize(new Dimension(32, 32));
		lblNewLabel.setPreferredSize(new Dimension(32, 32));
		lblNewLabel.setMinimumSize(new Dimension(32, 32));
		lblNewLabel.setMaximumSize(new Dimension(32, 32));
		lblNewLabel.setBounds(10, 11, 32, 32);
		contentPanel.add(lblNewLabel);
		{
			JLabel lblInstitutoSuperiorPolitecnico = new JLabel("Instituto Superior Politecnico José Antonio Echeverría");
			lblInstitutoSuperiorPolitecnico.setFont(new Font("Tahoma", Font.PLAIN, 8));
			lblInstitutoSuperiorPolitecnico.setBounds(52, 29, 201, 14);
			contentPanel.add(lblInstitutoSuperiorPolitecnico);
		}
		{
			JLabel lblLabel = new JLabel("Gestión de consumos eléctricos");
			lblLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblLabel.setBounds(20, 64, 284, 14);
			contentPanel.add(lblLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Versión 20111215");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(10, 89, 294, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cerrar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
