package net.neology.tolling.dev;

import net.neology.tolling.dev.mantis.MantisResponse;
import net.neology.tolling.dev.mantis.MantisResponseData;
import net.neology.tolling.dev.mantis.MantisResponseDimension;
//import org.slf4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.InputMismatchException;

public class Privatization {

//    Logger logger;

    public enum Strategy {
        BLACK_WINDSHIELD_ONLY,
        BLACK_EXTERIOR_ONLY,
        BLACK_WINDSHIELD_BLACK_EXTERIOR,
        BLUR_WINDSHIELD_ONLY,
        BLUR_EXTERIOR_ONLY,
        BLUR_WINDSHIELD_BLUR_EXTERIOR,
        BLACK_WINDSHIELD_BLUR_EXTERIOR,
        BLUR_WINDSHIELD_BLACK_EXTERIOR
    }

    /**
     *
     * @param input
     * @param mantisResponse
     * @param pixelation
     * @return
     * @throws IOException
     */
    public BufferedImage applyPrivatization(BufferedImage input, MantisResponse mantisResponse, int pixelation, Strategy strategy) throws IOException {

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
                    0
                    ,0
                    , input.getWidth()
                    , input.getHeight()
                    , windshield.getX0()
                    , windshield.getY0()
                    ,windshield.getX0() + windshield.getWidth()
                    ,windshield.getY0() + windshield.getHeight()

            );
        }

        return applyPrivatization(input, pc, pixelation, strategy);
    }

    /**
     *
     * @param input
     * @param pc
     * @param pixelation
     * @param strat
     * @return
     * @throws IOException
     */
    public BufferedImage applyPrivatization(BufferedImage input, PrivacyCords pc, int pixelation, Strategy strat) throws IOException {

        BufferedImage resultImage = resize(input, input.getWidth(), input.getHeight());

        switch (strat) {
            case BLACK_WINDSHIELD_ONLY:
                if(pc.getMyVersion() == PrivacyCords.Version.MALFORMED)
                    throw new InputMismatchException("mismatched version or malformed PrivacyCords...");
                for (int y = 0; y < input.getHeight(); y++) {
                    for (int x = 0; x < input.getWidth(); x++) {
                        if (isInsideWindow(x, y, pc)) {
                            resultImage.setRGB(x, y, 0); //Black
                        }
                    }
                }
                break;
            case BLACK_EXTERIOR_ONLY:
                if(pc.getMyVersion() != PrivacyCords.Version.V2)
                    throw new InputMismatchException("mismatched version or malformed PrivacyCords...");
                for (int y = 0; y < input.getHeight(); y++) {
                    for (int x = 0; x < input.getWidth(); x++) {
                        if (!isCarOfInterest(x, y, pc)) {
                            resultImage.setRGB(x, y, 0); //Black
                        }
                    }
                }
                break;
            case BLACK_WINDSHIELD_BLACK_EXTERIOR:
                if(pc.getMyVersion() != PrivacyCords.Version.V2)
                    throw new InputMismatchException("mismatched version or malformed PrivacyCords...");
                for (int y = 0; y < input.getHeight(); y++) {
                    for (int x = 0; x < input.getWidth(); x++) {
                        if (isInClearZone(x, y, pc)) {
                            resultImage.setRGB(x, y, 0); //Black
                        }
                    }
                }
                break;
            case BLUR_WINDSHIELD_ONLY:
                if(pc.getMyVersion() == PrivacyCords.Version.MALFORMED)
                    throw new InputMismatchException("mismatched version or malformed PrivacyCords...");
                //Blur\Pixelate Whole image
                resultImage = resize(resultImage, input.getWidth() / pixelation, input.getHeight() / pixelation);
                resultImage = resize(resultImage, input.getWidth(), input.getHeight());
                //restore what is not windshield
                for (int y = 0; y < input.getHeight(); y++) {
                    for (int x = 0; x < input.getWidth(); x++) {
                        if (!isInsideWindow(x, y, pc)) {
                            resultImage.setRGB(x, y, input.getRGB(x,y));
                        }
                    }
                }
                break;
            case BLUR_EXTERIOR_ONLY:
                if(pc.getMyVersion() != PrivacyCords.Version.V2)
                    throw new InputMismatchException("mismatched version or malformed PrivacyCords...");
                //Blur\Pixelate Whole image
                resultImage = resize(resultImage, input.getWidth() / pixelation, input.getHeight() / pixelation);
                resultImage = resize(resultImage, input.getWidth(), input.getHeight());
                //retore pixels that are car
                for (int y = 0; y < input.getHeight(); y++) {
                    for (int x = 0; x < input.getWidth(); x++) {
                        if (!isCarOfInterest(x, y, pc)) {
                            resultImage.setRGB(x, y, input.getRGB(x,y));
                        }
                    }
                }
                break;
            case BLUR_WINDSHIELD_BLUR_EXTERIOR:
                if(pc.getMyVersion() != PrivacyCords.Version.V2)
                    throw new InputMismatchException("mismatched version or malformed PrivacyCords...");
                //Blur\Pixelate Whole image
                resultImage = resize(resultImage, input.getWidth() / pixelation, input.getHeight() / pixelation);
                resultImage = resize(resultImage, input.getWidth(), input.getHeight());
                //restore pixels that are car, but not windshield
                for (int y = 0; y < input.getHeight(); y++) {
                    for (int x = 0; x < input.getWidth(); x++) {
                        if (isInClearZone(x, y, pc)) {
                            resultImage.setRGB(x, y, input.getRGB(x, y));
                        }
                    }
                }
                break;
            case BLACK_WINDSHIELD_BLUR_EXTERIOR:
                if(pc.getMyVersion() != PrivacyCords.Version.V2)
                    throw new InputMismatchException("mismatched version or malformed PrivacyCords...");
                //Blur\Pixelate Whole image
                resultImage = resize(resultImage, input.getWidth() / pixelation, input.getHeight() / pixelation);
                resultImage = resize(resultImage, input.getWidth(), input.getHeight());
                //restore pixels that are car
                for (int y = 0; y < input.getHeight(); y++) {
                    for (int x = 0; x < input.getWidth(); x++) {
                        if (isCarOfInterest(x, y, pc)) {
                            resultImage.setRGB(x, y, input.getRGB(x, y));
                        }
                    }
                }
                //Blacken windshield
                for (int y = 0; y < input.getHeight(); y++) {
                    for (int x = 0; x < input.getWidth(); x++) {
                        if (isInsideWindow(x, y, pc)) {
                            resultImage.setRGB(x, y, 0); //Black
                        }
                    }
                }
                break;
            case BLUR_WINDSHIELD_BLACK_EXTERIOR:
                if(pc.getMyVersion() != PrivacyCords.Version.V2)
                    throw new InputMismatchException("mismatched version or malformed PrivacyCords...");
                //Blur\Pixelate Whole image
                resultImage = resize(resultImage, input.getWidth() / pixelation, input.getHeight() / pixelation);
                resultImage = resize(resultImage, input.getWidth(), input.getHeight());
                //restore pixels that are not windshield
                for (int y = 0; y < input.getHeight(); y++) {
                    for (int x = 0; x < input.getWidth(); x++) {
                        if (!isInsideWindow(x, y, pc)) {
                            resultImage.setRGB(x, y, input.getRGB(x,y)); //restore selected pixels
                        }
                    }
                }
                //Blacken pixels outside the car
                for (int y = 0; y < input.getHeight(); y++) {
                    for (int x = 0; x < input.getWidth(); x++) {
                        if (isCarOfInterest(x, y, pc)) {
                            resultImage.setRGB(x, y, 0); //Black
                        }
                    }
                }
                break;
            default:
                //Do nothing. maybe throw an error?
                break;
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
    private boolean isInClearZone(int x, int y, PrivacyCords p) {
        return ( isCarOfInterest(x,y,p) && !isInsideWindow(x,y,p) );
    }

    /**
     *
     * @param x
     * @param y
     * @param p
     * @return
     */
    private boolean isCarOfInterest(int x, int y, PrivacyCords p) {
        return ( (x > p.getOutXone() && y > p.getOutYone()) && (x < p.getOutXtwo() && y < p.getOutYtwo()) );
    }

    /**
     *
     * @param x
     * @param y
     * @param p
     * @return
     */
    private boolean isInsideWindow(int x, int y, PrivacyCords p) {
        return ( (x > p.getWindXone() && y > p.getWindYone()) && (x < p.getWindXtwo() && y < p.getWindYtwo()) );
    }
}
