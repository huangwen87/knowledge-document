package sentiment.prediction;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import sentiment.util.WordSegment;


public class BayesianPredict {
    private WordSegment wordSegment;
    private HashMap<String, String> features = new HashMap<String, String>();
    private HashSet<String> neg_features = new HashSet<String>();
    private HashSet<String> pos_features = new HashSet<String>();
    // <categoryName, <feature, probability>>
    private Map<String, Map<String, Double>> featuresDT = new HashMap<String, Map<String, Double>>();
    private Map<String, Integer> labels = new HashMap<String, Integer>();
    private Map<String, Integer> fileNum = new HashMap<String, Integer>();
    private Logger log = Logger.getLogger(BayesianPredict.class);
    private Timer timer = new Timer(true);
    private boolean isCandidate = false;
    
    private static final BayesianPredict m_instance = new BayesianPredict();
    
    private BayesianPredict() {
    	//initFromDb();
    	timer.schedule(new TimerTask(){
    		@Override
    		public void run() {
    			initFromDb();
    		}
    	}, 86400000, 86400000);
    }
    
    public void initWordSegment(WordSegment ws){
    	wordSegment = ws;
    }
    
    public void initFromDb(){
    	neg_features.clear();
    	pos_features.clear();
    	features.clear();
    	featuresDT.clear();
    	labels.clear();
    	fileNum.clear();
    	InitialFromDb.getConnection();
    	InitialFromDb.loadNegSeeds(neg_features);
    	InitialFromDb.loadPosSeeds(pos_features);
    	InitialFromDb.loadFeatures(features);
    	InitialFromDb.loadFeaturesDT(featuresDT);
    	InitialFromDb.loadLabels(labels);
    	InitialFromDb.loadFileNum(fileNum);
    	InitialFromDb.finish();
    	log.info("Initial Successfully");  	
    }
    
    public static BayesianPredict getInstance(){
    	return m_instance;
    }
    
    public boolean checkCandidate(){
    	return isCandidate;
    }

    public double predict(String title, String fileString) {
    	isCandidate = false;
    	int title_res = 0;
    	if(title != null){
	    	title_res = titleProc(title);
    	}
    	
		int head_res = headProc(fileString);
		
		if(head_res * title_res > 0){
			return title_res;
		}
		
		try {
		    String[] words = wordSegment.GetSentenceWords(fileString);
		    Map<String, Double> result = calcProb(words, title_res, head_res);
		    double sum = getValueSum(result);
	
		    String category = getTop(result);
		       
		    double res = 1 - result.get(category) / sum;
		    
		    if(labels.get(category) == 1){
		    	return res;
		    }
		    else{
		    	//Below is the modification that has satisfactory recall rate
		    	/*
		    	if(sum < -20000)
		    		return -res;
		    	else if(sum < -12000 && res > 0.525)
		    		return -res;
		    	else if(res > 0.55)
		    		return -res;
		    	else
		    		return 0.5;
		    		*/
		    	
		    	//Below is the testing modification
		    	if(sum < -20000)
		    		return -res;
		    	else if(sum < -12000 && res > 0.515)
		    		return -res;
		    	else if(sum < -12000 && res > 0.505){
		    		isCandidate = true;
		    		return -res;
		    	}
		    	else if(res > 0.545)
		    		return -res;
		    	else if(res > 0.53){
		    		isCandidate = true;
		    		return -res;
		    	}
		    	else
		    		return 0.5;
		    }
	
		} catch (UnsupportedEncodingException e) {
		    e.printStackTrace();
		}
		return -100;
    }

