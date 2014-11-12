package sentiment.util;

//import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.LinkedList;

import org.apache.log4j.Logger;

import sentiment.configure.Configure;

import kevin.zhang.NLPIR;

public class NLPIRWordSegment implements WordSegment{
    private NLPIR testNLPIR = new NLPIR();
    private Logger log = Logger.getLogger(NLPIRWordSegment.class);
    private LinkedList<String> sentWords = new LinkedList<String>();
    private HashSet<String> separateWords = new HashSet<String>();
    private static Configure configure;

    public NLPIRWordSegment(Configure m_configure) {
    	configure = m_configure;
    	/*
    	String[] path_candidate = new String[3];
    	path_candidate[0] = System.getProperty("user.dir").replaceAll("%20", " ");								//Non web path
    	path_candidate[1] = this.getClass().getResource("/").getPath().substring(1).replaceAll("%20", " ");		//windows web path
    	path_candidate[2] = this.getClass().getResource("/").getPath().replaceAll("%20", " ");					//linux web path
    	File file;
    	String path = "";
    	for(int i = 0; i < 3; i++){
    		file = new File(path_candidate[i] + "/Files/Data");
    		if(file.exists())
    		{
    			path = path_candidate[i] + "/Files";
    			break;
    		}
    	}
    	log.info(path);
		*/
    	String path = configure.nlpirData;
    	//String path = System.getProperty("user.dir").replaceAll("%20", " ") + "/Files";
    	//System.out.println(path);
		if(NLPIR.NLPIR_Init(path.getBytes(), 0) == false) {
		    System.out.println("Init Fail!");
		    return;
		}
		testNLPIR.NLPIR_SetPOSmap(1);
		String usrdir = configure.nlpirUsrDict;
		//String usrdir = path + "/Files";
		try {
		    int num = testNLPIR.NLPIR_ImportUserDict(usrdir.getBytes("GB2312"));
		    if(num == 0) {
		    	log.info("import user dict error, num is 0");
		    }
		} catch (UnsupportedEncodingException e) {
		    e.printStackTrace();
		}
    }

    public String[] GetWords(String fileString) throws UnsupportedEncodingException {
		fileString = filterString(fileString);
		byte wordBytes[] = testNLPIR.NLPIR_ParagraphProcess(fileString.getBytes("GB2312"), 1);
		String wordStr = new String(wordBytes, "GB2312");
		wordStr = filterByclas(wordStr);
		return wordStr.split(" ");
    }
    
    public String[] GetSentenceWords(String fileString) throws UnsupportedEncodingException{
    	String[] sentences = fileString.split("[。；;]");
    	String[] tmpuse = null;
    	sentWords.clear();
    	for(String sen : sentences){
    		tmpuse = GetWords(sen);
    		for(String word : tmpuse)
    			separateWords.add(word);
    		sentWords.addAll(separateWords);
    		separateWords.clear();
    		tmpuse = null;
    	}
    	return sentWords.toArray(new String[0]);
    }

    public String filterString(String input) {
		if(input == null || input.trim().equals("")) {
		    return "";
		}
		// filter html tag
		String str = input.trim().replaceAll("<[^>]*>", " ").trim();
		return str;
    }

    public static String filterByclas(String input) {
		if(input == null || input.trim().equals("")) {
		    return "";
		}
		input = " " + input;
		String str = input.replaceAll("[\\r\\n]", " ");
		str = str.replaceAll(" [^/]+/[rdpcueyowqm]+[a-zA-Z0-9]*", " ");
		str = str.replaceAll(" [a-zA-Z0-9+=][^/]*/[a-z]+[a-zA-Z0-9]*", " ");
		str = str.replaceAll(" [ /]/[xw]", " ");
		str = str.replaceAll("　+", "");
		str = str.replaceAll("/[a-zA-Z]+[a-zA-Z0-9]*", " "); // remove tag
		str = str.trim().replaceAll(" +", " ");
		return str;
    }
    
    public String clasSentence(String input){
    	String output = "";
		try {		
			byte nativeBytes[] = testNLPIR.NLPIR_ParagraphProcess(input.getBytes("GB2312"), 1);
			output = new String(nativeBytes, "GB2312");
		}
		catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			ex.printStackTrace();
		}
		return output;
    }
    
    public void exit(){
    	NLPIR.NLPIR_Exit();
    }

//    public static void main(String[] args) throws UnsupportedEncodingException{ 
//    	
//    	String path = NLPIRWordSegment.class.getClassLoader().getResource("MyConfigure1.xml").getPath();
//    	//System.out.println("path: " + path);
//        Configure configure = Configure.getConfigure();
//        configure.init(path);
//    	
//    	NLPIRWordSegment ws = new NLPIRWordSegment(configure);
//    	String[] words = ws.GetWords("今天天气不错");
//    	for(String s : words)
//    		System.out.println(s);
//    }
}
