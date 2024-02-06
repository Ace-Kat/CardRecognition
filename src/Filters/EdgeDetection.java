package Filters;

import Interfaces.PixelFilter;
import core.DImage;

import javax.smartcardio.Card;
import java.util.ArrayList;

public class EdgeDetection implements PixelFilter {

    private double[][] sobelEdgeDetectionX = {{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}};
    private double[][] sobleEdgeDetectionY = {{1, 0, -1}, {2, 0, -2}, {1, 0, -1}};
    /*  you can define others here */

    public EdgeDetection() {

    }

    @Override
    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();
        short[][] grey = img.getBWPixelGrid();

//       red = kernal(red, sobelEdgeDetectionX, sobleEdgeDetectionY);
//       green = kernal(green, sobelEdgeDetectionX, sobleEdgeDetectionY);
//       blue = kernal(blue, sobelEdgeDetectionX, sobleEdgeDetectionY);

        grey = kernal(grey, sobelEdgeDetectionX, sobleEdgeDetectionY);

//        img.setColorChannels(red, green, blue);
        img.setPixels(grey);
        return img;
    }

    private short[][] kernal(short[][] img, double[][] kernel) {
        double kernelWeight = 0;
        short[][] blank = new short[img.length][img[0].length];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                kernelWeight += kernel[i][j];
            }

        }
        for (int row = 1; row < img.length-1; row++) {
            for (int col = 1; col < img[0].length-1; col++) {
                int output = 0;
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        double kernelVal = kernel[i+1][j+1];
                        short imageVal = img[row+i][col+j];
                        output += (int)(kernelVal*imageVal);
                    }
                }
                output = (int) (output/kernelWeight);
                if(output < 0) output = 0;
                if(output > 255) output = 255;
                blank[row][col] = (short)(output);

            }

        }
        return blank;
    }

    private short[][] kernal(short[][] img, double[][] Gx, double[][] Gy) {
        double kernelWeight = 0;
        short[][] blank = new short[img.length][img[0].length];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                kernelWeight += Gx[i][j] + Gy[i][j];
            }

        }
        for (int row = 1; row < img.length-1; row++) {
            for (int col = 1; col < img[0].length-1; col++) {
                double outputx = 0;
                double outputy = 0;
                double output = 0;
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        double x = Gx[i+1][j+1];
                        double y = Gy[i+1][j+1];
                        short imageVal = img[row+i][col+j];
                        outputx += (int)(x*imageVal);
                        outputy += (int)(y*imageVal);

                    }
                }
                output = Math.sqrt(outputx*outputx+outputy*outputy);
                if(output < 254) output = 0;
                if(output > 255) output = 255;
                blank[row][col] = (short)(output);

            }

        }
        return blank;
    }


}
