package models;

import java.awt.image.BufferedImage;
import java.util.List;
enum filterType{
    RGB, GRAY;
}

public interface Filter {
    public void setName(String initName);
    public String getName();
    public filterType getType();
    public int getId();
    public BufferedImage useFilter(BufferedImage img, int width, int height);

}
