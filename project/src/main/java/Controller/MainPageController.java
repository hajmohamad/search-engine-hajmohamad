package Controller;

import com.example.project.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    @FXML
    private TextField tf_search;

    @FXML
    private ListView<String> lv_searchContent;

    @FXML
    private ScrollPane sp_searchContent;
    @FXML
    void searchIt(MouseEvent event) {


    }


    @FXML
    private AnchorPane ap_main;

    @FXML
    void searching(MouseEvent event)  {
        System.out.println("hi");
        SearchPageController.sp_image=false;
        try {
        SearchPageController.searchInput=tf_search.getText();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("searchPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1150, 600);
        Main.orginalStage.setTitle("Hello!");
        Main.orginalStage.setScene(scene);
        Main.orginalStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lv_searchContent.setVisible(false);



        lv_searchContent.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            tf_search.setText(newValue);
            }
        });

        tf_search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
                lv_searchContent.getItems().clear();
                if(tf_search.getText().length()==0){
                    lv_searchContent.setVisible(false);
                }else{
                   lv_searchContent.setVisible(true);
                }
                for(String s:ReadFromDataBase.findContentForMainSearch(tf_search.getText())){
                    lv_searchContent.getItems().add(s);

                }




            }
        });

    }

}
