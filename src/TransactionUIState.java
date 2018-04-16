import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * a UIState that lets the user interact with transactions
 * @author Case Regan
 *
 */
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
		frame.setLayout(new GridLayout(4, 0));
		
		balanceLabel.setFont(frame.getStandardizedFont(48));
		frame.add(balanceLabel);
		updateBalance(frame.getLocalNode().getBalance());

		JPanel entryPanel = new JPanel(new GridLayout(2, 0));
		JTextField keyField = new JTextField("Reciever's public key");
		keyField.addFocusListener(
				new FocusListener() {
					public void focusGained(FocusEvent arg0) {
						keyField.selectAll();
					}
					public void focusLost(FocusEvent e) {}					
				});
		entryPanel.add(keyField);
		
		JTextField amountField = new JTextField("Amount");
		amountField.addFocusListener(
				new FocusListener() {
					public void focusGained(FocusEvent arg0) {
						amountField.selectAll();
					}
					public void focusLost(FocusEvent e) {}					
				});
		entryPanel.add(amountField);
		
		frame.add(entryPanel);
		
		sendButton.setFont(frame.getStandardizedFont(32));
		sendButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							int recieverKey = Integer.parseInt(keyField.getText());
							double amount = Double.parseDouble(amountField.getText());
							frame.getLocalNode().pushTransaction(new Transaction(frame.getLocalNode().getPublicKey(), recieverKey, amount));
						} catch(NumberFormatException nfe) {
							System.out.println("Invalid transaction");
							return;
						}
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