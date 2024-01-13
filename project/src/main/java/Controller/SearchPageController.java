package Controller;

import com.example.project.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import Controller.findImage.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;

public class SearchPageController  implements Initializable {
    static String searchInput;
    static boolean sp_image;


    @FXML
    private AnchorPane ap_1;

    @FXML
    private GridPane gp_image;

    @FXML
    private ImageView imageView;

    @FXML
    private ListView<String> lv_searchContent;

    @FXML
    private Label siteNumber;

    @FXML
    private ScrollPane sp_images;

    @FXML
    private ScrollPane sp_txts;

    @FXML
    private TextField tf_search;

    @FXML
    private VBox vbox_resault;


    @FXML
    void camera(MouseEvent event) {
        if(sp_image){
            sp_image=false;
        }else{
            sp_image=true;
        }
        searchIt(event);




    }

    @FXML
    void searchIt(MouseEvent event) {
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
        tf_search.setText(searchInput);
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

                for(String z:ReadFromDataBase.findContentForMainSearch(tf_search.getText())){
                    lv_searchContent.getItems().add(z);

                }




            }
        });





        vbox_resault.setStyle("-fx-background-color: transparent");
        //find image and display it
        if(sp_image){
            sp_images.setVisible(true);
            sp_txts.setVisible(false);
        lv_searchContent.setVisible(false);
        System.out.println(searchInput);
        ArrayList<String> imageLinke=findImage.getLink(searchInput)  ;
        System.out.println("linkedFind"+imageLinke.size());
        for(int i=0;i<imageLinke.size();i++) {
            ImageView A=new ImageView(new Image(imageLinke.get(i)));
            A.setFitHeight(339);
            A.setFitWidth(270);
            gp_image.add(A,i%4,i/4);

        }
        }else{
        //find text and display it
            sp_images.setVisible(false);
            sp_txts.setVisible(true);
        Set<String> siteArray = ReadFromDataBase.doAndOrNot(searchInput);
        for(String nameFile:siteArray) {
            siteNumber.setText(String.valueOf(siteArray.size()));
            Label fileNameLable=new Label();
            fileNameLable.setId("fileNameLable");
            fileNameLable.getStylesheets().add("searchPage.css");
            fileNameLable.setText(nameFile);
            Label siteNameLable=new Label();
            siteNameLable.setId("siteNameLable");
            siteNameLable.getStylesheets().add("searchPage.css");
            Label decriptionTxtArea=new Label();
            decriptionTxtArea.setId("decriptionTxtArea");
            decriptionTxtArea.getStylesheets().add("searchPage.css");
            decriptionTxtArea.setText(ReadFromDataBase.getStringFronFile(nameFile));
            siteNameLable.setText(ReadFromDataBase.findNameForFile(searchInput,decriptionTxtArea.getText()));

            decriptionTxtArea.setWrapText(true);
            VBox ap=new VBox();
            ap.prefHeight(300);
            ap.setId("ap");
            ap.getStylesheets().add("searchPage.css");
           ap.getChildren().add(siteNameLable);
            ap.getChildren().add(fileNameLable);
            ap.getChildren().add(decriptionTxtArea);
            vbox_resault.getChildren().add(ap);


        }
        }



    }
}
