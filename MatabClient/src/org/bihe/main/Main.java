package org.bihe.main;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.bihe.ui.secretary.GUIManager;
import org.bihe.ui.secretary.ShowMessage;

public class Main {

	private static String serverIp;

	public static String getServerIp() {
		return serverIp;
	}

	public static void setServerIp(String serverIp) {
		Main.serverIp = serverIp;
	}

	public static void main(String[] args) {
		try {

			try {

				UIManager.setLookAndFeel(UIManager
						.getSystemLookAndFeelClassName());
				if (isFileExist()) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							GUIManager.visibleLoginFrame();
						}

					});
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			ShowMessage.show(
					"خطای کلی رخ داده است!لطفا با واحد پشتیبانی تماس بگیرید.",
					ShowMessage.ERROR_ID, null);
		}

	}

	private static boolean isFileExist() {
		boolean isExist = false;

		Properties properties = new Properties();
		OutputStream outputStream = null;
		File file = new File("serverip.properties");

		if (file.exists()) {

			try {
				properties.load(new FileInputStream("serverip.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			serverIp = properties.getProperty("IP");
			isExist = true;
		} else {
			String ip = GUIManager.visibleIpFrame();

			try {
				properties.setProperty("IP", ip);
				outputStream = new FileOutputStream(file);
				properties.store(outputStream, new Date().toString());
				serverIp = ip;
				isExist = true;
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return isExist;

	}

}
