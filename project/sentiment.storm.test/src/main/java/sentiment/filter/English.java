package sentiment.filter;

public class English {
	public boolean isTotalEnglish(String text){
		String s = text.replaceAll("\\s", "").replaceAll("\\w", "").replaceAll("[,.?!'\"]", "");
		if(s.length() < text.length() / 4)
			return true;
		else
			return false;
	}
	
	public static void main(String[] args){
		String text1 = "This is a nice day\nI'd like to do some outdoor activities.\n";
		System.out.print(text1);
		English en = new English();
		System.out.println(en.isTotalEnglish(text1));
		String text2 = "今天不错啊\nI'd like to do some out door activities.\n";
		System.out.print(text2);
		System.out.println(en.isTotalEnglish(text2));
		String text3 = "今天不错啊\n可以出去逛逛\n";
		System.out.print(text3);
		System.out.println(en.isTotalEnglish(text3));
		
	}
}
