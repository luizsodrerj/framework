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
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class FieldDlg extends JDialog {

	private static final long serialVersionUID = 1L;

	static final int CAIXA_DE_TEXTO 	= 0;
	static final int AREA_DE_TEXTO 		= 1;
	static final int CAIXA_DE_CHECAGEM	= 2;
	static final int LISTA_DE_VALORES 	= 3;
	
	
	private PersistenceServiceUtil persistence = new PersistenceServiceUtil();

	private List<ComponentType>compTypes;
	private List<DataType>types; 		
	
	private JComboBox tipoComponente;
	private Component previewComp;
	private JComboBox tipoDado;
	private JTextField label;
	private JPanel panel;
	
	
	void preview() {
		if (tipoComponente.getSelectedIndex() == 0) {
			return;
		}
		Dimension prefSize	= new Dimension(355, 40);
		ComponentType type 	= compTypes.get(tipoComponente.getSelectedIndex() - 1);
		int typeId 			= type.getId();  
		
		switch (typeId) {
			case CAIXA_DE_TEXTO:
				JTextField tx = new JTextField();
				tx.setPreferredSize(prefSize);
				
				if (previewComp != null && !(previewComp instanceof JTextField)) {
					panel.remove(previewComp);
					previewComp = tx;
					panel.add(tx);
				} else if (previewComp == null) {
					previewComp = tx;
					panel.add(tx);
				}
				break;
			case AREA_DE_TEXTO:
				JTextArea ta = new JTextArea();
				ta.setPreferredSize(new Dimension(355, 75));
				
				if (previewComp != null && !(previewComp instanceof JTextArea)) {
					panel.remove(previewComp);
					previewComp = ta;
					panel.add(ta);
				} else if (previewComp == null) {
					previewComp = ta;
					panel.add(ta);
				}
				break;
			case CAIXA_DE_CHECAGEM:
				JCheckBox ck = new JCheckBox();
				ck.setSelected(true);
				
				if (previewComp != null && !(previewComp instanceof JCheckBox)) {
					panel.remove(previewComp);
					previewComp = ck;
					panel.add(ck);
				} else if (previewComp == null) {
					previewComp = ck;
					panel.add(ck);
				}
				break;
			case LISTA_DE_VALORES:
				JComboBox cb = new JComboBox();
				cb.setPreferredSize(prefSize);
				
				if (previewComp != null && !(previewComp instanceof JComboBox)) {
					panel.remove(previewComp);
					previewComp = cb;
					panel.add(cb);
				} else if (previewComp == null) {
					previewComp = cb;
					panel.add(cb);
				}
				break;
			default:
				break;
		}
		panel.doLayout();
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
		tipoComponente.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				preview();
			}
		});
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
