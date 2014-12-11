//package com.javabrown.pdf;
//
//
//import java.awt.BorderLayout;
//import java.awt.FlowLayout;
//import java.io.File;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.net.Socket;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.swing.JFrame;
//
//import java.awt.EventQueue;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.DefaultListModel;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFileChooser;
//import javax.swing.JList;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.SwingUtilities;
//import javax.swing.UIManager;
//import javax.swing.border.*;
//
//import org.apache.pdfbox.exceptions.COSVisitorException;
//import org.apache.pdfbox.util.PDFMergerUtility;
//
//public class PdfJoiner {
//  public static void main(String[] args) throws COSVisitorException,
//      IOException {
//    Runnable r = new Runnable() {
//      @Override
//      public void run() {
//        new BoxFrame();
//      }
//    };
//
//    EventQueue.invokeLater(r);
//  }
//}
//
////class BoxFrame extends JFrame {
////  public BoxFrame() {
////    this.setTitle("JavaBrown - PDF Merge");
////    this.setOSLookAndFeel();
////    this.setSize(300, 300);
////    this.setDefaultCloseOperation(3);
////    this.getContentPane().setLayout(new BorderLayout(10, 10));
////    this.getContentPane().add(new Dashboad(), BorderLayout.CENTER);
////    this.setVisible(true);
////  }
////
////  private void setOSLookAndFeel(){
////    NativeData nativeInfo = new NativeData();
////    String OS = System.getProperty("os.name").toLowerCase();
////
////    if(nativeInfo.isInternetOn() || true){
////      ImageIcon icon;
////      try {
////        icon = new ImageIcon(new URL(nativeInfo.TEST_URL));
////        this.setIconImage(icon.getImage());System.out.println("set icon");
////      } catch (MalformedURLException e) {
////        e.printStackTrace();
////      }
////    }
////
////    try {
////      //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
////      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
////    } catch (Exception e) {
////      e.printStackTrace();
////    }
////  }
////}
//
////class NativeData {
////  private String _osInfo;
////  public static String TEST_URL =
////      "http://www.javabrown.com/images/brown-logo.png";
////
////  public NativeData() {
////    _osInfo = System.getProperty("os.name").toLowerCase();
////  }
////
////  public boolean isWindows() {
////    return (_osInfo.indexOf("win") >= 0);
////  }
////
////  public boolean isMac() {
////    return (_osInfo.indexOf("mac") >= 0);
////  }
////
////  public boolean isUnix() {
////    return (_osInfo.indexOf("nix") >= 0 || _osInfo.indexOf("nux") >= 0 ||
////        _osInfo.indexOf("aix") > 0);
////  }
////
////  public boolean isSolaris() {
////    return (_osInfo.indexOf("sunos") >= 0);
////  }
////
////  public boolean isInternetOn(){
////    Socket socket = null;
////    boolean reachable = false;
////    try {
////      socket = new Socket(TEST_URL, 80);
////      reachable = true;
////    } catch(Exception ex){
////
////    }
////    finally {
////        if (socket != null) try { socket.close(); } catch(IOException e) {}
////    }
////
////    return reachable;
////  }
////}
//
////class Dashboad extends JPanel {
////  private PDFMerger _pdfMerger;
////
////  private JList _jList;
////  private DefaultListModel _listModel;
////
////  private JButton _addPdfFileBtn;
////  private JButton _joinBtn;
////  private JButton _resetBtn;
////  private JPanel _controlPanel;
////
////  public Dashboad() {
////    _pdfMerger = new PDFMerger();
////
////    _listModel = new DefaultListModel();
////    _jList = new JList(_listModel);
////
////    _addPdfFileBtn = new JButton("Add PDF File");
////    _joinBtn = new JButton("Join PDF");
////    _resetBtn = new JButton("Reset");
////
////    super.setLayout(new BorderLayout(10, 10));
////    super.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
////
////
////    this.pushComponent();
////    this.registerEvent();
////  }
////
////  private final void pushComponent(){
////    _controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
////
////    this.add(_jList, BorderLayout.CENTER);
////    this.add(_controlPanel, BorderLayout.SOUTH);
////
////    _controlPanel.add(_addPdfFileBtn);
////    _controlPanel.add(_joinBtn);
////    _controlPanel.add(_resetBtn);
////  }
////
////  private final void registerEvent() {
////    final JFrame frame= (JFrame) SwingUtilities.getWindowAncestor(this);
////
////    _addPdfFileBtn.addActionListener(new ActionListener() {
////
////      @Override
////      public void actionPerformed(ActionEvent arg0) {
////          JFileChooser openFile = new JFileChooser();
////          int returnVal = openFile.showOpenDialog(null);
////
////          if (returnVal == JFileChooser.APPROVE_OPTION) {
////            File pdfFile = openFile.getSelectedFile();
////            _pdfMerger.addPdfFile(pdfFile);
////            _listModel.addElement(pdfFile.getName());
////          } else {
////            System.out.println("Open command cancelled by user.");
////          }
////      }
////    });
////
////    _joinBtn.addActionListener(new ActionListener() {
////      @Override
////      public void actionPerformed(ActionEvent arg0) {
////        String mergedFileName =
////            _pdfMerger.merge("C:\\Users\\rkhan\\Desktop\\PdfMerger\\");
////
////         if(mergedFileName != null){
////           JOptionPane.showMessageDialog(frame,
////               "Done, merged file:" + mergedFileName);
////         }
////
////         try {
////          Runtime.getRuntime().exec("explorer.exe /select,"+mergedFileName);
////          //Runtime.getRuntime().exec("start "+mergedFileName);
////        } catch (IOException e) {
////          // TODO Auto-generated catch block
////          e.printStackTrace();
////        }
////      }
////    });
////
////    _resetBtn.addActionListener(new ActionListener() {
////      @Override
////      public void actionPerformed(ActionEvent arg0) {
////        _listModel.removeAllElements();
////        _pdfMerger.reset();
////      }
////    });
////
////  }
////}
//
////class PDFMerger {
////  private List<File> _pdfFileList;
////
////  public PDFMerger(){
////    _pdfFileList = new ArrayList<File>();
////  }
////
////  public void addPdfFile(File pdfPath){
////    _pdfFileList.add(pdfPath);
////  }
////
////  public void reset(){
////    _pdfFileList.clear();
////  }
////
////  public String merge(String outputDirectory){
////    if(_pdfFileList.size() > 0){
////      PDFMergerUtility merger = new PDFMergerUtility();
////
////      for(File pdfFile : _pdfFileList){
////        System.out.println("Added in queue"+ pdfFile.getName());
////        merger.addSource(pdfFile);
////      }
////
////      merger.setDestinationFileName(outputDirectory + "\\result.pdf");
////
////      try {
////        merger.mergeDocuments();
////        return merger.getDestinationFileName();
////      } catch (COSVisitorException | IOException e) {
////        e.printStackTrace();
////        JOptionPane.showMessageDialog(new JFrame(),
////          "Error during file merge. Please make sure your input file in valid",
////          "Merge Error",
////          JOptionPane.ERROR_MESSAGE);
////      }
////    }
////
////    return null;
////  }
////}
