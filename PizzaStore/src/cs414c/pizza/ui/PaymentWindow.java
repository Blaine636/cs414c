package cs414c.pizza.ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;

import cs414c.pizza.controller.OrderControllerInterface;
import cs414c.pizza.controller.PaymentController;
import cs414c.pizza.controller.PaymentControllerInterface;
import cs414c.pizza.dao.RewardsDAO;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public abstract class PaymentWindow extends JDialog {
	private JPanel panelPaymentTypes;
	private JTextField textFieldBalance;
	private JTextField textFieldPaymentAmount;
	protected OrderControllerInterface orderController;
	protected PaymentControllerInterface paymentController;
	protected int orderNumber;
	private JComboBox comboBoxPaymentType;
	private JList listPayments;
	private JButton btnPlaceOrder;
	//private double orderBalance;
	private BigDecimal orderBalance;
	private NumberFormat formatter = NumberFormat.getCurrencyInstance();
	private DefaultListModel listModel;
	private JButton btnAddPayment;
	private JTextField textFieldCreditRewardNumber;
	private boolean placeOrderPressed = false;
	private int year = Calendar.getInstance().get(Calendar.YEAR);

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		try {
			PaymentWindow dialog = new PaymentWindow();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public PaymentWindow(OrderControllerInterface argOrderController, PaymentControllerInterface paymentController, int argOrderNumber) {
		this.orderController = argOrderController;
		this.paymentController = paymentController;
		this.orderNumber = argOrderNumber;
		try {
			orderBalance = new BigDecimal(orderController.getOrderTotal(orderNumber));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(PaymentWindow.class.getResource("/cs414c/pizza/ui/money_icon.png")));
		setTitle(getWindowTitle() + " Payment");
		setBounds(100, 100, 545, 315);
		try {
			UIManager.setLookAndFeel(
			        UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		{
			panelPaymentTypes = new JPanel();
			springLayout.putConstraint(SpringLayout.EAST, panelPaymentTypes, 250, SpringLayout.WEST, getContentPane());
			panelPaymentTypes.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Payment Types", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			springLayout.putConstraint(SpringLayout.NORTH, panelPaymentTypes, 5, SpringLayout.NORTH, getContentPane());
			springLayout.putConstraint(SpringLayout.WEST, panelPaymentTypes, 5, SpringLayout.WEST, getContentPane());
			getContentPane().add(panelPaymentTypes);
		}
		{
			JPanel panelBalance = new JPanel();
			springLayout.putConstraint(SpringLayout.NORTH, panelBalance, -50, SpringLayout.SOUTH, getContentPane());
			springLayout.putConstraint(SpringLayout.SOUTH, panelPaymentTypes, 0, SpringLayout.NORTH, panelBalance);
			panelBalance.setBorder(new TitledBorder(null, "Balance", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			springLayout.putConstraint(SpringLayout.WEST, panelBalance, 5, SpringLayout.WEST, getContentPane());
			springLayout.putConstraint(SpringLayout.SOUTH, panelBalance, -5, SpringLayout.SOUTH, getContentPane());
			springLayout.putConstraint(SpringLayout.EAST, panelBalance, 0, SpringLayout.EAST, panelPaymentTypes);
			SpringLayout sl_panelPaymentTypes = new SpringLayout();
			panelPaymentTypes.setLayout(sl_panelPaymentTypes);
			
			JScrollPane scrollPane = new JScrollPane();
			sl_panelPaymentTypes.putConstraint(SpringLayout.NORTH, scrollPane, 0, SpringLayout.NORTH, panelPaymentTypes);
			sl_panelPaymentTypes.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, panelPaymentTypes);
			sl_panelPaymentTypes.putConstraint(SpringLayout.SOUTH, scrollPane, 0, SpringLayout.SOUTH, panelPaymentTypes);
			sl_panelPaymentTypes.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, panelPaymentTypes);
			panelPaymentTypes.add(scrollPane);
			
			listModel = new DefaultListModel();
			listPayments = new JList(listModel);
			scrollPane.setViewportView(listPayments);
			getContentPane().add(panelBalance);
			SpringLayout sl_panelBalance = new SpringLayout();
			panelBalance.setLayout(sl_panelBalance);
			
			textFieldBalance = new JTextField();
			textFieldBalance.setBackground(new Color(255, 102, 102));
			textFieldBalance.setText(formatter.format(orderBalance));
			textFieldBalance.setEditable(false);
			sl_panelBalance.putConstraint(SpringLayout.NORTH, textFieldBalance, 0, SpringLayout.NORTH, panelBalance);
			sl_panelBalance.putConstraint(SpringLayout.WEST, textFieldBalance, 0, SpringLayout.WEST, panelBalance);
			sl_panelBalance.putConstraint(SpringLayout.SOUTH, textFieldBalance, 0, SpringLayout.SOUTH, panelBalance);
			sl_panelBalance.putConstraint(SpringLayout.EAST, textFieldBalance, 0, SpringLayout.EAST, panelBalance);
			panelBalance.add(textFieldBalance);
			textFieldBalance.setColumns(10);
		}
		
		JPanel panelAddPayment = new JPanel();
		panelAddPayment.setBorder(new TitledBorder(null, "Add Payment", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		springLayout.putConstraint(SpringLayout.NORTH, panelAddPayment, 5, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panelAddPayment, 5, SpringLayout.EAST, panelPaymentTypes);
		springLayout.putConstraint(SpringLayout.SOUTH, panelAddPayment, -5, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panelAddPayment, -5, SpringLayout.EAST, getContentPane());
		getContentPane().add(panelAddPayment);
		GridBagLayout gbl_panelAddPayment = new GridBagLayout();
		gbl_panelAddPayment.columnWidths = new int[]{0, 0, 0};
		gbl_panelAddPayment.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelAddPayment.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panelAddPayment.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panelAddPayment.setLayout(gbl_panelAddPayment);
		
		JLabel lblPaymentType = new JLabel("Type");
		GridBagConstraints gbc_lblPaymentType = new GridBagConstraints();
		gbc_lblPaymentType.anchor = GridBagConstraints.WEST;
		gbc_lblPaymentType.insets = new Insets(0, 0, 5, 5);
		gbc_lblPaymentType.gridx = 0;
		gbc_lblPaymentType.gridy = 0;
		panelAddPayment.add(lblPaymentType, gbc_lblPaymentType);
		
		JLabel lblPaymentAmount = new JLabel("Amount");
		GridBagConstraints gbc_lblPaymentAmount = new GridBagConstraints();
		gbc_lblPaymentAmount.anchor = GridBagConstraints.WEST;
		gbc_lblPaymentAmount.insets = new Insets(0, 0, 5, 0);
		gbc_lblPaymentAmount.gridx = 1;
		gbc_lblPaymentAmount.gridy = 0;
		panelAddPayment.add(lblPaymentAmount, gbc_lblPaymentAmount);
		
		comboBoxPaymentType = new JComboBox();
		comboBoxPaymentType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBoxPaymentType.getSelectedIndex() == -1){
					
				}else{
					if(comboBoxPaymentType.getSelectedItem().toString().equals("Cash")){
						textFieldCreditRewardNumber.setText("");
						textFieldCreditRewardNumber.setEnabled(false);
					}else{
						textFieldCreditRewardNumber.setEnabled(true);
					}
				}
				
			}
		});
		comboBoxPaymentType.setModel(getModel());
		comboBoxPaymentType.setSelectedIndex(-1);
		GridBagConstraints gbc_comboBoxPaymentType = new GridBagConstraints();
		gbc_comboBoxPaymentType.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPaymentType.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPaymentType.gridx = 0;
		gbc_comboBoxPaymentType.gridy = 1;
		panelAddPayment.add(comboBoxPaymentType, gbc_comboBoxPaymentType);
		
		textFieldPaymentAmount = new JTextField();
		GridBagConstraints gbc_textFieldPaymentAmount = new GridBagConstraints();
		gbc_textFieldPaymentAmount.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPaymentAmount.ipadx = 30;
		gbc_textFieldPaymentAmount.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPaymentAmount.gridx = 1;
		gbc_textFieldPaymentAmount.gridy = 1;
		panelAddPayment.add(textFieldPaymentAmount, gbc_textFieldPaymentAmount);
		textFieldPaymentAmount.setColumns(10);
		
		JLabel lblCreditrewardsNumber = new JLabel("Credit/Rewards Number");
		GridBagConstraints gbc_lblCreditrewardsNumber = new GridBagConstraints();
		gbc_lblCreditrewardsNumber.anchor = GridBagConstraints.WEST;
		gbc_lblCreditrewardsNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblCreditrewardsNumber.gridx = 0;
		gbc_lblCreditrewardsNumber.gridy = 2;
		panelAddPayment.add(lblCreditrewardsNumber, gbc_lblCreditrewardsNumber);
		
		textFieldCreditRewardNumber = new JTextField();
		textFieldCreditRewardNumber.setEnabled(false);
		GridBagConstraints gbc_textFieldCreditRewardNumber = new GridBagConstraints();
		gbc_textFieldCreditRewardNumber.gridwidth = 2;
		gbc_textFieldCreditRewardNumber.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCreditRewardNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCreditRewardNumber.gridx = 0;
		gbc_textFieldCreditRewardNumber.gridy = 3;
		panelAddPayment.add(textFieldCreditRewardNumber, gbc_textFieldCreditRewardNumber);
		textFieldCreditRewardNumber.setColumns(10);
		
		JLabel lblExpiration = new JLabel("Expiration (month)");
		GridBagConstraints gbc_lblExpiration = new GridBagConstraints();
		gbc_lblExpiration.anchor = GridBagConstraints.WEST;
		gbc_lblExpiration.insets = new Insets(0, 0, 5, 5);
		gbc_lblExpiration.gridx = 0;
		gbc_lblExpiration.gridy = 4;
		panelAddPayment.add(lblExpiration, gbc_lblExpiration);
		
		JLabel lblYear = new JLabel("Year");
		GridBagConstraints gbc_lblYear = new GridBagConstraints();
		gbc_lblYear.anchor = GridBagConstraints.WEST;
		gbc_lblYear.insets = new Insets(0, 0, 5, 0);
		gbc_lblYear.gridx = 1;
		gbc_lblYear.gridy = 4;
		panelAddPayment.add(lblYear, gbc_lblYear);
		
		JComboBox comboBox = new JComboBox(monthModel());
		comboBox.setSelectedIndex(-1);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 5;
		panelAddPayment.add(comboBox, gbc_comboBox);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(year, year, year + 10, 1));
		spinner.setEditor(new JSpinner.NumberEditor(spinner,"#"));
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.insets = new Insets(0, 0, 5, 0);
		gbc_spinner.gridx = 1;
		gbc_spinner.gridy = 5;
		panelAddPayment.add(spinner, gbc_spinner);
		
		JLabel label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 1;
		gbc_label.gridy = 6;
		panelAddPayment.add(label, gbc_label);
		
		btnAddPayment = new JButton("Add Payment");
		btnAddPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addPaymentPress();
			}
		});
		GridBagConstraints gbc_btnAddPayment = new GridBagConstraints();
		gbc_btnAddPayment.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddPayment.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddPayment.gridx = 1;
		gbc_btnAddPayment.gridy = 7;
		panelAddPayment.add(btnAddPayment, gbc_btnAddPayment);
		
		btnPlaceOrder = new JButton("Place Order");
		btnPlaceOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				placeOrderPressed = true;
				try {
					orderController.placeOrder(orderNumber);
					JOptionPane.showMessageDialog(getContentPane(), "Order Completed!", "Complete!", JOptionPane.INFORMATION_MESSAGE);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispose();
			}
		});
		btnPlaceOrder.setEnabled(false);
		GridBagConstraints gbc_btnPlaceOrder = new GridBagConstraints();
		gbc_btnPlaceOrder.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPlaceOrder.gridx = 1;
		gbc_btnPlaceOrder.gridy = 8;
		panelAddPayment.add(btnPlaceOrder, gbc_btnPlaceOrder);
	}
	
	public void addPaymentPress(){
		if(comboBoxPaymentType.getSelectedIndex() == -1 || textFieldPaymentAmount.getText().equals("")){
			JOptionPane.showMessageDialog(getContentPane(),
					"All fields must have a value\n"
							+ "before a payment is added to an order!",
							"Error",
							JOptionPane.ERROR_MESSAGE);
		}else{
			try{
				double amount = Double.parseDouble(textFieldPaymentAmount.getText());
			}catch(Exception e){
				JOptionPane.showMessageDialog(getContentPane(),
						"Payment amount not in correct format!\n"
								+ "Must be a double (0.00)",
								"Error",
								JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(textFieldBalance.getText().equals("$0.00")){
				btnPlaceOrder.setEnabled(true);
			}else{
				try {
				paymentController = new PaymentController(new RewardsDAO());
				switch(comboBoxPaymentType.getSelectedItem().toString()){
				
				case "Credit":
					
						if(this.paymentController.makeCreditPayment(textFieldCreditRewardNumber.getText(), Double.parseDouble(textFieldPaymentAmount.getText()))){
							calculateBalance();
							comboBoxPaymentType.setSelectedIndex(-1);
							textFieldPaymentAmount.setText("");
						}else{
							JOptionPane.showMessageDialog(getContentPane(),
									"Invalid Card Number!\n"
											+ "Must be a valid 16 digit card number.",
											"Error",
											JOptionPane.ERROR_MESSAGE);
						}
					break;
					
				case "Rewards":
					int IDnum;
					if(textFieldCreditRewardNumber.getText().length() > 4 || textFieldCreditRewardNumber.getText().length() < 1){
						JOptionPane.showMessageDialog(getContentPane(),
								"Rewards ID number should be 1 to 4 digits!",
										"Error",
										JOptionPane.ERROR_MESSAGE);
						break;
					}
					
					try{
						IDnum = Integer.parseInt(textFieldCreditRewardNumber.getText());
						//Integer.parseInt(textFieldCreditRewardNumber.getText());
					}catch(Exception e){
						JOptionPane.showMessageDialog(getContentPane(),
								"Non numberic rewards ID entry!",
										"Error",
										JOptionPane.ERROR_MESSAGE);
						break;
					}
					double pointBalance = paymentController.getRewardsBalance(Integer.parseInt(textFieldCreditRewardNumber.getText()));
					System.out.println(pointBalance);
					if(pointBalance <= 0.0 || pointBalance < Double.parseDouble(textFieldPaymentAmount.getText())){
						JOptionPane.showMessageDialog(getContentPane(),
								"Not enough rewards points!\n" + 
										formatter.format(pointBalance) + " available.",
										"Error",
										JOptionPane.ERROR_MESSAGE);
						break;
					}else{
						if(orderBalance.compareTo(new BigDecimal(textFieldPaymentAmount.getText())) == -1){
							//BigDecimal onlyUse = orderBalance.subtract(new BigDecimal(textFieldPaymentAmount.getText()));
							//BigDecimal onlyUse = new BigDecimal(textFieldPaymentAmount.getText()).subtract(orderBalance);
							paymentController.useRewardsPoints(IDnum, Double.parseDouble(orderBalance.toString()));
							calculateBalance();
						}else{
							paymentController.useRewardsPoints(IDnum, Double.parseDouble(textFieldPaymentAmount.getText()));
							calculateBalance();
						}
					}
					
					
					break;
					
				case "Cash":
					//paymentController.makeCashPayment(Double.parseDouble(textFieldPaymentAmount.getText()), Double.parseDouble(orderBalance.toString()));
					String change = calculateBalance();
					if(!change.equals("")){
						JOptionPane.showMessageDialog(getContentPane(),
								"Change:" + change,
										"Change",
										JOptionPane.INFORMATION_MESSAGE);
					}
					comboBoxPaymentType.setSelectedIndex(-1);
					textFieldPaymentAmount.setText("");
					
					break;
				}
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	
	public String calculateBalance(){
		//Double payAmount = Double.parseDouble(textFieldPaymentAmount.getText());
		BigDecimal payAmount = new BigDecimal(textFieldPaymentAmount.getText());
		//if(payAmount < orderBalance){
		if(payAmount.compareTo(orderBalance) == -1 && orderBalance.compareTo(new BigDecimal(0.01)) == 1){
			//orderBalance = orderBalance - payAmount;
			orderBalance = orderBalance.subtract(payAmount);
			textFieldBalance.setText(formatter.format(orderBalance));
			listModel.addElement(comboBoxPaymentType.getSelectedItem().toString() + ": " + formatter.format(payAmount));
			return "";
		}else{
			textFieldBalance.setText(formatter.format(0));
			listModel.addElement(comboBoxPaymentType.getSelectedItem().toString() + ": " + formatter.format(orderBalance));
			btnAddPayment.setEnabled(false);
			btnPlaceOrder.setEnabled(true);
			textFieldCreditRewardNumber.setText("");
			return formatter.format(payAmount.subtract(orderBalance));
		}
	}
	
	public boolean isPlaceOrderPressed() {
		return placeOrderPressed;
	}
	
	public DefaultComboBoxModel monthModel(){
		return new DefaultComboBoxModel(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"});
	}
	
	public abstract String getWindowTitle();
	public abstract DefaultComboBoxModel getModel();
}
