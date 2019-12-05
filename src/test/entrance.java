package test;
import models.*;

import java.util.TreeMap;

public class entrance {
    public static void main(String[] args){
        String path = "/Users/mac/Downloads/test_set/16707ff998ed6d087036d4f44b1296ef96e1b1cf.png";
        Image img = new Image(path);
        TreeMap<String,String> map = img.getAttributes();
        ImageFrame imageFrame = new ImageFrame();
        imageFrame.append(img);

        String path1 = "/Users/mac/Downloads/test_set/DSC_1621.JPG";
        Image img1 = new Image(path1);
        imageFrame.append(img1);

        Filter redFilter = new RGBFilter(123);
        ((RGBFilter)redFilter).setFilter(0, -100, 0, -100);
        boolean[] choiceList = {true, true};
        imageFrame.addFilter(redFilter);
        ImageFrame newFrame = imageFrame.edit(choiceList, redFilter.getId(), "jpg");
        newFrame.addFilter(redFilter);
        newFrame.save(choiceList,"/Users/mac/Downloads/test_set/ttt/");
    }
}
