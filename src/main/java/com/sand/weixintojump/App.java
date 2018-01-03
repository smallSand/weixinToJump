package com.sand.weixintojump;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * Hello world!
 *
 */
public class App {

	private static final int HEIGHT = 1920;
	private static final int WIDTH = 1080;
	private static final int SCOPE_HEIGHT = 300;

	public static void main(String[] args) {
		try {
			BufferedImage image = ImageIO.read(new File("F:/screenshot.png"));
			int[] rgb = new int[3];

			// 棋子坐标
			int ball_x = 0;
			int ball_y = 0;
			out: for (int y = 1919; y > 0; y--) {
				for (int x = 0; x < WIDTH - 1; x++) {
					rgb = toRgbArray(image.getRGB(x, y));
					if (rgb[0] == 54 && rgb[1] == 58 && rgb[2] == 99) {
						ball_x = x + 8;
						ball_y = y - 23;
						break out;
					}
				}
			}

			System.out.println("棋子坐标: " + ball_x + " " + ball_y);
			// 下一个方块坐标
			int block_x = 0;
			int block_y = 0;

			out: for (int y = 300; y < HEIGHT - 1; y++) {
				for (int x = 0; x < WIDTH - 1; x++) {
					rgb = toRgbArray(image.getRGB(x, y));
					if (rgb[0] == 100 && rgb[1] == 149 && rgb[2] == 105) { // 绿色方块
						System.out.println("接触点坐标: " + x + " " + y);
						block_x = x;
						block_y = y;
						while (true) {
							block_x = block_x + 1;
							if (!isSameColar(toRgbArray(image.getRGB(x, y)), toRgbArray(image.getRGB(block_x, block_y)))) {
								break;
							}
						}
						System.out.println("中点坐标" + (block_x + x) / 2 + " " + block_y);
						break out;
					} else if (rgb[0] == 246 && rgb[1] == 246 && rgb[2] == 246) {// 白色圆桌
						System.out.println("接触点坐标: " + x + " " + y);
						block_x = x;
						block_y = y;
						while (true) {
							block_x = block_x + 1;
							if (!isSameColar(toRgbArray(image.getRGB(x, y)), toRgbArray(image.getRGB(block_x, block_y)))) {
								break;
							}
						}
						System.out.println("中点坐标" + (block_x + x) / 2 + " " + block_y);
						break out;
					}

				}
			}
			System.out.println("下一个方块坐标: " + block_x + " " + block_y);

			// 计算距离
			double length = Math.sqrt(Math.pow(Math.abs(block_x - ball_x), 2) + Math.pow(Math.abs(block_y - ball_y), 2));
			System.out.println(length);

			System.out.println(isSameColar(new int[] { 100, 149, 105 }, new int[] { 100, 149, 105 }));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 将一个数字转换为RGB数字
	private static int[] toRgbArray(int pixel) {
		int[] rgb = new int[3];
		rgb[0] = (pixel & 0xff0000) >> 16;
		rgb[1] = (pixel & 0xff00) >> 8;
		rgb[2] = (pixel & 0xff);
		return rgb;
	}

	// 距离小于10
	private static boolean isSameColar(int[] rgb1, int[] rgb2) {
		double length = Math.sqrt(Math.pow(Math.abs(rgb1[0] - rgb2[0]), 2) + Math.pow(Math.abs(rgb1[1] - rgb2[1]), 2) + Math.pow(Math.abs(rgb1[2] - rgb2[2]), 2));
		return length < 10;
	}

	// 计算距离
	public static double calLength(int block_x, int block_y) {
		double length = 0;
		try {
			BufferedImage image = ImageIO.read(new File("F:/screenshot.png"));
			int[] rgb = new int[3];
			// 棋子坐标
			int ball_x = 0;
			int ball_y = 0;
			out: for (int y = 640; y > 0; y--) {
				for (int x = 0; x < 360 - 1; x++) {
					rgb = toRgbArray(image.getRGB(x, y));
					if (rgb[0] == 54 && rgb[1] == 58 && rgb[2] == 99) {
						ball_x = x + 8;
						ball_y = y - 23;
						break out;
					}
				}
			}
			System.out.println("棋子坐标: " + ball_x / 3 + " " + ball_y / 3);
			// 计算距离
			length = Math.sqrt(Math.pow(Math.abs(block_x / 3 - ball_x / 3), 2) + Math.pow(Math.abs(block_y / 3 - ball_y / 3), 2));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return length;
	}
}
