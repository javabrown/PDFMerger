package com.javabrown.pdf;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.plaf.LayerUI;

import com.javabrown.components.BrownIcon;

public class BoxFrame extends JFrame implements JBrownConstants{
	
	
	public BoxFrame() {
		this.setTitle("PDF Merge - JavaBrown");
		this.setOSLookAndFeel();
		this.setSize(500, 400);
		
		this.setMinimumSize(new Dimension(500, 400));
		this.setDefaultCloseOperation(3);
		this.setNavigation();
		
		JPanel panel = new JPanel(new BorderLayout(10,10));
		//panel.add(new Dashboard(), BorderLayout.CENTER);
		
		//LayerUI<JPanel> layerUI = new JBrownUI();
		JBrownProcessLayerUI layerUI = new JBrownProcessLayerUI();
		
		panel.add(new Dashboard(layerUI), BorderLayout.CENTER);
		
		JLayer<JPanel> layer = new JLayer<JPanel>(panel, layerUI);
		
//		final Timer stopper = new Timer(4000, new ActionListener() {
//		      public void actionPerformed(ActionEvent ae) {
//		        layerUI.stop();
//		      }
//		});
//		stopper.setRepeats(false);
		    
		this.add(layer);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void setNavigation(){
		JMenu menu = new JMenu("File");
		JMenuBar menuBar = new JMenuBar();
		final JFrame root = this;
		
		menu.setMnemonic(KeyEvent.VK_F);
		
		JMenuItem about = new JMenuItem("About", 
				new BrownIcon("icons/icon_about.png").getScaledIcon(25, 25));
		about.setMnemonic(KeyEvent.VK_A);
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(root,
				  "Designed & Developed by Raja Khan, Javabrown Foundation");
			}
		});
		
		
		JMenuItem exit = new JMenuItem("Exit", 
				new BrownIcon("icons/icon_exit.png").getScaledIcon(25, 25));
		exit.setMnemonic(KeyEvent.VK_E);
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		menu.add(about);
		menu.add(exit);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
	}
	
	public JLayeredPane getBackgroundLayer() {
		JLayeredPane layeredPanel = new JLayeredPane();

		//layeredPanel.setPreferredSize(new Dimension(200, 200));
		try {
			BufferedImage image = ImageIO.read(new File(IMAGE_SITE));
			ImagePanel imagePanel = new ImagePanel(image);
			imagePanel.setSize(layeredPanel.getPreferredSize());

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

class JBrownUI extends LayerUI<JComponent> {
	public void paint(Graphics g, JComponent c){
		super.paint(g, c);
		
		Graphics2D g2 = (Graphics2D) g.create();
		int w = c.getWidth();
		int h = c.getHeight();
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f));
		g2.setPaint(new GradientPaint(0, 0, Color.yellow, 0, h, Color.red));
		g2.fillRect(0, 0, w, h);
		
		g2.dispose();
	}
}