import java.awt.Color;
import java.awt.image.PixelGrabber;
import java.util.Scanner;

public class ImageProcessor {

	public static void main(String[] args) {
		


		ImageUtils utils = new ImageUtils();
		Color[][] ogPic = utils.loadImage("Src/LennaCV.png");
		Color[][] Pic2 = utils.loadImage("Src/FlyingWV.jpg");

		utils.addImage(ogPic, "Original");
		utils.addImage(FlyingWV(Pic2,ogPic), "FlyingWV");
		utils.addImage(coldTransform(ogPic), "Cold Colors");
		utils.addImage(invertedPic(ogPic), "Invert");
		utils.addImage(hotTransform(ogPic), "Hot Colors");
		utils.addImage(tintPic(ogPic), "Tint");
		utils.addImage(shadows(ogPic), "Shadows");
		utils.addImage(blackWhite(ogPic), "Black and White");

		utils.display();

	}

	public static Color[][] FlyingWV(Color[][] wv, Color[][] Image) {
		Color[][] Wv = ImageUtils.cloneArray(wv);
		Color[][] image = ImageUtils.cloneArray(Image);

		for (int row = 0; row < Wv.length; row++) {
			for (int col = 0; col < Wv[row].length; col++) {
				Color pixel = Wv[row][col];

				if (pixel.getGreen() < 100) {
					image[row][col] = new Color(0,0,255);
				}

			}
		}
		return image;
	}

	public static Color[][] coldTransform(Color[][] Image) {
		Color[][] coldTransform = ImageUtils.cloneArray(Image);
		int r = 0;
		int g = 0;
		int b = 0;
		for (int row = 0; row < coldTransform.length; row++) {
			for (int col = 0; col < coldTransform[row].length; col++) {
				r = coldTransform[row][col].getRed()/2;
				g = coldTransform[row][col].getGreen();
				b = coldTransform[row][col].getBlue();

				coldTransform[row][col] = new Color(r,g,b);
			}
		}
		return coldTransform; 
	}
	public static Color[][] tintPic(Color[][] Image) {
		Color[][] tintPic = ImageUtils.cloneArray(Image);
		int r = 0;
		int g = 0;
		int b = 0;
		int inputr = 0;
		int inputg = 0;
		int inputb = 0;
		Scanner input = new Scanner(System.in);
		String buffer = null;
		System.out.println("I am going to ask for RGB values for the Tint option!");
		System.out.println("Please enter an R value!(0-255)");
		inputr = input.nextInt();
		buffer = input.nextLine();
		System.out.println("Please enter a R value!(0-255)");
		inputg = input.nextInt();
		buffer = input.nextLine();
		System.out.println("Please enter a B value!(0-255)");
		inputb = input.nextInt();
		buffer = input.nextLine();
		for (int row = 0; row < tintPic.length; row++) {
			for (int col = 0; col < tintPic[row].length; col++) {
				r = tintPic[row][col].getRed()/5 + (inputr/5);
				g = tintPic[row][col].getGreen()/5 + (inputg/5);
				b = tintPic[row][col].getBlue()/5 + (inputb/5);

				if(r<0){
					r=0;
				}
				if(g<0){
					g=0;
				}
				if(b<0){
					b=0;
				}
				if(r>255){
					r=255;
				}
				if(g>255){
					g=255;
				}
				if(b>255){
					b=255;
				}

				tintPic[row][col] = new Color(r,g,b);
			}
		}
		return tintPic; 
	}



	public static Color[][] invertedPic(Color[][] Image) {
		Color[][] invertedPic = ImageUtils.cloneArray(Image);
		int r = 0;
		int g = 0;
		int b = 0;
		for (int row = 0; row < invertedPic.length; row++) {
			for (int col = 0; col < invertedPic[row].length; col++) {
				r = 255-invertedPic[row][col].getRed();
				g = 255-invertedPic[row][col].getGreen();
				b = 255-invertedPic[row][col].getBlue();


				invertedPic[row][col] = new Color(r,g,b);
			}
		}
		return invertedPic; 
	}

	public static Color[][] hotTransform(Color[][] Image) {
		Color[][] hotTransform = ImageUtils.cloneArray(Image);
		int r = 0;
		int g = 0;
		int b = 0;
		for (int row = 0; row < hotTransform.length; row++) {
			for (int col = 0; col < hotTransform[row].length; col++) {
				r = hotTransform[row][col].getRed();
				g = hotTransform[row][col].getGreen()/2;
				b = hotTransform[row][col].getBlue()/2;


				hotTransform[row][col] = new Color(r,g,b);
			}
		}
		return hotTransform; 
	}
	public static Color[][] shadows(Color[][] Image) {
		Color[][] shadows = ImageUtils.cloneArray(Image);
		int r = 0;
		int g = 0;
		int b = 0;
		for (int row = 0; row < shadows.length; row++) {
			for (int col = 0; col < shadows[row].length; col++) {
				if((shadows[row][col].getRed()+shadows[row][col].getGreen()+shadows[row][col].getBlue()) > 382){
					r = shadows[row][col].getRed()*(5/3);
					g = shadows[row][col].getGreen()*(5/3);
					b = shadows[row][col].getBlue()*(5/3);
				}else{
					r = shadows[row][col].getRed()*(1/2);
					g = shadows[row][col].getGreen()*(1/2);
					b = shadows[row][col].getBlue()*(1/2);
				}

				shadows[row][col] = new Color(r,g,b);
			}
		}
		return shadows; 
	}
	public static Color[][] blackWhite(Color[][] Image) {
		Color[][] blackWhite = ImageUtils.cloneArray(Image);


		float[] hsb = new float[3];
		for (int row = 0; row < blackWhite.length; row++) {
			for (int col = 0; col < blackWhite[row].length; col++) {

				Color.RGBtoHSB(blackWhite[row][col].getRed(),blackWhite[row][col].getGreen(),blackWhite[row][col].getBlue(), hsb);
				hsb[1] = hsb[1]*(7/8);
				blackWhite[row][col] = Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
			}
		}
		return blackWhite; 
	}

}


