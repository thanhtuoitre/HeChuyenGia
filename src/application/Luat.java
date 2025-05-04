package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Luat {

	private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=LuatSuyDien;encrypt=true;trustServerCertificate=true";
	private static final String USER = "sa"; // Thay thế với tên người dùng của bạn
	private static final String PASSWORD = "1"; // Thay thế với mật khẩu của bạn
	private final StringProperty IDLuat;
	private final StringProperty HocLuc;
	private final StringProperty KhoiThi;
	private final StringProperty MonYeuThich;
	private final StringProperty SoThich;
	private final StringProperty NganhGoiY;

// Kết nối đến cơ sở dữ liệu
	public static Connection connect() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

// Lấy dữ liệu từ bảng Luat
	public static ObservableList<String> getHocLucData() {
		ObservableList<String> hocLucList = FXCollections.observableArrayList();
		String sql = "SELECT DISTINCT HocLuc FROM dbo.Luat"; // Lấy tất cả giá trị Học Lực

		try (Connection connection = connect();
				PreparedStatement stmt = connection.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				hocLucList.add(rs.getString("HocLuc"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return hocLucList;
	}

	public static ObservableList<String> getKhoiThiData() {
		ObservableList<String> khoiThiList = FXCollections.observableArrayList();
		String sql = "SELECT DISTINCT KhoiThi FROM dbo.Luat"; // Lấy tất cả giá trị Khối Thi

		try (Connection connection = connect();
				PreparedStatement stmt = connection.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				khoiThiList.add(rs.getString("KhoiThi"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return khoiThiList;
	}

	public static ObservableList<String> getMonYeuThichData() {
		ObservableList<String> monYeuThichList = FXCollections.observableArrayList();
		String sql = "SELECT DISTINCT MonYeuThich FROM dbo.Luat"; // Lấy tất cả giá trị Môn Yêu Thích

		try (Connection connection = connect();
				PreparedStatement stmt = connection.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				monYeuThichList.add(rs.getString("MonYeuThich"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return monYeuThichList;
	}

	public static ObservableList<String> getSoThichData() {
		ObservableList<String> soThichList = FXCollections.observableArrayList();
		String sql = "SELECT DISTINCT SoThich FROM dbo.Luat"; // Lấy tất cả giá trị Sở Thích

		try (Connection connection = connect();
				PreparedStatement stmt = connection.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				soThichList.add(rs.getString("SoThich"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return soThichList;
	}

	public static ObservableList<String> getNganhGoiYData() {
		ObservableList<String> nganhGoiYList = FXCollections.observableArrayList();
		String sql = "SELECT DISTINCT NganhGoiY FROM dbo.Luat"; // Lấy tất cả giá trị Ngành Gợi Ý

		try (Connection connection = connect();
				PreparedStatement stmt = connection.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				nganhGoiYList.add(rs.getString("NganhGoiY"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nganhGoiYList;
	}

// Constructor
	public Luat(String IDLuat, String HocLuc, String KhoiThi, String MonYeuThich, String SoThich, String NganhGoiY) {
		this.IDLuat = new SimpleStringProperty(IDLuat);
		this.HocLuc = new SimpleStringProperty(HocLuc);
		this.KhoiThi = new SimpleStringProperty(KhoiThi);
		this.MonYeuThich = new SimpleStringProperty(MonYeuThich);
		this.SoThich = new SimpleStringProperty(SoThich);
		this.NganhGoiY = new SimpleStringProperty(NganhGoiY);
	}

// Getter and Setter for IDLuat
	public String getIDLuat() {
		return IDLuat.get();
	}

	public void setIDLuat(String IDLuat) {
		this.IDLuat.set(IDLuat);
	}

	public StringProperty IDLuatProperty() {
		return IDLuat;
	}

// Getter and Setter for HocLuc
	public String getHocLuc() {
		return HocLuc.get();
	}

	public void setHocLuc(String HocLuc) {
		this.HocLuc.set(HocLuc);
	}

	public StringProperty HocLucProperty() {
		return HocLuc;
	}

// Getter and Setter for KhoiThi
	public String getKhoiThi() {
		return KhoiThi.get();
	}

	public void setKhoiThi(String KhoiThi) {
		this.KhoiThi.set(KhoiThi);
	}

	public StringProperty KhoiThiProperty() {
		return KhoiThi;
	}

// Getter and Setter for MonYeuThich
	public String getMonYeuThich() {
		return MonYeuThich.get();
	}

	public void setMonYeuThich(String MonYeuThich) {
		this.MonYeuThich.set(MonYeuThich);
	}

	public StringProperty MonYeuThichProperty() {
		return MonYeuThich;
	}

// Getter and Setter for SoThich
	public String getSoThich() {
		return SoThich.get();
	}

	public void setSoThich(String SoThich) {
		this.SoThich.set(SoThich);
	}

	public StringProperty SoThichProperty() {
		return SoThich;
	}

// Getter and Setter for NganhGoiY
	public String getNganhGoiY() {
		return NganhGoiY.get();
	}

	public void setNganhGoiY(String NganhGoiY) {
		this.NganhGoiY.set(NganhGoiY);
	}

	public StringProperty NganhGoiYProperty() {
		return NganhGoiY;
	}

// Lấy dữ liệu từ bảng Luat
	public static ObservableList<Luat> getLuatData() {
		ObservableList<Luat> luatList = FXCollections.observableArrayList();

		// Câu lệnh SQL để lấy dữ liệu từ bảng Luat
		String query = "SELECT * FROM dbo.Luat"; // Đảm bảo tên bảng và cột chính xác

		// Thực hiện kết nối và truy vấn cơ sở dữ liệu
		try (Connection connection = connect();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query)) {

			// Lặp qua các kết quả và tạo các đối tượng Luat
			while (resultSet.next()) {
				String idLuat = resultSet.getString("IDLuat");
				String hocLuc = resultSet.getString("HocLuc");
				String khoiThi = resultSet.getString("KhoiThi");
				String monYeuThich = resultSet.getString("MonYeuThich");
				String soThich = resultSet.getString("SoThich");
				String nganhGoiY = resultSet.getString("NganhGoiY");

				// Tạo đối tượng Luat và thêm vào danh sách
				Luat luat = new Luat(idLuat, hocLuc, khoiThi, monYeuThich, soThich, nganhGoiY);
				luatList.add(luat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Trả về danh sách chứa các đối tượng Luat
		return luatList;
	}

	public static void addLuatToDatabase(Luat luat) {
		String sql = "INSERT INTO dbo.Luat (IDLuat, HocLuc, KhoiThi, MonYeuThich, SoThich, NganhGoiY) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, luat.getIDLuat());
			stmt.setString(2, luat.getHocLuc());
			stmt.setString(3, luat.getKhoiThi());
			stmt.setString(4, luat.getMonYeuThich());
			stmt.setString(5, luat.getSoThich());
			stmt.setString(6, luat.getNganhGoiY());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// Cập nhật luật trong cơ sở dữ liệu (dựa theo IDLuat)
	public static void updateLuatInDatabase(Luat luat) {
		String sql = "UPDATE dbo.Luat SET HocLuc = ?, KhoiThi = ?, MonYeuThich = ?, SoThich = ?, NganhGoiY = ? WHERE IDLuat = ?";

		try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, luat.getHocLuc());
			stmt.setString(2, luat.getKhoiThi());
			stmt.setString(3, luat.getMonYeuThich());
			stmt.setString(4, luat.getSoThich());
			stmt.setString(5, luat.getNganhGoiY());
			stmt.setString(6, luat.getIDLuat());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// Xóa luật khỏi cơ sở dữ liệu (dựa theo IDLuat)
	public static void deleteLuatFromDatabase(Luat luat) {
		String sql = "DELETE FROM dbo.Luat WHERE IDLuat = ?";

		try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, luat.getIDLuat());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
