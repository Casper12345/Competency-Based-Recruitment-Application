package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.DataBaseConnection.ComparingAlgorithm;
import org.junit.Test;

/**
 * Created by Casper on 28/06/2017.
 */
public class ComparingAlgorithmTest {

  @Test
  public void compare() throws Exception {
    List<Integer> l1 = new ArrayList<>();
    List<Integer> l2 = new ArrayList<>();

    Collections.addAll(l1, 0,0,0,0,0,0,0,0,0,0);
    Collections.addAll(l2, 10,10,10,10,10,10,10,10,10,5);

      // similarity matrix




    ComparingAlgorithm c = new ComparingAlgorithm();

    System.out.println(c.compare(l1, l2));
  }

}