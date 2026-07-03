package agenda.ui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import agenda.entity.Contato;
import agenda.repo.AgendaRepository;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DadosContatoDlg extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField nome;
	private JTextField endereco;
	private JTextField ref;
	private JTextField telefones;
	private JTextField emails;
	private JTextArea obs; 

	private AgendaRepository repository = new AgendaRepository();
	private Contato contato;
	

	
	void salvarActionPerformed() {
		contato.setContato(nome.getText());
		contato.setEndereco(endereco.getText());
		contato.setReferencia(ref.getText());
		contato.setTelefones(telefones.getText());
		contato.setEmails(emails.getText());
		contato.setObs(obs.getText());
		
		repository.update(contato);
		
		JOptionPane.showMessageDialog(this, "Contato salvo com sucesso!");
	}
	
	public DadosContatoDlg(Contato contato) {
		this();
		
		nome.setText(contato.getContato());
		endereco.setText(contato.getEndereco());
		ref.setText(contato.getReferencia());
		telefones.setText(contato.getTelefones());
		emails.setText(contato.getEmails());
		obs.setText(contato.getObs());
		
		this.contato = contato;
	}
	
	/**
	 * Create the dialog.
	 */
	public DadosContatoDlg() {
		setBounds(100, 100, 1001, 756);
		getContentPane().setLayout(null);
		
		JLabel lblNomeContato = new JLabel("Nome Contato");
		lblNomeContato.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomeContato.setBounds(12, 16, 98, 16);
		getContentPane().add(lblNomeContato);
		
		nome = new JTextField();
		nome.setBounds(115, 10, 722, 28);
		getContentPane().add(nome);
		nome.setColumns(10);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEndereo.setBounds(44, 62, 66, 16);
		getContentPane().add(lblEndereo);
		
		endereco = new JTextField();
		endereco.setBounds(115, 56, 722, 28);
		getContentPane().add(endereco);
		endereco.setColumns(10);
		
		JLabel lblReferncia = new JLabel("Refer\u00EAncia");
		lblReferncia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReferncia.setBounds(44, 107, 66, 16);
		getContentPane().add(lblReferncia);
		
		ref = new JTextField();
		ref.setBounds(115, 101, 722, 28);
		getContentPane().add(ref);
		ref.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Telefones");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(12, 151, 98, 16);
		getContentPane().add(lblNewLabel);
		
		telefones = new JTextField();
		telefones.setBounds(115, 145, 722, 28);
		getContentPane().add(telefones);
		telefones.setColumns(10);
		
		emails = new JTextField();
		emails.setBounds(115, 186, 722, 28);
		getContentPane().add(emails);
		emails.setColumns(10);
		
		JLabel lblEmails = new JLabel("E-mails");
		lblEmails.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmails.setBounds(44, 192, 66, 16);
		getContentPane().add(lblEmails);
		
		JPanel panel = new JPanel();
		panel.setBounds(115, 227, 722, 469);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		obs = new JTextArea();
		scrollPane.setViewportView(obs);
		
		JLabel lblObs = new JLabel("Obs");
		lblObs.setHorizontalAlignment(SwingConstants.RIGHT);
		lblObs.setBounds(47, 227, 56, 16);
		getContentPane().add(lblObs);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salvarActionPerformed();
			}
		});
		btnSalvar.setBounds(849, 10, 120, 29);
		getContentPane().add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DadosContatoDlg.this.dispose(); 
			}
		});
		btnCancelar.setBounds(849, 47, 120, 31);
		getContentPane().add(btnCancelar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setBounds(849, 85, 120, 28);
		getContentPane().add(btnDeletar);

	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DadosContatoDlg dialog = new DadosContatoDlg();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
