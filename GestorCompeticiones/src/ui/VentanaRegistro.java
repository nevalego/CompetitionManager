package ui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;

import logic.exception.DataException;
import logic.inscripcion.HacerRegistro;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ButtonGroup;
import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaRegistro extends JFrame {

	/**
	 * 
	 */
	private HacerRegistro registrador;
	private static final long serialVersionUID = 1L;
	private JPanel generalPanel;
	private JLabel lblName;
	private JTextField textFieldNombre;
	private JLabel lblSurname;
	private JTextField textFieldApellido;
	private JLabel lblEmail;
	private JLabel lblDni;
	private JTextField textFieldEmail;
	private JTextField textFieldDNI;
	private JPanel genderPanel;
	private JRadioButton rdbtnFemenino;
	private JRadioButton rdbtnMasculino;
	private JPanel datePanel;
	private JLabel lblDia;
	private JComboBox<String>  comboBoxDia;
	private JLabel lblMes;
	private JComboBox<String>  comboBoxMes;
	private JLabel lblAo;
	private JComboBox<String> comboBoxAño;
	private final ButtonGroup buttonGroupgGender = new ButtonGroup();
	private Button buttonAceptar;
	private Button buttonCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistro frame = new VentanaRegistro();
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
	public VentanaRegistro() {
		registrador = new HacerRegistro();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 988, 615);
		generalPanel = new JPanel();
		generalPanel.setBackground(Color.WHITE);
		generalPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(generalPanel);
		generalPanel.setLayout(null);
		generalPanel.add(getLblName());
		generalPanel.add(getTextFieldNombre());
		generalPanel.add(getLblSurname());
		generalPanel.add(getTextFieldApellido());
		generalPanel.add(getLblEmail());
		generalPanel.add(getLblDni());
		generalPanel.add(getTextFieldEmail());
		generalPanel.add(getTextFieldDNI());
		generalPanel.add(getGenderPanel());
		generalPanel.add(getDatePanel());
		generalPanel.add(getButtonAceptar());
		generalPanel.add(getButtonCancelar());
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("Name:");
			lblName.setFont(new Font("Verdana", Font.PLAIN, 16));
			lblName.setBounds(39, 42, 131, 27);
		}
		return lblName;
	}
	private JTextField getTextFieldNombre() {
		if (textFieldNombre == null) {
			textFieldNombre = new JTextField();
			textFieldNombre.setFont(new Font("Verdana", Font.PLAIN, 16));
			textFieldNombre.setBounds(168, 44, 274, 27);
			textFieldNombre.setColumns(10);
		}
		return textFieldNombre;
	}
	private JLabel getLblSurname() {
		if (lblSurname == null) {
			lblSurname = new JLabel("Surname:");
			lblSurname.setFont(new Font("Verdana", Font.PLAIN, 16));
			lblSurname.setBounds(523, 47, 115, 27);
		}
		return lblSurname;
	}
	private JTextField getTextFieldApellido() {
		if (textFieldApellido == null) {
			textFieldApellido = new JTextField();
			textFieldApellido.setFont(new Font("Verdana", Font.PLAIN, 16));
			textFieldApellido.setBounds(660, 43, 259, 26);
			textFieldApellido.setColumns(10);
		}
		return textFieldApellido;
	}
	private JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel("Email :");
			lblEmail.setFont(new Font("Verdana", Font.PLAIN, 16));
			lblEmail.setBounds(39, 143, 109, 27);
		}
		return lblEmail;
	}
	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("DNI:");
			lblDni.setFont(new Font("Verdana", Font.PLAIN, 16));
			lblDni.setBounds(39, 197, 87, 27);
		}
		return lblDni;
	}
	private JTextField getTextFieldEmail() {
		if (textFieldEmail == null) {
			textFieldEmail = new JTextField();
			textFieldEmail.setFont(new Font("Verdana", Font.PLAIN, 16));
			textFieldEmail.setBounds(168, 145, 274, 27);
			textFieldEmail.setColumns(10);
		}
		return textFieldEmail;
	}
	private JTextField getTextFieldDNI() {
		if (textFieldDNI == null) {
			textFieldDNI = new JTextField();
			textFieldDNI.setFont(new Font("Verdana", Font.PLAIN, 16));
			textFieldDNI.setBounds(168, 200, 274, 25);
			textFieldDNI.setColumns(10);
		}
		return textFieldDNI;
	}
	private JPanel getGenderPanel() {
		if (genderPanel == null) {
			genderPanel = new JPanel();
			genderPanel.setBackground(Color.WHITE);
			genderPanel.setBorder(new TitledBorder(null, "Genero", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
			genderPanel.setBounds(566, 143, 358, 100);
			genderPanel.setLayout(new GridLayout(0, 2, 0, 0));
			genderPanel.add(getRdbtnMasculino());
			genderPanel.add(getRdbtnFemenino());
		}
		return genderPanel;
	}
	private JRadioButton getRdbtnFemenino() {
		if (rdbtnFemenino == null) {
			rdbtnFemenino = new JRadioButton("Femenino");
			rdbtnFemenino.setBackground(Color.WHITE);
			rdbtnFemenino.setFont(new Font("Verdana", Font.PLAIN, 16));
			buttonGroupgGender.add(rdbtnFemenino);
		}
		return rdbtnFemenino;
	}
	private JRadioButton getRdbtnMasculino() {
		if (rdbtnMasculino == null) {
			rdbtnMasculino = new JRadioButton("Masculino");
			rdbtnMasculino.setBackground(Color.WHITE);
			rdbtnMasculino.setFont(new Font("Verdana", Font.PLAIN, 16));
			buttonGroupgGender.add(rdbtnMasculino);
		}
		return rdbtnMasculino;
	}
	private JPanel getDatePanel() {
		if (datePanel == null) {
			datePanel = new JPanel();
			datePanel.setBackground(Color.WHITE);
			datePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Fecha de Nacimiento", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			datePanel.setBounds(25, 308, 457, 161);
			datePanel.setLayout(new GridLayout(3, 2, 0, 0));
			datePanel.add(getLblDia());
			datePanel.add(getComboBoxDia());
			datePanel.add(getLblMes());
			datePanel.add(getComboBoxMes());
			datePanel.add(getLblAo());
			datePanel.add(getComboBoxAño());
		}
		return datePanel;
	}
	private JLabel getLblDia() {
		if (lblDia == null) {
			lblDia = new JLabel("Dia:");
			lblDia.setFont(new Font("Verdana", Font.PLAIN, 16));
		}
		return lblDia;
	}
	private JComboBox<String>  getComboBoxDia() {
		if (comboBoxDia == null) {
			comboBoxDia = new JComboBox<String> ();
			comboBoxDia.setForeground(Color.WHITE);
			comboBoxDia.setBackground(Color.BLACK);
			comboBoxDia.setFont(new Font("Verdana", Font.PLAIN, 16));
			comboBoxDia.setModel(new DefaultComboBoxModel<String> (new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		}
		return comboBoxDia;
	}
	private JLabel getLblMes() {
		if (lblMes == null) {
			lblMes = new JLabel("Mes:");
			lblMes.setFont(new Font("Verdana", Font.PLAIN, 16));
		}
		return lblMes;
	}
	private JComboBox<String>  getComboBoxMes() {
		if (comboBoxMes == null) {
			comboBoxMes = new JComboBox<String> ();
			comboBoxMes.setForeground(Color.WHITE);
			comboBoxMes.setBackground(Color.BLACK);
			comboBoxMes.setFont(new Font("Verdana", Font.PLAIN, 16));
			comboBoxMes.setModel(new DefaultComboBoxModel<String> (new String[] {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}));
		}
		return comboBoxMes;
	}
	private JLabel getLblAo() {
		if (lblAo == null) {
			lblAo = new JLabel("A\u00F1o:");
			lblAo.setFont(new Font("Verdana", Font.PLAIN, 16));
		}
		return lblAo;
	}
	private JComboBox<String> getComboBoxAño() {
		if (comboBoxAño == null) {
			comboBoxAño = new JComboBox<String> ();
			comboBoxAño.setForeground(Color.WHITE);
			comboBoxAño.setBackground(Color.BLACK);
			comboBoxAño.setFont(new Font("Verdana", Font.PLAIN, 16));
			comboBoxAño.setModel(new DefaultComboBoxModel<String>(new String[] {"1900", "1901", "1902", "1903", "1904", "1905", "1906", "1907", "1908", "1909", "1910", "1911", "1912", "1913", "1914", "1915", "1916", "1917", "1918", "1919", "1920", "1921", "1922", "1923", "1924", "1925", "1926", "1927", "1928", "1929", "1930", "1931", "1932", "1933", "1934", "1935", "1936", "1937", "1938", "1939", "1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019"}));

		}
		return comboBoxAño;
	}
	private Button getButtonAceptar() {
		if (buttonAceptar == null) {
			buttonAceptar = new Button("Aceptar");
			buttonAceptar.setForeground(Color.WHITE);
			buttonAceptar.setBackground(Color.BLACK);
			buttonAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if(!registrador.comprobarDatos(textFieldEmail.getText(), textFieldDNI.getText()) && registrador.comprobarFecha(comboBoxDia.getSelectedItem().toString(),comboBoxMes.getSelectedItem().toString(),comboBoxAño.getSelectedItem().toString())) {
							String sexo = ""; if(rdbtnMasculino.isSelected()) sexo = "Masculino"; else sexo= "Femenino";
							registrador.registrar(textFieldNombre.getText(),textFieldApellido.getText(),textFieldEmail.getText(),textFieldDNI.getText(),
									sexo, comboBoxDia.getSelectedItem().toString(),comboBoxMes.getSelectedItem().toString(),comboBoxAño.getSelectedItem().toString());
							//TODO: IMPRIMIR JUSTIFICANTE
						}else {
							JOptionPane.showMessageDialog(getParentComponent(),"Datos incluidos ya registrados o erroneos,\npor favor reviselos", "Error con los datos", JOptionPane.ERROR_MESSAGE);
						}
					} catch (DataException e1) {
						JOptionPane.showMessageDialog(getParentComponent(),e1.getMessage(), "Error con los datos", JOptionPane.ERROR_MESSAGE);
					}
			}
			});
			buttonAceptar.setFont(new Font("Verdana", Font.PLAIN, 16));
			buttonAceptar.setBounds(817, 513, 143, 45);
		}
		return buttonAceptar;
	}
	private JFrame getParentComponent() {
		return this;
	}
	private Button getButtonCancelar() {
		if (buttonCancelar == null) {
			buttonCancelar = new Button("Cancelar");
			buttonCancelar.setForeground(Color.WHITE);
			buttonCancelar.setBackground(Color.BLACK);
			buttonCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					textFieldNombre.setText("");
					textFieldApellido.setText("");
					textFieldEmail.setText("");
					textFieldDNI.setText("");
					rdbtnMasculino.setSelected(false);
					rdbtnFemenino.setSelected(false);
					comboBoxDia.setSelectedIndex(1);
					comboBoxMes.setSelectedIndex(1);
					comboBoxAño.setSelectedIndex(1);
				}
			});
			buttonCancelar.setFont(new Font("Verdana", Font.PLAIN, 16));
			buttonCancelar.setBounds(629, 513, 155, 45);
		}
		return buttonCancelar;
	}
}
