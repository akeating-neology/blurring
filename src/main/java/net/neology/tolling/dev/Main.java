package net.neology.tolling.dev;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");

        // Press Shift+F10 or click the green arrow button in the gutter to run the code.
//        new Main();
        Test();

        /////////

        /////////
    }

    Color c[];

    Main() throws IOException, InterruptedException {

    }

    static void Test() throws IOException {

        BufferedImage input = new BufferedImage(100,100,BufferedImage.TYPE_3BYTE_BGR);
                input = ImageIO.read(Main.class.getResourceAsStream("/trximg.jpg"));
        BufferedImage resultImage = input.getSubimage(0,0,input.getWidth(),input.getHeight());
        PrivacyCords p = new PrivacyCords(270,0,1400,600,520,0,1111,170);
        int pixelation = 12;

        //This is way faster than blurring...
        resultImage =  resize(resultImage, input.getWidth()/pixelation, input.getHeight()/pixelation);
        resultImage =  resize(resultImage, input.getWidth(), input.getHeight());

        for (int y = 0; y < input.getHeight(); y++) {
            for (int x = 0; x < input.getWidth(); x++) {
                if(doTheThing(x,y,p)) {
                    resultImage.setRGB(x, y, input.getRGB(x, y));
                }
            }
        }
        //openJDK doesn't have a native jpg encoder after 11 -.- (but I assume it works in LEZ. wonder why/how)
        //https://stackoverflow.com/questions/3432388/imageio-not-able-to-write-a-jpeg-file
        //https://bugs.openjdk.org/browse/JDK-8204188
        Boolean success = ImageIO.write(resultImage, "jpg", new File("./target/resultimage.jpg"));
        System.out.println(success);
        System.out.println("debugbreakpoint");
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_FAST);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_3BYTE_BGR);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }


    private static boolean doTheThing(int x, int y, PrivacyCords p) {


        if (
                !( (x > p.getOutXone() && y > p.getOutYone())  && (x < p.getOutXtwo() && y < p.getOutYtwo()) )
                ||
                ( (x > p.getWindXone() && y > p.getWindYone())  && (x < p.getWindXtwo() && y < p.getWindYtwo()) )
        ) {
            return false;
        }
        else {
            return true;
        }
    }

}