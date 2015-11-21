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
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;

import cs414c.pizza.controller.LoginControllerInterface;
import cs414c.pizza.controller.MenuControllerInterface;
import cs414c.pizza.controller.OrderControllerInterface;
import cs414c.pizza.controller.PaymentControllerInterface;
import cs414c.pizza.domain.Item;
import cs414c.pizza.domain.Order;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
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

public class OrderManagement extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	private OrderControllerInterface orderController;
	private LoginControllerInterface loginController;
	private DefaultListModel<OrderEntry> listOrdersModel;
	private DefaultListModel<OrderItemEntry> listOrderItemsModel;
	private JList<OrderEntry> listOrders;
	private JList<OrderItemEntry> listOrderItems;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry();
			OrderControllerInterface orderStub = (OrderControllerInterface) registry.lookup("OrderController");
			LoginControllerInterface loginStub = (LoginControllerInterface) registry.lookup("LoginController");
			Login login = new OrderManagementLogin(loginStub, orderStub);
			login.setVisible(true);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public OrderManagement(OrderControllerInterface orderControllerParameter) {
		this.orderController = orderControllerParameter;
		setIconImage(Toolkit.getDefaultToolkit().getImage(OrderManagement.class.getResource("/cs414c/pizza/ui/order_icon.png")));
		setTitle("Order Management");
		setBounds(100, 100, 570, 360);
		setMinimumSize(new Dimension(570,360));

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

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
		
		listOrdersModel = new DefaultListModel<OrderEntry>();
		listOrders = new JList<OrderEntry>(listOrdersModel);
		
		listOrders.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				//update Items panel with Items pertaining to the selected Order
				if(arg0.getValueIsAdjusting()){
					listOrderItemsModel.removeAllElements();
					OrderEntry entry = listOrders.getSelectedValue();
					for(OrderItemEntry i : entry.getItems()){
						listOrderItemsModel.addElement(i);
					}
					textField.setText(entry.getOrderName());
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
		
		listOrderItemsModel = new DefaultListModel<OrderItemEntry>();
		listOrderItems = new JList<OrderItemEntry>(listOrderItemsModel);

		scrollPane_1.setViewportView(listOrderItems);

		JButton btnCompleteOrder = new JButton("Complete Order");
		btnCompleteOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int orderId = listOrders.getSelectedValue().getOrderId();
				try {
					orderController.completeOrder(orderId);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				listOrderItemsModel.removeAllElements();
				listOrdersModel.removeAllElements();
				textField.setText("");
				refreshOrderList();
			}
		});
		GridBagConstraints gbc_btnCompleteOrder = new GridBagConstraints();
		gbc_btnCompleteOrder.anchor = GridBagConstraints.EAST;
		gbc_btnCompleteOrder.gridx = 0; 
		gbc_btnCompleteOrder.gridy = 4;
		panelOrderDetails.add(btnCompleteOrder, gbc_btnCompleteOrder);

		this.refreshOrderList();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(570,360));
		setVisible(true);
	}

	private void refreshOrderList(){
		listOrdersModel.removeAllElements();
		try {
			for(int oId : orderController.getPlacedOrders()){
				listOrdersModel.addElement(orderController.getFullOrder(oId));
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		listOrderItemsModel.removeAllElements();
		textField.setText("");
	}

}
