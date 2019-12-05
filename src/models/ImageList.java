package models;

import java.util.ArrayList;
import java.util.List;

public class ImageList {
    protected ArrayList<Image> imgList;

    public ImageList(){
        imgList = new ArrayList<Image>();
    }

    public Boolean append(Image img){
        imgList.add(img);
        return true;
    }

    /**
     * delete the chosen image from list
     * @param indexs
     * int[] list
     * @return
     * successful
     */
    public Boolean del(int[] indexs){
        int max = indexs.length;
        for (int i = 0; i < max; i++) {
            imgList.remove(i);
        }
        return true;
    }
}
