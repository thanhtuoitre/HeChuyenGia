package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FormDangKyController {

	@FXML
	private TextField txtTaiKhoan, txtHoTen;
	@FXML
	private RadioButton rbNam, rbNu;
	@FXML
	private DatePicker datePickerNgaySinh;
	@FXML
	private PasswordField txtMatKhau;
	@FXML
	private CheckBox chkHienThi;
	@FXML
	private Button btnDangKy;
	@FXML
	private Label lblLoi;
	@FXML
	private Hyperlink linkChuyenLogin;

	private final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=LuatSuyDien;encrypt=false;";
	private static final String DB_USER = "sa"; // Cập nhật thông tin đăng nhập CSDL
	private static final String DB_PASSWORD = "1"; // Cập nhật mật khẩu CSDL

	@FXML
	private void dangKy() {
		String taiKhoan = txtTaiKhoan.getText().trim();
		String hoTen = txtHoTen.getText().trim();
		String matKhau = txtMatKhau.getText().trim();
		String gioiTinh = rbNam.isSelected() ? "Nam" : "Nữ";
		String ngaySinh = datePickerNgaySinh.getValue() != null ? datePickerNgaySinh.getValue().toString() : "";
		String vaiTro = "User";

		if (taiKhoan.isEmpty() || hoTen.isEmpty() || matKhau.isEmpty() || ngaySinh.isEmpty()) {
			lblLoi.setText("Vui lòng điền đầy đủ thông tin.");
			lblLoi.setVisible(true);
			return;
		}

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
			String sql = "INSERT INTO dbo.NguoiDung (TaiKhoan, HoTen, MatKhau, VaiTro) VALUES (?, ?, ?, ?)";
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, taiKhoan);
				pstmt.setString(2, hoTen);
				pstmt.setString(3, matKhau);
				pstmt.setString(4, vaiTro);

				int rowsAffected = pstmt.executeUpdate();
				if (rowsAffected > 0) {
					lblLoi.setText("Đăng ký thành công!");
					lblLoi.setTextFill(javafx.scene.paint.Color.GREEN);
					lblLoi.setVisible(true);

					// ✅ CHUYỂN MÀN TẠI ĐÂY
					chuyenLogin();
				} else {
					lblLoi.setText("Đã xảy ra lỗi khi đăng ký.");
					lblLoi.setTextFill(javafx.scene.paint.Color.RED);
					lblLoi.setVisible(true);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			lblLoi.setText("Lỗi kết nối cơ sở dữ liệu.");
			lblLoi.setTextFill(javafx.scene.paint.Color.RED);
			lblLoi.setVisible(true);
		}
	}

	@FXML
	private void chuyenLogin() {
		try {
			// Tải FXML của FormHome
			FXMLLoader loader = new FXMLLoader(getClass().getResource("FormDangNhap.fxml"));
			Scene homeScene = new Scene(loader.load());

			// Lấy stage hiện tại
			Stage currentStage = (Stage) linkChuyenLogin.getScene().getWindow();
			currentStage.setScene(homeScene); // Đổi scene của cửa sổ hiện tại

			// Hiển thị lại stage
			currentStage.show();
		} catch (Exception e) {
			e.printStackTrace();
			// Nếu có lỗi, hiển thị cảnh báo
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể chuyển tới màn hình chính");
			alert.setContentText("Đã xảy ra lỗi trong quá trình chuyển màn hình.");
			alert.showAndWait();
		}
	}


	@FXML
	private void togglePasswordVisibility() {
		if (chkHienThi.isSelected()) {
			txtMatKhau.setVisible(false); // Hiển thị mật khẩu
		} else {
			txtMatKhau.setVisible(true); // Ẩn mật khẩu
		}
	}
}
