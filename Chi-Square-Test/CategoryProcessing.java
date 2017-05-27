package naverHck;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CategoryProcessing {
	private HashMap<Integer, Integer> articleNum = new HashMap<Integer, Integer>();
	private List<HashMap<String, Integer>> catList = new ArrayList<HashMap<String, Integer>>();
	private int[] catNum;// = { 5000, 5000, 4999, 3428, 5000, 3963, 5000, 5000, 5000, 5000, 5000, 3723, 4999 };

	public void counting(int numOfCategory, int numOfFeature, ArrayList<String> featureList)
			throws IOException {
		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream("shopping-ca-cls.data.tsv"), "UTF-8"));

		HashMap<String, Integer> initialMap = new HashMap<String, Integer>();

		for (int j = 0; j < numOfFeature; j++)
			initialMap.put(featureList.get(j), 0);
		String line = null;
		String[] words = null;

		int count = 0, index = 0, featureNum = 0;
		
		while ((line = reader.readLine()) != null) {
			
			if (count == catNum[index]) {
				catList.add(index, initialMap);
				articleNum.put(index, featureNum);
				index++;
				count = 0;
				featureNum = 0;
				initialMap = new HashMap<String, Integer>();
				for (int j = 0; j < numOfFeature; j++)
					initialMap.put(featureList.get(j), 0);
				
			}
			words = line.split("\t")[6].replaceAll("[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]", " ").split(" ");

			for (int k = 0; k < words.length; k++)
				if (initialMap.containsKey(words[k])) {
					int cnt = initialMap.get(words[k]) + 1;
					initialMap.put(words[k], cnt);
					featureNum++;
				}
			
			count++;
		}
		
		for (int i=0; i<13; i++)
			System.out.println(catList.get(i).get("Canvas"));
		for (int i=0; i<13; i++)
			System.out.println(articleNum.get(i));
		
	}
	
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

	public void setArticleNum(HashMap<Integer, Integer> articleNum) {
		this.articleNum = articleNum;
	}

	public int[] getCatNum() {
		return catNum;
	}

	public void setCatNum(int[] catNum) {
		this.catNum = catNum;
	}

	public void setCatList(List<HashMap<String, Integer>> catList) {
		this.catList = catList;
	}

	// getter
	public List<HashMap<String, Integer>> getCatList() {
		return catList;
	}
}