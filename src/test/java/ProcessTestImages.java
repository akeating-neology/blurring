import net.neology.tolling.dev.Main;
import net.neology.tolling.dev.PrivacyCords;
import net.neology.tolling.dev.Privatization;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ProcessTestImages {

    Privatization priv = new Privatization();
    int pixelation = 1;
    String image;
    BufferedImage input;
    PrivacyCords p;
    BufferedImage bi;
    boolean success;

    Privatization.Strategy strat = Privatization.Strategy.BLUR_WINDSHIELD_BLUR_EXTERIOR;

    @Test
    public void processImages() throws IOException {
        image = "4da01c63-e18b-49e1-ac0b-72bb9995608e.png";
        p = new PrivacyCords(75,185,1000,1200,180,380,950,700);
        input = ImageIO.read(Main.class.getResourceAsStream("/"+image));
        bi = priv.applyPrivatization(input, p, pixelation, strat );
        success = ImageIO.write(bi, "jpg", new File("./target/result-"+image));
        System.out.println(success);

        image = "5fee1022-bb5f-4e48-9d60-2eb6c673233d.jpg";
        p = new PrivacyCords(20,50,910,770,195,210,845,435);
        input = ImageIO.read(Main.class.getResourceAsStream("/"+image));
        bi = priv.applyPrivatization(input, p, pixelation, strat);
        success = ImageIO.write(bi, "jpg", new File("./target/result-"+image));
        System.out.println(success);

        image = "8f0bf68a-8fb9-4d2a-a3ac-5c69aafbac6b.png";
        p = new PrivacyCords(0,0,930,1070,120,350,770,600);
        input = ImageIO.read(Main.class.getResourceAsStream("/"+image));
        bi = priv.applyPrivatization(input, p, pixelation, strat);
        success = ImageIO.write(bi, "jpg", new File("./target/result-"+image));
        System.out.println(success);

        image = "217752b2-8216-4aae-8635-006cc0d07587.jpg";
        p = new PrivacyCords(100,0,850,960,260,260,750,500);
        input = ImageIO.read(Main.class.getResourceAsStream("/"+image));
        bi = priv.applyPrivatization(input, p, pixelation, strat);
        success = ImageIO.write(bi, "jpg", new File("./target/result-"+image));
        System.out.println(success);

        image = "93858275-0bde-4ff6-b1ff-9bd3c3e3b03e.jpg";
        p = new PrivacyCords(0,55,875,1050,140,330,790,610);
        input = ImageIO.read(Main.class.getResourceAsStream("/"+image));
        bi = priv.applyPrivatization(input, p, pixelation, strat);
        success = ImageIO.write(bi, "jpg", new File("./target/result-"+image));
        System.out.println(success);

        image = "c12b8cff-fc17-4292-856e-d178450e3a94.jpg";
        p = new PrivacyCords(0,0,1020,890,0,125,700,410);
        input = ImageIO.read(Main.class.getResourceAsStream("/"+image));
        bi = priv.applyPrivatization(input, p, pixelation, strat);
        success = ImageIO.write(bi, "jpg", new File("./target/result-"+image));
        System.out.println(success);

        image = "e68ef981-7eea-48ab-b1e1-054d5937e1e1.jpg";
        p = new PrivacyCords(125,80,710,360,100,100,520,190);
        input = ImageIO.read(Main.class.getResourceAsStream("/"+image));
        bi = priv.applyPrivatization(input, p, pixelation, strat);
        success = ImageIO.write(bi, "jpg", new File("./target/result-"+image));
        System.out.println(success);

        image = "ff25d4e2-1ade-43df-8298-e2cfdc80a055.jpg";
        p = new PrivacyCords(100,160,850,1250,130,550,830,810);
        input = ImageIO.read(Main.class.getResourceAsStream("/"+image));
        bi = priv.applyPrivatization(input, p, pixelation, strat);
        success = ImageIO.write(bi, "jpg", new File("./target/result-"+image));
        System.out.println(success);
    }
}
