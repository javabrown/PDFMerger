package com.javabrown.pdf;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.util.PDFMergerUtility;
 

public class PDFMerger {
	  private List<File> _pdfFileList;

	  public PDFMerger(){
	    _pdfFileList = new ArrayList<File>();
	  }

	  public void addPdfFile(File pdfPath){
	    _pdfFileList.add(pdfPath);
	  }

	  public void reset(){
	    _pdfFileList.clear();
	  }

	  public String merge(String outputDirectory){
	    if(_pdfFileList.size() > 0){
	      PDFMergerUtility merger = new PDFMergerUtility();

	      for(File pdfFile : _pdfFileList){
	        System.out.println("Added in queue"+ pdfFile.getName());
	        merger.addSource(pdfFile);
	      }

	      try {
	    	File scratch = new File(System.getProperty("java.io.tmpdir") + "result.pdf");
	    	
	    	merger.setDestinationFileName(scratch.getPath());
	        merger.mergeDocumentsNonSeq(new org.apache.pdfbox.io.RandomAccessFile(scratch, "rw"));
	        return merger.getDestinationFileName();
	      } catch (COSVisitorException | IOException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(new JFrame(),
	          "Error during file merge. Please make sure your input file in valid",
	          "Merge Error",
	          JOptionPane.ERROR_MESSAGE);
	      }
	    }

	    return null;
	  }
	}
