package userinterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import dao.ServerInterface;
import flight.Flight;
import flight.Flights;
import reserveTravel.ReserveTravel;
import searchParameter.SearchParameter;
import searchTravel.SearchTravel;
import time.TimeConversion;
import travel.SortTravelByOrder;
import travel.Travel;
import travel.Travels;
import utils.Saps;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.security.auth.login.Configuration;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import airplane.Airplanes;
import airport.Airports;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.Button;
import javax.swing.ScrollPaneConstants;

/**This class provides a user interface to customer so that she can use this system
 * @author Houjue Wang
 * @author xujam
 * @author Sachin Suvarna
 */
public class UserInterface {
	private Airports airports;
	private Airplanes airplanes;
	private JFrame frame;
	private JTextField departureAirportCodeText;
	private JTextField departureDateText;


	private JTable firstTravelsTable;
	private JTable secondTravelsTable;
	private JScrollPane scrollPane0;
	private JScrollPane scrollPane1;
	private JTextField arrivalAirportCodeText;
	private JTextField returnDateText;
	
	private JRadioButton isRoundTripRadioButton;
	private JRadioButton isOneWayRadioButton;
	private ButtonGroup roundTripOrOneWayButtonGroup;
	
	private boolean isRoundTrip = true;
	
	private JRadioButton isFirstClassRadioButton;
	private JRadioButton isCoachRadioButton;
	private ButtonGroup firstClassOrCoachButtonGroup;
	
	private boolean isFirstClass = true;
	
