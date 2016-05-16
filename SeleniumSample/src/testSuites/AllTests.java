package testSuites;

import gMailTest.simpleGmailLoginJUnitTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	simpleGmailLoginJUnitTest.class
})
public class AllTests {

}
