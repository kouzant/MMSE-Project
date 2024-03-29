package se.kth.carins.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import se.kth.carins.Business.ClaimFunc;
import se.kth.carins.Business.ClientFunc;
import se.kth.carins.Business.Communication;
import se.kth.carins.Business.CustomerFormFunc;
import se.kth.carins.Business.UsersFunc;
import se.kth.carins.Entities.Claim;
import se.kth.carins.Entities.Client;
import se.kth.carins.Entities.CustomerForm;
import se.kth.carins.Entities.Employee;
import java.awt.SystemColor;
import java.util.List;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ApplicationWindow {

	private JFrame frmCarInsurance;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JTextField txtCustomerName;
	private JPanel panelLogin;
	private JPanel panelCDB;
	private JPanel panelCDA;
	private JPanel panelFinance;
	private JPanel panelAfterCustomerForm;
	private JPanel panelCustomer;
	private JPanel panelAddClient;
	private JPanel panelGarage;
	private JPanel panelSearch;
	private JPanel panelPaymentHistory;
	private JTextField txtLicencePlate;
	private JTextField txtCustomerPhone;
	private JTextField txtCustomerEmail;
	private JLabel lblInvalidUsernameOr;
	private JLabel lblUserAdded;
	private JLabel lblGarage;
	private JTextField txtClientName;
	private JTextField txtClientEmail;
	private JTextField txtClientPhone;
	private JTextField txtClientLicencePlate;
	private ClientFunc clientfunc;
	private JButton btnClientAddSubmit;
	private JButton btnNewButton;
	private JButton btnRegisterClaim;
	private JButton btnShowRegisteredClaims;
	private JTable formTable; 	
	private JScrollPane scrollPane;	
	private JTable formSearchTable;
	private JScrollPane scrollSearchPane;	
	private JTable formFinanceTable; 	
	private JScrollPane scrollFinancePane;	
	private JTable formClaimTable;
	private JScrollPane scrollClaimPane;
	private JTable formPaymentTable;
	private JScrollPane scrollPaymentPane;
	private Employee loggedEmployee;
	private CustomerFormFunc formfunc;
	private ClaimFunc claimfunc;
	private JTextField txtGarageLicencePlate;
	private JTextField txtGarageCost;
	private JTextField txtSearchField;
	private JLabel lblSearchName;
	private JLabel lblSearchEmail; 
	private JLabel lblSearchPhone; 
	private JLabel lblSearchLicencePlate;
	private JLabel lblGarageInformed;
	private JTextField txtCarValue;


	/**  Launch the application. */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationWindow window = new ApplicationWindow();
					window.frmCarInsurance.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/** Create the application. */
	public ApplicationWindow() {
		initializeData();
		initialize();
	}

	// Insert Data
	public void initializeData() {
		formfunc = new CustomerFormFunc();
		CustomerForm formData = new CustomerForm("nikos", "accident description", "IEB-8898", "anestos@kth.se", "0787872313");
		CustomerForm formDataz = new CustomerForm("antonis", "accident Description", "IEK-5234", "antkou@kth.se", "0788637213");
		formfunc.addCustomerForm(formData);
		formfunc.addCustomerForm(formDataz);

		UsersFunc userfunc = new UsersFunc();
		char[] pwd = { '1', '2', '3', '4'};
		Employee employeeFinance = new Employee("finance", pwd, "finance");
		Employee employeeCDB = new Employee("employeeCDB", pwd, "CDB");
		Employee employeeCDA = new Employee("employeeCDA", pwd, "CDA");
		Employee employeeGarage = new Employee("garage", pwd, "garage");
		userfunc.addUser(employeeFinance);
		userfunc.addUser(employeeCDB);
		userfunc.addUser(employeeCDA);
		userfunc.addUser(employeeGarage);


		clientfunc = new ClientFunc();
		Client client = new Client("nikos","anestos@kth.se","0787872313", "IEB-8898", "10000");
		Client client1 = new Client("client1", "email1", "phone1", "licencePlate1", "10000");
		Client client2 = new Client("client2", "email2", "phone2", "licencePlate2", "10000");
		Client client3 = new Client("client3", "email3", "phone3", "licencePlate3", "8000");
		Client client4 = new Client("client4", "email4", "phone4", "licencePlate4", "9000");
		Client client5 = new Client("antonis", "antkou@kth.se", "0788637213", "IEK-5234", "11000");
		clientfunc.addClient(client);
		clientfunc.addClient(client1);
		clientfunc.addClient(client2);
		clientfunc.addClient(client3);
		clientfunc.addClient(client4);
		clientfunc.addClient(client5);


		claimfunc = new ClaimFunc();
		Claim claim = new Claim("nikos","accident description","IEB-8898","0787872313","anestos@kth.se",employeeCDA);
		Claim claim1 = new Claim("client1","accident description","licencePlate1","phone1","email1",employeeCDB);
		Claim claim2 = new Claim("client2","accident description","licencePlate2","phone2","email2",employeeCDA);
		Claim claim3 = new Claim("antonis","first accident description","IEK-5234","0788637213","antkou@kth.se",employeeCDA);
		Claim claim4 = new Claim("antonis","second accident description","IEK-5234","0788637213","antkou@kth.se",employeeCDB);
		claimfunc.addClaim(claim);
		claimfunc.addClaim(claim1);
		claimfunc.addClaim(claim2);
		claimfunc.addClaim(claim3);
		claimfunc.addClaim(claim4);
		claim.setCategory("simple");
		claim1.setCategory("simple");
		claim2.setCategory("simple");
		claim4.setCategory("simple");
		claim3.setCategory("complex");
		claim.setStatus("registered");
		claim1.setStatus("registered");
		claim2.setStatus("registered");
		claim4.setStatus("OK");
		claim4.setCost(1000f);
		claim3.setStatus("archived");
		claim3.setCost(500f);
		claim3.setPaid();
		client.addClaimToHistory(claim);
		client1.addClaimToHistory(claim1);
		client2.addClaimToHistory(claim2);
		client5.addClaimToHistory(claim3);
		client5.addClaimToHistory(claim4);
	}


	/**  Initialize the contents of the frame.  */
	private void initialize() {
		frmCarInsurance = new JFrame();
		frmCarInsurance.setTitle("Car Insurance");
		frmCarInsurance.setBounds(100, 100, 600, 400);
		frmCarInsurance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCarInsurance.getContentPane().setLayout(new CardLayout(0, 0));

		panelLogin = new JPanel();
		frmCarInsurance.getContentPane().add(panelLogin, "name_1413134037279670000");
		panelLogin.setLayout(null);

		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(180, 60, 135, 25);
		panelLogin.add(txtUsername);

		txtPassword = new JPasswordField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(180, 95, 135, 25);
		panelLogin.add(txtPassword);

		JButton btnLoginSubmit = new JButton("Submit");
		btnLoginSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText();
				char[] password = txtPassword.getPassword();
				UsersFunc userfunc = new UsersFunc();
				String authenticated = "0";
				authenticated = userfunc.getUser(username, password);

				if (authenticated == "CDB") {
					panelLogin.setVisible(false);
					panelCDB.setVisible(true);
					lblInvalidUsernameOr.setText("");
					loggedEmployee = userfunc.getEmployee(username, password);

				} else if (authenticated == "CDA") {
					panelLogin.setVisible(false);
					panelCDA.setVisible(true);
					lblInvalidUsernameOr.setText("");
					loggedEmployee = userfunc.getEmployee(username, password);
				} else if (authenticated == "finance") {
					panelLogin.setVisible(false);
					panelFinance.setVisible(true);
					lblInvalidUsernameOr.setText("");
					loggedEmployee = userfunc.getEmployee(username, password);
				} else if (authenticated == "garage") {
					panelLogin.setVisible(false);
					panelGarage.setVisible(true);
					lblInvalidUsernameOr.setText("");
					loggedEmployee = userfunc.getEmployee(username, password);
				} else {
					lblInvalidUsernameOr.setText("Invalid Username or Password. Please try again");
				}
			}
		});
		
		btnLoginSubmit.setBounds(180, 132, 135, 30);
		panelLogin.add(btnLoginSubmit);

		lblInvalidUsernameOr = new JLabel("");
		lblInvalidUsernameOr.setBounds(80, 173, 301, 16);
		panelLogin.add(lblInvalidUsernameOr);

		JLabel label = new JLabel("Username");
		label.setBounds(80, 65, 62, 16);
		panelLogin.add(label);

		JLabel label_1 = new JLabel("Password");
		label_1.setBounds(80, 100, 59, 16);
		panelLogin.add(label_1);

		JButton btnGoToCustomerForm = new JButton("Report an Accident");
		btnGoToCustomerForm.setBackground(SystemColor.scrollbar);
		btnGoToCustomerForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelLogin.setVisible(false);
				panelCustomer.setVisible(true);
				lblInvalidUsernameOr.setText("");

			}
		});
		
		btnGoToCustomerForm.setBounds(378, 44, 177, 60);
		panelLogin.add(btnGoToCustomerForm);

		panelCustomer = new JPanel();
		panelCustomer.setVisible(false);
		frmCarInsurance.getContentPane().add(panelCustomer, "name_1413135446784299000");
		panelCustomer.setLayout(null);

		txtCustomerName = new JTextField();
		txtCustomerName.setBounds(200, 30, 134, 28);
		panelCustomer.add(txtCustomerName);
		txtCustomerName.setColumns(10);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(137, 35, 36, 16);
		panelCustomer.add(lblName);

		txtLicencePlate = new JTextField();
		txtLicencePlate.setBounds(200, 60, 134, 28);
		panelCustomer.add(txtLicencePlate);
		txtLicencePlate.setColumns(10);

		JLabel lblLicencePlate = new JLabel("Licence Plate");
		lblLicencePlate.setBounds(92, 65, 81, 16);
		panelCustomer.add(lblLicencePlate);

		final JTextArea txtAccidentDescription = new JTextArea();
		txtAccidentDescription.setLineWrap(true);
		txtAccidentDescription.setRows(10);
		txtAccidentDescription.setColumns(10);
		txtAccidentDescription.setBounds(200, 160, 200, 75);
		panelCustomer.add(txtAccidentDescription);

		JButton btnAccidentSubmit = new JButton("Submit");
		btnAccidentSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formfunc = new CustomerFormFunc();
				String customerName = txtCustomerName.getText();
				String accidentDescription = txtAccidentDescription.getText();
				String licencePlate = txtLicencePlate.getText();
				String customerPhone = txtCustomerPhone.getText();
				String customerEmail = txtCustomerEmail.getText();
				CustomerForm formData = new CustomerForm(customerName, accidentDescription, licencePlate, customerEmail,customerPhone);
				formfunc.addCustomerForm(formData);

				panelCustomer.setVisible(false);
				panelAfterCustomerForm.setVisible(true);
				txtCustomerName.setText("");
				txtLicencePlate.setText("");
				txtCustomerEmail.setText("");
				txtCustomerPhone.setText("");
				txtAccidentDescription.setText("");
			}
		});
		
		btnAccidentSubmit.setBounds(149, 240, 117, 29);
		panelCustomer.add(btnAccidentSubmit);

		JLabel lblAccidentDescription = new JLabel("Accident Description");
		lblAccidentDescription.setBounds(40, 160, 135, 16);
		panelCustomer.add(lblAccidentDescription);

		JLabel lblReportAndAccident = new JLabel("Report an Accident");
		lblReportAndAccident.setBounds(148, 6, 134, 16);
		panelCustomer.add(lblReportAndAccident);

		txtCustomerPhone = new JTextField();
		txtCustomerPhone.setBounds(200, 90, 134, 28);
		panelCustomer.add(txtCustomerPhone);
		txtCustomerPhone.setColumns(10);

		JLabel lblTelephone = new JLabel("Telephone");
		lblTelephone.setBounds(110, 95, 68, 16);
		panelCustomer.add(lblTelephone);

		txtCustomerEmail = new JTextField();
		txtCustomerEmail.setBounds(200, 120, 134, 28);
		panelCustomer.add(txtCustomerEmail);
		txtCustomerEmail.setColumns(10);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(138, 125, 35, 16);
		panelCustomer.add(lblEmail);

		panelCDB = new JPanel();
		panelCDB.setVisible(false);
		frmCarInsurance.getContentPane().add(panelCDB, "name_1413135463172740000");
		panelCDB.setLayout(null);



		panelCDA = new JPanel();
		panelCDA.setVisible(false);
		frmCarInsurance.getContentPane().add(panelCDA, "name_1413135468390753000");
		panelCDA.setLayout(null);

		panelFinance = new JPanel();
		panelFinance.setVisible(false);
		frmCarInsurance.getContentPane().add(panelFinance, "name_1413135501609176000");
		panelFinance.setLayout(null);

		JButton btnAddClient = new JButton("Add Client");
		btnAddClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAddClient.setVisible(true);
				panelFinance.setVisible(false);
			}
		});
		
		btnAddClient.setBounds(457, 325, 120, 30);
		panelFinance.add(btnAddClient);

		panelAfterCustomerForm = new JPanel();
		panelAfterCustomerForm.setVisible(false);
		frmCarInsurance.getContentPane().add(panelAfterCustomerForm, "name_1413142865239629000");
		panelAfterCustomerForm.setLayout(null);

		JLabel lblNewLabel = new JLabel("Your form is submitted. We will contact you as soon as possible. ");
		lblNewLabel.setBounds(29, 74, 415, 33);
		panelAfterCustomerForm.add(lblNewLabel);

		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAfterCustomerForm.setVisible(false);
				panelLogin.setVisible(true);
			}
		});
		
		btnGoBack.setBounds(64, 140, 117, 29);
		panelAfterCustomerForm.add(btnGoBack);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		btnExit.setBounds(249, 140, 117, 29);
		panelAfterCustomerForm.add(btnExit);

		panelAddClient = new JPanel();
		frmCarInsurance.getContentPane().add(panelAddClient, "name_1413205905273841000");
		panelAddClient.setLayout(null);

		btnClientAddSubmit = new JButton("Submit");
		btnClientAddSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String clientName = txtClientName.getText();
				String clientEmail = txtClientEmail.getText();
				String clientPhone = txtClientPhone.getText();
				String clientLicencePlate = txtClientLicencePlate.getText();
				String carValue = txtCarValue.getText();
				Client client = new Client(clientName,clientEmail,clientPhone, clientLicencePlate, carValue);
				clientfunc.addClient(client);
				txtClientName.setText("");
				txtClientEmail.setText("");
				txtClientPhone.setText("");
				txtClientLicencePlate.setText("");
				txtCarValue.setText("");
				txtClientName.setEnabled(false);
				txtClientEmail.setEnabled(false);
				txtClientPhone.setEnabled(false);
				txtClientLicencePlate.setEnabled(false);
				txtCarValue.setEnabled(false);
				btnNewButton.setEnabled(true);
				lblUserAdded.setText("User Added to the system");
				btnClientAddSubmit.setEnabled(false);

			}
		});
		
		btnClientAddSubmit.setBounds(29, 295, 117, 30);
		panelAddClient.add(btnClientAddSubmit);

		JButton btnClientAddBack = new JButton("Back");
		btnClientAddBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblUserAdded.setText("");
				panelAddClient.setVisible(false);
				panelFinance.setVisible(true);
			}
		});
		
		btnClientAddBack.setBounds(353, 295, 117, 30);
		panelAddClient.add(btnClientAddBack);

		JLabel lblAddNewClient = new JLabel("Add New Client");
		lblAddNewClient.setBounds(223, 13, 104, 16);
		panelAddClient.add(lblAddNewClient);

		txtClientName = new JTextField();
		txtClientName.setBounds(193, 50, 134, 28);
		panelAddClient.add(txtClientName);
		txtClientName.setColumns(10);

		txtClientEmail = new JTextField();
		txtClientEmail.setBounds(193, 89, 134, 28);
		panelAddClient.add(txtClientEmail);
		txtClientEmail.setColumns(10);

		txtClientPhone = new JTextField();
		txtClientPhone.setBounds(193, 128, 134, 28);
		panelAddClient.add(txtClientPhone);
		txtClientPhone.setColumns(10);

		txtClientLicencePlate = new JTextField();
		txtClientLicencePlate.setBounds(193, 167, 134, 28);
		panelAddClient.add(txtClientLicencePlate);
		txtClientLicencePlate.setColumns(10);

		JLabel lblName_1 = new JLabel("Name");
		lblName_1.setBounds(98, 56, 61, 16);
		panelAddClient.add(lblName_1);

		JLabel lblEmail_1 = new JLabel("Email");
		lblEmail_1.setBounds(98, 95, 61, 16);
		panelAddClient.add(lblEmail_1);

		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(98, 134, 61, 16);
		panelAddClient.add(lblPhone);

		JLabel lblLicencePlate_1 = new JLabel("Licence Plate");
		lblLicencePlate_1.setBounds(98, 173, 70, 16);
		panelAddClient.add(lblLicencePlate_1);

		lblUserAdded = new JLabel("");
		lblUserAdded.setBounds(104, 268, 272, 16);
		panelAddClient.add(lblUserAdded);

		btnNewButton = new JButton("Add New");
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblUserAdded.setText("");
				btnClientAddSubmit.setEnabled(true);
				txtClientName.setEnabled(true);
				txtClientEmail.setEnabled(true);
				txtClientPhone.setEnabled(true);
				txtClientLicencePlate.setEnabled(true);	
				txtCarValue.setEnabled(true);
				btnNewButton.setEnabled(false);
			}
		});
		
		btnNewButton.setBounds(193, 295, 117, 30);
		panelAddClient.add(btnNewButton);

		JButton btnLogoutAddClient = new JButton("Logout");
		btnLogoutAddClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAddClient.setVisible(false);
				panelLogin.setVisible(true);
				loggedEmployee = null;
			}
		});
		
		btnLogoutAddClient.setBounds(491, 6, 85, 30);
		panelAddClient.add(btnLogoutAddClient);

		JButton btnLogoutFinance = new JButton("Logout");
		btnLogoutFinance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelFinance.setVisible(false);
				panelLogin.setVisible(true);
				loggedEmployee = null;
			}
		});
		
		btnLogoutFinance.setBounds(491, 6, 85, 30);
		panelFinance.add(btnLogoutFinance);

		JButton btnFinancePay = new JButton("Pay");
		btnFinancePay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int topay = formFinanceTable.getSelectedRow();
				Claim claimToPay = claimfunc.filterClaims("OK").get(topay);
				// To implement Online Payment
				claimToPay.setPaid();
				claimToPay.setStatus("archived");

				panelFinance.setVisible(false);
				panelFinance.setVisible(true);
			}
		});
		
		btnFinancePay.setBounds(10, 325, 90, 30);
		panelFinance.add(btnFinancePay);

		JButton btnViewPaymentHistory = new JButton("View Payment history");
		btnViewPaymentHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelFinance.setVisible(false);
				panelPaymentHistory.setVisible(true);
			}
		});
		
		btnViewPaymentHistory.setBounds(266, 325, 185, 30);
		panelFinance.add(btnViewPaymentHistory);

		JButton btnSearchFinance = new JButton("Search");
		btnSearchFinance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelFinance.setVisible(false);
				panelSearch.setVisible(true);
			}
		});
		
		btnSearchFinance.setBounds(491, 40, 85, 30);
		panelFinance.add(btnSearchFinance);

		JButton btnSearchCDB = new JButton("Search");
		btnSearchCDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCDB.setVisible(false);
				panelSearch.setVisible(true);
			}
		});
		
		btnSearchCDB.setBounds(491, 40, 85, 30);
		panelCDB.add(btnSearchCDB);

		JButton btnSearchCDA= new JButton("Search");
		btnSearchCDA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCDA.setVisible(false);
				panelSearch.setVisible(true);
			}
		});
		
		btnSearchCDA.setBounds(491, 40, 85, 30);
		panelCDA.add(btnSearchCDA);

		JButton btnLogoutCDA = new JButton("Logout");
		btnLogoutCDA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCDA.setVisible(false);
				panelLogin.setVisible(true);
				loggedEmployee = null;
			}
		});

		JButton btnSearchAddClient = new JButton("Search");
		btnSearchAddClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAddClient.setVisible(false);
				panelSearch.setVisible(true);
			}
		});
		
		btnSearchAddClient.setBounds(491, 40, 85, 30);
		panelAddClient.add(btnSearchAddClient);
		
		txtCarValue = new JTextField();
		txtCarValue.setBounds(193, 206, 134, 28);
		panelAddClient.add(txtCarValue);
		txtCarValue.setColumns(10);
		
		JLabel lblCarValue = new JLabel("Car Value");
		lblCarValue.setBounds(100, 217, 59, 14);
		panelAddClient.add(lblCarValue);

		btnLogoutCDA.setBounds(491, 6, 85, 30);
		panelCDA.add(btnLogoutCDA);

		JButton btnOrderPayment = new JButton("Order Payment");
		btnOrderPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// OK
				int toregister = formClaimTable.getSelectedRow();
				Claim claimToRegister = claimfunc.filterClaims("registered").get(toregister);
				claimToRegister.setStatus("OK");

				panelCDA.setVisible(false);
				panelCDA.setVisible(true);

			}
		});
		
		btnOrderPayment.setBounds(10, 325, 136, 29);
		panelCDA.add(btnOrderPayment);

		JButton btnRejectPayment = new JButton("Reject Payment");
		btnRejectPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// = NOK
				int toregister = formClaimTable.getSelectedRow();
				Claim claimToRegister = claimfunc.filterClaims("registered").get(toregister);
				claimToRegister.setStatus("archived");

				panelCDA.setVisible(false);
				panelCDA.setVisible(true);
			}
		});
		
		btnRejectPayment.setBounds(150, 325, 144, 29);
		panelCDA.add(btnRejectPayment);
		
		JButton btnCheckNewAccidents = new JButton("Check New Accidents");
		btnCheckNewAccidents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCDA.setVisible(false);
				panelCDB.setVisible(true);
			}
		});
		btnCheckNewAccidents.setBounds(400, 325, 160, 30);
		panelCDA.add(btnCheckNewAccidents);

		JButton btnLogoutCDB = new JButton("Logout");
		btnLogoutCDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCDB.setVisible(false);
				panelLogin.setVisible(true);
				loggedEmployee = null;
			}
		});
		
		btnLogoutCDB.setBounds(491, 6, 85, 30);
		panelCDB.add(btnLogoutCDB);

		btnRegisterClaim = new JButton("Register Claim");
		btnRegisterClaim.setEnabled(false);
		btnRegisterClaim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int toregister = formTable.getSelectedRow();
				CustomerForm customerform = formfunc.getCustomerForms().get(toregister);
				
				Client client0 = clientfunc.getClient(customerform.getCustomerName(), customerform.getLicensePlate());
				
				if (clientfunc.hasRegisteredClaim(customerform)) {			
					lblGarageInformed.setText("This claim is already register. Delete it.");
					btnRegisterClaim.setEnabled(false);
				} else {
				
				Claim claim = new Claim(customerform.getCustomerName(), customerform.getAccidentDesc(), 
						customerform.getLicensePlate(), customerform.getCustomerPhone(), customerform.getCustomerEmail(), loggedEmployee);

				if (clientfunc.hasHistory(client0) > 0 || Integer.parseInt(client0.getCarValue()) > 15000)
					claim.setCategory("complex");
				else
					claim.setCategory("simple");

				claimfunc.addClaim(claim);
				client0.addClaimToHistory(claim);
				formfunc.getCustomerForms().remove(toregister);
				
				Communication com = new Communication();
				String reply = com.informGarage(customerform.getLicensePlate());
				
				panelCDB.setVisible(false);
				panelCDB.setVisible(true);
				lblGarageInformed.setText(reply);
				}
			}
		});
		
		btnRegisterClaim.setBounds(10, 325, 117, 30);
		panelCDB.add(btnRegisterClaim);

		JButton btnDeleteAccident = new JButton("Delete Accident");
		btnDeleteAccident.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int todelete = formTable.getSelectedRow();
				formfunc.getCustomerForms().remove(todelete);
				panelCDB.setVisible(false);
				panelCDB.setVisible(true);
			}
		});
		
		btnDeleteAccident.setBounds(140, 325, 130, 30);
		panelCDB.add(btnDeleteAccident);
		
		lblGarageInformed = new JLabel("");
		lblGarageInformed.setBounds(100, 300, 360, 15);
		panelCDB.add(lblGarageInformed);
		
		btnShowRegisteredClaims = new JButton("Go back ");
		btnShowRegisteredClaims.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCDB.setVisible(false);
				panelCDA.setVisible(true);
				
			}
		});
		btnShowRegisteredClaims.setBounds(400, 325, 160, 30);
		panelCDB.add(btnShowRegisteredClaims);
		btnShowRegisteredClaims.setVisible(false);

		panelGarage = new JPanel();
		frmCarInsurance.getContentPane().add(panelGarage, "name_1413220264189332000");
		panelGarage.setLayout(null);

		lblGarage = new JLabel("Welcome");
		lblGarage.setBounds(220, 25, 150, 15);
		panelGarage.add(lblGarage);

		JButton btnGarageSubmit = new JButton("Submit");
		btnGarageSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String garageLicencePlate = txtGarageLicencePlate.getText();
				String garageCost = txtGarageCost.getText();
				Claim claimToAddCost = claimfunc.searchClaim(garageLicencePlate);
				if (claimToAddCost == null) {
					lblGarage.setText("Invalid Licence Plate");
					txtGarageLicencePlate.setText("");
				} else {
					if (!garageCost.equals("")) {
						claimToAddCost.setCost(new Float(garageCost));
						txtGarageCost.setText("");
						txtGarageLicencePlate.setText("");
						panelGarage.setVisible(false);
						panelLogin.setVisible(true);
					}
				}
			}
		});
		
		btnGarageSubmit.setBounds(221, 159, 115, 30);
		panelGarage.add(btnGarageSubmit);

		txtGarageLicencePlate = new JTextField();
		txtGarageLicencePlate.setBounds(221, 79, 134, 28);
		panelGarage.add(txtGarageLicencePlate);
		txtGarageLicencePlate.setColumns(10);

		txtGarageCost = new JTextField();
		txtGarageCost.setBounds(221, 119, 134, 28);
		panelGarage.add(txtGarageCost);
		txtGarageCost.setColumns(10);

		JLabel lblLicencePlate_2 = new JLabel("Licence Plate");
		lblLicencePlate_2.setBounds(102, 85, 93, 16);
		panelGarage.add(lblLicencePlate_2);

		JLabel lblCost = new JLabel("Cost");
		lblCost.setBounds(102, 125, 61, 16);
		panelGarage.add(lblCost);

		panelSearch = new JPanel();
		panelSearch.setVisible(false);
		frmCarInsurance.getContentPane().add(panelSearch, "name_610164216064514");
		panelSearch.setLayout(null);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSearch.setVisible(false);
				panelLogin.setVisible(true);
				loggedEmployee = null;
			}
		});
		
		btnLogout.setBounds(491, 6, 85, 30);
		panelSearch.add(btnLogout);

		txtSearchField = new JTextField();
		txtSearchField.setBounds(30, 30, 300, 30);
		panelSearch.add(txtSearchField);
		txtSearchField.setColumns(10);

		JButton btnSearchGo = new JButton("Go");
		btnSearchGo.setBorder(null);
		btnSearchGo.setBorderPainted(false);
		btnSearchGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblSearchEmail.setText("");
				lblSearchName.setText("");
				lblSearchLicencePlate.setText("");
				lblSearchPhone.setText("");	

				if (formSearchTable != null) {
					panelSearch.remove(formSearchTable);
					panelSearch.remove(scrollSearchPane);
					panelSearch.repaint();					
				}

				String query = txtSearchField.getText();
				Client client = clientfunc.searchClient(query);

				if (client == null) {
					lblSearchEmail.setText("Client Name, Email, Phone or Licence Plate" );					
					lblSearchName.setText("Nothing found, please search using:");
				} else {
					// construct table with history
					lblSearchEmail.setText("Email: "+ client.getEmail());
					lblSearchName.setText("Name: "+ client.getName());
					lblSearchLicencePlate.setText("Licence Plate: "+ client.getLicensePlate());
					lblSearchPhone.setText("Phone: "+ client.getPhone());

					String[] columnNames = {
							"Accident Description",
							"Category",
							"Cost",
							"Status",
							"Paid",
							"Registration Date",
					"Employee"};

					int length = client.getClaimHistory().size();
					Object[][] dataz = new Object[length][7];

					List<Claim> clientClaims = client.getClaimHistory();
					for (int i = 0; i < length; i++) {
						Claim lala = clientClaims.get(i);
						dataz[i][0] = lala.getAccidentDesc();
						dataz[i][1] = lala.getCategory();
						dataz[i][2] = lala.getCost();
						dataz[i][3] = lala.getStatus();
						dataz[i][4] = lala.getPaid();
						dataz[i][5] = lala.getRegistrationDate();
						dataz[i][6] = lala.getEmployee().getUsername();
					}

					formSearchTable = new JTable(dataz, columnNames);
					formSearchTable.setBounds(49, 232, 368, -165);
					panelSearch.add(formSearchTable);
					formSearchTable.setPreferredScrollableViewportSize(new Dimension(500, 100));
					formSearchTable.setFillsViewportHeight(true);
					formSearchTable.setVisible(true);
					formSearchTable.repaint();

					scrollSearchPane = new JScrollPane(formSearchTable);
					scrollSearchPane.setLocation(30, 120);
					scrollSearchPane.setSize(500, 220);
					scrollSearchPane.setOpaque(true);
					panelSearch.add(scrollSearchPane);
				};
			}
		});
		
		btnSearchGo.setBounds(330, 28, 50, 30);
		panelSearch.add(btnSearchGo);

		lblSearchName = new JLabel("");
		lblSearchName.setBounds(30, 70, 250, 15);
		panelSearch.add(lblSearchName);

		lblSearchEmail = new JLabel("");
		lblSearchEmail.setBounds(30, 90, 250, 15);
		panelSearch.add(lblSearchEmail);

		lblSearchPhone = new JLabel("");
		lblSearchPhone.setBounds(300, 70, 200, 15);
		panelSearch.add(lblSearchPhone);

		lblSearchLicencePlate = new JLabel("");
		lblSearchLicencePlate.setBounds(300, 90, 200, 15);
		panelSearch.add(lblSearchLicencePlate);

		JButton btnSearchBack = new JButton("Back");
		btnSearchBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblSearchName.setText("");
				lblSearchEmail.setText("");
				lblSearchPhone.setText("");
				lblSearchLicencePlate.setText("");
				if (formSearchTable != null) {
					panelSearch.remove(formSearchTable);
					panelSearch.remove(scrollSearchPane);
					panelSearch.repaint();
				}
				txtSearchField.setText("");
				String authenticated = loggedEmployee.getAcl();;

				if (authenticated == "CDB") {
					panelCDB.setVisible(true);
				} else if (authenticated == "CDA") {
					panelCDA.setVisible(true);
				} else if (authenticated == "finance") {
					panelFinance.setVisible(true);
				} 				
				panelSearch.setVisible(false);

			}
		});
		
		btnSearchBack.setBounds(491, 40, 85, 30);
		panelSearch.add(btnSearchBack);
		
		panelPaymentHistory = new JPanel();
		frmCarInsurance.getContentPane().add(panelPaymentHistory, "name_617643924390979");
		panelPaymentHistory.setLayout(null);
		
		JButton btnLogoutPaymentHistory = new JButton("Logout");
		btnLogoutPaymentHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPaymentHistory.setVisible(false);
				panelLogin.setVisible(true);
				loggedEmployee = null;
			}
		});
		
		btnLogoutPaymentHistory.setBounds(491, 6, 85, 30);
		panelPaymentHistory.add(btnLogoutPaymentHistory);
		
		JButton btnPaymentHistoryBack = new JButton("Back");
		btnPaymentHistoryBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPaymentHistory.setVisible(false);
				panelFinance.setVisible(true);
			}
		});
		
		btnPaymentHistoryBack.setBounds(491, 40, 85, 30);
		panelPaymentHistory.add(btnPaymentHistoryBack);


		// CDB Table Construct
		panelCDB.addComponentListener(new ComponentAdapter() {
			public void componentHidden(ComponentEvent evt) {
				panelCDB.remove(formTable);
				panelCDB.remove(scrollPane);
			}
			public void componentShown(ComponentEvent evt) {
				
				
				if (loggedEmployee.getAcl().equals("CDA")) {
					btnShowRegisteredClaims.setVisible(true);
				}
				
				
				lblGarageInformed.setText("");
				String[] columnNames = {"Name",
						"Email",
						"Phone",
						"Licence Plate",
						"Accident Description",
				"Client"};

				int length = formfunc.getCustomerForms().size();
				final Object[][] dataz = new Object[length][6];
				for (int i = 0; i < length; i++) {
					CustomerForm lala = formfunc.getCustomerForms().get(i);
					dataz[i][0] = lala.getCustomerName();
					dataz[i][1] = lala.getCustomerEmail();
					dataz[i][2] = lala.getCustomerPhone();
					dataz[i][3] = lala.getLicensePlate();
					dataz[i][4] = lala.getAccidentDesc();
					dataz[i][5] = clientfunc.isClient(lala);
				}


				formTable = new JTable(dataz, columnNames);
				formTable.setBounds(49, 232, 368, -165);
				panelCDB.add(formTable);
				formTable.setPreferredScrollableViewportSize(new Dimension(500, 100));
				formTable.setFillsViewportHeight(true);
				formTable.setVisible(true);

				scrollPane = new JScrollPane(formTable);
				scrollPane.setLocation(10, 10);
				scrollPane.setSize(450, 300);
				scrollPane.setOpaque(true);
				panelCDB.add(scrollPane);
				
				
				formTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			        public void valueChanged(ListSelectionEvent event) {
			        	lblGarageInformed.setText("");
			        	Integer row = formTable.getSelectedRow();
			        	Boolean what = (Boolean) dataz[row][5];
			        	if (what) {
			        		btnRegisterClaim.setEnabled(true);
			        	} else {
			        		btnRegisterClaim.setEnabled(false);
			        	}
			        }
			    });
			}
		});
		
		// CDA Table Construct
		panelCDA.addComponentListener(new ComponentAdapter() {
			public void componentHidden(ComponentEvent evt) {
				panelCDA.remove(formClaimTable);
				panelCDA.remove(scrollClaimPane);
			}
			public void componentShown(ComponentEvent evt) {

				String[] columnNames = {"Name",
						"Email",
						"Phone",
						"Licence Plate",
						"Accident Description",
						"Category",
				"Cost"};

				int length = claimfunc.filterClaims("registered").size();
				Object[][] dataz = new Object[length][7];
				for (int i = 0; i < length; i++) {
					Claim lala = claimfunc.filterClaims("registered").get(i);
					dataz[i][0] = lala.getCustomerName();
					dataz[i][1] = lala.getCustomerEmail();
					dataz[i][2] = lala.getCustomerPhone();
					dataz[i][3] = lala.getLicensePlate();
					dataz[i][4] = lala.getAccidentDesc();
					dataz[i][5] = lala.getCategory();
					dataz[i][6] = lala.getCost();
				}

				formClaimTable = new JTable(dataz, columnNames);
				formClaimTable.setBounds(49, 232, 368, -165);
				panelCDA.add(formClaimTable);
				formClaimTable.setPreferredScrollableViewportSize(new Dimension(500, 100));
				formClaimTable.setFillsViewportHeight(true);
				formClaimTable.setVisible(true);

				scrollClaimPane = new JScrollPane(formClaimTable);
				scrollClaimPane.setLocation(10, 10);
				scrollClaimPane.setSize(450, 300);
				scrollClaimPane.setOpaque(true);
				panelCDA.add(scrollClaimPane);
			}
		});

		// Finance Table Construct
		panelFinance.addComponentListener(new ComponentAdapter() {
			public void componentHidden(ComponentEvent evt) {
				panelFinance.remove(formFinanceTable);
				panelFinance.remove(scrollFinancePane);
			}
			public void componentShown(ComponentEvent evt) {

				String[] columnNames = {"Name",
						"Email",
						"Phone",
						"Licence Plate",
						"Accident Description",
						"Category",
				"Cost"};

				int length = claimfunc.filterClaims("OK").size();
				Object[][] dataz = new Object[length][7];
				for (int i = 0; i < length; i++) {
					Claim lala = claimfunc.filterClaims("OK").get(i);
					dataz[i][0] = lala.getCustomerName();
					dataz[i][1] = lala.getCustomerEmail();
					dataz[i][2] = lala.getCustomerPhone();
					dataz[i][3] = lala.getLicensePlate();
					dataz[i][4] = lala.getAccidentDesc();
					dataz[i][5] = lala.getCategory();
					dataz[i][6] = lala.getCost();
				}

				formFinanceTable = new JTable(dataz, columnNames);
				formFinanceTable.setBounds(49, 232, 368, -165);
				panelFinance.add(formFinanceTable);
				formFinanceTable.setPreferredScrollableViewportSize(new Dimension(500, 100));
				formFinanceTable.setFillsViewportHeight(true);
				formFinanceTable.setVisible(true);

				scrollFinancePane = new JScrollPane(formFinanceTable);
				scrollFinancePane.setLocation(10, 10);
				scrollFinancePane.setSize(450, 300);
				scrollFinancePane.setOpaque(true);
				panelFinance.add(scrollFinancePane);
			}
		});

		panelPaymentHistory.addComponentListener(new ComponentAdapter() {
			public void componentHidden(ComponentEvent evt) {
				panelPaymentHistory.remove(formPaymentTable);
				panelPaymentHistory.remove(scrollPaymentPane);
			}
			public void componentShown(ComponentEvent evt) {

				String[] columnNames = {"Name",
						"Email",
						"Licence Plate",
						"Cost"};

				int length = claimfunc.filterClaims().size();
				Object[][] dataz = new Object[length][4];
				for (int i = 0; i < length; i++) {
					Claim lala = claimfunc.filterClaims().get(i);
					dataz[i][0] = lala.getCustomerName();
					dataz[i][1] = lala.getCustomerEmail();
					dataz[i][2] = lala.getLicensePlate();
					dataz[i][3] = lala.getCost();
				}

				formPaymentTable = new JTable(dataz, columnNames);
				formPaymentTable.setBounds(49, 232, 368, -165);
				panelPaymentHistory.add(formPaymentTable);
				formPaymentTable.setPreferredScrollableViewportSize(new Dimension(500, 100));
				formPaymentTable.setFillsViewportHeight(true);
				formPaymentTable.setVisible(true);

				scrollPaymentPane = new JScrollPane(formPaymentTable);
				scrollPaymentPane.setLocation(10, 10);
				scrollPaymentPane.setSize(450, 300);
				scrollPaymentPane.setOpaque(true);
				panelPaymentHistory.add(scrollPaymentPane);
			}
		});
	}
}
