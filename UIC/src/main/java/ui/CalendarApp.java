package ui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import framework.presentation.swing.Window;
import framework.util.DateUtil;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.BorderLayout;
import java.awt.Color;

public class CalendarApp extends JFrame {

	private static final long serialVersionUID = 1L;


	private JPanel gridPanel = new JPanel();
	private JPanel contentPane;

	
	void postInit() {
		gridPanel.setLayout(new GridLayout(6, 7));
		
		DateUtil dtUtil = new DateUtil(new Date());
		
		int mes = dtUtil.getMonth();
		int y 	= dtUtil.getYear();
		int d	= 1;
		
		dtUtil.set(DateUtil.YEAR, y);
		dtUtil.set(DateUtil.MONTH, mes - 1);
		dtUtil.set(DateUtil.DAY_OF_MONTH, d);
		
		int weekDayOfFirstDay = dtUtil.getDayOfWeek();
		int lastDay			  = dtUtil.getActualMaximum(DateUtil.DAY_OF_MONTH);	
		
		setFirstSevenDays(weekDayOfFirstDay);
		
		DateUtil now = new DateUtil(new Date());
		
		for (int i = 2; i <= lastDay; i++) {
			JButton bt = new JButton();
			bt.setText(String.valueOf(i));
			bt.setFont(new Font("Tahoma", Font.BOLD, 14));
			
			if (now.getDayOfMonth() == i) {
				bt.setFont(new Font("Tahoma", Font.BOLD, 14));
				bt.setBackground(Color.WHITE);
				bt.setForeground(Color.RED);
			}
			gridPanel.add(bt);
		}
		int lastButtonRange = lastDay == 30 ? 8 : 7;
		
		for (int i = 1; i <= lastButtonRange; i++) {
			gridPanel.add(new JButton(""));
		}
	}

	private void setFirstSevenDays(int weekDay) {
		DateUtil now 	 = new DateUtil(new Date());
		boolean firstDay = false;
		
		for (int i = 1; i <= 7; i++) {
			if (firstDay) {
				break;
			}
			JButton bt 	 = new JButton();
			String label = "";
			label 		 = weekDay == i ? "1" : label;
			firstDay	 = weekDay == i;
			
			bt.setFont(new Font("Tahoma", Font.BOLD, 14));
			bt.setText(label);
			
			if (now.getDayOfMonth() == 1) {
				bt.setBackground(Color.WHITE);
			}
			gridPanel.add(bt);
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window.setNimbusLookAndFeel();
					
					CalendarApp frame = new CalendarApp();
					frame.postInit();
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
		
		JLabel lblS = new JLabel("D");
		lblS.setHorizontalAlignment(SwingConstants.CENTER);
		lblS.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblS.setBounds(32, 11, 27, 23);
		contentPane.add(lblS);
		
		JLabel lblQ = new JLabel("T");
		lblQ.setHorizontalAlignment(SwingConstants.CENTER);
		lblQ.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQ.setBounds(164, 11, 27, 23);
		contentPane.add(lblQ);
		
		JLabel lblT = new JLabel("S");
		lblT.setHorizontalAlignment(SwingConstants.CENTER);
		lblT.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblT.setBounds(100, 11, 27, 23);
		contentPane.add(lblT);
		
		JLabel lblQ_1 = new JLabel("Q");
		lblQ_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblQ_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQ_1.setBounds(235, 11, 27, 23);
		contentPane.add(lblQ_1);
		
		JLabel lblS_4 = new JLabel("Q");
		lblS_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblS_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblS_4.setBounds(302, 11, 27, 23);
		contentPane.add(lblS_4);
		
		JLabel lblS_4_1 = new JLabel("S");
		lblS_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblS_4_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblS_4_1.setBounds(370, 11, 27, 23);
		contentPane.add(lblS_4_1);
		
		JLabel lblS_4_2 = new JLabel("S");
		lblS_4_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblS_4_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblS_4_2.setBounds(444, 11, 27, 23);
		contentPane.add(lblS_4_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 45, 481, 2);
		contentPane.add(separator);
		
		gridPanel.setBounds(10, 58, 481, 318);
		contentPane.add(gridPanel);
		gridPanel.setLayout(new BorderLayout(0, 0));
	}
}
