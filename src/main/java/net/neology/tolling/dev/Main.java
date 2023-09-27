package net.neology.tolling.dev;

import com.google.gson.Gson;
import net.neology.tolling.dev.mantis.MantisResponse;
import net.neology.tolling.dev.mantis.MantisResponseData;
import net.neology.tolling.dev.mantis.MantisResponseDimension;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static Gson gson = new Gson();
    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("Hello and welcome!");

        int pixelation = 12;
        Privatization priv = new Privatization();

        MantisResponse mantisResponse = buildMantisResponse();


        BufferedImage input = ImageIO.read(Main.class.getResourceAsStream("/trximg.jpg"));
        BufferedImage bi = priv.applyPrivatization(input, mantisResponse, pixelation);

        Boolean success = ImageIO.write(bi, "jpg", new File("./target/resultimage.jpg"));
        System.out.println(success);
        System.out.println("debugbreakpoint");

    }

    private static MantisResponse buildMantisResponse() {
        MantisResponse mantisResponse = new MantisResponse();
        mantisResponse.setHttp_status_code(200);
        mantisResponse.setStatus_message("Request succeeded.");

        MantisResponseData mantisResponseData = new MantisResponseData();
        mantisResponseData.setStatus_code("success");
        mantisResponseData.setStatus_message("Success.");
        mantisResponseData.setImage_name("9908c445-b16f-4218-a96e-3b1263a299e6");
        mantisResponseData.setScores(new Float[] {0.99958736f, 0.9997273f});
        mantisResponseData.setTags(new String[] {"car", "windshield"});

        MantisResponseDimension windshield = new MantisResponseDimension();
        windshield.setX0(520);
        windshield.setY0(0);
        windshield.setWidth(591);
        windshield.setHeight(170);
        mantisResponseData.setWindshield(windshield);

//        MantisResponseDimension car = new MantisResponseDimension();
//        car.setX0(270);
//        car.setY0(0);
//        car.setWidth(1130);
//        car.setHeight(600);
//        mantisResponseData.setCar(car);

        List<MantisResponseData> data = new ArrayList<>();
        data.add(mantisResponseData); //will add to index 0
        mantisResponse.setData(data);

        System.out.println(gson.toJson(mantisResponse));
        return mantisResponse;
    }


}