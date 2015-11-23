package cs414c.pizza.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cs414c.pizza.controller.LoginControllerInterface;
import cs414c.pizza.controller.OrderControllerInterface;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import cs414c.pizza.util.Enum.OrderStatus;

public class OrderManagement extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private OrderControllerInterface orderController;
	private LoginControllerInterface loginController;
	private DefaultListModel<OrderEntry> listOrdersModel;
	private DefaultListModel<OrderItemEntry> listOrderItemsModel;
	private JList<OrderEntry> listOrders;

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
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(OrderManagement.class.getResource("/cs414c/pizza/ui/order_icon.png")));
		setTitle("Order Management");
		setBounds(100, 100, 570, 360);
		setMinimumSize(new Dimension(570, 360));

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
		sl_contentPane.putConstraint(SpringLayout.NORTH, panelOrders, 50, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panelOrders, -5, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, panelOrders, -5, SpringLayout.EAST, contentPane);
		panelOrders.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Orders",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		scrollPane.setViewportView(listOrders);

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
		
		JPanel panel = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, panel, 0, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panel, 50, SpringLayout.NORTH, contentPane);
		panel.setBorder(new TitledBorder(null, "Status", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_contentPane.putConstraint(SpringLayout.WEST, panel, 5, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, panel, -5, SpringLayout.EAST, contentPane);
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(OrderStatus.values()));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 0;
		panel.add(comboBox, gbc_comboBox);
		
		JButton btnSetStatus = new JButton("Set Status");
		GridBagConstraints gbc_btnSetStatus = new GridBagConstraints();
		gbc_btnSetStatus.gridx = 1;
		gbc_btnSetStatus.gridy = 0;
		panel.add(btnSetStatus, gbc_btnSetStatus);

		listOrderItemsModel = new DefaultListModel<OrderItemEntry>();

		this.refreshOrderList();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(570, 360));
		setVisible(true);
	}

	private void refreshOrderList() {
		listOrdersModel.removeAllElements();
		try {
			for (int oId : orderController.getOrders()) {
				listOrdersModel.addElement(orderController.getFullOrder(oId));
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		listOrderItemsModel.removeAllElements();
	}
}