	private SearchParameter searchParameter;
	private Travels firstTravels;
	private Travels secondTravels;
	private JLabel firstTravelsLabel;
	private JLabel secondTravelsLabel;
	
	
	/**
	 * Do some initialization for the system.
	 */
	public void init(){
		airports = ServerInterface.INSTANCE.getAirports(Saps.TEAM_NAME);
		airplanes = ServerInterface.INSTANCE.getAirplanes(Saps.TEAM_NAME);
		searchParameter = new SearchParameter();
		firstTravels = new Travels();
		secondTravels = new Travels();
	}
	
	
	/**Tha main function of the system
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = new UserInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserInterface() {
		init();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 773, 758);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		departureAirportCodeText = new JTextField();
		departureAirportCodeText.setBounds(270, 10, 180, 24);
		frame.getContentPane().add(departureAirportCodeText);
		departureAirportCodeText.setColumns(10);
		
		departureDateText = new JTextField();
		departureDateText.setColumns(10);
		departureDateText.setBounds(270, 108, 180, 24);
		frame.getContentPane().add(departureDateText);
		
		arrivalAirportCodeText = new JTextField();
		arrivalAirportCodeText.setColumns(10);
		arrivalAirportCodeText.setBounds(270, 47, 180, 24);
		frame.getContentPane().add(arrivalAirportCodeText);
		
		returnDateText = new JTextField();
		returnDateText.setColumns(10);
		returnDateText.setBounds(270, 145, 180, 24);
		frame.getContentPane().add(returnDateText);
		
		JLabel departureAirportLabel = new JLabel("Departure Airport Code:");
		departureAirportLabel.setBounds(24, 13, 197, 18);
		frame.getContentPane().add(departureAirportLabel);
		
		JLabel departureDateLabel = new JLabel("Departure Date (yyyy_MM_dd):");
		departureDateLabel.setBounds(23, 111, 233, 18);
		frame.getContentPane().add(departureDateLabel);
		
		JLabel arrivalAirportLabel = new JLabel("Arrival Airport Code:");
		arrivalAirportLabel.setBounds(24, 44, 197, 18);
		frame.getContentPane().add(arrivalAirportLabel);
		
		JLabel returnDateLabel = new JLabel("Return Date (yyyy_MM_dd):");
		returnDateLabel.setBounds(24, 148, 233, 18);
		frame.getContentPane().add(returnDateLabel);
		
		scrollPane0 = new JScrollPane();
		scrollPane0.setBounds(14, 272, 731, 162);
		scrollPane0.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane0.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		frame.getContentPane().add(scrollPane0);
		
		firstTravelsTable = new JTable();
		firstTravelsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		firstTravelsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane0.setViewportView(firstTravelsTable);
		
		
		scrollPane1 = new JScrollPane();
		scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane1.setBounds(14, 479, 731, 162);
		frame.getContentPane().add(scrollPane1);
		
		secondTravelsTable = new JTable();
		secondTravelsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		secondTravelsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane1.setViewportView(secondTravelsTable);
		
		isRoundTripRadioButton = new JRadioButton("Round Trip");
		isRoundTripRadioButton.setSelected(true);
		isRoundTripRadioButton.setBounds(24, 80, 113, 27);
		frame.getContentPane().add(isRoundTripRadioButton);
		
		isOneWayRadioButton = new JRadioButton("One Way");
		isOneWayRadioButton.setBounds(157, 80, 85, 27);
		frame.getContentPane().add(isOneWayRadioButton);
		
		ActionListener roundTripOneWayRadioButtonActionListener = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(isRoundTripRadioButton.isSelected()){
					isRoundTrip = true;
					returnDateText.setEnabled(true);
				}else if(isOneWayRadioButton.isSelected()){
					isRoundTrip = false;
					returnDateText.setEnabled(false);
				}
				
			}
		};
		isRoundTripRadioButton.addActionListener(roundTripOneWayRadioButtonActionListener);
		isOneWayRadioButton.addActionListener(roundTripOneWayRadioButtonActionListener);
		roundTripOrOneWayButtonGroup = new ButtonGroup();
		roundTripOrOneWayButtonGroup.add(isRoundTripRadioButton);
		roundTripOrOneWayButtonGroup.add(isOneWayRadioButton);
		
		
		isFirstClassRadioButton = new JRadioButton("First Class");
		isFirstClassRadioButton.setSelected(true);
		isFirstClassRadioButton.setBounds(21, 175, 117, 27);
		frame.getContentPane().add(isFirstClassRadioButton);
		
		isCoachRadioButton = new JRadioButton("Coach");
		isCoachRadioButton.setBounds(157, 178, 76, 27);
		frame.getContentPane().add(isCoachRadioButton);
		ActionListener firstClassOrCoachRadioButtonActionListener = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(isFirstClassRadioButton.isSelected()){
					isFirstClass = true;
				}else if(isCoachRadioButton.isSelected()){
					isFirstClass = false;
				}
			}
		};
		
		isFirstClassRadioButton.addActionListener(firstClassOrCoachRadioButtonActionListener);
		isCoachRadioButton.addActionListener(firstClassOrCoachRadioButtonActionListener);
		firstClassOrCoachButtonGroup = new ButtonGroup();
		firstClassOrCoachButtonGroup.add(isFirstClassRadioButton);
		firstClassOrCoachButtonGroup.add(isCoachRadioButton);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String departureAirportCodeString = departureAirportCodeText.getText().trim();
				String departureDateString = departureDateText.getText().trim();
				String arrivalAirportCodeString = arrivalAirportCodeText.getText().trim();
				String returnDateString = returnDateText.getText().trim();
							
				searchParameter = new SearchParameter(isRoundTrip,departureAirportCodeString,departureDateString,arrivalAirportCodeString,returnDateString,isFirstClass);
				if(!searchParameter.isDepartureAirportCodeValid(airports)){
					JOptionPane.showMessageDialog(null,"The Departure Airport is incorrect!", "alert", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(!searchParameter.isArrivingAirportCodeValid(airports)){
					JOptionPane.showMessageDialog(null,"The Arrival Airport is incorrect!", "alert", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(!searchParameter.isTravelDatesValid()){
					JOptionPane.showMessageDialog(null,"One of the Travel Dates is incorrect!", "alert", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				SearchTravel searchTravel = new SearchTravel(searchParameter,airplanes,airports);
				firstTravels = searchTravel.getFistTravels();
				secondTravels =searchTravel.getSecondTravels();
				displaySearchResult(firstTravels,secondTravels);
			}
		});
		btnSubmit.setBounds(269, 182, 85, 27);
		frame.getContentPane().add(btnSubmit);
		
		JButton btnSortByPrice = new JButton("Sort By Price");
		btnSortByPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int order;
				if(searchParameter.isFirstClass()){
					order = SortTravelByOrder.FIRST_CLASS_PRICE;
				}else{
					order = SortTravelByOrder.COACH_PRICE;
				}
				sortAndDisplaySearchResult(order,firstTravels,secondTravels);
			}
		});
		btnSortByPrice.setBounds(380, 182, 137, 27);
		frame.getContentPane().add(btnSortByPrice);
		
		JButton btnSortByDepatureTime = new JButton("Sort By Departure Time");
		btnSortByDepatureTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int order = SortTravelByOrder.DEPARTURE_TIME;
				sortAndDisplaySearchResult(order,firstTravels,secondTravels);
			}
		});
		btnSortByDepatureTime.setBounds(531, 182, 214, 27);
		frame.getContentPane().add(btnSortByDepatureTime);
		
		JButton btnSortByTravelTime = new JButton("Sort By Travel Time");
		btnSortByTravelTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int order = SortTravelByOrder.TRAVEL_TIME;
				sortAndDisplaySearchResult(order,firstTravels,secondTravels);
			}
		});
		btnSortByTravelTime.setBounds(531, 215, 191, 27);
		frame.getContentPane().add(btnSortByTravelTime);
		
		JButton btnSortByArrivalTime = new JButton("Sort By Arrival Time");
		btnSortByArrivalTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int order = SortTravelByOrder.ARRIVAL_TIME;
				sortAndDisplaySearchResult(order,firstTravels,secondTravels);
			}
		});
		btnSortByArrivalTime.setBounds(303, 215, 214, 27);
		frame.getContentPane().add(btnSortByArrivalTime);
		
		firstTravelsLabel = new JLabel("Departure Travels:");
		firstTravelsLabel.setBounds(14, 252, 153, 18);
		frame.getContentPane().add(firstTravelsLabel);
		
		secondTravelsLabel = new JLabel("Return Travels:");
		secondTravelsLabel.setBounds(14, 458, 123, 18);
		frame.getContentPane().add(secondTravelsLabel);
		
		JButton btnReserveTravel = new JButton("Reserve Travel");
		btnReserveTravel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice1 = firstTravelsTable.getSelectedRow();
				int choice2 = secondTravelsTable.getSelectedRow();
				if(choice1 < 0){
					JOptionPane.showMessageDialog(null,"No Departure Travel is Selected!", "alert", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(searchParameter.isRoundTrip() && choice2 < 0){
					JOptionPane.showMessageDialog(null,"You search for a Round Trip, But Only Select One Travel!", "alert", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Travel firstTravel = firstTravels.get(choice1);
				Travel secondTravel = null;
				if(searchParameter.isRoundTrip()){
					secondTravel = secondTravels.get(choice2);
					if(firstTravel.getArrivalTime().after(secondTravel.getDepartureTime())){
						JOptionPane.showMessageDialog(null,"Departure Travel's Arrival Time is after Return Travel's Departure Time!", "alert", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				ReserveTravel reserveTravel = new ReserveTravel(searchParameter.isRoundTrip(),firstTravel,secondTravel,searchParameter.isFirstClass());
				
				String travelsInformation = "Here is Your Reservation Information.\n";
					
				if(searchParameter.isRoundTrip()){
					travelsInformation += "Round Trip:\n";
					travelsInformation += "Departure Travel:\n" + firstTravel.getTravelInformation();
					travelsInformation += "Return Travel:\n" + secondTravel.getTravelInformation();
				}else{
					travelsInformation += "One Way:\n";
					travelsInformation += firstTravel.getTravelInformation();
				}
				travelsInformation += "If information is correct, please choose Yes to make reservation. Otherwise, please choose No!";
				int confirm = JOptionPane.showConfirmDialog(null, travelsInformation, "Confirm", JOptionPane.YES_NO_OPTION); 
				if(confirm == JOptionPane.YES_OPTION){
					boolean n = reserveTravel.reserveTravel();
					if(n){
						JOptionPane.showMessageDialog(null, "Your Reservation is Successfully Made!","information", JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "Your Reservation failed. Please Try again later!", "alert", JOptionPane.ERROR_MESSAGE); 
					}
					displaySearchResult(firstTravels,secondTravels);
				}else{
					return;
				}
			}
		});
		btnReserveTravel.setBounds(14, 654, 145, 27);
		frame.getContentPane().add(btnReserveTravel);
	}
	
	/**Change size of table columns to fit the content.
	 * @param myTable is the JTable whose column size would be fit
	 */
	public static void FitTableColumns(JTable myTable){
		JTableHeader header = myTable.getTableHeader();
		int rowCount = myTable.getRowCount();
		Enumeration<TableColumn> columns = myTable.getColumnModel().getColumns();
		while(columns.hasMoreElements()){
			TableColumn column = (TableColumn)columns.nextElement();
		    int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
		    int width = (int)myTable.getTableHeader().getDefaultRenderer().getTableCellRendererComponent(myTable, column.getIdentifier(), false, false, -1, col).getPreferredSize().getWidth();
		    for(int row = 0; row<rowCount; row++){
		    	int preferedWidth = (int)myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable,
		        myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
		        width = Math.max(width, preferedWidth);
		    }
		    header.setResizingColumn(column);
		    column.setWidth(width+myTable.getIntercellSpacing().width);
		}
	}
	
