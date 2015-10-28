package cs414c.pizza.ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import cs414c.pizza.controller.MenuController;

public class ManagerWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablePizzas;
	private JTextField textFieldPizzaName;
	private JTextField textFieldPizzaBasePrice;
	private JTable tableToppings;
	private JTable tableSides;
	private JTable tableSpecials;
	private JTextField textFieldToppingName;
	private JTextField textFieldToppingPrice;
	private JTextField textFieldSideName;
	private JTextField textFieldSidePrice;
	
	private MenuController menuController;
	private JTable tablePeople;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(
				            UIManager.getSystemLookAndFeelClassName());
					ManagerWindow frame = new ManagerWindow();
					frame.setMinimumSize(new Dimension(611,376));
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
	public ManagerWindow(MenuController menuController) {
		this.menuController = menuController;
		setupWindow();
		
		List<PizzaEntry> pizzas = menuController.getPizzas();
	}
	
	private void setupWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ManagerWindow.class.getResource("/com/sun/java/swing/plaf/windows/icons/ListView.gif")));
		setTitle("Management Suite");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 611, 430);
		setMinimumSize(new Dimension(611,376));
		
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
		
		tablePizzas = new JTable();
		tablePizzas.setFillsViewportHeight(true);
		scrollPane.setViewportView(tablePizzas);
		
		JButton btnRemovepizza = new JButton("Remove Pizza");
		sl_panelPizzas.putConstraint(SpringLayout.SOUTH, scrollPane, -5, SpringLayout.NORTH, btnRemovepizza);
		sl_panelPizzas.putConstraint(SpringLayout.SOUTH, btnRemovepizza, 0, SpringLayout.SOUTH, panelPizzas);
		sl_panelPizzas.putConstraint(SpringLayout.EAST, btnRemovepizza, 0, SpringLayout.EAST, scrollPane);
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
		
		JList listAddableToppings = new JList();
		scrollPane_1.setViewportView(listAddableToppings);
		
		JButton btnPizzaAdd = new JButton("Add");
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
		
		tableToppings = new JTable();
		tableToppings.setFillsViewportHeight(true);
		scrollPane_2.setViewportView(tableToppings);
		
		JPanel panelToppingsAdd = new JPanel();
		panelToppingsAdd.setBorder(new TitledBorder(null, "Add", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_tabToppings.putConstraint(SpringLayout.NORTH, panelToppingsAdd, 5, SpringLayout.NORTH, tabToppings);
		sl_tabToppings.putConstraint(SpringLayout.WEST, panelToppingsAdd, 5, SpringLayout.EAST, panelToppings);
		
		JButton btnRemoveTopping = new JButton("Remove Topping");
		sl_panelToppings.putConstraint(SpringLayout.SOUTH, scrollPane_2, -5, SpringLayout.NORTH, btnRemoveTopping);
		sl_panelToppings.putConstraint(SpringLayout.SOUTH, btnRemoveTopping, 0, SpringLayout.SOUTH, panelToppings);
		sl_panelToppings.putConstraint(SpringLayout.EAST, btnRemoveTopping, 0, SpringLayout.EAST, scrollPane_2);
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
		
		tableSides = new JTable();
		tableSides.setFillsViewportHeight(true);
		scrollPane_3.setViewportView(tableSides);
		
		JPanel panelSidesAdd = new JPanel();
		panelSidesAdd.setBorder(new TitledBorder(null, "Add", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_tabSides.putConstraint(SpringLayout.NORTH, panelSidesAdd, 5, SpringLayout.NORTH, tabSides);
		sl_tabSides.putConstraint(SpringLayout.WEST, panelSidesAdd, 5, SpringLayout.EAST, panelSides);
		
		JButton btnRemoveSide = new JButton("Remove Side");
		sl_panelSides.putConstraint(SpringLayout.SOUTH, scrollPane_3, -5, SpringLayout.NORTH, btnRemoveSide);
		sl_panelSides.putConstraint(SpringLayout.SOUTH, btnRemoveSide, 0, SpringLayout.SOUTH, panelSides);
		sl_panelSides.putConstraint(SpringLayout.EAST, btnRemoveSide, 0, SpringLayout.EAST, scrollPane_3);
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
		GridBagConstraints gbc_btnSideAdd = new GridBagConstraints();
		gbc_btnSideAdd.anchor = GridBagConstraints.EAST;
		gbc_btnSideAdd.gridx = 1;
		gbc_btnSideAdd.gridy = 3;
		panelSidesAdd.add(btnSideAdd, gbc_btnSideAdd);
		
		JPanel tabSpecials = new JPanel();
		tabbedPane.addTab("Specials", null, tabSpecials, null);
		SpringLayout sl_tabSpecials = new SpringLayout();
		tabSpecials.setLayout(sl_tabSpecials);
		
		JPanel panelSpecials = new JPanel();
		panelSpecials.setBorder(new TitledBorder(null, "Specials", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_tabSpecials.putConstraint(SpringLayout.NORTH, panelSpecials, 5, SpringLayout.NORTH, tabSpecials);
		sl_tabSpecials.putConstraint(SpringLayout.WEST, panelSpecials, 5, SpringLayout.WEST, tabSpecials);
		sl_tabSpecials.putConstraint(SpringLayout.SOUTH, panelSpecials, -5, SpringLayout.SOUTH, tabSpecials);
		sl_tabSpecials.putConstraint(SpringLayout.EAST, panelSpecials, 300, SpringLayout.WEST, tabSpecials);
		tabSpecials.add(panelSpecials);
		SpringLayout sl_panelSpecials = new SpringLayout();
		panelSpecials.setLayout(sl_panelSpecials);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		sl_panelSpecials.putConstraint(SpringLayout.NORTH, scrollPane_4, 0, SpringLayout.NORTH, panelSpecials);
		sl_panelSpecials.putConstraint(SpringLayout.WEST, scrollPane_4, 0, SpringLayout.WEST, panelSpecials);
		sl_panelSpecials.putConstraint(SpringLayout.EAST, scrollPane_4, 0, SpringLayout.EAST, panelSpecials);
		panelSpecials.add(scrollPane_4);
		
		tableSpecials = new JTable();
		tableSpecials.setFillsViewportHeight(true);
		scrollPane_4.setViewportView(tableSpecials);
		
		JPanel panelSpecialsAdd = new JPanel();
		panelSpecialsAdd.setBorder(new TitledBorder(null, "Add", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_tabSpecials.putConstraint(SpringLayout.NORTH, panelSpecialsAdd, 5, SpringLayout.NORTH, tabSpecials);
		sl_tabSpecials.putConstraint(SpringLayout.WEST, panelSpecialsAdd, 5, SpringLayout.EAST, panelSpecials);
		
		JButton btnRemoveSpecial = new JButton("Remove Special");
		sl_panelSpecials.putConstraint(SpringLayout.SOUTH, scrollPane_4, -5, SpringLayout.NORTH, btnRemoveSpecial);
		sl_panelSpecials.putConstraint(SpringLayout.SOUTH, btnRemoveSpecial, 0, SpringLayout.SOUTH, panelSpecials);
		sl_panelSpecials.putConstraint(SpringLayout.EAST, btnRemoveSpecial, 0, SpringLayout.EAST, panelSpecials);
		panelSpecials.add(btnRemoveSpecial);
		sl_tabSpecials.putConstraint(SpringLayout.SOUTH, panelSpecialsAdd, -5, SpringLayout.SOUTH, tabSpecials);
		sl_tabSpecials.putConstraint(SpringLayout.EAST, panelSpecialsAdd, -5, SpringLayout.EAST, tabSpecials);
		tabSpecials.add(panelSpecialsAdd);
		GridBagLayout gbl_panelSpecialsAdd = new GridBagLayout();
		gbl_panelSpecialsAdd.columnWidths = new int[]{0, 0, 0};
		gbl_panelSpecialsAdd.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelSpecialsAdd.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panelSpecialsAdd.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelSpecialsAdd.setLayout(gbl_panelSpecialsAdd);
		
		JLabel lblSpecialItem = new JLabel("Item");
		GridBagConstraints gbc_lblSpecialItem = new GridBagConstraints();
		gbc_lblSpecialItem.anchor = GridBagConstraints.WEST;
		gbc_lblSpecialItem.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpecialItem.gridx = 0;
		gbc_lblSpecialItem.gridy = 0;
		panelSpecialsAdd.add(lblSpecialItem, gbc_lblSpecialItem);
		
		JLabel lblSpecialDiscount = new JLabel("Discount %");
		GridBagConstraints gbc_lblSpecialDiscount = new GridBagConstraints();
		gbc_lblSpecialDiscount.anchor = GridBagConstraints.WEST;
		gbc_lblSpecialDiscount.insets = new Insets(0, 0, 5, 0);
		gbc_lblSpecialDiscount.gridx = 1;
		gbc_lblSpecialDiscount.gridy = 0;
		panelSpecialsAdd.add(lblSpecialDiscount, gbc_lblSpecialDiscount);
		
		JComboBox comboBoxSpecialItem = new JComboBox();
		GridBagConstraints gbc_comboBoxSpecialItem = new GridBagConstraints();
		gbc_comboBoxSpecialItem.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxSpecialItem.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSpecialItem.gridx = 0;
		gbc_comboBoxSpecialItem.gridy = 1;
		panelSpecialsAdd.add(comboBoxSpecialItem, gbc_comboBoxSpecialItem);
		
		JSpinner spinnerSpecialDiscount = new JSpinner();
		spinnerSpecialDiscount.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		GridBagConstraints gbc_spinnerSpecialDiscount = new GridBagConstraints();
		gbc_spinnerSpecialDiscount.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerSpecialDiscount.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerSpecialDiscount.gridx = 1;
		gbc_spinnerSpecialDiscount.gridy = 1;
		panelSpecialsAdd.add(spinnerSpecialDiscount, gbc_spinnerSpecialDiscount);
		
		JLabel lblNewLabel = new JLabel("");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		panelSpecialsAdd.add(lblNewLabel, gbc_lblNewLabel);
		
		JButton btnSpecialAdd = new JButton("Add");
		GridBagConstraints gbc_btnSpecialAdd = new GridBagConstraints();
		gbc_btnSpecialAdd.anchor = GridBagConstraints.EAST;
		gbc_btnSpecialAdd.gridx = 1;
		gbc_btnSpecialAdd.gridy = 3;
		panelSpecialsAdd.add(btnSpecialAdd, gbc_btnSpecialAdd);
		
		JPanel tabPeople = new JPanel();
		tabbedPane.addTab("People", null, tabPeople, null);
		tabbedPane.setEnabledAt(4, true);
		SpringLayout sl_tabPeople = new SpringLayout();
		tabPeople.setLayout(sl_tabPeople);
		
		JPanel panelCashierManager = new JPanel();
		panelCashierManager.setBorder(new TitledBorder(null, "Cashiers and Managers", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_tabPeople.putConstraint(SpringLayout.NORTH, panelCashierManager, 5, SpringLayout.NORTH, tabPeople);
		sl_tabPeople.putConstraint(SpringLayout.WEST, panelCashierManager, 5, SpringLayout.WEST, tabPeople);
		sl_tabPeople.putConstraint(SpringLayout.SOUTH, panelCashierManager, -5, SpringLayout.SOUTH, tabPeople);
		sl_tabPeople.putConstraint(SpringLayout.EAST, panelCashierManager, 300, SpringLayout.WEST, tabPeople);
		tabPeople.add(panelCashierManager);
		SpringLayout sl_panelCashierManager = new SpringLayout();
		panelCashierManager.setLayout(sl_panelCashierManager);
		
		JButton btnRemovePerson = new JButton("Remove Person");
		sl_panelCashierManager.putConstraint(SpringLayout.SOUTH, btnRemovePerson, 0, SpringLayout.SOUTH, panelCashierManager);
		sl_panelCashierManager.putConstraint(SpringLayout.EAST, btnRemovePerson, 0, SpringLayout.EAST, panelCashierManager);
		panelCashierManager.add(btnRemovePerson);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		sl_panelCashierManager.putConstraint(SpringLayout.NORTH, scrollPane_5, 0, SpringLayout.NORTH, panelCashierManager);
		sl_panelCashierManager.putConstraint(SpringLayout.WEST, scrollPane_5, 0, SpringLayout.WEST, panelCashierManager);
		sl_panelCashierManager.putConstraint(SpringLayout.SOUTH, scrollPane_5, -5, SpringLayout.NORTH, btnRemovePerson);
		sl_panelCashierManager.putConstraint(SpringLayout.EAST, scrollPane_5, 0, SpringLayout.EAST, panelCashierManager);
		panelCashierManager.add(scrollPane_5);
		
		tablePeople = new JTable();
		tablePeople.setFillsViewportHeight(true);
		scrollPane_5.setViewportView(tablePeople);
		
		JPanel panelAddModify = new JPanel();
		sl_tabPeople.putConstraint(SpringLayout.NORTH, panelAddModify, 5, SpringLayout.NORTH, tabPeople);
		sl_tabPeople.putConstraint(SpringLayout.WEST, panelAddModify, 5, SpringLayout.EAST, panelCashierManager);
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
		panelAdd.setBorder(new TitledBorder(null, "Add", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelAdd = new GridBagConstraints();
		gbc_panelAdd.weightx = 1.0;
		gbc_panelAdd.weighty = 0.5;
		gbc_panelAdd.insets = new Insets(0, 0, 5, 0);
		gbc_panelAdd.fill = GridBagConstraints.BOTH;
		gbc_panelAdd.gridx = 0;
		gbc_panelAdd.gridy = 0;
		panelAddModify.add(panelAdd, gbc_panelAdd);
		GridBagLayout gbl_panelAdd = new GridBagLayout();
		gbl_panelAdd.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelAdd.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panelAdd.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelAdd.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelAdd.setLayout(gbl_panelAdd);
		
		JLabel lblAddFName = new JLabel("First Name");
		GridBagConstraints gbc_lblAddFName = new GridBagConstraints();
		gbc_lblAddFName.anchor = GridBagConstraints.WEST;
		gbc_lblAddFName.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddFName.gridx = 0;
		gbc_lblAddFName.gridy = 0;
		panelAdd.add(lblAddFName, gbc_lblAddFName);
		
		JLabel lblAddLName = new JLabel("Last Name");
		GridBagConstraints gbc_lblAddLName = new GridBagConstraints();
		gbc_lblAddLName.anchor = GridBagConstraints.WEST;
		gbc_lblAddLName.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddLName.gridx = 1;
		gbc_lblAddLName.gridy = 0;
		panelAdd.add(lblAddLName, gbc_lblAddLName);
		
		JLabel lblAddRole = new JLabel("Role");
		GridBagConstraints gbc_lblAddRole = new GridBagConstraints();
		gbc_lblAddRole.anchor = GridBagConstraints.WEST;
		gbc_lblAddRole.insets = new Insets(0, 0, 5, 0);
		gbc_lblAddRole.gridx = 2;
		gbc_lblAddRole.gridy = 0;
		panelAdd.add(lblAddRole, gbc_lblAddRole);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		panelAdd.add(textField, gbc_textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		panelAdd.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 1;
		panelAdd.add(comboBox, gbc_comboBox);
		
		JLabel lblAddUsername = new JLabel("Username");
		GridBagConstraints gbc_lblAddUsername = new GridBagConstraints();
		gbc_lblAddUsername.anchor = GridBagConstraints.WEST;
		gbc_lblAddUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddUsername.gridx = 0;
		gbc_lblAddUsername.gridy = 2;
		panelAdd.add(lblAddUsername, gbc_lblAddUsername);
		
		JLabel lblAddPassword = new JLabel("Password");
		GridBagConstraints gbc_lblAddPassword = new GridBagConstraints();
		gbc_lblAddPassword.anchor = GridBagConstraints.WEST;
		gbc_lblAddPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddPassword.gridx = 1;
		gbc_lblAddPassword.gridy = 2;
		panelAdd.add(lblAddPassword, gbc_lblAddPassword);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 0;
		gbc_textField_2.gridy = 3;
		panelAdd.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 3;
		panelAdd.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 4;
		panelAdd.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JButton btnAddAdd = new JButton("Add");
		GridBagConstraints gbc_btnAddAdd = new GridBagConstraints();
		gbc_btnAddAdd.anchor = GridBagConstraints.EAST;
		gbc_btnAddAdd.gridx = 2;
		gbc_btnAddAdd.gridy = 5;
		panelAdd.add(btnAddAdd, gbc_btnAddAdd);
		
		JPanel panelModify = new JPanel();
		panelModify.setBorder(new TitledBorder(null, "Modify", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelModify = new GridBagConstraints();
		gbc_panelModify.fill = GridBagConstraints.BOTH;
		gbc_panelModify.weighty = 1.0;
		gbc_panelModify.weightx = 0.5;
		gbc_panelModify.gridx = 0;
		gbc_panelModify.gridy = 1;
		panelAddModify.add(panelModify, gbc_panelModify);
		GridBagLayout gbl_panelModify = new GridBagLayout();
		gbl_panelModify.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelModify.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panelModify.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelModify.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelModify.setLayout(gbl_panelModify);
		
		JLabel lblModifyFName = new JLabel("First Name");
		GridBagConstraints gbc_lblModifyFName = new GridBagConstraints();
		gbc_lblModifyFName.anchor = GridBagConstraints.WEST;
		gbc_lblModifyFName.insets = new Insets(0, 0, 5, 5);
		gbc_lblModifyFName.gridx = 0;
		gbc_lblModifyFName.gridy = 0;
		panelModify.add(lblModifyFName, gbc_lblModifyFName);
		
		JLabel lblModifyLName = new JLabel("Last Name");
		GridBagConstraints gbc_lblModifyLName = new GridBagConstraints();
		gbc_lblModifyLName.anchor = GridBagConstraints.WEST;
		gbc_lblModifyLName.insets = new Insets(0, 0, 5, 5);
		gbc_lblModifyLName.gridx = 1;
		gbc_lblModifyLName.gridy = 0;
		panelModify.add(lblModifyLName, gbc_lblModifyLName);
		
		JLabel lblModifyRole = new JLabel("Role");
		GridBagConstraints gbc_lblModifyRole = new GridBagConstraints();
		gbc_lblModifyRole.anchor = GridBagConstraints.WEST;
		gbc_lblModifyRole.insets = new Insets(0, 0, 5, 0);
		gbc_lblModifyRole.gridx = 2;
		gbc_lblModifyRole.gridy = 0;
		panelModify.add(lblModifyRole, gbc_lblModifyRole);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 0;
		gbc_textField_4.gridy = 1;
		panelModify.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 1;
		gbc_textField_5.gridy = 1;
		panelModify.add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);
		
		JComboBox comboBox_1 = new JComboBox();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 2;
		gbc_comboBox_1.gridy = 1;
		panelModify.add(comboBox_1, gbc_comboBox_1);
		
		JLabel lblModifyUsername = new JLabel("Username");
		GridBagConstraints gbc_lblModifyUsername = new GridBagConstraints();
		gbc_lblModifyUsername.anchor = GridBagConstraints.WEST;
		gbc_lblModifyUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblModifyUsername.gridx = 0;
		gbc_lblModifyUsername.gridy = 2;
		panelModify.add(lblModifyUsername, gbc_lblModifyUsername);
		
		JLabel lblModifyPassword = new JLabel("Password");
		GridBagConstraints gbc_lblModifyPassword = new GridBagConstraints();
		gbc_lblModifyPassword.anchor = GridBagConstraints.WEST;
		gbc_lblModifyPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblModifyPassword.gridx = 1;
		gbc_lblModifyPassword.gridy = 2;
		panelModify.add(lblModifyPassword, gbc_lblModifyPassword);
		
		textField_6 = new JTextField();
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 5, 5);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 0;
		gbc_textField_6.gridy = 3;
		panelModify.add(textField_6, gbc_textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.insets = new Insets(0, 0, 5, 5);
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.gridx = 1;
		gbc_textField_7.gridy = 3;
		panelModify.add(textField_7, gbc_textField_7);
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_9.gridx = 2;
		gbc_lblNewLabel_9.gridy = 4;
		panelModify.add(lblNewLabel_9, gbc_lblNewLabel_9);
		
		JButton btnModifyModify = new JButton("Modify");
		GridBagConstraints gbc_btnModifyModify = new GridBagConstraints();
		gbc_btnModifyModify.anchor = GridBagConstraints.EAST;
		gbc_btnModifyModify.gridx = 2;
		gbc_btnModifyModify.gridy = 5;
		panelModify.add(btnModifyModify, gbc_btnModifyModify);
	}
}
