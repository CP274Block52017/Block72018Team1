import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Dashboard_fx extends BorderPane implements PaneState {
	
	private ApplicationUI frame;
	private Label verified;
	private Label title;
	
	private final static Font DEFAULT_FONT = new Font("Aspergit", 20);
	
	public Dashboard_fx(ApplicationUI _frame) {
		frame = _frame;
		addComponents();
	}
	@Override
	public void addComponents() {
		String transactions = "";
		/*
		for(Transaction transaction : frame.getLocalNode().getLocalChain().getAllTransactions()) {
			transactions += transaction + "\n";
		}
		*/
		for(String transaction : Database.getTransactionHistory(frame.getLocalNode().getPublicKey())) {
			transactions += transaction + "\n ";  
		}
		//System.out.println(transactions);
		title = new Label("Dashboard");
		title.setFont(ApplicationUI.TITLE_FONT);
		verified = new Label(" Verified"+"\n "+transactions);
		verified.setFont(DEFAULT_FONT);
		
		VBox vbox = new VBox();
		vbox.getChildren().add(title);
		vbox.getChildren().add(new Separator());
		vbox.getChildren().add(verified);
		vbox.setSpacing(20);
		this.setCenter(vbox);
//		vbox.setAlignment(Pos.CENTER);
	}

}
