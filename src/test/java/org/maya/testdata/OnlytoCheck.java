package org.maya.testdata;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class OnlytoCheck {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	}

	public static Object[][] getData(String path) throws IOException {
		DataReader d1 = new DataReader();
		List<HashMap<String, String>> dataitr = d1.getdatafromfile(path);
		Object[][] obj1 = new Object[dataitr.size()][2];
		for (int i = 0; i < dataitr.size(); i++) {
			obj1[i][0] = dataitr.get(i).get("email");
			obj1[i][1] = dataitr.get(i).get("password");
		}
		return obj1;
	}

}
