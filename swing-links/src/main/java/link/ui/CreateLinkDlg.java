package link.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import link.repo.LinksRepository;

public class CreateLinkDlg extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private MainFrame mainFrame;
	private JTextField txDescricao;
	private JTextField txURL;

	private LinksRepository repository = new LinksRepository();
	
	
	
	void okActionPerformed() {
		repository.persistLink(
			txDescricao.getText(), 
			txURL.getText()
		);
		mainFrame.populateLinks();
		dispose();
	}
	
	
	public CreateLinkDlg(MainFrame mainFrame) {
		this();
		this.mainFrame = mainFrame;
	}
	
	/**
	 * Create the dialog.
	 */
	public CreateLinkDlg() {
		setBounds(100, 100, 1029, 264);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 999, 218);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o");
			lblDescrio.setBounds(12, 13, 141, 16);
			contentPanel.add(lblDescrio);
		}
		{
			txDescricao = new JTextField();
			txDescricao.setBounds(12, 33, 479, 28);
			contentPanel.add(txDescricao);
			txDescricao.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("URL");
			lblNewLabel.setBounds(12, 74, 56, 16);
			contentPanel.add(lblNewLabel);
		}
		{
			txURL = new JTextField();
			txURL.setBounds(12, 93, 985, 28);
			contentPanel.add(txURL);
			txURL.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 170, 997, 35);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg) {
						okActionPerformed();
					}
				});
				okButton.setBounds(12, 5, 88, 25);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						CreateLinkDlg.this.dispose();
					}
				});
				cancelButton.setBounds(112, 5, 88, 25);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateLinkDlg dialog = new CreateLinkDlg();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
