import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Transaction_fx extends BorderPane implements PaneState {
	
	private Button sendButton;
	private Button contactButton;
	private TextField amountField;
	private TextField receiverField;
	private Label title;
	private Label to;
	private Label amount;
	private static final Font CONTENT_FONT = new Font("Aspergit", 24);
	private ApplicationUI frame;
	
	public Transaction_fx(ApplicationUI _frame) {
		frame = _frame;
		title = new Label("Send a Transaction");
		to = new Label("To: ");
		amount = new Label("Amount: ");
		contactButton = new Button("Contact");
		to.setFont(CONTENT_FONT);
		amount.setFont(CONTENT_FONT);
		title.setFont(ApplicationUI.TITLE_FONT);
		sendButton = new Button("Send");
		amountField = new TextField();
		amountField.setPromptText("Amount");
		receiverField = new TextField();
		receiverField.setPromptText("Receiver's public key");
		addComponents();
		initializeButtons();
	}

	@Override
	public void addComponents() {
		VBox vbox = new VBox();
		HBox amount_box = new HBox();
		HBox receiver_box = new HBox();
		vbox.getChildren().add(title);
		vbox.getChildren().add(new Separator());
		
		amount_box.getChildren().add(amount);
		amount_box.getChildren().add(amountField);
		receiver_box.getChildren().add(to);
		receiver_box.getChildren().add(receiverField);
		receiver_box.getChildren().add(contactButton);
		
		vbox.getChildren().add(receiver_box);
		vbox.getChildren().add(amount_box);
		vbox.getChildren().add(sendButton);
		vbox.setSpacing(20);

		this.setCenter(vbox);

	}
	
	
	public void initializeButtons() {
		
		sendButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				double amount = Double.parseDouble(amountField.getText());
				int receiver = Integer.parseInt(receiverField.getText());
				System.out.println("SENDING: " + amount + " SP to " + receiver);
				int publicKey=0;
				int privateKey;
				int n_value=0;
				int message;
				FileReader filereader;
				BufferedReader buffered;
				String FILENAME = "SPAMCOIN.wlt";
				try {
					filereader = new FileReader(FILENAME);
					buffered = new BufferedReader(filereader);
					publicKey = Integer.parseInt(buffered.readLine());
					privateKey = Integer.parseInt(buffered.readLine());
					n_value = Integer.parseInt(buffered.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
				Transaction t = new Transaction(publicKey, receiver, n_value,Database.getTransactionHistory(publicKey).size()+1, amount);
				SignatureGenerator siggen = new SignatureGenerator(t.message());
				t.setSignature(siggen.signature());
				Database.receiveSpamCoin(receiver, amount);

				
				frame.getLocalNode().pushTransaction(t);
				
				
				frame.updateBalance();
				
			}
		});
		
		
	}

}
