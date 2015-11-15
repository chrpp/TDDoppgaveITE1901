import org.junit.runners.Suite;
import org.junit.runner.RunWith;

/**
 * Class for creating a test suite of the unit tests in CollectorTest, ConverterTest 
 * and ParameterizedConverterTest.
 * 
 * @author Christian Petersen
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses ({
	CollectorTest.class,
	ConverterTest.class,
	ParameterizedConverterTest.class
})

public class MyTestSuite {
}
