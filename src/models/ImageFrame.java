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
     * the filter to edit the picture. Can be null when do not use filter.
     * @param format
     * format of saved file type. Can be null when save as original format.
     * @return
     * the edited set of pictures.
     */
    public ImageFrame edit(boolean[] choice, Filter filter, String format){
        if(choice.length != imgList.size()){
            System.err.println("Error: length of choice does not match length of image list");
        }

        ImageFrame newFrame = new ImageFrame();

        //int type = filter.getType();

        int indexOfChoice = 0;
        for (Image currentImg : imgList) {
            //if(choice[indexOfChoice]) {
                    int width = currentImg.getWidth();
                    int height = currentImg.getHeight();
                BufferedImage imgBuffer;
                    if(filter!=null){
                    imgBuffer =
                            filter.useFilter(currentImg.getImg(), width, height, format);}
                    else {
                        imgBuffer = currentImg.getImg();
                    }
                    if (imgBuffer == null) return null;
                    Image newImage = new Image(currentImg.getPath(),
                            height,width, null, format != null ? format : currentImg.getFormat(), false, imgBuffer);
                    newFrame.append(newImage);
            //}
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

    public boolean checkFormat(int T){
        if(T == FilterType.GRAY) {
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
