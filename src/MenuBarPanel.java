import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MenuBarPanel extends JPanel{
	
	private JButton overViewButton;
	private JButton transactionButton;
	private JButton miningButton;
	private JButton settingsButton;
	private UIFrame frame;
	
	public MenuBarPanel(UIFrame _frame) {
		overViewButton = new JButton("Overview");
		transactionButton = new JButton("Transaction");
		miningButton = new JButton("Mining");
		settingsButton = new JButton("Settings");
		frame = _frame;
		this.initializePanel();
		this.addFunctions();
	}
	
	public void initializePanel() {
		this.setLayout(new FlowLayout());
		this.add(overViewButton);
		this.add(transactionButton);
		this.add(miningButton);
		this.add(settingsButton);
		this.setVisible(true);	
	}
	
	public void addFunctions() {
		overViewButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.setState(new OverViewUIState());
					}
			
		});
		
		transactionButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.setState(new TransactionUIState());
					}
				});
		miningButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.setState(new MiningUIState());
					}
				});
		settingsButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.setState(new SettingUIState());
					}
				});
	}
	
	public static void main(String[] args) {
		final UIFrame main_frame = new UIFrame();
		main_frame.add(new MenuBarPanel(main_frame));
		main_frame.setVisible(true);
	}
	
	

}
