package cs414c.pizza.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

public abstract class Login extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final JPanel contentPanel = new JPanel();
	protected JTextField usernameField;
	protected JPasswordField passwordField;

	/**
	 * Create the dialog.
	 */
	public Login() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		setTitle(getWindowTitle() + " Login");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 348, 170);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		
		JLabel lblUsername = new JLabel("Username:");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblUsername, 5, SpringLayout.NORTH, contentPanel);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblUsername, 10, SpringLayout.WEST, contentPanel);
		contentPanel.add(lblUsername);
		
		usernameField = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, usernameField, 5, SpringLayout.SOUTH, lblUsername);
		sl_contentPanel.putConstraint(SpringLayout.WEST, usernameField, 10, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, usernameField, -10, SpringLayout.EAST, contentPanel);
		contentPanel.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblPassword, 5, SpringLayout.SOUTH, usernameField);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblPassword, 10, SpringLayout.WEST, contentPanel);
		contentPanel.add(lblPassword);
		
		passwordField = new JPasswordField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, passwordField, 5, SpringLayout.SOUTH, lblPassword);
		sl_contentPanel.putConstraint(SpringLayout.WEST, passwordField, 10, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, passwordField, -10, SpringLayout.EAST, contentPanel);
		contentPanel.add(passwordField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							okPush(usernameField.getText(), passwordField.getPassword());
						} catch (HeadlessException | RemoteException e1) {
							System.out.println("Remote Exception: " + e1.toString());
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
	}
	public abstract void okPush(String username, char[] password) throws HeadlessException, RemoteException;
	public abstract String getWindowTitle();
}
