import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	private Label unverified;
	
	public Dashboard_fx(ApplicationUI _frame) {
		frame = _frame;
		addComponents();
	}
	@Override
	public void addComponents() {
		String transactions = "";
		for(Transaction transaction : frame.getLocalNode().getLocalChain().getAllTransactions()) {
			transactions += transaction + "\n";
		}
		System.out.println(transactions);

	}

}
