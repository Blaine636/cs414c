package cs414.a5.jlarison.pizza.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import cs414.a5.jlarison.pizza.controller.MenuControllerInterface;
import cs414.a5.jlarison.pizza.controller.OrderControllerInterface;
import cs414.a5.jlarison.pizza.controller.PaymentControllerInterface;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class OrderTypeDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldAddress;
	private JTextField textFieldPhone;
	
	private ButtonGroup bGroup;
	private JRadioButton rdbtnDelivery;
	private JRadioButton rdbtnInstore;
	
	private MenuControllerInterface menuController;
	private OrderControllerInterface orderController;
	private PaymentControllerInterface paymentController;
	private String orderName;

	/**
	 * Create the dialog.
	 * @param paymentController 
	 * @param orderController 
	 * @param menuController 
	 */
	public OrderTypeDialog(String orderNameArg, MenuControllerInterface menuControllerArg, OrderControllerInterface orderControllerArg, PaymentControllerInterface paymentControllerArg) {
		this.menuController = menuControllerArg;
		this.orderController  = orderControllerArg;
		this.paymentController = paymentControllerArg;
		this.orderName = orderNameArg;
		
		setTitle("Order Type");
		setBounds(100, 100, 450, 221);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		
		bGroup = new ButtonGroup();
		
		JPanel panel = new JPanel();
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, panel, 50, SpringLayout.NORTH, contentPanel);
		panel.setBorder(new TitledBorder(null, "Type", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_contentPanel.putConstraint(SpringLayout.NORTH, panel, 0, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, panel, 0, SpringLayout.EAST, contentPanel);
		contentPanel.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		rdbtnInstore = new JRadioButton("In-store");
		rdbtnInstore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldPhone.setEnabled(false);
				textFieldAddress.setEnabled(false);
			}
		});
		GridBagConstraints gbc_rdbtnInstore = new GridBagConstraints();
		gbc_rdbtnInstore.anchor = GridBagConstraints.WEST;
		gbc_rdbtnInstore.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnInstore.gridx = 0;
		gbc_rdbtnInstore.gridy = 0;
		panel.add(rdbtnInstore, gbc_rdbtnInstore);
		bGroup.add(rdbtnInstore);
		
		rdbtnDelivery = new JRadioButton("Delivery");
		rdbtnDelivery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldAddress.setEnabled(true);
				textFieldPhone.setEnabled(true);
			}
		});
		GridBagConstraints gbc_rdbtnDelivery = new GridBagConstraints();
		gbc_rdbtnDelivery.anchor = GridBagConstraints.WEST;
		gbc_rdbtnDelivery.gridx = 1;
		gbc_rdbtnDelivery.gridy = 0;
		panel.add(rdbtnDelivery, gbc_rdbtnDelivery);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, rdbtnDelivery, -38, SpringLayout.SOUTH, contentPanel);
		bGroup.add(rdbtnDelivery);
		sl_contentPanel.putConstraint(SpringLayout.EAST, rdbtnInstore, 0, SpringLayout.EAST, rdbtnDelivery);
		
		JPanel panel_1 = new JPanel();
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, panel_1, 75, SpringLayout.SOUTH, panel);
		panel_1.setBorder(new TitledBorder(null, "Delivery Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_contentPanel.putConstraint(SpringLayout.NORTH, panel_1, 5, SpringLayout.SOUTH, panel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, panel_1, 0, SpringLayout.WEST, panel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, panel_1, 0, SpringLayout.EAST, contentPanel);
		contentPanel.add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		GridBagConstraints gbc_lblPhoneNumber = new GridBagConstraints();
		gbc_lblPhoneNumber.anchor = GridBagConstraints.WEST;
		gbc_lblPhoneNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhoneNumber.gridx = 0;
		gbc_lblPhoneNumber.gridy = 0;
		panel_1.add(lblPhoneNumber, gbc_lblPhoneNumber);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblPhoneNumber, 10, SpringLayout.WEST, contentPanel);
		
		JLabel lblDeliveryAddress = new JLabel("Delivery Address");
		GridBagConstraints gbc_lblDeliveryAddress = new GridBagConstraints();
		gbc_lblDeliveryAddress.anchor = GridBagConstraints.WEST;
		gbc_lblDeliveryAddress.insets = new Insets(0, 0, 5, 0);
		gbc_lblDeliveryAddress.gridx = 1;
		gbc_lblDeliveryAddress.gridy = 0;
		panel_1.add(lblDeliveryAddress, gbc_lblDeliveryAddress);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, lblDeliveryAddress, -53, SpringLayout.SOUTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, rdbtnInstore, -4, SpringLayout.NORTH, lblDeliveryAddress);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setEnabled(false);
		GridBagConstraints gbc_textFieldPhone = new GridBagConstraints();
		gbc_textFieldPhone.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPhone.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldPhone.gridx = 0;
		gbc_textFieldPhone.gridy = 1;
		panel_1.add(textFieldPhone, gbc_textFieldPhone);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, textFieldPhone, 172, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, textFieldPhone, 10, SpringLayout.WEST, contentPanel);
		textFieldPhone.setColumns(10);
		sl_contentPanel.putConstraint(SpringLayout.WEST, rdbtnDelivery, 62, SpringLayout.EAST, textFieldPhone);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, lblPhoneNumber, -6, SpringLayout.NORTH, textFieldPhone);
		
		textFieldAddress = new JTextField();
		textFieldAddress.setEnabled(false);
		GridBagConstraints gbc_textFieldAddress = new GridBagConstraints();
		gbc_textFieldAddress.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAddress.gridx = 1;
		gbc_textFieldAddress.gridy = 1;
		panel_1.add(textFieldAddress, gbc_textFieldAddress);
		sl_contentPanel.putConstraint(SpringLayout.WEST, textFieldAddress, 214, SpringLayout.WEST, contentPanel);
		textFieldAddress.setColumns(10);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, textFieldAddress, 16, SpringLayout.SOUTH, lblDeliveryAddress);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblDeliveryAddress, 0, SpringLayout.WEST, textFieldAddress);
		
		bGroup.setSelected(rdbtnInstore.getModel(), true);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(rdbtnDelivery.isSelected() && textFieldAddress.getText().isEmpty()) {
							JOptionPane.showMessageDialog(getContentPane(),
									"Must enter a delivery address before a delivery order can be created!",
									"Error", JOptionPane.ERROR_MESSAGE);
						}
						else if(rdbtnDelivery.isSelected() && textFieldPhone.getText().isEmpty()) {
							JOptionPane.showMessageDialog(getContentPane(),
									"Must enter a phone number before a delivery order can be created!",
									"Error", JOptionPane.ERROR_MESSAGE);
						}
						else if (rdbtnInstore.isSelected()){
							//in-store order
							int orderId;
							try {
								orderId = orderController.createOrder(orderName);
								dispose();
								RegisterWindow window = new RegisterWindow(orderId, menuController, orderController, paymentController);
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else {
							//delivery order
							int orderId;
							try {
								orderId = orderController.createDeliveryOrder(orderName,textFieldAddress.getText(),textFieldPhone.getText());
								dispose();
								RegisterWindow window = new RegisterWindow(orderId, menuController, orderController, paymentController);
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.exit(0);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
