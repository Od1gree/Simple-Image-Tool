package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageTest {
    static Image img;
    @BeforeEach
    void setUp() {
        String path = "/Users/od1gree/Downloads/test_set/DSC_0890.jpg";
        img = new Image(path);
    }

    @Test
    void getImg() {
    }

    @Test
    void getAttributes() {
    }

    @Test
    void getPath() {
    }

    @Test
    void save() {
    }
}