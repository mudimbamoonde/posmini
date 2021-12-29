package Controller;


import Binder.DBConnection;
import Model.DBtables;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Locale;

public class DashboardController {
    Connection sqlev;
    @FXML
    private ComboBox<String> comboView;

    @FXML
    private TableView<DBtables> tbl_db;

    @FXML
    private TableColumn<DBtables, String> tbls;

    @FXML
    private TreeView<String> treeViewdb;

    TreeItem<String>  rootItem;



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

//                System.out.println(st.getString(1));

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
        smt.executeQuery("use "+value);

//        View Tables
        tbsmt = sql.createStatement();
        tb = tbsmt.executeQuery("show tables");
        rootItem = new TreeItem<>(value.toUpperCase());
        while(tb.next()){
            tbls.setCellValueFactory(new PropertyValueFactory<>("tableName"));

            tableList.add(new DBtables(tb.getString(1)));
            tbl_db.setItems(tableList);

            TreeItem<String> tables = new TreeItem<>(tb.getString(1));
            rootItem.getChildren().addAll(tables);
//            System.out.println(tb.getString(1));
        }
        treeViewdb.setRoot(rootItem);


        tbl_db.getSelectionModel().selectedItemProperty().addListener((obs, old, newSelection) -> {
            System.out.println(newSelection.getTableName());
//            tableList.clear();
            ResultSet ViewEle;
            Statement  view;


            try {
                sqlev = DBConnection.getConnection();
                view = sql.createStatement();
                view.executeQuery("use "+value);

                sqlev = DBConnection.getConnection();
                view = sqlev.createStatement();
                ViewEle = view.executeQuery("desc "  + newSelection.getTableName());
                while (ViewEle.next()){
                  System.out.println(ViewEle.getString(1));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

    }

    public void selectItem() {
    }
}
