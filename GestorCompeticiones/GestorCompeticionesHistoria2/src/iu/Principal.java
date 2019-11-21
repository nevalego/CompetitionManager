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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ComboBoxModel;
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
import logic.model.Resultados;
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
	private JPanel pnPago;
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
	private DefaultTableModel modelCompeticiones = new DefaultTableModel();
	private List<Competicion> competiciones = new ArrayList<>();
	private DefaultTableModel modelInscripciones = new DefaultTableModel();
	private List<Inscripcion> inscripciones = new ArrayList<>();
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
	private JComboBox<String> comboBoxSexo;
	private JLabel lblFechaNacimiento;
	private JComboBox<Integer> comboBoxDiaNacimiento;
	private JComboBox<Integer> comboBoxMesNacimiento;
	private JComboBox<Integer> comboBoxAñoNacimiento;
	private JPanel panelOrganizador;
	private JPanel panelCompeticion;
	private JLabel lblCompeticion;
	private JTextField txtCompeticion;
	private JButton btnListar;
	private JPanel lowerPanel;
	// -------------------------Miguel
	private JScrollPane scCompeticiones;
	private JList<AtletaInscripcion> list;
	private DefaultListModel<AtletaInscripcion> modeloInscripciones = new DefaultListModel<>();
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
	private JPanel pnBtnCrearCompeticion;
	private JPanel pnTablaPlazos;
	private JScrollPane scrollPanePlazos;
	private JTable tablePlazos;
	private List<Plazo> plazosNuevaCompeticion = new ArrayList<>();
	private Competicion competicionNueva = new Competicion();
	private DefaultTableModel modelTablaPlazos = new DefaultTableModel();
	private List<Competicion> todasCompeticiones = new ArrayList<>();
	private DefaultTableModel modelTodasCompeticiones = new DefaultTableModel();
	private List<Inscripcion> todasInscripciones = new ArrayList<>();
	private DefaultTableModel modelTodasInscripciones = new DefaultTableModel();
	private List<Resultados> resultados = new ArrayList<>();
	private DefaultTableModel modelResultados = new DefaultTableModel();
	private JPanel pnCentralCompeticionNueva;
	private JPanel pnTablaCategorias;
	private JLabel lblTablaPlazos;
	private JPanel pnNuevaFilaPlazos;
	private JButton buttonNuevaFilaPlazo;
	private JButton buttonBorrarFilaPlazo;
	private JLabel lblCategorias;
	private JPanel pnNuevaFilaCategorias;
	private JButton buttonNuevaFilaCategoria;
	private JButton buttonBorrarFilaCategoria;
	private JScrollPane scrollPaneCategorias;
	private JTable tableCategorias;
	private JTable tableCompeticionesAtleta;
	private JTable tableInscripcionesAtleta;
	private JButton btnCancelar;
	private JPanel pnInfoPagoInscripcion;
	private int inscripcionSeleccionada = -1;
	private JComboBox<Integer> comboBoxDiaCaducidad;
	private JComboBox<Integer> comboBoxMesCaducidad;
	private JComboBox<Integer> comboBoxAnioCaducidad;
	private JLabel lblInfoInscripcionPago;
	private JComboBox<Integer> comboBoxDiaNuevaComp;
	private JComboBox<Integer> comboBoxMesNuevaComp;
	private JComboBox<Integer> comboBoxAnioNuevaComp;
	private JPanel pnTablasOrganizador;
	private JLabel lblMenuOrganizador;
	private JScrollPane scrollPaneTablaOrganIns;
	private JScrollPane scrollPaneTablaOrganComp;
	private JTable tableCompeticionesOrganizador;
	private JTable tableIInscripcionesOrganizador;
	private JLabel lblTodasCompeticiones;
	private JLabel lblTodasInscripciones;
	private JPanel pnTablaCompeticionesOrgan;
	private JPanel pnTablaInscripcionesOrgan;
	private JPanel pnOrganizadorCompeticion;
	private JButton btnCargarPagos;
	private JButton btnCargarResultados;
	private JPanel pnAtletaHistorial;
	private JButton btnVerMiHistorial;
	private JPanel pnResultados;
	private JLabel lblMenuOrganizadorResultados;
	private JScrollPane scrollPaneTablaResultados;
	private JTable tableResultados;

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
		setBounds(100, 100, 848, 443);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnCards(), BorderLayout.CENTER);
		contentPane.add(getPnButtons(), BorderLayout.SOUTH);
		setContentPane(contentPane);
		seleccionarPagoTransferencia();
		loadCompeticiones();
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
			btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
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
			lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblEmail;
	}

	private JTextField getTxtFieldEmail() {
		if (txtFieldEmail == null) {
			txtFieldEmail = new JTextField();
			txtFieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtFieldEmail.setColumns(10);
		}
		return txtFieldEmail;
	}

	private JButton getBtnIniciaSesin() {
		if (btnIniciaSesin == null) {
			btnIniciaSesin = new JButton("Iniciar sesi\u00F3n");
			btnIniciaSesin.setFont(new Font("Tahoma", Font.PLAIN, 14));
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
				JOptionPane.showMessageDialog(this, "Tu email no esta registrado en ningun atleta");
			else
				toAtletaMenu();
		} catch (DataException e) {
			JOptionPane.showMessageDialog(this, "Error en inicio de sesion");
		}
	}

	private JButton getBtnRegistrarme() {
		if (btnRegistrarme == null) {
			btnRegistrarme = new JButton("Registrarme");
			btnRegistrarme.setFont(new Font("Tahoma", Font.PLAIN, 14));
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
			pnCards.add(getPnResultados(), "resultados");
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
			pnAtletaMenu.add(getPnAtletaHistorial(), BorderLayout.NORTH);
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
			lblMenuAtleta.setHorizontalAlignment(SwingConstants.LEFT);
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
			lblListaDeCompeticiones.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblListaDeCompeticiones;
	}

	private JLabel getLblListaDeInscripciones() {
		if (lblListaDeInscripciones == null) {
			lblListaDeInscripciones = new JLabel("Lista de mis Inscripciones ");
			lblListaDeInscripciones.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblListaDeInscripciones;
	}

	private JScrollPane getScCompeticiones() {
		if (scrollPaneCompeticiones == null) {
			scrollPaneCompeticiones = new JScrollPane();
			scrollPaneCompeticiones.setViewportView(getTableCompeticionesAtleta());
		}
		return scrollPaneCompeticiones;
	}

	private JScrollPane getScrollPaneInscripciones() {
		if (scrollPaneInscripciones == null) {
			scrollPaneInscripciones = new JScrollPane();
			scrollPaneInscripciones.setViewportView(getTableInscripcionesAtleta());
		}
		return scrollPaneInscripciones;
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
			btnInscribirme.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnInscribirme.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int row = tableCompeticionesAtleta.getSelectedRow();
					if (row != -1) {
						inscribirse(competiciones.get(row));
					} else
						JOptionPane.showMessageDialog(getThis(), "No has seleccionado la competicion");
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
			pnBtnPagarInscripcion.add(getBtnCancelar());
		}
		return pnBtnPagarInscripcion;
	}

	private JButton getBtnPagar() {
		if (btnPagar == null) {
			btnPagar = new JButton("Pagar");
			btnPagar.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnPagar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					if (inscripcionSeleccionada != -1) {
						Inscripcion ins = inscripciones.get(inscripcionSeleccionada);
						// PagoInscripcion pago = new PagoInscripcion();
						// Inscripcion ins = pago.obtenerInscripcion(atleta.getId(),
						// inscripcion.getId());

						if (ins.estado.equals("ABONADA")) {
							JOptionPane.showMessageDialog(getThis(), "La inscripcion seleccionada ya ha sido abonada");
						} else {

							cardNumber++;
							toPagoAtleta();
						}
					} else
						JOptionPane.showMessageDialog(getThis(), "No has seleccionado ninguna inscripcion para pagar");
				}
			});
		}
		return btnPagar;

	}

	protected void pagar(Inscripcion inscripcion) {

		PagoInscripcion pago = new PagoInscripcion();
		Inscripcion ins = null;
		Date plazoMaxPago = null;
		try {
			ins = pago.obtenerInscripcion(atleta.getId(), inscripcion.getId());
			ins.fechaPago = Dates.now();
			ins.medioPago = "Tarjeta";
			plazoMaxPago = Dates.addDays(ins.fecha, 2);// 48 h tras
														// la
														// inscripcion

		} catch (DataException e) {
			JOptionPane.showMessageDialog(this, "Error al obtener inscripcion");
		}
		// Fecha Caducidad
		int dia = (Integer) comboBoxDiaCaducidad.getSelectedItem();
		int mes = (Integer) comboBoxMesCaducidad.getSelectedItem();
		int anio = (Integer) comboBoxAnioCaducidad.getSelectedItem();
		Date caducidad = new Date(anio - 1900, mes - 1, dia);

		if (txtNumeroTarjeta.getText().length() != 16)
			JOptionPane.showMessageDialog(this, "La longitud del número de la tarjeta "
					+ txtNumeroTarjeta.getText().length() + " no es correcta. Dede ser 16");
		else if (Dates.isAfter(Dates.now(), caducidad))
			JOptionPane.showMessageDialog(this, "La tarjeta ha sobrepasado su fecha de expiración");
		else {

			if (ins.fechaPago.after(plazoMaxPago)) {
				JOptionPane.showMessageDialog(this, "La fecha de pago se encuentra fuera del periodo de pago");
			} else {
				Plazo plazo = null;
				try {
					plazo = pago.obtenerPlazo(ins);

					ins.cantidad = (int) plazo.cuota;
					ins.fechaPago = Dates.now();
					ins.fechaModificacion = Dates.now();
					ListarCompeticiones verNombre = new ListarCompeticiones();
					ins.nombreCompeticion = verNombre.verCompeticionInscripcion(ins);
					ins.nombreAtleta = atleta.nombre;
				} catch (DataException e) {
					JOptionPane.showMessageDialog(this, "Error al obtener plazo o competicion");
				}
				try {
					pago.pagarInscripcion(ins);
					JOptionPane.showMessageDialog(this, "Su pago se realizado con exito. Generando justificante ...");
					pago.generarJustificante(ins);
					toAtletaMenu();
				} catch (DataException e) {
					JOptionPane.showMessageDialog(this, "Error al pagar inscripcion");
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
			pnMetodoPago.add(getPnMetodosPago(), BorderLayout.CENTER);
			pnMetodoPago.add(getPnInfoPagoInscripcion(), BorderLayout.NORTH);
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
			pnMetodosPago.setLayout(new BorderLayout(0, 0));
			pnMetodosPago.add(getPnRadioButtonsPago(), BorderLayout.WEST);
			pnMetodosPago.add(getPnInfoPago());
			pnMetodosPago.add(getLblMetodosDePago(), BorderLayout.NORTH);
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
				+ "\nEl plazo de pago es de 48 horas tras la " + "fecha de inscripcion.");
	}

	private JRadioButton getRdbtnPagoPorTarjeta() {
		if (rdbtnPagoPorTarjeta == null) {
			rdbtnPagoPorTarjeta = new JRadioButton("Pago por Tarjeta Credito");
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
			pnInfoPago.add(getPnPago(), BorderLayout.CENTER);
		}
		return pnInfoPago;
	}

	private JButton getBtnPagarTarjeta() {
		if (btnPagarTarjeta == null) {
			btnPagarTarjeta = new JButton("Pagar con tarjeta");
			btnPagarTarjeta.setBounds(103, 166, 196, 23);
			btnPagarTarjeta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (checkTarjeta()) {
						Inscripcion ins = inscripciones.get(inscripcionSeleccionada);
						pagar(ins);
					} else {
						JOptionPane.showMessageDialog(getThis(), "Rellena todos los campos de la tarjeta");
					}

				}
			});
			btnPagarTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnPagarTarjeta.setBackground(new Color(240, 240, 240));
		}
		return btnPagarTarjeta;
	}

	protected boolean checkTarjeta() {
		if (txtNombreTarjeta.getText().equals("") || txtNumeroTarjeta.getText().equals("")
				|| txtCodigoTarjeta.getText().equals(""))
			return false;
		return true;
	}

	private JLabel getLblMetodoDePago() {
		if (lblMetodoDePago == null) {
			lblMetodoDePago = new JLabel("Metodo de Pago");
			lblMetodoDePago.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lblMetodoDePago;
	}

	private JPanel getPnPago() {
		if (pnPago == null) {
			pnPago = new JPanel();
			pnPago.setBackground(Color.WHITE);
			pnPago.setLayout(new BorderLayout(0, 0));
			pnPago.add(getTxtpnInformacinPago(), BorderLayout.NORTH);
			pnPago.add(getPnTarjetaCredito());
		}
		return pnPago;
	}

	private JTextPane getTxtpnInformacinPago() {
		if (txtpnInformacinPago == null) {
			txtpnInformacinPago = new JTextPane();
			txtpnInformacinPago.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtpnInformacinPago.setText("Informacion Pago");
		}
		return txtpnInformacinPago;
	}

	private JPanel getPnTarjetaCredito() {
		if (pnTarjetaCredito == null) {
			pnTarjetaCredito = new JPanel();
			pnTarjetaCredito.setBackground(Color.WHITE);
			pnTarjetaCredito.setLayout(null);
			pnTarjetaCredito.add(getLblNombreTarjeta());
			pnTarjetaCredito.add(getTxtNombreTarjeta());
			pnTarjetaCredito.add(getLblNmero());
			pnTarjetaCredito.add(getTxtNumeroTarjeta());
			pnTarjetaCredito.add(getLblFechaDeCaducidad());
			pnTarjetaCredito.add(getLblCdigoVerificacin());
			pnTarjetaCredito.add(getTxtCodigoTarjeta());
			pnTarjetaCredito.add(getBtnPagarTarjeta());
			pnTarjetaCredito.add(getComboBoxDiaCaducidad());
			pnTarjetaCredito.add(getComboBoxMesCaducidad());
			pnTarjetaCredito.add(getComboBoxAnioCaducidad());
			pnTarjetaCredito.setVisible(false);
		}
		return pnTarjetaCredito;
	}

	private JLabel getLblNombreTarjeta() {
		if (lblNombreTarjeta == null) {
			lblNombreTarjeta = new JLabel("Nombre Tarjeta:");
			lblNombreTarjeta.setBounds(36, 22, 101, 17);
			lblNombreTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblNombreTarjeta;
	}

	private JTextField getTxtNombreTarjeta() {
		if (txtNombreTarjeta == null) {
			txtNombreTarjeta = new JTextField();
			txtNombreTarjeta.setBounds(149, 19, 150, 23);
			txtNombreTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtNombreTarjeta.setColumns(10);
		}
		return txtNombreTarjeta;
	}

	private JLabel getLblNmero() {
		if (lblNmero == null) {
			lblNmero = new JLabel("N\u00FAmero Tarjeta:");
			lblNmero.setBounds(36, 58, 101, 17);
			lblNmero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblNmero;
	}

	private JTextField getTxtNumeroTarjeta() {
		if (txtNumeroTarjeta == null) {
			txtNumeroTarjeta = new JTextField();
			txtNumeroTarjeta.setBounds(149, 55, 193, 23);
			txtNumeroTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtNumeroTarjeta.setColumns(10);
		}
		return txtNumeroTarjeta;
	}

	private JLabel getLblFechaDeCaducidad() {
		if (lblFechaDeCaducidad == null) {
			lblFechaDeCaducidad = new JLabel("Caducidad (dd/mm/aaaa):");
			lblFechaDeCaducidad.setBounds(36, 94, 161, 17);
			lblFechaDeCaducidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblFechaDeCaducidad;
	}

	private JLabel getLblCdigoVerificacin() {
		if (lblCdigoVerificacin == null) {
			lblCdigoVerificacin = new JLabel("C\u00F3digo Verificaci\u00F3n:");
			lblCdigoVerificacin.setBounds(36, 127, 131, 17);
			lblCdigoVerificacin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblCdigoVerificacin;
	}

	private JTextField getTxtCodigoTarjeta() {
		if (txtCodigoTarjeta == null) {
			txtCodigoTarjeta = new JTextField();
			txtCodigoTarjeta.setBounds(179, 124, 120, 23);
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
		} else if (cardNumber == 4) {// Panel Menu Organizador
			toFirst();
		} else if (cardNumber == 5) { // Panel Nueva Competicion
			toOrganizadorMenu();
		} else if (cardNumber == 6) { // Panel Resultados Competicion
			toOrganizadorMenu();
		} else if(cardNumber == 7) { // Panel Pagos Transferencia Competicion
			// toPagos();
		}

	}

	protected void toOrganizadorMenu() {
		loadCompeticionesOrganizador();
		loadInscripcionesOrganizador();
		pnButtons.setVisible(true);
		cardNumber = 4;
		((CardLayout) pnCards.getLayout()).show(pnCards, "organizadormenu");
	}

	private void loadCompeticionesOrganizador() {

		tableCompeticionesOrganizador.removeAll();
		
		ListarCompeticiones listarCompeticiones = new ListarCompeticiones();
		try {
			todasCompeticiones = listarCompeticiones.verCompeticiones();
			Object[][] m = new Object[todasCompeticiones.size()][todasCompeticiones.size()];

			for (int i = 0; i < todasCompeticiones.size(); i++) {
				m[i] = new Object[] { todasCompeticiones.get(i).nombre, todasCompeticiones.get(i).fecha,
						todasCompeticiones.get(i).tipo, todasCompeticiones.get(i).km, todasCompeticiones.get(i).plazas };
			}

			modelTodasCompeticiones.setDataVector(m,
					new String[] { "Competicion", "Fecha", "Tipo", "Kilometros", "Plazas" });

			tableCompeticionesOrganizador.getTableHeader().setReorderingAllowed(false);
			scrollPaneTablaOrganComp.setViewportView(tableCompeticionesOrganizador);
			tableCompeticionesOrganizador.revalidate();
			tableCompeticionesOrganizador.repaint();

		} catch (DataException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

	}

	private void loadInscripcionesOrganizador() {

		tableIInscripcionesOrganizador.removeAll();
		
		ListarInscripciones listar = new ListarInscripciones();
		ListarCompeticiones listarCompeticiones = new ListarCompeticiones();
		try {
			todasInscripciones = listar.verInscripciones();
			Object[][] m = new Object[todasInscripciones.size()][todasInscripciones.size()];

			for (int i = 0; i < todasInscripciones.size(); i++) {
				String nombreCompeticion = listarCompeticiones.verCompeticionInscripcion(todasInscripciones.get(i));
				m[i] = new Object[] {nombreCompeticion, todasInscripciones.get(i).fecha,
						todasInscripciones.get(i).estado};
			}

			modelTodasInscripciones.setDataVector(m,
					new String[] { "Competicion", "Fecha", "Estado" });

			tableIInscripcionesOrganizador.getTableHeader().setReorderingAllowed(false);
			scrollPaneTablaOrganIns.setViewportView(tableIInscripcionesOrganizador);
			tableIInscripcionesOrganizador.revalidate();
			tableIInscripcionesOrganizador.repaint();

		} catch (DataException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

	}
	
	private void toAtletaMenu() {

		loadCompeticiones();
		loadInscripciones();
		pnButtons.setVisible(true);
		lblMenuAtleta.setText("Menu Atleta " + atleta.nombre + " " + atleta.apellidos);
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

		tableInscripcionesAtleta.removeAll();
		tableCompeticionesAtleta.removeAll();
		tablePlazos.removeAll();

		ListarCompeticiones listarCompeticiones = new ListarCompeticiones();
		try {
			competiciones = listarCompeticiones.verCompeticionesDisponibles();
			Object[][] m = new Object[competiciones.size()][competiciones.size()];

			for (int i = 0; i < competiciones.size(); i++) {
				m[i] = new Object[] { competiciones.get(i).nombre, competiciones.get(i).fecha,
						competiciones.get(i).tipo, competiciones.get(i).km, competiciones.get(i).plazas };
			}

			modelCompeticiones.setDataVector(m,
					new String[] { "Competicion", "Fecha", "Tipo", "Kilometros", "Plazas" });

			tableCompeticionesAtleta.getTableHeader().setReorderingAllowed(false);
			scrollPaneCompeticiones.setViewportView(tableCompeticionesAtleta);
			tableCompeticionesAtleta.revalidate();
			tableCompeticionesAtleta.repaint();

		} catch (DataException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

	}

	private void loadInscripciones() {

		tableInscripcionesAtleta.removeAll();
		tableCompeticionesAtleta.removeAll();
		tablePlazos.removeAll();

		ListarInscripciones listarInscripciones = new ListarInscripciones();
		ListarCompeticiones listarCompeticiones = new ListarCompeticiones();
		try {
			inscripciones = listarInscripciones.verInscripcionesAtleta(atleta.getId());
			Object[][] ins = new Object[inscripciones.size()][inscripciones.size()];

			for (int i = 0; i < inscripciones.size(); i++) {
				String nombre = listarCompeticiones.verCompeticionInscripcion(inscripciones.get(i));
				ins[i] = new String[] { nombre, inscripciones.get(i).fecha.toString(), inscripciones.get(i).estado };
			}

			modelInscripciones.setDataVector(ins, new String[] { "Competicion", "Fecha", "Estado" });

			tableInscripcionesAtleta.getTableHeader().setReorderingAllowed(false);
			scrollPaneInscripciones.setViewportView(tableInscripcionesAtleta);

			tableCompeticionesAtleta.revalidate();
			tableCompeticionesAtleta.repaint();

		} catch (DataException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

	}

	private void toFirst() {

		pnButtons.setVisible(false);
		cardNumber = 0;
		((CardLayout) pnCards.getLayout()).first(pnCards);
	}

	private void toPagoAtleta() {
		lblMenAtletaPago.setText("Menu Atleta " + atleta.nombre + " " + atleta.apellidos + ": Pago Inscripcion");
		Inscripcion ins = inscripciones.get(inscripcionSeleccionada);

		ListarCompeticiones verNombre = new ListarCompeticiones();
		try {
			lblInfoInscripcionPago.setText("Inscripcion del atleta " + atleta.nombre + " " + atleta.apellidos
					+ " en la competicion " + verNombre.verCompeticionInscripcion(ins));
		} catch (DataException e) {
			JOptionPane.showMessageDialog(this, "Error al cargar la información de pago");
		}
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

	private JComboBox<String> getComboBoxSexo() {
		if (comboBoxSexo == null) {
			comboBoxSexo = new JComboBox<String>();
			comboBoxSexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			comboBoxSexo.setModel(new DefaultComboBoxModel<String>(new String[] { "Masculino", "Femenino" }));
			comboBoxSexo.setBounds(290, 37, 116, 19);
		}
		return comboBoxSexo;
	}

	private JLabel getLblFechaNacimiento() {
		if (lblFechaNacimiento == null) {
			lblFechaNacimiento = new JLabel("Fecha nacimiento (dd/mm/aaaa):");
			lblFechaNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblFechaNacimiento.setBounds(53, 122, 224, 19);
		}
		return lblFechaNacimiento;
	}

	private JComboBox<Integer> getComboBoxDiaNacimiento() {
		if (comboBoxDiaNacimiento == null) {
			comboBoxDiaNacimiento = new JComboBox<Integer>();
			comboBoxDiaNacimiento.setModel(getModelDias());
			comboBoxDiaNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
			comboBoxDiaNacimiento.setBounds(290, 120, 61, 22);
		}
		return comboBoxDiaNacimiento;
	}

	private JComboBox<Integer> getComboBoxMesNacimiento() {
		if (comboBoxMesNacimiento == null) {
			comboBoxMesNacimiento = new JComboBox<Integer>();
			comboBoxMesNacimiento.setModel(getModelMeses());
			comboBoxMesNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
			comboBoxMesNacimiento.setBounds(373, 120, 61, 21);
		}
		return comboBoxMesNacimiento;
	}

	private JComboBox<Integer> getComboBoxAñoNacimiento() {
		if (comboBoxAñoNacimiento == null) {
			comboBoxAñoNacimiento = new JComboBox<Integer>();
			comboBoxAñoNacimiento.setModel(getModelAnios());
			comboBoxAñoNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
			comboBoxAñoNacimiento.setBounds(457, 120, 77, 21);
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
			JOptionPane.showMessageDialog(this, "No se ha podido mostrar las competiciones");
		}
	}

	private void mostrarCompeticiones(String id) throws DataException {

		ListarInscripciones inscripciones = new ListarInscripciones();
		// List<AtletaInscripcion> inscr = inscripciones.verAtletasEInscripciones(id);

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
			scrollPaneCompeticiones.setViewportView(getTableCompeticionesAtleta());
		}
		return scrollPaneCompeticiones;
	}

	private JPanel getPnOrganizador() {
		if (pnOrganizador == null) {
			pnOrganizador = new JPanel();
			pnOrganizador.setBackground(Color.WHITE);
			pnOrganizador.setLayout(new BorderLayout(0, 0));
			pnOrganizador.add(getPnBtnCrearCompeticion(), BorderLayout.SOUTH);
			pnOrganizador.add(getPnTablasOrganizador(), BorderLayout.CENTER);
			pnOrganizador.add(getLblMenuOrganizador(), BorderLayout.NORTH);
		}
		return pnOrganizador;
	}

	private JButton getBtnCrearCompeticion() {
		if (btnCrearCompeticion == null) {
			btnCrearCompeticion = new JButton("Crear Competicion");
			btnCrearCompeticion.setFont(new Font("Dialog", Font.PLAIN, 14));
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
			txtNombreCompeticionNueva.setBounds(12, 31, 242, 23);
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
			txtTipoCompeticionNueva.setBounds(55, 96, 199, 23);
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
			txtKmCompeticionNueva.setBounds(121, 64, 133, 23);
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
			pnInfoNuevaCompeticion.add(getComboBoxDiaNuevaComp());
			pnInfoNuevaCompeticion.add(getComboBoxMesNuevaComp());
			pnInfoNuevaCompeticion.add(getComboBoxAnioNuevaComp());
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
			lblNuevaCompeticion = new JLabel("Menu Organizador: Nueva Competicion");
			lblNuevaCompeticion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblNuevaCompeticion;
	}

	private JButton getBtnCrear() {
		if (btnCrear == null) {
			btnCrear = new JButton("Crear");
			btnCrear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (camposVacios())
						JOptionPane.showMessageDialog(getThis(), "Debes completar los campos de la competicion");
					else
						crearCompeticionNueva();
				}
			});
			btnCrear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return btnCrear;
	}

	protected void crearCompeticionNueva() {

		leerPlazosTabla();
		NuevaCompeticion nueva = new NuevaCompeticion();

		competicionNueva.nombre = txtNombreCompeticionNueva.getText();
		competicionNueva.tipo = txtTipoCompeticionNueva.getText();
		competicionNueva.km = ((Integer) spinnerPlazasCompeticionNueva.getValue());
		int dia = (Integer) comboBoxDiaNuevaComp.getSelectedItem();
		int mes = (Integer) comboBoxMesNuevaComp.getSelectedItem();
		int anio = (Integer) comboBoxAnioNuevaComp.getSelectedItem();

		competicionNueva.fecha = new Date(anio, mes, dia);
		competicionNueva.plazas = (int) spinnerPlazasCompeticionNueva.getValue();

		if (competicionNueva.fecha.before(Dates.now())) {
			JOptionPane.showMessageDialog(this, "La fecha de la competicion no puede ser anterior al presente");
		} else {
			try {
				nueva.crearCompeticion(competicionNueva);
				if (plazosCorrectos()) {
					for (Plazo plazo : plazosNuevaCompeticion)
						nueva.añadirPlazoCompeticion(competicionNueva.id, plazo);
					tablePlazos.revalidate();
					tablePlazos.repaint();
					JOptionPane.showMessageDialog(this, "Competicion Creada");
					toOrganizadorMenu();
				} else
					JOptionPane.showMessageDialog(this, "Fechas incorrectas");

			} catch (DataException e) {
				JOptionPane.showMessageDialog(this, "Error al crear la competicion");
			}
		}

	}

	private boolean plazosCorrectos() {
		for (Plazo plazo : plazosNuevaCompeticion) {
			if (Dates.isBefore(plazo.fechaFin, plazo.fechaInicio))
				return false;
			if (Dates.isAfter(plazo.fechaFin, competicionNueva.fecha))
				return false;
		}
		return true;
	}

	private void leerPlazosTabla() {

		plazosNuevaCompeticion.removeAll(plazosNuevaCompeticion);
		for (int i = 0; i < tablePlazos.getRowCount(); i++) {

			Plazo plazo = new Plazo();
			int anio = 2000 + (Integer.parseInt(((String) modelTablaPlazos.getValueAt(i, 0)).split("/")[2]));
			int mes = (Integer.parseInt(((String) modelTablaPlazos.getValueAt(i, 0)).split("/")[1]));
			int dia = (Integer.parseInt(((String) modelTablaPlazos.getValueAt(i, 0)).split("/")[0]));

			plazo.fechaInicio = new Date(anio, mes, dia);

			anio = 2000 + (Integer.parseInt(((String) modelTablaPlazos.getValueAt(i, 1)).split("/")[2]));
			mes = (Integer.parseInt(((String) modelTablaPlazos.getValueAt(i, 1)).split("/")[1]));
			dia = (Integer.parseInt(((String) modelTablaPlazos.getValueAt(i, 1)).split("/")[0]));

			plazo.fechaFin = new Date(anio, mes, dia);
			// if (i == 0)
			// plazo.cuota += (int) modelTablaPlazos.getValueAt(i, 2);
			// if (i == 1)
			plazo.cuota += Integer.parseInt((String) (modelTablaPlazos.getValueAt(i, 2)));
			plazosNuevaCompeticion.add(plazo);
		}

	}

	protected boolean camposVacios() {
		return txtNombreCompeticionNueva.getText().equals("") || txtTipoCompeticionNueva.getText().equals("");
	}

	private JLabel getLblPlazas() {
		if (lblPlazas == null) {
			lblPlazas = new JLabel("Plazas:");
			lblPlazas.setBounds(12, 222, 58, 17);
			lblPlazas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblPlazas;
	}

	private JSpinner getSpinnerPlazasCompeticionNueva() {
		if (spinnerPlazasCompeticionNueva == null) {
			spinnerPlazasCompeticionNueva = new JSpinner();
			spinnerPlazasCompeticionNueva
					.setModel(new SpinnerNumberModel(new Integer(50), new Integer(10), null, new Integer(10)));
			spinnerPlazasCompeticionNueva.setBounds(95, 212, 70, 30);
			spinnerPlazasCompeticionNueva.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return spinnerPlazasCompeticionNueva;
	}

	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha (dd/mm/aaaa):");
			lblFecha.setBounds(12, 132, 187, 23);
			lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblFecha;
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
		modelTablaPlazos.addColumn("Fecha inicio");
		modelTablaPlazos.addColumn("Fecha fin");
		modelTablaPlazos.addColumn("Cuota");

		for (Plazo p : plazosNuevaCompeticion) {
			modelTablaPlazos.addRow(new Object[] { Dates.toString(p.fechaInicio), Dates.toString(p.fechaFin),
					String.valueOf(p.cuota) });
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
			pnTablaCategorias.add(getPnNuevaFilaCategorias(), BorderLayout.SOUTH);
			pnTablaCategorias.add(getScrollPaneCategorias(), BorderLayout.CENTER);
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
			buttonNuevaFilaPlazo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					nuevaFilaTablaPlazos();
				}
			});
		}
		return buttonNuevaFilaPlazo;
	}

	protected void nuevaFilaTablaPlazos() {
		modelTablaPlazos.addRow(new Object[] { "", "", "" });
	}

	private JButton getButtonBorrarFilaPlazo() {
		if (buttonBorrarFilaPlazo == null) {
			buttonBorrarFilaPlazo = new JButton("-");
			buttonBorrarFilaPlazo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					modelTablaPlazos.removeRow(tablePlazos.getSelectedRow());
				}
			});
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

	private JPanel getPnNuevaFilaCategorias() {
		if (pnNuevaFilaCategorias == null) {
			pnNuevaFilaCategorias = new JPanel();
			pnNuevaFilaCategorias.setBackground(Color.WHITE);
			pnNuevaFilaCategorias.add(getButtonNuevaFilaCategoria());
			pnNuevaFilaCategorias.add(getButtonBorrarFilaCategoria());
		}
		return pnNuevaFilaCategorias;
	}

	private JButton getButtonNuevaFilaCategoria() {
		if (buttonNuevaFilaCategoria == null) {
			buttonNuevaFilaCategoria = new JButton("+");
		}
		return buttonNuevaFilaCategoria;
	}

	private JButton getButtonBorrarFilaCategoria() {
		if (buttonBorrarFilaCategoria == null) {
			buttonBorrarFilaCategoria = new JButton("-");
		}
		return buttonBorrarFilaCategoria;
	}

	private JScrollPane getScrollPaneCategorias() {
		if (scrollPaneCategorias == null) {
			scrollPaneCategorias = new JScrollPane();
			scrollPaneCategorias.setViewportView(getTableCategorias());
		}
		return scrollPaneCategorias;
	}

	private JTable getTableCategorias() {
		if (tableCategorias == null) {
			tableCategorias = new JTable();
		}
		return tableCategorias;
	}

	private JTable getTableCompeticionesAtleta() {
		if (tableCompeticionesAtleta == null) {
			tableCompeticionesAtleta = new JTable();
			tableCompeticionesAtleta.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tableCompeticionesAtleta.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			tableCompeticionesAtleta.setModel(modelCompeticiones);
			tableCompeticionesAtleta.setRowSelectionAllowed(true);
		}
		return tableCompeticionesAtleta;
	}

	private JTable getTableInscripcionesAtleta() {
		if (tableInscripcionesAtleta == null) {
			tableInscripcionesAtleta = new JTable();
			tableInscripcionesAtleta.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tableInscripcionesAtleta.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					inscripcionSeleccionada = tableInscripcionesAtleta.rowAtPoint(e.getPoint());
				}
			});
			tableInscripcionesAtleta.setModel(modelInscripciones);
			tableCompeticionesAtleta.setRowSelectionAllowed(true);
		}
		return tableInscripcionesAtleta;
	}

	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return btnCancelar;
	}

	private JPanel getPnInfoPagoInscripcion() {
		if (pnInfoPagoInscripcion == null) {
			pnInfoPagoInscripcion = new JPanel();
			pnInfoPagoInscripcion.setBackground(Color.WHITE);
			pnInfoPagoInscripcion.setLayout(new BorderLayout(0, 0));
			pnInfoPagoInscripcion.add(getLblInfoInscripcionPago(), BorderLayout.NORTH);
		}
		return pnInfoPagoInscripcion;
	}

	private JComboBox<Integer> getComboBoxDiaCaducidad() {
		if (comboBoxDiaCaducidad == null) {
			comboBoxDiaCaducidad = new JComboBox<Integer>();
			comboBoxDiaCaducidad.setBounds(209, 91, 53, 23);
			comboBoxDiaCaducidad.setModel(getModelDias());
		}
		return comboBoxDiaCaducidad;
	}

	private ComboBoxModel<Integer> getModelDias() {
		Integer[] dias = new Integer[31];
		for (int i = 0; i < dias.length; i++) {
			dias[i] = i + 1;
		}
		return new DefaultComboBoxModel<Integer>(dias);
	}

	private JComboBox<Integer> getComboBoxMesCaducidad() {
		if (comboBoxMesCaducidad == null) {
			comboBoxMesCaducidad = new JComboBox<Integer>();
			comboBoxMesCaducidad.setBounds(274, 91, 58, 23);
			comboBoxMesCaducidad.setModel(getModelMeses());
		}
		return comboBoxMesCaducidad;
	}

	private ComboBoxModel<Integer> getModelMeses() {
		Integer[] meses = new Integer[12];
		for (int i = 0; i < meses.length; i++) {
			meses[i] = i + 1;
		}
		return new DefaultComboBoxModel<Integer>(meses);
	}

	private JComboBox<Integer> getComboBoxAnioCaducidad() {
		if (comboBoxAnioCaducidad == null) {
			comboBoxAnioCaducidad = new JComboBox<Integer>();
			comboBoxAnioCaducidad.setBounds(344, 92, 60, 23);
			comboBoxAnioCaducidad.setModel(getModelAnios());
		}
		return comboBoxAnioCaducidad;
	}

	private ComboBoxModel<Integer> getModelAnios() {
		Integer[] anios = new Integer[2030 - 1950];
		int index = 0;
		for (int a = 1950; a < 2030; a++) {
			anios[index] = a + 1;
			index++;
		}
		return new DefaultComboBoxModel<Integer>(anios);
	}

	private JLabel getLblInfoInscripcionPago() {
		if (lblInfoInscripcionPago == null) {
			lblInfoInscripcionPago = new JLabel("Informacion");
			lblInfoInscripcionPago.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblInfoInscripcionPago.setBackground(Color.WHITE);
		}
		return lblInfoInscripcionPago;
	}

	private JComboBox<Integer> getComboBoxDiaNuevaComp() {
		if (comboBoxDiaNuevaComp == null) {
			comboBoxDiaNuevaComp = new JComboBox<Integer>();
			comboBoxDiaNuevaComp.setModel(getModelDias());
			comboBoxDiaNuevaComp.setSelectedIndex(1);
			comboBoxDiaNuevaComp.setBounds(33, 161, 58, 30);
		}
		return comboBoxDiaNuevaComp;
	}

	private JComboBox<Integer> getComboBoxMesNuevaComp() {
		if (comboBoxMesNuevaComp == null) {
			comboBoxMesNuevaComp = new JComboBox<Integer>();
			comboBoxMesNuevaComp.setModel(getModelMeses());
			comboBoxMesNuevaComp.setSelectedIndex(1);
			comboBoxMesNuevaComp.setBounds(103, 161, 62, 30);
		}
		return comboBoxMesNuevaComp;
	}

	private JComboBox<Integer> getComboBoxAnioNuevaComp() {
		if (comboBoxAnioNuevaComp == null) {
			comboBoxAnioNuevaComp = new JComboBox<Integer>();
			comboBoxAnioNuevaComp.setModel(getModelAnios());
			comboBoxAnioNuevaComp.setSelectedIndex(69);
			comboBoxAnioNuevaComp.setBounds(177, 161, 77, 30);
		}
		return comboBoxAnioNuevaComp;
	}

	private JPanel getPnTablasOrganizador() {
		if (pnTablasOrganizador == null) {
			pnTablasOrganizador = new JPanel();
			pnTablasOrganizador.setBackground(Color.WHITE);
			pnTablasOrganizador.setLayout(new GridLayout(1, 0, 0, 0));
			pnTablasOrganizador.add(getPnTablaCompeticionesOrgan());
			pnTablasOrganizador.add(getPnTablaInscripcionesOrgan());
		}
		return pnTablasOrganizador;
	}

	private JLabel getLblMenuOrganizador() {
		if (lblMenuOrganizador == null) {
			lblMenuOrganizador = new JLabel("Menu Organizador");
			lblMenuOrganizador.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblMenuOrganizador;
	}

	private JScrollPane getScrollPaneTablaOrganIns() {
		if (scrollPaneTablaOrganIns == null) {
			scrollPaneTablaOrganIns = new JScrollPane();
			scrollPaneTablaOrganIns.setViewportView(getTableIInscripcionesOrganizador());
		}
		return scrollPaneTablaOrganIns;
	}

	private JScrollPane getScrollPaneTablaOrganComp() {
		if (scrollPaneTablaOrganComp == null) {
			scrollPaneTablaOrganComp = new JScrollPane();
			scrollPaneTablaOrganComp.setViewportView(getTableCompeticionesOrganizador());
		}
		return scrollPaneTablaOrganComp;
	}

	private JTable getTableCompeticionesOrganizador() {
		if (tableCompeticionesOrganizador == null) {
			tableCompeticionesOrganizador = new JTable();
			tableCompeticionesOrganizador.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tableCompeticionesOrganizador.setModel(modelTodasCompeticiones);
			tableCompeticionesOrganizador.setRowSelectionAllowed(true);
		}
		return tableCompeticionesOrganizador;
	}

	private JTable getTableIInscripcionesOrganizador() {
		if (tableIInscripcionesOrganizador == null) {
			tableIInscripcionesOrganizador = new JTable();
			tableIInscripcionesOrganizador.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tableIInscripcionesOrganizador.setModel(modelTodasInscripciones);
			tableIInscripcionesOrganizador.setRowSelectionAllowed(true);
		}
		return tableIInscripcionesOrganizador;
	}

	private JLabel getLblTodasCompeticiones() {
		if (lblTodasCompeticiones == null) {
			lblTodasCompeticiones = new JLabel("Competiciones");
			lblTodasCompeticiones.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblTodasCompeticiones;
	}

	private JLabel getLblTodasInscripciones() {
		if (lblTodasInscripciones == null) {
			lblTodasInscripciones = new JLabel("Inscripciones");
			lblTodasInscripciones.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblTodasInscripciones;
	}

	private JPanel getPnTablaCompeticionesOrgan() {
		if (pnTablaCompeticionesOrgan == null) {
			pnTablaCompeticionesOrgan = new JPanel();
			pnTablaCompeticionesOrgan.setBackground(Color.WHITE);
			pnTablaCompeticionesOrgan.setLayout(new BorderLayout(0, 0));
			pnTablaCompeticionesOrgan.add(getScrollPaneTablaOrganComp());
			pnTablaCompeticionesOrgan.add(getLblTodasCompeticiones(), BorderLayout.NORTH);
			pnTablaCompeticionesOrgan.add(getPnOrganizadorCompeticion(), BorderLayout.SOUTH);
		}
		return pnTablaCompeticionesOrgan;
	}

	private JPanel getPnTablaInscripcionesOrgan() {
		if (pnTablaInscripcionesOrgan == null) {
			pnTablaInscripcionesOrgan = new JPanel();
			pnTablaInscripcionesOrgan.setBackground(Color.WHITE);
			pnTablaInscripcionesOrgan.setLayout(new BorderLayout(0, 0));
			pnTablaInscripcionesOrgan.add(getScrollPaneTablaOrganIns());
			pnTablaInscripcionesOrgan.add(getLblTodasInscripciones(), BorderLayout.NORTH);
		}
		return pnTablaInscripcionesOrgan;
	}

	private JPanel getPnOrganizadorCompeticion() {
		if (pnOrganizadorCompeticion == null) {
			pnOrganizadorCompeticion = new JPanel();
			pnOrganizadorCompeticion.setBackground(Color.WHITE);
			pnOrganizadorCompeticion.add(getBtnCargarPagos());
			pnOrganizadorCompeticion.add(getBtnCargarResultados());
		}
		return pnOrganizadorCompeticion;
	}

	private JButton getBtnCargarPagos() {
		if (btnCargarPagos == null) {
			btnCargarPagos = new JButton("Cargar Pagos");
			btnCargarPagos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int row = tableCompeticionesOrganizador.getSelectedRow();
					if (row != -1) {
						cargarPagos(todasCompeticiones.get(row));
					} else
						JOptionPane.showMessageDialog(getThis(), "No has seleccionado la competicion");
				}
			});
			btnCargarPagos.setFont(new Font("Dialog", Font.PLAIN, 14));
		}
		return btnCargarPagos;
	}

	protected void cargarPagos(Competicion competicion) {
		// TODO YO
		
		String file = JOptionPane.showInputDialog(this,"Escribe el nombre del fichero con los pagos por transferencia");
		toPagos();
		
	}

	private void toPagos() {
		//loadPagos();
		pnButtons.setVisible(true);
		cardNumber = 7;
		((CardLayout) pnCards.getLayout()).show(pnCards, "pagos");
	}

	private JButton getBtnCargarResultados() {
		if (btnCargarResultados == null) {
			btnCargarResultados = new JButton("Cargar Resultados");
			btnCargarResultados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int row = tableCompeticionesOrganizador.getSelectedRow();
					if (row != -1) {
						cargarResultados(todasCompeticiones.get(row));
					} else
						JOptionPane.showMessageDialog(getThis(), "No has seleccionado la competicion");
				}
			});
			btnCargarResultados.setFont(new Font("Dialog", Font.PLAIN, 14));
		}
		return btnCargarResultados;
	}
	protected void cargarResultados(Competicion competicion) {
		// TODO (aqui tu codigo AITOR)
		String file = JOptionPane.showInputDialog(this,"Escribe el nombre del fichero con los resultados de la competicion");
		toResultados();
		
	}

	private void toResultados() {
		// TODO Auto-generated method stub
		//loadResultados();
		pnButtons.setVisible(true);
		cardNumber = 6;
		((CardLayout) pnCards.getLayout()).show(pnCards, "resultados");
	}

	private JPanel getPnAtletaHistorial() {
		if (pnAtletaHistorial == null) {
			pnAtletaHistorial = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnAtletaHistorial.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnAtletaHistorial.setBackground(Color.WHITE);
			pnAtletaHistorial.add(getLblMenuAtleta());
			pnAtletaHistorial.add(getBtnVerMiHistorial());
		}
		return pnAtletaHistorial;
	}
	private JButton getBtnVerMiHistorial() {
		if (btnVerMiHistorial == null) {
			btnVerMiHistorial = new JButton("Ver Mi Historial");
			btnVerMiHistorial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return btnVerMiHistorial;
	}
	private JPanel getPnResultados() {
		if (pnResultados == null) {
			pnResultados = new JPanel();
			pnResultados.setBackground(Color.WHITE);
			pnResultados.setLayout(new BorderLayout(0, 0));
			pnResultados.add(getLblMenuOrganizadorResultados(), BorderLayout.NORTH);
			pnResultados.add(getScrollPaneTablaResultados(), BorderLayout.CENTER);
		}
		return pnResultados;
	}
	private JLabel getLblMenuOrganizadorResultados() {
		if (lblMenuOrganizadorResultados == null) {
			lblMenuOrganizadorResultados = new JLabel("Menu Organizador: Resultados ");
			lblMenuOrganizadorResultados.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblMenuOrganizadorResultados;
	}
	private JScrollPane getScrollPaneTablaResultados() {
		if (scrollPaneTablaResultados == null) {
			scrollPaneTablaResultados = new JScrollPane();
			scrollPaneTablaResultados.setViewportView(getTableResultados());
		}
		return scrollPaneTablaResultados;
	}
	private JTable getTableResultados() {
		if (tableResultados == null) {
			tableResultados = new JTable();
			tableResultados.setModel(modelResultados);
			tableResultados.setRowSelectionAllowed(true);
		}
		return tableResultados;
	}
}
