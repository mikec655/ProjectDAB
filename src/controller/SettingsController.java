package controller;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import logic.AdHocCar;
import logic.Model;
import logic.ParkingPassCar;
import logic.ResCar;
import view.AbstractView;

public class SettingsController extends AbstractView implements ActionListener, ChangeListener {
	private static final long serialVersionUID = -4733852151674920525L;
	private JFrame parent;
	private JLabel enterSpeedLabel;
	private JTextField enterSpeedField;
	private JButton enterSpeedButton;
	private JLabel paymentSpeedLabel;
	private JTextField paymentSpeedField;
	private JButton paymentSpeedButton;
	private JLabel exitSpeedLabel;
	private JTextField exitSpeedField;
	private JButton exitSpeedButton;
	private JLabel pricePerHourLabel;
	private JTextField pricePerHourField;
	private JButton pricePerHourButton;
	private JLabel reservationPriceLabel;
	private JTextField reservationPriceField;
	private JButton reservationPriceButton;
	private JLabel passCarPriceLabel;
	private JTextField passCarPriceField;
	private JButton passCarPriceButton;
	private JLabel extraJazzLabel;
	private JSlider extraJazzSlider;
	private JPanel enterSpeedPanel;
	private JPanel paymentSpeedPanel;
	private JPanel exitSpeedPanel;
	private JPanel pricePerHourPanel;
	private JPanel reservationPricePanel;
	private JPanel passCarPricePanel;
	private JPanel extraJazzPanel;
	private JPanel queueSizePanel;
	private JLabel queueSizeLabel;
	private JTextField queueSizeField;
	private JButton queueSizeButton;
	
	public SettingsController(Model model, JFrame frame) {
		super(model);
		parent = frame;
		enterSpeedPanel = new JPanel();
		enterSpeedLabel = new JLabel("Binnenrij snelheid:");
		enterSpeedField = new JTextField(12);
		enterSpeedButton = new JButton("Bevestig");
		paymentSpeedPanel = new JPanel();
		paymentSpeedLabel = new JLabel("Betaal snelheid:   ");
		paymentSpeedField = new JTextField(12);
		paymentSpeedButton = new JButton("Bevestig");
		exitSpeedPanel = new JPanel();
		exitSpeedLabel = new JLabel("Uitrij snelheid:     ");
		exitSpeedField = new JTextField(12);
		exitSpeedButton = new JButton("Bevestig");
		pricePerHourPanel = new JPanel();
		pricePerHourLabel = new JLabel("Prijs per uur:             ");
		pricePerHourField = new JTextField(12);
		pricePerHourButton = new JButton("Bevestig");
		reservationPricePanel = new JPanel();
		reservationPriceLabel = new JLabel("Prijs voor reserveren:");
		reservationPriceField = new JTextField(12);
		reservationPriceButton = new JButton("Bevestig");
		passCarPricePanel = new JPanel();
		passCarPriceLabel = new JLabel("Prijs abbonnement per maand:");
		passCarPriceField = new JTextField(12);
		passCarPriceButton = new JButton("Bevestig");
		queueSizePanel = new JPanel();
		queueSizeLabel = new JLabel("Rij grootte:                   ");
		queueSizeField = new JTextField(12);
		queueSizeButton = new JButton("Bevestig");
		extraJazzPanel = new JPanel();
		extraJazzLabel = new JLabel("Extra drukte normale auto's:");
		extraJazzSlider = new JSlider(0, 20, 0);
		setUpPanel();
	}
	
	public Dimension getPreferredSize() {
        return new Dimension(240, 0);
    }
	
