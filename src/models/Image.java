package models;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.png.PngMetadataReader;
import com.drew.imaging.tiff.TiffMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Image {
    private String path;
    private int height;
    private int width;
    private Metadata exif;
    private String format;
    private Boolean isSaved;
    private BufferedImage img;

    public Image(String initPath){
        //从目录中生成
        isSaved = true;
        path = initPath;

        assert path != null;
        try {
            File image = new File(path);
            BufferedImage bufImg = ImageIO.read(image);
            height = bufImg.getHeight();
            width = bufImg.getWidth();
            img = bufImg;

            ImageInputStream iis = ImageIO.createImageInputStream(image);
            Iterator<ImageReader> imageReaders= ImageIO.getImageReaders(iis);
            while (imageReaders.hasNext()) {

                ImageReader reader = (ImageReader) imageReaders.next();
                System.out.printf("formatName: %s%n", reader.getFormatName());
                format = reader.getFormatName();
            }

            Metadata metadata;
            if(format.equals("JPEG")){
                metadata = JpegMetadataReader.readMetadata(image);
            }
            else if(format.equals("png")){
                metadata = PngMetadataReader.readMetadata(image);
            }
            else if(format.equals("tiff")||format.equals("raw")){
                metadata = TiffMetadataReader.readMetadata(image);
            }
            else {
                metadata = ImageMetadataReader.readMetadata(image);
            }

            exif = metadata;

        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    public Image(String initPath, int initHeight, int initWidth, Metadata initExif, String initFormat, Boolean initIsSaved, BufferedImage initImg){
        // generated from Frame.edit()
        path = initPath;
        height = initHeight;
        width = initWidth;
        exif = initExif;
        format = initFormat;
        isSaved = initIsSaved;
        img = initImg;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public BufferedImage getImg() {
        return img;
    }

    public TreeMap<String,String> getAttributes(){
        TreeMap<String, String> attributes = new TreeMap<String, String>();
        for (Directory directory : exif.getDirectories()){
            for (Tag tag : directory.getTags()){
                // System.out.println(tag);
                // System.out.println(tag.getDescription());
                // System.out.println(tag.getTagName());
                String k = tag.getTagName();
                String v = tag.getDescription();

                Pattern pattern = Pattern.compile("^Unknown\\s");
                Matcher matcher = pattern.matcher(k);
                if(matcher.find()){
                    continue;
                }
                attributes.put(k,v);
            }

            for (String err : directory.getErrors()){
                System.err.println(err);
            }
        }
        return attributes;
    }

    public String getPath() {
        return path;
    }

    public void save(String pathExt){
        String pathFilename;
        if (pathExt != null) {
            //Pattern pattern = Pattern.compile("[\\w%?_:;'\\\\`]+\\.", Pattern.UNICODE_CHARACTER_CLASS);
            //Matcher m = pattern.matcher(path);
            //String fileName = m.group(0);
            String[] arr = path.split("/");
            String fileName = arr[arr.length-1].split("\\.")[0];
            pathFilename = pathExt + fileName;
        }
        else {
            pathFilename = path.split("\\.")[0];
        }
        Long time = System.currentTimeMillis();
        path = pathFilename + "_" + time.toString() + "." + format;
        File saving = new File(path);
        try {
            ImageIO.write(img, format, saving);
        }
        catch (Exception e){
            System.err.println(e.toString());
        }
    }
}
