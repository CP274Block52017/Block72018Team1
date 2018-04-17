import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MiningUIState implements UIState {
	private JButton mineButton;
	private JButton transactionButton;
	private JLabel balanceLabel;
	
	public MiningUIState() {
		Icon spamIcon = new ImageIcon("spam.png");
		mineButton = new JButton(spamIcon);
		transactionButton = new JButton("Make a transaction");
		balanceLabel = new JLabel("", SwingConstants.CENTER);
	}
	
	public void updateBalance(double balance) {
		balanceLabel.setText("Current balance: " + balance);
	}
	
	public void addComponents(UIFrame frame) {
		frame.setLayout(new GridLayout(3, 0));
		
		balanceLabel.setFont(frame.getStandardizedFont(48));
		frame.add(balanceLabel);
		updateBalance(frame.getLocalNode().getBalance());

		mineButton.setFont(frame.getStandardizedFont(32));
		mineButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.getLocalNode().work();
						updateBalance(frame.getLocalNode().getBalance());
					}
				});
		frame.add(mineButton);
		
		transactionButton.setFont(frame.getStandardizedFont(32));
		transactionButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.setState(new TransactionUIState());
					}
				});
		frame.add(transactionButton);
	}
}
