package models;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class RGBFilter implements Filter, java.io.Serializable{
    private int r;
    private int g;
    private int b;
    private int a;
    private String name;
    private int id;

    public RGBFilter(String initName) {
        name = initName;
    }

    public void setName(String initName) {
        name = initName;
    }

    public void setId(int initId) {
        id = initId;
    }

    public int getType() {
        return FilterType.RGB;
    }

    public String getName() {
        return name;
    }

    public void setFilter(int red, int green, int blue, int alpha) {
        r = red;
        g = green;
        b = blue;
        a = alpha;
    }

    public int getR() {
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

    public int calcRed(int color) {
        return color & 0x000000FF;
    }

    public int calcGreen(int color) {
        return (color >> 8) & 0xFF;
    }

    public int calcBlue(int color) {
        return (color >> 16) & 0xFF;
    }

    public int calcAlpha(int color) {
        return (color >> 24) & 0xff;
    }

    /**
     * edit each pixel according to the filter, set alpha=0xFF.
     * @param color
     * pixel to be edited, formaat: argb from high-end to low-end.
     * @return
     * edited int pixel
     */
    public int editPixelBGR(int color) {
        int result = change((color & 0x000000FF), b);
        result |= (change((color & 0x0000FF00) >> 8, g) << 8);
        result |= (change((color & 0x00FF0000) >> 16, r) << 16);
        result |= 0xFF000000;
        return result;
    }

    /**
     * edit each pixel according to the filter, include alpha.
     * @param color
     * pixel to be edited, format: argb from high-end to low-end.
     * @return
     * edited int pixel
     */
    public int editPixelAGBR(int color) {
        int result = change((color & 0x000000FF), b);
        result |= (change((color >> 8) & 0xFF, g) << 8);
        result |= (change((color >> 16) & 0xFF, r) << 16);
        result |= (change((color >> 24) & 0xFF, a) << 24);
        return result;
    }

    /**
     * edit the value of colour channel(eg: r,g,b) of a pixel.
     * @param original
     * original channel value.
     * @param variety
     * the filter value of that channel
     * @return
     * the result channel value.
     */
    private int change(int original, int variety) {
        int result = original + variety;
        if (result > 255) {
            return 255;
        }
        if (result < 0) {
            return 0;
        }
        return result;

    }

    /**
     * use this filter to edit a BufferedImage
     * @param img
     * BufferedImage which only contains pixel information.
     * @param width
     * width of the image(for convenience usage)
     * @param height
     * height of the image(for convecience usage)
     * @param format
     * format of the original image.
     * it ensures whether it saves the alpha channel.
     * @return
     * BufferedImage
     */
    @Override
    public BufferedImage useFilter(BufferedImage img, int width, int height, String format) {
        int imgType = img.getType();
        if (imgType == BufferedImage.TYPE_BYTE_GRAY){
            return null;
        }
        int[] pinels = img.getRGB(0, 0, width, height, null, 0, width);

        System.out.println("img type: " + imgType);
        BufferedImage newImg;
        int maxLength = pinels.length;
        if (format.equals("png") || format.equals("tiff")) {
            newImg = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
            for (int i = 0; i < maxLength; i++) {
                pinels[i] = editPixelAGBR(pinels[i]);
            }
        } else {
            newImg = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            for (int i = 0; i < maxLength; i++) {
                pinels[i] = editPixelBGR(pinels[i]);
            }
        }
        newImg.setRGB(0, 0, width, height, pinels, 0, width);
        return newImg;
    }

    private int[][] get2D(BufferedImage img, int width, int height) {
        byte[] pixels = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        boolean hasAlphaChannel = img.getAlphaRaster() != null;

        int[][] resultPixel = new int[height][width];
        if (hasAlphaChannel) {
            for (int i = 0, currentRow = 0, currentCol = 0; i < pixels.length - 3; i += 4) {
                int currentPixel = 0;
                currentPixel |= (((int) pixels[i] & 0x000000FF) << 24); //a
                currentPixel |= (((int) pixels[i + 1] & 0x000000FF)); //b
                currentPixel |= (((int) pixels[i + 2] & 0x000000FF) << 8); //g
                currentPixel |= (((int) pixels[i + 3] & 0x000000FF) << 16); //r
                resultPixel[currentRow][currentCol] = currentPixel;
                currentCol++;
                if (currentCol == width) {
                    currentCol = 0;
                    currentRow++;
                }
            }
        } else {
            for (int i = 0, currentRow = 0, currentCol = 0; i < pixels.length - 2; i += 3) {
                int currentPixel = 0;
                currentPixel |= 0xFF000000;
                currentPixel |= (((int) pixels[i] & 0x000000FF));
                currentPixel |= (((int) pixels[i + 1] & 0x000000FF) << 8);
                currentPixel |= (((int) pixels[i + 2] & 0x000000FF) << 16);
                resultPixel[currentRow][currentCol] = currentPixel;
                currentCol++;
                if (currentCol == width) {
                    currentCol = 0;
                    currentRow++;
                }
            }
        }
        return resultPixel;
    }
}
