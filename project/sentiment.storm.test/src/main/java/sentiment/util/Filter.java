package sentiment.util;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Filter implements java.io.Serializable 
{
	/**
     * 
     * @param input String.
     * @return 
     */
    public String ToDBC(String input) {
    	char c[] = input.toCharArray();
    	for (int i = 0; i < c.length; i++) {
    		if (c[i] == '\u3000') {
    			c[i] = ' ';
    		} 
    		else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
    			c[i] = (char) (c[i] - 65248);
    		}
    	}
        String returnString = new String(c);
        
        return returnString;
    }
	
	/**
     * 
     * @param input String.
     * @return 
     */
	public String delHtml(String input) {
		if (input == null || input.trim().equals("")) {
			return "";
		}
		
		// filter html tag
		String str = input.replaceAll("<[^>]*>", " ");
		
		return str;
	}
	
	/**
     *
     * @param input String.
     * @return 
     */
	public String regex_filter(String input) {
		if(input == null || input.length()<=0) {
			return "";
		}
		
		String str = input.replaceAll("[^u4E00-u9FA5]{1,5}(\u7f51|\u793e)([^u4E00-u9FA5]|[0-9A-Za-z]){2,12}(\u7535|\u8baf|\u6d88\u606f)", "");//
		str = str.replaceAll("[/(��](\u8bb0\u8005|\u8d23\u4efb\u7f16\u8f91)([^u4E00-u9FA5]|[:0-9a-zA-Z]){2,12}[/)��]", "");//
		str = str.replaceAll("\u6765\u6e90[^u4E00-u9FA5]{2,6}(\u62a5|\u7f51)", "");//
		str = str.replaceAll("[����][^u4E00-u9FA5]{1,6}\u7f51[^u4E00-u9FA5]{0,6}[����]", "");//
		str = str.replaceAll("\u636e[^u4E00-u9FA5]{1,10}\u62a5\u9053", "");//
		str = str.replaceAll("[(����][^��^��]{1,12}[)����]", " ");//
		
		return str;
	}

	/**
     * 
     * 
     */
	public String filterByClas(String input) {
		if (input == null || input.trim().equals("")) {
			return "";
		}

		input = " " + input;
		String str = input.replaceAll("[\\r\\n]", " ");
		str = str.replaceAll(" [^/]+/[rdpcueyowq]+[a-zA-Z]+", " ");
		str = str.replaceAll("/[a-zA-Z]+", " "); // remove tag
		str = str.replaceAll("\\p{Punct}", " "); // remove punctuation
		str = str.trim().replaceAll("[	 ]+", " ");// kongge table

		return str;
	}

	/**
     * 
     * @param
     * @return 
     */
	public String[] filter_indiv_char(String[] chars) {
		if(chars.length<1) {
			return chars;
		}
		
		List<String> list = new ArrayList<String>();
		for(int i=0;i<chars.length;i++) {
			if((chars[i]!=null)&&(chars[i].length()>1)) {
				list.add(chars[i]);
			}
		}
		String[] a = new String[]{""};
		String[] words = (String[])list.toArray(a);
		return words;
	}
}
