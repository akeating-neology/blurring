package net.neology.tolling.dev;

import net.neology.tolling.dev.mantis.MantisResponse;
import net.neology.tolling.dev.mantis.MantisResponseData;
import net.neology.tolling.dev.mantis.MantisResponseDimension;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Privatization {

    /**
     *
     * @param input
     * @param mantisResponse
     * @param pixelation
     * @return
     * @throws IOException
     */
    public BufferedImage applyPrivatization(BufferedImage input, MantisResponse mantisResponse, int pixelation) throws IOException {

        MantisResponseData mantisData = mantisResponse.getData().get(0);
        MantisResponseDimension windshield = mantisData.getWindshield();
        MantisResponseDimension car = mantisData.getCar();

        PrivacyCords pc;
        if(car != null ) { //V2
            pc = new PrivacyCords(
                    car.getX0()
                    ,car.getY0()
                    ,car.getX0() + car.getWidth()
                    ,car.getY0() + car.getHeight()
                    ,windshield.getX0()
                    ,windshield.getY0()
                    ,windshield.getX0() + windshield.getWidth()
                    ,windshield.getY0() + windshield.getHeight()
            );

        }
        else { //V1
            pc = new PrivacyCords(
                    car.getX0()
                    , car.getY0()
                    ,car.getX0() + car.getWidth()
                    ,car.getY0() + car.getHeight()
            );
        }


        return applyPrivatization(input, pc, pixelation);
    }

    /**
     *
     * @param input
     * @param pc
     * @param pixelation
     * @return
     * @throws IOException
     */
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

    /**
     *
     * @param img
     * @param newW
     * @param newH
     * @return
     */
    private BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_FAST);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_3BYTE_BGR);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
    /**
     *
     * @param x
     * @param y
     * @param p
     * @return
     */
    private static boolean isInPrivacyZone(int x, int y, PrivacyCords p) {

        boolean isOutsidePrivacyZone = ( (x > p.getOutXone() && y > p.getOutYone())
                && (x < p.getOutXtwo() && y < p.getOutYtwo()) );
        boolean isInsideWindow = !( (x > p.getWindXone() && y > p.getWindYone())
                && (x < p.getWindXtwo() && y < p.getWindYtwo()) );
        return ( isOutsidePrivacyZone && isInsideWindow);
    }
}
