package cs414c.pizza.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JTable;

public class ManagerWindow extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTable table_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(
				            UIManager.getSystemLookAndFeelClassName());
					ManagerWindow frame = new ManagerWindow();
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
	public ManagerWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ManagerWindow.class.getResource("/com/sun/java/swing/plaf/windows/icons/ListView.gif")));
		setTitle("Management Suite");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 678, 468);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		mnFile.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JPanel panel = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.EAST, panel, 300, SpringLayout.WEST, contentPane);
		panel.setBorder(new TitledBorder(null, "Items", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_contentPane.putConstraint(SpringLayout.NORTH, panel, 5, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panel, -5, SpringLayout.SOUTH, contentPane);
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblPizzas = new JLabel("Pizzas");
		GridBagConstraints gbc_lblPizzas = new GridBagConstraints();
		gbc_lblPizzas.anchor = GridBagConstraints.WEST;
		gbc_lblPizzas.insets = new Insets(0, 0, 5, 0);
		gbc_lblPizzas.gridx = 0;
		gbc_lblPizzas.gridy = 0;
		panel.add(lblPizzas, gbc_lblPizzas);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		panel.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		
		JLabel lblSidesdrinks = new JLabel("Sides/Drinks");
		GridBagConstraints gbc_lblSidesdrinks = new GridBagConstraints();
		gbc_lblSidesdrinks.anchor = GridBagConstraints.WEST;
		gbc_lblSidesdrinks.insets = new Insets(0, 0, 5, 0);
		gbc_lblSidesdrinks.gridx = 0;
		gbc_lblSidesdrinks.gridy = 2;
		panel.add(lblSidesdrinks, gbc_lblSidesdrinks);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 3;
		panel.add(scrollPane_1, gbc_scrollPane_1);
		
		table_1 = new JTable();
		table_1.setFillsViewportHeight(true);
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblSpecials = new JLabel("Specials");
		GridBagConstraints gbc_lblSpecials = new GridBagConstraints();
		gbc_lblSpecials.insets = new Insets(0, 0, 5, 0);
		gbc_lblSpecials.anchor = GridBagConstraints.WEST;
		gbc_lblSpecials.gridx = 0;
		gbc_lblSpecials.gridy = 4;
		panel.add(lblSpecials, gbc_lblSpecials);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 0;
		gbc_scrollPane_2.gridy = 5;
		panel.add(scrollPane_2, gbc_scrollPane_2);
		
		table_2 = new JTable();
		table_2.setFillsViewportHeight(true);
		scrollPane_2.setViewportView(table_2);
	}
}
