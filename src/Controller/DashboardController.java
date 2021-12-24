package Controller;


import Binder.DBConnection;
import Model.DBtables;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DashboardController {
    public Button btn;
    @FXML
    private ComboBox<String> comboView;

    @FXML
    private TableView<DBtables> tbl_db;

    @FXML
    private TableColumn<DBtables, String> tbls;


    ObservableList<DBtables> tableList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        Connection sql;
        ResultSet st;
        Statement smt;
        tableList.clear();
        try{
            sql = DBConnection.getConnection();
            smt = sql.createStatement();
            st = smt.executeQuery("Show Databases");
            while(st.next()){
                comboView.getItems().addAll(FXCollections.observableArrayList(st.getString(1)));

                System.out.println(st.getString(1));
            }
        }catch (Exception exql){
            System.out.println("Error:" + exql.toString());
        }

    }

    public void getSelected(ActionEvent actionEvent) throws Exception{
        String value = comboView.getSelectionModel().getSelectedItem().toString();
        Connection sql;
        ResultSet st,tb;
        Statement smt,tbsmt;

        tableList.clear();

        sql = DBConnection.getConnection();
        smt = sql.createStatement();
        st = smt.executeQuery("use "+value);

//        View Tables
        tbsmt = sql.createStatement();
        tb = tbsmt.executeQuery("show tables");

        while(tb.next()){
            tbls.setCellValueFactory(new PropertyValueFactory<>("tableName"));

            tableList.add(new DBtables(tb.getString(1)));
            tbl_db.setItems(tableList);

            System.out.println(tb.getString(1));
        }

    }

}
