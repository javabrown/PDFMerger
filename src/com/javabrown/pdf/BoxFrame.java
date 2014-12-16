package com.javabrown.pdf;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.UIManager;

public class BoxFrame extends JFrame {
	public BoxFrame() {
		this.setTitle("JavaBrown - PDF Merge");
		this.setOSLookAndFeel();
		this.setSize(300, 300);
		this.setMinimumSize(new Dimension(300, 300));
		this.setDefaultCloseOperation(3);
		this.getContentPane().setLayout(new BorderLayout(10, 10));
		this.getContentPane().add(new Dashboard(), BorderLayout.CENTER);
		//this.setLayeredPane(getBackgroundLayer());
		this.setVisible(true);
	}

	public JLayeredPane getBackgroundLayer() {
		String IMAGE_SITE = "http://internetofeverything.cisco.com/sites/"
				+ "default/files/styles/image_text_tile/public/"
				+ "link_tiles/Marquee_images.png";

		JLayeredPane layeredPanel = new JLayeredPane();

		//layeredPanel.setPreferredSize(new Dimension(200, 200));
		try {
			URL url = new URL(IMAGE_SITE);
			BufferedImage image = ImageIO.read(url);
			ImagePanel imagePanel = new ImagePanel(image);
			imagePanel.setSize(layeredPanel.getPreferredSize());

			//layeredPanel.add(imagePanel, JLayeredPane.DEFAULT_LAYER);
			layeredPanel.add(imagePanel, JLayeredPane.PALETTE_LAYER);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		return layeredPanel;
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