
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class InitialPage_fx extends BorderPane implements PaneState {
	private Button joinButton;
	private Button loginButton;
	
	public InitialPage_fx() {
		joinButton = new Button("Join");
		loginButton = new Button("login");
	}
	
	public void addComponents() {
		VBox vbox = new VBox();
		
		vbox.getChildren().add(joinButton);
		vbox.getChildren().add(loginButton);
		
		this.setCenter(vbox);
	}
	
	public void initializeButtons() {
		joinButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("JOIN");
			}
		});
		
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Login");
			}
		});
	}

}
