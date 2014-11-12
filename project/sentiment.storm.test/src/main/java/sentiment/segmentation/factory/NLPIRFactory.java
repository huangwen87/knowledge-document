package sentiment.segmentation.factory;

import sentiment.configure.Configure;
import sentiment.filter.Bayes;
import sentiment.prediction.BayesianPredict;
import sentiment.prediction.InitialFromDb;
import sentiment.util.NLPIRWordSegment;
import sentiment.util.WordSegment;

public class NLPIRFactory implements WordSegmentFactory{
	
	private static Configure configure = null;

	public NLPIRFactory(Configure m_configure){
		configure = m_configure;
	}
	
	public WordSegment MakeWordSegment() {
		NLPIRWordSegment segment = new NLPIRWordSegment(configure);
		return segment;
	}

	public Bayes MakeContentFilter() {
		Bayes bayes  = new Bayes(configure.bayesfilterDriver, configure.bayesfilterUrl, configure.bayesfilterUser, configure.bayesfilterPassword, 0, configure);
		return bayes;
	}

	public Bayes MakeTitleFilter() {
		Bayes bayesTitle = new Bayes(configure.bayesfilterDriver, configure.bayesfilterUrl, configure.bayesfilterUser, configure.bayesfilterPassword, 1, configure);
		return bayesTitle;
	}

	public Bayes MakeWeiboFilter() {
		Bayes bayesWeibo = new Bayes(configure.bayesfilterDriver, configure.bayesfilterUrl, configure.bayesfilterUser, configure.bayesfilterPassword, 2, configure);
		return bayesWeibo;
	}

	public BayesianPredict MakeBayesPredict() {
		String sheets[] = {
			"ncps.news_sentipredict_features",
			"ncps.news_sentipredict_name",
			"ncps.news_sentipredict_seeds",
			"ncps.news_sentipredict_total",
		};
		InitialFromDb.init(sheets, configure);
		BayesianPredict b = BayesianPredict.getInstance();
		b.initFromDb();
		b.initWordSegment(MakeWordSegment());
		return b;
	}

}
