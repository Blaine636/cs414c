package cs414c.pizza.ui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import cs414c.pizza.controller.MenuController;
import cs414c.pizza.controller.OrderController;
import cs414c.pizza.controller.PaymentController;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

public abstract class OrderWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel_2;
	private JTable tableOrderItems;
	private JPanel panel_3;
	private JList listToppings;
	private JTextPane txtpnTotal;
	private JSpinner spinnerSideDrinkQuantity;
	private JSpinner spinnerPizzaQuantity;
	private JComboBox comboBoxPizzaType;
	private JComboBox comboBoxPizzaSize;
	private JComboBox comboBoxSideDrinkType;
	protected MenuController menuController;
	protected OrderController orderController;
	protected PaymentController paymentController;


	/**
	 * Create the dialog.
	 */
	public OrderWindow() {
		menuController = new MenuController();
		setTitle(getWindowTitle());
		setIconImage(Toolkit.getDefaultToolkit().getImage(OrderWindow_old.class.getResource("/cs414c/pizza/ui/Pizza-icon.png")));
		setBounds(100, 100, 610, 423);
		setMinimumSize(new Dimension(610, 423));
		{
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			{
				JMenu mnFile = new JMenu("File");
				menuBar.add(mnFile);
				{
					JMenuItem mntmExit = new JMenuItem("Exit");
					mntmExit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							dispose();
						}
					});
					mnFile.add(mntmExit);
				}
			}
		}
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JPanel panelPizza = new JPanel();
		springLayout.putConstraint(SpringLayout.SOUTH, panelPizza, -110, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panelPizza, -5, SpringLayout.EAST, getContentPane());
		panelPizza.setBorder(new TitledBorder(null, "Pizza", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		springLayout.putConstraint(SpringLayout.NORTH, panelPizza, 10, SpringLayout.NORTH, getContentPane());
		getContentPane().add(panelPizza);
		GridBagLayout gbl_panelPizza = new GridBagLayout();
		gbl_panelPizza.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelPizza.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelPizza.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelPizza.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelPizza.setLayout(gbl_panelPizza);
		{
			JLabel lblPizzaSize = new JLabel("Size");
			GridBagConstraints gbc_lblPizzaSize = new GridBagConstraints();
			gbc_lblPizzaSize.anchor = GridBagConstraints.WEST;
			gbc_lblPizzaSize.insets = new Insets(0, 0, 5, 5);
			gbc_lblPizzaSize.gridx = 0;
			gbc_lblPizzaSize.gridy = 0;
			panelPizza.add(lblPizzaSize, gbc_lblPizzaSize);
		}
		{
			JLabel lblPizzaType = new JLabel("Type");
			GridBagConstraints gbc_lblPizzaType = new GridBagConstraints();
			gbc_lblPizzaType.anchor = GridBagConstraints.WEST;
			gbc_lblPizzaType.insets = new Insets(0, 0, 5, 5);
			gbc_lblPizzaType.gridx = 1;
			gbc_lblPizzaType.gridy = 0;
			panelPizza.add(lblPizzaType, gbc_lblPizzaType);
		}
		{
			JLabel lblPizzaQuantity = new JLabel("Quantity");
			GridBagConstraints gbc_lblPizzaQuantity = new GridBagConstraints();
			gbc_lblPizzaQuantity.ipadx = 30;
			gbc_lblPizzaQuantity.anchor = GridBagConstraints.WEST;
			gbc_lblPizzaQuantity.insets = new Insets(0, 0, 5, 0);
			gbc_lblPizzaQuantity.gridx = 2;
			gbc_lblPizzaQuantity.gridy = 0;
			panelPizza.add(lblPizzaQuantity, gbc_lblPizzaQuantity);
		}
		{
			comboBoxPizzaSize = new JComboBox(menuController.getSizes().toArray());
			comboBoxPizzaSize.setSelectedIndex(-1);
			comboBoxPizzaSize.setMaximumRowCount(10);
			GridBagConstraints gbc_comboBoxPizzaSize = new GridBagConstraints();
			gbc_comboBoxPizzaSize.ipadx = 50;
			gbc_comboBoxPizzaSize.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxPizzaSize.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxPizzaSize.gridx = 0;
			gbc_comboBoxPizzaSize.gridy = 1;
			panelPizza.add(comboBoxPizzaSize, gbc_comboBoxPizzaSize);
		}
		{
			/*DefaultComboBoxModel model = new DefaultComboBoxModel<>();
			for(int i : menuController.getPizzas()){
				String pizzaDisplay = "";
				pizzaDisplay += menuController.getItemName(i);
				pizzaDisplay += ": ";
				pizzaDisplay += menuController.getItemPrice(i);
				model.addElement(pizzaDisplay);
			}*/
			comboBoxPizzaType = new JComboBox(menuController.getPizzas().toArray());
			comboBoxPizzaType.setSelectedIndex(-1);
			comboBoxPizzaType.setMaximumRowCount(10);
			GridBagConstraints gbc_comboBoxPizzaType = new GridBagConstraints();
			gbc_comboBoxPizzaType.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxPizzaType.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxPizzaType.gridx = 1;
			gbc_comboBoxPizzaType.gridy = 1;
			panelPizza.add(comboBoxPizzaType, gbc_comboBoxPizzaType);
		}
		{
			spinnerPizzaQuantity = new JSpinner();
			spinnerPizzaQuantity.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			GridBagConstraints gbc_spinnerPizzaQuantity = new GridBagConstraints();
			gbc_spinnerPizzaQuantity.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinnerPizzaQuantity.insets = new Insets(0, 0, 5, 0);
			gbc_spinnerPizzaQuantity.gridx = 2;
			gbc_spinnerPizzaQuantity.gridy = 1;
			panelPizza.add(spinnerPizzaQuantity, gbc_spinnerPizzaQuantity);
		}
		{
			JLabel lblToppings = new JLabel("Toppings:");
			GridBagConstraints gbc_lblToppings = new GridBagConstraints();
			gbc_lblToppings.anchor = GridBagConstraints.WEST;
			gbc_lblToppings.insets = new Insets(0, 0, 5, 5);
			gbc_lblToppings.gridx = 0;
			gbc_lblToppings.gridy = 2;
			panelPizza.add(lblToppings, gbc_lblToppings);
		}
		{
			JScrollPane scrollPane_1 = new JScrollPane();
			GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
			gbc_scrollPane_1.gridwidth = 3;
			gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
			gbc_scrollPane_1.gridx = 0;
			gbc_scrollPane_1.gridy = 3;
			panelPizza.add(scrollPane_1, gbc_scrollPane_1);
			{
				
				/*DefaultListModel model = new DefaultListModel<>();
				for(ItemEnty i : menuController.getToppings()){
					String toppingDisplay = "";
					toppingDisplay += menuController.getItemName(i);
					toppingDisplay += ": ";
					toppingDisplay += menuController.getItemPrice(i);
					model.addElement(toppingDisplay);
				}*/
				listToppings = new JList(menuController.getToppings().toArray());
				scrollPane_1.setViewportView(listToppings);
			}
		}
		{
			panel_2 = new JPanel();
			springLayout.putConstraint(SpringLayout.SOUTH, panel_2, -10, SpringLayout.SOUTH, getContentPane());
			springLayout.putConstraint(SpringLayout.EAST, panel_2, -5, SpringLayout.EAST, getContentPane());
			getContentPane().add(panel_2);
			GridBagLayout gbl_panel_2 = new GridBagLayout();
			gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0};
			gbl_panel_2.rowHeights = new int[]{0, 0};
			gbl_panel_2.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_panel_2.rowWeights = new double[]{1.0, Double.MIN_VALUE};
			panel_2.setLayout(gbl_panel_2);
			{
				JButton btnAddToOrder = new JButton("Add To Order");
				btnAddToOrder.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						for(Object item : listToppings.getSelectedValuesList()){
							System.out.println(((ItemEntry)item).getName());
						}
						
					}
				});
				GridBagConstraints gbc_btnAddToOrder = new GridBagConstraints();
				gbc_btnAddToOrder.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnAddToOrder.insets = new Insets(0, 0, 0, 5);
				gbc_btnAddToOrder.gridx = 0;
				gbc_btnAddToOrder.gridy = 0;
				panel_2.add(btnAddToOrder, gbc_btnAddToOrder);
			}
			{
				JButton btnPayNow = new JButton("Pay Now");
				btnPayNow.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						paymentBehavior();
					}
				});
				GridBagConstraints gbc_btnPayNow = new GridBagConstraints();
				gbc_btnPayNow.gridwidth = 2;
				gbc_btnPayNow.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnPayNow.gridx = 1;
				gbc_btnPayNow.gridy = 0;
				panel_2.add(btnPayNow, gbc_btnPayNow);
			}
		}
		{
			panel_3 = new JPanel();
			springLayout.putConstraint(SpringLayout.WEST, panelPizza, 5, SpringLayout.EAST, panel_3);
			springLayout.putConstraint(SpringLayout.WEST, panel_3, 10, SpringLayout.WEST, getContentPane());
			springLayout.putConstraint(SpringLayout.SOUTH, panel_3, -10, SpringLayout.SOUTH, getContentPane());
			springLayout.putConstraint(SpringLayout.WEST, panel_2, 5, SpringLayout.EAST, panel_3);
			getContentPane().add(panel_3);
			GridBagLayout gbl_panel_3 = new GridBagLayout();
			gbl_panel_3.columnWidths = new int[]{0, 0, 0, 0};
			gbl_panel_3.rowHeights = new int[]{0, 0};
			gbl_panel_3.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
			gbl_panel_3.rowWeights = new double[]{1.0, Double.MIN_VALUE};
			panel_3.setLayout(gbl_panel_3);
			{
				JButton btnRemoveItem = new JButton("Remove Item");
				GridBagConstraints gbc_btnRemoveItem = new GridBagConstraints();
				gbc_btnRemoveItem.insets = new Insets(0, 0, 0, 5);
				gbc_btnRemoveItem.gridx = 0;
				gbc_btnRemoveItem.gridy = 0;
				panel_3.add(btnRemoveItem, gbc_btnRemoveItem);
			}
			{
				JLabel lblTotal = new JLabel("Total:");
				GridBagConstraints gbc_lblTotal = new GridBagConstraints();
				gbc_lblTotal.insets = new Insets(0, 0, 0, 5);
				gbc_lblTotal.gridx = 1;
				gbc_lblTotal.gridy = 0;
				panel_3.add(lblTotal, gbc_lblTotal);
			}
			{
				txtpnTotal = new JTextPane();
				txtpnTotal.setBackground(new Color(144, 238, 144));
				txtpnTotal.setEditable(false);
				txtpnTotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
				txtpnTotal.setText("$0.00");
				GridBagConstraints gbc_txtpnTotal = new GridBagConstraints();
				gbc_txtpnTotal.fill = GridBagConstraints.BOTH;
				gbc_txtpnTotal.gridx = 2;
				gbc_txtpnTotal.gridy = 0;
				panel_3.add(txtpnTotal, gbc_txtpnTotal);
			}
		}
		{
			JPanel panelOrder = new JPanel();
			springLayout.putConstraint(SpringLayout.EAST, panelOrder, 250, SpringLayout.WEST, getContentPane());
			springLayout.putConstraint(SpringLayout.NORTH, panel_2, 5, SpringLayout.SOUTH, panelOrder);
			springLayout.putConstraint(SpringLayout.NORTH, panel_3, 5, SpringLayout.SOUTH, panelOrder);
			springLayout.putConstraint(SpringLayout.EAST, panel_3, 0, SpringLayout.EAST, panelOrder);
			panelOrder.setBorder(new TitledBorder(null, "Order Items:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			springLayout.putConstraint(SpringLayout.NORTH, panelOrder, 10, SpringLayout.NORTH, getContentPane());
			springLayout.putConstraint(SpringLayout.WEST, panelOrder, 10, SpringLayout.WEST, getContentPane());
			springLayout.putConstraint(SpringLayout.SOUTH, panelOrder, -40, SpringLayout.SOUTH, getContentPane());
			getContentPane().add(panelOrder);
			GridBagLayout gbl_panelOrder = new GridBagLayout();
			gbl_panelOrder.columnWidths = new int[]{0, 0};
			gbl_panelOrder.rowHeights = new int[]{0, 0};
			gbl_panelOrder.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_panelOrder.rowWeights = new double[]{1.0, Double.MIN_VALUE};
			panelOrder.setLayout(gbl_panelOrder);
			{
				JScrollPane scrollPane = new JScrollPane();
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 0;
				gbc_scrollPane.gridy = 0;
				panelOrder.add(scrollPane, gbc_scrollPane);
				
				tableOrderItems = new JTable();
				tableOrderItems.setFillsViewportHeight(true);
				scrollPane.setViewportView(tableOrderItems);
			}
		}
		{
			JPanel panelSideDrink = new JPanel();
			panelSideDrink.setBorder(new TitledBorder(null, "Sides/Drinks", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			springLayout.putConstraint(SpringLayout.NORTH, panelSideDrink, 5, SpringLayout.SOUTH, panelPizza);
			springLayout.putConstraint(SpringLayout.WEST, panelSideDrink, 0, SpringLayout.WEST, panel_2);
			springLayout.putConstraint(SpringLayout.SOUTH, panelSideDrink, -5, SpringLayout.NORTH, panel_2);
			springLayout.putConstraint(SpringLayout.EAST, panelSideDrink, 0, SpringLayout.EAST, panel_2);
			getContentPane().add(panelSideDrink);
			GridBagLayout gbl_panelSideDrink = new GridBagLayout();
			gbl_panelSideDrink.columnWidths = new int[]{0, 0, 0};
			gbl_panelSideDrink.rowHeights = new int[]{0, 0, 0};
			gbl_panelSideDrink.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
			gbl_panelSideDrink.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			panelSideDrink.setLayout(gbl_panelSideDrink);
			{
				JLabel lblSideDrinkType = new JLabel("Type");
				GridBagConstraints gbc_lblSideDrinkType = new GridBagConstraints();
				gbc_lblSideDrinkType.anchor = GridBagConstraints.WEST;
				gbc_lblSideDrinkType.insets = new Insets(0, 0, 5, 5);
				gbc_lblSideDrinkType.gridx = 0;
				gbc_lblSideDrinkType.gridy = 0;
				panelSideDrink.add(lblSideDrinkType, gbc_lblSideDrinkType);
			}
			{
				JLabel lblSideDrinkQuantity = new JLabel("Quantity");
				GridBagConstraints gbc_lblSideDrinkQuantity = new GridBagConstraints();
				gbc_lblSideDrinkQuantity.ipadx = 30;
				gbc_lblSideDrinkQuantity.anchor = GridBagConstraints.WEST;
				gbc_lblSideDrinkQuantity.insets = new Insets(0, 0, 5, 0);
				gbc_lblSideDrinkQuantity.gridx = 1;
				gbc_lblSideDrinkQuantity.gridy = 0;
				panelSideDrink.add(lblSideDrinkQuantity, gbc_lblSideDrinkQuantity);
			}
			{
				comboBoxSideDrinkType = new JComboBox(menuController.getSides().toArray());
				comboBoxSideDrinkType.setSelectedIndex(-1);
				comboBoxSideDrinkType.setMaximumRowCount(10);
				GridBagConstraints gbc_comboBoxSideDrinkType = new GridBagConstraints();
				gbc_comboBoxSideDrinkType.insets = new Insets(0, 0, 0, 5);
				gbc_comboBoxSideDrinkType.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBoxSideDrinkType.gridx = 0;
				gbc_comboBoxSideDrinkType.gridy = 1;
				panelSideDrink.add(comboBoxSideDrinkType, gbc_comboBoxSideDrinkType);
			}
			
			spinnerSideDrinkQuantity = new JSpinner();
			spinnerSideDrinkQuantity.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			GridBagConstraints gbc_spinnerSideDrinkQuantity = new GridBagConstraints();
			gbc_spinnerSideDrinkQuantity.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinnerSideDrinkQuantity.gridx = 1;
			gbc_spinnerSideDrinkQuantity.gridy = 1;
			panelSideDrink.add(spinnerSideDrinkQuantity, gbc_spinnerSideDrinkQuantity);
		}
	}
	public abstract String getWindowTitle();
	public abstract void paymentBehavior();
}
