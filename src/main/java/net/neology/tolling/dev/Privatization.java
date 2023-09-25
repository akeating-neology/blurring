package net.neology.tolling.dev;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Privatization {

    public BufferedImage applyPrivatization(BufferedImage input, PrivacyCords pc, int pixelation) throws IOException {
        BufferedImage resultImage = resize(input, input.getWidth() / pixelation, input.getHeight() / pixelation);
        resultImage = resize(resultImage, input.getWidth(), input.getHeight());

        for (int y = 0; y < input.getHeight(); y++) {
            for (int x = 0; x < input.getWidth(); x++) {
                if (isInPrivacyZone(x, y, pc)) {
                    resultImage.setRGB(x, y, input.getRGB(x, y));
                }
            }
        }
        return resultImage;
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_FAST);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_3BYTE_BGR);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    private static boolean isInPrivacyZone(int x, int y, PrivacyCords p) {
        boolean isOutsidePrivacyZone = (x > p.getOutXone() && y > p.getOutYone())
                && (x < p.getOutXtwo() && y < p.getOutYtwo());

        boolean isInsideWindow = (x > p.getWindXone() && y > p.getWindYone())
                && (x < p.getWindXtwo() && y < p.getWindYtwo());

        return (isOutsidePrivacyZone || isInsideWindow);
    }
}
