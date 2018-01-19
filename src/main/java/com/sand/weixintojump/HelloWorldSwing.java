package com.sand.weixintojump;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

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
			Process p = Runtime.getRuntime().exec("adb shell screencap -p /sdcard/screenshot_" + timestamp +".png");
			p.waitFor();
			Thread.sleep(1000);
			Process p1 = Runtime.getRuntime().exec("adb pull /sdcard/screenshot_" + timestamp +".png E:/screenshot.png");
			p1.waitFor();
		} catch (Exception e2) {
		}
        ImageIcon bg=new ImageIcon("E:/screenshot.png");  
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
					int max=500;
			        int min=300;
			        Random random = new Random();
			        int x = random.nextInt(max)%(max-min+1) + min;
			        int y = random.nextInt(max)%(max-min+1) + min;
			        StringBuilder s = new StringBuilder();
			        s.append(x + " ").append(y + " ").append(x + " ").append(y + " ");
			        System.out.println("exe adb shell input swipe " +s+  Math.round(length * 4.25));
					Process p = Runtime.getRuntime().exec("adb shell input swipe " +s+  Math.round(length * 4.25));
					System.out.println("系数 :" +  Math.round(length * 4.25));
					long timestamp = System.currentTimeMillis();
					p.waitFor();
					Thread.sleep(1000);
					Process p1 = Runtime.getRuntime().exec("adb shell screencap -p /sdcard/screenshot_" + timestamp +".png");
					p1.waitFor();
					Process p2 = Runtime.getRuntime().exec("adb pull /sdcard/screenshot_" + timestamp +".png E:/screenshot.png");
					p2.waitFor();
					ImageIcon bg=new ImageIcon("E:/screenshot.png");
					bg.setImage(bg.getImage().getScaledInstance(WIDTH, HEIGHT,Image.SCALE_DEFAULT));
					label.setIcon(bg);
				} catch (Exception e1) {
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