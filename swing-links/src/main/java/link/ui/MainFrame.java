package link.ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import framework.persistence.jpa.PersistenceServiceUtil;
import framework.presentation.swing.Window;
import link.entity.Link;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	
	private DefaultTableModel tabModel = new DefaultTableModel(); 
	private JPanel contentPane;
	private JTextField txSearch;
	private JTable linksTab;

	private PersistenceServiceUtil persistence = new PersistenceServiceUtil();
	
	private List<Link>linksList = new ArrayList<>(); 
	
	
	
	void newLinkActionPerformed() {
		CreateLinkDlg dialog = new CreateLinkDlg(this);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Window.centralizeWindow(dialog);
		dialog.setVisible(true);
	}

	void pesquisarActionPerformed() {
		try {
			String desc = txSearch.getText().toUpperCase();
			String url  = txSearch.getText().toUpperCase();
			List<Link>list = persistence.findByNamedQuery(
								Link.FIND_BY_FILTER, 
								new Object[] {
									"%" + desc + "%",
									"%" + url + "%"
								}
							);
			populateTable(list);
			
		} finally {
			persistence.close();
		}		
	}

	public void populateLinks() {
		try {
			List<Link>list = persistence.findAll(Link.class, null);
			populateTable(list);
			
			//((DataTable)linksTab).setData(linksList);
		} finally {
			persistence.close();
		}		
	}
	
	private void populateTable(List<Link>list) {
		tabModel.setRowCount(0);
		linksList.clear();
		linksList.addAll(list);
		linksList.forEach(link -> {
			tabModel.addRow(new Object[]{
				link.getDescricao(),
				link.getUrl()
			});
		});
	}

	private void onSelectRow() {
		int row = linksTab.getSelectedRow();
		
        if (row != -1) {
        	Link link = linksList.get(row);
			LinkDlg dialog = new LinkDlg();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.postConstruct(this,link);
			Window.centralizeWindow(dialog);
			dialog.setVisible(true);
        }
	}
	
	public void postConstruct() {
		tabModel.setColumnIdentifiers(new Object[] {
			"Descri\u00E7\u00E3o",
			"URL"
		});
		linksTab.getTableHeader().setFont(linksTab.getTableHeader().getFont().deriveFont(Font.BOLD));
		linksTab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		linksTab.setModel(tabModel);
		
		TableColumnModel columnModel = linksTab.getColumnModel();

		columnModel.getColumn(0).setCellRenderer(new BoldRenderer());
		columnModel.getColumn(0).setPreferredWidth(455);
		
		columnModel.getColumn(1).setCellRenderer(new BoldRenderer());
		columnModel.getColumn(1).setPreferredWidth(1075);

		linksTab.getSelectionModel().addListSelectionListener(e -> {
		    if (!e.getValueIsAdjusting()) {
		    	onSelectRow();
		    }
		});
		
		populateLinks();
	}
	
	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1346, 747);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txSearch = new JTextField();
		txSearch.setBounds(12, 13, 539, 32);
		contentPane.add(txSearch);
		txSearch.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				pesquisarActionPerformed();
			}
		});
		btnPesquisar.setBounds(563, 13, 113, 32);
		contentPane.add(btnPesquisar);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 58, 1304, 629);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		//linksTab = new DataTable();
		linksTab = new JTable();
		scrollPane.setViewportView(linksTab);
		
		JButton btnNovoLink = new JButton("Novo Link");
		btnNovoLink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newLinkActionPerformed();
			}
		});
		btnNovoLink.setBounds(688, 13, 113, 32);
		contentPane.add(btnNovoLink);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window.setNimbusLookAndFeel();
					MainFrame frame = new MainFrame();
					frame.postConstruct();
					Window.centralizeWindow(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
}
