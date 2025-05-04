package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Luat {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=LuatSuyDien;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa"; // Thay thế với tên người dùng của bạn
    private static final String PASSWORD = "1"; // Thay thế với mật khẩu của bạn

    // Kết nối đến cơ sở dữ liệu
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Lấy dữ liệu từ bảng Luat
    public static ObservableList<String> getHocLucData() {
        ObservableList<String> hocLucList = FXCollections.observableArrayList();
        String sql = "SELECT DISTINCT HocLuc FROM dbo.Luat";  // Lấy tất cả giá trị Học Lực

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
        String sql = "SELECT DISTINCT KhoiThi FROM dbo.Luat";  // Lấy tất cả giá trị Khối Thi

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
        String sql = "SELECT DISTINCT MonYeuThich FROM dbo.Luat";  // Lấy tất cả giá trị Môn Yêu Thích

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
        String sql = "SELECT DISTINCT SoThich FROM dbo.Luat";  // Lấy tất cả giá trị Sở Thích

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
        String sql = "SELECT DISTINCT NganhGoiY FROM dbo.Luat";  // Lấy tất cả giá trị Ngành Gợi Ý

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
}
