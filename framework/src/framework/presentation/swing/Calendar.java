package framework.presentation.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import framework.util.DateUtil;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Calendar extends JDialog {

	private Dimension defaultBtDim = new Dimension(48,23);
	
	//private List<JButton>days = new ArrayList<JButton>();
	
	private JPanel panel;
	private JTextField ano;
	private JComboBox mes;
	private JButton currButton;

	

	protected void showYearChooser() {
		YearChooser dialog = new YearChooser(this, ano.getText());
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Window.centralizeWindow(dialog);
		dialog.populateYears();
		dialog.setVisible(true);
	}
	
	public void onChangeMonth() {
		if (mes.getSelectedIndex() > -1 && !"".equals(ano.getText())) {
			int monthIndex 	= mes.getSelectedIndex();
			DateUtil date 	= new DateUtil();
			
			date.set(DateUtil.YEAR, Integer.parseInt(ano.getText()));
			date.set(DateUtil.MONTH, monthIndex);
			
			panel.removeAll();
			populateDaysOfMonth(date);
			panel.revalidate();
			panel.repaint();
		}
	}
	
	public void populateDaysOfMonth() {
		DateUtil date = new DateUtil();
		
		populateDaysOfMonth(date);
		
		populateMeses(date);
	}

	private void populateDaysOfMonth(DateUtil date) {
		date.set(DateUtil.DAY_OF_MONTH, 1);
		setFirstDay(date);
		
		int lastDay = date.getActualMaximum(DateUtil.DAY_OF_MONTH);
		int currDay = 2;
		
		while (currDay <= lastDay) {
			String label = currDay < 10 ? "0" + String.valueOf(currDay): String.valueOf(currDay);
			JButton day  = new JButton(label);
			day.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton bt = (JButton)e.getSource();
					Color defaultBack = bt.getBackground();
					Color defaultFore = bt.getForeground();
					bt.setBackground(Color.BLACK);
					bt.setForeground(Color.WHITE);
					
					if (currButton != null) {
						currButton.setBackground(defaultBack);
						currButton.setForeground(defaultFore);
					}
					currButton = bt;
				}
			});
			panel.add(day);
			currDay++;
		}
		date.set(DateUtil.DAY_OF_MONTH, lastDay);
		completeCalendar(date);
	}
	
	private void populateMeses(DateUtil date) {
		String[]meses = new String[] {
				            "Janeiro",
				            "Fevereiro",
				            "Mar\u00E7o",
				            "Abril",
				            "Maio",
				            "Junho",
				            "Julho",
				            "Agosto",
				            "Setembro",
				            "Outubro",
				            "Novembro",
				            "Dezembro"
						};
		for (String mes: meses) {
			this.mes.addItem(mes);
		}
		int currMonth = date.getMonth();
		int year	  = date.getYear();	
		
		this.mes.setSelectedIndex(currMonth - 1);
		ano.setText(String.valueOf(year));
	}

	private void completeCalendar(DateUtil date) {
		int dOfWeek = date.getDayOfWeek();
		int[]days   = new int[] {
					DateUtil.SUNDAY,
					DateUtil.MONDAY,
					DateUtil.TUESDAY,
					DateUtil.WEDNESDAY,
					DateUtil.THURSDAY,
					DateUtil.FRIDAY,
					DateUtil.SATURDAY
				};
		for (int day : days) {
			JButton bt = null;
			
			if (day > dOfWeek) {
				bt = new JButton("  ");
				bt.setPreferredSize(defaultBtDim);
				panel.add(bt);
			}
		}
	}
	
	private void setFirstDay(DateUtil date) {
		int dOfWeek = date.getDayOfWeek();
		int[]days   = new int[] {
					DateUtil.SUNDAY,
					DateUtil.MONDAY,
					DateUtil.TUESDAY,
					DateUtil.WEDNESDAY,
					DateUtil.THURSDAY,
					DateUtil.FRIDAY,
					DateUtil.SATURDAY
				};
		for (int day : days) {
			JButton bt = null;
			
			if (day == dOfWeek) {
				bt = new JButton("01");
				bt.setPreferredSize(defaultBtDim);
				panel.add(bt);
				break;
			} else {
				bt = new JButton("  ");
				bt.setPreferredSize(defaultBtDim);
			}
			panel.add(bt);
		}
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calendar dialog = new Calendar();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.populateDaysOfMonth();
					Window.centralizeWindow(dialog);
					dialog.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Calendar() {
		setBounds(100, 100, 432, 400);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(10, 87, 396, 200);
		getContentPane().add(panel);
		
		JPanel weekDayPanel = new JPanel();
		weekDayPanel.setBounds(10, 44, 396, 30);
		getContentPane().add(weekDayPanel);
		initWeekDayPanel(weekDayPanel);
		
		mes = new JComboBox();
		mes.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				onChangeMonth();
			}
		});
		mes.setBounds(10, 11, 170, 20);
		getContentPane().add(mes);
		
		ano = new JTextField();
		ano.setBounds(192, 11, 72, 20);
		getContentPane().add(ano);
		ano.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 299, 396, 8);
		getContentPane().add(separator);
		
		JButton yearChooser = new JButton("Selecinar Ano");
		yearChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showYearChooser();
			}
		});
		yearChooser.setBounds(276, 8, 130, 26);
		getContentPane().add(yearChooser);
		
	}
	

	void initWeekDayPanel(JPanel weekDayPanel) {
		String[] weekDays = new String[]{
			"D", "S", "T", "Q", "Q", "S", "S",	
		};
		for (String day: weekDays) {
			JLabel label = new JLabel(day);
			label.setPreferredSize(defaultBtDim);
			label.setHorizontalAlignment(JLabel.CENTER);
			weekDayPanel.add(label);
		}
	}
	
	public JTextField getAno() {
		return ano;
	}
}




