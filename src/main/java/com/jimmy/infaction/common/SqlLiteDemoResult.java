package com.jimmy.infaction.common;
import com.sun.jna.platform.win32.Crypt32Util;


/**
 * @author jimmy on 2019/9/26 16:29
 */
public class SqlLiteDemoResult {
	private String action_url ;
	private String username_value;
	private byte[] password_value;

//	public SqlLiteDemoResult(String password_value) {
//			this.password_value = new String(Crypt32Util.cryptUnprotectData(toBytes(password_value)), StandardCharsets.UTF_16LE);
//	}

	public String getAction_url() {
		return action_url;
	}

	public void setAction_url(String action_url) {
		this.action_url = action_url;
	}

	public String getUsername_value() {
		return username_value;
	}

	public void setUsername_value(String username_value) {
		this.username_value = username_value;
	}

	public byte[] getPassword_value() {
//		this.password_value = new String(Crypt32Util.cryptUnprotectData(toBytes(password_value)), StandardCharsets.UTF_16LE);
		return password_value;
	}

	public void setPassword_value(byte[] password_value) {
		this.password_value = password_value;
	}

	private static byte[] toBytes(String str) {
		if (str == null || str.trim().equals("")) {
			return new byte[0];
		}
		byte[] bytes = new byte[str.length() / 2];
		for (int i = 0; i < str.length() / 2; i++) {
			String subStr = str.substring(i * 2, i * 2 + 2);
			bytes[i] = (byte) Integer.parseInt(subStr, 16);
		}
		return bytes;
	}
}