	/**Display search result after system gets the travels meeting customer's requirement
	 * @param firstTravels is the list of departure travels
	 * @param secondTravels is the list return travels if the customer chooses a round-trip 
	 */
	public void displaySearchResult(Travels firstTravels,Travels secondTravels){
		DefaultTableModel model1 = new DefaultTableModel();
		Vector colName = new Vector();
		colName.addElement("Departure Airport");
		colName.addElement("Departure Time");
		colName.addElement("First Stopover");
		colName.addElement("Second Stopover");
		colName.addElement("Arrival Airport");
		colName.addElement("Arrival Time");
		colName.addElement("Travel Time(minutes)");
		colName.addElement("Total Price($)");
		colName.addElement("Remaining Seats of First Flight");
		colName.addElement("Remaining Seats of Second Flight");
		colName.addElement("Remaining Seats of Third Flight");
		
		Vector data1 = createVector(firstTravels);
		model1.setDataVector(data1, colName);
		
		firstTravelsTable.setModel(model1);
		FitTableColumns(firstTravelsTable);
		
		DefaultTableModel model2 = new DefaultTableModel();
		Vector data2 = createVector(secondTravels);
		
		model2.setDataVector(data2, colName);
		secondTravelsTable.setModel(model2);
		FitTableColumns(secondTravelsTable);
	}
	
