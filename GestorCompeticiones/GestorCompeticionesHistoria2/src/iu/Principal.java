package iu;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import logic.exception.DataException;
import logic.inscripcion.HacerInscripcion;
import logic.inscripcion.HacerRegistro;
import logic.inscripcion.ListarCompeticiones;
import logic.inscripcion.ListarInscripciones;
import logic.inscripcion.NuevaCompeticion;
import logic.inscripcion.PagoInscripcion;
import logic.model.Atleta;
import logic.model.AtletaInscripcion;
import logic.model.Competicion;
import logic.model.Inscripcion;
import logic.model.Plazo;
import util.Dates;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JPanel pnEntrarOrganizador;
	private JLabel lblEntrarComoOrganizador;
	private JButton btnEntrar;
	private JPanel pnEntrarAtleta;
	private JLabel lblEntrarComoAtleta;
	private JPanel pnAtleta;
	private JPanel pnIniciarSesion;
	private JPanel pnRegistrarAtleta;
	private JLabel lblEmail;
	private JTextField txtFieldEmail;
	private JButton btnIniciaSesin;
	private JButton btnRegistrarme;
	private JPanel pnCards;
	private JPanel pnPrincipal;
	private JPanel pnAtletaMenu;
	private JLabel lblInicio;
	private JLabel lblMenuAtleta;
	private JPanel pnListasMenuAtleta;
	private JPanel pnMenuAtletaCompeticiones;
	private JPanel pnMenuAtletaInscripciones;
	private JPanel pnButtons;
	private JButton btnVolver;
	private JLabel lblListaDeCompeticiones;
	private JLabel lblListaDeInscripciones;
	private JScrollPane scrollPaneCompeticiones;
	private JScrollPane scrollPaneInscripciones;
	private JList<Competicion> listCompeticiones;
	private JList<Inscripcion> listInscripciones;
	private JPanel pnBtnInscribirme;
	private JButton btnInscribirme;
	private JPanel pnBtnPagarInscripcion;
	private JButton btnPagar;
	private JPanel pnPagoAtleta;
	private JLabel lblMenAtletaPago;
	private JPanel pnMetodoPago;
	private JLabel lblMetodosDePago;
	private JPanel pnMetodosPago;
	private JRadioButton rdbtnPagoPorTransferencia;
	private JRadioButton rdbtnPagoPorTarjeta;
	private JPanel pnRadioButtonsPago;
	private JPanel pnInfoPago;
	private JButton btnPagarTarjeta;
	private JLabel lblMetodoDePago;
	private JPanel panel;
	private JTextPane txtpnInformacinPago;
	private JPanel pnTarjetaCredito;
	private JLabel lblNombreTarjeta;
	private JTextField txtNombreTarjeta;
	private JLabel lblNmero;
	private JTextField txtNumeroTarjeta;
	private JLabel lblFechaDeCaducidad;
	private JLabel lblCdigoVerificacin;
	private JTextField txtCodigoTarjeta;
	private int cardNumber = 0;
	private Atleta atleta = null;
	private DefaultListModel<Competicion> modelCompeticiones = new DefaultListModel<>();
	private DefaultListModel<Inscripcion> modelInscripciones = new DefaultListModel<>();
	private JPanel pnRegistro;
	private JLabel lblRegistroNuevoAtleta;
	private JPanel pnDatosRegistro;
	private JPanel pnBtnRegistrar;
	private JButton btnRegistro;
	private JLabel lblNombre;
	private JTextField txtNombreAtleta;
	private JLabel lblApellidos;
	private JTextField txtApellidosAtleta;
	private JLabel lblDni;
	private JTextField txtDniAtleta;
	private JLabel lblEmailAtleta;
	private JTextField txtEmailAtleta;
	private JLabel lblSexo;
	private JComboBox comboBoxSexo;
	private JLabel lblFechaNacimiento;
	private JComboBox comboBoxDiaNacimiento;
	private JComboBox comboBoxMesNacimiento;
	private JComboBox comboBoxAñoNacimiento;
	private JPanel panelOrganizador;
	private JPanel panelCompeticion;
	private JLabel lblCompeticion;
	private JTextField txtCompeticion;
	private JButton btnListar;
	private JPanel lowerPanel;
	private JScrollPane scCompeticiones;
	private JList<AtletaInscripcion> list;
	private DefaultListModel<AtletaInscripcion> modeloInscripciones = new DefaultListModel<>();
	private JTextField txtCaducidad;
	private JPanel pnOrganizador;
	private JButton btnCrearCompeticion;
	private JPanel pnNuevaCompeticion;
	private JLabel lblNombreCompeticion;
	private JTextField txtNombreCompeticionNueva;
	private JLabel lblTipo;
	private JTextField txtTipoCompeticionNueva;
	private JLabel lblKilometroskm;
	private JTextField txtKmCompeticionNueva;
	private JPanel pnInfoNuevaCompeticion;
	private JPanel pnBtnCrear;
	private JLabel lblNuevaCompeticion;
	private JButton btnCrear;
	private JLabel lblPlazas;
	private JSpinner spinnerPlazasCompeticionNueva;
	private JLabel lblFecha;
	private JTextField txtFechaCompeticionNueva;
	private JPanel pnBtnCrearCompeticion;
	private JPanel pnTablaPlazos;
	private JScrollPane scrollPanePlazos;
	private JTable tablePlazos;
	private List<Plazo> plazosNuevaCompeticion = new ArrayList<>();
	private Competicion competicionNueva = new Competicion();
	private DefaultTableModel modelTablaPlazos = new DefaultTableModel();
	private JPanel pnCentralCompeticionNueva;
	private JPanel pnTablaCategorias;
	private JLabel lblTablaPlazos;
	private JPanel pnNuevaFilaPlazos;
	private JButton buttonNuevaFilaPlazo;
	private JButton buttonBorrarFilaPlazo;
	private JLabel lblCategorias;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/util/icono.png")));
		setResizable(false);
		setBackground(Color.WHITE);
		setTitle("Gestor Competiciones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 362);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnCards(), BorderLayout.CENTER);
		contentPane.add(getPnButtons(), BorderLayout.SOUTH);
		setContentPane(contentPane);
		seleccionarPagoTransferencia();
	}

	private JPanel getPnEntrarOrganizador() {
		if (pnEntrarOrganizador == null) {
			pnEntrarOrganizador = new JPanel();
			pnEntrarOrganizador.setBorder(new LineBorder(new Color(0, 0, 0)));
			pnEntrarOrganizador.setBackground(Color.WHITE);
			pnEntrarOrganizador.add(getLblEntrarComoOrganizador());
			pnEntrarOrganizador.add(getBtnEntrar());
		}
		return pnEntrarOrganizador;
	}

	private JLabel getLblEntrarComoOrganizador() {
		if (lblEntrarComoOrganizador == null) {
			lblEntrarComoOrganizador = new JLabel("Entrar como Organizador");
			lblEntrarComoOrganizador.setHorizontalAlignment(SwingConstants.LEFT);
			lblEntrarComoOrganizador.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lblEntrarComoOrganizador;
	}

	private JButton getBtnEntrar() {
		if (btnEntrar == null) {
			btnEntrar = new JButton("Entrar");
			btnEntrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					toOrganizadorMenu();
				}
			});
		}
		return btnEntrar;
	}

	private JPanel getPnEntrarAtleta() {
		if (pnEntrarAtleta == null) {
			pnEntrarAtleta = new JPanel();
			pnEntrarAtleta.setBorder(new LineBorder(new Color(0, 0, 0)));
			pnEntrarAtleta.setBackground(Color.WHITE);
			pnEntrarAtleta.setLayout(new BorderLayout(0, 0));
			pnEntrarAtleta.add(getLblEntrarComoAtleta(), BorderLayout.NORTH);
			pnEntrarAtleta.add(getPnAtleta(), BorderLayout.CENTER);
		}
		return pnEntrarAtleta;
	}

	private JLabel getLblEntrarComoAtleta() {
		if (lblEntrarComoAtleta == null) {
			lblEntrarComoAtleta = new JLabel("Entrar como Atleta");
			lblEntrarComoAtleta.setHorizontalAlignment(SwingConstants.CENTER);
			lblEntrarComoAtleta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lblEntrarComoAtleta;
	}

	private JPanel getPnAtleta() {
		if (pnAtleta == null) {
			pnAtleta = new JPanel();
			pnAtleta.setBorder(null);
			pnAtleta.setBackground(Color.WHITE);
			pnAtleta.setLayout(new GridLayout(1, 0, 0, 0));
			pnAtleta.add(getPnIniciarSesion());
			pnAtleta.add(getPnRegistrarAtleta());
		}
		return pnAtleta;
	}

	private JPanel getPnIniciarSesion() {
		if (pnIniciarSesion == null) {
			pnIniciarSesion = new JPanel();
			pnIniciarSesion.setBackground(Color.WHITE);
			pnIniciarSesion.add(getLblEmail());
			pnIniciarSesion.add(getTxtFieldEmail());
			pnIniciarSesion.add(getBtnIniciaSesin());
		}
		return pnIniciarSesion;
	}

	private JPanel getPnRegistrarAtleta() {
		if (pnRegistrarAtleta == null) {
			pnRegistrarAtleta = new JPanel();
			pnRegistrarAtleta.setBackground(Color.WHITE);
			pnRegistrarAtleta.add(getBtnRegistrarme());
		}
		return pnRegistrarAtleta;
	}

	private JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel("Email:");
		}
		return lblEmail;
	}

	private JTextField getTxtFieldEmail() {
		if (txtFieldEmail == null) {
			txtFieldEmail = new JTextField();
			txtFieldEmail.setColumns(10);
		}
		return txtFieldEmail;
	}

	private JButton getBtnIniciaSesin() {
		if (btnIniciaSesin == null) {
			btnIniciaSesin = new JButton("Iniciar sesi\u00F3n");
			btnIniciaSesin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					iniciarSesion(txtFieldEmail.getText());
				}
			});
		}
		return btnIniciaSesin;
	}

	protected void iniciarSesion(String email) {

		HacerInscripcion ins = new HacerInscripcion();
		try {
			atleta = ins.getAtleta(email);
			if (atleta == null)
				JOptionPane.showMessageDialog(this, "Tu email no est� registrado en ning�n atleta");
			else
				toAtletaMenu();
		} catch (DataException e) {
			JOptionPane.showMessageDialog(this, "Error en inicio de sesi�n");
		}
	}

	private JButton getBtnRegistrarme() {
		if (btnRegistrarme == null) {
			btnRegistrarme = new JButton("Registrarme");
			btnRegistrarme.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					toRegistrarse();
				}
			});
		}
		return btnRegistrarme;
	}

	private JPanel getPnCards() {
		if (pnCards == null) {
			pnCards = new JPanel();
			pnCards.setBackground(Color.WHITE);
			pnCards.setLayout(new CardLayout(0, 0));
			pnCards.add(getPnPrincipal(), "principal");
			pnCards.add(getPnAtletaMenu(), "atletamenu");
			pnCards.add(getPnPagoAtleta(), "pagoatleta");
			pnCards.add(getPnRegistro(), "registro");
			pnCards.add(getPnOrganizador(), "organizadormenu");
			pnCards.add(getPnNuevaCompeticion(), "crearcompeticion");
		}
		return pnCards;
	}

	private JPanel getPnPrincipal() {
		if (pnPrincipal == null) {
			pnPrincipal = new JPanel();
			pnPrincipal.setBackground(Color.WHITE);
			pnPrincipal.setLayout(new BorderLayout(0, 0));
			pnPrincipal.add(getPnEntrarOrganizador(), BorderLayout.SOUTH);
			pnPrincipal.add(getPnEntrarAtleta());
			pnPrincipal.add(getLblInicio(), BorderLayout.NORTH);
		}
		return pnPrincipal;
	}

	private JPanel getPnAtletaMenu() {
		if (pnAtletaMenu == null) {
			pnAtletaMenu = new JPanel();
			pnAtletaMenu.setBackground(Color.WHITE);
			pnAtletaMenu.setLayout(new BorderLayout(0, 0));
			pnAtletaMenu.add(getLblMenuAtleta(), BorderLayout.NORTH);
			pnAtletaMenu.add(getPnListasMenuAtleta(), BorderLayout.CENTER);
		}
		return pnAtletaMenu;
	}

	private JLabel getLblInicio() {
		if (lblInicio == null) {
			lblInicio = new JLabel("Inicio");
			lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
			lblInicio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblInicio;
	}

	private JLabel getLblMenuAtleta() {
		if (lblMenuAtleta == null) {
			lblMenuAtleta = new JLabel("Men\u00FA Atleta");
			lblMenuAtleta.setHorizontalAlignment(SwingConstants.CENTER);
			lblMenuAtleta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblMenuAtleta;
	}

	private JPanel getPnListasMenuAtleta() {
		if (pnListasMenuAtleta == null) {
			pnListasMenuAtleta = new JPanel();
			pnListasMenuAtleta.setBackground(new Color(255, 255, 255));
			pnListasMenuAtleta.setLayout(new GridLayout(1, 0, 0, 0));
			pnListasMenuAtleta.add(getPnMenuAtletaCompeticiones());
			pnListasMenuAtleta.add(getPnMenuAtletaInscripciones());
		}
		return pnListasMenuAtleta;
	}

	private JPanel getPnMenuAtletaCompeticiones() {
		if (pnMenuAtletaCompeticiones == null) {
			pnMenuAtletaCompeticiones = new JPanel();
			pnMenuAtletaCompeticiones.setBackground(Color.WHITE);
			pnMenuAtletaCompeticiones.setLayout(new BorderLayout(0, 0));
			pnMenuAtletaCompeticiones.add(getLblListaDeCompeticiones(), BorderLayout.NORTH);
			pnMenuAtletaCompeticiones.add(getScrollPaneCompeticiones(), BorderLayout.CENTER);
			pnMenuAtletaCompeticiones.add(getPnBtnInscribirme(), BorderLayout.SOUTH);
		}
		return pnMenuAtletaCompeticiones;
	}

	private JPanel getPnMenuAtletaInscripciones() {
		if (pnMenuAtletaInscripciones == null) {
			pnMenuAtletaInscripciones = new JPanel();
			pnMenuAtletaInscripciones.setBackground(Color.WHITE);
			pnMenuAtletaInscripciones.setLayout(new BorderLayout(0, 0));
			pnMenuAtletaInscripciones.add(getLblListaDeInscripciones(), BorderLayout.NORTH);
			pnMenuAtletaInscripciones.add(getScrollPaneInscripciones(), BorderLayout.CENTER);
			pnMenuAtletaInscripciones.add(getPnBtnPagarInscripcion(), BorderLayout.SOUTH);
		}
		return pnMenuAtletaInscripciones;
	}

	private JPanel getPnButtons() {
		if (pnButtons == null) {
			pnButtons = new JPanel();
			pnButtons.setBackground(Color.WHITE);
			pnButtons.add(getBtnVolver());
			pnButtons.setVisible(false);
		}
		return pnButtons;
	}

	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					previousCard();
				}
			});
		}
		return btnVolver;
	}

	private JLabel getLblListaDeCompeticiones() {
		if (lblListaDeCompeticiones == null) {
			lblListaDeCompeticiones = new JLabel("Lista de Competiciones Disponibles");
		}
		return lblListaDeCompeticiones;
	}

	private JLabel getLblListaDeInscripciones() {
		if (lblListaDeInscripciones == null) {
			lblListaDeInscripciones = new JLabel("Lista de mis Inscripciones ");
		}
		return lblListaDeInscripciones;
	}

	private JScrollPane getScCompeticiones() {
		if (scrollPaneCompeticiones == null) {
			scrollPaneCompeticiones = new JScrollPane();
			scrollPaneCompeticiones.setViewportView(getListCompeticiones());
		}
		return scrollPaneCompeticiones;
	}

	private JScrollPane getScrollPaneInscripciones() {
		if (scrollPaneInscripciones == null) {
			scrollPaneInscripciones = new JScrollPane();
			scrollPaneInscripciones.setViewportView(getListInscripciones());
		}
		return scrollPaneInscripciones;
	}

	private JList<Competicion> getListCompeticiones() {
		if (listCompeticiones == null) {
			listCompeticiones = new JList<Competicion>(modelCompeticiones);
		}
		return listCompeticiones;
	}

	private JList<Inscripcion> getListInscripciones() {
		if (listInscripciones == null) {
			listInscripciones = new JList<Inscripcion>(modelInscripciones);
		}
		return listInscripciones;
	}

	private JPanel getPnBtnInscribirme() {
		if (pnBtnInscribirme == null) {
			pnBtnInscribirme = new JPanel();
			pnBtnInscribirme.setBackground(Color.WHITE);
			pnBtnInscribirme.add(getBtnInscribirme());
		}
		return pnBtnInscribirme;
	}

	private JButton getBtnInscribirme() {
		if (btnInscribirme == null) {
			btnInscribirme = new JButton("Inscribirme");
			btnInscribirme.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					inscribirse(modelCompeticiones.get(listCompeticiones.getSelectedIndex()));
				}
			});
		}
		return btnInscribirme;
	}

	protected void inscribirse(Competicion competicion) {

		HacerInscripcion inscribirse = new HacerInscripcion();
		try {
			inscribirse.inscribirse(atleta.getId(), competicion.getId());

			Inscripcion ins = inscribirse.getInscripcion(atleta.getId(), competicion.getId());
			if (ins == null) {
				JOptionPane.showMessageDialog(this, "Error al realizar la inscripci�n");
			} else {
				JOptionPane.showMessageDialog(this, "Su inscripci�n se ha realizado con �xito");
				toAtletaMenu();
			}
		} catch (DataException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

	}

	private JPanel getPnBtnPagarInscripcion() {
		if (pnBtnPagarInscripcion == null) {
			pnBtnPagarInscripcion = new JPanel();
			pnBtnPagarInscripcion.setBackground(Color.WHITE);
			pnBtnPagarInscripcion.add(getBtnPagar());
		}
		return pnBtnPagarInscripcion;
	}

	private JButton getBtnPagar() {
		if (btnPagar == null) {
			btnPagar = new JButton("Pagar");
			btnPagar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cardNumber++;
					toPagoAtleta();
				}
			});
		}
		return btnPagar;
	}

	protected void pagar(Inscripcion inscripcion) {

		if (inscripcion == null) {
			JOptionPane.showMessageDialog(this, "No ha seleccionado ninguna inscripci�n para pagar");
		} else {
			PagoInscripcion pago = new PagoInscripcion();
			Inscripcion ins = null;

			int mes = Integer.parseInt(txtCaducidad.getText().split("/")[0]);
			int año = Integer.parseInt(txtCaducidad.getText().split("/")[1]);
			Date caducidad = new Date(año, mes, 1);

			if (txtNumeroTarjeta.getText().length() != 16) {
				JOptionPane.showMessageDialog(this, "La longitud del número de la tarjeta no es correcta");
			} else if (Dates.isAfter(Dates.now(), caducidad)) {
				JOptionPane.showMessageDialog(this, "La tarjeta ha sobrepasado su fecha de expiración");
			} else {
				try {
					ins = pago.obtenerInscripcion(atleta.getId(), inscripcion.getId());
					ins.fechaPago = Dates.now();
					ins.medioPago = "Tarjeta";
					Date plazoMaxPago = Dates.addDays(ins.fecha, 2);// 48 h tras
																	// la
																	// inscripcion

					if (ins.fechaPago.after(plazoMaxPago)) {
						JOptionPane.showMessageDialog(this, "La fecha de pago se encuentra fuera del periodo de pago");
					} else {
						Plazo plazo = pago.obtenerPlazo(ins);
						ins.cantidad = plazo.cuota;
						pago.pagarInscripcion(ins, ins.cantidad, ins.fechaPago);
					}
				} catch (DataException e) {
					JOptionPane.showMessageDialog(this, e.getMessage());
				}
			}
		}

	}

	private JPanel getPnPagoAtleta() {
		if (pnPagoAtleta == null) {
			pnPagoAtleta = new JPanel();
			pnPagoAtleta.setBackground(Color.WHITE);
			pnPagoAtleta.setLayout(new BorderLayout(0, 0));
			pnPagoAtleta.add(getLblMenAtletaPago(), BorderLayout.NORTH);
			pnPagoAtleta.add(getPnMetodoPago(), BorderLayout.CENTER);
		}
		return pnPagoAtleta;
	}

	private JLabel getLblMenAtletaPago() {
		if (lblMenAtletaPago == null) {
			lblMenAtletaPago = new JLabel("Men\u00FA Atleta: Pago de Inscripci\u00F3n");
			lblMenAtletaPago.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblMenAtletaPago;
	}

	private JPanel getPnMetodoPago() {
		if (pnMetodoPago == null) {
			pnMetodoPago = new JPanel();
			pnMetodoPago.setBackground(Color.WHITE);
			pnMetodoPago.setLayout(new BorderLayout(0, 0));
			pnMetodoPago.add(getLblMetodosDePago(), BorderLayout.NORTH);
			pnMetodoPago.add(getPnMetodosPago(), BorderLayout.CENTER);
		}
		return pnMetodoPago;
	}

	private JLabel getLblMetodosDePago() {
		if (lblMetodosDePago == null) {
			lblMetodosDePago = new JLabel("M\u00E9todos de Pago");
			lblMetodosDePago.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lblMetodosDePago;
	}

	private JPanel getPnMetodosPago() {
		if (pnMetodosPago == null) {
			pnMetodosPago = new JPanel();
			pnMetodosPago.setBackground(Color.WHITE);
			pnMetodosPago.setLayout(new GridLayout(0, 2, 0, 0));
			pnMetodosPago.add(getPnRadioButtonsPago());
			pnMetodosPago.add(getPnInfoPago());
		}
		return pnMetodosPago;
	}

	private JRadioButton getRdbtnPagoPorTransferencia() {
		if (rdbtnPagoPorTransferencia == null) {
			rdbtnPagoPorTransferencia = new JRadioButton("Pago por Transferencia Bancaria");
			rdbtnPagoPorTransferencia.setFont(new Font("Tahoma", Font.PLAIN, 15));
			rdbtnPagoPorTransferencia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					seleccionarPagoTransferencia();
				}
			});
			rdbtnPagoPorTransferencia.setSelected(true);
			rdbtnPagoPorTransferencia.setBackground(Color.WHITE);
		}
		return rdbtnPagoPorTransferencia;
	}

	protected void seleccionarPagoTransferencia() {
		rdbtnPagoPorTarjeta.setSelected(false);
		pnTarjetaCredito.setVisible(false);
		lblMetodoDePago.setText("Pago por Transferencia");
		txtpnInformacinPago.setVisible(true);
		txtpnInformacinPago.setText("Se debe realizar la transferencia a la cuenta ES04 3379 2010 3472 0238"
				+ "\nEl plazo de pago es de 48 horas tras la " + "fecha de inscripci�n.");
	}

	private JRadioButton getRdbtnPagoPorTarjeta() {
		if (rdbtnPagoPorTarjeta == null) {
			rdbtnPagoPorTarjeta = new JRadioButton("Pago por Tarjeta Cr\u00E9dito");
			rdbtnPagoPorTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 15));
			rdbtnPagoPorTarjeta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					seleccionarPagoTarjeta();
				}
			});
			rdbtnPagoPorTarjeta.setBackground(Color.WHITE);
		}
		return rdbtnPagoPorTarjeta;
	}

	protected void seleccionarPagoTarjeta() {
		rdbtnPagoPorTransferencia.setSelected(false);
		pnTarjetaCredito.setVisible(true);
		txtpnInformacinPago.setVisible(false);
		lblMetodoDePago.setText("Pago por Tarjeta");
	}

	private JPanel getPnRadioButtonsPago() {
		if (pnRadioButtonsPago == null) {
			pnRadioButtonsPago = new JPanel();
			pnRadioButtonsPago.setLayout(new GridLayout(0, 1, 0, 0));
			pnRadioButtonsPago.add(getRdbtnPagoPorTarjeta());
			pnRadioButtonsPago.add(getRdbtnPagoPorTransferencia());
		}
		return pnRadioButtonsPago;
	}

	private JPanel getPnInfoPago() {
		if (pnInfoPago == null) {
			pnInfoPago = new JPanel();
			pnInfoPago.setBackground(Color.WHITE);
			pnInfoPago.setLayout(new BorderLayout(0, 0));
			pnInfoPago.add(getLblMetodoDePago(), BorderLayout.NORTH);
			pnInfoPago.add(getPanel(), BorderLayout.CENTER);
		}
		return pnInfoPago;
	}

	private JButton getBtnPagarTarjeta() {
		if (btnPagarTarjeta == null) {
			btnPagarTarjeta = new JButton("Pagar con tarjeta");
			btnPagarTarjeta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					pagar(modelInscripciones.get(listInscripciones.getSelectedIndex()));
				}
			});
			btnPagarTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnPagarTarjeta.setBackground(new Color(240, 240, 240));
		}
		return btnPagarTarjeta;
	}

	private JLabel getLblMetodoDePago() {
		if (lblMetodoDePago == null) {
			lblMetodoDePago = new JLabel("Metodo de Pago");
			lblMetodoDePago.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblMetodoDePago;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getTxtpnInformacinPago(), BorderLayout.NORTH);
			panel.add(getPnTarjetaCredito(), BorderLayout.CENTER);
		}
		return panel;
	}

	private JTextPane getTxtpnInformacinPago() {
		if (txtpnInformacinPago == null) {
			txtpnInformacinPago = new JTextPane();
			txtpnInformacinPago.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtpnInformacinPago.setText("Informaci\u00F3n Pago");
		}
		return txtpnInformacinPago;
	}

	private JPanel getPnTarjetaCredito() {
		if (pnTarjetaCredito == null) {
			pnTarjetaCredito = new JPanel();
			pnTarjetaCredito.setBackground(Color.WHITE);
			pnTarjetaCredito.add(getLblNombreTarjeta());
			pnTarjetaCredito.add(getTxtNombreTarjeta());
			pnTarjetaCredito.add(getLblNmero());
			pnTarjetaCredito.add(getTxtNumeroTarjeta());
			pnTarjetaCredito.add(getLblFechaDeCaducidad());
			pnTarjetaCredito.add(getTxtCaducidad());
			pnTarjetaCredito.add(getLblCdigoVerificacin());
			pnTarjetaCredito.add(getTxtCodigoTarjeta());
			pnTarjetaCredito.add(getBtnPagarTarjeta());
			pnTarjetaCredito.setVisible(false);
		}
		return pnTarjetaCredito;
	}

	private JLabel getLblNombreTarjeta() {
		if (lblNombreTarjeta == null) {
			lblNombreTarjeta = new JLabel("Nombre Tarjeta:");
			lblNombreTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblNombreTarjeta;
	}

	private JTextField getTxtNombreTarjeta() {
		if (txtNombreTarjeta == null) {
			txtNombreTarjeta = new JTextField();
			txtNombreTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtNombreTarjeta.setColumns(10);
		}
		return txtNombreTarjeta;
	}

	private JLabel getLblNmero() {
		if (lblNmero == null) {
			lblNmero = new JLabel("N\u00FAmero Tarjeta:");
			lblNmero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblNmero;
	}

	private JTextField getTxtNumeroTarjeta() {
		if (txtNumeroTarjeta == null) {
			txtNumeroTarjeta = new JTextField();
			txtNumeroTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtNumeroTarjeta.setColumns(10);
		}
		return txtNumeroTarjeta;
	}

	private JLabel getLblFechaDeCaducidad() {
		if (lblFechaDeCaducidad == null) {
			lblFechaDeCaducidad = new JLabel("Caducidad (mm/aa):");
			lblFechaDeCaducidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblFechaDeCaducidad;
	}

	private JLabel getLblCdigoVerificacin() {
		if (lblCdigoVerificacin == null) {
			lblCdigoVerificacin = new JLabel("C\u00F3digo Verificaci\u00F3n:");
			lblCdigoVerificacin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblCdigoVerificacin;
	}

	private JTextField getTxtCodigoTarjeta() {
		if (txtCodigoTarjeta == null) {
			txtCodigoTarjeta = new JTextField();
			txtCodigoTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtCodigoTarjeta.setColumns(10);
		}
		return txtCodigoTarjeta;
	}

	protected void previousCard() {

		if (cardNumber == 1) {// Panel Atleta Menu
			toFirst();
		} else if (cardNumber == 2) { // Panel Atleta Pago
			toAtletaMenu();
		} else if (cardNumber == 3) { // Panel Registro Atleta
			toFirst();
		} else if (cardNumber == 4) {// Panel menu de organizador
			toFirst();
		} else if (cardNumber == 5) { // Panel nueva competicion
			toOrganizadorMenu();
		} else if (cardNumber == 6) { // Panel configuracion plazos
			toNuevaCompeticion();
		}

	}

	protected void toOrganizadorMenu() {
		pnButtons.setVisible(true);
		cardNumber = 4;
		((CardLayout) pnCards.getLayout()).show(pnCards, "organizadormenu");
	}

	private void toAtletaMenu() {

		loadCompeticiones();
		loadInscripciones();
		pnButtons.setVisible(true);
		cardNumber = 1;
		((CardLayout) pnCards.getLayout()).show(pnCards, "atletamenu");
	}

	private void toRegistrarse() {
		pnButtons.setVisible(true);
		cardNumber = 3;
		((CardLayout) pnCards.getLayout()).show(pnCards, "registro");
	}

	private void toNuevaCompeticion() {
		NuevaCompeticion nueva = new NuevaCompeticion();
		competicionNueva.fecha = Dates.now();
		try {
			competicionNueva.id = nueva.obtenerUltimoIdCompeticion();
			Plazo plazo = new Plazo();
			plazo.competicionId = competicionNueva.id;
			plazo.fechaInicio = Dates.now();
			plazo.fechaFin = Dates.addMonths(plazo.fechaInicio, 3);
			plazo.cuota = 17;
			plazosNuevaCompeticion.add(plazo);
			
			mostrarTablaPlazos();
			
			pnButtons.setVisible(true);
			cardNumber = 5;
			((CardLayout) pnCards.getLayout()).show(pnCards, "crearcompeticion");

		} catch (DataException e) {
			JOptionPane.showMessageDialog(this, "Error al crear una competicion");
		}

	}

	private void loadCompeticiones() {

		modelCompeticiones.removeAllElements();
		ListarCompeticiones listarCompeticiones = new ListarCompeticiones();
		List<Competicion> competiciones = null;
		try {
			competiciones = listarCompeticiones.verCompeticiones(atleta.getEmail());
			for (Competicion c : competiciones) {

				modelCompeticiones.addElement(c);
			}
			listCompeticiones.repaint();
			listCompeticiones.revalidate();
		} catch (DataException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	private void loadInscripciones() {

		modelInscripciones.removeAllElements();
		ListarInscripciones listarInscripciones = new ListarInscripciones();
		List<Inscripcion> inscripciones = null;
		try {
			inscripciones = listarInscripciones.findInscripcion(atleta.getEmail());

			inscripciones.forEach(i -> modelInscripciones.addElement(i));

			listInscripciones.repaint();
			listInscripciones.revalidate();

		} catch (DataException e) {
			JOptionPane.showMessageDialog(this, "Error al ver las inscripciones del atleta");
		}

	}

	private void toFirst() {
		pnButtons.setVisible(false);
		cardNumber = 0;
		((CardLayout) pnCards.getLayout()).first(pnCards);
	}

	private void toPagoAtleta() {
		cardNumber = 2;
		((CardLayout) pnCards.getLayout()).show(pnCards, "pagoatleta");
	}

	private JPanel getPnRegistro() {
		if (pnRegistro == null) {
			pnRegistro = new JPanel();
			pnRegistro.setBackground(Color.WHITE);
			pnRegistro.setLayout(new BorderLayout(0, 0));
			pnRegistro.add(getLblRegistroNuevoAtleta(), BorderLayout.NORTH);
			pnRegistro.add(getPnDatosRegistro(), BorderLayout.CENTER);
			pnRegistro.add(getPnBtnRegistrar(), BorderLayout.SOUTH);
		}
		return pnRegistro;
	}

	private JLabel getLblRegistroNuevoAtleta() {
		if (lblRegistroNuevoAtleta == null) {
			lblRegistroNuevoAtleta = new JLabel("Registro Nuevo Atleta");
			lblRegistroNuevoAtleta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblRegistroNuevoAtleta;
	}

	private JPanel getPnDatosRegistro() {
		if (pnDatosRegistro == null) {
			pnDatosRegistro = new JPanel();
			pnDatosRegistro.setBackground(Color.WHITE);
			pnDatosRegistro.setLayout(null);
			pnDatosRegistro.add(getLblNombre());
			pnDatosRegistro.add(getTxtNombreAtleta());
			pnDatosRegistro.add(getLblApellidos());
			pnDatosRegistro.add(getTxtApellidosAtleta());
			pnDatosRegistro.add(getLblDni());
			pnDatosRegistro.add(getTxtDniAtleta());
			pnDatosRegistro.add(getLblEmailAtleta());
			pnDatosRegistro.add(getTxtEmailAtleta());
			pnDatosRegistro.add(getLblSexo());
			pnDatosRegistro.add(getComboBoxSexo());
			pnDatosRegistro.add(getLblFechaNacimiento());
			pnDatosRegistro.add(getComboBoxDiaNacimiento());
			pnDatosRegistro.add(getComboBoxMesNacimiento());
			pnDatosRegistro.add(getComboBoxAñoNacimiento());
		}
		return pnDatosRegistro;
	}

	private JPanel getPnBtnRegistrar() {
		if (pnBtnRegistrar == null) {
			pnBtnRegistrar = new JPanel();
			pnBtnRegistrar.setBackground(Color.WHITE);
			pnBtnRegistrar.add(getBtnRegistro());
		}
		return pnBtnRegistrar;
	}

	private JButton getBtnRegistro() {
		if (btnRegistro == null) {
			btnRegistro = new JButton("Registrarme");
			btnRegistro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					registrarAtleta();
				}
			});
			btnRegistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnRegistro.setBackground(new Color(240, 240, 240));
		}
		return btnRegistro;
	}

	@SuppressWarnings("deprecation")
	protected void registrarAtleta() {
		HacerRegistro registrar = new HacerRegistro();
		try {
			boolean correcto = registrar.comprobarDatos(txtEmailAtleta.getText(), txtDniAtleta.getText());
			if (correcto) {
				registrar.comprobarFecha(String.valueOf(comboBoxDiaNacimiento.getSelectedItem()),
						String.valueOf(comboBoxMesNacimiento.getSelectedItem()),
						String.valueOf(comboBoxAñoNacimiento.getSelectedItem()));
				atleta = new Atleta();

				atleta.dni = txtDniAtleta.getText();
				atleta.email = txtEmailAtleta.getText();
				atleta.nombre = txtNombreAtleta.getText();
				atleta.apellidos = txtApellidosAtleta.getText();
				atleta.sexo = String.valueOf(comboBoxSexo.getSelectedItem());

				atleta.fechaNacimiento = new Date(
						Integer.valueOf((String) comboBoxAñoNacimiento.getSelectedItem()) - 1900,
						Integer.valueOf((String) comboBoxMesNacimiento.getSelectedItem()),
						Integer.valueOf((String) comboBoxDiaNacimiento.getSelectedItem()));

				registrar.registrar(atleta);
				JOptionPane.showMessageDialog(this, "El registro del nuevo atleta ha sido un �xito");
				toAtletaMenu();
			}

		} catch (DataException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNombre.setBounds(53, 8, 61, 16);
		}
		return lblNombre;
	}

	private JTextField getTxtNombreAtleta() {
		if (txtNombreAtleta == null) {
			txtNombreAtleta = new JTextField();
			txtNombreAtleta.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtNombreAtleta.setBounds(115, 5, 116, 22);
			txtNombreAtleta.setColumns(10);
		}
		return txtNombreAtleta;
	}

	private JLabel getLblApellidos() {
		if (lblApellidos == null) {
			lblApellidos = new JLabel("Apellidos:");
			lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblApellidos.setBounds(264, 8, 56, 16);
		}
		return lblApellidos;
	}

	private JTextField getTxtApellidosAtleta() {
		if (txtApellidosAtleta == null) {
			txtApellidosAtleta = new JTextField();
			txtApellidosAtleta.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtApellidosAtleta.setBounds(329, 5, 219, 22);
			txtApellidosAtleta.setColumns(10);
		}
		return txtApellidosAtleta;
	}

	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("Dni:");
			lblDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblDni.setBounds(53, 40, 50, 16);
		}
		return lblDni;
	}

	private JTextField getTxtDniAtleta() {
		if (txtDniAtleta == null) {
			txtDniAtleta = new JTextField();
			txtDniAtleta.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtDniAtleta.setBounds(115, 37, 116, 22);
			txtDniAtleta.setColumns(10);
		}
		return txtDniAtleta;
	}

	private JLabel getLblEmailAtleta() {
		if (lblEmailAtleta == null) {
			lblEmailAtleta = new JLabel("Email:");
			lblEmailAtleta.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblEmailAtleta.setBounds(53, 69, 36, 16);
		}
		return lblEmailAtleta;
	}

	private JTextField getTxtEmailAtleta() {
		if (txtEmailAtleta == null) {
			txtEmailAtleta = new JTextField();
			txtEmailAtleta.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtEmailAtleta.setBounds(114, 72, 219, 22);
			txtEmailAtleta.setColumns(10);
		}
		return txtEmailAtleta;
	}

	private JLabel getLblSexo() {
		if (lblSexo == null) {
			lblSexo = new JLabel("Sexo:");
			lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblSexo.setBounds(239, 40, 56, 16);
		}
		return lblSexo;
	}

	private JComboBox getComboBoxSexo() {
		if (comboBoxSexo == null) {
			comboBoxSexo = new JComboBox();
			comboBoxSexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			comboBoxSexo.setModel(new DefaultComboBoxModel(new String[] { "Masculino", "Femenino" }));
			comboBoxSexo.setBounds(290, 37, 116, 19);
		}
		return comboBoxSexo;
	}

	private JLabel getLblFechaNacimiento() {
		if (lblFechaNacimiento == null) {
			lblFechaNacimiento = new JLabel("Fecha nacimiento (dd/mm/aa):");
			lblFechaNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblFechaNacimiento.setBounds(53, 122, 199, 16);
		}
		return lblFechaNacimiento;
	}

	private JComboBox getComboBoxDiaNacimiento() {
		if (comboBoxDiaNacimiento == null) {
			comboBoxDiaNacimiento = new JComboBox();
			comboBoxDiaNacimiento.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7",
					"8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
					"25", "26", "27", "28", "29", "30", "31" }));
			comboBoxDiaNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
			comboBoxDiaNacimiento.setBounds(264, 119, 44, 22);
		}
		return comboBoxDiaNacimiento;
	}

	private JComboBox getComboBoxMesNacimiento() {
		if (comboBoxMesNacimiento == null) {
			comboBoxMesNacimiento = new JComboBox();
			comboBoxMesNacimiento.setModel(new DefaultComboBoxModel(
					new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
			comboBoxMesNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
			comboBoxMesNacimiento.setBounds(321, 119, 42, 22);
		}
		return comboBoxMesNacimiento;
	}

	private JComboBox getComboBoxAñoNacimiento() {
		if (comboBoxAñoNacimiento == null) {
			comboBoxAñoNacimiento = new JComboBox();
			comboBoxAñoNacimiento.setModel(new DefaultComboBoxModel(new String[] { "1950", "1951", "1952", "1953",
					"1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965",
					"1966", "1967", "1967", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975",
					"1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987",
					"1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999",
					"2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011",
					"2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019" }));
			comboBoxAñoNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
			comboBoxAñoNacimiento.setBounds(375, 119, 68, 22);
		}
		return comboBoxAñoNacimiento;
	}

	private JPanel getPanelOrganizador() {
		if (panelOrganizador == null) {
			panelOrganizador = new JPanel();
			panelOrganizador.setBorder(new EmptyBorder(5, 5, 5, 5));
			panelOrganizador.setLayout(new BorderLayout(0, 0));
			panelOrganizador.add(getPanelCompeticion(), BorderLayout.NORTH);
			panelOrganizador.add(getLowerPanel(), BorderLayout.SOUTH);
			panelOrganizador.add(getScCompeticiones(), BorderLayout.CENTER);
		}
		return panelOrganizador;
	}

	private JPanel getPanelCompeticion() {
		if (panelCompeticion == null) {
			panelCompeticion = new JPanel();
			panelCompeticion.setLayout(new GridLayout(0, 2, 0, 0));
			panelCompeticion.add(getLblCompeticion());
			panelCompeticion.add(getTxtCompeticion());
		}
		return panelCompeticion;
	}

	private JLabel getLblCompeticion() {
		if (lblCompeticion == null) {
			lblCompeticion = new JLabel("Competicion a listar:");
			lblCompeticion.setFont(new Font("Verdana", Font.PLAIN, 16));
		}
		return lblCompeticion;
	}

	private JTextField getTxtCompeticion() {
		if (txtCompeticion == null) {
			txtCompeticion = new JTextField();
			txtCompeticion.setColumns(10);
		}
		return txtCompeticion;
	}

	private JButton getBtnListar() {
		if (btnListar == null) {
			btnListar = new JButton("Ver");
			btnListar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					mostrar();
				}
			});
			btnListar.setFont(new Font("Verdana", Font.PLAIN, 16));
		}
		return btnListar;
	}

	protected void mostrar() {
		try {
			mostrarCompeticiones(txtCompeticion.getText());
		} catch (DataException e) {
			// algo se har� aqu�?
		}
	}

	private void mostrarCompeticiones(String id) throws DataException {
		// Nuevo tipo creado, revisadlo

		ListarInscripciones inscripciones = new ListarInscripciones();
		List<AtletaInscripcion> inscr = inscripciones.verAtletasEInscripciones(id);

		/*
		 * for (AtletaInscripcion i : inscr) { modeloInscripciones.addElement(i); }
		 */ // Repito Miguel: No puedes usar el modelo que ya existe porque
			// tiene otros
			// datos que uso yo (nerea)

	}

	private JPanel getLowerPanel() {
		if (lowerPanel == null) {
			lowerPanel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) lowerPanel.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			lowerPanel.add(getBtnListar());
		}
		return lowerPanel;
	}

	private JScrollPane getScrollPaneCompeticiones() {
		if (scrollPaneCompeticiones == null) {
			scrollPaneCompeticiones = new JScrollPane();
			scrollPaneCompeticiones.setViewportView(getListCompeticiones());
		}
		return scrollPaneCompeticiones;
	}

	private JTextField getTxtCaducidad() {
		if (txtCaducidad == null) {
			txtCaducidad = new JTextField();
			txtCaducidad.setColumns(10);
		}
		return txtCaducidad;
	}

	private JPanel getPnOrganizador() {
		if (pnOrganizador == null) {
			pnOrganizador = new JPanel();
			pnOrganizador.setBackground(Color.WHITE);
			pnOrganizador.setLayout(new BorderLayout(0, 0));
			pnOrganizador.add(getPnBtnCrearCompeticion(), BorderLayout.SOUTH);
		}
		return pnOrganizador;
	}

	private JButton getBtnCrearCompeticion() {
		if (btnCrearCompeticion == null) {
			btnCrearCompeticion = new JButton("Crear Competicion");
			btnCrearCompeticion.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnCrearCompeticion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					toNuevaCompeticion();
				}
			});
		}
		return btnCrearCompeticion;
	}

	private JPanel getPnNuevaCompeticion() {
		if (pnNuevaCompeticion == null) {
			pnNuevaCompeticion = new JPanel();
			pnNuevaCompeticion.setBackground(Color.WHITE);
			pnNuevaCompeticion.setLayout(new BorderLayout(0, 0));
			pnNuevaCompeticion.add(getPnBtnCrear(), BorderLayout.SOUTH);
			pnNuevaCompeticion.add(getLblNuevaCompeticion(), BorderLayout.NORTH);
			pnNuevaCompeticion.add(getPnCentralCompeticionNueva(), BorderLayout.CENTER);
		}
		return pnNuevaCompeticion;
	}

	private JFrame getThis() {
		return this;
	}

	private JLabel getLblNombreCompeticion() {
		if (lblNombreCompeticion == null) {
			lblNombreCompeticion = new JLabel("Nombre Competicion:");
			lblNombreCompeticion.setBounds(12, 8, 134, 17);
			lblNombreCompeticion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblNombreCompeticion;
	}

	private JTextField getTxtNombreCompeticionNueva() {
		if (txtNombreCompeticionNueva == null) {
			txtNombreCompeticionNueva = new JTextField();
			txtNombreCompeticionNueva.setBounds(12, 31, 192, 23);
			txtNombreCompeticionNueva.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtNombreCompeticionNueva.setColumns(10);
		}
		return txtNombreCompeticionNueva;
	}

	private JLabel getLblTipo() {
		if (lblTipo == null) {
			lblTipo = new JLabel("Tipo:");
			lblTipo.setBounds(12, 99, 31, 17);
			lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblTipo;
	}

	private JTextField getTxtTipoCompeticionNueva() {
		if (txtTipoCompeticionNueva == null) {
			txtTipoCompeticionNueva = new JTextField();
			txtTipoCompeticionNueva.setBounds(55, 96, 149, 23);
			txtTipoCompeticionNueva.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtTipoCompeticionNueva.setColumns(10);
		}
		return txtTipoCompeticionNueva;
	}

	private JLabel getLblKilometroskm() {
		if (lblKilometroskm == null) {
			lblKilometroskm = new JLabel("Kilometros (km):");
			lblKilometroskm.setBounds(12, 67, 101, 17);
			lblKilometroskm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblKilometroskm;
	}

	private JTextField getTxtKmCompeticionNueva() {
		if (txtKmCompeticionNueva == null) {
			txtKmCompeticionNueva = new JTextField();
			txtKmCompeticionNueva.setBounds(121, 64, 83, 23);
			txtKmCompeticionNueva.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtKmCompeticionNueva.setColumns(10);
		}
		return txtKmCompeticionNueva;
	}

	private JPanel getPnInfoNuevaCompeticion() {
		if (pnInfoNuevaCompeticion == null) {
			pnInfoNuevaCompeticion = new JPanel();
			pnInfoNuevaCompeticion.setBackground(Color.WHITE);
			pnInfoNuevaCompeticion.setLayout(null);
			pnInfoNuevaCompeticion.add(getLblNombreCompeticion());
			pnInfoNuevaCompeticion.add(getTxtNombreCompeticionNueva());
			pnInfoNuevaCompeticion.add(getLblTipo());
			pnInfoNuevaCompeticion.add(getTxtTipoCompeticionNueva());
			pnInfoNuevaCompeticion.add(getLblKilometroskm());
			pnInfoNuevaCompeticion.add(getTxtKmCompeticionNueva());
			pnInfoNuevaCompeticion.add(getLblPlazas());
			pnInfoNuevaCompeticion.add(getSpinnerPlazasCompeticionNueva());
			pnInfoNuevaCompeticion.add(getLblFecha());
			pnInfoNuevaCompeticion.add(getTxtFechaCompeticionNueva());
		}
		return pnInfoNuevaCompeticion;
	}

	private JPanel getPnBtnCrear() {
		if (pnBtnCrear == null) {
			pnBtnCrear = new JPanel();
			pnBtnCrear.setBackground(Color.WHITE);
			pnBtnCrear.add(getBtnCrear());
		}
		return pnBtnCrear;
	}

	private JLabel getLblNuevaCompeticion() {
		if (lblNuevaCompeticion == null) {
			lblNuevaCompeticion = new JLabel("Nueva Competicion");
			lblNuevaCompeticion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblNuevaCompeticion;
	}

	private JButton getBtnCrear() {
		if (btnCrear == null) {
			btnCrear = new JButton("Crear");
			btnCrear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return btnCrear;
	}

	private JLabel getLblPlazas() {
		if (lblPlazas == null) {
			lblPlazas = new JLabel("Plazas:");
			lblPlazas.setBounds(12, 197, 41, 17);
			lblPlazas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblPlazas;
	}

	private JSpinner getSpinnerPlazasCompeticionNueva() {
		if (spinnerPlazasCompeticionNueva == null) {
			spinnerPlazasCompeticionNueva = new JSpinner();
			spinnerPlazasCompeticionNueva
					.setModel(new SpinnerNumberModel(new Integer(50), new Integer(10), null, new Integer(10)));
			spinnerPlazasCompeticionNueva.setBounds(65, 195, 68, 20);
			spinnerPlazasCompeticionNueva.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return spinnerPlazasCompeticionNueva;
	}

	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha (dd/mm/aaaa):");
			lblFecha.setBounds(12, 132, 133, 17);
			lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblFecha;
	}

	private JTextField getTxtFechaCompeticionNueva() {
		if (txtFechaCompeticionNueva == null) {
			txtFechaCompeticionNueva = new JTextField();
			txtFechaCompeticionNueva.setBounds(12, 162, 116, 22);
			txtFechaCompeticionNueva.setColumns(10);
		}
		return txtFechaCompeticionNueva;
	}

	private JPanel getPnBtnCrearCompeticion() {
		if (pnBtnCrearCompeticion == null) {
			pnBtnCrearCompeticion = new JPanel();
			pnBtnCrearCompeticion.setBackground(Color.WHITE);
			pnBtnCrearCompeticion.add(getBtnCrearCompeticion());
		}
		return pnBtnCrearCompeticion;
	}

	private JPanel getPnTablaPlazos() {
		if (pnTablaPlazos == null) {
			pnTablaPlazos = new JPanel();
			pnTablaPlazos.setBackground(Color.WHITE);
			pnTablaPlazos.setLayout(new BorderLayout(0, 0));
			pnTablaPlazos.add(getScrollPanePlazos());
			pnTablaPlazos.add(getPnNuevaFilaPlazos(), BorderLayout.SOUTH);
			pnTablaPlazos.add(getLblTablaPlazos(), BorderLayout.NORTH);
		}
		return pnTablaPlazos;
	}

	private JScrollPane getScrollPanePlazos() {
		if (scrollPanePlazos == null) {
			scrollPanePlazos = new JScrollPane();
			scrollPanePlazos.setBackground(Color.WHITE);
			scrollPanePlazos.setViewportView(getTablePlazos());
		}
		return scrollPanePlazos;
	}

	private JTable getTablePlazos() {
		if (tablePlazos == null) {
			tablePlazos = new JTable();
			tablePlazos.setModel(modelTablaPlazos);
			tablePlazos.setBackground(Color.WHITE);
			tablePlazos.setEnabled(true);
			tablePlazos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		}
		return tablePlazos;
	}

	private void mostrarTablaPlazos() {
		modelTablaPlazos.addColumn("Fecha Inicio");
		modelTablaPlazos.addColumn("Fecha Fin");
		modelTablaPlazos.addColumn("Cuota(€)");
		
		for(Plazo p : plazosNuevaCompeticion) {
			modelTablaPlazos.addRow( new Object[] {Dates.toString(p.fechaInicio)
					,Dates.toString(p.fechaFin)
					, p.cuota});
		}
		
		tablePlazos.getTableHeader().setReorderingAllowed(false);
		scrollPanePlazos.setViewportView(tablePlazos);
		
	}

	private JPanel getPnCentralCompeticionNueva() {
		if (pnCentralCompeticionNueva == null) {
			pnCentralCompeticionNueva = new JPanel();
			pnCentralCompeticionNueva.setLayout(new GridLayout(1, 1, 0, 0));
			pnCentralCompeticionNueva.add(getPnInfoNuevaCompeticion());
			pnCentralCompeticionNueva.add(getPnTablaPlazos());
			pnCentralCompeticionNueva.add(getPnTablaCategorias());
		}
		return pnCentralCompeticionNueva;
	}

	private JPanel getPnTablaCategorias() {
		if (pnTablaCategorias == null) {
			pnTablaCategorias = new JPanel();
			pnTablaCategorias.setBackground(Color.WHITE);
			pnTablaCategorias.setLayout(new BorderLayout(0, 0));
			pnTablaCategorias.add(getLblCategorias(), BorderLayout.NORTH);
		}
		return pnTablaCategorias;
	}

	private JLabel getLblTablaPlazos() {
		if (lblTablaPlazos == null) {
			lblTablaPlazos = new JLabel("Plazos");
			lblTablaPlazos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblTablaPlazos;
	}

	private JPanel getPnNuevaFilaPlazos() {
		if (pnNuevaFilaPlazos == null) {
			pnNuevaFilaPlazos = new JPanel();
			pnNuevaFilaPlazos.setBackground(Color.WHITE);
			pnNuevaFilaPlazos.add(getButtonNuevaFilaPlazo());
			pnNuevaFilaPlazos.add(getButtonBorrarFilaPlazo());
		}
		return pnNuevaFilaPlazos;
	}

	private JButton getButtonNuevaFilaPlazo() {
		if (buttonNuevaFilaPlazo == null) {
			buttonNuevaFilaPlazo = new JButton("+");
		}
		return buttonNuevaFilaPlazo;
	}

	private JButton getButtonBorrarFilaPlazo() {
		if (buttonBorrarFilaPlazo == null) {
			buttonBorrarFilaPlazo = new JButton("-");
		}
		return buttonBorrarFilaPlazo;
	}

	private JLabel getLblCategorias() {
		if (lblCategorias == null) {
			lblCategorias = new JLabel("Categorias");
			lblCategorias.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblCategorias;
	}
}
