package cs414c.pizza.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cs414c.pizza.controller.MenuControllerInterface;
import cs414c.pizza.controller.OrderControllerInterface;
import cs414c.pizza.controller.PaymentControllerInterface;

import javax.swing.SpringLayout;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

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
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		
		rdbtnDelivery = new JRadioButton("Delivery");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, rdbtnDelivery, 10, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, rdbtnDelivery, 144, SpringLayout.WEST, contentPanel);
		contentPanel.add(rdbtnDelivery);
		
		rdbtnInstore = new JRadioButton("In-store");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, rdbtnInstore, 6, SpringLayout.SOUTH, rdbtnDelivery);
		sl_contentPanel.putConstraint(SpringLayout.WEST, rdbtnInstore, 0, SpringLayout.WEST, rdbtnDelivery);
		contentPanel.add(rdbtnInstore);
		
		bGroup = new ButtonGroup();
		bGroup.add(rdbtnDelivery);
		bGroup.add(rdbtnInstore);
		
		textFieldAddress = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.WEST, textFieldAddress, 10, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, textFieldAddress, -85, SpringLayout.SOUTH, contentPanel);
		contentPanel.add(textFieldAddress);
		textFieldAddress.setColumns(10);
		
		JLabel lblDeliveryAddress = new JLabel("Delivery Address");
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblDeliveryAddress, 0, SpringLayout.WEST, textFieldAddress);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, lblDeliveryAddress, -6, SpringLayout.NORTH, textFieldAddress);
		contentPanel.add(lblDeliveryAddress);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblPhoneNumber, 18, SpringLayout.SOUTH, textFieldAddress);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblPhoneNumber, 10, SpringLayout.WEST, contentPanel);
		contentPanel.add(lblPhoneNumber);
		
		textFieldPhone = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, textFieldPhone, 6, SpringLayout.SOUTH, lblPhoneNumber);
		sl_contentPanel.putConstraint(SpringLayout.WEST, textFieldPhone, 0, SpringLayout.WEST, textFieldAddress);
		contentPanel.add(textFieldPhone);
		textFieldPhone.setColumns(10);
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
		
		bGroup.setSelected(rdbtnDelivery.getModel(), true);
	}
}
