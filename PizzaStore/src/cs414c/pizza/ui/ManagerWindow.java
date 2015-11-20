package cs414c.pizza.ui;

import java.awt.Dimension;
import java.awt.EventQueue;
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
import java.util.List;
import java.util.UUID;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import cs414c.pizza.controller.LoginControllerInterface;
import cs414c.pizza.controller.MenuControllerInterface;
import cs414c.pizza.controller.OrderControllerInterface;
import junit.framework.TestFailure;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;

public class ManagerWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldPizzaName;
	private JTextField textFieldPizzaBasePrice;
	private JTextField textFieldToppingName;
	private JTextField textFieldToppingPrice;
	private JTextField textFieldSideName;
	private JTextField textFieldSidePrice;

	private MenuControllerInterface menuController;
	private LoginControllerInterface loginController;

	private JTextField textFieldUserNameAdd;
	private JTextField textFieldPasswordAdd;

	private DefaultListModel<PizzaEntry> pizzaModel;
	private DefaultListModel<ItemEntry> sideItemModel;
	private DefaultListModel<ItemEntry> toppingModel;
	private DefaultListModel<ItemEntry> customizeToppingsModel;
	private DefaultListModel<String> personModel;
	private DefaultListModel<ItemEntry> allItemModel;;

	private JComboBox comboBoxRoleAdd;

	private JList listPizzas;
	private JList listAddableToppings;
	private JList listToppings;
	private JList listSides;
	private JTextField textFieldDiscountItemName;
	private JList<ItemEntry> listDiscountableItems;
	private JSpinner spinnerDiscountItemAmount;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry();
			LoginControllerInterface loginStub = (LoginControllerInterface) registry.lookup("LoginController");
			MenuControllerInterface menuStub = (MenuControllerInterface) registry.lookup("MenuController");
			Login window = new ManagerLogin(loginStub, menuStub);
			window.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			window.setVisible(true);
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
	public ManagerWindow(MenuControllerInterface menuController, LoginControllerInterface loginController) {
		this.menuController = menuController;
		this.loginController = loginController;
		setupWindow();
		refreshLists();
	}

	private void refreshLists() {
		try {
			allItemModel.clear();
			pizzaModel.clear();

			for(PizzaEntry pe : menuController.getPizzas()) {
				pizzaModel.addElement(pe);
				allItemModel.addElement(pe);
			}

			sideItemModel.clear();
			for(ItemEntry sideItem : menuController.getSides()) {
				sideItemModel.addElement(sideItem);
				allItemModel.addElement(sideItem);
			}
			toppingModel.clear();
			customizeToppingsModel.clear();
			for(ItemEntry topping : menuController.getToppings()) {
				toppingModel.addElement(topping);
				customizeToppingsModel.addElement(topping);
				allItemModel.addElement(topping);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setupWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ManagerWindow.class.getResource("/com/sun/java/swing/plaf/windows/icons/ListView.gif")));
		setTitle("Management Suite");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 611, 430);
		setMinimumSize(new Dimension(611,376));

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}


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

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		sl_contentPane.putConstraint(SpringLayout.NORTH, tabbedPane, 5, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, tabbedPane, 5, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, tabbedPane, -5, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, tabbedPane, -5, SpringLayout.EAST, contentPane);
		contentPane.add(tabbedPane);

		JPanel tabPizzas = new JPanel();
		tabbedPane.addTab("Pizzas", null, tabPizzas, null);
		SpringLayout sl_tabPizzas = new SpringLayout();
		tabPizzas.setLayout(sl_tabPizzas);

		JPanel panelPizzas = new JPanel();
		panelPizzas.setBorder(new TitledBorder(null, "Pizzas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_tabPizzas.putConstraint(SpringLayout.NORTH, panelPizzas, 5, SpringLayout.NORTH, tabPizzas);
		sl_tabPizzas.putConstraint(SpringLayout.WEST, panelPizzas, 5, SpringLayout.WEST, tabPizzas);
		sl_tabPizzas.putConstraint(SpringLayout.SOUTH, panelPizzas, -5, SpringLayout.SOUTH, tabPizzas);
		sl_tabPizzas.putConstraint(SpringLayout.EAST, panelPizzas, 300, SpringLayout.WEST, tabPizzas);
		tabPizzas.add(panelPizzas);

		JPanel panelPizzasAdd = new JPanel();
		panelPizzasAdd.setBorder(new TitledBorder(null, "Add", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_tabPizzas.putConstraint(SpringLayout.NORTH, panelPizzasAdd, 5, SpringLayout.NORTH, tabPizzas);
		sl_tabPizzas.putConstraint(SpringLayout.WEST, panelPizzasAdd, 5, SpringLayout.EAST, panelPizzas);
		SpringLayout sl_panelPizzas = new SpringLayout();
		panelPizzas.setLayout(sl_panelPizzas);

		JScrollPane scrollPane = new JScrollPane();
		sl_panelPizzas.putConstraint(SpringLayout.NORTH, scrollPane, 0, SpringLayout.NORTH, panelPizzas);
		sl_panelPizzas.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, panelPizzas);
		sl_panelPizzas.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, panelPizzas);
		panelPizzas.add(scrollPane);

		JButton btnRemovepizza = new JButton("Remove Pizza");
		btnRemovepizza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					menuController.removePizza(((ItemEntry)listPizzas.getSelectedValue()).getItemId());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				refreshLists();
			}
		});
		sl_panelPizzas.putConstraint(SpringLayout.SOUTH, scrollPane, -5, SpringLayout.NORTH, btnRemovepizza);
		sl_panelPizzas.putConstraint(SpringLayout.SOUTH, btnRemovepizza, 0, SpringLayout.SOUTH, panelPizzas);
		sl_panelPizzas.putConstraint(SpringLayout.EAST, btnRemovepizza, 0, SpringLayout.EAST, scrollPane);

		pizzaModel = new DefaultListModel();
		listPizzas = new JList(pizzaModel);
		scrollPane.setViewportView(listPizzas);
		panelPizzas.add(btnRemovepizza);
		sl_tabPizzas.putConstraint(SpringLayout.SOUTH, panelPizzasAdd, -5, SpringLayout.SOUTH, tabPizzas);
		sl_tabPizzas.putConstraint(SpringLayout.EAST, panelPizzasAdd, -5, SpringLayout.EAST, tabPizzas);
		tabPizzas.add(panelPizzasAdd);
		GridBagLayout gbl_panelPizzasAdd = new GridBagLayout();
		gbl_panelPizzasAdd.columnWidths = new int[]{0, 0, 0};
		gbl_panelPizzasAdd.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelPizzasAdd.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panelPizzasAdd.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelPizzasAdd.setLayout(gbl_panelPizzasAdd);

		JLabel lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 0;
		panelPizzasAdd.add(lblName, gbc_lblName);

		JLabel lblPrice = new JLabel("Base Price");
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.anchor = GridBagConstraints.WEST;
		gbc_lblPrice.insets = new Insets(0, 0, 5, 0);
		gbc_lblPrice.gridx = 1;
		gbc_lblPrice.gridy = 0;
		panelPizzasAdd.add(lblPrice, gbc_lblPrice);

		textFieldPizzaName = new JTextField();
		GridBagConstraints gbc_textFieldPizzaName = new GridBagConstraints();
		gbc_textFieldPizzaName.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPizzaName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPizzaName.gridx = 0;
		gbc_textFieldPizzaName.gridy = 1;
		panelPizzasAdd.add(textFieldPizzaName, gbc_textFieldPizzaName);
		textFieldPizzaName.setColumns(10);

		textFieldPizzaBasePrice = new JTextField();
		GridBagConstraints gbc_textFieldPizzaBasePrice = new GridBagConstraints();
		gbc_textFieldPizzaBasePrice.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPizzaBasePrice.anchor = GridBagConstraints.NORTH;
		gbc_textFieldPizzaBasePrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPizzaBasePrice.gridx = 1;
		gbc_textFieldPizzaBasePrice.gridy = 1;
		panelPizzasAdd.add(textFieldPizzaBasePrice, gbc_textFieldPizzaBasePrice);
		textFieldPizzaBasePrice.setColumns(10);

		JLabel lblToppings = new JLabel("Toppings");
		GridBagConstraints gbc_lblToppings = new GridBagConstraints();
		gbc_lblToppings.anchor = GridBagConstraints.WEST;
		gbc_lblToppings.insets = new Insets(0, 0, 5, 5);
		gbc_lblToppings.gridx = 0;
		gbc_lblToppings.gridy = 2;
		panelPizzasAdd.add(lblToppings, gbc_lblToppings);

		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 3;
		panelPizzasAdd.add(scrollPane_1, gbc_scrollPane_1);

		customizeToppingsModel = new DefaultListModel<ItemEntry>();
		listAddableToppings = new JList(customizeToppingsModel);
		scrollPane_1.setViewportView(listAddableToppings);

		JButton btnPizzaAdd = new JButton("Add");
		btnPizzaAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//read fields and add pizza to list
				String name = textFieldPizzaName.getText();
				double basePrice = Double.parseDouble(textFieldPizzaBasePrice.getText());
				List<ItemEntry> toppings = listAddableToppings.getSelectedValuesList();
				try {
					menuController.addPizzaToMenu(name, basePrice, "", toppings);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				refreshLists();
			}
		});
		GridBagConstraints gbc_btnPizzaAdd = new GridBagConstraints();
		gbc_btnPizzaAdd.anchor = GridBagConstraints.EAST;
		gbc_btnPizzaAdd.gridx = 1;
		gbc_btnPizzaAdd.gridy = 4;
		panelPizzasAdd.add(btnPizzaAdd, gbc_btnPizzaAdd);

		JPanel tabToppings = new JPanel();
		tabbedPane.addTab("Toppings", null, tabToppings, null);
		SpringLayout sl_tabToppings = new SpringLayout();
		tabToppings.setLayout(sl_tabToppings);

		JPanel panelToppings = new JPanel();
		panelToppings.setBorder(new TitledBorder(null, "Toppings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_tabToppings.putConstraint(SpringLayout.NORTH, panelToppings, 5, SpringLayout.NORTH, tabToppings);
		sl_tabToppings.putConstraint(SpringLayout.WEST, panelToppings, 5, SpringLayout.WEST, tabToppings);
		sl_tabToppings.putConstraint(SpringLayout.SOUTH, panelToppings, -5, SpringLayout.SOUTH, tabToppings);
		sl_tabToppings.putConstraint(SpringLayout.EAST, panelToppings, 300, SpringLayout.WEST, tabToppings);
		tabToppings.add(panelToppings);
		SpringLayout sl_panelToppings = new SpringLayout();
		panelToppings.setLayout(sl_panelToppings);

		JScrollPane scrollPane_2 = new JScrollPane();
		sl_panelToppings.putConstraint(SpringLayout.NORTH, scrollPane_2, 0, SpringLayout.NORTH, panelToppings);
		sl_panelToppings.putConstraint(SpringLayout.WEST, scrollPane_2, 0, SpringLayout.WEST, panelToppings);
		sl_panelToppings.putConstraint(SpringLayout.EAST, scrollPane_2, 0, SpringLayout.EAST, panelToppings);
		panelToppings.add(scrollPane_2);

		JPanel panelToppingsAdd = new JPanel();
		panelToppingsAdd.setBorder(new TitledBorder(null, "Add", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_tabToppings.putConstraint(SpringLayout.NORTH, panelToppingsAdd, 5, SpringLayout.NORTH, tabToppings);
		sl_tabToppings.putConstraint(SpringLayout.WEST, panelToppingsAdd, 5, SpringLayout.EAST, panelToppings);

		JButton btnRemoveTopping = new JButton("Remove Topping");
		btnRemoveTopping.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!menuController.removeTopping(((ItemEntry)listToppings.getSelectedValue()).getItemId())) {
						JOptionPane.showMessageDialog(getContentPane(),
								"Cannot remove a topping which is on a customized pizza", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				refreshLists();
			}
		});
		sl_panelToppings.putConstraint(SpringLayout.SOUTH, scrollPane_2, -5, SpringLayout.NORTH, btnRemoveTopping);
		sl_panelToppings.putConstraint(SpringLayout.SOUTH, btnRemoveTopping, 0, SpringLayout.SOUTH, panelToppings);
		sl_panelToppings.putConstraint(SpringLayout.EAST, btnRemoveTopping, 0, SpringLayout.EAST, scrollPane_2);

		toppingModel = new DefaultListModel<ItemEntry>();
		listToppings = new JList(toppingModel);
		scrollPane_2.setViewportView(listToppings);
		panelToppings.add(btnRemoveTopping);
		sl_tabToppings.putConstraint(SpringLayout.SOUTH, panelToppingsAdd, -5, SpringLayout.SOUTH, tabToppings);
		sl_tabToppings.putConstraint(SpringLayout.EAST, panelToppingsAdd, -5, SpringLayout.EAST, tabToppings);
		tabToppings.add(panelToppingsAdd);
		GridBagLayout gbl_panelToppingsAdd = new GridBagLayout();
		gbl_panelToppingsAdd.columnWidths = new int[]{0, 0, 0};
		gbl_panelToppingsAdd.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelToppingsAdd.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panelToppingsAdd.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelToppingsAdd.setLayout(gbl_panelToppingsAdd);

		JLabel lblToppingName = new JLabel("Name");
		GridBagConstraints gbc_lblToppingName = new GridBagConstraints();
		gbc_lblToppingName.anchor = GridBagConstraints.WEST;
		gbc_lblToppingName.insets = new Insets(0, 0, 5, 5);
		gbc_lblToppingName.gridx = 0;
		gbc_lblToppingName.gridy = 0;
		panelToppingsAdd.add(lblToppingName, gbc_lblToppingName);

		JLabel lblToppingPrice = new JLabel("Price");
		GridBagConstraints gbc_lblToppingPrice = new GridBagConstraints();
		gbc_lblToppingPrice.anchor = GridBagConstraints.WEST;
		gbc_lblToppingPrice.insets = new Insets(0, 0, 5, 0);
		gbc_lblToppingPrice.gridx = 1;
		gbc_lblToppingPrice.gridy = 0;
		panelToppingsAdd.add(lblToppingPrice, gbc_lblToppingPrice);

		textFieldToppingName = new JTextField();
		GridBagConstraints gbc_textFieldToppingName = new GridBagConstraints();
		gbc_textFieldToppingName.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldToppingName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldToppingName.gridx = 0;
		gbc_textFieldToppingName.gridy = 1;
		panelToppingsAdd.add(textFieldToppingName, gbc_textFieldToppingName);
		textFieldToppingName.setColumns(10);

		textFieldToppingPrice = new JTextField();
		GridBagConstraints gbc_textFieldToppingPrice = new GridBagConstraints();
		gbc_textFieldToppingPrice.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldToppingPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldToppingPrice.gridx = 1;
		gbc_textFieldToppingPrice.gridy = 1;
		panelToppingsAdd.add(textFieldToppingPrice, gbc_textFieldToppingPrice);
		textFieldToppingPrice.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		panelToppingsAdd.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JButton btnToppingAdd = new JButton("Add");
		btnToppingAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = textFieldToppingName.getText();
				double price = Double.parseDouble(textFieldToppingPrice.getText());
				try {
					menuController.addToppingToMenu(name, price);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				refreshLists();
			}
		});
		GridBagConstraints gbc_btnToppingAdd = new GridBagConstraints();
		gbc_btnToppingAdd.anchor = GridBagConstraints.EAST;
		gbc_btnToppingAdd.gridx = 1;
		gbc_btnToppingAdd.gridy = 3;
		panelToppingsAdd.add(btnToppingAdd, gbc_btnToppingAdd);

		JPanel tabSides = new JPanel();
		tabbedPane.addTab("Sides", null, tabSides, null);
		SpringLayout sl_tabSides = new SpringLayout();
		tabSides.setLayout(sl_tabSides);

		JPanel panelSides = new JPanel();
		panelSides.setBorder(new TitledBorder(null, "Sides", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_tabSides.putConstraint(SpringLayout.NORTH, panelSides, 5, SpringLayout.NORTH, tabSides);
		sl_tabSides.putConstraint(SpringLayout.WEST, panelSides, 5, SpringLayout.WEST, tabSides);
		sl_tabSides.putConstraint(SpringLayout.SOUTH, panelSides, -5, SpringLayout.SOUTH, tabSides);
		sl_tabSides.putConstraint(SpringLayout.EAST, panelSides, 300, SpringLayout.WEST, tabSides);
		tabSides.add(panelSides);
		SpringLayout sl_panelSides = new SpringLayout();
		panelSides.setLayout(sl_panelSides);

		JScrollPane scrollPane_3 = new JScrollPane();
		sl_panelSides.putConstraint(SpringLayout.NORTH, scrollPane_3, 0, SpringLayout.NORTH, panelSides);
		sl_panelSides.putConstraint(SpringLayout.WEST, scrollPane_3, 0, SpringLayout.WEST, panelSides);
		sl_panelSides.putConstraint(SpringLayout.EAST, scrollPane_3, 0, SpringLayout.EAST, panelSides);
		panelSides.add(scrollPane_3);

		JPanel panelSidesAdd = new JPanel();
		panelSidesAdd.setBorder(new TitledBorder(null, "Add", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_tabSides.putConstraint(SpringLayout.NORTH, panelSidesAdd, 5, SpringLayout.NORTH, tabSides);
		sl_tabSides.putConstraint(SpringLayout.WEST, panelSidesAdd, 5, SpringLayout.EAST, panelSides);

		JButton btnRemoveSide = new JButton("Remove Side");
		btnRemoveSide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					menuController.removeSide(((ItemEntry)listSides.getSelectedValue()).getItemId());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				refreshLists();
			}
		});
		sl_panelSides.putConstraint(SpringLayout.SOUTH, scrollPane_3, -5, SpringLayout.NORTH, btnRemoveSide);
		sl_panelSides.putConstraint(SpringLayout.SOUTH, btnRemoveSide, 0, SpringLayout.SOUTH, panelSides);
		sl_panelSides.putConstraint(SpringLayout.EAST, btnRemoveSide, 0, SpringLayout.EAST, scrollPane_3);

		sideItemModel = new DefaultListModel<ItemEntry>();
		listSides = new JList(sideItemModel);
		scrollPane_3.setViewportView(listSides);
		panelSides.add(btnRemoveSide);
		sl_tabSides.putConstraint(SpringLayout.SOUTH, panelSidesAdd, -5, SpringLayout.SOUTH, tabSides);
		sl_tabSides.putConstraint(SpringLayout.EAST, panelSidesAdd, -5, SpringLayout.EAST, tabSides);
		tabSides.add(panelSidesAdd);
		GridBagLayout gbl_panelSidesAdd = new GridBagLayout();
		gbl_panelSidesAdd.columnWidths = new int[]{0, 0, 0};
		gbl_panelSidesAdd.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelSidesAdd.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panelSidesAdd.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelSidesAdd.setLayout(gbl_panelSidesAdd);

		JLabel lblSideName = new JLabel("Name");
		GridBagConstraints gbc_lblSideName = new GridBagConstraints();
		gbc_lblSideName.anchor = GridBagConstraints.WEST;
		gbc_lblSideName.insets = new Insets(0, 0, 5, 5);
		gbc_lblSideName.gridx = 0;
		gbc_lblSideName.gridy = 0;
		panelSidesAdd.add(lblSideName, gbc_lblSideName);

		JLabel lblSidePrice = new JLabel("Price");
		GridBagConstraints gbc_lblSidePrice = new GridBagConstraints();
		gbc_lblSidePrice.anchor = GridBagConstraints.WEST;
		gbc_lblSidePrice.insets = new Insets(0, 0, 5, 0);
		gbc_lblSidePrice.gridx = 1;
		gbc_lblSidePrice.gridy = 0;
		panelSidesAdd.add(lblSidePrice, gbc_lblSidePrice);

		textFieldSideName = new JTextField();
		GridBagConstraints gbc_textFieldSideName = new GridBagConstraints();
		gbc_textFieldSideName.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldSideName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSideName.gridx = 0;
		gbc_textFieldSideName.gridy = 1;
		panelSidesAdd.add(textFieldSideName, gbc_textFieldSideName);
		textFieldSideName.setColumns(10);

		textFieldSidePrice = new JTextField();
		GridBagConstraints gbc_textFieldSidePrice = new GridBagConstraints();
		gbc_textFieldSidePrice.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldSidePrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSidePrice.gridx = 1;
		gbc_textFieldSidePrice.gridy = 1;
		panelSidesAdd.add(textFieldSidePrice, gbc_textFieldSidePrice);
		textFieldSidePrice.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 2;
		panelSidesAdd.add(lblNewLabel_2, gbc_lblNewLabel_2);

		JButton btnSideAdd = new JButton("Add");
		btnSideAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = textFieldSideName.getText();
				double price = Double.parseDouble(textFieldSidePrice.getText());
				try {
					menuController.addSideItemToMenu(name, price, "");
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				refreshLists();
			}
		});
		GridBagConstraints gbc_btnSideAdd = new GridBagConstraints();
		gbc_btnSideAdd.anchor = GridBagConstraints.EAST;
		gbc_btnSideAdd.gridx = 1;
		gbc_btnSideAdd.gridy = 3;
		panelSidesAdd.add(btnSideAdd, gbc_btnSideAdd);
		
		JPanel tabDiscount = new JPanel();
		tabbedPane.addTab("Discounts", null, tabDiscount, null);
		SpringLayout sl_tabDiscount = new SpringLayout();
		tabDiscount.setLayout(sl_tabDiscount);
		
		JPanel panelDiscountableItems = new JPanel();
		panelDiscountableItems.setBorder(new TitledBorder(null, "Items", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_tabDiscount.putConstraint(SpringLayout.NORTH, panelDiscountableItems, 5, SpringLayout.NORTH, tabDiscount);
		sl_tabDiscount.putConstraint(SpringLayout.WEST, panelDiscountableItems, 5, SpringLayout.WEST, tabDiscount);
		sl_tabDiscount.putConstraint(SpringLayout.SOUTH, panelDiscountableItems, -5, SpringLayout.SOUTH, tabDiscount);
		sl_tabDiscount.putConstraint(SpringLayout.EAST, panelDiscountableItems, 300, SpringLayout.WEST, tabDiscount);
		tabDiscount.add(panelDiscountableItems);
		
		JPanel panelDiscountEdit = new JPanel();
		sl_tabDiscount.putConstraint(SpringLayout.NORTH, panelDiscountEdit, 5, SpringLayout.NORTH, tabDiscount);
		panelDiscountEdit.setBorder(new TitledBorder(null, "Edit", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_tabDiscount.putConstraint(SpringLayout.WEST, panelDiscountEdit, 5, SpringLayout.EAST, panelDiscountableItems);
		SpringLayout sl_panelDiscountableItems = new SpringLayout();
		panelDiscountableItems.setLayout(sl_panelDiscountableItems);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		sl_panelDiscountableItems.putConstraint(SpringLayout.NORTH, scrollPane_4, 0, SpringLayout.NORTH, panelDiscountableItems);
		sl_panelDiscountableItems.putConstraint(SpringLayout.WEST, scrollPane_4, 0, SpringLayout.WEST, panelDiscountableItems);
		sl_panelDiscountableItems.putConstraint(SpringLayout.SOUTH, scrollPane_4, 0, SpringLayout.SOUTH, panelDiscountableItems);
		sl_panelDiscountableItems.putConstraint(SpringLayout.EAST, scrollPane_4, 0, SpringLayout.EAST, panelDiscountableItems);
		panelDiscountableItems.add(scrollPane_4);
		
		allItemModel = new DefaultListModel<ItemEntry>();
		listDiscountableItems = new JList<ItemEntry>(allItemModel);
		listDiscountableItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listDiscountableItems.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
					ItemEntry e = (ItemEntry) listDiscountableItems.getSelectedValue();
					if(e == null) return;
					textFieldDiscountItemName.setText(e.getName());
					spinnerDiscountItemAmount.setValue(e.getItemDiscountPercent());
			}
		});
		scrollPane_4.setViewportView(listDiscountableItems);
		sl_tabDiscount.putConstraint(SpringLayout.SOUTH, panelDiscountEdit, -5, SpringLayout.SOUTH, tabDiscount);
		sl_tabDiscount.putConstraint(SpringLayout.EAST, panelDiscountEdit, -5, SpringLayout.EAST, tabDiscount);
		tabDiscount.add(panelDiscountEdit);
		GridBagLayout gbl_panelDiscountEdit = new GridBagLayout();
		gbl_panelDiscountEdit.columnWidths = new int[]{0, 0, 0};
		gbl_panelDiscountEdit.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelDiscountEdit.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panelDiscountEdit.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelDiscountEdit.setLayout(gbl_panelDiscountEdit);
		
		JLabel lblDiscountItemName = new JLabel("Name");
		GridBagConstraints gbc_lblDiscountItemName = new GridBagConstraints();
		gbc_lblDiscountItemName.anchor = GridBagConstraints.WEST;
		gbc_lblDiscountItemName.insets = new Insets(0, 0, 5, 5);
		gbc_lblDiscountItemName.gridx = 0;
		gbc_lblDiscountItemName.gridy = 0;
		panelDiscountEdit.add(lblDiscountItemName, gbc_lblDiscountItemName);
		
		JLabel lblDiscountItemAmount = new JLabel("Discount");
		GridBagConstraints gbc_lblDiscountItemAmount = new GridBagConstraints();
		gbc_lblDiscountItemAmount.anchor = GridBagConstraints.WEST;
		gbc_lblDiscountItemAmount.insets = new Insets(0, 0, 5, 0);
		gbc_lblDiscountItemAmount.gridx = 1;
		gbc_lblDiscountItemAmount.gridy = 0;
		panelDiscountEdit.add(lblDiscountItemAmount, gbc_lblDiscountItemAmount);
		
		textFieldDiscountItemName = new JTextField();
		textFieldDiscountItemName.setEditable(false);
		GridBagConstraints gbc_textFieldDiscountItemName = new GridBagConstraints();
		gbc_textFieldDiscountItemName.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDiscountItemName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDiscountItemName.gridx = 0;
		gbc_textFieldDiscountItemName.gridy = 1;
		panelDiscountEdit.add(textFieldDiscountItemName, gbc_textFieldDiscountItemName);
		textFieldDiscountItemName.setColumns(10);
		
		spinnerDiscountItemAmount = new JSpinner();
		spinnerDiscountItemAmount.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		GridBagConstraints gbc_spinnerDiscountItemAmount = new GridBagConstraints();
		gbc_spinnerDiscountItemAmount.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerDiscountItemAmount.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerDiscountItemAmount.gridx = 1;
		gbc_spinnerDiscountItemAmount.gridy = 1;
		panelDiscountEdit.add(spinnerDiscountItemAmount, gbc_spinnerDiscountItemAmount);
		
		JLabel lblNewLabel = new JLabel("");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		panelDiscountEdit.add(lblNewLabel, gbc_lblNewLabel);
		
		JButton btnDiscountItemApply = new JButton("Apply");
		btnDiscountItemApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					menuController.applyDiscount(listDiscountableItems.getSelectedValue().getItemId(), (int)Double.parseDouble(spinnerDiscountItemAmount.getValue().toString()));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				refreshLists();
			}
		});
		GridBagConstraints gbc_btnDiscountItemApply = new GridBagConstraints();
		gbc_btnDiscountItemApply.anchor = GridBagConstraints.EAST;
		gbc_btnDiscountItemApply.gridx = 1;
		gbc_btnDiscountItemApply.gridy = 3;
		panelDiscountEdit.add(btnDiscountItemApply, gbc_btnDiscountItemApply);

		JPanel tabPeople = new JPanel();
		tabbedPane.addTab("People", null, tabPeople, null);
		tabbedPane.setEnabledAt(4, true);
		SpringLayout sl_tabPeople = new SpringLayout();
		tabPeople.setLayout(sl_tabPeople);

		personModel = new DefaultListModel<String>();

		JPanel panelAddModify = new JPanel();
		sl_tabPeople.putConstraint(SpringLayout.NORTH, panelAddModify, 5, SpringLayout.NORTH, tabPeople);
		sl_tabPeople.putConstraint(SpringLayout.WEST, panelAddModify, 305, SpringLayout.WEST, tabPeople);
		sl_tabPeople.putConstraint(SpringLayout.SOUTH, panelAddModify, -5, SpringLayout.SOUTH, tabPeople);
		sl_tabPeople.putConstraint(SpringLayout.EAST, panelAddModify, -5, SpringLayout.EAST, tabPeople);
		tabPeople.add(panelAddModify);
		GridBagLayout gbl_panelAddModify = new GridBagLayout();
		gbl_panelAddModify.columnWidths = new int[]{0, 0};
		gbl_panelAddModify.rowHeights = new int[]{0, 0, 0};
		gbl_panelAddModify.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelAddModify.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panelAddModify.setLayout(gbl_panelAddModify);

		JPanel panelAdd = new JPanel();
		GridBagConstraints gbc_panelAdd = new GridBagConstraints();
		gbc_panelAdd.insets = new Insets(0, 0, 5, 0);
		gbc_panelAdd.gridx = 0;
		gbc_panelAdd.gridy = 0;
		panelAddModify.add(panelAdd, gbc_panelAdd);
		sl_tabPeople.putConstraint(SpringLayout.NORTH, panelAdd, 0, SpringLayout.NORTH, panelAddModify);
		sl_tabPeople.putConstraint(SpringLayout.EAST, panelAdd, -4, SpringLayout.WEST, panelAddModify);
		panelAdd.setBorder(new TitledBorder(null, "Add", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gbl_panelAdd = new GridBagLayout();
		gbl_panelAdd.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelAdd.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panelAdd.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelAdd.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelAdd.setLayout(gbl_panelAdd);

		JLabel lblAddUsername = new JLabel("Username");
		GridBagConstraints gbc_lblAddUsername = new GridBagConstraints();
		gbc_lblAddUsername.anchor = GridBagConstraints.WEST;
		gbc_lblAddUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddUsername.gridx = 0;
		gbc_lblAddUsername.gridy = 0;
		panelAdd.add(lblAddUsername, gbc_lblAddUsername);

		JLabel lblAddPassword = new JLabel("Password");
		GridBagConstraints gbc_lblAddPassword = new GridBagConstraints();
		gbc_lblAddPassword.anchor = GridBagConstraints.WEST;
		gbc_lblAddPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddPassword.gridx = 1;
		gbc_lblAddPassword.gridy = 0;
		panelAdd.add(lblAddPassword, gbc_lblAddPassword);

		JLabel lblAddRole = new JLabel("Role");
		GridBagConstraints gbc_lblAddRole = new GridBagConstraints();
		gbc_lblAddRole.anchor = GridBagConstraints.WEST;
		gbc_lblAddRole.insets = new Insets(0, 0, 5, 0);
		gbc_lblAddRole.gridx = 2;
		gbc_lblAddRole.gridy = 0;
		panelAdd.add(lblAddRole, gbc_lblAddRole);

		textFieldUserNameAdd = new JTextField();
		GridBagConstraints gbc_textFieldUserNameAdd = new GridBagConstraints();
		gbc_textFieldUserNameAdd.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUserNameAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUserNameAdd.gridx = 0;
		gbc_textFieldUserNameAdd.gridy = 1;
		panelAdd.add(textFieldUserNameAdd, gbc_textFieldUserNameAdd);
		textFieldUserNameAdd.setColumns(10);

		textFieldPasswordAdd = new JTextField();
		GridBagConstraints gbc_textFieldPasswordAdd = new GridBagConstraints();
		gbc_textFieldPasswordAdd.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPasswordAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPasswordAdd.gridx = 1;
		gbc_textFieldPasswordAdd.gridy = 1;
		panelAdd.add(textFieldPasswordAdd, gbc_textFieldPasswordAdd);
		textFieldPasswordAdd.setColumns(10);

		comboBoxRoleAdd = new JComboBox();
		GridBagConstraints gbc_comboBoxRoleAdd = new GridBagConstraints();
		gbc_comboBoxRoleAdd.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxRoleAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxRoleAdd.gridx = 2;
		gbc_comboBoxRoleAdd.gridy = 1;
		comboBoxRoleAdd.addItem("Cashier");
		comboBoxRoleAdd.addItem("Manager");
		panelAdd.add(comboBoxRoleAdd, gbc_comboBoxRoleAdd);

		JLabel lblNewLabel_3 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 4;
		panelAdd.add(lblNewLabel_3, gbc_lblNewLabel_3);

		JButton btnAddAdd = new JButton("Add");
		btnAddAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textFieldUserNameAdd.getText();
				String password = textFieldPasswordAdd.getText();
				if(comboBoxRoleAdd.getSelectedItem().toString().equals("Cashier")) {
					try {
						loginController.createCashierAccount(username, password);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
				if(comboBoxRoleAdd.getSelectedItem().toString().equals("Manager")) {
					try {
						loginController.createManagerAccount(username, password);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		GridBagConstraints gbc_btnAddAdd = new GridBagConstraints();
		gbc_btnAddAdd.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnAddAdd.gridx = 2;
		gbc_btnAddAdd.gridy = 5;
		panelAdd.add(btnAddAdd, gbc_btnAddAdd);
		setVisible(true);
	}
}
