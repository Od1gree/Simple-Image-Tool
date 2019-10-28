package models;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class RGBFilter implements Filter{
    private int r;
    private int g;
    private int b;
    private int a;
    private String name;
    private int id;

    public RGBFilter(int initId){
        id = initId;
    }

    public void setName(String initName){
        name = initName;
    }

    public filterType getType(){
        return filterType.RGB;
    }

    public String getName(){
        return name;
    }

    public void setFilter(int red, int green, int blue, int alpha){
        r = red;
        g = green;
        b = blue;
        a = alpha;
    }

    public int getR(){
        return r;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getId() {
        return id;
    }

    public int getG() {
        return g;
    }

    public int calcRed(int color){
        return color & 0x000000FF;
    }

    public int calcGreen(int color){
        return (color & 0x0000FF00)>>8;
    }

    public int calcBlue(int color){
        return (color & 0x00FF0000)>>16;
    }

    public int calcAlpha(int color){
        return (color & 0xff000000)>>24;
    }

    public int editPixel(int color){
        int result = change((color & 0x000000FF), r);
        result += (change((color & 0x0000FF00)>>8,g)<<8);
        result += (change((color & 0x00FF0000)>>16, b) << 16);
        result += (change((color & 0xFF000000)>>24,a)<<24);
        return result;
    }

    private int change(int original, int variety){
        int result = original + variety;
        if(result > 255){
            return 255;
        }
        if(result < 0){
            return 0;
        }
        return result;

    }

    @Override
    public BufferedImage useFilter(BufferedImage img, int width, int height) {
        int[] pinels = img.getRGB(0,0,width,height, null, 0, width);
        BufferedImage newImg = new BufferedImage(width,height,img.getType());
        int maxLength = pinels.length;
        for (int i = 0; i < maxLength; i++) {
            pinels[i] = editPixel(pinels[i]);
        }
        newImg.setRGB(0,0,width,height,pinels,0,width);
        return newImg;
    }
}
