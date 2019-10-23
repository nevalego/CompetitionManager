package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.dto.AtletaDto;
import logic.exception.DataException;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;

public class VentanaJustificante extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnNewButton;
	private JLabel lblSuAlgoSe;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblEmail;
	private JLabel lblDni;
	private JLabel lblFechaDeAlgo;
	private JLabel lblPonerAlgo;
	private JLabel lblPonerAlgo_1;
	private JLabel lblPonerAlgo_2;
	private JLabel lblPonerAlgo_3;
	private JLabel lblPonerAlgo_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaJustificante frame = new VentanaJustificante();
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
	public VentanaJustificante() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 433, 643);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnNewButton());
		contentPane.add(getLblSuAlgoSe());
		contentPane.add(getLblNombre());
		contentPane.add(getLblApellido());
		contentPane.add(getLblEmail());
		contentPane.add(getLblDni());
		contentPane.add(getLblFechaDeAlgo());
		contentPane.add(getLblPonerAlgo());
		contentPane.add(getLblPonerAlgo_1());
		contentPane.add(getLblPonerAlgo_2());
		contentPane.add(getLblPonerAlgo_3());
		contentPane.add(getLblPonerAlgo_4());
	}
	

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Continuar");
			btnNewButton.setBounds(270, 543, 139, 53);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					finalizar();
				}
			});
		}
		return btnNewButton;
	}
	private JLabel getLblSuAlgoSe() {
		if (lblSuAlgoSe == null) {
			lblSuAlgoSe = new JLabel("Su algo se ha completado con exito");
			lblSuAlgoSe.setFont(new Font("Verdana", Font.PLAIN, 18));
			lblSuAlgoSe.setBounds(10, 28, 407, 53);
		}
		return lblSuAlgoSe;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setFont(new Font("Verdana", Font.PLAIN, 15));
			lblNombre.setBounds(20, 90, 80, 27);
		}
		return lblNombre;
	}
	private JLabel getLblApellido() {
		if (lblApellido == null) {
			lblApellido = new JLabel("Apellido;");
			lblApellido.setFont(new Font("Verdana", Font.PLAIN, 15));
			lblApellido.setBounds(20, 152, 100, 53);
		}
		return lblApellido;
	}
	private JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel("Email:");
			lblEmail.setFont(new Font("Verdana", Font.PLAIN, 15));
			lblEmail.setBounds(20, 215, 127, 53);
		}
		return lblEmail;
	}
	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("Dni:");
			lblDni.setFont(new Font("Verdana", Font.PLAIN, 15));
			lblDni.setBounds(20, 278, 127, 85);
		}
		return lblDni;
	}
	private JLabel getLblFechaDeAlgo() {
		if (lblFechaDeAlgo == null) {
			lblFechaDeAlgo = new JLabel("Fecha de algo:");
			lblFechaDeAlgo.setFont(new Font("Verdana", Font.PLAIN, 15));
			lblFechaDeAlgo.setBounds(20, 406, 180, 46);
		}
		return lblFechaDeAlgo;
	}
	
	private void finalizar() {
		this.dispose();
	}
	private JLabel getLblPonerAlgo() {
		if (lblPonerAlgo == null) {
			lblPonerAlgo = new JLabel("PONER ALGO");
			lblPonerAlgo.setFont(new Font("Verdana", Font.PLAIN, 15));
			lblPonerAlgo.setHorizontalAlignment(SwingConstants.CENTER);
			lblPonerAlgo.setBounds(208, 91, 201, 27);
		}
		return lblPonerAlgo;
	}
	private JLabel getLblPonerAlgo_1() {
		if (lblPonerAlgo_1 == null) {
			lblPonerAlgo_1 = new JLabel("PONER ALGO");
			lblPonerAlgo_1.setFont(new Font("Verdana", Font.PLAIN, 15));
			lblPonerAlgo_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblPonerAlgo_1.setBounds(208, 406, 201, 53);
		}
		return lblPonerAlgo_1;
	}
	private JLabel getLblPonerAlgo_2() {
		if (lblPonerAlgo_2 == null) {
			lblPonerAlgo_2 = new JLabel("PONER ALGO");
			lblPonerAlgo_2.setFont(new Font("Verdana", Font.PLAIN, 15));
			lblPonerAlgo_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblPonerAlgo_2.setBounds(208, 297, 201, 53);
		}
		return lblPonerAlgo_2;
	}
	private JLabel getLblPonerAlgo_3() {
		if (lblPonerAlgo_3 == null) {
			lblPonerAlgo_3 = new JLabel("PONER ALGO");
			lblPonerAlgo_3.setFont(new Font("Verdana", Font.PLAIN, 15));
			lblPonerAlgo_3.setHorizontalAlignment(SwingConstants.CENTER);
			lblPonerAlgo_3.setBounds(208, 215, 201, 53);
		}
		return lblPonerAlgo_3;
	}
	private JLabel getLblPonerAlgo_4() {
		if (lblPonerAlgo_4 == null) {
			lblPonerAlgo_4 = new JLabel("PONER ALGO");
			lblPonerAlgo_4.setFont(new Font("Verdana", Font.PLAIN, 15));
			lblPonerAlgo_4.setHorizontalAlignment(SwingConstants.CENTER);
			lblPonerAlgo_4.setBounds(203, 142, 206, 53);
		}
		return lblPonerAlgo_4;
	}

	public void pideDatos(String string, String string2, String text, String string3, String text2, String string4,
			String text3, String string5, String text4, String string6, String string7) {
		lblNombre.setText(string2);
		lblPonerAlgo.setText(text);
		lblApellido.setText(string3);
		lblPonerAlgo_1.setText(text2);
		lblEmail.setText(string4);
		lblPonerAlgo_2.setText(text3);
		lblDni.setText(string5);
		lblPonerAlgo_3.setText(text4);
		lblFechaDeAlgo.setText(string6);
		lblPonerAlgo_4.setText(string7);
		lblSuAlgoSe.setText(string);
		
	}

	public void pideDatos(String string, String string2, String text, String string3, String string4) {
		lblNombre.setText(string2);
		lblPonerAlgo.setText(text);
		lblApellido.setText(string3);
		lblPonerAlgo_1.setText(string4);
		lblEmail.setText("");
		lblPonerAlgo_2.setText("");
		lblDni.setText("");
		lblPonerAlgo_3.setText("");
		lblFechaDeAlgo.setText("");
		lblPonerAlgo_4.setText("");
		lblSuAlgoSe.setText(string);
		
	}
}
