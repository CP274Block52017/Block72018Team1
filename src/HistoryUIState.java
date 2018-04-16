import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HistoryUIState implements UIState {
	private JButton backButton;
	
	public HistoryUIState() {
		backButton = new JButton("Make a transaction");
	}
	
	public void addComponents(UIFrame frame) {
		frame.setLayout(new BorderLayout());
		
		JLabel balanceLabel = new JLabel("Current balance: " + frame.getLocalNode().getBalance(), SwingConstants.CENTER);
		balanceLabel.setFont(frame.getStandardizedFont(48));
		frame.add(balanceLabel, BorderLayout.NORTH);
		
		JTextArea transactionHistory = new JTextArea();
		transactionHistory.setEditable(false);
		transactionHistory.setFont(frame.getStandardizedFont(24));
		
		String transactions = "";
		for(Transaction transaction : frame.getLocalNode().getLocalChain().getAllTransactions()) {
			transactions += transaction + "\n";
		}
		
		transactionHistory.setText(transactions);
		
		JScrollPane transactionPane = new JScrollPane(transactionHistory);
		frame.add(transactionPane);
		
		backButton.setFont(frame.getStandardizedFont(32));
		backButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.setState(new TransactionUIState());
					}
				});
		frame.add(backButton, BorderLayout.SOUTH);
	}

}
