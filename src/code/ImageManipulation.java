package code;

import image.APImage;
import image.Pixel;

public class ImageManipulation {

    /** CHALLENGE 0: Display Image
     *  Write a statement that will display the image in a window
     */
    public static void main(String[] args) {
//        APImage img = new APImage("cyberpunk2077.jpg");
//        img.draw();
//        grayScale("cyberpunk2077.jpg");
//        blackAndWhite("cyberpunk2077.jpg");
        edgeDetection("cyberpunk2077.jpg",20);

    }

    /** CHALLENGE ONE: Grayscale
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a grayscale copy of the image
     *
     * To convert a colour image to grayscale, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * Set the red, green, and blue components to this average value. */
    public static void grayScale(String pathOfFile) {
        APImage img = new APImage(pathOfFile);
        int width = img.getWidth();
        int height = img.getHeight();
        for(int x = 0; x < width; x ++){
            for(int y = 0;y < height; y++){
                Pixel px = img.getPixel(x,y);
                int avg = getAverageColour(px);
                px.setRed(avg);
                px.setBlue(avg);
                px.setGreen(avg);
            }
        }
        img.draw();
    }

    /** A helper method that can be used to assist you in each challenge.
     * This method simply calculates the average of the RGB values of a single pixel.
     * @param pixel
     * @return the average RGB value
     */
    private static int getAverageColour(Pixel px) {
        int r = px.getRed();
        int g = px.getGreen();
        int b = px.getBlue();
        int avg = (r+g+b)/3;
        return avg;
    }

    /** CHALLENGE TWO: Black and White
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a black and white copy of the image
     *
     * To convert a colour image to black and white, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * If the average is less than 128, set the pixel to black
     * If the average is equal to or greater than 128, set the pixel to white */
    public static void blackAndWhite(String pathOfFile) {
        APImage img = new APImage(pathOfFile);
        int width = img.getWidth();
        int height = img.getHeight();
        for(int x = 0; x < width; x ++){
            for(int y = 0;y < height; y++){
                Pixel px = img.getPixel(x,y);
                int avg = getAverageColour(px);
                if(avg < 128){
                    px.setRed(0);
                    px.setGreen(0);
                    px.setBlue(0);
                } else{
                    px.setRed(255);
                    px.setGreen(255);
                    px.setBlue(255);
                }
            }
        }
        img.draw();
    }

    /** CHALLENGE Three: Edge Detection
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: an outline of the image. The amount of information will correspond to the threshold.
     *
     * Edge detection is an image processing technique for finding the boundaries of objects within images.
     * It works by detecting discontinuities in brightness. Edge detection is used for image segmentation
     * and data extraction in areas such as image processing, computer vision, and machine vision.
     *
     * There are many different edge detection algorithms. We will use a basic edge detection technique
     * For each pixel, we will calculate ...
     * 1. The average colour value of the current pixel
     * 2. The average colour value of the pixel to the left of the current pixel
     * 3. The average colour value of the pixel below the current pixel
     * If the difference between 1. and 2. OR if the difference between 1. and 3. is greater than some threshold value,
     * we will set the current pixel to black. This is because an absolute difference that is greater than our threshold
     * value should indicate an edge and thus, we colour the pixel black.
     * Otherwise, we will set the current pixel to white
     * NOTE: We want to be able to apply edge detection using various thresholds
     * For example, we could apply edge detection to an image using a threshold of 20 OR we could apply
     * edge detection to an image using a threshold of 35
     *  */
    public static void edgeDetection(String pathToFile, int threshold) {
        APImage img = new APImage(pathToFile);
        int width = img.getWidth();
        int height = img.getHeight();
        for(int x = img.getWidth()-1; x >= 0; x--){
            for(int y = img.getHeight()-1; y >= 0; y--){
                if ((x==0||y==0)) {
                    img.setPixel(x, y, new Pixel(255, 255, 255));
                    continue;
                }
                Pixel px = img.getPixel(x,y);
                int avgThis = getAverageColour(px);
                Pixel left = img.getPixel(x-1,y);
                int avgLeft = getAverageColour(left);
                Pixel below = img.getPixel(x,y-1);
                int avgBelow = getAverageColour(below);
                if ((Math.abs(avgThis-avgLeft)>threshold)||(Math.abs(avgThis-avgBelow)>threshold)){
                    px.setRed(0);
                    px.setGreen(0);
                    px.setBlue(0);
                } else{
                    px.setRed(255);
                    px.setGreen(255);
                    px.setBlue(255);
                }

            }
        }
        img.draw();
    }

    /** CHALLENGE Four: Reflect Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image reflected about the y-axis
     *
     */
    public static void reflectImage(String pathToFile) {
        APImage image = new APImage(pathToFile);

        Pixel[][] pixels = new Pixel[image.getWidth()][image.getHeight()];

        for (int x = 0; x < image.getWidth(); x++){

            for (int y = 0; y < image.getHeight(); y++){

                pixels[x][y] = image.getPixel(x, y);

            }

        }

        for (int x = 0; x < image.getWidth(); x++){

            for (int y = 0; y < image.getHeight(); y++){

                image.setPixel(x, y, pixels[image.getWidth()-x-1][y]);

            }
        }

        image.draw();

    }

    /** CHALLENGE Five: Rotate Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image rotated 90 degrees CLOCKWISE
     *
     *  */
    public static void rotateImage(String pathToFile) {

        APImage image = new APImage(pathToFile);

        Pixel[][] pixels = new Pixel[image.getWidth()][image.getHeight()];

        for (int x = 0; x < image.getWidth(); x++){


            for (int y = 0; y < image.getHeight(); y++){
                pixels[x][y] = image.getPixel(x, y);

            }



        }

        APImage image2 = new APImage(image.getHeight(), image.getWidth());

        for (int y = 0; y < image.getWidth(); y++){



            for (int x = 0; x < image.getHeight(); x++){
                if (image.getHeight()-1-x < image.getWidth() && y < image.getHeight())

                    
                    image2.setPixel(x, y, pixels[y][image.getHeight()-1-x]);
            }
        }

        image2.draw();
    }

}
