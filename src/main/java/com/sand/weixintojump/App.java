package com.sand.weixintojump;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * Hello world!
 *
 */
public class App 
{
	
	private static final int HEIGHT = 1920;
	private static final int WIDTH = 1080;
	private static final int SCOPE_HEIGHT = 300;
	
    public static void main( String[] args )
    {
       try {
		BufferedImage image = ImageIO.read(new File("F:/adb/screenshot2.png"));
		int[] rgb = new int[3];
		
		//棋子坐标
		int ball_x = 0; 
		int ball_y = 0; 
		out : for(int y = 1919 ; y > 0 ; y--){
			for(int x = 0 ; x < WIDTH -1 ; x++){
				rgb = toRgbArray(image.getRGB(x, y));
				if(rgb[0] == 54 && rgb[1] == 58 && rgb[2] == 99){
					ball_x = x + 8;
					ball_y = y - 23;
					break out;
				}
			}
		}
		
		System.out.println("棋子坐标: " + ball_x + " "+ ball_y);
		//下一个方块坐标
		int block_X = 0; 
		int block_y = 0;
		
		out : for(int y = 300 ; y < HEIGHT - 1 ; y++){
			for(int x = 0 ; x < WIDTH -1 ; x++){
				rgb = toRgbArray(image.getRGB(x, y));
				if(rgb[0] == 100 && rgb[1] == 149 && rgb[2] == 105){ //绿色方块
					block_X = x + 1;
					block_y = y + 130;
					break out;
				}
				else if(rgb[0] == 246 && rgb[1] == 246 && rgb[2] == 246){//白色圆桌
					block_X = x + 11;
					block_y = y + 74;
					break out;
				}
			}
		}
		System.out.println("下一个方块坐标: " + block_X + " "+ block_y);
		
		//计算距离
		double length = Math.sqrt(Math.pow(Math.abs(block_X - ball_x), 2) 
				+ Math.pow(Math.abs(block_y - ball_y), 2));
		System.out.println(length);
	} catch (Exception e) {
		e.printStackTrace();
	}
    }
    
     //将一个数字转换为RGB数字  
    private static int[] toRgbArray(int pixel){
    	int[] rgb = new int[3];
        rgb[0] = (pixel & 0xff0000) >> 16;  
        rgb[1] = (pixel & 0xff00) >> 8;  
        rgb[2] = (pixel & 0xff); 
    	return rgb;
    }
}
