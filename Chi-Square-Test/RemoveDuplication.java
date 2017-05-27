package naverHck;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class RemoveDuplication {
	private HashMap<Integer, Integer> articleNum = new HashMap<Integer, Integer>();
	private int[] catNum;// = { 5000, 5000, 4999, 3428, 5000, 3963, 5000, 5000, 5000, 5000, 5000, 3723, 4999 };
	private Set<String> allFeature = new HashSet<String>();
	int counter = 0;

	public void removeDup() throws IOException {
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(new FileInputStream("shopping-ca-cls.data.tsv"), "UTF-8"));

			for (int i = 0; i < catNum.length; i++)
				articleNum.put(i, catNum[i]);
			
			String line = null;
			String[] splitWords = null;
			Set<String> catList = null;

			while ((line = reader.readLine()) != null) {
				if (line.length() == 0)
					continue;
				counter++;
				splitWords = line.split("\t")[6].replaceAll("[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]", " ").split(" ");
				
				catList = new HashSet<String>();
				splitWords = new HashSet<String>(Arrays.asList(splitWords)).toArray(new String[0]);
				for (int i=0; i<splitWords.length; i++) {
					if (splitWords[i].length() > 1) {
						allFeature.add(splitWords[i]);
						catList.add(splitWords[i]);
					}
				}
			}
			reader.close();

		} catch (

		FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// getter
	
	public int getAllArticleNum() {
		int count = 0;
		for (int i = 0; i < articleNum.size(); i++) {
			count += articleNum.get(i);
		}
		return count;
	}

	public HashMap<Integer, Integer> getArticleNum() {
		return articleNum;
	}

	public int getAllFeatureNum() {
		return allFeature.size();
	}

	public ArrayList<String> getAllFeature() {
		ArrayList<String> strSet = new ArrayList<String>();
		for (String s : allFeature)
			strSet.add(s);
		return strSet;
	}
}