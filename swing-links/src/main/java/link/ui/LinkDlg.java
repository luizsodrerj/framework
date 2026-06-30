package link.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import link.entity.Link;
import link.repo.LinksRepository;
import link.util.SystemUtil;

public class LinkDlg extends JDialog {

	private static final long serialVersionUID = 1L;

	
	private LinksRepository repository = new LinksRepository();
	private final JPanel contentPanel = new JPanel();
	private MainFrame mainFrame;
	private JTextField txDesc;
	private JTextField txURL;
	private Link link;
	
	
	
	void btRemoveActionPerformed() {
		int response = JOptionPane.showConfirmDialog(
				            null, 
				            "Tem certeza que quer remover este Link?", 
				            "Confirmar Remover Link", 
				            JOptionPane.YES_NO_OPTION
				        );

        if (response == JOptionPane.YES_OPTION) {
        	repository.removeLink(link);
        	mainFrame.populateLinks();
        	dispose();
        } 		
	}
	
	void copiarURL() {
		SystemUtil.copyToClipBoard(txURL.getText());
	}
	
	
	public void postConstruct(MainFrame mainFrame, Link data) {
		txDesc.setText(data.getDescricao());
		txURL.setText(data.getUrl());
		this.mainFrame = mainFrame;
		link = data;
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LinkDlg dialog = new LinkDlg();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LinkDlg() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1332, 285);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		lblDescrio.setBounds(12, 13, 96, 16);
		contentPanel.add(lblDescrio);
		
		txDesc = new JTextField();
		txDesc.setBounds(12, 35, 585, 29);
		contentPanel.add(txDesc);
		txDesc.setColumns(10);
		
		JLabel lblUrl = new JLabel("URL");
		lblUrl.setBounds(12, 77, 56, 16);
		contentPanel.add(lblUrl);
		
		txURL = new JTextField();
		txURL.setBounds(12, 100, 1278, 29);
		contentPanel.add(txURL);
		txURL.setColumns(10);
		
		JButton btnCopiarUrl = new JButton("Copiar URL");
		btnCopiarUrl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				copiarURL();
			}
		});
		btnCopiarUrl.setBounds(12, 142, 153, 29);
		contentPanel.add(btnCopiarUrl);
		
		JButton btnRemover = new JButton("Remover Link");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btRemoveActionPerformed();
			}
		});
		btnRemover.setBounds(177, 142, 153, 29);
		contentPanel.add(btnRemover);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setPreferredSize(new Dimension(88, 25));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						LinkDlg.this.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
