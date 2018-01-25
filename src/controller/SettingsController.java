package controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import logic.Model;
import view.AbstractView;

public class SettingsController extends AbstractView implements ActionListener {
	private static final long serialVersionUID = -4733852151674920525L;
	private JLabel enterSpeedLabel;
	private JTextField enterSpeedField;
	private JButton enterSpeedButton;
	private JLabel paymentSpeedLabel;
	private JTextField paymentSpeedField;
	private JButton paymentSpeedButton;
	private JLabel exitSpeedLabel;
	private JTextField exitSpeedField;
	private JButton exitSpeedButton;
	
	public SettingsController(Model model) {
		super(model);
		//Prijs per auto
		//Toegevoegde drukte per auto
		//Waarschuwing schermen
		enterSpeedLabel = new JLabel("Binnenrij snelheld:");
		enterSpeedField = new JTextField();
		enterSpeedButton = new JButton("Bevestig");
		paymentSpeedLabel = new JLabel("Betaal snelheld:");
		paymentSpeedField = new JTextField();
		paymentSpeedButton = new JButton("Bevestig");
		exitSpeedLabel = new JLabel("Uitrij snelheld:");
		exitSpeedField = new JTextField();
		exitSpeedButton = new JButton("Bevestig");

		setUpPanel();
	}
	
	public Dimension getPreferredSize() {
        return new Dimension(320, 0);
    }
	
	private void setUpPanel(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(enterSpeedLabel);
		add(enterSpeedField);
		add(enterSpeedButton);
		add(paymentSpeedLabel);
		add(paymentSpeedField);
		add(paymentSpeedButton);
		add(exitSpeedLabel);
		add(exitSpeedField);
		add(exitSpeedButton);
	}

	//actionPerformed voor de actie van de knoppen
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
	
	
