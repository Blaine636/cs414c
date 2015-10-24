package cs414c.pizza.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ManagerWindow extends JFrame {

	private JPanel contentPane;
	private JTable tablePizzas;
	private JTextField textFieldName;
	private JTextField textFieldBasePrice;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

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
		setBounds(100, 100, 611, 376);
		
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
		
		JPanel panelAdd = new JPanel();
		panelAdd.setBorder(new TitledBorder(null, "Add", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_tabPizzas.putConstraint(SpringLayout.NORTH, panelAdd, 5, SpringLayout.NORTH, tabPizzas);
		sl_tabPizzas.putConstraint(SpringLayout.WEST, panelAdd, 5, SpringLayout.EAST, panelPizzas);
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
		sl_tabPizzas.putConstraint(SpringLayout.SOUTH, panelAdd, -5, SpringLayout.SOUTH, tabPizzas);
		sl_tabPizzas.putConstraint(SpringLayout.EAST, panelAdd, -5, SpringLayout.EAST, tabPizzas);
		tabPizzas.add(panelAdd);
		GridBagLayout gbl_panelAdd = new GridBagLayout();
		gbl_panelAdd.columnWidths = new int[]{0, 0, 0};
		gbl_panelAdd.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelAdd.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panelAdd.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelAdd.setLayout(gbl_panelAdd);
		
		JLabel lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 0;
		panelAdd.add(lblName, gbc_lblName);
		
		JLabel lblPrice = new JLabel("Base Price");
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.anchor = GridBagConstraints.WEST;
		gbc_lblPrice.insets = new Insets(0, 0, 5, 0);
		gbc_lblPrice.gridx = 1;
		gbc_lblPrice.gridy = 0;
		panelAdd.add(lblPrice, gbc_lblPrice);
		
		textFieldName = new JTextField();
		GridBagConstraints gbc_textFieldName = new GridBagConstraints();
		gbc_textFieldName.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldName.gridx = 0;
		gbc_textFieldName.gridy = 1;
		panelAdd.add(textFieldName, gbc_textFieldName);
		textFieldName.setColumns(10);
		
		textFieldBasePrice = new JTextField();
		GridBagConstraints gbc_textFieldBasePrice = new GridBagConstraints();
		gbc_textFieldBasePrice.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldBasePrice.anchor = GridBagConstraints.NORTH;
		gbc_textFieldBasePrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldBasePrice.gridx = 1;
		gbc_textFieldBasePrice.gridy = 1;
		panelAdd.add(textFieldBasePrice, gbc_textFieldBasePrice);
		textFieldBasePrice.setColumns(10);
		
		JLabel lblTopings = new JLabel("Topings");
		GridBagConstraints gbc_lblTopings = new GridBagConstraints();
		gbc_lblTopings.anchor = GridBagConstraints.WEST;
		gbc_lblTopings.insets = new Insets(0, 0, 5, 5);
		gbc_lblTopings.gridx = 0;
		gbc_lblTopings.gridy = 2;
		panelAdd.add(lblTopings, gbc_lblTopings);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 3;
		panelAdd.add(scrollPane_1, gbc_scrollPane_1);
		
		JList listAddableToppings = new JList();
		scrollPane_1.setViewportView(listAddableToppings);
		
		JButton btnAdd = new JButton("Add");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.anchor = GridBagConstraints.EAST;
		gbc_btnAdd.gridx = 1;
		gbc_btnAdd.gridy = 4;
		panelAdd.add(btnAdd, gbc_btnAdd);
		
		JPanel tabToppings = new JPanel();
		tabbedPane.addTab("Toppings", null, tabToppings, null);
		SpringLayout sl_tabToppings = new SpringLayout();
		tabToppings.setLayout(sl_tabToppings);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Toppings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_tabToppings.putConstraint(SpringLayout.NORTH, panel, 5, SpringLayout.NORTH, tabToppings);
		sl_tabToppings.putConstraint(SpringLayout.WEST, panel, 5, SpringLayout.WEST, tabToppings);
		sl_tabToppings.putConstraint(SpringLayout.SOUTH, panel, -5, SpringLayout.SOUTH, tabToppings);
		sl_tabToppings.putConstraint(SpringLayout.EAST, panel, 300, SpringLayout.WEST, tabToppings);
		tabToppings.add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		sl_panel.putConstraint(SpringLayout.NORTH, scrollPane_2, 0, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, scrollPane_2, 0, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, scrollPane_2, 0, SpringLayout.EAST, panel);
		panel.add(scrollPane_2);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		scrollPane_2.setViewportView(table);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Add", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_tabToppings.putConstraint(SpringLayout.NORTH, panel_3, 5, SpringLayout.NORTH, tabToppings);
		sl_tabToppings.putConstraint(SpringLayout.WEST, panel_3, 5, SpringLayout.EAST, panel);
		
		JButton btnRemoveTopping = new JButton("Remove Topping");
		sl_panel.putConstraint(SpringLayout.SOUTH, scrollPane_2, -5, SpringLayout.NORTH, btnRemoveTopping);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnRemoveTopping, 0, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnRemoveTopping, 0, SpringLayout.EAST, scrollPane_2);
		panel.add(btnRemoveTopping);
		sl_tabToppings.putConstraint(SpringLayout.SOUTH, panel_3, -5, SpringLayout.SOUTH, tabToppings);
		sl_tabToppings.putConstraint(SpringLayout.EAST, panel_3, -5, SpringLayout.EAST, tabToppings);
		tabToppings.add(panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_3.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblName_2 = new JLabel("Name");
		GridBagConstraints gbc_lblName_2 = new GridBagConstraints();
		gbc_lblName_2.anchor = GridBagConstraints.WEST;
		gbc_lblName_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblName_2.gridx = 0;
		gbc_lblName_2.gridy = 0;
		panel_3.add(lblName_2, gbc_lblName_2);
		
		JLabel lblPrice_2 = new JLabel("Price");
		GridBagConstraints gbc_lblPrice_2 = new GridBagConstraints();
		gbc_lblPrice_2.anchor = GridBagConstraints.WEST;
		gbc_lblPrice_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblPrice_2.gridx = 1;
		gbc_lblPrice_2.gridy = 0;
		panel_3.add(lblPrice_2, gbc_lblPrice_2);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 0;
		gbc_textField_2.gridy = 1;
		panel_3.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 1;
		panel_3.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		panel_3.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JButton btnAdd_2 = new JButton("Add");
		GridBagConstraints gbc_btnAdd_2 = new GridBagConstraints();
		gbc_btnAdd_2.anchor = GridBagConstraints.EAST;
		gbc_btnAdd_2.gridx = 1;
		gbc_btnAdd_2.gridy = 3;
		panel_3.add(btnAdd_2, gbc_btnAdd_2);
		
		JPanel tabSides = new JPanel();
		tabbedPane.addTab("Sides", null, tabSides, null);
		SpringLayout sl_tabSides = new SpringLayout();
		tabSides.setLayout(sl_tabSides);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Sides", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_tabSides.putConstraint(SpringLayout.NORTH, panel_1, 5, SpringLayout.NORTH, tabSides);
		sl_tabSides.putConstraint(SpringLayout.WEST, panel_1, 5, SpringLayout.WEST, tabSides);
		sl_tabSides.putConstraint(SpringLayout.SOUTH, panel_1, -5, SpringLayout.SOUTH, tabSides);
		sl_tabSides.putConstraint(SpringLayout.EAST, panel_1, 300, SpringLayout.WEST, tabSides);
		tabSides.add(panel_1);
		SpringLayout sl_panel_1 = new SpringLayout();
		panel_1.setLayout(sl_panel_1);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		sl_panel_1.putConstraint(SpringLayout.NORTH, scrollPane_3, 0, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, scrollPane_3, 0, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, scrollPane_3, 0, SpringLayout.EAST, panel_1);
		panel_1.add(scrollPane_3);
		
		table_1 = new JTable();
		table_1.setFillsViewportHeight(true);
		scrollPane_3.setViewportView(table_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Add", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_tabSides.putConstraint(SpringLayout.NORTH, panel_4, 5, SpringLayout.NORTH, tabSides);
		sl_tabSides.putConstraint(SpringLayout.WEST, panel_4, 5, SpringLayout.EAST, panel_1);
		
		JButton btnRemoveSide = new JButton("Remove Side");
		sl_panel_1.putConstraint(SpringLayout.SOUTH, scrollPane_3, -5, SpringLayout.NORTH, btnRemoveSide);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, btnRemoveSide, 0, SpringLayout.SOUTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnRemoveSide, 0, SpringLayout.EAST, scrollPane_3);
		panel_1.add(btnRemoveSide);
		sl_tabSides.putConstraint(SpringLayout.SOUTH, panel_4, -5, SpringLayout.SOUTH, tabSides);
		sl_tabSides.putConstraint(SpringLayout.EAST, panel_4, -5, SpringLayout.EAST, tabSides);
		tabSides.add(panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{0, 0, 0};
		gbl_panel_4.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_4.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JLabel lblName_3 = new JLabel("Name");
		GridBagConstraints gbc_lblName_3 = new GridBagConstraints();
		gbc_lblName_3.anchor = GridBagConstraints.WEST;
		gbc_lblName_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblName_3.gridx = 0;
		gbc_lblName_3.gridy = 0;
		panel_4.add(lblName_3, gbc_lblName_3);
		
		JLabel lblPrice_3 = new JLabel("Price");
		GridBagConstraints gbc_lblPrice_3 = new GridBagConstraints();
		gbc_lblPrice_3.anchor = GridBagConstraints.WEST;
		gbc_lblPrice_3.insets = new Insets(0, 0, 5, 0);
		gbc_lblPrice_3.gridx = 1;
		gbc_lblPrice_3.gridy = 0;
		panel_4.add(lblPrice_3, gbc_lblPrice_3);
		
		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 0;
		gbc_textField_4.gridy = 1;
		panel_4.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 0);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 1;
		gbc_textField_5.gridy = 1;
		panel_4.add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 2;
		panel_4.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JButton btnAdd_3 = new JButton("Add");
		GridBagConstraints gbc_btnAdd_3 = new GridBagConstraints();
		gbc_btnAdd_3.anchor = GridBagConstraints.EAST;
		gbc_btnAdd_3.gridx = 1;
		gbc_btnAdd_3.gridy = 3;
		panel_4.add(btnAdd_3, gbc_btnAdd_3);
		
		JPanel tabSpecials = new JPanel();
		tabbedPane.addTab("Specials", null, tabSpecials, null);
		SpringLayout sl_tabSpecials = new SpringLayout();
		tabSpecials.setLayout(sl_tabSpecials);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Specials", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_tabSpecials.putConstraint(SpringLayout.NORTH, panel_2, 5, SpringLayout.NORTH, tabSpecials);
		sl_tabSpecials.putConstraint(SpringLayout.WEST, panel_2, 5, SpringLayout.WEST, tabSpecials);
		sl_tabSpecials.putConstraint(SpringLayout.SOUTH, panel_2, -5, SpringLayout.SOUTH, tabSpecials);
		sl_tabSpecials.putConstraint(SpringLayout.EAST, panel_2, 300, SpringLayout.WEST, tabSpecials);
		tabSpecials.add(panel_2);
		SpringLayout sl_panel_2 = new SpringLayout();
		panel_2.setLayout(sl_panel_2);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		sl_panel_2.putConstraint(SpringLayout.NORTH, scrollPane_4, 0, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.WEST, scrollPane_4, 0, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, scrollPane_4, 0, SpringLayout.EAST, panel_2);
		panel_2.add(scrollPane_4);
		
		table_2 = new JTable();
		table_2.setFillsViewportHeight(true);
		scrollPane_4.setViewportView(table_2);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Add", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_tabSpecials.putConstraint(SpringLayout.NORTH, panel_5, 5, SpringLayout.NORTH, tabSpecials);
		sl_tabSpecials.putConstraint(SpringLayout.WEST, panel_5, 5, SpringLayout.EAST, panel_2);
		
		JButton btnRemoveSpecial = new JButton("Remove Special");
		sl_panel_2.putConstraint(SpringLayout.SOUTH, scrollPane_4, -5, SpringLayout.NORTH, btnRemoveSpecial);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, btnRemoveSpecial, 0, SpringLayout.SOUTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, btnRemoveSpecial, 0, SpringLayout.EAST, panel_2);
		panel_2.add(btnRemoveSpecial);
		sl_tabSpecials.putConstraint(SpringLayout.SOUTH, panel_5, -5, SpringLayout.SOUTH, tabSpecials);
		sl_tabSpecials.putConstraint(SpringLayout.EAST, panel_5, -5, SpringLayout.EAST, tabSpecials);
		tabSpecials.add(panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{0, 0, 0};
		gbl_panel_5.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_5.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_5.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);
		
		JLabel lblName_1 = new JLabel("Item");
		GridBagConstraints gbc_lblName_1 = new GridBagConstraints();
		gbc_lblName_1.anchor = GridBagConstraints.WEST;
		gbc_lblName_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblName_1.gridx = 0;
		gbc_lblName_1.gridy = 0;
		panel_5.add(lblName_1, gbc_lblName_1);
		
		JLabel lblPrice_1 = new JLabel("Discount %");
		GridBagConstraints gbc_lblPrice_1 = new GridBagConstraints();
		gbc_lblPrice_1.anchor = GridBagConstraints.WEST;
		gbc_lblPrice_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblPrice_1.gridx = 1;
		gbc_lblPrice_1.gridy = 0;
		panel_5.add(lblPrice_1, gbc_lblPrice_1);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 1;
		panel_5.add(comboBox, gbc_comboBox);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.insets = new Insets(0, 0, 5, 0);
		gbc_spinner.gridx = 1;
		gbc_spinner.gridy = 1;
		panel_5.add(spinner, gbc_spinner);
		
		JLabel lblNewLabel = new JLabel("");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		panel_5.add(lblNewLabel, gbc_lblNewLabel);
		
		JButton btnAdd_1 = new JButton("Add");
		GridBagConstraints gbc_btnAdd_1 = new GridBagConstraints();
		gbc_btnAdd_1.anchor = GridBagConstraints.EAST;
		gbc_btnAdd_1.gridx = 1;
		gbc_btnAdd_1.gridy = 3;
		panel_5.add(btnAdd_1, gbc_btnAdd_1);
	}
}
