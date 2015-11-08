package org.bihe.fileIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class save file and load file
 * 
 */
public class FileIO {
	/**
	 * This method save object in file
	 * 
	 * @param object
	 *            is a first parameter to save file
	 * @param fileName
	 *            is a second parameter to save file
	 * @return boolean
	 */
	public static boolean saveFile(Object object, String fileName) {
		FileOutputStream file = null;
		ObjectOutputStream objOut = null;
		try {
			file = new FileOutputStream(fileName);
			objOut = new ObjectOutputStream(file);
			objOut.writeObject(object);
			objOut.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				objOut.close();
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NullPointerException e2) {
				return false;
			}

		}
		return true;
	}

	/**
	 * This method load files from the path
	 * 
	 * @param fileName
	 *            is a parameter to load file
	 * @return Object
	 */
	public static Object loadFile(String fileName) {
		Object result = null;
		FileInputStream file = null;
		ObjectInputStream objOut = null;
		try {
			file = new FileInputStream(fileName);
			objOut = new ObjectInputStream(file);
			result = objOut.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				objOut.close();
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NullPointerException e2) {
			}

		}

		return result;
	}

}
