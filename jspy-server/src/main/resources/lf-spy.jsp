<%@ page contentType="text/html; charset=gb2312" %>
<%
/**
 * Java服务器测试用探针
 *
 * @project LF-Spy
 * @version 0.1.0
 * @author chenpeng  
 * @email：ceponline@yahoo.com.cn 
 **/
 class LfSpy {
 
        boolean supportHibernate = false;

		boolean supportJNDI = false;

		boolean supportJavaxSql = false;

		boolean supportJAF = false;

		boolean supportMail = false;

		boolean supportBeanUtils = false;

		boolean supportCommonLogging = false;

		boolean supportCommonCodec = false;

		boolean supportCommonCollection = false;

		boolean supportCommonDigester = false;

		boolean supportCommonLang = false;

		boolean supportJakartaRegExp = false;

		boolean supportLucene = false;

		boolean supportDom4j = false;
		
		boolean supportLoonframework = false;

		boolean supportMmMysqlDriver = false;

		boolean supportComMysqlDriver = false;

		boolean supportImageProcessing = false;
		
		boolean supportStruts = false;
		
		boolean supportSpring = false;

		String serverName;
		
		String serverIP="127.0.0.1";
		
        long startTime = System.currentTimeMillis(); 
 
        long startMemory = Runtime.getRuntime().freeMemory(); 
        
        java.util.Properties prop = System.getProperties();
	    
	    String javaVersion=prop.getProperty("java.version");
	    
        String FS=prop.getProperty("file.separator");
        
         int CPUTIME = 30;

	     int PERCENT = 100;
	     
		public LfSpy() {
		
			try {
				Class.forName("org.springframework.context.ApplicationContext");
				supportSpring = true;
			} catch (ClassNotFoundException ex) {
			}
			
			try {
				Class.forName("org.loon.framework.Loon");
				supportLoonframework = true;
			} catch (ClassNotFoundException ex) {
			}
		
			try {
				Class.forName("org.hibernate.Transaction");
				supportHibernate = true;
			} catch (ClassNotFoundException ex) {
			}

	        try {
				Class.forName("org.apache.struts.action.ActionServlet");
				supportStruts = true;
			} catch (ClassNotFoundException ex) {
			}
			
			try {
				Class.forName("javax.naming.Name");
				supportJNDI = true;
			} catch (ClassNotFoundException ex) {
			}

			try {
				Class.forName("javax.sql.DataSource");
				supportJavaxSql = true;
			} catch (ClassNotFoundException ex) {
			}

			try {
				Class.forName("javax.activation.DataSource");
				supportJAF = true;
			} catch (ClassNotFoundException ex) {
			}

			try {
				Class.forName("javax.mail.Message");
				supportMail = true;
			} catch (ClassNotFoundException ex) {
			}

			try {
				Class.forName("org.apache.commons.beanutils.MethodUtils");
				supportBeanUtils = true;
			} catch (ClassNotFoundException ex) {
			}

			try {
				Class.forName("org.apache.commons.logging.LogFactory");
				supportCommonLogging = true;
			} catch (ClassNotFoundException ex) {
			}

			try {
				Class.forName("org.apache.commons.codec.Decoder");
				supportCommonCodec = true;
			} catch (ClassNotFoundException ex) {
			}

			try {
				Class.forName("org.apache.commons.collections.ArrayStack");
				supportCommonCollection = true;
			} catch (ClassNotFoundException ex) {
			}

			try {
				Class.forName("org.apache.commons.digester.Digester");
				supportCommonDigester = true;
			} catch (ClassNotFoundException ex) {
			}

			try {
				Class.forName("org.apache.commons.lang.SystemUtils");
				supportCommonLang = true;
			} catch (ClassNotFoundException ex) {
			}

			try {
				Class.forName("org.apache.regexp.RE");
				supportJakartaRegExp = true;
			} catch (ClassNotFoundException ex) {
			}

			try {
				Class.forName("org.apache.lucene.index.IndexWriter");
				supportLucene = true;
			} catch (ClassNotFoundException ex) {
			}

			try {
				Class.forName("org.dom4j.Document");
				supportDom4j = true;
			} catch (ClassNotFoundException ex) {
			}

			try {
				Class.forName("org.gjt.mm.mysql.Driver");
				supportMmMysqlDriver = true;
			} catch (ClassNotFoundException ex) {
			}

			try {
				Class.forName("com.mysql.jdbc.Driver");
				supportComMysqlDriver = true;
			} catch (ClassNotFoundException ex) {
			}
			
			loadAddress();

			try {
				java.awt.image.BufferedImage bufferedImage = new java.awt.image.BufferedImage(
						10, 10, java.awt.image.BufferedImage.TYPE_INT_RGB);
				java.awt.Graphics2D g = bufferedImage.createGraphics();
				g.drawLine(0, 0, 10, 10);
				g.dispose();

				supportImageProcessing = true;
			} catch (Throwable ex) {
			}

		}
		
	public String getOSarch(){
	return prop.getProperty("os.arch");
	}
	public String getTimeZone(){
	return prop.getProperty("user.timezone");
	}
    public String getNowUser(){
	return prop.getProperty("user.name");
	}
	public String getNowUserDir(){
	return prop.getProperty("user.dir");
	}
    public String getOSName(){
     return prop.getProperty("os.name");
    }
    public String getSystemModel(){
	return prop.getProperty("sun.arch.data.model");
	}
	final private long getDiskForLinuxInfo(final String dirPath) {
		try {
			String dir = dirPath.startsWith("/") ? dirPath : "/"+dirPath;
			long space = -1;
			Process process;
			Runtime run = Runtime.getRuntime();
			String osName = System.getProperty("os.name").toLowerCase();
			String command = "";
			if (osName.startsWith("linux")) {
				command = "df -k " + dir;
			}
			process = run.exec(command);
			java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(
					process.getInputStream()));
			String freeSpace = "", line;
			while ((line = in.readLine()) != null) {
				if (line.length() > 0) {
					freeSpace = line;
				}
			}
			if (freeSpace == null || freeSpace.length() == 0) {
				return -1;
			}
			process.destroy();
			freeSpace = freeSpace.trim().replaceAll("\\\\", "\\/");
			String[] results = freeSpace.split(" ");
			for (int i = results.length - 1; i > 0; i--) {
				try {
					space = Long.parseLong(results[i]);
					return space;
				} catch (NumberFormatException ex) {
					continue;
				}
			}
		} catch (java.io.IOException e) {
			return -1;
		}
		return -1;
	}

	/**
	 * 获得windows下指定地址硬盘空间大小
	 * 
	 * @param dirPath
	 * @return
	 */
	final private long getDiskForWindowsInfo(String dirPath) {
		try {
			long space = -1;
			Process process;
			Runtime run = Runtime.getRuntime();
			String osName = System.getProperty("os.name").toLowerCase();
			String command = "";
			if (osName.startsWith("windows") && osName.indexOf("98") == -1) {
				command = "cmd.exe /c dir " + dirPath;
			} else if (osName.startsWith("windows")
					&& osName.indexOf("98") != -1) {
				command = "command.com /c dir " + dirPath;
			}
			process = run.exec(command);
			java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(
					process.getInputStream()));
			String freeSpace = "", line;
			while ((line = in.readLine()) != null) {
				freeSpace = line;
			}
			if (freeSpace == null) {
				return -1;
			}
			process.destroy();
			freeSpace = freeSpace.trim();
			freeSpace = freeSpace.replaceAll("\\\\\\\\\\\\\\\\.", "");
			freeSpace = freeSpace.replaceAll(",", "");
			String[] results = freeSpace.split(" ");
			for (int i = 1; i < results.length; i++) {
				try {
					space = Long.parseLong(results[i]);
					return space;
				} catch (NumberFormatException ex) {
					continue;
				}
			}
			return space;
		} catch (java.io.IOException e) {
			return -1;
		}
	}

	public long getObjectDisk() {
		String path = prop.getProperty("user.dir");
		int index = path.indexOf(FS);
		path = path.substring(0, index);
		String osName = System.getProperty("os.name").toLowerCase();
		if (osName.startsWith("windows")) {
			return getDiskForWindowsInfo(path);
		} else if (osName.startsWith("linux")) {
			return getDiskForLinuxInfo(path);
		} else {
			return -1;
		}
	}
	
	public final  String getMacAddress()  {
			String os = System.getProperty("os.name");
			try {
				if(os.startsWith("Windows")) {
					return windowsParseMacAddress(windowsRunIpConfigCommand());
				} else if(os.startsWith("Linux")) {
					return linuxParseMacAddress(linuxRunIfConfigCommand());
				} else {
					throw new java.io.IOException("unknown operating system: " + os);
				}
			} catch(Exception ex) {
				return "Nothing";
			}
		}
		
	
		private final  String linuxParseMacAddress(String ipConfigResponse) throws java.text.ParseException {
			String localHost = null;
			try {
				localHost = java.net.InetAddress.getLocalHost().getHostAddress();
			} catch(java.net.UnknownHostException ex) {
				ex.printStackTrace();
				throw new java.text.ParseException(ex.getMessage(), 0);
			}
			
			java.util.StringTokenizer tokenizer = new java.util.StringTokenizer(ipConfigResponse, "\n");
			String lastMacAddress = null;
			
			while(tokenizer.hasMoreTokens()) {
				String line = tokenizer.nextToken().trim();
				boolean containsLocalHost = line.indexOf(localHost) >= 0;
				
				if(containsLocalHost && lastMacAddress != null) {
					return lastMacAddress;
				}
				
				int macAddressPosition = line.indexOf("HWaddr");
				if(macAddressPosition <= 0) continue;
				
				String macAddressCandidate = line.substring(macAddressPosition + 6).trim();
				if(linuxIsMacAddress(macAddressCandidate)) {
					lastMacAddress = macAddressCandidate;
					continue;
				}
			}
			
			java.text.ParseException ex = new java.text.ParseException
				("cannot read MAC address for " + localHost + " from [" + ipConfigResponse + "]", 0);
			ex.printStackTrace();
			throw ex;
		}
		
		
		private final  boolean linuxIsMacAddress(String macAddressCandidate) {
			if(macAddressCandidate.length() != 17) return false;
			return true;
		}
		
		
		private final  String linuxRunIfConfigCommand() throws java.io.IOException {
			Process p = Runtime.getRuntime().exec("ifconfig");
			java.io.InputStream stdoutStream = new java.io.BufferedInputStream(p.getInputStream());
			
			StringBuffer buffer= new StringBuffer();
			for (;;) {
				int c = stdoutStream.read();
				if (c == -1) break;
				buffer.append((char)c);
			}
			String outputText = buffer.toString();
			
			stdoutStream.close();
			
			return outputText;
		}
		
		
		private final  String windowsParseMacAddress(String ipConfigResponse) throws java.text.ParseException {
			String localHost = null;
			try {
				localHost = java.net.InetAddress.getLocalHost().getHostAddress();
			} catch(java.net.UnknownHostException ex) {
				ex.printStackTrace();
				throw new java.text.ParseException(ex.getMessage(), 0);
			}
			
			java.util.StringTokenizer tokenizer = new java.util.StringTokenizer(ipConfigResponse, "\n");
			String lastMacAddress = null;
			
			while(tokenizer.hasMoreTokens()) {
				String line = tokenizer.nextToken().trim();
		
				if(line.endsWith(localHost) && lastMacAddress != null) {
					return lastMacAddress;
				}
				
				int macAddressPosition = line.indexOf(":");
				if(macAddressPosition <= 0) continue;
				
				String macAddressCandidate = line.substring(macAddressPosition + 1).trim();
				if(windowsIsMacAddress(macAddressCandidate)) {
					lastMacAddress = macAddressCandidate;
					continue;
				}
			}
			java.text.ParseException ex = new java.text.ParseException("cannot read MAC address from [" + ipConfigResponse + "]", 0);
			ex.printStackTrace();
			throw ex;
		}
		
		
		private final boolean windowsIsMacAddress(String macAddressCandidate) {
			if(macAddressCandidate.length() != 17) return false;
			return true;
		}
		
		
		private final String windowsRunIpConfigCommand() throws java.io.IOException {
			Process p = Runtime.getRuntime().exec("ipconfig /all");
			java.io.InputStream in = new java.io.BufferedInputStream(p.getInputStream());
			StringBuffer buffer= new StringBuffer();
			for (;;) {
				int c = in.read();
				if (c == -1) break;
				buffer.append((char)c);
			}
			String outputText = buffer.toString();
			in.close();	
			return outputText;
		}
	
    public double getCpuRatio(){
	    double cpuRatio = 0;
			if (getOSName().toLowerCase().startsWith("windows")) {
			        cpuRatio = getCpuRatioForWindows();
				} else if (getOSName().toLowerCase().startsWith("linux")) {
					cpuRatio = getCpuRatioForLinux();
			}
			return cpuRatio;
    }
   
    private double getCpuRatioForWindows() {
		try {
			String procCmd = System.getenv("windir")
					+ "\\system32\\wbem\\wmic.exe process get Caption,CommandLine,"
					+ "KernelModeTime,ReadOperationCount,ThreadCount,UserModeTime,WriteOperationCount";

			long[] c0 = readCpu(Runtime.getRuntime().exec(procCmd));
			Thread.sleep(CPUTIME);
			long[] c1 = readCpu(Runtime.getRuntime().exec(procCmd));
			if (c0 != null && c1 != null) {
				long idletime = c1[0] - c0[0];
				long busytime = c1[1] - c0[1];
				return Double.valueOf(
						PERCENT * (busytime) / (busytime + idletime))
						.doubleValue();
			} else {
				return 0.0;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0.0;
		}
	}
	
	final  private double getCpuRatioForLinux() {
		try {
			java.io.File file = new java.io.File("/proc/stat");
			java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(
					new java.io.FileInputStream(file)));
			java.util.StringTokenizer token = new java.util.StringTokenizer(br.readLine());
			token.nextToken();
			int user = Integer.parseInt(token.nextToken());
			int nice = Integer.parseInt(token.nextToken());
			int system = Integer.parseInt(token.nextToken());
			int idle = Integer.parseInt(token.nextToken());
			Thread.sleep(1000);
			br = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(
					file)));
			token = new java.util.StringTokenizer(br.readLine());
			token.nextToken();
			int user2 = Integer.parseInt(token.nextToken());
			int nice2 = Integer.parseInt(token.nextToken());
			int sys2 = Integer.parseInt(token.nextToken());
			int idle2 = Integer.parseInt(token.nextToken());
			return (double) ((user2 + sys2 + nice2) - (user + system + nice))
					/ (float) ((user2 + nice2 + sys2 + idle2) - (user + nice
							+ system + idle));
		} catch (Exception ex) {
			return (double) 0.0;
		}

	}
	
	final   String substring(String src, int startidx, int endidx) {
		byte[] b = src.getBytes();
		StringBuffer sbr = new StringBuffer();
		for (int i = startidx; i <= endidx; i++) {
			sbr.append((char) b[i]);
		}
		return sbr.toString();
	}
	
	
	
	final   long[] readCpu(final Process proc) {
		long[] retn = new long[2];
		try {
			proc.getOutputStream().close();
			java.io.InputStreamReader ir = new java.io.InputStreamReader(proc.getInputStream());
			java.io.LineNumberReader input = new java.io.LineNumberReader(ir);
			String line = input.readLine();
			if (line == null || line.length() < 10) {
				return null;
			}
			int capidx = line.indexOf("Caption");
			int cmdidx = line.indexOf("CommandLine");
			int rocidx = line.indexOf("ReadOperationCount");
			int umtidx = line.indexOf("UserModeTime");
			int kmtidx = line.indexOf("KernelModeTime");
			int wocidx = line.indexOf("WriteOperationCount");
			long idletime = 0;
			long kneltime = 0;
			long usertime = 0;
			while ((line = input.readLine()) != null) {
				if (line.length() < wocidx) {
					continue;
				}

				String caption = substring(line, capidx, cmdidx - 1).trim();
				String cmd = substring(line, cmdidx, kmtidx - 1).trim();
				if (cmd.indexOf("wmic.exe") >= 0) {
					continue;
				}
				if (caption.equals("System Idle Process")
						|| caption.equals("System")) {
					idletime += Long.valueOf(
							substring(line, kmtidx, rocidx - 1).trim())
							.longValue();
					idletime += Long.valueOf(
							substring(line, umtidx, wocidx - 1).trim())
							.longValue();
					continue;
				}

				kneltime += Long.valueOf(
						substring(line, kmtidx, rocidx - 1).trim()).longValue();
				usertime += Long.valueOf(
						substring(line, umtidx, wocidx - 1).trim()).longValue();
			}
			retn[0] = idletime;
			retn[1] = kneltime + usertime;
			return retn;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				proc.getInputStream().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
        public long getStartTime(){
                return startTime;
        }
        
        public long getStartMemory(){
                return startMemory;
        }
	
		public boolean isSun() {
			return System.getProperty("java.vm.vendor").indexOf("Sun") != -1;
		}


		public String getJavaVersion() {
			return javaVersion;
		}


		public void loadAddress() {
			try {
				java.net.InetAddress address = java.net.InetAddress
						.getLocalHost();
				java.net.InetAddress[] all = java.net.InetAddress.getAllByName(address.getHostName());
			for (int i = 0; i < all.length; i++) {
				String temp = null;
				temp =  all[i].getHostAddress().toString();
				if (!temp.startsWith("127.0") && !temp.startsWith("169.254")) {
					serverIP=temp;
				}
			}
			serverName = address.getHostName();
			} catch (java.net.UnknownHostException e) {
			}
		}


		/**
		 * 获得所在服务器名称
		 */
		public String getServerName() {
			return serverName;
		}
		
        public String getServerIP() {
			return serverIP;
		}
		
		public boolean isSupportSpring(){
		   return supportSpring;
		}
		
		public boolean isSupportLoonframework(){
		   return supportLoonframework; 
		}
		
		public boolean isSupportStruts() {
			return supportStruts;
		}
		
		public boolean isSupportJAF() {
			return supportJAF;
		}

		public boolean isSupportJavaxSql() {
			return supportJavaxSql;
		}
		
		public boolean isSupportHibernate(){
		    return supportHibernate;
		}

		public boolean isSupportJNDI() {
			return supportJNDI;
		}

		public boolean isSupportMail() {
			return supportMail;
		}

		public boolean isSupportBeanUtils() {
			return supportBeanUtils;
		}

		public boolean isSupportCommonLogging() {
			return supportCommonLogging;
		}

		public boolean isSupportCommonCodec() {
			return supportCommonCodec;
		}

		public boolean isSupportCommonCollection() {
			return supportCommonCollection;
		}

		public boolean isSupportCommonDigester() {
			return supportCommonDigester;
		}

		public boolean isSupportCommonLang() {
			return supportCommonLang;
		}

		public boolean isSupportJakartaRegExp() {
			return supportJakartaRegExp;
		}

		public boolean isSupportLucene() {
			return supportLucene;
		}

		public boolean isSupportDom4j() {
			return supportDom4j;
		}

		public boolean isSupportMmMysqlDriver() {
			return supportMmMysqlDriver;
		}

		public boolean isSupportComMysqlDriver() {
			return supportComMysqlDriver;
		}

		public boolean isSupportImageProcessing() {
			return supportImageProcessing;
		}
	
		public String getDoubleOperation(){
		  long begin = System.currentTimeMillis();
		  int flag = 0;
		  double random = (double)new java.util.Random().nextInt( 1000 );
		  while( flag < 100000 ){
		    random = Math.sqrt( random );
			flag++;
		  }
		  long end = System.currentTimeMillis();
		  long result = end - begin;
		  return String.valueOf(result);
	}
	
		public String getNumberOperation() {
			long begin = startTime;
			int flag = 0;
			while (flag < 1000000) {
				flag++;
			}
			long end = System.currentTimeMillis();
			long result = end - begin;

			return String.valueOf(result);
		}
	}
LfSpy spyLib = new LfSpy();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>lf-jsp探针 Ver 0.1.0</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<style type="text/css">
<!--
.stylebase {color: #FFFFFF; font-size:14px; font-weight:bold;}
.table-header-text {  color: #FFFFFF;
  font-weight: normal;
  margin: 3px 5px 1px 15px;
  font-family:  Arial, Verdana,Helvetica, Sans-Serif;
}
td{white-space:normal; word-break:break-all;}
      A{ COLOR: #000000; TEXT-DECORATION: none}
      A:hover { COLOR: #f58200}
      body,td,span { font-size: 9pt}
      .input  { BACKGROUND-COLOR: #E3F1D1;BORDER:#A9D46D 1px solid;FONT-SIZE: 9pt}
      .tableBorder {BORDER-RIGHT: #84BE3C 1px solid; BORDER-TOP: #84BE3C 1px solid; BORDER-LEFT: #84BE3C 1px solid; BORDER-BOTTOM: #84BE3C 1px solid; BACKGROUND-COLOR: #ffffff; WIDTH: 760;}
      .backc  { BACKGROUND-COLOR: #E3F1D1;BORDER:#A9D46D 1px solid;FONT-SIZE: 9pt;color:#000000;}
      .PicBar { background-color: #A9D46D; border: 1px solid #ffffff; height: 12px;}
      .InnerHead {
	    background-color: #E3F1D1;
	    border-top-width: 1px;
	    border-right-width: 0px;
	    border-bottom-width: 0px;
	    border-left-width: 1px;
	    border-top-style: solid;
	    border-right-style: solid;
	    border-bottom-style: solid;
	    border-left-style: solid;
	    border-top-color: #FFFFFF;
	    border-right-color: #FFFFFF;
	    border-bottom-color: #FFFFFF;
	    border-left-color: #FFFFFF;
	    text-align: left;
	    padding-left: 10px;
		padding-top: 3px;
      }
      .InnerTable {
	    background-color: #999999;
      }
      .InnerMain {
	    background-color: #FFFFFF;
	    padding-left: 10px;
		padding-top: 3px;
      }
-->
</style>
</head>
<body>
<table width="100%" height="45" border="0" cellpadding="0" class="InnerHead">
  <tr>
    <td><div align="center"><span class="stylebase"><a href="http://looframework.sourceforge.net/">LF-Spy Java服务器探针 Ver 0.1.0</a></span></div></td>
  </tr>
</table>
<h3>服务器连接速度:</h3>
	    <table border=0 width=100% cellspacing=1 cellpadding=0 class="InnerTable">
		  <tr>
		        <td width="29%" height="22" class="InnerHead"><span class="table-header-text">接入设备 </span></td>
		        <td width="45%" height="22" class="InnerHead"><span class="table-header-text">连接速度(理论值) </span></td>
                <td width="26%" class="InnerHead"><span class="table-header-text">下载速度(理论值) </span></td>
		  </tr>
		  <tr>
		    <td height="20" class="InnerMain">56k Modem</td>
			<td class="InnerMain"><img align=absmiddle class=PicBar width='1%'> 56 Kbps</td>
			<td class="InnerMain">7.0 k/s</td>
		  </tr>
		  <tr>
		    <td height="20" class="InnerMain">64k ISDN</td>
			<td class="InnerMain"><img align=absmiddle class=PicBar width='1%'> 64 Kbps</td>
			<td class="InnerMain">8.0 k/s</td>
		  </tr>
		  <tr>
		    <td height="20" class="InnerMain">512k ADSL</td>
			<td class="InnerMain"><img align=absmiddle class=PicBar width='5%'> 512 Kbps</td>
			<td class="InnerMain">64.0 k/s</td>
		  </tr>
		  <tr>
		    <td height="20" class="InnerMain">1.5M Cable</td>
			<td class="InnerMain"><img align=absmiddle class=PicBar width='15%'> 1500 Kbps</td>
			<td class="InnerMain">187.5 k/s</td>
		  </tr>
		  <tr>
		    <td height="20" class="InnerMain">5M FTTP</td>
			<td class="InnerMain"><img align=absmiddle class=PicBar width='50%'> 5000 Kbps</td>
			<td class="InnerMain">625.0 k/s</td>
		  </tr>
		    <tr>
            <td height="20" class="InnerMain">当前连接速度</td>
			<%
			  if(request.getParameter("action") != null && "speedTest".equals(request.getParameter("action"))){
			    StringBuffer sbuffer=new StringBuffer(99);
			    sbuffer.append("<script language='JavaScript'>var tSpeedStart=new Date();</script>" );
				sbuffer.append("<script language='JavaScript'>var tSpeedEnd=new Date();</script>\n" );
				sbuffer.append("<script language='JavaScript'>" );
				sbuffer.append("var iSpeedTime = 0; iSpeedTime = ( tSpeedEnd - tSpeedStart ) / 1000;" );
				sbuffer.append("if( iSpeedTime > 0 ) iKbps = Math.round( Math.round( 100 * 8 / iSpeedTime * 10.5 ) / 10 ); else iKbps = 10000 ;" );
				sbuffer.append("var iShowPer = Math.round( iKbps / 100 );" );
				sbuffer.append("if( iShowPer < 1 ) iShowPer = 1;  else if( iShowPer > 82 )   iShowPer = 82;" );
				sbuffer.append("</script>\n" );
				sbuffer.append("<script language='JavaScript'>" );
				sbuffer.append("document.write('<td class=InnerMain><img align=absmiddle class=PicBar width=\"' + iShowPer + '%\">' + iKbps + ' Kbps');" );
				sbuffer.append("</script>\n" );
				sbuffer.append("</td><td class=InnerMain>&nbsp;<a href='?action=speedTest' title=连接速度测试><u>" );
				sbuffer.append("<script language='JavaScript'>" );
				sbuffer.append("document.write(Math.round(iKbps/8*10)/10+'k/s');");
				sbuffer.append("</script>\n") ;
				sbuffer.append("</u></a></td>");
				out.println(sbuffer.toString());
               }else{
               %>
			<td class="InnerMain">&nbsp;</td>
			<td class="InnerMain"><a href='?action=speedTest' title=测试连接速度><u>开始测试</u></a></td>
			<%}%>
		  </tr>
		</table>
<h3>服务器相关数据:</h3>
 <table border=0 width=100% cellspacing=1 cellpadding=0 class="InnerTable">
  <tr>
     <td width="29%" height="22" class="InnerHead"><span class="table-header-text">服务器相关</span></td>
     <td width="71%" height="22" class="InnerHead"><span class="table-header-text">数值信息</span></td>
  </tr>
  <tr>
  <td width="29%" class="InnerMain">服务器名</td>
    <td class="InnerMain">&nbsp;<%=spyLib.getServerName()%>&nbsp;</td>
  </tr>
  <tr>
  <td width="29%" class="InnerMain">服务器软件名</td>
  <td class="InnerMain">&nbsp;<%=getServletContext().getServerInfo()%>&nbsp;</td>
  </tr>
  <tr>
    <td width="29%" class="InnerMain">服务器IP</td>
    <td class="InnerMain">&nbsp;<%=spyLib.getServerIP()%>&nbsp;</td>
  </tr>
  <tr>
    <td width="29%" class="InnerMain">服务器操作系统</td>
    <td class="InnerMain">&nbsp;<%=spyLib.getOSName()%>&nbsp;</td>
  </tr>
  <tr>
    <td width="29%" class="InnerMain">服务器系统模式</td>
    <td class="InnerMain">&nbsp;<%=spyLib.getSystemModel()%>&nbsp;</td>
  </tr>
  <tr>
    <td width="29%" class="InnerMain">服务器MAC地址</td>
    <td class="InnerMain">&nbsp;<%=spyLib.getMacAddress()%>&nbsp;</td>
  </tr>
  <tr>
    <td width="29%" class="InnerMain">操作系统类型</td>
    <td class="InnerMain">&nbsp;<%=spyLib.getOSarch()%>&nbsp;</td>
  </tr>
  <tr>
    <td width="29%" class="InnerMain">操作系统CPU占用率</td>
    <td class="InnerMain">&nbsp;<%=spyLib.getCpuRatio()+"%"%>&nbsp;</td>
  </tr>
  <tr>
    <td width="29%" class="InnerMain">JRE版本</td>
    <td class="InnerMain">&nbsp;<%=spyLib.getJavaVersion()%>&nbsp;</td>
  </tr>
  <tr>
    <td width="29%" class="InnerMain">JVM是否为Sun提供</td>
    <td class="InnerMain">&nbsp;<%=spyLib.isSun()%>&nbsp;</td>
  </tr>
  <tr>
    <td width="29%" class="InnerMain">服务器当前时间</td>
    <td class="InnerMain">&nbsp;<%=new java.util.Date()%>&nbsp;</td>
  </tr>
   <tr>
    <td width="29%" class="InnerMain">服务器时区设置</td>
    <td class="InnerMain">&nbsp;<%=spyLib.getTimeZone()%>&nbsp;</td>
  </tr>
  <tr>
    <td width="29%" class="InnerMain">服务器当前用户</td>
    <td class="InnerMain">&nbsp;<%=spyLib.getNowUser()%>&nbsp;</td>
  </tr>
   <tr>
    <td width="29%" class="InnerMain">服务器当前用户所在硬盘剩余空间</td>
    <td class="InnerMain">&nbsp;<%=spyLib.getObjectDisk()==-1?"未知":spyLib.getObjectDisk()/1024/1024+"MB"%>&nbsp;</td>
  </tr>
  <tr>
    <td width="29%" class="InnerMain">服务器当前用户目录</td>
    <td class="InnerMain">&nbsp;<%=spyLib.getNowUserDir()%>&nbsp;</td>
  </tr>
   <tr>
    <td width="29%" class="InnerMain">服务器当前页面物理路径</td>
    <td class="InnerMain">&nbsp;<%=config.getServletContext().getRealPath(request.getServletPath())%>&nbsp;</td>
  </tr>
  <tr>
    <td width="29%" class="InnerMain">当前服务端口</td>
    <td class="InnerMain">&nbsp;<%=request.getServerPort()%>&nbsp;</td>
  </tr>
</table>
<h3>截获的浏览器参数一览:</h3>
<table border=0 width=100% cellspacing=1 cellpadding=0 class="InnerTable">
  <tr>
     <td width="29%" height="22" class="InnerHead"><span class="table-header-text">属性 </span></td>
     <td width="%71%" height="22" class="InnerHead"><span class="table-header-text">数值 </span></td>
  </tr>
  <tr>
    <td width="29%" class="InnerMain">&nbsp;http-protocol</td>
    <td class="InnerMain">&nbsp;<%=request.getProtocol()%>&nbsp;</td>
  </tr>
  <tr>
    <td width="29%" class="InnerMain">&nbsp;http-method</td>
    <td class="InnerMain">&nbsp;<%=request.getMethod()%>&nbsp;</td>
  </tr> 
  <tr>
    <td width="29%" class="InnerMain">&nbsp;remote-host</td>
    <td class="InnerMain">&nbsp;<%=request.getRemoteHost()%>&nbsp;</td>
  </tr>
  <tr>
    <td width="29%" class="InnerMain">&nbsp;remote-host</td>
    <td class="InnerMain">&nbsp;<%=request.getRemoteAddr()%>&nbsp;</td>
  </tr>
  <tr>
    <td width="29%" class="InnerMain">&nbsp;remote-user</td>
    <td class="InnerMain">&nbsp;<%=request.getRemoteUser()%>&nbsp;</td>
  </tr>
  <tr>
    <td width="29%" class="InnerMain">&nbsp;remote-port</td>
    <td class="InnerMain">&nbsp;<%=request.getRemotePort()%>&nbsp;</td>
  </tr>
  <tr>
    <td width="29%" class="InnerMain">&nbsp;content-type</td>
    <td class="InnerMain">&nbsp;<%=request.getContentType()%>&nbsp;</td>
  </tr>
  <%
  java.util.Enumeration enums=request.getHeaderNames();
   StringBuffer headBuffer=new StringBuffer(); 
    String name="";
    Object value="";
  while(enums.hasMoreElements()){
   name=(String)enums.nextElement();
   value=(String)request.getHeader(name);
		  headBuffer.append("<tr>");
		  headBuffer.append("<td class=\"InnerMain\">&nbsp;"+name+"</td>");
		  headBuffer.append("<td class=\"InnerMain\">&nbsp;"+value+"</td>");
		  headBuffer.append("</tr>");
  }
  out.println(headBuffer.toString());
  %>
</table>
<h3>部分CGI(Common Gateway Interface)参数:</h3>
<table border=0 width=100% cellspacing=1 cellpadding=0 class="InnerTable">
  <tr>
     <td width="29%" height="22" class="InnerHead"><span class="table-header-text">属性 </span></td>
     <td width="%71%" height="22" class="InnerHead"><span class="table-header-text">数值 </span></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;SERVER_NAME&nbsp;(服务名)</td>
    <td class="InnerMain">&nbsp;<%=request.getServerName()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;PATH_TRANSLATED&nbsp;(服务器实际路径信息)</td>
    <td class="InnerMain">&nbsp;<%=request.getPathTranslated()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;SERVER_SOFTWARE&nbsp;(服务器软件名)</td>
    <td class="InnerMain">&nbsp;<%=getServletContext().getServerInfo()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;PATH_INFO&nbsp;(附加路径信息)</td>
    <td class="InnerMain">&nbsp;<%=request.getPathInfo()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;DOCUMENT_ROOT&nbsp;(站点的物理路径)</td>
    <td class="InnerMain">&nbsp;<%=getServletContext().getRealPath("/")%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;CONTEXT_PATH&nbsp;(站点的根路径)</td>
    <td class="InnerMain">&nbsp;<%=request.getContextPath()%></td>
  </tr>
</table>
<h3>通过请求(Request)获得的参数:</h3>
<table border=0 width=100% cellspacing=1 cellpadding=0 class="InnerTable">
  <tr>
     <td width="29%" height="22" class="InnerHead"><span class="table-header-text">属性 </span></td>
     <td width="%71%" height="22" class="InnerHead"><span class="table-header-text">数值 </span></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;getAuthType</td>
    <td class="InnerMain">&nbsp;<%=request.getAuthType()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;getCharacterEncoding</td>
    <td class="InnerMain">&nbsp;<%=request.getCharacterEncoding()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;getContentLength</td>
    <td class="InnerMain">&nbsp;<%=request.getContentLength()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;getContentType</td>
    <td class="InnerMain">&nbsp;<%=request.getContentType()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;getContextPath</td>
    <td class="InnerMain">&nbsp;<%=request.getContextPath()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;getLocale</td>
    <td class="InnerMain">&nbsp;<%=request.getLocale()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;getLocales</td>
    <td class="InnerMain">&nbsp;<%=request.getLocales()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;getMethod</td>
    <td class="InnerMain">&nbsp;<%=request.getMethod()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;getPathInfo</td>
    <td class="InnerMain">&nbsp;<%=request.getPathInfo()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;getPathTranslated</td>
    <td class="InnerMain">&nbsp;<%=request.getPathTranslated()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;getProtocol</td>
    <td class="InnerMain">&nbsp;<%=request.getProtocol()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;getReader</td>
    <td class="InnerMain">&nbsp;<%=request.getReader()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;getRealPath</td>
    <td class="InnerMain">&nbsp;<%=config.getServletContext().getRealPath(".")%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;getRemoteAddr</td>
    <td class="InnerMain">&nbsp;<%=request.getRemoteAddr()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;getRemoteHost</td>
    <td class="InnerMain">&nbsp;<%=request.getRemoteHost()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;getRemoteUser</td>
    <td class="InnerMain">&nbsp;<%=request.getRemoteUser()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;getRequestDispatcher</td>
    <td class="InnerMain">&nbsp;<%=request.getRequestDispatcher("/")%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;getRequestURI</td>
    <td class="InnerMain">&nbsp;<%=request.getRequestURI()%></td>
  </tr>  
  <tr>
    <td class="InnerMain">&nbsp;getRequestURL</td>
    <td class="InnerMain">&nbsp;<%=request.getRequestURL()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;getServerName</td>
    <td class="InnerMain">&nbsp;<%=request.getServerName()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;getServerPort</td>
    <td class="InnerMain">&nbsp;<%=request.getServerPort()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;getServletPath</td>
    <td class="InnerMain">&nbsp;<%=request.getServletPath()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;getUserPrincipal</td>
    <td class="InnerMain">&nbsp;<%=request.getUserPrincipal()%></td>
  </tr>  
</table>
<h3>通过应答(Response)返回的参数:</h3>
<table border=0 width=100% cellspacing=1 cellpadding=0 class="InnerTable">
  <tr>
           <td width="50%" height="22" class="InnerHead"><span class="table-header-text">属性 </span></td>
     <td width="50%" height="22" class="InnerHead"><span class="table-header-text">数值 </span></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;getBufferSize</td>
    <td class="InnerMain">&nbsp;<%=response.getBufferSize()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;getCharacterEncoding</td>
    <td class="InnerMain">&nbsp;<%=response.getCharacterEncoding()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;getContentType</td>
    <td class="InnerMain">&nbsp;<%=response.getContentType()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;getLocale</td>
    <td class="InnerMain">&nbsp;<%=response.getLocale()%></td>
  </tr>
</table>
<h3>通过会话(Session)获得的参数:</h3>
<table border=0 width=100% cellspacing=1 cellpadding=0 class="InnerTable">
  <tr>
    <td width="50%" height="22" class="InnerHead"><span class="table-header-text">属性 </span></td>
    <td width="50%" height="22" class="InnerHead"><span class="table-header-text">数值</span></td>
  </tr>
   <%
   enums=session.getAttributeNames();
   headBuffer=new StringBuffer(); 
  while(enums.hasMoreElements()){
   name=(String)enums.nextElement();
   value=session.getAttribute(name);
		  headBuffer.append("<tr>");
		  headBuffer.append("<td class=\"InnerMain\">&nbsp;"+name+"</td>");
		  headBuffer.append("<td class=\"InnerMain\">&nbsp;"+session.getAttribute(name)+"</td>");
		  headBuffer.append("</tr>");
  }
  out.println(headBuffer.toString());
  %>
</table>
<h3>通过服务器应用（Application）获得的参数:</h3>
<table border=0 width="100%" cellspacing=1 cellpadding=0 class="InnerTable">
  <tr>
    <td width="29%" class="InnerHead"><span class="table-header-text">属性</span></td>
    <td width="71%" class="InnerHead"><span class="table-header-text">数值</span></td>
  </tr>
   <%
   enums=application.getAttributeNames();
    headBuffer=new StringBuffer(); 
	  while(enums.hasMoreElements()){
	   name=(String)enums.nextElement();
	   value=application.getAttribute(name);
		   if(value instanceof String){
		     String temp=(String)value;
			     if(temp.indexOf(";")!=-1){
			             java.util.StringTokenizer token=new java.util.StringTokenizer(temp,";");
			             StringBuffer buffer=new StringBuffer(token.countTokens());
			             while(token.hasMoreElements()){
			                 buffer.append(token.nextElement());
			                 buffer.append(";\n<br>");
			             } 
			             value=buffer.toString();
			     }
		   }
			  headBuffer.append("<tr>");
			  headBuffer.append("<td class=\"InnerMain\">&nbsp;"+name+"&nbsp;</td>");
			  headBuffer.append("<td class=\"InnerMain\">&nbsp;"+value+"&nbsp;</td>");
			  headBuffer.append("</tr>");
	  }
	  out.println(headBuffer.toString());
  %>
</table>

<h3>通过小甜饼(Cookies)获得的参数:</h3>
<table border=0 width=100% cellspacing=1 cellpadding=0 class="InnerTable">
  <tr>
    <td width="50%" height="22" class="InnerHead"><span class="table-header-text">属性 </span></td>
    <td width="50%" height="22" class="InnerHead"><span class="table-header-text">数值</span></td>
  </tr>
  <%
  	Cookie[] cos=request.getCookies();
   	if(cos!=null){
		for(int i=0;i<cos.length;i++)
		{   
  %>
  <tr>
    <td class="InnerMain">&nbsp;<%=cos[i].getName()%></td>
    <td class="InnerMain">&nbsp;<%=cos[i].getValue()%></td>
  </tr>
  <%
		}
  	}
  %>
</table>

<h3>服务器效率测试:</h3>
<table border=0 width=100% cellspacing=1 cellpadding=0 class="InnerTable">
  <tr>
    <td width="54%" height="22" class="InnerHead"><span class="table-header-text">让服务器执行100万次整数运算和10万次开方，记录所用的时间。</span></td>
    <td width="21%" height="22" class="InnerHead"><span class="table-header-text">整数运算</span></td>
    <td width="25%" height="22" class="InnerHead"><span class="table-header-text">浮点运算</span></td>
  </tr>
  <tr>
    <td class="InnerMain" height="40">&nbsp;<input name="button" type="button" class=backc onclick="javascript:location.reload()" value="测试当前服务器"/></td>
    <td class="InnerMain">&nbsp;<%=spyLib.getNumberOperation()+"(毫秒)"%></td>
    <td class="InnerMain">&nbsp;<%=spyLib.getDoubleOperation()+"(毫秒)"%></td>
  </tr>
</table>
<h3>服务器所有可用参数一览:</h3>
<table border=0 width=100% cellspacing=1 cellpadding=0 class="InnerTable">
  <tr>
     <td width="29%" height="22" class="InnerHead"><span class="table-header-text">服务器相关 </span></td>
     <td width="%71%" height="22" class="InnerHead"><span class="table-header-text">数值信息 </span></td>
  </tr>
  <%
   java.util.Properties props=System.getProperties();
   java.util.Iterator iter=props.keySet().iterator();
  while(iter.hasNext())
  {
  	name=(String)iter.next();
  	value=props.get(name);
  	  if(value instanceof String){
     String temp=(String)value;
     boolean isA=temp.indexOf(";")!=-1;
     boolean isB=temp.indexOf(",")!=-1;
     if(isA||isB){
             java.util.StringTokenizer token=new java.util.StringTokenizer(temp.replace(" ",""),isA?";":",");
             StringBuffer buffer=new StringBuffer(token.countTokens());
             while(token.hasMoreElements()){
                 buffer.append("&nbsp;"+token.nextElement());
                 buffer.append(isA?";"+"\n<br>":","+"\n<br>");
             } 
             value=buffer.toString();
     }
  	}else{
  	  value="&nbsp;"+value;
  	}
  	      headBuffer.append("<tr>");
		  headBuffer.append("<td class=\"InnerMain\">"+name+"&nbsp;</td>");
		  headBuffer.append("<td class=\"InnerMain\">"+value+"&nbsp;</td>");
		  headBuffer.append("</tr>");
		  
  	}
  	out.println(headBuffer.toString());
  %>
</table>
<h3>针对部分类库的服务器加载验证</h3>
<table border=0 width=100% cellspacing=1 cellpadding=0 class="InnerTable">
  <tr>
     <td width="50%" height="22" class="InnerHead"><span class="table-header-text">属性 </span></td>
     <td width="50%" height="22" class="InnerHead"><span class="table-header-text">数值 </span></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;是否支持 JNDI</td>
    <td class="InnerMain">&nbsp;<%=spyLib.isSupportJNDI()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;是否支持 Loonframework</td>
    <td class="InnerMain">&nbsp;<%=spyLib.isSupportLoonframework()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;是否支持 JavaxSql</td>
    <td class="InnerMain">&nbsp;<%=spyLib.isSupportJavaxSql()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;是否支持 JAF</td>
    <td class="InnerMain">&nbsp;<%=spyLib.isSupportJAF()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;是否支持 Mail</td>
    <td class="InnerMain">&nbsp;<%=spyLib.isSupportMail()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;是否支持 BeanUtils</td>
    <td class="InnerMain">&nbsp;<%=spyLib.isSupportBeanUtils()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;是否支持 CommonLogging</td>
    <td class="InnerMain">&nbsp;<%=spyLib.isSupportCommonLogging()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;是否支持 CommonCodec</td>
    <td class="InnerMain">&nbsp;<%=spyLib.isSupportCommonCodec()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;是否支持 CommonCollection</td>
    <td class="InnerMain">&nbsp;<%=spyLib.isSupportCommonCollection()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;是否支持 CommonDigester</td>
    <td class="InnerMain">&nbsp;<%=spyLib.isSupportCommonDigester()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;是否支持 CommonLang</td>
    <td class="InnerMain">&nbsp;<%=spyLib.isSupportCommonLang()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;是否支持 JakartaRegExp</td>
    <td class="InnerMain">&nbsp;<%=spyLib.isSupportJakartaRegExp()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;是否支持 Struts</td>
    <td class="InnerMain">&nbsp;<%=spyLib.isSupportStruts()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;是否支持 Lucene</td>
    <td class="InnerMain">&nbsp;<%=spyLib.isSupportLucene()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;是否支持 Dom4j</td>
    <td class="InnerMain">&nbsp;<%=spyLib.isSupportDom4j()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;是否支持 MysqlDriver(Mm)</td>
    <td class="InnerMain">&nbsp;<%=spyLib.isSupportMmMysqlDriver()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;是否支持 MysqlDriver(Com)</td>
    <td class="InnerMain">&nbsp;<%=spyLib.isSupportComMysqlDriver()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;是否支持 ImageProcessing</td>
    <td class="InnerMain">&nbsp;<%=spyLib.isSupportImageProcessing()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;是否支持 Hibernate</td>
    <td class="InnerMain">&nbsp;<%=spyLib.isSupportHibernate()%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;是否支持 Spring</td>
    <td class="InnerMain">&nbsp;<%=spyLib.isSupportSpring()%></td>
  </tr>
</table>
<p>
<h3>资源消费一览</h3>
<table border=0 width=100% cellspacing=1 cellpadding=0 class="InnerTable">
  <tr>
      <td width="50%" height="22" class="InnerHead"><span class="table-header-text">属性 </span></td>
      <td width="50%" height="22" class="InnerHead"><span class="table-header-text">数值 </span></td>
  </tr>
<%
long endMem = Runtime.getRuntime().freeMemory(); 
long total= Runtime.getRuntime().maxMemory();
long endTime = System.currentTimeMillis(); 
java.util.Date date = new java.util.Date(spyLib.getStartTime());  
java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
String starttime = df.format(date);    
date = new java.util.Date(endTime);  
df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
String endtime = df.format(date);   
%>
  <tr>
    <td class="InnerMain">&nbsp;最大内存容量</td>
    <td class="InnerMain">&nbsp;<%=(total/1024/1024+"MB")%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;应用结束时剩余自由内存</td>
    <td class="InnerMain">&nbsp;<%=(endMem/1024/1024+"MB")%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;应用结束时减少自由内存</td>
    <td class="InnerMain">&nbsp;<%=((spyLib.getStartMemory() - endMem)/1024/1024+"MB")%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;应用开始时间</td>
    <td class="InnerMain">&nbsp;<%=starttime%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;应用结束时间</td>
    <td class="InnerMain">&nbsp;<%=endtime%></td>
  </tr>
  <tr>
    <td class="InnerMain">&nbsp;应用耗时(毫秒)</td>
    <td class="InnerMain">&nbsp;<%=(endTime-spyLib.getStartTime())%></td>
  </tr>
</table>
<p><br>
</p>
<center>
    <sup>&copy;</sup>
    <a href="http://blog.csdn.net/cping1982" target="_blank">Copyright 2008</a><br>
    LF-Spy 0.1.0(java服务器用探针)
 </center><br>
</body>
</html>
