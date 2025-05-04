package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class FormLuatController {

	@FXML
	private TableView<Luat> luatTable;
	@FXML
	private TableColumn<Luat, String> idLuatColumn;
	@FXML
	private TableColumn<Luat, String> hocLucColumn;
	@FXML
	private TableColumn<Luat, String> khoiThiColumn;
	@FXML
	private TableColumn<Luat, String> monYeuThichColumn;
	@FXML
	private TableColumn<Luat, String> soThichColumn;
	@FXML
	private TableColumn<Luat, String> nganhGoiYColumn;

	@FXML
	private TextField idLuatField;
	@FXML
	private TextField hocLucField;
	@FXML
	private TextField khoiThiField;
	@FXML
	private TextField monYeuThichField;
	@FXML
	private TextField soThichField;
	@FXML
	private TextField nganhGoiYField;

// ObservableList chứa dữ liệu bảng
	private ObservableList<Luat> luatList = FXCollections.observableArrayList();

// Initialize method to bind the table with the data
	@FXML
	private void initialize() {
		// Bind table columns to the Luat object fields
		idLuatColumn.setCellValueFactory(cellData -> cellData.getValue().IDLuatProperty());
		hocLucColumn.setCellValueFactory(cellData -> cellData.getValue().HocLucProperty());
		khoiThiColumn.setCellValueFactory(cellData -> cellData.getValue().KhoiThiProperty());
		monYeuThichColumn.setCellValueFactory(cellData -> cellData.getValue().MonYeuThichProperty());
		soThichColumn.setCellValueFactory(cellData -> cellData.getValue().SoThichProperty());
		nganhGoiYColumn.setCellValueFactory(cellData -> cellData.getValue().NganhGoiYProperty());

		// Lấy dữ liệu từ database và hiển thị vào bảng
		luatList = Luat.getLuatData();
		luatTable.setItems(luatList);
	}

// Handle "Thêm" button click
	@FXML
	private void handleAddButton(ActionEvent event) {
		// Get data from text fields
		String idLuat = idLuatField.getText();
		String hocLuc = hocLucField.getText();
		String khoiThi = khoiThiField.getText();
		String monYeuThich = monYeuThichField.getText();
		String soThich = soThichField.getText();
		String nganhGoiY = nganhGoiYField.getText();

		// Create a new Luat object
		Luat newLuat = new Luat(idLuat, hocLuc, khoiThi, monYeuThich, soThich, nganhGoiY);

		// Add the new entry to the ObservableList
		luatList.add(newLuat);

		// Thêm dữ liệu vào cơ sở dữ liệu
		Luat.addLuatToDatabase(newLuat);

		// Optionally clear the input fields after adding
		idLuatField.clear();
		hocLucField.clear();
		khoiThiField.clear();
		monYeuThichField.clear();
		soThichField.clear();
		nganhGoiYField.clear();
	}

// Handle "Cập Nhật" button click
	@FXML
	private void handleUpdateButton(ActionEvent event) {
		// Get the selected Luat from the table
		Luat selectedLuat = luatTable.getSelectionModel().getSelectedItem();

		if (selectedLuat != null) {
			// Update the selected Luat's data with the values from the text fields
			selectedLuat.setIDLuat(idLuatField.getText());
			selectedLuat.setHocLuc(hocLucField.getText());
			selectedLuat.setKhoiThi(khoiThiField.getText());
			selectedLuat.setMonYeuThich(monYeuThichField.getText());
			selectedLuat.setSoThich(soThichField.getText());
			selectedLuat.setNganhGoiY(nganhGoiYField.getText());

			// Cập nhật dữ liệu vào cơ sở dữ liệu
			Luat.updateLuatInDatabase(selectedLuat);

			// Refresh the table to reflect the updated data
			luatTable.refresh();

			// Optionally clear the input fields after updating
			idLuatField.clear();
			hocLucField.clear();
			khoiThiField.clear();
			monYeuThichField.clear();
			soThichField.clear();
			nganhGoiYField.clear();
		} else {
			// Show a message if no item is selected
			showAlert("Cập nhật không thành công", "Vui lòng chọn một bản ghi để cập nhật.");
		}
	}

// Handle "Xóa" button click
	@FXML
	private void handleDeleteButton(ActionEvent event) {
		// Get the selected Luat from the table
		Luat selectedLuat = luatTable.getSelectionModel().getSelectedItem();

		if (selectedLuat != null) {
			// Remove the selected Luat from the ObservableList
			luatList.remove(selectedLuat);

			// Xóa dữ liệu khỏi cơ sở dữ liệu
			Luat.deleteLuatFromDatabase(selectedLuat);
		} else {
			// Show a message if no item is selected
			showAlert("Xóa không thành công", "Vui lòng chọn một bản ghi để xóa.");
		}
	}

// Method to show alert messages
	private void showAlert(String title, String message) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

}
