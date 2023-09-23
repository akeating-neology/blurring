package net.neology.tolling.dev;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
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

        // Creating a File class object to
        // read the image in the form of file from directory

        // Directory path is passed as an argument
        InputStream inputStream = Main.class.getResourceAsStream("/trximg.jpg");
        BufferedImage input = ImageIO.read(inputStream);
        BufferedImage resultImage = ImageIO.read(Main.class.getResourceAsStream("/trximg.jpg"));

        // Now object of BufferedImage class is created to
        // convert file into image form


        // Again creating an object of BufferedImage to
        // create output Image
        BufferedImage output = new BufferedImage(
                input.getWidth(), input.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        // Setting dimensions for the image to be processed
        int i = 0;
        int max = 400, rad = 10;
        int a1 = 0, r1 = 0, g1 = 0, b1 = 0;
        color = new Color[max];

        // Now this core section of code is responsible for
        // blurring of an image
        System.out.println("here");
        int x = 1, y = 1, x1, y1, ex = 5, d = 0;

        // Running nested for loops for each pixel
        // and blurring it
        for (x = rad; x < input.getHeight() - rad; x++) {
            for (y = rad; y < input.getWidth() - rad; y++) {
                for (x1 = x - rad; x1 < x + rad; x1++) {
                    for (y1 = y - rad; y1 < y + rad; y1++) {
                        color[i++] = new Color(
                                input.getRGB(y1, x1));
                    }
                }

                // Smoothing colors of image
                i = 0;
                for (d = 0; d < max; d++) {
                    a1 = a1 + color[d].getAlpha();
                }

                a1 = a1 / (max);
                for (d = 0; d < max; d++) {
                    r1 = r1 + color[d].getRed();
                }

                r1 = r1 / (max);
                for (d = 0; d < max; d++) {
                    g1 = g1 + color[d].getGreen();
                }

                g1 = g1 / (max);
                for (d = 0; d < max; d++) {
                    b1 = b1 + color[d].getBlue();
                }

                b1 = b1 / (max);
                int sum1 = (a1 << 24) + (r1 << 16)
                        + (g1 << 8) + b1;
                output.setRGB(y, x, (int)(sum1));
            }
        }

        // Writing the blurred image on the disc where
        // directory is passed as an argument


        // Message to be displayed in the console when
        // program is successfully executed
        System.out.println("Image blurred successfully !");





        System.out.println("debugbreakpoint");
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