package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import logic.exception.DataException;
import logic.inscripcion.PagoInscripcion;
import logic.model.Atleta;
import logic.model.Competicion;
import logic.model.Inscripcion;
import util.Dates;

public class VentanaPago extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnPagar;
	private JButton btnPagar;
	private JPanel pnTitulo;
	private JLabel lblDatosDePago;
	private JPanel pnInfo;
	private JPanel pnMetodoPago;
	private JPanel pnInfoPago;
	private JLabel lblMtodoDePago;
	private JRadioButton rdbtnTransferenciaBancaria;
	private JTextField txtCuentaEs;
	private JTextField txtCantidad;
	private PagoInscripcion pago = new PagoInscripcion();
	private Competicion competicion= null;
	private Atleta atleta = null;
	private Inscripcion inscripcion= null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPago frame = new VentanaPago();
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
	public VentanaPago() {
		setTitle("Datos Pago");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 742, 267);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnPagar(), BorderLayout.SOUTH);
		contentPane.add(getPnTitulo(), BorderLayout.NORTH);
		contentPane.add(getPnInfo(), BorderLayout.CENTER);
	}

	private JPanel getPnPagar() {
		if (pnPagar == null) {
			pnPagar = new JPanel();
			pnPagar.setBackground(Color.WHITE);
			pnPagar.add(getBtnPagar());
		}
		return pnPagar;
	}

	private JButton getBtnPagar() {
		if (btnPagar == null) {
			btnPagar = new JButton("Pagar");
			btnPagar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					pagar();
					
				}
			});
			btnPagar.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnPagar.setBackground(Color.WHITE);
		}
		return btnPagar;
	}

	protected void pagar() {
		
		try{pago.pagarInscripcion(inscripcion);
		VentanaJustificante vj = new VentanaJustificante();
		vj.pideDato("SU INSCRIPCION HA SIDO UN EXITO","EMAIL", atleta.email+ " "+atleta.nombre+" "+atleta.apellido,
				"COMPETICION",competicion.nombre , "FECHA", competicion.fecha.toString(), "PAGO", "Abonado" );
		vj.setVisible(true);
		}catch( DataException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "FALLO DE INSCRIPCION", JOptionPane.ERROR_MESSAGE);
		}
	}

	private JPanel getPnTitulo() {
		if (pnTitulo == null) {
			pnTitulo = new JPanel();
			pnTitulo.setBackground(Color.WHITE);
			pnTitulo.setLayout(new GridLayout(1, 0, 0, 0));
			pnTitulo.add(getLblDatosDePago());
		}
		return pnTitulo;
	}

	private JLabel getLblDatosDePago() {
		if (lblDatosDePago == null) {
			lblDatosDePago = new JLabel("Datos de Pago de Inscripcion");
			lblDatosDePago.setFont(new Font("Tahoma", Font.PLAIN, 19));
			lblDatosDePago.setBackground(Color.WHITE);
		}
		return lblDatosDePago;
	}

	private JPanel getPnInfo() {
		if (pnInfo == null) {
			pnInfo = new JPanel();
			pnInfo.setBackground(Color.WHITE);
			pnInfo.setLayout(new GridLayout(1, 0, 0, 0));
			pnInfo.add(getPnMetodoPago());
			pnInfo.add(getPnInfoPago());
		}
		return pnInfo;
	}

	private JPanel getPnMetodoPago() {
		if (pnMetodoPago == null) {
			pnMetodoPago = new JPanel();
			pnMetodoPago.setBackground(Color.WHITE);
			pnMetodoPago.setLayout(new GridLayout(0, 1, 0, 0));
			pnMetodoPago.add(getLblMtodoDePago());
			pnMetodoPago.add(getRdbtnTransferenciaBancaria());
		}
		return pnMetodoPago;
	}

	private JPanel getPnInfoPago() {
		if (pnInfoPago == null) {
			pnInfoPago = new JPanel();
			pnInfoPago.setBackground(Color.WHITE);
			pnInfoPago.setLayout(new GridLayout(0, 1, 0, 0));
			pnInfoPago.add(getTxtCuentaEs());
			pnInfoPago.add(getTxtCantidad());
		}
		return pnInfoPago;
	}

	private JLabel getLblMtodoDePago() {
		if (lblMtodoDePago == null) {
			lblMtodoDePago = new JLabel("M\u00E9todo de Pago");
			lblMtodoDePago.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblMtodoDePago;
	}

	private JRadioButton getRdbtnTransferenciaBancaria() {
		if (rdbtnTransferenciaBancaria == null) {
			rdbtnTransferenciaBancaria = new JRadioButton("Transferencia Bancaria (menos de 48 h)");
			rdbtnTransferenciaBancaria.setFont(new Font("Tahoma", Font.PLAIN, 15));
			rdbtnTransferenciaBancaria.setSelected(true);
			rdbtnTransferenciaBancaria.setBackground(Color.WHITE);
		}
		return rdbtnTransferenciaBancaria;
	}

	private JTextField getTxtCuentaEs() {
		if (txtCuentaEs == null) {
			txtCuentaEs = new JTextField();
			txtCuentaEs.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtCuentaEs.setText("Cuenta: ES9200 4739 2046 2473 8402 ");
			txtCuentaEs.setColumns(10);
		}
		return txtCuentaEs;
	}

	private JTextField getTxtCantidad() {
		if (txtCantidad == null) {
			txtCantidad = new JTextField();
			txtCantidad.setHorizontalAlignment(SwingConstants.LEFT);
			txtCantidad.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtCantidad.setText("Cantidad a abonar: 27.98 \u20AC");
			txtCantidad.setColumns(10);
		}
		return txtCantidad;
	}

	public void mostrar(Inscripcion inscripcion, Atleta atleta) {

		Competicion competicion = null;
		this.inscripcion = inscripcion;
		this.atleta = atleta;
		
		try {
			// Pago valido 2 dias despues de la fecha de inscripcion
			if ( Dates.isBefore(Dates.now(), Dates.addDays(inscripcion.fecha, 2)) ) {
				this.competicion = pago.getCompeticion(inscripcion.id);	
			}
		} catch (DataException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "FALLO DE INSCRIPCION", JOptionPane.ERROR_MESSAGE);
		}
	}
}
