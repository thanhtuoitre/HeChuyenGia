package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FormHomeController {

    @FXML
    private void moTuVan(ActionEvent event) {
        try {
            // Mở Form Tư Vấn
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FormTuVan.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Tư Vấn");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi khi mở Tư Vấn");
        }
    }

    @FXML
    private void moLuat(ActionEvent event) {
        try {
            // Mở Form Luật
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FormLuat.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Luật");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi khi mở Luật");
        }
    }

    @FXML
    private void dangXuat(ActionEvent event) {
        try {
            // Đóng cửa sổ hiện tại (Form Home) khi đăng xuất
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.close();
            System.out.println("Đăng Xuất");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi khi đăng xuất");
        }
    }
}