
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Setting_fx extends BorderPane implements PaneState {

	private Button showPrivate;
	private ApplicationUI frame;
	private VBox vbox, vbox2;
	private BorderPane main = this;
	
	public Setting_fx(ApplicationUI _frame) {
		frame = _frame;
		vbox = new VBox();
		vbox2 = new VBox();
		addComponents();
		initializeButton();
	}
	
	
	@Override
	public void addComponents() {
		showPrivate = new Button("See my Private Key");
		vbox.getChildren().add(showPrivate);
		this.setCenter(vbox);
		vbox.setAlignment(Pos.CENTER);
	}
	
	public void initializeButton() {
		Label privateKey = new Label("" + frame.getLocalNode().getPrivateKey());
		vbox2.getChildren().add(privateKey);
		showPrivate.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				main.setCenter(vbox2);
				vbox2.setAlignment(Pos.CENTER);
			}
		});
	}

}
