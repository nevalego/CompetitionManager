package ui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.exception.DataException;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JSeparator separator_3;
	private JButton btnVerInscripciones;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(7, 0, 0, 0));
		contentPane.add(getSeparator());
		contentPane.add(getBtnNewButton_1());
		contentPane.add(getSeparator_1());
		contentPane.add(getBtnNewButton());
		contentPane.add(getSeparator_2());
		contentPane.add(getBtnVerInscripciones());
		contentPane.add(getSeparator_3());
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Inscribirse");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaInscripcion vi;
					try {
						vi = new VentanaInscripcion();
						vi.setVisible(true);
					} catch (DataException e1) {}
					
					
					
					turndown();
				}
			});
			btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 22));
		}
		return btnNewButton;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("Registrarse");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaRegistro r = new VentanaRegistro();
					r.setVisible(true);
					turndown();
				}
			});
			btnNewButton_1.setFont(new Font("Verdana", Font.PLAIN, 20));
		}
		return btnNewButton_1;
	}
	
	private void turndown() {
		this.dispose();
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}
	private JSeparator getSeparator_2() {
		if (separator_2 == null) {
			separator_2 = new JSeparator();
		}
		return separator_2;
	}
	private JSeparator getSeparator_3() {
		if (separator_3 == null) {
			separator_3 = new JSeparator();
		}
		return separator_3;
	}
	private JButton getBtnVerInscripciones() {
		if (btnVerInscripciones == null) {
			btnVerInscripciones = new JButton("Ver Inscripciones");
			btnVerInscripciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaOrganizador o = null;
					try {
						o = new VentanaOrganizador();
					} catch (DataException e1) {
					}
					o.setVisible(true);
					turndown();
				}
			});
			btnVerInscripciones.setFont(new Font("Verdana", Font.PLAIN, 22));
		}
		return btnVerInscripciones;
	}
}
