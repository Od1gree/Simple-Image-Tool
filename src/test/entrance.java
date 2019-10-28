package test;
import models.*;

import java.util.TreeMap;

public class entrance {
    public static void main(String[] args){
        String path = "/Users/naizhengwang/Downloads/test_set/2333.png";
        Image img = new Image(path);
        TreeMap<String,String> map = img.getAttributes();
        ImageFrame imageFrame = new ImageFrame();
        imageFrame.append(img);

        String path1 = "/Users/naizhengwang/Downloads/test_set/e.jpg";
        Image img1 = new Image(path1);
        imageFrame.append(img1);

        Filter redFilter = new RGBFilter(123);
        ((RGBFilter)redFilter).setFilter(100, 0, 0, 0);
        boolean[] choiceList = {true, true};
        imageFrame.addFilter(redFilter);
        ImageFrame newFrame = imageFrame.edit(choiceList, redFilter.getId(), "JPEG");
        newFrame.addFilter(redFilter);
        newFrame.save(choiceList,"/Users/naizhengwang/Downloads/test_set/ttt/");
    }
}
