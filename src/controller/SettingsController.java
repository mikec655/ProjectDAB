package controller;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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

import logic.Model;
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
	private JLabel adHocCarPriceLabel;
	private JTextField adHocCarPriceField;
	private JButton adHocCarPriceButton;
	private JLabel resCarPriceLabel;
	private JTextField resCarPriceField;
	private JButton resCarPriceButton;
	private JLabel passCarPriceLabel;
	private JTextField passCarPriceField;
	private JButton passCarPriceButton;
	private JLabel extraJazzLabel;
	private JSlider extraJazzSlider;
	//private JLabel extraPassJazzLabel;
	//private JSlider extraPassJazzSlider;
	//private JLabel extraResJazzLabel;
	//private JSlider extraResJazzSlider;
	private JPanel enterSpeedPanel;
	private JPanel paymentSpeedPanel;
	private JPanel exitSpeedPanel;
	private JPanel adHocCarPricePanel;
	private JPanel resCarPricePanel;
	private JPanel passCarPricePanel;
	private JPanel extraJazzPanel;
	//private JPanel extraPassJazzPanel;
	//private JPanel extraResJazzPanel;
	
	public SettingsController(Model model, JFrame frame) {
		super(model);
		parent = frame;
		//Prijs per auto
		//Toegevoegde drukte per auto
		//Waarschuwing schermen
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
		adHocCarPricePanel = new JPanel();
		adHocCarPriceLabel = new JLabel("Prijs normale auto/uur:");
		adHocCarPriceField = new JTextField(12);
		adHocCarPriceButton = new JButton("Bevestig");
		resCarPricePanel = new JPanel();
		resCarPriceLabel = new JLabel("Prijs gereserveerde auto/uur:");
		resCarPriceField = new JTextField(12);
		resCarPriceButton = new JButton("Bevestig");
		passCarPricePanel = new JPanel();
		passCarPriceLabel = new JLabel("Prijs abbonnement auto/maand:");
		passCarPriceField = new JTextField(12);
		passCarPriceButton = new JButton("Bevestig");
		extraJazzPanel = new JPanel();
		extraJazzLabel = new JLabel("Extra drukte normale auto's:");
		extraJazzSlider = new JSlider(10, 20, 10);
		//extraPassJazzPanel = new JPanel();
		//extraPassJazzLabel = new JLabel("Extra drukte abbonnement auto's:");
		//extraPassJazzSlider = new JSlider(10, 20, 10);
		//extraResJazzPanel = new JPanel();
		//extraResJazzLabel = new JLabel("Extra drukte gereserveerde auto's:");
		//extraResJazzSlider = new JSlider(10, 20, 10);

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
		add(adHocCarPricePanel);
		adHocCarPricePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		adHocCarPricePanel.add(adHocCarPriceLabel);
		adHocCarPricePanel.add(adHocCarPriceField);
		adHocCarPricePanel.add(adHocCarPriceButton);
		add(resCarPricePanel);
		resCarPricePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		resCarPricePanel.add(resCarPriceLabel);
		resCarPricePanel.add(resCarPriceField);
		resCarPricePanel.add(resCarPriceButton);
		add(passCarPricePanel);
		passCarPricePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		passCarPricePanel.add(passCarPriceLabel);
		passCarPricePanel.add(passCarPriceField);
		passCarPricePanel.add(passCarPriceButton);
		add(extraJazzPanel);
		extraJazzPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		extraJazzPanel.add(extraJazzLabel);
		extraJazzPanel.add(extraJazzSlider);
		//add(extraPassJazzPanel);
		//extraPassJazzPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		//extraPassJazzPanel.add(extraPassJazzLabel);
		//extraPassJazzPanel.add(extraPassJazzSlider);
		//add(extraResJazzPanel);
		//extraResJazzPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		//extraResJazzPanel.add(extraResJazzLabel);
		//extraResJazzPanel.add(extraResJazzSlider);
		enterSpeedButton.addActionListener(this);
		paymentSpeedButton.addActionListener(this);
		exitSpeedButton.addActionListener(this);
		adHocCarPriceButton.addActionListener(this);
		resCarPriceButton.addActionListener(this);
		passCarPriceButton.addActionListener(this);
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
		if (e.getSource() == adHocCarPriceButton) {
			try {
				//model.set(Integer.parseInt(adHocCarPriceField.getText().trim()));
				adHocCarPriceField.setText("");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(parent,
					    "Ongeldige invoer in prijs normale auto/uur veld!",
					    "Ongeldige invoer",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource() == resCarPriceButton) {
			try {
				//model.set(Integer.parseInt(resCarPriceField.getText().trim()));
				resCarPriceField.setText("");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(parent,
					    "Ongeldige invoer in prijs gereserveerde auto/uur veld!",
					    "Ongeldige invoer",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource() == passCarPriceButton) {
			try {
				//model.set(Integer.parseInt(passCarPriceField.getText().trim()));
				passCarPriceField.setText("");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(parent,
					    "Ongeldige invoer in prijs abbonnement auto/maand veld!",
					    "Ongeldige invoer",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == extraJazzSlider) {
			model.setsyntJazz(1.0 * extraJazzSlider.getValue());
		}
		
	}

}
	
	
