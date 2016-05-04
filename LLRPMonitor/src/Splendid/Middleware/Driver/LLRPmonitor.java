package Splendid.Middleware.Driver;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.commons.net.telnet.TelnetClient;

public class LLRPmonitor {

	private TelnetClient		telnet			= new TelnetClient();
	private BufferedInputStream	input;
	private PrintStream			output;										// output stream
	// ========Tokens=============
	private final String		promptAccessSpecCount	= "LLRPAccessSpecCount";
	private final String		promptNumROS	= "LLRPROSpecCount='";
	private final String		promptStatusROS	= "LLRPROSpec";
	private final String		promptPeerIPAddress	= "LLRPPeerIPAddress='";
	private final int waitTime = 500; //wait time response from reader, in ms
	// ===================================
	public LLRPmonitor() {
		super();
	}

	/**
	 * Connect to reader
	 * 
	 * @param userName
	 *            : The user name for login
	 * @param password
	 *            : The password for login
	 *@param IPReader
	 *            : IP address of reader
	 * @return boolean. connect OK or Fail
	 * @throws IOException
	 *             Any problems during connect
	 * @author ThangLe
	 */
	private boolean connect(String IPReader, String userName, String password) {
		try {
			
			if (telnet != null && telnet.isConnected()) {
				telnet.disconnect();
			}
			if (input != null) {
				input.close();
			}
			if (output != null) {
				output.flush();
				output.close();
			}
			
			// Connect to the specified server
			telnet.setConnectTimeout(5000);// Timeout 5s
			telnet.connect(IPReader, 23);

			// Get input and output stream references			
			input = new BufferedInputStream(telnet.getInputStream());
			output = new PrintStream(telnet.getOutputStream());

			Thread.sleep(waitTime); // wait for responding from reader
			// Log the user on
			if (readUntil("login: ") == null) return false;
			write(userName);
			Thread.sleep(waitTime); // wait for responding from reader
			if (readUntil("Password: ") == null) return false;
			write(password);
			Thread.sleep(waitTime); // wait for responding from reader
			// Advance to a prompt
			// readUntil(prompt + " ");

			return true; // connect OK
		} catch (Exception e) {
			e.printStackTrace();
			return false; // connect fail
		}
	}

	/**
	 * Check LLRP status
	 * 
	 *@param IPReader
	 *            : IP address of reader
	 * @return int: <br>
	 *         0: Has no the ROSPEC in reader.<br>
	 *         1: The ROSPEC is activating.<br>
	 *         2: The ROSPEC is inactive.<br>
	 *         3: No peer connecting to reader.<br>
	 *         -1: A error occurred
	 * @throws Exception
	 *             Any problems during connect
	 * @author ThangLe
	 */
	public synchronized int checkROSPEC(String IPReader) {
		String ROstatus = "";
		int index = 0;

		try {
			if (!connect(IPReader, "root", "impinj")) {// connect and login to reader
				return -1; // connect fail
			}
			// ==================
			ROstatus = sendCommand("show rfid llrp summary");

			disconnect(); // stop connect to reader

			if (ROstatus == null) return -1;

			// Start processing received data from reader
			// ===============get number of the ROSPEC in reader====================
			index = ROstatus.indexOf(promptNumROS);
			if (index != -1) {
				String NumROS = ROstatus.substring(index + 17, index + 18);
				// System.out.println(NumROS);
				if (NumROS.equals("0"))
					return 0; // has no ROSPEC in reader
			}

			// ==========get peer connection status=========
			index = ROstatus.indexOf(promptPeerIPAddress);
			if (index != -1) {
				String statusROS = ROstatus.substring(index + 19, index + 20);
				// System.out.println(statusROS);
				if (statusROS.equals("'"))
					return 3;// No peer connecting to reader			
			}
			// ==========get ROSPEC status=========
			index = ROstatus.indexOf(promptAccessSpecCount);
			if (index != -1) {
				String ROspecPosition = ROstatus.substring(index - 20, index + 5); 
				index = ROspecPosition.indexOf("='");				
				String statusROspecPosition = ROspecPosition.substring(index + 2, index + 8);
				// System.out.println(statusROS);
				if (statusROspecPosition.equals("Active"))
					return 1; // ROSPEC is Activated
				else
					return 2;// ROSPEC is Inactivated
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return -1;
	}

	// ================================
	/*
	 * 
	 */
	private String readUntil(String pattern) {
		try {
			char lastChar = pattern.charAt(pattern.length() - 1);
			StringBuffer sb = new StringBuffer();
			int numRead = 0;
			
			if(input.available()<= 5){//reader always returns more 5 chars
				return null;
			}
			char ch = (char) input.read();

			while (true) {
				// System.out.print(ch);
				numRead++;
				sb.append(ch);
				if (ch == lastChar) {
					if (sb.toString().endsWith(pattern)) {						
						return sb.toString();
					}
				}
				
				if(input.available()==0){
					break;
				}
				ch = (char) input.read();

				if (numRead > 2000) {
					break; // can not read the pattern
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// =================================
	private void write(String value) {
		try {
			output.println(value);
			output.flush();
			// System.out.println(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ===================================
	private String sendCommand(String command) {
		try {
			write(command);
			Thread.sleep(waitTime); // wait for responding from reader
			return readUntil(promptAccessSpecCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// ==================================
	private void disconnect() {
		try {			
			telnet.disconnect();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ===================to Test==================

	public static void _main(String[] args) {
		try {
			LLRPmonitor llrpMonitor = new LLRPmonitor();
			System.out.println(llrpMonitor.checkROSPEC("192.168.10.250"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
