package sentiment.util;

public class NewsData {

	private long newsid = 0;
	private String title = null;
	private String text = null;
	private int sentiment = 0;
	private double posRate = 0.0;
	private double negRate = 0.0;
	private long processTime = 0;
	
	public void setId(long newsid){
		this.newsid = newsid;
	}
	
	public long getId(){
		return newsid;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public String getText(){
		return text;
	}
	
	public void setSentiment(int sentiment){
		this.sentiment = sentiment;
	}
	
	public int getSentiment(){
		return sentiment;
	}
	
	public void setPosRate(double posRate){
		this.posRate = posRate;
	}
	
	public double getPosRate(){
		return posRate;
	}
	
	public void setNegRate(double negRate){
		this.negRate = negRate;
	}
	
	public double getNegRate(){
		return negRate;
	}
	
	public void setProcessTime(long processTime){
		this.processTime = processTime;
	}
	
	public long getProcessTime(){
		return processTime;
	}
}
