package cs414c.pizza.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.SpringLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.border.TitledBorder;

public class OrderWindow_old extends JDialog {
	private JPanel panel_2;
	private JTable table;
	private JPanel panel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			OrderWindow_old dialog = new OrderWindow_old();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public OrderWindow_old() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(OrderWindow_old.class.getResource("/cs414c/pizza/ui/Pizza-icon.png")));
		setBounds(100, 100, 610, 423);
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
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -110, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, -5, SpringLayout.EAST, getContentPane());
		panel.setBorder(new TitledBorder(null, "Pizza", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		springLayout.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.NORTH, getContentPane());
		getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		{
			JLabel lblSize = new JLabel("Size");
			GridBagConstraints gbc_lblSize = new GridBagConstraints();
			gbc_lblSize.anchor = GridBagConstraints.WEST;
			gbc_lblSize.insets = new Insets(0, 0, 5, 5);
			gbc_lblSize.gridx = 0;
			gbc_lblSize.gridy = 0;
			panel.add(lblSize, gbc_lblSize);
		}
		{
			JLabel lblType = new JLabel("Type");
			GridBagConstraints gbc_lblType = new GridBagConstraints();
			gbc_lblType.anchor = GridBagConstraints.WEST;
			gbc_lblType.insets = new Insets(0, 0, 5, 5);
			gbc_lblType.gridx = 1;
			gbc_lblType.gridy = 0;
			panel.add(lblType, gbc_lblType);
		}
		{
			JLabel lblQuantity = new JLabel("Quantity");
			GridBagConstraints gbc_lblQuantity = new GridBagConstraints();
			gbc_lblQuantity.ipadx = 30;
			gbc_lblQuantity.anchor = GridBagConstraints.WEST;
			gbc_lblQuantity.insets = new Insets(0, 0, 5, 0);
			gbc_lblQuantity.gridx = 2;
			gbc_lblQuantity.gridy = 0;
			panel.add(lblQuantity, gbc_lblQuantity);
		}
		{
			JComboBox comboBox = new JComboBox();
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.ipadx = 50;
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 0;
			gbc_comboBox.gridy = 1;
			panel.add(comboBox, gbc_comboBox);
		}
		{
			JComboBox comboBox = new JComboBox();
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = 1;
			panel.add(comboBox, gbc_comboBox);
		}
		{
			JComboBox comboBox = new JComboBox();
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 0);
			gbc_comboBox.ipadx = 30;
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 2;
			gbc_comboBox.gridy = 1;
			panel.add(comboBox, gbc_comboBox);
		}
		{
			JLabel lblToppings = new JLabel("Toppings:");
			GridBagConstraints gbc_lblToppings = new GridBagConstraints();
			gbc_lblToppings.anchor = GridBagConstraints.WEST;
			gbc_lblToppings.insets = new Insets(0, 0, 5, 5);
			gbc_lblToppings.gridx = 0;
			gbc_lblToppings.gridy = 2;
			panel.add(lblToppings, gbc_lblToppings);
		}
		{
			JScrollPane scrollPane_1 = new JScrollPane();
			GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
			gbc_scrollPane_1.gridwidth = 3;
			gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
			gbc_scrollPane_1.gridx = 0;
			gbc_scrollPane_1.gridy = 3;
			panel.add(scrollPane_1, gbc_scrollPane_1);
			{
				JList list = new JList();
				scrollPane_1.setViewportView(list);
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
				GridBagConstraints gbc_btnAddToOrder = new GridBagConstraints();
				gbc_btnAddToOrder.ipadx = 50;
				gbc_btnAddToOrder.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnAddToOrder.insets = new Insets(0, 0, 0, 5);
				gbc_btnAddToOrder.gridx = 0;
				gbc_btnAddToOrder.gridy = 0;
				panel_2.add(btnAddToOrder, gbc_btnAddToOrder);
			}
			{
				JButton btnPay = new JButton("Pay Now");
				GridBagConstraints gbc_btnPay = new GridBagConstraints();
				gbc_btnPay.ipadx = 50;
				gbc_btnPay.gridwidth = 2;
				gbc_btnPay.insets = new Insets(0, 0, 0, 5);
				gbc_btnPay.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnPay.gridx = 1;
				gbc_btnPay.gridy = 0;
				panel_2.add(btnPay, gbc_btnPay);
			}
		}
		{
			panel_3 = new JPanel();
			springLayout.putConstraint(SpringLayout.WEST, panel, 5, SpringLayout.EAST, panel_3);
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
				JTextPane txtpnTotal = new JTextPane();
				txtpnTotal.setEditable(false);
				txtpnTotal.setFont(new Font("Tahoma", Font.PLAIN, 11));
				txtpnTotal.setText("$250.00");
				GridBagConstraints gbc_txtpnTotal = new GridBagConstraints();
				gbc_txtpnTotal.fill = GridBagConstraints.BOTH;
				gbc_txtpnTotal.gridx = 2;
				gbc_txtpnTotal.gridy = 0;
				panel_3.add(txtpnTotal, gbc_txtpnTotal);
			}
		}
		{
			JPanel panel_1 = new JPanel();
			springLayout.putConstraint(SpringLayout.EAST, panel_1, 250, SpringLayout.WEST, getContentPane());
			springLayout.putConstraint(SpringLayout.NORTH, panel_2, 5, SpringLayout.SOUTH, panel_1);
			springLayout.putConstraint(SpringLayout.NORTH, panel_3, 5, SpringLayout.SOUTH, panel_1);
			springLayout.putConstraint(SpringLayout.EAST, panel_3, 0, SpringLayout.EAST, panel_1);
			panel_1.setBorder(new TitledBorder(null, "Order Items:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			springLayout.putConstraint(SpringLayout.NORTH, panel_1, 10, SpringLayout.NORTH, getContentPane());
			springLayout.putConstraint(SpringLayout.WEST, panel_1, 10, SpringLayout.WEST, getContentPane());
			springLayout.putConstraint(SpringLayout.SOUTH, panel_1, -40, SpringLayout.SOUTH, getContentPane());
			getContentPane().add(panel_1);
			GridBagLayout gbl_panel_1 = new GridBagLayout();
			gbl_panel_1.columnWidths = new int[]{0, 0};
			gbl_panel_1.rowHeights = new int[]{0, 0};
			gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
			panel_1.setLayout(gbl_panel_1);
			{
				JScrollPane scrollPane = new JScrollPane();
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 0;
				gbc_scrollPane.gridy = 0;
				panel_1.add(scrollPane, gbc_scrollPane);
				
				table = new JTable();
				table.setFillsViewportHeight(true);
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(null, "Sides/Drinks", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			springLayout.putConstraint(SpringLayout.NORTH, panel_1, 5, SpringLayout.SOUTH, panel);
			springLayout.putConstraint(SpringLayout.WEST, panel_1, 0, SpringLayout.WEST, panel_2);
			springLayout.putConstraint(SpringLayout.SOUTH, panel_1, -5, SpringLayout.NORTH, panel_2);
			springLayout.putConstraint(SpringLayout.EAST, panel_1, 0, SpringLayout.EAST, panel_2);
			getContentPane().add(panel_1);
			GridBagLayout gbl_panel_1 = new GridBagLayout();
			gbl_panel_1.columnWidths = new int[]{0, 0, 0};
			gbl_panel_1.rowHeights = new int[]{0, 0, 0};
			gbl_panel_1.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
			gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			panel_1.setLayout(gbl_panel_1);
			{
				JLabel lblType_1 = new JLabel("Type");
				GridBagConstraints gbc_lblType_1 = new GridBagConstraints();
				gbc_lblType_1.anchor = GridBagConstraints.WEST;
				gbc_lblType_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblType_1.gridx = 0;
				gbc_lblType_1.gridy = 0;
				panel_1.add(lblType_1, gbc_lblType_1);
			}
			{
				JLabel lblQuantity_1 = new JLabel("Quantity");
				GridBagConstraints gbc_lblQuantity_1 = new GridBagConstraints();
				gbc_lblQuantity_1.ipadx = 30;
				gbc_lblQuantity_1.anchor = GridBagConstraints.WEST;
				gbc_lblQuantity_1.insets = new Insets(0, 0, 5, 0);
				gbc_lblQuantity_1.gridx = 1;
				gbc_lblQuantity_1.gridy = 0;
				panel_1.add(lblQuantity_1, gbc_lblQuantity_1);
			}
			{
				JComboBox comboBox = new JComboBox();
				GridBagConstraints gbc_comboBox = new GridBagConstraints();
				gbc_comboBox.insets = new Insets(0, 0, 0, 5);
				gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBox.gridx = 0;
				gbc_comboBox.gridy = 1;
				panel_1.add(comboBox, gbc_comboBox);
			}
			{
				JComboBox comboBox = new JComboBox();
				GridBagConstraints gbc_comboBox = new GridBagConstraints();
				gbc_comboBox.ipadx = 30;
				gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBox.gridx = 1;
				gbc_comboBox.gridy = 1;
				panel_1.add(comboBox, gbc_comboBox);
			}
		}
	}
}
