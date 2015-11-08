package org.bihe.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.bihe.bean.Constant;
import org.bihe.bean.Doctor;
import org.bihe.bean.SystemParameters;
import org.bihe.bean.WaitingMessages;
import org.bihe.dao.SystemParametersDAO;
import org.bihe.dao.WaitingMessagesDAO;

public class SendMessage implements Runnable {

	private String sendMethod;
	private String userName;
	private String passWord;
	private String from;
	private String to;
	private String text;
	private int delay;

	public static void main(String[] args) {

	}

	/*
	 * public SendMessage() { SystemParameters systemParameters =
	 * SystemParametersDAO .select(Constant.MESSAGE_SENDING_DURATION_ID);
	 * 
	 * try { delay = Integer.parseInt(systemParameters.getValue()); } catch
	 * (NumberFormatException e) { delay = 0; }
	 * 
	 * this.sendMethod = SystemParametersDAO.select(
	 * Constant.WEB_SERVIS_ADDRESS_ID).getValue(); this.userName =
	 * SystemParametersDAO.select( Constant.WEB_SERVIS_USERNAME_ID).getValue();
	 * this.passWord = SystemParametersDAO.select(
	 * Constant.WEB_SERVIS_PASSWORD_ID).getValue(); this.from =
	 * SystemParametersDAO.select(Constant.FROM_ID).getValue(); }
	 */

	public SendMessage(String to, String text, Doctor doc) {
		super();
		this.to = to;
		this.text = text;

		SystemParameters systemParameters = SystemParametersDAO.select(
				Constant.MESSAGE_SENDING_DURATION_ID, doc);

		try {
			delay = Integer.parseInt(systemParameters.getValue());
		} catch (NumberFormatException e) {
			delay = 0;
		}

		this.sendMethod = SystemParametersDAO.select(
				Constant.WEB_SERVIS_ADDRESS_ID, doc).getValue();
		this.userName = SystemParametersDAO.select(
				Constant.WEB_SERVIS_USERNAME_ID, doc).getValue();
		this.passWord = SystemParametersDAO.select(
				Constant.WEB_SERVIS_PASSWORD_ID, doc).getValue();
		this.from = SystemParametersDAO.select(Constant.FROM_ID, doc)
				.getValue();

	}

	public SendMessage(Doctor doc) {
		super();

		SystemParameters systemParameters = SystemParametersDAO.select(
				Constant.MESSAGE_SENDING_DURATION_ID, doc);

		try {
			delay = Integer.parseInt(systemParameters.getValue());
		} catch (NumberFormatException e) {
			delay = 0;
		}

		this.sendMethod = SystemParametersDAO.select(
				Constant.WEB_SERVIS_ADDRESS_ID, doc).getValue();
		this.userName = SystemParametersDAO.select(
				Constant.WEB_SERVIS_USERNAME_ID, doc).getValue();
		this.passWord = SystemParametersDAO.select(
				Constant.WEB_SERVIS_PASSWORD_ID, doc).getValue();
		this.from = SystemParametersDAO.select(Constant.FROM_ID, doc)
				.getValue();

	}

	public SendMessage(String to, String text) {
		super();
		this.to = to;
		this.text = text;

	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	private int send(String to, String text) {
		int result = 0;
		String URL = sendMethod + "?username=" + userName + "&password="
				+ passWord + "&from=" + from + "&to=" + to + "&text=" + text;

		try {
			URL obj = new URL(URL);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setDoInput(true);
			con.setDoOutput(true);

			DataOutputStream out = new DataOutputStream(con.getOutputStream());
			out.writeBytes(URL);
			out.flush();
			out.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			StringBuilder response = new StringBuilder();
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
				response.append("\n\n\n");
			}

			// parse response code
			String resultCode = response.substring(0, 4);
			result = Integer.parseInt(resultCode);
			in.close();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void run() {
		if (delay > 0) {
			while (true) {

				ArrayList<WaitingMessages> sndMessage = WaitingMessagesDAO
						.getAllMessages();
				WaitingMessagesDAO.deleteAllMessages();
				if (!sndMessage.isEmpty()) {
					for (int i = 0; i < sndMessage.size(); i++) {

						if (0 != send(sndMessage.get(i).getTelephoneNumber(),
								sndMessage.get(i).getMessage())) {

						}

					}

				}
				try {
					Thread.sleep(delay * 60 * 1000);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}

	}
}
