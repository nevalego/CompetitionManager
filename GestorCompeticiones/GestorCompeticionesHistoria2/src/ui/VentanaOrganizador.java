package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import logic.exception.DataException;
import logic.inscripcion.HacerInscripcion;
import logic.inscripcion.ListarInscripciones;
import logic.model.Inscripcion;

public class VentanaOrganizador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel principalPanel;
	private JPanel panelEnterCompeticion;
	private JPanel panelCompeticion;
	private JLabel lblCompeticion;
	private JTextField txtCompeticion;
	private JButton btnListar;
	private JPanel lowerPanel;
	private JScrollPane scrollPaneCompeticiones;
	private JList<Inscripcion> listCompeticionesDisponibles;
	private DefaultListModel<Inscripcion> modeloInscripciones = new DefaultListModel<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaOrganizador frame = new VentanaOrganizador();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws DataException
	 */
	public VentanaOrganizador() throws DataException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 918, 627);
		principalPanel = new JPanel();
		principalPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		principalPanel.setLayout(new BorderLayout(0, 0));
		principalPanel.add(getPanelCompeticion(), BorderLayout.NORTH);
		principalPanel.add(getLowerPanel(), BorderLayout.SOUTH);
		principalPanel.add(getScrollPaneCompeticiones(), BorderLayout.CENTER);

		setContentPane(principalPanel);

		mostrarCompeticiones();
	}

	private void mostrarCompeticiones() throws DataException {
		ListarInscripciones inscripciones = new ListarInscripciones();
		List<Inscripcion> inscr = inscripciones.verInscripciones("");

		for (Inscripcion i : inscr) {
			modeloInscripciones.addElement(i);
		}

	}

	private JPanel getPanelEnterCompeticion() {
		if (panelEnterCompeticion == null) {
			panelEnterCompeticion = new JPanel();
			panelEnterCompeticion.setLayout(new GridLayout(0, 2, 0, 0));
			panelEnterCompeticion.add(getPanelCompeticion());
		}
		return panelEnterCompeticion;
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
			btnListar = new JButton("Inscribirse");
			btnListar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
				}
			});
			btnListar.setFont(new Font("Verdana", Font.PLAIN, 16));
		}
		return btnListar;
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
			scrollPaneCompeticiones.setViewportView(getListCompeticionesDisponibles());
		}
		return scrollPaneCompeticiones;
	}

	private JList<Inscripcion> getListCompeticionesDisponibles() {
		if (listCompeticionesDisponibles == null) {
			listCompeticionesDisponibles = new JList<Inscripcion>(modeloInscripciones);
			listCompeticionesDisponibles.setVisible(true);
		}
		return listCompeticionesDisponibles;
	}
}
