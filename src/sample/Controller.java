package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller{
    @FXML
    private Button hlo;

    @FXML
    private VBox hlobox;

    @FXML
    private Label hlolabel;


    private int i=0;

    private  String pass;


//    Controller(){
//        i=0;
//    }
    @FXML
    void hlo(ActionEvent event) {
        System.out.println("dfghjk");
//        Integer t = i++;
//
//        hlobox.getChildren().add(new Button(t.toString()));
        FileChooser fil_chooser = new FileChooser();
        File file = fil_chooser.showOpenDialog(new Stage());

        if (file != null) {

            System.out.println(file.getAbsolutePath());
            pass=file.getAbsolutePath();
            //File file = new File("/System/Library/CoreServices/loginwindow.app/Contents/Resources/LogOut.png");
            Image image = new Image(file.toURI().toString());
            ImageView iv = new ImageView(image);
            iv.setFitHeight(100);
            iv.setFitWidth(100);
            hlobox.getChildren().add(iv);

//            label.setText(file.getAbsolutePath()
//                    + "  selected");
        }
    }

    @FXML
    void clk(KeyEvent event) {
        System.out.println(pass);
    }




}
