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
	
	public NewUser_fx(ApplicationUI _frame) {
		frame = _frame;
		start = new Button("START");
		label = new Label("Your ID has been generated:");
		id = new Label(IDGenerator.generateRandomID());
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
				int[] keyPair = new KeyPairGenerator().generateNext();
				Node localNode = new Node(keyPair[0], keyPair[1]);
				localNode.addToNetwork(ApplicationUI.GLOBAL_NETWORK);
				frame.initializeLocalNode(localNode);
				frame.setDashboardTemplate();
				
			}
		});
	}

}
