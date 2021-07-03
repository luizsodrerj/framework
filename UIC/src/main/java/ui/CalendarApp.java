package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class CalendarApp extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalendarApp frame = new CalendarApp();
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
	public CalendarApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 431);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblS = new JLabel("S");
		lblS.setHorizontalAlignment(SwingConstants.CENTER);
		lblS.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblS.setBounds(10, 11, 27, 23);
		contentPane.add(lblS);
		
		JLabel lblQ = new JLabel("Q");
		lblQ.setHorizontalAlignment(SwingConstants.CENTER);
		lblQ.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQ.setBounds(153, 11, 27, 23);
		contentPane.add(lblQ);
		
		JLabel lblT = new JLabel("T");
		lblT.setHorizontalAlignment(SwingConstants.CENTER);
		lblT.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblT.setBounds(81, 11, 27, 23);
		contentPane.add(lblT);
		
		JLabel lblQ_1 = new JLabel("Q");
		lblQ_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblQ_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQ_1.setBounds(222, 11, 27, 23);
		contentPane.add(lblQ_1);
		
		JLabel lblS_4 = new JLabel("S");
		lblS_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblS_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblS_4.setBounds(299, 11, 27, 23);
		contentPane.add(lblS_4);
		
		JLabel lblS_4_1 = new JLabel("S");
		lblS_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblS_4_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblS_4_1.setBounds(375, 11, 27, 23);
		contentPane.add(lblS_4_1);
		
		JLabel lblS_4_2 = new JLabel("D");
		lblS_4_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblS_4_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblS_4_2.setBounds(454, 11, 27, 23);
		contentPane.add(lblS_4_2);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(59, 11, 12, 365);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(131, 11, 12, 365);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(200, 11, 12, 365);
		contentPane.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(277, 11, 12, 365);
		contentPane.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(353, 11, 12, 365);
		contentPane.add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setOrientation(SwingConstants.VERTICAL);
		separator_5.setBounds(426, 11, 12, 365);
		contentPane.add(separator_5);
	}
}
