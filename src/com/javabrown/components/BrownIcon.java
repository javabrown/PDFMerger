package com.javabrown.components;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * @author Raja Khan
 *
 * Modified version of ImageIcon to modify the icon size
 */
public class BrownIcon extends ImageIcon {
	public BrownIcon(String iconWithPath) {
		super(iconWithPath);
	}

	public ImageIcon getScaledIcon(int width, int height) {
		Image img = this.getImage();
		Image newimg = img.getScaledInstance(width, height,
				java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newimg);
	}
}
