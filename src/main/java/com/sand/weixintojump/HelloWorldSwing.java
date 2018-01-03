package com.sand.weixintojump;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.*;
public class HelloWorldSwing {
	
	private static final int HEIGHT = 1920/3;//640
	private static final int WIDTH = 1080/3; //360
    /**{
     * 创建并显示GUI。出于线程安全的考虑，
     * 这个方法在事件调用线程中调用。
     */
    private static void createAndShowGUI() {
    	
        // 确保一个漂亮的外观风格
        JFrame.setDefaultLookAndFeelDecorated(true);

        // 创建及设置窗口
        JFrame frame = new JFrame("HelloWorldSwing");
        
        long timestamp = System.currentTimeMillis();
		try {
			Runtime.getRuntime().exec("adb shell screencap -p /sdcard/screenshot_" + timestamp +".png");
			Thread.sleep(3000);
			Runtime.getRuntime().exec("adb pull /sdcard/screenshot_" + timestamp +".png f:/screenshot.png");
			Thread.sleep(1000);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
        ImageIcon bg=new ImageIcon("F:/screenshot.png");  
        
        final JLabel label=new JLabel(bg);  
        bg.setImage(bg.getImage().getScaledInstance(WIDTH, HEIGHT,Image.SCALE_DEFAULT));
        label.setBounds(0,0, WIDTH, HEIGHT);
        label.setSize(WIDTH, HEIGHT);
        label.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent e) {
				System.out.println(e.getX() + " " + e.getY());
				double length = App.calLength(e.getX(), e.getY());
				System.out.println("距离 ：" + length);
				try {
					Runtime.getRuntime().exec("adb shell input swipe 300 500 300 500 " + Math.round(length * 5.35));
					System.out.println("系数 :" +  Math.round(length * 5.35));
					long timestamp = System.currentTimeMillis();
					Thread.sleep(3000);
					Runtime.getRuntime().exec("adb shell screencap -p /sdcard/screenshot_" + timestamp +".png");
					Thread.sleep(3000);
					Runtime.getRuntime().exec("adb pull /sdcard/screenshot_" + timestamp +".png f:/screenshot.png");
					Thread.sleep(1000);
					ImageIcon bg=new ImageIcon("F:/screenshot.png");
					bg.setImage(bg.getImage().getScaledInstance(WIDTH, HEIGHT,Image.SCALE_DEFAULT));
					label.setIcon(bg);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}                                                                                                                                                           
			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}});
        
        // 添加面板
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // 显示窗口
        frame.pack();
        frame.setBounds(300, 0, 370, 670);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // 显示应用 GUI
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}