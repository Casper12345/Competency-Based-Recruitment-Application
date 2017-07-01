package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Casper on 23/06/2017.
 */
public class ComparingAlgorithm {

   public Integer compare(List<Integer> toCompare, List<Integer> toCompare2  ){
    List<Integer> calculate = new ArrayList<>();


    for(int i = 0; i < toCompare.size(); i++){

      calculate.add(Math.abs(toCompare.get(i) - toCompare2.get(i)));

    }

    return calculate.stream().reduce((a,b) -> a + b).get() / calculate.size();

   }


}
