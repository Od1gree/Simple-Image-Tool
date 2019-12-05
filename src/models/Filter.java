package models;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * type of the filters
 */
enum filterType{
    RGB, GRAY;
}

/**
 * Filter interface
 * set the interface of the functions in filters.
 */
public interface Filter {
    public void setName(String initName);
    public String getName();
    public filterType getType();
    public void setId(int id);
    public int getId();
    public BufferedImage useFilter(BufferedImage img, int width, int height, String format);

}
