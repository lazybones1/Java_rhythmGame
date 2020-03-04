package dynamic_beat_3;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class DynamicBeat extends JFrame {

	private Image screenImage; // for double buffering
	private Graphics screenGraphic; // for double buffering
	private Image introBackground;

	public DynamicBeat() {
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); // ban size change
		setLocationRelativeTo(null); // location = center
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // game exit = exit
		setVisible(true); // JFrame visible
		
		introBackground = new ImageIcon(Main.class.getResource("../images/introBackground(Title).jpg")).getImage(); // main.class.getResource = base : main class (relative path)
		
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
		g.drawImage(introBackground, 0, 0, null);
		this.repaint(); // repeat drawing image while before end

	}

}
