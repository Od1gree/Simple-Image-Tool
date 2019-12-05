package test;
import models.*;

import java.util.TreeMap;

public class entrance {
    public static void main(String[] args){
//        String path = "/Users/od1gree/Downloads/test_set/lena512.bmp";
//        Image img = new Image(path);
//        TreeMap<String,String> map = img.getAttributes();
//        ImageFrame imageFrame = new ImageFrame();
//        imageFrame.append(img);
//
//        String path1 = "/Users/Od1gree/Downloads/test_set/move.gf";
//        Image img1 = new Image(path1);
//        imageFrame.append(img1);
//
//        Filter redFilter = new RGBFilter("testFilter");
//        ((RGBFilter)redFilter).setFilter(0, -100, 50, -100);
//        FilterList filterlist = new FilterList();
//        filterlist.addFilter(redFilter);
//        boolean[] choiceList = {true, true};
//        ImageFrame newFrame = imageFrame.edit(choiceList, redFilter, "jpg");
//        newFrame.save(choiceList,"/Users/Od1gree/Downloads/test_set/ttt/");

        String path ="/Users/od1gree/Downloads/test_set/lena512.bmp";
        Image img = new Image(path);
        ImageFrame imageFrame = new ImageFrame();
        imageFrame.append(img);

        Filter filter = new YFilter("testGray");
        ((YFilter)filter).setY(40);
    }
}
