package unicodeconv;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.commons.lang3.StringEscapeUtils;

import framework.presentation.swing.Window;

public class UnicodeConvGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField input;
	private JTextArea result;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnicodeConvGUI frame = new UnicodeConvGUI();
					Window.centralizeWindow(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	void converter() {
		result.setText(
			StringEscapeUtils.escapeJava(
				input.getText()
			)
		);
	}

	
	/**
	 * Create the frame.
	 */
	public UnicodeConvGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 828, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		input = new JTextField();
		input.setFont(new Font("Tahoma", Font.PLAIN, 15));
		input.setBounds(12, 13, 654, 32);
		contentPane.add(input);
		input.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 64, 654, 149);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		result = new JTextArea();
		result.setFont(new Font("Monospaced", Font.PLAIN, 15));
		scrollPane.setViewportView(result);
		
		JButton btnConverter = new JButton("Converter");
		btnConverter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				converter();
			}
		});
		btnConverter.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnConverter.setBounds(678, 13, 120, 32);
		contentPane.add(btnConverter);
	}

}
