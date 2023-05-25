import static org.junit.Assert.*;
import org.junit.*;
import java.util.Arrays;
import java.util.List;

class IsMoon implements StringChecker {
  public boolean checkString(String s) {
    return s.equalsIgnoreCase("moon");
  }
}

public class TestListExamples {
  @Test(timeout = 500)
  public void testMergeRightEnd() {
    List<String> left = Arrays.asList("a", "b", "c");
    List<String> right = Arrays.asList("a", "d");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "a", "b", "c", "d");
    assertEquals(expected, merged);
  }
  
  @Test(timeout = 500)
  public void testMergeRightEnd1() {
    List<String> left = Arrays.asList("a", "a", "b");
    List<String> right = Arrays.asList("a", "a");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "a", "a", "a", "b");
    assertEquals(expected, merged);
  }

  @Test(timeout = 500)
  public void testFilter1() {
    List<String> left = Arrays.asList("Moan", "Moon", "Mooooon");
    List<String> right = Arrays.asList("Mo0n", "mOoN");
    IsMoon sc = new IsMoon();
    List<String> filtered = ListExamples.filter(left, sc);
    List<String> expected = Arrays.asList("Moon");
    assertEquals(expected, filtered);
  }

  @Test(timeout = 500)
  public void testFilter2() {
    List<String> left = Arrays.asList("Moan", "Moon", "Mooooon");
    List<String> right = Arrays.asList("Mo0n", "mOoN");
    IsMoon sc = new IsMoon();
    List<String> filtered = ListExamples.filter(right, sc);
    List<String> expected = Arrays.asList("mOoN");
    assertEquals(expected, filtered);
  }
}