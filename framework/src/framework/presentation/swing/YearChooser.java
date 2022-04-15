package framework.presentation.swing;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;

import framework.presentation.swing.Window;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class YearChooser extends JDialog {

	private JButton[] btArray = new JButton[12];
	private String currentYear;
	private Calendar parent;
	private JPanel panel;
	

	public YearChooser(Calendar parent, String currentYear) {
		this();
		
		this.currentYear = currentYear;
		this.parent		 = parent;
	}

	protected void onClickAvancar() {
		int nextYear = Integer.parseInt(
						 btArray[btArray.length - 1].getText()
					   );
		for (int i = 0; i < btArray.length; i++) {
			JButton bt = btArray[i];
			bt.setText(String.valueOf(nextYear));
			nextYear++;
		}
	}

	protected void onClickRetro() {
		JButton first = btArray[0];
		int nextYear  = Integer.parseInt(first.getText());
		
		for (int i = btArray.length - 1; i > -1; i--) {
			JButton bt = btArray[i];
			bt.setText(String.valueOf(nextYear));
			nextYear--;
		}
	}
	
	
	public void populateYears() {
		int nextYear = Integer.parseInt(currentYear);
		
		for (int i = 0; i < btArray.length; i++) {
			JButton bt = new JButton();
			btArray[i] = bt;
			bt.setText(String.valueOf(nextYear));
			bt.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton b = (JButton)e.getSource();
					parent.getAno().setText(b.getText());
					parent.onChangeMonth();
					
					YearChooser.this.dispose();
				}
			});
			panel.add(bt);
			nextYear++;
		}
	}
	
	/**
	 * Create the dialog.
	 */
	public YearChooser() {
		setBounds(100, 100, 336, 260);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(10, 11, 300, 140);
		getContentPane().add(panel);
		
		JButton retro = new JButton("Retroceder");
		retro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClickRetro();
			}
		});
		retro.setBounds(10, 162, 141, 26);
		getContentPane().add(retro);
		
		JButton avancar = new JButton("Avan\u00E7ar");
		avancar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClickAvancar();
			}
		});
		avancar.setBounds(169, 162, 141, 26);
		getContentPane().add(avancar);

	}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YearChooser dialog = new YearChooser(null, "2022");
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					Window.centralizeWindow(dialog);
					dialog.populateYears();
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
