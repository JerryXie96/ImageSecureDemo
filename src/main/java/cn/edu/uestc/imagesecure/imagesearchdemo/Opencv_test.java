package cn.edu.uestc.imagesecure.imagesearchdemo;

import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.MatOfPoint;
import org.opencv.objdetect.HOGDescriptor;

public class Opencv_test {
    public static double[] test(String path) {
        System.loadLibrary("opencv_java341");
        double[] ret=new double[CryptoMaterial.originalvectorLength];
        Mat img = Imgcodecs.imread(path);
        Imgproc.resize(img, img,new Size(64,128));
        HOGDescriptor hog = new HOGDescriptor(new Size(64,128),new Size(64,128),new Size(3,3),new Size(8,8),9);

        MatOfFloat mod = new MatOfFloat();
        MatOfPoint locations = new MatOfPoint();
        hog.compute(img, mod,new Size(0,0),new Size(0,0),locations);
        for(int i=0;i<CryptoMaterial.originalvectorLength;i++){
            ret[i]=mod.toArray()[i];
        }
        return ret;
    }
}