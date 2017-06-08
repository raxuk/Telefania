package mvc.vista.barraMenu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LookAndFeelDemo {

	private JFrame frame;
	private JButton button;
	private JLabel lafNameLabel;
	private ButtonGroup bg;
	private JRadioButtonMenuItem[] radioItems;

	private UIManager.LookAndFeelInfo[] lafs;

	public LookAndFeelDemo() {
		lafs = UIManager.getInstalledLookAndFeels();
	}

	private ActionListener eventActions = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource() instanceof JRadioButtonMenuItem) {
				JRadioButtonMenuItem radioItem = (JRadioButtonMenuItem) ae.getSource();
				String lafName = radioItem.getActionCommand();
				System.out.println("LAF Name : " + lafName);
				for (int i = 0; i < radioItems.length; i++) {
					if (lafName.equals(radioItems[i].getActionCommand())) {
						setApplicationLookAndFeel(lafs[i].getClassName());
					}
				}
			}
		}

		private void setApplicationLookAndFeel(String className) {
			try {
				UIManager.setLookAndFeel(className);
			} catch (Exception e) {
				e.printStackTrace();
			}
			SwingUtilities.updateComponentTreeUI(frame);
			frame.pack();
		}
	};

	private void displayGUI() {
		frame = new JFrame("");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel contentPane = new JPanel();
		lafNameLabel = new JLabel("Nothing to display yet...", JLabel.CENTER);
		button = new JButton("Start");
		button.addActionListener(eventActions);
		contentPane.add(lafNameLabel);
		contentPane.add(button);

		frame.setJMenuBar(getMenuBar());
		frame.setContentPane(contentPane);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	private JMenuBar getMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu lookAndFeelMenu = new JMenu("Look And Feels");

		bg = new ButtonGroup();
		radioItems = new JRadioButtonMenuItem[lafs.length];
		for (int i = 0; i < radioItems.length; i++) {
			radioItems[i] = new JRadioButtonMenuItem(lafs[i].getName());
			radioItems[i].addActionListener(eventActions);
			bg.add(radioItems[i]);
			lookAndFeelMenu.add(radioItems[i]);
		}

		menuBar.add(lookAndFeelMenu);

		return menuBar;
	}

	public static void main(String[] args) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				new LookAndFeelDemo().displayGUI();
			}
		};
		EventQueue.invokeLater(runnable);
	}
}