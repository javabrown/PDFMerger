package com.javabrown.pdf;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

public class Dashboard extends JPanel {
	  private PDFMerger _pdfMerger;

	  private JList _jList;
	  private DefaultListModel _listModel;

	  private JButton _addPdfFileBtn;
	  private JButton _joinBtn;
	  private JButton _resetBtn;
	  private JPanel _controlPanel;

	  public Dashboard() {
	    _pdfMerger = new PDFMerger();

	    _listModel = new DefaultListModel();
	    _jList = new JList(_listModel);

	    _addPdfFileBtn = new JButton("Add PDF File");
	    _joinBtn = new JButton("Join PDF");
	    _resetBtn = new JButton("Reset");

	    super.setLayout(new BorderLayout(10, 10));
	    super.setBorder(new EtchedBorder(EtchedBorder.LOWERED));


	    this.pushComponent();
	    this.registerEvent();
	  }

	  private final void pushComponent(){
	    _controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

	    this.add(_jList, BorderLayout.CENTER);
	    this.add(_controlPanel, BorderLayout.SOUTH);

	    _controlPanel.add(_addPdfFileBtn);
	    _controlPanel.add(_joinBtn);
	    _controlPanel.add(_resetBtn);
	  }

	  private final void registerEvent() {
	    final JFrame frame= (JFrame) SwingUtilities.getWindowAncestor(this);

	    _addPdfFileBtn.addActionListener(new ActionListener() {

	      @Override
	      public void actionPerformed(ActionEvent arg0) {
	          JFileChooser openFile = new JFileChooser();
	          int returnVal = openFile.showOpenDialog(null);

	          if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File pdfFile = openFile.getSelectedFile();
	            _pdfMerger.addPdfFile(pdfFile);
	            _listModel.addElement(pdfFile.getName());
	          } else {
	            System.out.println("Open command cancelled by user.");
	          }
	      }
	    });

	    _joinBtn.addActionListener(new ActionListener() {
	      @Override
	      public void actionPerformed(ActionEvent arg0) {
	    	  String workingDir = System.getProperty("user.dir");
	    	  System.out.println("wok:="+workingDir);
	        String mergedFileName = _pdfMerger.merge(workingDir);

	         if(mergedFileName != null){
	           JOptionPane.showMessageDialog(frame,
	               "Done, merged file:" + mergedFileName);
	         }

	         try {
	          Runtime.getRuntime().exec("explorer.exe /select,"+workingDir);
	          //Runtime.getRuntime().exec("start "+mergedFileName);
	        } catch (IOException e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	        }
	      }
	    });

	    _resetBtn.addActionListener(new ActionListener() {
	      @Override
	      public void actionPerformed(ActionEvent arg0) {
	        _listModel.removeAllElements();
	        _pdfMerger.reset();
	      }
	    });

	  }
	}
