import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.*;

public class MiningPanel extends JPanel {
	
	private UIFrame main_frame;
	private JPanel mining_panel;
	private JPanel stats_panel;
	private JButton mine;
	private JLabel description;
	public static final int WIDTH = 600;
	public static final int HEIGHT = 400;
	
	public MiningPanel() {
		main_frame = new UIFrame();

		description = new JLabel("MINE SPAM COIN");
		mining_panel = new JPanel();
		stats_panel = new JPanel();
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		initializeMiningPage(mining_panel);
		mining_panel.add(description);
		this.add(mining_panel);
		this.add(stats_panel);
		main_frame.add(this);
		main_frame.setVisible(true);
		
	}

	
	public void initializeMiningPage(JPanel panel) {		
		panel.setSize(WIDTH, HEIGHT);
		ActionListener mining = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("MINE");				
			}
		};
		
		mine = new JButton("MINE");
				mine.addActionListener(mining);
		
		panel.add(mine);
	}
	
	public void initializeStats_panel(JPanel panel) {
		try {
			URL url = new URL("https://coinmarketcap.com/currencies/bitcoin/#markets?utm_source=rss-utm_medium=rss");
			Scanner s = new Scanner(url.openStream());
			String data = s.nextLine();
			JLabel label = new JLabel(data);
			panel.add(label);	
		}catch(IOException e) {
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) {
		new MiningPanel();
	}
}
