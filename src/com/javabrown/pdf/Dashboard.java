package com.javabrown.pdf;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.LayerUI;

public class Dashboard extends JPanel {
	private PDFMerger _pdfMerger;

	private JList _jList;
	private DefaultListModel _listModel;

	private JButton _addPdfFileBtn;
	private JButton _removePdfFileBtn;
	private JButton _joinBtn;
	private JButton _resetBtn;
	private JPanel _controlPanel;
	private JBrownProcessLayerUI _busyLayerUI;
	
	final Timer stopper = new Timer(4000, new ActionListener() {
	      public void actionPerformed(ActionEvent ae) {
	        _busyLayerUI.stop();
	      }
	});
	
	public Dashboard(JBrownProcessLayerUI layerUI) {
		this.setBackground(new Color(0,0,0,125));
		
		_pdfMerger = new PDFMerger();
		
		_busyLayerUI = layerUI;
		
		_listModel = new DefaultListModel();
		_jList = new JList(_listModel);
		_jList.setCellRenderer(new ListCellRenderer());
		
		_addPdfFileBtn = new JButton("Add");
		_removePdfFileBtn = new JButton("Remove");
		_joinBtn = new JButton("Join");
		_resetBtn = new JButton("Reset");

		super.setLayout(new BorderLayout(10, 10));
		super.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

		this.pushComponent();
		this.registerEvent();
	}

	private final void pushComponent() {
		_controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

		this.add(_jList, BorderLayout.CENTER);
		this.add(_controlPanel, BorderLayout.SOUTH);

		_controlPanel.add(_addPdfFileBtn);
		_controlPanel.add(_removePdfFileBtn);
		_controlPanel.add(_joinBtn);
		_controlPanel.add(_resetBtn);
	}

	private final void registerEvent() {
		final JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);

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

		_removePdfFileBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = _jList.getSelectedIndex();
				if (index != -1) {
					_listModel.remove(index);
				}
			}
		});

		_joinBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				_busyLayerUI.start();
				if (!stopper.isRunning()) {
		            stopper.start();
		        }
				
				String workingDir = System.getProperty("user.dir");
				System.out.println("wok:=" + workingDir);
				String mergedFileName = _pdfMerger.merge(workingDir);

				if (mergedFileName != null) {
					JOptionPane.showMessageDialog(frame, "Done, merged file:"
							+ mergedFileName);
				}

				try {
					Runtime.getRuntime().exec(
							"explorer.exe /select," + System.getProperty("java.io.tmpdir"));
					// Runtime.getRuntime().exec("start "+mergedFileName);
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

class ImagePanel extends JPanel {
	private Image image;

	public ImagePanel(Image image) {
		this.image = image;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			g.drawImage(image, 0, 0, null);
		}
	}
}

class ListCellRenderer extends DefaultListCellRenderer {
    final static ImageIcon shortIcon = 
    		new ImageIcon("icons/file_extension_pdf.png");

    /* This is the only method defined by ListCellRenderer.  We just
     * reconfigure the Jlabel each time we're called.
     */
    public Component getListCellRendererComponent(
        JList list,
        Object value,   // value to display
        int index,      // cell index
        boolean iss,    // is the cell selected
        boolean chf)    // the list and the cell have the focus
    {
        /* The DefaultListCellRenderer class will take care of
         * the JLabels text property, it's foreground and background
         * colors, and so on.
         */
        super.getListCellRendererComponent(list, value, index, iss, chf);

        /* We additionally set the JLabels icon property here.
         */
        String s = value.toString();
        setIcon(shortIcon);

        return this;
    }
}