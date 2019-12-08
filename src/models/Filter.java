package models;

import java.awt.image.BufferedImage;

/**
 * Filter interface
 * set the interface of the functions in filters.
 */
public interface Filter {
    public void setName(String initName);
    public String getName();
    public int getType();
    public void setId(int id);
    public int getId();
    public BufferedImage useFilter(BufferedImage img, int width, int height, String format);

}
