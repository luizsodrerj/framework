package appbuilder.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import framework.presentation.swing.Window;
import framework.swing.Button;
import framework.swing.Label;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormDesignDlg extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;


	
	void novoCampoActionPerformed() {
		FieldDlg dialog = new FieldDlg();
		dialog.postConstruct();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Window.centralizeWindow(dialog);
		dialog.setModal(true);
		dialog.setVisible(true);
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FormDesignDlg dialog = new FormDesignDlg();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			Window.centralizeWindow(dialog);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FormDesignDlg() {
		setBounds(100, 100, 1192, 776);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblInformeONome = new Label("Informe o nome do Formul\u00E1rio");
		lblInformeONome.setBounds(12, 13, 253, 16);
		contentPanel.add(lblInformeONome);
		
		textField = new AppTextField();
		textField.setBounds(12, 42, 760, 35);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 186, 1107, 16);
		contentPanel.add(separator);
		
		JButton novoCampo = new Button("Incluir novo Campo");
		novoCampo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				novoCampoActionPerformed();
			}
		});
		novoCampo.setBounds(861, 13, 301, 47);
		contentPanel.add(novoCampo);
		
		JButton cancelar = new Button("Cancelar e Retornar");
		cancelar.setBounds(861, 123, 301, 47);
		contentPanel.add(cancelar);
		
		JButton finalizar = new Button("Finalizar e Retornar");
		finalizar.setBounds(861, 69, 301, 47);
		contentPanel.add(finalizar);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(815, 13, 13, 161);
		contentPanel.add(separator_1);
		
		Label lblpreviewDoLayout = new Label("Informe o nome do Formul\u00E1rio");
		lblpreviewDoLayout.setText("\"PREVIEW\" do Layout do Formul\u00E1rio");
		lblpreviewDoLayout.setBounds(12, 202, 390, 16);
		contentPanel.add(lblpreviewDoLayout);
		
		JPanel previewPane = new JPanel();
		previewPane.setBounds(12, 243, 1150, 472);
		contentPanel.add(previewPane);
	}

}
