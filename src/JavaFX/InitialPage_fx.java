
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class InitialPage_fx extends BorderPane implements PaneState {
	private Button joinButton;
	private ApplicationUI frame;
	
	public InitialPage_fx(ApplicationUI _frame) {
		frame = _frame;
		
		joinButton = new Button("Join");
		addComponents();
		initializeButtons();
		
	}
	
	public void addComponents() {
		VBox vbox = new VBox();
		
		vbox.getChildren().add(joinButton);
		
		this.setCenter(vbox);
		this.setCenter(joinButton);
	}
	
	public void initializeButtons() {
		NewUser_fx page = new NewUser_fx(frame);
		joinButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				frame.setPage(page);
			}
		});
	}

}
