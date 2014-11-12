package sentiment.util;

//import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.LinkedList;

import org.apache.log4j.Logger;

import sentiment.configure.Configure;
import ICTCLAS.I3S.AC.ICTCLAS50;

public class NLPIRWordSegment3 implements WordSegment{
  private ICTCLAS50 testNLPIR = new ICTCLAS50();
  private Logger log = Logger.getLogger(NLPIRWordSegment3.class);
  private LinkedList<String> sentWords = new LinkedList<String>();
  private HashSet<String> separateWords = new HashSet<String>();
  private static Configure configure;

  public NLPIRWordSegment3(Configure m_configure) throws UnsupportedEncodingException {
  	configure = m_configure;
  	String path = configure.nlpirData;
		if(testNLPIR.ICTCLAS_Init(path.getBytes("GB2312")) == false) {
		    System.out.println("Init Fail!");
		    return;
		}
		testNLPIR.ICTCLAS_SetPOSmap(1);
		String usrdir = configure.nlpirUsrDict;
		try {
		    int num = testNLPIR.ICTCLAS_ImportUserDictFile(usrdir.getBytes("GB2312"),0);
		    if(num == 0) {
		    	log.info("import user dict error, num is 0");
		    }
		} catch (UnsupportedEncodingException e) {
		    e.printStackTrace();
		}
  }

  public String[] GetWords(String fileString) throws UnsupportedEncodingException {
		fileString = filterString(fileString);
		byte wordBytes[] = testNLPIR.ICTCLAS_ParagraphProcess(fileString.getBytes("GB2312"), 2, 0);
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
			byte nativeBytes[] = testNLPIR.ICTCLAS_ParagraphProcess(input.getBytes("GB2312"), 2,0);
			output = new String(nativeBytes, "GB2312");
		}
		catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			ex.printStackTrace();
		}
		return output;
  }
  
  public void exit(){
	  testNLPIR.ICTCLAS_Exit();
  }

  public static void main(String[] args) throws UnsupportedEncodingException{ 
  	
  	String path = NLPIRWordSegment3.class.getClassLoader().getResource("MyConfigure1.xml").getPath();
  	//System.out.println("path: " + path);
      Configure configure = Configure.getConfigure();
      configure.init(path);
  	
  	NLPIRWordSegment3 ws = new NLPIRWordSegment3(configure);
  	String[] words = ws.GetWords("张华平2009年底调入北京理工大学计算机学院。");
  	for(String s : words)
  		System.out.println(s);
  }
}
