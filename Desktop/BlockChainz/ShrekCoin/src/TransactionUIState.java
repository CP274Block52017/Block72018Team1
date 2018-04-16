import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TransactionUIState implements UIState {
	private JButton sendButton;
	private JLabel balanceLabel;
	private JButton backButton;
	
	public TransactionUIState() {
		sendButton = new JButton("Send");
		balanceLabel = new JLabel("", SwingConstants.CENTER);
		backButton = new JButton("Back to mining");
	}
	
	public void updateBalance(double balance) {
		balanceLabel.setText("Current balance: " + balance);
	}
	
	public void addComponents(UIFrame frame) {
		frame.setLayout(new GridLayout(5, 0));
		
		balanceLabel.setFont(frame.getStandardizedFont(48));
		frame.add(balanceLabel);
		updateBalance(frame.getLocalNode().getBalance());
		
		JTextField keyField = new JTextField("Reciever's public key");
		frame.add(keyField);
		JTextField amountField = new JTextField("Amount");
		frame.add(amountField);
		// TODO make pretty
		
		sendButton.setFont(frame.getStandardizedFont(32));
		sendButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.getLocalNode().pushTransaction(new Transaction(frame.getLocalNode().getPublicKey(), Integer.parseInt(keyField.getText()), Double.parseDouble(amountField.getText())));
						updateBalance(frame.getLocalNode().getBalance());
					}
				});
		frame.add(sendButton);
		
		backButton.setFont(frame.getStandardizedFont(32));
		backButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.setState(new MiningUIState());
					}
				});
		frame.add(backButton);
	}
}
