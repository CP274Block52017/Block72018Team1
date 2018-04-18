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
		for(Transaction transaction : Database.getTransactionHistory()) {
			transactions += transaction + "\n";
		}
		//System.out.println(transactions);
		verified = new Label("Verified"+"\n"+transactions);
		VBox vbox = new VBox();
		vbox.getChildren().add(verified);
		this.setCenter(vbox);
		vbox.setAlignment(Pos.CENTER);
	}

}
