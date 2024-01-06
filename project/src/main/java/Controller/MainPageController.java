package Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
