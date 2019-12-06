package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.ImageFrame;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class Controller{
    @FXML
    private Button hlo;

    @FXML
    private VBox big_img;

    @FXML
    private ListView<String> Image_Info;

    @FXML
    private VBox hlobox;

    @FXML
    private Label hlolabel;

    @FXML
    private Button delateImage;

    ArrayList<ImageView> imgList = new ArrayList<>();


    private int i=0;

    //private  String pass;

    private ImageFrame imageFrame =new ImageFrame();

    ImageView currentImg;


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
            //pass=file.getAbsolutePath();
            //File file = new File("/System/Library/CoreServices/loginwindow.app/Contents/Resources/LogOut.png");
            Image image = new Image(file.toURI().toString());
            ImageView iv = new ImageView(image);
            imgList.add(iv);
            System.out.println(hlo.getParent());
            models.Image img = new models.Image(file.getAbsolutePath());
            imageFrame.append(img);
            iv.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println("123");
                    big_img.getChildren().clear();
                    ImageView vi = new ImageView((image));


                    big_img.getChildren().add(vi);

                    vi.setFitHeight(300);
                    vi.setPreserveRatio(true);
                    currentImg = iv;

                    Image_Info.getItems().clear();
                    TreeMap<String,String> exif = img.getAttributes();
                    exif.forEach((k,v)->{
                        Image_Info.getItems().add(k+":"+v);
                    });
                    System.out.print(exif);

                }
            });
            iv.setFitHeight(100);
            iv.setFitWidth(100);
            hlobox.getChildren().add(iv);

//            label.setText(file.getAbsolutePath()
//                    + "  selected");
        }
    }

    @FXML
    void clk(KeyEvent event) {
        //System.out.println(pass);
    }



    @FXML
    void delImage(ActionEvent event) {
        imgList.remove(currentImg);
        hlobox.getChildren().remove(currentImg);
        big_img.getChildren().clear();
        Image_Info.getItems().clear();

    }


}
