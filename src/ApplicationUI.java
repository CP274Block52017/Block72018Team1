import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ApplicationUI extends Application{
	
	Button dashboard_btn;
	Button transactions_btn;
	Button mining_btn;
	Button setting_btn;
	Label balance;
	UIScene curr_scene;
	BorderPane border;
	StackPane root;
	
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 800;
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		border = new BorderPane();
		root = new StackPane();
		curr_scene = new UIScene(border, WIDTH, HEIGHT);
		
		int amount = 100;
		balance = new Label(amount + "Spam Coin");
		dashboard_btn = new Button("Dashboard");
		transactions_btn = new Button("Transactions");
		mining_btn = new Button("Mining");
		setting_btn = new Button("Setting");
		ToolBar toolbar = new ToolBar(
				dashboard_btn,
				transactions_btn,
				mining_btn,
				setting_btn,
				new Separator(),
				balance
				);
		
		initializeButtons(curr_scene, primaryStage);
		root.getChildren().add(toolbar);
		border.setTop(root);
		primaryStage.setTitle("Spam Coin Wallet");
		primaryStage.setResizable(true);
		primaryStage.setScene(curr_scene);
		primaryStage.show();

		
	}
	
	public void initializeButtons(UIScene curr_scene, Stage stage) {
		dashboard_btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				border.setCenter(new Dashboard_fx());
			}
		});
		
		transactions_btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				border.setCenter(new Transaction_fx());
			}
		});
		
		mining_btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				border.setCenter(new Mining_fx());
			}
		});
		
		setting_btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				border.setCenter(new Setting_fx());
			}
		});
	}		
	
	public static void main(String[] args) {
		launch(args);
	}
}
