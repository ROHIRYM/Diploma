package com.rohirym.diploma;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MenuWindow {

	private HashMap<String, ArrayList<DateAndSales>> observationsOfShops;

	private JFrame jFrame;

	private JCheckBox[] checks;
	private JButton b1;

	private eHandler handler;

	public MenuWindow(String name,
			HashMap<String, ArrayList<DateAndSales>> observationsOfShops) {
		this.observationsOfShops = observationsOfShops;
		jFrame = new JFrame(name);
		jFrame.setLayout(new GridLayout(0, 1));

		int size = observationsOfShops.size();
		checks = new JCheckBox[size];

		int i = 0;
		Iterator<Map.Entry<String, ArrayList<DateAndSales>>> iterator = observationsOfShops
				.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, ArrayList<DateAndSales>> pair = iterator.next();
			checks[i] = new JCheckBox(pair.getKey(), false);
			jFrame.add(checks[i]);
			i++;
		}

		b1 = new JButton("Показати прогнози");
		b1.setBackground(Color.BLACK);
		b1.setForeground(Color.WHITE);
		b1.setOpaque(true);

		jFrame.add(b1);

		handler = new eHandler();
		b1.addActionListener(handler);

		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setSize(300, size * 50 + 50);
		jFrame.setResizable(false);
		jFrame.setLocationRelativeTo(null);
	}

	public class eHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			HashMap<String, ArrayList<DateAndSales>> observations;
			if (e.getSource() == b1) {
				observations = new HashMap<>();
				for (JCheckBox check : checks) {
					if (check.isSelected()) {
						ArrayList<DateAndSales> shopSales = observationsOfShops
								.get(check.getText());
						observations.put(check.getText(), shopSales);
					}
				}
				if (observations.isEmpty()) {
					observations = new HashMap<>();
					JOptionPane.showMessageDialog(null,
							"Виберіть торгові об'єкти!");
				} else {
					new Graphics(observations);
					observations = new HashMap<>();
				}
			}
		}
	}
}
