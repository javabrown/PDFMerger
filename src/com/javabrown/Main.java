package com.javabrown;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.URL;

import org.apache.pdfbox.exceptions.COSVisitorException;

import com.javabrown.pdf.BoxFrame;

public class Main {
	public static void main(String[] args) throws COSVisitorException,
			IOException {
		Runnable r = new Runnable() {
			@Override
			public void run() {
				new BoxFrame();
			}
		};

		EventQueue.invokeLater(r);
	}
}