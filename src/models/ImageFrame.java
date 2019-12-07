package models;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.TreeMap;

public class ImageFrame extends ImageList{

    public ImageFrame(){
        super();
    }


    /**
     * use filter to edit current picture.
     * @param choice
     * chosen pictures.
     * @param filter
     * the filter to edit the picture.
     * @param format
     * format of saved file type.
     * @return
     * the edited set of pictures.
     */
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

    /**
     * save picture using the given path.
     * @param choice
     * choice to save files.
     * @param path
     * path to a folder.
     */
    public void save(boolean[] choice, String path){
        int indexOfChoice = 0;
        for (Image currentImg : imgList) {
            if(choice[indexOfChoice]) {
                currentImg.save(path);
            }
        }
    }

    public void save(){
        for (Image currentImg: imgList){
            currentImg.save(null);
        }
    }

    public boolean checkFormat(filterType T){
        if(T == filterType.GRAY) {
            for (Image currentImg : imgList) {
                int type = currentImg.getImg().getType();
                if (type != 11 && type != 12) {
                    return false;
                }
            }
        }
        else{
            for (Image currentImg : imgList){
                int type = currentImg.getImg().getType();
                if (type == 11 || type == 12) {
                    return false;
                }
            }
        }
        return true;
    }

}
