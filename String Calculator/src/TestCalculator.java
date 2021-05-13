import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCalculator {
	
	@Test
	public void TestEmptyString(){
		Assert.assertEquals(KataStringCalculator.sum(""), 0);
	}
	
	@Test
	public void TestStringWithSingleElement(){
		Assert.assertEquals(KataStringCalculator.sum("5"), 5);
	}
	
	@Test
	public void TestStringForMultipleElement(){
		Assert.assertEquals(KataStringCalculator.sum("1,2"), 3);
		Assert.assertEquals(KataStringCalculator.sum("1,"), 1);
		Assert.assertEquals(KataStringCalculator.sum("1,2,3"), 6);
	}

	@Test
	public void TestToHandleStringWithNewLine(){
		Assert.assertEquals(KataStringCalculator.sum("1\n2"), 3);
		Assert.assertEquals(KataStringCalculator.sum("1,2\n3"), 6);
	}
	
	@Test
	public void TestToHandleDelimiter(){
		Assert.assertEquals(KataStringCalculator.sum("//;\n1;2"), 3);
		Assert.assertEquals(KataStringCalculator.sum("//.\n2.3.1"), 6);
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void TestToCheckNegativeNumber(){
		KataStringCalculator.sum("1,2,-3");
	}
	
	@Test
	public void TestToHandleNumberGreaterThan1000(){
		Assert.assertEquals(KataStringCalculator.sum("1002"), 2);
		Assert.assertEquals(KataStringCalculator.sum("1002,10008"), 10);
	}
	
	@Test
	public void TestToCheckDelimiters(){
		Assert.assertEquals(KataStringCalculator.sum("//[***]\n1***2***3"), 6);
	}
	
	@Test
	public void TestToCheckMultipleDelimiters(){
		Assert.assertEquals(KataStringCalculator.sum("//[-][;]\n1-2;3"), 6);
		Assert.assertEquals(KataStringCalculator.sum("//[--][...]\n2--3...4"), 9);
		Assert.assertEquals(KataStringCalculator.sum("//[**][%%]\n1**2%%3"), 6);
	}
}
