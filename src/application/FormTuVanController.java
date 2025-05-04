package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class FormTuVanController implements Initializable {

	@FXML
	private ChoiceBox<String> choiceHocLuc;
	@FXML
	private ChoiceBox<String> choiceKhoiThi;
	@FXML
	private ChoiceBox<String> choiceMonYeuThich;
	@FXML
	private ChoiceBox<String> choiceSoThich;
	@FXML
	private TextFlow textFlowResult;
	@FXML
	private ProgressBar progressBar;
	@FXML
	private Label labelKetQua;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadChoiceBoxData();
	}

	private void loadChoiceBoxData() {
		try (Connection conn = getConnection()) {
			// Tạo danh sách không trùng lặp cho từng cột
			loadDistinctValues(conn, "HocLuc", choiceHocLuc);
			loadDistinctValues(conn, "KhoiThi", choiceKhoiThi);
			loadDistinctValues(conn, "MonYeuThich", choiceMonYeuThich);
			loadDistinctValues(conn, "SoThich", choiceSoThich);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void loadDistinctValues(Connection conn, String columnName, ChoiceBox<String> choiceBox)
			throws SQLException {
		String sql = "SELECT DISTINCT " + columnName + " FROM Luat WHERE " + columnName + " IS NOT NULL";
		ObservableList<String> values = FXCollections.observableArrayList();
		try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				values.add(rs.getString(1));
			}
		}
		choiceBox.setItems(values);
		if (!values.isEmpty())
			choiceBox.getSelectionModel().selectFirst();
	}

	@FXML
	public void handleTuVan(ActionEvent event) {
		String hocLuc = choiceHocLuc.getValue();
		String khoiThi = choiceKhoiThi.getValue();
		String monYeuThich = choiceMonYeuThich.getValue();
		String soThich = choiceSoThich.getValue();

		String ketQua = suyDienTien(hocLuc, khoiThi, monYeuThich, soThich);

		textFlowResult.getChildren().clear();
		textFlowResult.getChildren().add(new Text(ketQua));
		progressBar.setProgress(1.0);
	}

	@FXML
	public void handleHuy(ActionEvent event) {
		choiceHocLuc.getSelectionModel().clearSelection();
		choiceKhoiThi.getSelectionModel().clearSelection();
		choiceMonYeuThich.getSelectionModel().clearSelection();
		choiceSoThich.getSelectionModel().clearSelection();
		textFlowResult.getChildren().clear();
		progressBar.setProgress(0.0);
	}

	private String suyDienTien(String hocLuc, String khoiThi, String monYeuThich, String soThich) {
		String nganhGoiY = "";
		try (Connection conn = getConnection()) {
			String sql = "SELECT NganhGoiY FROM Luat WHERE HocLuc = ? AND KhoiThi = ? AND MonYeuThich = ? AND SoThich = ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, hocLuc);
				stmt.setString(2, khoiThi);
				stmt.setString(3, monYeuThich);
				stmt.setString(4, soThich);
				try (ResultSet rs = stmt.executeQuery()) {
					if (rs.next()) {
						nganhGoiY = rs.getString("NganhGoiY");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nganhGoiY.isEmpty() ? "Không tìm thấy ngành phù hợp với các tiêu chí bạn đã chọn."
				: "Ngành phù hợp với bạn là: " + nganhGoiY;
	}

	private Connection getConnection() throws SQLException {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=LuatSuyDien;encrypt=true;trustServerCertificate=true";
		String user = "sa"; // sửa lại nếu cần
		String password = "1"; // thay bằng mật khẩu thật
		return DriverManager.getConnection(url, user, password);
	}
}