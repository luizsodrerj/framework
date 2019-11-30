package appbuilder.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import framework.swing.Button;
import framework.swing.Label;
import java.awt.Font;

public class AppDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AppDialog dialog = new AppDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AppDialog() {
		setTitle("Criar Aplica\u00E7\u00E3o");
		setBounds(100, 100, 787, 368);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblInformeONome = new Label("Informe o nome da sua Aplica\u00E7\u00E3o");
		lblInformeONome.setBounds(12, 13, 254, 16);
		contentPanel.add(lblInformeONome);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(12, 42, 447, 42);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new Button("Salvar e Finalizar");
		btnNewButton.setBounds(507, 23, 250, 42);
		contentPanel.add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(479, 13, 16, 297);
		contentPanel.add(separator);
		
		JButton btnNewButton_1 = new Button("Cancelar");
		btnNewButton_1.setBounds(507, 78, 250, 42);
		contentPanel.add(btnNewButton_1);
		
		JButton btnCriarPrimeiroFormulrio = new Button("Criar primeiro Formul\u00E1rio");
		btnCriarPrimeiroFormulrio.setBounds(507, 133, 250, 42);
		contentPanel.add(btnCriarPrimeiroFormulrio);
	}
}
