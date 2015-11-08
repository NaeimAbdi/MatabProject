package org.bihe.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.bihe.bean.Doctor;
import org.bihe.bean.WaitingMessages;
import org.bihe.dao.WaitingMessagesDAO;
import org.bihe.util.GenerateWaitingMessages;
import org.bihe.util.SendMessage;

/**
 * This imple class that implements iGeneratedMessage which is interface, make
 * relation between messagesDAO and and IGeneratedMessage services. this
 * services set for implementation RMI networking system and client can use them
 * as server services.
 */
public class GeneratedMessagesImpl extends UnicastRemoteObject implements
		IGeneratedMessages {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected GeneratedMessagesImpl() throws RemoteException {
		super();
	}

	@Override
	public boolean doctorMessageRunner(Doctor doctor) throws RemoteException {

		SendMessage sendMessage = new SendMessage(doctor);
		Thread t = new Thread(sendMessage);
		t.start();

		return true;
	}

	@Override
	public boolean sendMessage(String text, String number)
			throws RemoteException {

		boolean check = false;

		WaitingMessages watingMsg = new WaitingMessages();

		watingMsg.setMessage(generateMessageTxt(text));
		watingMsg.setTelephoneNumber(number);

		check = WaitingMessagesDAO.insertMessage(watingMsg);

		return check;
	}

	@Override
	public boolean automaticSendMessage(boolean on_off, Doctor doctor)
			throws RemoteException {

		if (on_off) {

			new GenerateWaitingMessages(doctor);
		}

		return on_off;
	}

	private String generateMessageTxt(String txt) {

		String[] txtArray = txt.split(" ");
		String result = "";

		for (int f = 0; f < txtArray.length; f++) {
			result += txtArray[f] + "+";

		}
		String textRes = result.substring(0, result.length() - 1);

		String finlaRes = textRes;

		return finlaRes;

	}

	@Override
	public boolean sendWaitingMessage(Doctor doctor) throws RemoteException {

		SendMessage snd = new SendMessage(doctor);
		Thread t = new Thread(snd);
		t.start();

		return true;
	}

}
