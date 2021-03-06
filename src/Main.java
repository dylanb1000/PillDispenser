
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
	Model model = new Model();
	Controller controller;

	@Override
	public void start(Stage primaryStage) throws IOException {
		try {
			this.model = WriterReader.readObjectFromFile(new File(System.getProperty("user.dir") + "/data.ser"));
			System.out.println("Data read successfully");
		} catch (Exception e) {
			System.out.println("Not able to read data file");
		}
		this.controller = new Controller(model);

		final FXMLLoader mainView = new FXMLLoader();
		mainView.setLocation(getClass().getResource("/resources/MainView.fxml"));
		mainView.setController(controller);

		GridPane grid = mainView.load();
		Scene scene = new Scene(grid);
		primaryStage.setTitle("Pill Dispenser");
		primaryStage.getIcons().add(new Image("resources/main_icon.png"));
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		primaryStage.show();
	}

	@Override
	public void stop() {
		try {
			WriterReader.writeObjectToFile(this.model, new File(System.getProperty("user.dir") + "/data.ser"));
			System.out.println("Data saved successfully");
			controller.stopThread();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Unable to save file.");
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
//notes
/*-fix combobox to allow scrolling on pi
 *-enlarge info text
 *-*/



