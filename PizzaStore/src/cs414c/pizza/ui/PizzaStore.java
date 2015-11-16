package cs414c.pizza.ui;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import cs414c.pizza.controller.LoginController;
import cs414c.pizza.controller.LoginControllerInterface;
import cs414c.pizza.controller.MenuController;
import cs414c.pizza.controller.MenuControllerInterface;
import cs414c.pizza.controller.OrderController;
import cs414c.pizza.controller.OrderControllerInterface;
import cs414c.pizza.controller.PaymentController;
import cs414c.pizza.controller.PaymentControllerInterface;
import cs414c.pizza.dao.LoginDAO;
import cs414c.pizza.dao.MenuDAO;
import cs414c.pizza.dao.RewardsDAO;

public class PizzaStore {

	private JFrame frmPizzaStoreLauncher;
	
	//Controller objects
	private MenuControllerInterface menuController;
	private LoginControllerInterface loginController;
	private OrderControllerInterface orderController;
	private PaymentControllerInterface paymentController;
	private MenuDAO menuDAO;
	private LoginDAO loginDAO;
	private RewardsDAO rewardsDAO;
	private JTextArea textAreaLog;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
            PizzaStore store = new PizzaStore();
            MenuControllerInterface menuStub = (MenuControllerInterface) UnicastRemoteObject.exportObject(store.menuController, 0);
            LoginControllerInterface loginStub = (LoginControllerInterface) UnicastRemoteObject.exportObject(store.loginController, 0);
            OrderControllerInterface orderStub = (OrderControllerInterface) UnicastRemoteObject.exportObject(store.orderController, 0);
            PaymentControllerInterface paymentStub = (PaymentControllerInterface) UnicastRemoteObject.exportObject(store.paymentController, 0);

            LocateRegistry.createRegistry(1099);
            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("MenuController", menuStub);
            registry.bind("LoginController", loginStub);
            registry.bind("OrderController", orderStub);
            registry.bind("PaymentController", paymentStub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(
				            UIManager.getSystemLookAndFeelClassName());
					PizzaStore window = new PizzaStore();
					window.frmPizzaStoreLauncher.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PizzaStore() {
		menuDAO = new MenuDAO();
		loginDAO = new LoginDAO();
		rewardsDAO = new RewardsDAO();
		
		loginController = new LoginController(loginDAO, rewardsDAO);
		menuController = new MenuController(menuDAO);
		orderController = new OrderController(menuDAO);
		paymentController = new PaymentController(rewardsDAO);
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPizzaStoreLauncher = new JFrame();
		frmPizzaStoreLauncher.setTitle("Pizza Store Server");
		frmPizzaStoreLauncher.setBounds(100, 100, 460, 320);
		frmPizzaStoreLauncher.setMinimumSize(new Dimension(260,250));
		frmPizzaStoreLauncher.setIconImage(Toolkit.getDefaultToolkit().getImage(PizzaStore.class.getResource("/cs414c/pizza/ui/Pizza-icon.png")));
		frmPizzaStoreLauncher.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frmPizzaStoreLauncher.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        exitDialog();
		    }
		});
		SpringLayout springLayout = new SpringLayout();
		frmPizzaStoreLauncher.getContentPane().setLayout(springLayout);
		
		scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.NORTH, frmPizzaStoreLauncher.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, frmPizzaStoreLauncher.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -10, SpringLayout.SOUTH, frmPizzaStoreLauncher.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, frmPizzaStoreLauncher.getContentPane());
		frmPizzaStoreLauncher.getContentPane().add(scrollPane);
		
		textAreaLog = new JTextArea();
		textAreaLog.setEditable(false);
		scrollPane.setViewportView(textAreaLog);
		textAreaLog.setText("Starting server...");
		
/*		JLabel lblRole = new JLabel("Select System Role");
		lblRole.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRole.setHorizontalAlignment(SwingConstants.CENTER);
		springLayout.putConstraint(SpringLayout.NORTH, lblRole, 10, SpringLayout.NORTH, frmPizzaStoreLauncher.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblRole, 10, SpringLayout.WEST, frmPizzaStoreLauncher.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblRole, -10, SpringLayout.EAST, frmPizzaStoreLauncher.getContentPane());
		frmPizzaStoreLauncher.getContentPane().add(lblRole);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login mLogin = new CashierLogin(loginController, menuController, orderController, paymentController);
				//mLogin.setCaller("Cashier");
				mLogin.setVisible(true);
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnRegister, 5, SpringLayout.SOUTH, lblRole);
		springLayout.putConstraint(SpringLayout.WEST, btnRegister, 10, SpringLayout.WEST, frmPizzaStoreLauncher.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnRegister, -10, SpringLayout.EAST, frmPizzaStoreLauncher.getContentPane());
		frmPizzaStoreLauncher.getContentPane().add(btnRegister);
		
		JButton btnKiosk = new JButton("Kiosk");
		btnKiosk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BeginOrderKiosk kiosk = new BeginOrderKiosk(menuController, orderController, paymentController);
				kiosk.setVisible(true);
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnKiosk, 5, SpringLayout.SOUTH, btnRegister);
		springLayout.putConstraint(SpringLayout.WEST, btnKiosk, 10, SpringLayout.WEST, frmPizzaStoreLauncher.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnKiosk, -10, SpringLayout.EAST, frmPizzaStoreLauncher.getContentPane());
		frmPizzaStoreLauncher.getContentPane().add(btnKiosk);
		
		JButton btnChef = new JButton("Chef");
		btnChef.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChefWindow chef = new ChefWindow(orderController);
				chef.setVisible(true);
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnChef, 5, SpringLayout.SOUTH, btnKiosk);
		springLayout.putConstraint(SpringLayout.WEST, btnChef, 10, SpringLayout.WEST, frmPizzaStoreLauncher.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnChef, -10, SpringLayout.EAST, frmPizzaStoreLauncher.getContentPane());
		frmPizzaStoreLauncher.getContentPane().add(btnChef);
		
		JButton btnManager = new JButton("Manager");
		btnManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login mLogin = new ManagerLogin(loginController, menuController);
				//mLogin.setCaller("Manager");
				mLogin.setVisible(true);
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnManager, 5, SpringLayout.SOUTH, btnChef);
		springLayout.putConstraint(SpringLayout.WEST, btnManager, 10, SpringLayout.WEST, frmPizzaStoreLauncher.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnManager, -10, SpringLayout.EAST, frmPizzaStoreLauncher.getContentPane());
		frmPizzaStoreLauncher.getContentPane().add(btnManager);
		*/
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		frmPizzaStoreLauncher.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmResetWindow = new JMenuItem("Reset Window");
		mntmResetWindow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmPizzaStoreLauncher.setSize(260, 250);
			}
		});
		mntmResetWindow.setIcon(new ImageIcon(PizzaStore.class.getResource("/javax/swing/plaf/metal/icons/ocean/iconify.gif")));
		mnFile.add(mntmResetWindow);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exitDialog();
			}
		});
		mnFile.add(mntmExit);
	}
	
	private void exitDialog(){
		int n = JOptionPane.showConfirmDialog(
				frmPizzaStoreLauncher,
			    "Are you sure you want to exit?\n"
			    + "Exiting will close all windows!",
			    "Are you sure?",
			    JOptionPane.YES_NO_OPTION);
		if(n == 0){
			System.exit(0);
		}
	}
}
