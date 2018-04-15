import javax.swing.*;
import java.awt.color.*;
import java.awt.event.*;


public class MiningGUI extends UIFrame{

	private JPanel mining_panel, market_info;
	private JButton mine;
	private JLabel description;
	public static final int WIDTH = 600;
	public static final int HEIGHT = 400;
	
	public MiningGUI() {
		super();
		mining_panel = new JPanel();
		market_info = new JPanel();
		description = new JLabel("MINE SPAMCOIN");
		mining_panel.add(description);
		initializeMiningPage(mining_panel);
		this.add(mining_panel);
	}	
	
	public void initializeMarketInfo(JPanel panel) {
		panel.setSize(800,800);
		
	}
	
	public void initializeMiningPage(JPanel panel) {		
		panel.setSize(WIDTH, HEIGHT);
		ActionListener mining = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("MINE");				
			}
		};
		
		Icon icon = new ImageIcon("Spam_can.png");
		mine = new JButton(icon);
		mine.addActionListener(mining);
		panel.add(mine);
	}
	
	public static void main(String[] args){
		new MiningGUI();
	}
}
