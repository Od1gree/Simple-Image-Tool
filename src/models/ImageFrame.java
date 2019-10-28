package models;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.TreeMap;

public class ImageFrame extends ImageList{
    private TreeMap<Integer, Filter> filters;

    public ImageFrame(){
        super();
        filters = new TreeMap<>();
    }

    public Boolean addFilter(Filter filter){
        filters.put(filter.getId(),filter);
        return true;
    }

    public ImageFrame edit(boolean[] choice, int filterID, String format){
        if(choice.length != imgList.size()){
            System.err.println("Error: length of choice does not match length of image list");
        }

        ImageFrame newFrame = new ImageFrame();

        Filter filter = filters.get(filterID);
        filterType type = filter.getType();

        int indexOfChoice = 0;
        for (Image currentImg : imgList) {
            if(choice[indexOfChoice]) {
                if(type == filterType.RGB){
                    int width = currentImg.getWidth();
                    int height = currentImg.getHeight();
                    BufferedImage imgBuffer =
                            filter.useFilter(currentImg.getImg(), width, height);
                    Image newImage = new Image(currentImg.getPath(),
                            height,width, null, format, false, imgBuffer);
                    newFrame.append(newImage);
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
