package org.bihe.fileIO;

import java.io.File;
import java.util.ArrayList;

import org.bihe.bean.VisitTimes;

public class VisitTimesMessageController {
	private ArrayList<VisitTimes> visitTimesList = new ArrayList<VisitTimes>();

	public VisitTimesMessageController() {
		super();
	}

	public ArrayList<VisitTimes> getVisitTimesList() {
		return visitTimesList;
	}

	public void setVisitTimesList(ArrayList<VisitTimes> visitTimesList) {
		this.visitTimesList = visitTimesList;
	}

	@SuppressWarnings("unchecked")
	public void loadVisitTimesFile() {
		File fi = new File("visitTimes.obj");
		if (fi.exists()) {
			visitTimesList = (ArrayList<VisitTimes>) FileIO
					.loadFile("visitTimes.obj");
		} else {

			FileIO.saveFile(visitTimesList, "visitTimes.obj");
		}
	}

	public void updateVisitTimesFile() {
		FileIO.saveFile(visitTimesList, "visitTimes.obj");
	}

}
