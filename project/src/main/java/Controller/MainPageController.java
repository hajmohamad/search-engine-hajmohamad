package Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    @FXML
    private TextField tf_search;

    @FXML
    private ListView<String> lv_searchContent;

    @FXML
    private ScrollPane sp_searchContent;







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lv_searchContent.setVisible(false);

        tf_search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
                lv_searchContent.getItems().clear();
                if(tf_search.getText().length()==0){
                    lv_searchContent.setVisible(false);
                }else{
                    lv_searchContent.setVisible(true);
                }
//                for(String s:ReadFromDataBase.findContentForMainSearch(tf_search.getText())){
//                    lv_searchContent.getItems().add(s);
//
//                }





            }
        });

    }

}
