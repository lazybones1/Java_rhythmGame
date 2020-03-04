package dynamic_beat_1;
import javax.swing.JFrame;

public class DynamicBeat extends JFrame{
	
	public DynamicBeat() {
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); // ban size change
		setLocationRelativeTo(null); // location = center
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //game exit = exit
		setVisible(true); // JFrame visible
	}
	
}
