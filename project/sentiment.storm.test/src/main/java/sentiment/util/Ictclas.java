package sentiment.util;

import sentiment.configure.Configure;

public class Ictclas
{
	// log
	//static private Logger log = Logger.getLogger(Ictclas.class.getName());
	
	private NLPIRWordSegment wordSegment;
	
	public Ictclas(Configure m_configure){
		wordSegment = new NLPIRWordSegment(m_configure);
	}
	
	public String clasSentence(String input) {
		
		return wordSegment.clasSentence(input);
	}
	
	public void exit() {
		wordSegment.exit();
	}
}
