package sentiment.segmentation.factory;

import sentiment.filter.Bayes;
import sentiment.prediction.BayesianPredict;
import sentiment.util.WordSegment;

public interface WordSegmentFactory {
	/**
	 * Specify the strategy that is used to segment Chinese words
	 * @return the configured word segment class
	 */
	public WordSegment MakeWordSegment();
	
	/**
	 * Make the Bayes filter to deal with content
	 * @return Bayes mode with the specific Chinese word segment strategy
	 */
	public Bayes MakeContentFilter();
	
	/**
	 * Make the Bayes filter to deal with news' titles
	 * @return Bayes mode with the specific Chinese word segment strategy
	 */
	public Bayes MakeTitleFilter();
	
	/**
	 * Make the Bayes filter to deal with weibo
	 * @return Bayes mode with the specific Chinese word segment strategy
	 */
	public Bayes MakeWeiboFilter();
	
	/**
	 * Make the sentiment prediction model
	 * @return
	 */
	public BayesianPredict MakeBayesPredict();
}
