package test;
import models.*;

import java.util.TreeMap;

public class entrance {
    public static void main(String[] args){
        String path = "/Users/naizhengwang/Downloads/test_set/DSC_0033.JPG";
        Image img = new Image(path);
        TreeMap<String,String> map = img.getAttributes();
    }
}
