package runner;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JWindow;

import main.Simulator;

public class SplashScreen extends JWindow{
	private static final long serialVersionUID = -4943328726608367132L;
	JPanel panel;
	BufferedImage image;
	
	public SplashScreen() {
		//image = new BufferedImage();
		panel = new JPanel();
		getImage();
		setBounds(0, 0, 1294, 580);
		panel.repaint();
		getContentPane().add(panel);
		setLocationRelativeTo(null);
		setVisible(true);
		
		try {
		    Thread.sleep(3000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		
		setVisible(false);
		new Simulator();
		dispose();
	}
	
	private void getImage() {
		try {
			InputStream stream = Simulator.class.getClassLoader().getResourceAsStream("images/hanzesplash.png");
			image = ImageIO.read(stream);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}
}