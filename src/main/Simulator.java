package main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import controller.Controller;
import controller.SettingsController;
import logic.Model;
import view.AbstractView;
import view.CarParkView;
import view.HistogramView;
import view.TimeView;
import view.Labels;
import view.LineGraphView;
import view.NumberView;
import view.ProfitView;
import view.ProfitsHistogramView;
import view.PieChartView;
import view.QueueView;

//extends maakt het window aan bij de frontend.
public class Simulator extends JFrame implements ComponentListener{
	private static final long serialVersionUID = 8060582986233007360L;
	private Model model;
	private Controller controller;
	private SettingsController settingsController;
	private JPanel viewPanel;
	private JPanel carPanel;
	private JLabel carLabel;
	private JTabbedPane carTabPane;
	private JPanel financialPanel;
	private JLabel financialLabel;
	private JTabbedPane financialTabPane;
	private JPanel queuesPanel;
	private JLabel queuesLabel;
	private JTabbedPane queuesTabPane;
	private AbstractView carParkView;
	private AbstractView tijdView;
	private AbstractView labels;
	private AbstractView percentView;
	private AbstractView pieChartView;
	private AbstractView numberView;
	private AbstractView histogramView;
	private AbstractView queueView;
	private AbstractView lineGraphView;
	private AbstractView profitsHistogramView;
	
	//super zorgt ervoor dat het een titel krijgt, die wordt boven in het frame weergegeven.
	public Simulator() {
		super("Pakeer Garage Simulatie");
		model = new Model();
		controller = new Controller(model);
		settingsController = new SettingsController(model, this);
		viewPanel = new JPanel();
		carPanel = new JPanel();
		carLabel = new JLabel("Auto weergaven");
		carTabPane = new JTabbedPane();
		financialPanel = new JPanel();
		financialLabel = new JLabel("Financiele weergaven");
		financialTabPane = new JTabbedPane();
		queuesPanel = new JPanel();
		queuesLabel = new JLabel("Wachtrijen weergaven");
		queuesTabPane = new JTabbedPane();
		//westPanel = new JPanel();
		carParkView = new CarParkView(model);
		tijdView = new TimeView(model);
		labels = new Labels(model);
		percentView = new ProfitView(model);
		pieChartView = new PieChartView(model);
		numberView = new NumberView(model);
		histogramView = new HistogramView(model);
		queueView = new QueueView(model);
		lineGraphView = new LineGraphView(model);
		profitsHistogramView = new ProfitsHistogramView(model);
		setUpFrame();
	}

	//setup frame geeft een grootte aan het frame (hier komt alles in wat ingesteld moet worden binnen het frame)
	private void setUpFrame() {
		setLayout(new BorderLayout());
		createTabPanes();
		viewPanel.setLayout(new GridLayout(2, 2, 20, 20));
		carPanel.setLayout(new BoxLayout(carPanel, BoxLayout.Y_AXIS));
		financialPanel.setLayout(new BoxLayout(financialPanel, BoxLayout.Y_AXIS));
		queuesPanel.setLayout(new BoxLayout(queuesPanel, BoxLayout.Y_AXIS));
		viewPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
		addComponents();
		setIcon();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addComponentListener(this);
		setVisible(true);
	}
	
	private void createTabPanes() {
		//car tabs
		carTabPane.addTab("Tekst", null, numberView, "Tekst weergave van auto's");
		carTabPane.addTab("Cirkeld...", null, pieChartView, "Cirkeldiagram weergave van auto's");
		carTabPane.addTab("Histogram", null, histogramView, "Histogram weergave van de auto's");
		carTabPane.addTab("Lijngrafiek", null, lineGraphView, "Lijngrafiek weergave van de auto's");
		
		//finacial tabs
		financialTabPane.addTab("Tekst", null, percentView, "Tekst weergave van de omzet");
		financialTabPane.addTab("Diagram", null, profitsHistogramView, "Diagram weergave van de omzet van de afgelopen 12 maanden");
		
		//queues tabs
		queuesTabPane.addTab("Tekst", null, queueView, "Tekst weergave van rijen");
	}

	private void setIcon() {
		try {
			InputStream stream = Simulator.class.getClassLoader().getResourceAsStream("images/icon.png");
			BufferedImage icon = ImageIO.read(stream);
			setIconImage(icon);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	//addcomponents daarmee kun je views en controllers toevoegen aan het frame.
	public void addComponents() {
		getContentPane().add(viewPanel, BorderLayout.EAST);
		getContentPane().add(carParkView, BorderLayout.CENTER);
		getContentPane().add(controller, BorderLayout.SOUTH);
		getContentPane().add(tijdView, BorderLayout.NORTH);
		getContentPane().add(settingsController, BorderLayout.WEST);
		viewPanel.add(carPanel);
		viewPanel.add(financialPanel);
		viewPanel.add(queuesPanel);
		viewPanel.add(labels);
		carPanel.add(carLabel);
		carPanel.add(carTabPane);
		financialPanel.add(financialLabel);
		financialPanel.add(financialTabPane);
		queuesPanel.add(queuesLabel);
		queuesPanel.add(queuesTabPane);
		pack();
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void componentResized(ComponentEvent e) {
		model.notifyViews();
	}
	
	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
}
