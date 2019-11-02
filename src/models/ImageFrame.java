package models;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.TreeMap;

public class ImageFrame extends ImageList{

    public ImageFrame(){
        super();
    }


    public ImageFrame edit(boolean[] choice, Filter filter, String format){
        if(choice.length != imgList.size()){
            System.err.println("Error: length of choice does not match length of image list");
        }

        ImageFrame newFrame = new ImageFrame();

        filterType type = filter.getType();

        int indexOfChoice = 0;
        for (Image currentImg : imgList) {
            if(choice[indexOfChoice]) {
                if(type == filterType.RGB){
                    int width = currentImg.getWidth();
                    int height = currentImg.getHeight();
                    BufferedImage imgBuffer =
                            filter.useFilter(currentImg.getImg(), width, height, format);
                    Image newImage = new Image(currentImg.getPath(),
                            height,width, null, format, false, imgBuffer);
                    newFrame.append(newImage);
                }
                else {

                }
            }
            indexOfChoice++;
        }
        return newFrame;
    }

    public void save(boolean[] choice, String path){
        int indexOfChoice = 0;
        for (Image currentImg : imgList) {
            if(choice[indexOfChoice]) {
                currentImg.save(path);
            }
        }
    }


}
