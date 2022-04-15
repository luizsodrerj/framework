package framework.presentation.swing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import framework.presentation.swing.Window;

public class Form extends JFrame {

	private JPanel contentPane;
	private JTextField data;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form frame = new Form();
			        //Window.setSystemLookAndFeel();
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
	public Form() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 346, 186);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		data = new JTextField();
		data.setBounds(10, 11, 124, 20);
		contentPane.add(data);
		data.setColumns(10);
		
		JButton btnSelecionarData = new JButton("Selecionar Data");
		btnSelecionarData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showCalendar();
			}
		});
		btnSelecionarData.setBounds(144, 10, 140, 23);
		contentPane.add(btnSelecionarData);
	}

	protected void showCalendar() {
		Calendar dialog = new Calendar();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Window.centralizeWindow(dialog);
		dialog.populateDaysOfMonth();
		dialog.setVisible(true);
	}
}






