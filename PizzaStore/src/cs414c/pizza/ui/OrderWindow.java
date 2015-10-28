package cs414c.pizza.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

import org.apache.commons.lang3.ArrayUtils;

import cs414c.pizza.controller.MenuController;
import cs414c.pizza.controller.OrderController;
import cs414c.pizza.controller.PaymentController;

public abstract class OrderWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel_2;
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
	protected String orderName;
	protected int orderNumber;
	private JList listOrderItems;
	private DefaultListModel listModel;


	/**
	 * Create the dialog.
	 */
	public OrderWindow(String orderName) {

		this.orderName = orderName;
		System.out.println(this.orderName);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				exitBehavior();
			}
		});
		menuController = new MenuController();
		setTitle(getWindowTitle());
		setIconImage(Toolkit.getDefaultToolkit().getImage(OrderWindow_old.class.getResource("/cs414c/pizza/ui/Pizza-icon.png")));
		setBounds(100, 100, 650, 425);
		setMinimumSize(new Dimension(650, 425));
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
							exitBehavior();
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



			comboBoxPizzaType.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					selectPizza();
				}
			});
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
			spinnerPizzaQuantity.setModel(new SpinnerNumberModel(1, 1, 10, 1));
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
				listToppings.setEnabled(false);
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
			gbl_panel_2.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
			gbl_panel_2.rowWeights = new double[]{1.0, Double.MIN_VALUE};
			panel_2.setLayout(gbl_panel_2);
			{
				JButton btnAddToOrder = new JButton("Add Pizza");
				btnAddToOrder.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(comboBoxPizzaSize.getSelectedIndex() == -1 || comboBoxPizzaType.getSelectedIndex() == -1){
							JOptionPane.showMessageDialog(getContentPane(),
									"All fields must have a value\n"
											+ "before a pizza is added to an order!",
											"Error",
											JOptionPane.ERROR_MESSAGE);
						}else{
							for(int i = 0; i < (Integer)spinnerPizzaQuantity.getValue(); i++){
								PizzaEntry PE = (PizzaEntry) comboBoxPizzaType.getSelectedItem();
								PE.setSize((SizeEntry)comboBoxPizzaSize.getSelectedItem());
								UUID uuid = orderController.addPizzaToOrder(orderNumber, PE, listToppings.getSelectedValuesList(), (SizeEntry)comboBoxPizzaSize.getSelectedItem());
								OrderPizzaEntry oie = orderController.getOrderItem(orderNumber, uuid);
								listModel.addElement(oie);
							}
							txtpnTotal.setText(orderController.getOrderTotalString(orderNumber));
							comboBoxPizzaSize.setSelectedIndex(-1);
							comboBoxPizzaType.setSelectedIndex(-1);
							spinnerPizzaQuantity.setValue(1);
							listToppings.clearSelection();
						}
						/*for(Object item : listToppings.getSelectedValuesList()){
							System.out.println(((ItemEntry)item).getName());
						}*/
						//orderController.addPizzaToOrder(orderId, pizza, toppings, size)

					}
				});
				GridBagConstraints gbc_btnAddToOrder = new GridBagConstraints();
				gbc_btnAddToOrder.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnAddToOrder.insets = new Insets(0, 0, 0, 5);
				gbc_btnAddToOrder.gridx = 0;
				gbc_btnAddToOrder.gridy = 0;
				panel_2.add(btnAddToOrder, gbc_btnAddToOrder);
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
				btnRemoveItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(listModel.size() == 0){

						}else{
							if(listOrderItems.getSelectedValue() instanceof OrderSideEntry ){
								orderController.removeItemFromOrder(orderNumber, ((OrderSideEntry)listOrderItems.getSelectedValue()).getUUID());
								listModel.removeElement(listOrderItems.getSelectedValue());
								txtpnTotal.setText(orderController.getOrderTotalString(orderNumber));
							}else if(listOrderItems.getSelectedValue() instanceof OrderPizzaEntry){
								orderController.removeItemFromOrder(orderNumber, ((OrderPizzaEntry)listOrderItems.getSelectedValue()).getUUID());
								listModel.removeElement(listOrderItems.getSelectedValue());
								txtpnTotal.setText(orderController.getOrderTotalString(orderNumber));
							}else{}
							
						}
					}
				});
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
				{
					listModel = new DefaultListModel();
					listOrderItems = new JList(listModel);
					scrollPane.setViewportView(listOrderItems);
				}
			}
		}
		{
			JPanel panelSideDrink = new JPanel();
			panelSideDrink.setBorder(new TitledBorder(null, "Sides/Drinks", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			springLayout.putConstraint(SpringLayout.NORTH, panelSideDrink, 5, SpringLayout.SOUTH, panelPizza);
			springLayout.putConstraint(SpringLayout.WEST, panelSideDrink, 0, SpringLayout.WEST, panel_2);
			springLayout.putConstraint(SpringLayout.SOUTH, panelSideDrink, -5, SpringLayout.NORTH, panel_2);
			springLayout.putConstraint(SpringLayout.EAST, panelSideDrink, 0, SpringLayout.EAST, panel_2);
			{
				JButton btnPayNow = new JButton("Pay Now");
				btnPayNow.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						paymentBehavior();
					}
				});
				{
					JButton btnAddSide = new JButton("Add Side");
					btnAddSide.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(comboBoxSideDrinkType.getSelectedIndex() == -1){
								JOptionPane.showMessageDialog(getContentPane(),
										"All fields must have a value\n"
												+ "before a side is added to an order!",
												"Error",
												JOptionPane.ERROR_MESSAGE);
								return;
							}else{
								for(int i = 0; i < (Integer)spinnerSideDrinkQuantity.getValue(); i++){
									ItemEntry side = (ItemEntry) comboBoxSideDrinkType.getSelectedItem();
//									UUID uuid = orderController.addPizzaToOrder(orderNumber, PE, listToppings.getSelectedValuesList(), (SizeEntry)comboBoxPizzaSize.getSelectedItem());
									UUID uuid = orderController.addItemToOrder(orderNumber, side);
									OrderSideEntry ose = orderController.getOrderSide(orderNumber, uuid);
									listModel.addElement(ose);
								}
								txtpnTotal.setText(orderController.getOrderTotalString(orderNumber));
								comboBoxSideDrinkType.setSelectedIndex(-1);
								spinnerSideDrinkQuantity.setValue(1);
							}
						}
					});
					GridBagConstraints gbc_btnAddSide = new GridBagConstraints();
					gbc_btnAddSide.fill = GridBagConstraints.HORIZONTAL;
					gbc_btnAddSide.insets = new Insets(0, 0, 0, 5);
					gbc_btnAddSide.gridx = 1;
					gbc_btnAddSide.gridy = 0;
					panel_2.add(btnAddSide, gbc_btnAddSide);
				}
				GridBagConstraints gbc_btnPayNow = new GridBagConstraints();
				gbc_btnPayNow.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnPayNow.gridx = 2;
				gbc_btnPayNow.gridy = 0;
				panel_2.add(btnPayNow, gbc_btnPayNow);
			}
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
			spinnerSideDrinkQuantity.setModel(new SpinnerNumberModel(1, 1, 10, 1));
			GridBagConstraints gbc_spinnerSideDrinkQuantity = new GridBagConstraints();
			gbc_spinnerSideDrinkQuantity.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinnerSideDrinkQuantity.gridx = 1;
			gbc_spinnerSideDrinkQuantity.gridy = 1;
			panelSideDrink.add(spinnerSideDrinkQuantity, gbc_spinnerSideDrinkQuantity);
		}
	}

	private void selectPizza(){
		List<Integer> indices = new ArrayList<Integer>();
		if(comboBoxPizzaType.getSelectedIndex() != -1){
			PizzaEntry i;
			i = (PizzaEntry) comboBoxPizzaType.getSelectedItem();
			if(i.getName().equals("-Build It-")){
				listToppings.clearSelection();
				listToppings.setEnabled(true);
			}else{
				listToppings.clearSelection();
				listToppings.setEnabled(false);
				for(UUID itemID : i.getToppingIds()){
					System.out.println(i.getToppingIds().toString());
					ItemEntry topping = menuController.getItem(itemID);
					for(int j = 0; j < listToppings.getModel().getSize(); j++){
						//System.out.println("List object: " + ((ItemEntry)listToppings.getModel().getElementAt(j)).getItemId() + " menu object: " + topping.getItemId());
						if(listToppings.getModel().getElementAt(j).equals(topping)){
							indices.add(j);
						}
					}
					//listToppings.setSelectedValue(topping, false);
				}
				Integer[] intArray = indices.toArray(new Integer[indices.size()]);
				listToppings.setSelectedIndices(ArrayUtils.toPrimitive(intArray));
			}
		}
	}

	protected void createOrder() {
		this.orderNumber = orderController.createOrder(this.orderName);
	}

	public abstract String getWindowTitle();
	public abstract void paymentBehavior();
	public abstract void exitBehavior();
}
