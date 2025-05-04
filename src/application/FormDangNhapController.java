package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FormDangNhapController {

	@FXML
	private TextField txtTaiKhoan;

	@FXML
	private PasswordField txtMatKhau;

	@FXML
	private TextField txtShowMatKhau;

	@FXML
	private CheckBox chkHienThi;

	@FXML
	private Label lblLoi;

	@FXML
	private Hyperlink linkChuyenManChinh;

	@FXML
	private Button btnDangNhap;

	@FXML
	private Button btnDangKy;

	// Thông tin kết nối cơ sở dữ liệu SQL Server
	private final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=LuatSuyDien;encrypt=true;trustServerCertificate=true"; 
	private final String DB_USER = "sa"; // Tên người dùng SQL Server
	private final String DB_PASSWORD = "1"; // Mật khẩu của người dùng SQL Server

	// Phương thức xử lý đăng nhập
	@FXML
	private void dangNhap() {
		String taiKhoan = txtTaiKhoan.getText();
		String matKhau = txtMatKhau.getText();

		// Kiểm tra tài khoản và mật khẩu không trống
		if (taiKhoan.isEmpty() || matKhau.isEmpty()) {
			lblLoi.setText("Tài khoản hoặc mật khẩu không được để trống!");
			lblLoi.setVisible(true);
		} else {
			// Kiểm tra tài khoản và mật khẩu với cơ sở dữ liệu
			if (kiemTraDangNhap(taiKhoan, matKhau)) {
				lblLoi.setVisible(false);
				System.out.println("Đăng nhập thành công!");
				chuyenManChinh();
			} else {
				lblLoi.setText("Tài khoản hoặc mật khẩu không đúng!");
				lblLoi.setVisible(true);
			}
		}
	}

	// Phương thức kiểm tra tài khoản và mật khẩu trong cơ sở dữ liệu
	private boolean kiemTraDangNhap(String taiKhoan, String matKhau) {
		boolean ketQua = false;
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
			String sql = "SELECT * FROM dbo.NguoiDung WHERE TaiKhoan = ? AND MatKhau = ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, taiKhoan);
				stmt.setString(2, matKhau);
				try (ResultSet rs = stmt.executeQuery()) {
					if (rs.next()) {
						// Tìm thấy tài khoản và mật khẩu đúng
						ketQua = true;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	// Phương thức xử lý đăng ký
	@FXML
	private void dangKy() {
		// Chuyển sang màn hình đăng ký (ví dụ)
		System.out.println("Chuyển đến màn hình đăng ký");
	}

	// Phương thức xử lý chuyển đến màn hình chính
	@FXML
	private void chuyenManChinh() {
		try {
			// Tải FXML của FormHome
			FXMLLoader loader = new FXMLLoader(getClass().getResource("FormHome.fxml"));
			Scene homeScene = new Scene(loader.load());

			// Lấy stage hiện tại
			Stage currentStage = (Stage) linkChuyenManChinh.getScene().getWindow();
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


	// Phương thức xử lý checkbox hiển thị mật khẩu
	@FXML
	private void togglePasswordVisibility() {
		if (chkHienThi.isSelected()) {
			txtMatKhau.setVisible(false);
			txtShowMatKhau.setText(txtMatKhau.getText());
			txtShowMatKhau.setVisible(true);
		} else {
			txtShowMatKhau.setVisible(false);
			txtMatKhau.setVisible(true);
		}
	}
}
