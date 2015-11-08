package org.bihe.Resource;

import java.awt.Image;
import java.awt.Toolkit;

/**
 * 
 * @author Erfan Samieyan
 *
 */
public class Resource {
	/**
	 * 
	 * @param name
	 *            : Image name which is beside of the org.bihe.Resource class.
	 * @return: An image
	 */
	public static Image getImage(String name) {
		return Toolkit.getDefaultToolkit().getImage(
				Resource.class.getResource(name));
	}
}
