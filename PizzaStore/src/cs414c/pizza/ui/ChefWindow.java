package cs414c.pizza.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.JButton;

public class ChefWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable tableOrderItems;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChefWindow frame = new ChefWindow();
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
	public ChefWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ChefWindow.class.getResource("/cs414c/pizza/ui/chef.png")));
		setTitle("Chef Station");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 360);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mnFile.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JPanel panelOrders = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.EAST, panelOrders, 200, SpringLayout.WEST, contentPane);
		panelOrders.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Orders", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_contentPane.putConstraint(SpringLayout.NORTH, panelOrders, 5, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, panelOrders, 5, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panelOrders, -5, SpringLayout.SOUTH, contentPane);
		contentPane.add(panelOrders);
		SpringLayout sl_panelOrders = new SpringLayout();
		panelOrders.setLayout(sl_panelOrders);
		
		JScrollPane scrollPane = new JScrollPane();
		sl_panelOrders.putConstraint(SpringLayout.NORTH, scrollPane, 0, SpringLayout.NORTH, panelOrders);
		sl_panelOrders.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, panelOrders);
		sl_panelOrders.putConstraint(SpringLayout.SOUTH, scrollPane, 0, SpringLayout.SOUTH, panelOrders);
		sl_panelOrders.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, panelOrders);
		panelOrders.add(scrollPane);
		
		JList listOrders = new JList();
		scrollPane.setViewportView(listOrders);
		
		JPanel panelOrderDetails = new JPanel();
		panelOrderDetails.setBorder(new TitledBorder(null, "Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_contentPane.putConstraint(SpringLayout.NORTH, panelOrderDetails, 5, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, panelOrderDetails, 5, SpringLayout.EAST, panelOrders);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panelOrderDetails, -5, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, panelOrderDetails, -5, SpringLayout.EAST, contentPane);
		contentPane.add(panelOrderDetails);
		GridBagLayout gbl_panelOrderDetails = new GridBagLayout();
		gbl_panelOrderDetails.columnWidths = new int[]{0, 0};
		gbl_panelOrderDetails.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelOrderDetails.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelOrderDetails.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelOrderDetails.setLayout(gbl_panelOrderDetails);
		
		JLabel lblCustomerName = new JLabel("Customer Name");
		GridBagConstraints gbc_lblCustomerName = new GridBagConstraints();
		gbc_lblCustomerName.anchor = GridBagConstraints.WEST;
		gbc_lblCustomerName.insets = new Insets(0, 0, 5, 0);
		gbc_lblCustomerName.gridx = 0;
		gbc_lblCustomerName.gridy = 0;
		panelOrderDetails.add(lblCustomerName, gbc_lblCustomerName);
		
		textField = new JTextField();
		textField.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		panelOrderDetails.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblItems = new JLabel("Items");
		GridBagConstraints gbc_lblItems = new GridBagConstraints();
		gbc_lblItems.insets = new Insets(0, 0, 5, 0);
		gbc_lblItems.anchor = GridBagConstraints.WEST;
		gbc_lblItems.gridx = 0;
		gbc_lblItems.gridy = 2;
		panelOrderDetails.add(lblItems, gbc_lblItems);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 3;
		panelOrderDetails.add(scrollPane_1, gbc_scrollPane_1);
		
		tableOrderItems = new JTable();
		tableOrderItems.setFillsViewportHeight(true);
		scrollPane_1.setViewportView(tableOrderItems);
		
		JButton btnCompleteOrder = new JButton("Complete Order");
		GridBagConstraints gbc_btnCompleteOrder = new GridBagConstraints();
		gbc_btnCompleteOrder.anchor = GridBagConstraints.EAST;
		gbc_btnCompleteOrder.gridx = 0;
		gbc_btnCompleteOrder.gridy = 4;
		panelOrderDetails.add(btnCompleteOrder, gbc_btnCompleteOrder);
	}

}