    private Map<String, Double> calcProb(String[] words, int title_pro, int head_pro) {
    	Map<String, Double> result = new HashMap<String, Double>();
		for (Entry<String, Map<String, Double>> entry : featuresDT.entrySet()) {
		    String categoryName = entry.getKey();
		    Map<String, Double> featureValue = entry.getValue();
		    // P(X|Yi) * P(Yi) = P(X1|Yi)*P(X2|Yi)*...*P(Xn|Yi)* P(Yi)
		    double prob = Math.log(getCategoryProb(categoryName)); // P(Yi)
		    double dt = 0.5;
		    String word;
		    int cate = 0;
		    
		    if(categoryName.equals("positive"))
		    	cate = 1;
		    else
		    	cate = -1;
		    
		    for (int i = 0; i < words.length; i++) {
				word = words[i].trim();
				if(features.containsKey(word)){
					if(features.get(word).equals(categoryName)){
						dt = (double)fileNum.get(categoryName)/100.0;
					}else{
						dt = 1.0/1000.0;
					}
				}else{
					if(featureValue.containsKey(word)) {
					    dt = featureValue.get(word);
					}
					else {
					    dt = 0.5;
					}
				}
				prob += Math.log(dt / (double) fileNum.get(categoryName));
		    }
		    
		    if(title_pro * cate > 0){
		    	prob += Math.log(0.5);
		    }
		    if(title_pro * cate < 0){
		    	prob += prob / 10;
		    }
		    
		    if(head_pro * cate > 0){
		    	prob += Math.log(0.1);
		    }
		    if(head_pro * cate < 0){
		    	prob += prob / 100;
		    }
		    
		    result.put(categoryName, prob);
		}
		return result;
    }

    private double getCategoryProb(String category) {
    	return 1 / (double) labels.size();
    }

    private String getTop(Map<String, Double> map) {
    	
		double value = 0;
		String key = null;
		boolean first = true;
		for (Entry<String, Double> entry : map.entrySet()) {
		    if(first) {
				value = entry.getValue();
				key = entry.getKey();
				first = false;
		    }
		    else if(entry.getValue() > value) {
				value = entry.getValue();
				key = entry.getKey();
		    }
		}
		return key;
    }

    private double getValueSum(Map<String, Double> map) {
		double sum = 0;
		for (Entry<String, Double> entry : map.entrySet()) {
		    sum += entry.getValue();
		}
		return sum;
    }

 
    public int titleProc(String title){
    	try{
    		String[] words = wordSegment.GetWords(title);
    		int neg = 0;
    		int pos = 0;
    		for(String word : words){
    			if(neg_features.contains(word)){
    				neg++;
    			}else if(pos_features.contains(word)){
    				pos++;
    			}
    		}
    		if(neg > 0 && pos == 0)
		    	return -1;
		    if(pos > 0 && neg == 0)
		    	return 1;
		    return 0;
    	}catch (UnsupportedEncodingException e) {
		    e.printStackTrace();
		    return 0;
		}
    }

    /**
     * 
     * @param fileString
     * @return 0: 无法判定 1: 正 -1: 负
     */
    private int headProc(String fileString) {
		int index = 0;
		for (int i = 0; i < fileString.length() / 2; i++) {
		    char ch = fileString.charAt(i);
		    if(ch == '。' || ch == '；') {
				index = i;
				break;
		    }
		}
		if(index == 0) {
		    return 0;
		}
		try {
		    String[] sentence = wordSegment.GetWords(fileString.substring(0, index));
		    int neg = 0;
		    int pos = 0;
		    for (int i = 0; i < sentence.length; i++) {
				String word = sentence[i];
				if(neg_features.contains(word)) {
				    neg++;
				}
				else if(pos_features.contains(word)) {
				    pos++;
				}
		    }
		    if(neg > 0 && pos == 0)
		    	return -1;
		    if(pos > 0 && neg == 0)
		    	return 1;
		} catch (UnsupportedEncodingException e) {
		    e.printStackTrace();
		    return 0;
		}
		return 0;
    }

    public boolean containFeatures(String word){
    	if(features.containsKey(word))
    		return true;
    	else
    		return false;
    }
    
    public double valueNegFeatureDT(String word){
    	Map<String, Double> featureValue = featuresDT.get("negative");
    	if(featureValue.containsKey(word)){
 		   	return featureValue.get(word)/(double)fileNum.get("negative");
    	}
    	return 0;
    }
    
    public double valuePosFeatureDT(String word){
    	Map<String, Double> featureValue = featuresDT.get("positive");
    	if(featureValue.containsKey(word)){
    		return featureValue.get(word)/(double)fileNum.get("positive");
    	}
    	return 0;
    }
}
