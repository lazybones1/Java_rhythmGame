package dynamic_beat_4;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame {

	private Image screenImage; // for double buffering
	private Graphics screenGraphic; // for double buffering
		
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	
	private Image introBackground = new ImageIcon(Main.class.getResource("../images/introBackground(Title).jpg")).getImage(); // main.class.getResource = base : main class (relative path);
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
		private JButton exitButton = new JButton(exitButtonBasicImage);
	
	private int mouseX, mouseY;
	
	public DynamicBeat() {
		setUndecorated(true); // delete basic menuBar
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); // ban size change
		setLocationRelativeTo(null); // location = center
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // game exit = exit
		setVisible(true); // JFrame visible
		setBackground(new Color(0,0,0,0)); //background-color : white
		setLayout(null); // no layout
		
		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false); //button border setting
		exitButton.setContentAreaFilled(false); // button area background setting
		exitButton.setFocusPainted(false); // focus sign setting 
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				try {
					Thread.sleep(1000);
				}catch(InterruptedException ex) {
					ex.printStackTrace();
				}
				buttonPressedMusic.start();
				System.exit(0);				
			}
		});		
		add(exitButton);
		
		menuBar.setBounds(0,0,1280,30); // set menuBar position, size 
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x-mouseX, y-mouseY); //game screen location change
			}
		});
		add(menuBar);
		
		
		
		Music introMusic = new Music("introMusic.mp3", true);
		introMusic.start(); //start : in thread
	}

	public void paint(Graphics g) { //setVisible implicitly call paint(), double buffering
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null); // screenImage draw in (0,0)
	}

	public void screenDraw(Graphics g) { //paint -> screenDraw -> paint -> screenDraw (reservation) , double buffering
		g.drawImage(introBackground, 0, 0, null); // if changeable
		paintComponents(g); // add component draw (if unchangeable)
		this.repaint(); // repeat drawing image while before end

	}

}
