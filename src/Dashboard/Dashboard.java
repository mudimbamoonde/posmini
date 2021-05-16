package Dashboard;

import Binder.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Dashboard{
    public static void main(String[] args){
        Connection con;
        Statement smt;
        ResultSet st;
        try {
            con = DBConnection.getConnection();
//            smt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            smt = con.createStatement();
            st = smt.executeQuery("Show databases");
//          st = smt.executeQuery("SELECT*FROM category");

//            System.out.println("Connection Successfull");
//            System.out.println("Category ID \t\tCategory Name");
//            System.out.println("===============\t\t=============");

            while(st.next()) {
//                System.out.println(st.getInt("catID") +"\t\t\t\t\t" + st.getString("catName"));
                System.out.println(st.getString(1));
            }
            //catID | catName
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