	private void setUpPanel(){
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(enterSpeedPanel);
		enterSpeedPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		enterSpeedPanel.add(enterSpeedLabel);
		enterSpeedPanel.add(enterSpeedField);
		enterSpeedPanel.add(enterSpeedButton);
		add(paymentSpeedPanel);
		paymentSpeedPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		paymentSpeedPanel.add(paymentSpeedLabel);
		paymentSpeedPanel.add(paymentSpeedField);
		paymentSpeedPanel.add(paymentSpeedButton);
		add(exitSpeedPanel);
		exitSpeedPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		exitSpeedPanel.add(exitSpeedLabel);
		exitSpeedPanel.add(exitSpeedField);
		exitSpeedPanel.add(exitSpeedButton);
		add(pricePerHourPanel);
		pricePerHourPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		pricePerHourPanel.add(pricePerHourLabel);
		pricePerHourPanel.add(pricePerHourField);
		pricePerHourPanel.add(pricePerHourButton);
		add(reservationPricePanel);
		reservationPricePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		reservationPricePanel.add(reservationPriceLabel);
		reservationPricePanel.add(reservationPriceField);
		reservationPricePanel.add(reservationPriceButton);
		add(passCarPricePanel);
		passCarPricePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		passCarPricePanel.add(passCarPriceLabel);
		passCarPricePanel.add(passCarPriceField);
		passCarPricePanel.add(passCarPriceButton);
		add(extraJazzPanel);
		extraJazzPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		extraJazzPanel.add(extraJazzLabel);
		extraJazzPanel.add(extraJazzSlider);
		add(queueSizePanel);
		queueSizePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		queueSizePanel.add(queueSizeLabel);
		queueSizePanel.add(queueSizeField);
		queueSizePanel.add(queueSizeButton);
		enterSpeedButton.addActionListener(this);
		paymentSpeedButton.addActionListener(this);
		exitSpeedButton.addActionListener(this);
		pricePerHourButton.addActionListener(this);
		reservationPriceButton.addActionListener(this);
		passCarPriceButton.addActionListener(this);
		queueSizeButton.addActionListener(this);
		extraJazzSlider.addChangeListener(this);
	}

	//actionPerformed voor de actie van de knoppen
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == enterSpeedButton) {
			try {
				model.setenterSpeed(Integer.parseInt(enterSpeedField.getText().trim()));
				enterSpeedField.setText("");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(parent,
					    "Ongeldige invoer in binnenrij snelheid veld!",
					    "Ongeldige invoer",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource() == paymentSpeedButton) {
			try {
				model.setpaymentSpeed(Integer.parseInt(paymentSpeedField.getText().trim()));
				paymentSpeedField.setText("");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(parent,
					    "Ongeldige invoer in betaalrij snelheid veld!",
					    "Ongeldige invoer",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource() == exitSpeedButton) {
			try {
				model.setexitSpeed(Integer.parseInt(exitSpeedField.getText().trim()));
				exitSpeedField.setText("");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(parent,
					    "Ongeldige invoer in uitrij snelheid veld!",
					    "Ongeldige invoer",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource() == pricePerHourButton) {
			try {
				AdHocCar.setPricePerHour(Double.parseDouble(pricePerHourField.getText().trim()));
				ResCar.setPricePerHour(Double.parseDouble(pricePerHourField.getText().trim()));
				pricePerHourField.setText("");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(parent,
					    "Ongeldige invoer in prijs normale auto/uur veld!",
					    "Ongeldige invoer",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource() == reservationPriceButton) {
			try {
				ResCar.setPriceReservation(Double.parseDouble(reservationPriceField.getText().trim()));
				reservationPriceField.setText("");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(parent,
					    "Ongeldige invoer in prijs gereserveerde auto/uur veld!",
					    "Ongeldige invoer",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource() == passCarPriceButton) {
			try {
				ParkingPassCar.setPricePerMonth(Integer.parseInt(passCarPriceField.getText().trim()));
				passCarPriceField.setText("");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(parent,
					    "Ongeldige invoer in prijs abbonnement auto/maand veld!",
					    "Ongeldige invoer",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource() == queueSizeButton) {
			try {
				model.setQueueSize(Integer.parseInt(queueSizeField.getText().trim()));
				queueSizeField.setText("");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(parent,
					    "Ongeldige invoer in rij grootte veld!",
					    "Ongeldige invoer",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
		model.notifyViews();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == extraJazzSlider) {
			model.setSyntJazz(5 * extraJazzSlider.getValue());
		}
		
	}

}
	
	
