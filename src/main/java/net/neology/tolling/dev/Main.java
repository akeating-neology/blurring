package net.neology.tolling.dev;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
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

        Color color[];
        Color c[];
        // Creating a File class object to
        // read the image in the form of file from directory

        // Directory path is passed as an argument
        InputStream inputStream = Main.class.getResourceAsStream("/trximg.jpg");
        BufferedImage input = ImageIO.read(inputStream);
        BufferedImage resultImage = ImageIO.read(Main.class.getResourceAsStream("/trximg.jpg"));

//        ConvolveOp op = new ConvolveOp(new Kernel(3, 3,
//                new float[] { 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f }),
//                ConvolveOp.EDGE_NO_OP, null);
//        resultImage = op.filter(input,null);

//        Image tempImage = resultImage.getScaledInstance(, Image.);
//        BufferedImage tempBufImage = resize(resultImage, resultImage.getWidth()/4, resultImage.getHeight()/4);
        resultImage =  resize(resultImage, input.getWidth()/12, input.getHeight()/12);
        resultImage =  resize(resultImage, input.getWidth(), input.getHeight());


        System.out.println("debugbreakpoint");
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_FAST);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }


    private static boolean doTheThing(int x, int y) {
        int outXone = 270;
        int outYone = 1;
        int outXtwo = 1400;
        int outYtwo = 600;
        int windXone = 520;
        int windYone = 2;
        int windXtwo = 1111;
        int windYtwo = 170;

        if (
                !( (x > 270 && y >2)  && (x < 1430 && y < 600) )
                ||
                ( (x > 520 && y >2)  && (x < 1111 && y < 170) )
        ) {
            return true;
        }
        else {
            return false;
        }
    }

}