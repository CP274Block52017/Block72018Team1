import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class NewUser_fx extends BorderPane implements PaneState {
	private Label label;
	private Button start;
	private ApplicationUI frame;
	private Label id;
	private VBox vbox;
	private final static int WIDTH = 130;
	private final static int HEIGHT = 40;
	
	public NewUser_fx(ApplicationUI _frame) {
		frame = _frame;
		start = new Button("START");
		start.setFont(new Font("Aspergit", 20));
		start.setPrefSize(WIDTH, HEIGHT);
		label = new Label("Your Public Key has been generated:");
		label.setFont(new Font("Aspergit", 50));
		id = new Label(((Integer)frame.getLocalNode().getPublicKey()).toString());
		id.setFont(new Font("Aspergit", 50));
		vbox = new VBox();
		addComponents();
		initializeButtons();
	}
	
	public void addComponents() {
		
		
		vbox.getChildren().add(label);
		vbox.getChildren().add(id);
		vbox.getChildren().add(start);
		this.setCenter(vbox);
		vbox.setAlignment(Pos.CENTER);
		
	}
	
	public void initializeButtons() {
		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				vbox.getChildren().clear();
				frame.setDashboardTemplate();
				
			}
		});
	}

}
