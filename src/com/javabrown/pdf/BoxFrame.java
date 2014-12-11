package com.javabrown.pdf;

import java.awt.BorderLayout;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class BoxFrame extends JFrame {
	public BoxFrame() {
		this.setTitle("JavaBrown - PDF Merge");
		this.setOSLookAndFeel();
		this.setSize(300, 300);
		this.setDefaultCloseOperation(3);
		this.getContentPane().setLayout(new BorderLayout(10, 10));
		this.getContentPane().add(new Dashboard(), BorderLayout.CENTER);
		this.setVisible(true);
	}

	private void setOSLookAndFeel() {
		NativeData nativeInfo = new NativeData();
		String OS = System.getProperty("os.name").toLowerCase();

		if (nativeInfo.isInternetOn() || true) {
			ImageIcon icon;
			try {
				icon = new ImageIcon(new URL(nativeInfo.TEST_URL));
				this.setIconImage(icon.getImage());
				System.out.println("set icon");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		try {
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}