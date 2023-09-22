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
//        File f = new File();
        InputStream inputStream = getClass().getResourceAsStream("/trximg.jpg");
        BufferedImage im = ImageIO.read(inputStream);
        Long slow = System.currentTimeMillis();

        BufferedImage bi = new BufferedImage(im.getWidth(), im.getHeight(), BufferedImage.TYPE_INT_RGB);
        int i = 0;
        int max = 400, radius = 10;
        int a1 = 0, r1 = 0, g1 = 0, b1 = 0;
        c = new Color[max];
        int x = 1, y = 1, x1, y1, ex = 5, d = 0;
        for (x = radius; x < im.getHeight() - radius; x++) {
            for (y = radius; y < im.getWidth() - radius; y++) {

                //20x20 matrix
                for (x1 = x - radius; x1 < x + radius; x1++) {
                    for (y1 = y - radius; y1 < y + radius; y1++) {
                        c[i++] = new Color(im.getRGB(y1, x1));
                        //System.out.println(i);
                    }
                }
                i = 0;

                for (d = 0; d < max; d++) {
                    a1 = a1 + c[d].getAlpha();
                }
                a1 = a1 / (max);

                for (d = 0; d < max; d++) {
                    r1 = r1 + c[d].getRed();
                }
                r1 = r1 / (max);

                for (d = 0; d < max; d++) {
                    g1 = g1 + c[d].getGreen();
                }
                g1 = g1 / (max);

                for (d = 0; d < max; d++) {
                    b1 = b1 + c[d].getBlue();
                }
                b1 = b1 / (max);
                int sum1 = (a1 << 24) + (r1 << 16) + (g1 << 8) + b1;
                bi.setRGB(y, x, (int) (sum1));

            }
        }
        System.out.println(slow = System.currentTimeMillis() - slow);

//        ImageIO.write(bi, "jpg", new File("/x1.jpg"));
    }

    static void Test() throws IOException {
        InputStream inputStream = Main.class.getResourceAsStream("/trximg.jpg");
        BufferedImage image = ImageIO.read(inputStream);
        BufferedImage resultImage = ImageIO.read(Main.class.getResourceAsStream("/trximg.jpg"));

        Long slow = System.currentTimeMillis();
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int  clr   = image.getRGB(x, y);
                int  red   = (clr & 0x00ff0000) >> 16;
                int  green = (clr & 0x0000ff00) >> 8;
                int  blue  =  clr & 0x000000ff;

                int diff1,diff2,diff3,diff4,diff5,diff6,diff7,diff8;
                if(doTheThing(x,y)) {
//                    clr = clr & 0x00000000; //make black

                    //3x3 kernel
                    try {
                        diff1 = image.getRGB(x-1,y-1);
                        diff2 = image.getRGB(x-1,y); //2
                        diff3 = image.getRGB(x-1,y+1);
                        diff4 = image.getRGB(x+1,y-1);
                        diff5 = image.getRGB(x+1,y); //2
                        diff6 = image.getRGB(x+1,y+1);
                        diff7 = image.getRGB(x,y+1); //2
                        diff8 = image.getRGB(x,y-1); //2

                        //1,3,4,6 //2,5,7,8 x2
                        int blur = (diff1+diff3+diff4+diff6)
                                + ((diff2+diff5+diff7+diff8)*2)
                                + clr*4;
                        blur = blur /16;


                    } catch (Exception e) {
                        //  Block of code to handle errors
                    }
                }


                resultImage.setRGB(x, y, clr);
            }
        }
        slow = System.currentTimeMillis() - slow;
        System.out.println(slow);
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