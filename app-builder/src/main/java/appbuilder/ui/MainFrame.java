package appbuilder.ui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import framework.presentation.swing.Window;
import framework.swing.BaseFrame;
import framework.swing.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends BaseFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;

	
	
	void criarApp() {
		AppDialog app = new AppDialog();
		app.setModal(true);
		app.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Window.centralizeWindow(app);
		app.setVisible(true);
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					Window.setNimbusLookAndFeel();
					Window.centralizeWindow(frame);
					
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
	public MainFrame() {
		setTitle("Construtor de Aplicativos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCriarUmaAplicao = new Button("Criar uma Aplica\u00E7\u00E3o");
		btnCriarUmaAplicao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criarApp();
			}
		});
		btnCriarUmaAplicao.setBounds(6, 47, 241, 50);
		contentPane.add(btnCriarUmaAplicao);
		
		JButton btnImportarPlanilha = new Button("Importar Planilha");
		btnImportarPlanilha.setText("Configurar uma Aplica\u00E7\u00E3o");
		btnImportarPlanilha.setBounds(259, 48, 259, 49);
		contentPane.add(btnImportarPlanilha);
	}
}
