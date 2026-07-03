package agenda.ui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import agenda.entity.Contato;
import agenda.repo.AgendaRepository;
import framework.presentation.swing.Window;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JComboBox comboContatos;
	private JTextField contatosInput;
	private JPanel contentPane;

	private AgendaRepository repository = new AgendaRepository(); 

	private List<Contato>contatos = new ArrayList<>();
	
	private boolean ajustandoLista = false;
	
	
	
	void selecionarContato() {
		String criterio = comboContatos.getSelectedItem().toString().toUpperCase();
		Contato contato = repository.getSingleResultByNamedQuery("Contato.findByContato",new Object[]{criterio});
		
		DadosContatoDlg dialog = new DadosContatoDlg(contato);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Window.centralizeWindow(dialog);
		dialog.setVisible(true);
	}
	
	
	void autoCompleteListaContatos() {
        if (ajustandoLista) {
        	return;
        }

        SwingUtilities.invokeLater(() -> {
            ajustandoLista = true;
            
            String textoDigitado = contatosInput.getText().toLowerCase();
            comboContatos.removeAllItems();

            if (!textoDigitado.isEmpty()) {
            	contatos.forEach(contato -> {
                    if (contato.getContato().toLowerCase().contains(textoDigitado)) {
                    	comboContatos.addItem(contato.getContato());
                    }
            	});
            } else {
        		contatos.forEach(contato -> {
        			comboContatos.addItem(contato.getContato());	
        		});
            }

            contatosInput.setText(textoDigitado);
            if (comboContatos.getItemCount() > 0 && textoDigitado.length() > 0) {
                comboContatos.setPopupVisible(true);
            } else {
                comboContatos.setPopupVisible(false);
            }

            ajustandoLista = false;
        });		
	}
	
	
	private void postConstruct() {
		comboContatos.setEditable(true);
		
		List<Contato>list = repository.getAll();
		contatos.clear();
		contatos.addAll(list);
		contatos.forEach(contato -> {
			comboContatos.addItem(contato.getContato());	
		});
		
		contatosInput = (JTextField)comboContatos.getEditor().getEditorComponent();
		contatosInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { 
            	autoCompleteListaContatos(); 
            }
            @Override
            public void removeUpdate(DocumentEvent e) { 
            	autoCompleteListaContatos(); 
            }
            @Override
            public void changedUpdate(DocumentEvent e) { 
            	autoCompleteListaContatos(); 
            }
        });		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window.setNimbusLookAndFeel();
					MainFrame frame = new MainFrame();
					Window.centralizeWindow(frame);
					frame.postConstruct();
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboContatos = new JComboBox();
		comboContatos.setBounds(12, 13, 468, 33);
		contentPane.add(comboContatos);
		
		JButton btnSelecionarContato = new JButton("Selecionar Contato");
		btnSelecionarContato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selecionarContato();
			}
		});
		btnSelecionarContato.setBounds(12, 59, 468, 33);
		contentPane.add(btnSelecionarContato);
	}
}
