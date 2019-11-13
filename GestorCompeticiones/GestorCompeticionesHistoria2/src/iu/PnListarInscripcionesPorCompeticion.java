package iu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import logic.inscripcion.ListarInscripciones;
import logic.model.AtletaInscripcion;

public class PnListarInscripcionesPorCompeticion extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelCompeticion;
	private JLabel lblCompeticion;
	private JTextField txtCompeticion;
	private JButton btnListar;
	private JPanel lowerPanel;
	private JScrollPane scrollPaneCompeticiones;
	private JList<AtletaInscripcion> list;
	private DefaultListModel<AtletaInscripcion> modeloInscripciones = new DefaultListModel<>();

	/**
	 * Create the panel.
	 */
	public PnListarInscripcionesPorCompeticion() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		add(getPanelCompeticion(), BorderLayout.NORTH);
		add(getLowerPanel(), BorderLayout.SOUTH);
		add(getScrollPaneCompeticiones(), BorderLayout.CENTER);
	}

	private JPanel getPanelCompeticion() {
		if (panelCompeticion == null) {
			panelCompeticion = new JPanel();
			panelCompeticion.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panelCompeticion.add(getLblCompeticion());
			panelCompeticion.add(getTxtCompeticion());
		}
		return panelCompeticion;
	}

	private JLabel getLblCompeticion() {
		if (lblCompeticion == null) {
			lblCompeticion = new JLabel("Id de la competición para listar inscripciones:");
			lblCompeticion.setFont(new Font("Verdana", Font.PLAIN, 12));
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
			btnListar = new JButton("Listar");
			btnListar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					mostrarCompeticiones(txtCompeticion.getText());
				}
			});
			btnListar.setFont(new Font("Verdana", Font.PLAIN, 16));
		}
		return btnListar;
	}

	private void mostrarCompeticiones(String id) {
		ListarInscripciones inscripciones = new ListarInscripciones();
		List<AtletaInscripcion> inscr = inscripciones.verAtletasEInscripciones(id);

		modeloInscripciones.clear();

		for (AtletaInscripcion i : inscr) {
			modeloInscripciones.addElement(i);
		}

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

	private JList<AtletaInscripcion> getListCompeticionesDisponibles() {
		if (list == null) {
			list = new JList<AtletaInscripcion>(modeloInscripciones);
			list.setVisible(true);
		}
		return list;
	}

}
