package cs414c.pizza.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;

import cs414c.pizza.controller.OrderController;
import cs414c.pizza.domain.Item;
import cs414c.pizza.domain.Order;

import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class ChefWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	
	private OrderController orderController;
	private DefaultListModel listOrdersModel;
	private DefaultListModel listOrderItemsModel;
	private JList listOrders;
	private JList listOrderItems;
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChefWindow frame = new ChefWindow();
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setMinimumSize(new Dimension(570,360));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public ChefWindow(OrderController orderController) {
		this.orderController = orderController;
		setIconImage(Toolkit.getDefaultToolkit().getImage(ChefWindow.class.getResource("/cs414c/pizza/ui/chef.png")));
		setTitle("Chef Station");
		setBounds(100, 100, 570, 360);
		setMinimumSize(new Dimension(570,360));
		
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
		contentPane.add(panelOrders);
		SpringLayout sl_panelOrders = new SpringLayout();
		panelOrders.setLayout(sl_panelOrders);
		
		JScrollPane scrollPane = new JScrollPane();
		sl_panelOrders.putConstraint(SpringLayout.NORTH, scrollPane, 0, SpringLayout.NORTH, panelOrders);
		sl_panelOrders.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, panelOrders);
		sl_panelOrders.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, panelOrders);
		panelOrders.add(scrollPane);
		
		listOrdersModel = new DefaultListModel();
		listOrders = new JList(listOrdersModel);
		listOrders.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				//update Items panel with Items pertaining to the selected Order
				if(arg0.getValueIsAdjusting()){
					Order o = (Order)listOrders.getSelectedValue();
					for(Item i : o.getAllItems()){
						listOrderItemsModel.addElement(i);
					}					
				}
			}
		});
		scrollPane.setViewportView(listOrders);
		
		JPanel panelOrderDetails = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.WEST, panelOrderDetails, 0, SpringLayout.EAST, panelOrders);
		sl_contentPane.putConstraint(SpringLayout.EAST, panelOrderDetails, -5, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panelOrders, 0, SpringLayout.SOUTH, panelOrderDetails);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshOrderList();
			}
		});
		sl_panelOrders.putConstraint(SpringLayout.SOUTH, scrollPane, -5, SpringLayout.NORTH, btnRefresh);
		sl_panelOrders.putConstraint(SpringLayout.WEST, btnRefresh, 0, SpringLayout.WEST, scrollPane);
		sl_panelOrders.putConstraint(SpringLayout.SOUTH, btnRefresh, 0, SpringLayout.SOUTH, panelOrders);
		panelOrders.add(btnRefresh);
		panelOrderDetails.setBorder(new TitledBorder(null, "Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_contentPane.putConstraint(SpringLayout.NORTH, panelOrderDetails, 5, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panelOrderDetails, -5, SpringLayout.SOUTH, contentPane);
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
		
		listOrderItemsModel = new DefaultListModel();
		listOrderItems = new JList(listOrderItemsModel);
		scrollPane_1.setViewportView(listOrderItems);
		
		JButton btnCompleteOrder = new JButton("Complete Order");
		GridBagConstraints gbc_btnCompleteOrder = new GridBagConstraints();
		gbc_btnCompleteOrder.anchor = GridBagConstraints.EAST;
		gbc_btnCompleteOrder.gridx = 0; 
		gbc_btnCompleteOrder.gridy = 4;
		panelOrderDetails.add(btnCompleteOrder, gbc_btnCompleteOrder);
		
		this.refreshOrderList();
	}
	
	private void refreshOrderList(){
		listOrdersModel.removeAllElements();
		for(int oId : orderController.getPlacedOrders()){
			listOrdersModel.addElement(orderController.getOrder(oId));
		}
	}

}
