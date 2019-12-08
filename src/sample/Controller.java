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



    @FXML
    private VBox big_img;



    @FXML
    private Button hlo;

    @FXML
    private ListView<String> Image_Info;

    @FXML
    private Button delateImage;

    @FXML
    private Button fxsave;


    @FXML
    private VBox hlobox;


    @FXML
    private RadioButton fxGfilter1;
    @FXML
    private RadioButton fxGfilter2;
    @FXML
    private RadioButton fxGfilter3;

    @FXML
    private RadioButton fxCfilter1;

    @FXML
    private RadioButton fxCfilter2;

    @FXML
    private RadioButton fxCfilter3;


    @FXML
    private RadioButton fxJpeg;

    @FXML
    private RadioButton fxGif;

    @FXML
    private RadioButton fxPNG;



    ArrayList<ImageView> imgList = new ArrayList<>();


    private int i=0;

    //private  String pass;

    private ImageFrame imageFrame =new ImageFrame();

    private FilterList filterList=new FilterList();
    private ArrayList<String> arrayList = new ArrayList<>();

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
            arrayList.add(file.getAbsolutePath());


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


     //private Label asdf;

//    @FXML
//    void creatFlist(ActionEvent event) {
//
//        fxfilterlist.getItems().add("12122");
//        fxfilterlist.getItems().add("121243");
//        fxfilterlist.getItems().add("1214323");
//        asdf.setText(fxfilterlist.getValue().toString());
//    }
    boolean format1 [] = {true,false,false};
    boolean format2 [] = {false,true,false};
    boolean format3 [] = {false,false,true};

    @FXML
    void jpeg(ActionEvent event) {
        if(fxPNG.isSelected()){
        fxPNG.fire();}
        else if(fxGif.isSelected()){
            fxGif.fire();
        }


        System.out.println("21");
    }

    @FXML
    void png(ActionEvent event) {
        if(fxJpeg.isSelected()){
            fxJpeg.fire();
        }
        else if(fxGif.isSelected()){
            fxGif.fire();
        }

    }

    @FXML
    void gif(ActionEvent event) {
        if(fxJpeg.isSelected()){
            fxJpeg.fire();
        }
        else if(fxPNG.isSelected()){
            fxPNG.fire();
        }

    }

    boolean cf1 [] = {true,false,false,false,false,false};
    boolean cf2 [] = {false,true,false,false,false,false};
    boolean cf3 [] = {false,false,true,false,false,false};
    boolean gf1 [] = {false,false,false,true,false,false};
    boolean gf2 [] = {false,false,false,false,true,false};
    boolean gf3 [] = {false,false,false,false,false,true};


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

        boolean cf1 [] = {true,false,false,false,false,false};




    }

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

    int judgeFilter(){
        if(fxGfilter1.isSelected()){
            return 1;
        }
        if(fxGfilter2.isSelected()){
            return 2;
        }
        if(fxGfilter3.isSelected()){
            return 3;
        }

        if(fxCfilter1.isSelected()){
            return 4;
        }

        if(fxCfilter2.isSelected()){
            return 5;
        }
        if(fxCfilter3.isSelected()){
            return 6;
        }
        return 0;
    }

    @FXML
    void save(ActionEvent event) {

        int size = imgList.size();

        boolean [] size2 = new boolean[size];
        for (int i1 = 0; i1 < size; i1++) {
            size2[i1]=true;
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




        //FilterType filterType = chosedFilter.getType() //FilterType.RGB or FilterType.GRAY



        ImageFrame newFrame = imageFrame.edit(size2,chosedFilter, judgefomrat());
        if(newFrame== null){
            //exception
        }

        newFrame.save(size2,null);


    }

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
