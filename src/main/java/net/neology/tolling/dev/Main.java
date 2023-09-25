package net.neology.tolling.dev;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("Hello and welcome!");
        Privatization priv = new Privatization();

        BufferedImage input = ImageIO.read(Main.class.getResourceAsStream("/trximg.jpg"));
        PrivacyCords p = new PrivacyCords(270,0,1400,600,520,0,1111,170);
        int pixelation = 12;
        BufferedImage bi = priv.applyPrivatization(input, p, pixelation);

        //openJDK doesn't guarantee a native jpg encoder after 11 -.- (but I assume it works in LEZ. wonder why/how)
        //https://stackoverflow.com/questions/3432388/imageio-not-able-to-write-a-jpeg-file
        //https://bugs.openjdk.org/browse/JDK-8204188
        Boolean success = ImageIO.write(bi, "jpg", new File("./target/resultimage.jpg"));
        System.out.println(success);
        System.out.println("debugbreakpoint");

    }


}