package huangjingyu.algorithm.practice;

import huangjingyu.algorithm.practice.Knapsack_01.Item;
import java.io.FileReader;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

public class Knapsack_01Test extends TestBase {
    Knapsack_01 t = null;

    @Before
    public void setUp() throws Exception {
        t = new Knapsack_01();
    }

    @Test
    public void test1() throws Exception {
        String filePath = testDataDir + "knapsackdata01.txt";
        List<String> list = IOUtils.readLines(new FileReader(filePath));
        Item[] items = new Item[list.size()];
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            String[] arr = s.split(",");
            Item item = new Item();
            item.w = Integer.parseInt(arr[1]);
            item.v = Integer.parseInt(arr[2]);
            items[i] = item;
        }
        int v = t.maxValue(items, 7);
        System.out.println(v);
    }

}
