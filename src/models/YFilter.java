package models;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class YFilter implements Filter{
    private byte y;
    private boolean variance;
    private String name;
    private int id;

    public YFilter(String initName){
        name = initName;
    }

    public void setName(String initName){
        name = initName;
    }

    public void setId(int id){
        id = id;
    }

    public int getId(){
        return id;
    }

    public int getType(){
        return FilterType.GRAY;
    }

    public String getName(){
        return name;
    }

    public void setY(int gray){
        y = (byte) gray;
    }

    public byte getY(){
        return y;
    }

    public void setVariance(boolean var){
        variance = var;
    }

    public boolean getVariance(){
        return variance;
    }

    public BufferedImage useFilter(BufferedImage img, int width, int height, String format){
        byte[] pixels = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        int imgType = img.getType();
        System.out.println("img type: " + imgType);
        int maxLength = pixels.length;

        for(int i = 0; i<maxLength; i++){
            pixels[i] = change(pixels[i]);
        }

        return img;
    }

    /**
     * edit the value of colour channel(gray) of a pixel.
     * @param original
     * original channel value.
     * @return
     * the result channel value.
     */
    private byte change(byte original) {
        if(variance){
            // increase the pixel value
            byte result = (byte) (original + y);
            if(result < original){
                return (byte) 0xFF;
            }
            return result;
        }else {
            byte result = (byte) (original - y);
            if(result > original){
                return (byte) 0x0;
            }
            return result;
        }
    }
}


