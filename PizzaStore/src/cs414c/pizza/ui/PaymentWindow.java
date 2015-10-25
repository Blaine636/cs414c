package cs414c.pizza.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;

public class PaymentWindow extends JDialog {
	private JPanel panelPaymentTypes;
	private JTextField textFieldBalance;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PaymentWindow dialog = new PaymentWindow();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PaymentWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PaymentWindow.class.getResource("/cs414c/pizza/ui/money_icon.png")));
		setTitle("Payment");
		setBounds(100, 100, 545, 315);
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
			
			JList listPayments = new JList();
			scrollPane.setViewportView(listPayments);
			getContentPane().add(panelBalance);
			SpringLayout sl_panelBalance = new SpringLayout();
			panelBalance.setLayout(sl_panelBalance);
			
			textFieldBalance = new JTextField();
			textFieldBalance.setBackground(new Color(255, 102, 102));
			textFieldBalance.setText("$0.00");
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
		gbl_panelAddPayment.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelAddPayment.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panelAddPayment.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
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
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 1;
		panelAddPayment.add(comboBox, gbc_comboBox);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.ipadx = 30;
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		panelAddPayment.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 1;
		gbc_label.gridy = 2;
		panelAddPayment.add(label, gbc_label);
		
		JButton btnAddPayment = new JButton("Add Payment");
		GridBagConstraints gbc_btnAddPayment = new GridBagConstraints();
		gbc_btnAddPayment.anchor = GridBagConstraints.EAST;
		gbc_btnAddPayment.gridx = 1;
		gbc_btnAddPayment.gridy = 3;
		panelAddPayment.add(btnAddPayment, gbc_btnAddPayment);
	}
}
