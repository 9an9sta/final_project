package framework.helpers;

import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

@Log4j2

public class ListHelper {

    public static List<Double> restoreStringValueAsDouble(List<String> list) {
        log.info("Restore String list as Double list");
        List<Double> productDoubleList = new ArrayList<>();
        for (String product : list) {
            productDoubleList.add(Double.parseDouble(product));
        }
        return productDoubleList;
    }

    public static List<String> restoreDoubleValueAsString(List<Double> list) {
        log.info("Restore Double list as String list");
        List<String> productDoubleList = new ArrayList<>();
        for (Double product : list) {
            productDoubleList.add(String.valueOf(product));
        }
        return productDoubleList;
    }
}
