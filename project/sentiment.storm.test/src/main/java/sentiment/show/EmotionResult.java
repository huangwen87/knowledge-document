package sentiment.show;

/**
 * 
 * @author zhufeng
 *
 */
public class EmotionResult {
	private double pos;
	private double neg;
	private int result;
	
	public void setPos(double pos){
		this.pos = pos;
	}
	
	public void setNeg(double neg){
		this.neg = neg;
	}
	
	public void setResult(int result){
		this.result = result;
	}
	
	public double getPos(){
		return pos;
	}
	
	public double getNeg(){
		return neg;
	}
	
	public int getResult(){
		return result;
	}
}