	private Vector createVector(Travels travels){
		Vector data = new Vector();
		for(Travel travel:travels){
			Vector row = new Vector();
			row.addElement(travel.getDepartureAirportCode());
			row.addElement(travel.getDepartureTimeInLocalTimeZone());
			row.addElement(travel.getFirstStopover());
			row.addElement(travel.getSecondStopover());
			row.addElement(travel.getArrivalAirportCode());
			row.addElement(travel.getArrivalTimeInLocalTimeZone());	
			row.addElement(travel.getTravelTimeInMinutes());
			row.addElement(travel.getTotalPrice(isFirstClass));
			row.addElement(travel.getNumOfRemainingSeats(0, isFirstClass, airplanes));
			row.addElement(travel.getNumOfRemainingSeats(1, isFirstClass, airplanes));
			row.addElement(travel.getNumOfRemainingSeats(2, isFirstClass, airplanes));
			data.addElement(row);
		}
		return data;
	}
	
	/**Sort and display search result
	 * @param order is on which kind of order the sorting is based
	 * @param firstTravels is the list of departure travels
	 * @param secondTravels is the list return travels if the customer chooses a round-trip 
	 */
	public void sortAndDisplaySearchResult(int order,Travels firstTravels,Travels secondTravels){
		SortTravelByOrder sortTravelOrder = new SortTravelByOrder(order);
		Collections.sort(firstTravels, sortTravelOrder);
		Collections.sort(secondTravels, sortTravelOrder);
		displaySearchResult(firstTravels,secondTravels);
	}
}
