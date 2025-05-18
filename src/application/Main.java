package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Tải FXML của FormDangKy (hoặc FormDangNhap)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FormDangNhap.fxml"));
            GridPane root = loader.load();  

            // Tạo Scene và thiết lập cho Stage
            Scene scene = new Scene(root, 608, 268);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Hệ chuyên gia tư vấn ");

            // Hiển thị Stage
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args); // Khởi chạy ứng dụng JavaFX
    }
}
