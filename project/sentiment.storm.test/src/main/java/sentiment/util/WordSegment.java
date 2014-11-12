package sentiment.util;

import java.io.UnsupportedEncodingException;

public interface WordSegment {

  public String[] GetWords(String fileString) throws UnsupportedEncodingException;
  
  public String[] GetSentenceWords(String fileString) throws UnsupportedEncodingException;

  public String filterString(String input);
  
  public String clasSentence(String input);
  
}

