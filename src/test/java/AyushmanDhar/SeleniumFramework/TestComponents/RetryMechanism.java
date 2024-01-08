package AyushmanDhar.SeleniumFramework.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryMechanism implements IRetryAnalyzer {		//add retryAnalyzer=RetryMechanism.class to annotation
	int count=0;
	int maxRetry=2;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(count<maxRetry) {
			count++;
			return true;
		}
		return false;
	}

}
