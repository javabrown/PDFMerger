package com.javabrown.pdf;

import java.io.IOException;
import java.net.Socket;
 

public class NativeData implements JBrownConstants {
	  private String _osInfo;
	  

	  public NativeData() {
	    _osInfo = System.getProperty("os.name").toLowerCase();
	  }

	  public boolean isWindows() {
	    return (_osInfo.indexOf("win") >= 0);
	  }

	  public boolean isMac() {
	    return (_osInfo.indexOf("mac") >= 0);
	  }

	  public boolean isUnix() {
	    return (_osInfo.indexOf("nix") >= 0 || _osInfo.indexOf("nux") >= 0 ||
	        _osInfo.indexOf("aix") > 0);
	  }

	  public boolean isSolaris() {
	    return (_osInfo.indexOf("sunos") >= 0);
	  }

	  public boolean isInternetOn(){
	    Socket socket = null;
	    boolean reachable = false;
	    try {
	      socket = new Socket(TEST_URL, 80);
	      reachable = true;
	    } catch(Exception ex){

	    }
	    finally {
	        if (socket != null) try { socket.close(); } catch(IOException e) {}
	    }

	    return reachable;
	  }
	}
