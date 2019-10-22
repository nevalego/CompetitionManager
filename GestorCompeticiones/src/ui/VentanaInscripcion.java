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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import logic.exception.DataException;
import logic.inscripcion.ListarCompeticiones;
import logic.model.Competicion;

public class VentanaInscripcion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel principalPanel;
	private JPanel panelIntroduceEmail;
	private JPanel panelEmail;
	private JLabel lblEmail;
	private JTextField textFieldEmail;
	private JButton btnInscribirse;
	private JPanel lowerPanel;
	private JScrollPane scrollPaneCompeticiones;
	private JList<Competicion> listCompeticionesDisponibles;
	private DefaultListModel<Competicion> modelCompeticiones = new DefaultListModel<>();
	 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInscripcion frame = new VentanaInscripcion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws DataException 
	 */
	public VentanaInscripcion() throws DataException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 918, 627);
		principalPanel = new JPanel();
		principalPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		principalPanel.setLayout(new BorderLayout(0, 0));
		principalPanel.add(getPanelIntroduceEmail(), BorderLayout.NORTH);
		principalPanel.add(getLowerPanel(), BorderLayout.SOUTH);
		principalPanel.add(getScrollPaneCompeticiones(), BorderLayout.CENTER);
		
		setContentPane(principalPanel);
		
		
		mostrarCompeticiones();
	}

	private void mostrarCompeticiones() throws DataException {
		ListarCompeticiones competiciones = new ListarCompeticiones();
		List<Competicion> comps = competiciones.verCompeticiones("");
		
		for(Competicion c : comps) {
			modelCompeticiones.addElement(c);
		}
		
	}
	
	private JPanel getPanelIntroduceEmail() {
		if (panelIntroduceEmail == null) {
			panelIntroduceEmail = new JPanel();
			panelIntroduceEmail.setLayout(new GridLayout(0, 2, 0, 0));
			panelIntroduceEmail.add(getPanelEmail());
		}
		return panelIntroduceEmail;
	}
	private JPanel getPanelEmail() {
		if (panelEmail == null) {
			panelEmail = new JPanel();
			panelEmail.setLayout(new GridLayout(0, 2, 0, 0));
			panelEmail.add(getLblEmail());
			panelEmail.add(getTextFieldEmail());
		}
		return panelEmail;
	}
	private JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel("Email:");
			lblEmail.setFont(new Font("Verdana", Font.PLAIN, 16));
		}
		return lblEmail;
	}
	private JTextField getTextFieldEmail() {
		if (textFieldEmail == null) {
			textFieldEmail = new JTextField();
			textFieldEmail.setColumns(10);
		}
		return textFieldEmail;
	}
	private JButton getBtnInscribirse() {
		if (btnInscribirse == null) {
			btnInscribirse = new JButton("Inscribirse");
			btnInscribirse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnInscribirse.setFont(new Font("Verdana", Font.PLAIN, 16));
		}
		return btnInscribirse;
	}
	private JPanel getLowerPanel() {
		if (lowerPanel == null) {
			lowerPanel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) lowerPanel.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			lowerPanel.add(getBtnInscribirse());
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
	private JList<Competicion> getListCompeticionesDisponibles() {
		if (listCompeticionesDisponibles == null) {
			listCompeticionesDisponibles = new JList<Competicion>(modelCompeticiones);
			listCompeticionesDisponibles.setVisible(true);
		}
		return listCompeticionesDisponibles;
	}
}
