package appbuilder.ui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import appbuilder.entity.ComponentType;
import appbuilder.entity.DataType;
import framework.persistence.jpa.PersistenceServiceUtil;
import framework.swing.Label;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Font;

public class FieldDlg extends JDialog {

	private static final long serialVersionUID = 1L;

	private PersistenceServiceUtil persistence = new PersistenceServiceUtil();

	private List<ComponentType>compTypes;
	private List<DataType>types; 		
	
	private JComboBox tipoComponente;
	private Component previewComp;
	private JComboBox tipoDado;
	private JTextField label;
	private JPanel panel;
	
	
	void preview() {
//		VALUES ('CAIXA DE TEXTO'); --> 0
//		VALUES ('ÁREA DE TEXTO'); --> 1
//		VALUES ('CAIXA DE CHECAGEM'); --> 2
//		VALUES ('LISTA DE VALORES'); --> 3

		int index 			= tipoComponente.getSelectedIndex();
		ComponentType type 	= compTypes.get(index);
		int typeId 			= type.getId();  

		if (previewComp != null) {
			panel.remove(previewComp);
		}
		
		switch (typeId) {
			case 0:
				JTextField tx = new JTextField();
				tx.setPreferredSize(new Dimension(255, 25));
				previewComp = tx;
				panel.add(tx);
				break;
			case 1:
				JTextArea ta = new JTextArea();
				ta.setPreferredSize(new Dimension(255, 45));
				previewComp = ta;
				panel.add(ta);
				break;
			case 2:
				JCheckBox ck = new JCheckBox();
				previewComp = ck;
				panel.add(ck);
				break;
			case 3:
				JComboBox cb = new JComboBox();
				cb.setPreferredSize(new Dimension(255, 25));
				previewComp = cb;
				panel.add(cb);
				break;
			default:
				break;
		}
	}
	
	private void getAllComponentTypes() {
		try {
			compTypes = persistence.findAll(ComponentType.class, null);
		} finally {
			persistence.close();
		} 
	}
	
	private void getAllDataTypes() {
		try {
			types = persistence.findAll(DataType.class, null);
		} finally {
			persistence.close();
		} 
	}
	
	public void postConstruct() {
		getAllComponentTypes();
		getAllDataTypes();

		tipoComponente.addItem("");
		tipoDado.addItem("");
		
		for (ComponentType type: compTypes) {
			tipoComponente.addItem(type.getTipo());
		}
		for (DataType type: types) {
			tipoDado.addItem(type.getDescricao());
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FieldDlg dialog = new FieldDlg();
					dialog.postConstruct();
					
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public FieldDlg() {
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 910, 585);
		getContentPane().setLayout(null);
		
		JLabel lblInformeOLabel = new Label("Informe o Label do Campo");
		lblInformeOLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInformeOLabel.setBounds(22, 24, 294, 16);
		getContentPane().add(lblInformeOLabel);
		
		label = new AppTextField();
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(341, 15, 539, 35);
		getContentPane().add(label);
		label.setColumns(10);
		
		Label lblInformeOTipo = new Label("Informe o Label do Campo");
		lblInformeOTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInformeOTipo.setText("Informe o Tipo de Dado do Campo");
		lblInformeOTipo.setBounds(22, 71, 294, 16);
		getContentPane().add(lblInformeOTipo);
		
		Label lblInformeOTipo_2 = new Label("Informe o Label do Campo");
		lblInformeOTipo_2.setText("Informe o Tipo de Componente do Campo");
		lblInformeOTipo_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInformeOTipo_2.setBounds(22, 119, 294, 16);
		getContentPane().add(lblInformeOTipo_2);
		
		tipoDado = new JComboBox();
		tipoDado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tipoDado.setBounds(341, 63, 539, 35);
		getContentPane().add(tipoDado);
		
		tipoComponente = new JComboBox();
		tipoComponente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tipoComponente.setBounds(341, 111, 539, 35);
		getContentPane().add(tipoComponente);
		
		JLabel lblNewLabel = new Label("Preview do Tipo de Componente");
		lblNewLabel.setText("Preview do Tipo de Componente:");
		lblNewLabel.setBounds(22, 191, 323, 16);
		getContentPane().add(lblNewLabel);
		
		panel = new JPanel();
		panel.setBounds(22, 220, 858, 229);
		getContentPane().add(panel);

	}
}
