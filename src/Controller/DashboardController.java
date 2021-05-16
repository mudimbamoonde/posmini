package Controller;


import Binder.DBConnection;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DashboardController {
    public Button btn;
    @FXML
    private ComboBox<String> comboView;

    @FXML
    private void initialize() {
        Connection sql;
        ResultSet st;
        Statement smt;
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

}
