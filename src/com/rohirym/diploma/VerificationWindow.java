package com.rohirym.diploma;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class VerificationWindow {

	private String dataBaseName;
	private String login;
	private String password;

	private DataBaseReader baseReader;

	private JFrame jFrame;
	private JButton b1;
	private JLabel l1, l2, l3;
	private JTextField t1, t2;
	private JPasswordField p1;

	private eHandler handler;

	public VerificationWindow(String s) {
		jFrame = new JFrame(s);
		jFrame.setLayout(new GridLayout(0, 1));
		l1 = new JLabel("Назва бази даних:");
		l1.setBackground(Color.lightGray);
		l1.setOpaque(true);
		l2 = new JLabel("Логін:");
		l2.setBackground(Color.lightGray);
		l2.setOpaque(true);
		l3 = new JLabel("Пароль:");
		l3.setBackground(Color.lightGray);
		l3.setOpaque(true);
		t1 = new JTextField(10);
		t2 = new JTextField(10);
		p1 = new JPasswordField(10);
		b1 = new JButton("Підтвердити");
		b1.setBackground(Color.BLACK);
		b1.setForeground(Color.WHITE);
		b1.setOpaque(true);
		
		jFrame.add(l1);
		jFrame.add(t1);
		jFrame.add(l2);
		jFrame.add(t2);
		jFrame.add(l3);
		jFrame.add(p1);
		jFrame.add(b1);

		handler = new eHandler();
		b1.addActionListener(handler);

		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setSize(250, 250);
		jFrame.setResizable(false);
		jFrame.setLocationRelativeTo(null);
	}

	public class eHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getSource() == b1) {
					dataBaseName = t1.getText();
					login = t2.getText();
					password = p1.getText();
					baseReader = new DataBaseReader(dataBaseName, login,
							password);
					HashMap<String, ArrayList<DateAndSales>> observationsOfShops = baseReader
							.getObservationsOfShops();
					jFrame.setVisible(false);
					new MenuWindow("Вибір торгових об'єктів", observationsOfShops);
				}
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Поля введені невірно!");
			}
		}
	}

}
