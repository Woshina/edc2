import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class RegexEngineTest {

  @Test
  public void setenfa() {
    RegexEngine re= new RegexEngine();
    ArrayList<ArrayList<HashMap>> efa = new ArrayList<ArrayList<HashMap>>();
    efa=re.Setenfa("a+b|c*");
    int number=efa.size();

    assertEquals(9, number);
  }
}