package naverHck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MakeTrainningSet {
	
	public void makingSet(String fileName, HashMap<String, Integer> indexMap, float ResultChi[],
			List<Pair<String, Float>> ChiList, int numOfCategory, String fileN, int[] catNum) throws IOException {

		
		File outputFile = new File(fileN);
		FileWriter fWriter = new FileWriter(outputFile, false);
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(fileName), "UTF-8"));

			String line = null;
			String[] splitWord = null;
			int weight = 10;
			
			int count = 0, index = 0;
			
			while ((line = reader.readLine()) != null) {
				if (line.length() == 0)
					continue;
				
				if (count == catNum[index]) {
					index++;
					count = 0;
				}
				count++;
				
				
				splitWord = line.split(" ");
				String rbuffer = "";

				ArrayList<Integer> arr = new ArrayList<>();
				int cnt = 0;
				for (int k = 0; k < splitWord.length; k++)
					if (indexMap.containsKey(splitWord[k]))
						arr.add(indexMap.get(splitWord[k]));
					else
						cnt++;
				Collections.sort(arr);
				Set<Integer> s = new HashSet<Integer>();
				
				for (int k = 0; k < splitWord.length - cnt; k++) {
					if (!s.contains(arr.get(k) + 1)) {
						if (ResultChi[arr.get(k)] != 0.0) {
							s.add(arr.get(k) + 1);
							rbuffer += (arr.get(k) + 1) + ":" + ResultChi[arr.get(k)] * weight + " ";
						}
						else {
							s.add(arr.get(k) + 1);
							rbuffer += (arr.get(k) + 1) + ":1.0 ";
						}
					}
				}
				if (rbuffer.length() != 0) {
					fWriter.write("\r\n" + (index + 1) + " ");
					fWriter.write(rbuffer);
					fWriter.flush();
				}
			}
			reader.close();
		fWriter.close();
	}
}