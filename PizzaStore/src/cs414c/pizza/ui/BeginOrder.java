package cs414c.pizza.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import cs414c.pizza.controller.MenuControllerInterface;
import cs414c.pizza.controller.OrderControllerInterface;
import cs414c.pizza.controller.PaymentControllerInterface;

public abstract class BeginOrder extends JDialog {

	private final JPanel contentPanel = new JPanel();
	protected JTextField textField;
	protected MenuControllerInterface menuController;
	protected OrderControllerInterface orderController;
	protected PaymentControllerInterface paymentController;


	/**
	 * Create the dialog.
	 */
	public BeginOrder() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(BeginOrder.class.getResource("/cs414c/pizza/ui/Pizza-icon.png")));
		setTitle("Start Order: " + getWindowTitle());
		setBounds(100, 100, 380, 130);
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblOrderName = new JLabel("Order Name:");
			GridBagConstraints gbc_lblOrderName = new GridBagConstraints();
			gbc_lblOrderName.anchor = GridBagConstraints.WEST;
			gbc_lblOrderName.insets = new Insets(0, 0, 5, 0);
			gbc_lblOrderName.gridx = 0;
			gbc_lblOrderName.gridy = 0;
			contentPanel.add(lblOrderName, gbc_lblOrderName);
		}
		{
			textField = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 0;
			gbc_textField.gridy = 1;
			contentPanel.add(textField, gbc_textField);
			textField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Start Order");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(textField.getText().equals("")){
							JOptionPane.showMessageDialog(getContentPane(),
								    "Order must have a name!",
								    "Error",
								    JOptionPane.ERROR_MESSAGE);
						}else{
							startOrderBehavior();
							dispose();
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
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public abstract void startOrderBehavior();
	public abstract String getWindowTitle();

}
