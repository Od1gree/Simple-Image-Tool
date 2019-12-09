package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.*;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class Controller {


    /**
     * creat a VBox for image, fxid:big_img
     */
    @FXML
    private VBox big_img;

    /**
     * Creat "Load Image" button, the fxid: hlo
     */
    @FXML
    private Button hlo;

    /**
     * Creat a list to show the information of Image, Fxid:Image_Info
     */
    @FXML
    private ListView<String> Image_Info;

    /**
     * Creat a Button "Delete this Image", Fxid:deleteImage
     */
    @FXML
    private Button deleteImage;

    /**
     *Creat a Button "Save", Fxid:fxsave
     */
    @FXML
    private Button fxsave;


    /**
     * Creat a VBox to show image thumbnails, fxid:hlobox
     */
    @FXML
    private VBox hlobox;


    /**
     *Creat a RadioButton "Gray Filter1", fxid:fxGfilter1
     */
    @FXML
    private RadioButton fxGfilter1;
    /**
     *Creat a RadioButton "Gray Filter2", fxid:fxGfilter2
     */
    @FXML
    private RadioButton fxGfilter2;
    /**
     *Creat a RadioButton "Gray Filter3", fxid:fxGfilter3
     */
    @FXML
    private RadioButton fxGfilter3;

    /**
     *Creat a RadioButton "Color Filter1", fxid:fxCfilter1
     */
    @FXML
    private RadioButton fxCfilter1;

    /**
     *Creat a RadioButton "Color Filter2", fxid:fxCfilter2
     */
    @FXML
    private RadioButton fxCfilter2;

    /**
     *Creat a RadioButton "Color Filter3", fxid:fxCfilter3
     */
    @FXML
    private RadioButton fxCfilter3;


    /**
     *Creat a RadioButton "JPEG", fxid:fxJpeg
     */
    @FXML
    private RadioButton fxJpeg;

    /**
     *Creat a RadioButton "GIF", fxid:fxGif
     */
    @FXML
    private RadioButton fxGif;

    /**
     *Creat a RadioButton "PNG", fxid:fxPNG
     */
    @FXML
    private RadioButton fxPNG;

    /**
     * To Store the data for thumbnails.
     */
    ArrayList<ImageView> imgList = new ArrayList<>();

    /**
     * To store the data of the image that will be saved
     */
    private ImageFrame imageFrame =new ImageFrame();
    /**
     * To save path of images
     */
    private ArrayList<String> arrayList = new ArrayList<>();

    ImageView currentImg;

    /**
     * creat the function of loading image
     * @param event event
     */
    @FXML
    void hlo(ActionEvent event) {
        FileChooser fil_chooser = new FileChooser();
        File file = fil_chooser.showOpenDialog(new Stage());

        if (file != null) {
            arrayList.add(file.getAbsolutePath());
            System.out.println(file.getAbsolutePath());
            Image image = new Image(file.toURI().toString());
            ImageView iv = new ImageView(image);
            imgList.add(iv);
            System.out.println(hlo.getParent());
            models.Image img = new models.Image(file.getAbsolutePath());
            imageFrame.append(img);
            iv.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
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
        }
    }

    /**
     * implement delete image function
     * @param event event
     */
    @FXML
    void delImage(ActionEvent event) {
        imgList.remove(currentImg);
        hlobox.getChildren().remove(currentImg);
        big_img.getChildren().clear();
        Image_Info.getItems().clear();

    }

    /**
     *convert image to jpeg format
     * @param event
     */
    @FXML
    void jpeg(ActionEvent event) {
        if(fxPNG.isSelected()){
        fxPNG.fire();}
        else if(fxGif.isSelected()){
            fxGif.fire();
        }


        System.out.println("21");
    }

    /**
     *convert image to png format
     * @param event
     */
    @FXML
    void png(ActionEvent event) {
        if(fxJpeg.isSelected()){
            fxJpeg.fire();
        }
        else if(fxGif.isSelected()){
            fxGif.fire();
        }

    }

    /**
     *convert image to gif format
     * @param event
     */
    @FXML
    void gif(ActionEvent event) {
        if(fxJpeg.isSelected()){
            fxJpeg.fire();
        }
        else if(fxPNG.isSelected()){
            fxPNG.fire();
        }

    }

    /**
     * implement color filter 1
     * @param event event
     */
    @FXML
    void cf1(ActionEvent event) {
        if(fxCfilter2.isSelected()){
            fxCfilter2.fire();
        }
        if(fxCfilter3.isSelected()){
            fxCfilter3.fire();
        }

        if(fxGfilter1.isSelected()){
            fxGfilter1.fire();
        }

        if(fxGfilter2.isSelected()){
            fxGfilter2.fire();
        }
        if(fxGfilter3.isSelected()){
            fxGfilter3.fire();
        }

    }

    /**
     *implement color filter 2
     * @param event
     */
    @FXML
    void cf2(ActionEvent event) {
        if(fxCfilter1.isSelected()){
            fxCfilter1.fire();
        }
        if(fxCfilter3.isSelected()){
            fxCfilter3.fire();
        }

        if(fxGfilter1.isSelected()){
            fxGfilter1.fire();
        }

        if(fxGfilter2.isSelected()){
            fxGfilter2.fire();
        }
        if(fxGfilter3.isSelected()){
            fxGfilter3.fire();
        }

    }

    /**
     *implement color filter3
     * @param event
     */

    @FXML
    void cf3(ActionEvent event) {
        if(fxCfilter2.isSelected()){
            fxCfilter2.fire();
        }
        if(fxCfilter1.isSelected()){
            fxCfilter1.fire();
        }

        if(fxGfilter1.isSelected()){
            fxGfilter1.fire();
        }

        if(fxGfilter2.isSelected()){
            fxGfilter2.fire();
        }
        if(fxGfilter3.isSelected()){
            fxGfilter3.fire();
        }

    }

    /**
     *implement gray filter1
     * @param event event
     */

    @FXML
    void gf1(ActionEvent event) {
        if(fxCfilter2.isSelected()){
            fxCfilter2.fire();
        }
        if(fxCfilter3.isSelected()){
            fxCfilter3.fire();
        }

        if(fxCfilter1 .isSelected()){
            fxCfilter1.fire();
        }

        if(fxGfilter2.isSelected()){
            fxGfilter2.fire();
        }
        if(fxGfilter3.isSelected()){
            fxGfilter3.fire();
        }

    }

    /**
     *implement gray filter2
     * @param event event
     */

    @FXML
    void gf2(ActionEvent event) {
        if(fxCfilter2.isSelected()){
            fxCfilter2.fire();
        }
        if(fxCfilter3.isSelected()){
            fxCfilter3.fire();
        }

        if(fxGfilter1.isSelected()){
            fxGfilter1.fire();
        }

        if(fxCfilter1.isSelected()){
            fxCfilter1.fire();
        }
        if(fxGfilter3.isSelected()){
            fxGfilter3.fire();
        }

    }

    /**
     *implement gray filter3
     * @param event event
     */

    @FXML
    void gf3(ActionEvent event) {
        if(fxCfilter2.isSelected()){
            fxCfilter2.fire();
        }
        if(fxCfilter3.isSelected()){
            fxCfilter3.fire();
        }

        if(fxGfilter1.isSelected()){
            fxGfilter1.fire();
        }

        if(fxGfilter2.isSelected()){
            fxGfilter2.fire();
        }
        if(fxCfilter1.isSelected()){
            fxCfilter1.fire();
        }




    }

    /**
     *implement judge filter function
     * @return chose image
     */

    int judgeFilter(){
        if(fxGfilter1.isSelected()){
            return 0;
        }
        if(fxGfilter2.isSelected()){
            return 1;
        }
        if(fxGfilter3.isSelected()){
            return 2;
        }

        if(fxCfilter1.isSelected()){
            return 3;
        }

        if(fxCfilter2.isSelected()){
            return 4;
        }
        if(fxCfilter3.isSelected()){
            return 5;
        }
        return 6;
    }

    /**
     *implement save function
     * @param event event
     */

    @FXML
    void save(ActionEvent event) {


        int size = imgList.size();

        boolean [] size2 = new boolean[size];
        for (int i1 = 0; i1 < size; i1++) {
            size2[i1]=true;
        }
        if (size == 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("Waring");
            alert.headerTextProperty().set("Please load a Image!!");
            alert.showAndWait();
            return;


        }


        RGBFilter rgbFilter1 = new RGBFilter("Color_Filter1");
        rgbFilter1.setId(4);
        rgbFilter1.setFilter(50,30,-30,-20);

        RGBFilter rgbFilter2 = new RGBFilter("Color_Filter2");
        rgbFilter2.setId(5);
        rgbFilter2.setFilter(-20,30,30,-40);

        RGBFilter rgbFilter3 = new RGBFilter("Color_Filter3");
        rgbFilter3.setId(6);
        rgbFilter3.setFilter(10,10,60,-80);

        YFilter yFilter1 = new YFilter("Gray_Filter2");
        yFilter1.setId(1);
        yFilter1.setY(30);
        yFilter1.setVariance(true);

        YFilter yFilter2 = new YFilter("Gray_Filter2");
        yFilter2.setId(2);
        yFilter2.setY(70);
        yFilter2.setVariance(false);

        YFilter yFilter3 = new YFilter("Gray_Filter2");
        yFilter3.setId(3);
        yFilter3.setY(50);
        yFilter3.setVariance(true);

        FilterList filter1 = new FilterList();
        filter1.addFilter(yFilter1);
        filter1.addFilter(yFilter2);
        filter1.addFilter(yFilter3);
        filter1.addFilter(rgbFilter1);
        filter1.addFilter(rgbFilter2);
        filter1.addFilter(rgbFilter3);

        Filter chosedFilter;
        int filterChooice = judgeFilter();
        if(filterChooice != 0) {
            chosedFilter = filter1.getFilter(filterChooice);
        }
        else {
            chosedFilter = null;
        }


        ImageFrame newFrame = imageFrame.edit(size2,chosedFilter, judgefomrat());
        if(newFrame== null){

        }

        newFrame.save(size2,null);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.titleProperty().set("Good !");
        alert.headerTextProperty().set("Save Success !");
        alert.showAndWait();
    }

    /**
     *implement judgeformat function
     * @return chose format
     */

    public String judgefomrat(){
        if (fxJpeg.isSelected()){
            return "jpeg";
        }

        if (fxPNG.isSelected()){
            return "png";
        }

        if (fxGif.isSelected()){
            return "gif";
        }
        else {
            return null;
        }


    }





}
