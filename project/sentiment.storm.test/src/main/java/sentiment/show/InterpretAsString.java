package sentiment.show;

import sentiment.configure.Configure;
import sentiment.prediction.BayesianPredict;
import sentiment.segmentation.factory.NLPIRFactory;
import sentiment.segmentation.factory.WordSegmentFactory;


/**
 * Result 	1: 	Positive
 * 			-1:	Negative
 * 			0:	uncertain
 * 			null:	ERROR!
 * @author zhufeng
 *
 */
public class InterpretAsString {
	private static WordSegmentFactory wsfactory;
	private static BayesianPredict b;
	
	public InterpretAsString(Configure m_configure){
		wsfactory = new NLPIRFactory(m_configure);
		b = wsfactory.MakeBayesPredict();
		b.predict(null, " ");
	}
	
	public EmotionResult explain(String news){
		return explain(null, news);
	}
	
	public EmotionResult explain(String title, String news){
		double res = b.predict(title, news);
		EmotionResult result = new EmotionResult();
		if(news.trim().length() == 0){
			//System.out.println("News error");
		    result = null;
		}
		else if(res + 100 < 0.0000000000000000001){
			//System.out.println("res = " + res);
		    result = null;
		}
		else if(Math.abs(res) - 0.5 < 0.00000000000000000000000001){
			result.setPos(res*100);
			result.setNeg((1-res)*100);
			result.setResult(0);
		}
		else if(res > 0){
			if(b.checkCandidate() == false){
				result.setPos(res*100);
				result.setNeg((1-res)*100);
				result.setResult(1);
			}else{
				result.setPos(res*100);
				result.setNeg((1-res)*100);
				result.setResult(2);
			}
		}else{
			if(b.checkCandidate() == false){
				result.setPos((1+res)*100);
				result.setNeg(-res*100);
				result.setResult(-1);
			}else{
				result.setPos((1+res)*100);
				result.setNeg(-res*100);
				result.setResult(-2);
			}
		}
		return result;
	}
	
	public static void main(String[] args){
		/*
		InterpretAsString ias = new InterpretAsString();
		EmotionResult result = ias.explain("暴力执法");
		System.out.println(result.getResult());
		System.out.println(result.getPos());
		System.out.println(result.getNeg());
		*/
	}
}
