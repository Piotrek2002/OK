import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class JSONReader {

    private List<CalculationTask> calculationTaskList;

    private List<ComputingCenter> computingCenterList;

    /**
     * Constructor that read data from JSON and instantiates calculationTaskList and computingCenterList.
     * @param fileName
     */
    public JSONReader(String fileName) throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(fileName));

        JSONObject jo = (JSONObject) obj;

        JSONArray calculationTaskJA = (JSONArray) jo.get("calculationTasks");

        calculationTaskList = new ArrayList<>();

        Iterator<Map.Entry> itr1;

        Iterator itr2 = calculationTaskJA.iterator();

        while (itr2.hasNext()) {
            itr1 = ((Map) itr2.next()).entrySet().iterator();
            Long size = null;
            Long value = null;
            String name = null;
            while (itr1.hasNext()) {
                Map.Entry pair = itr1.next();
                if (pair.getKey().equals( "size")) {
                    size = (Long) pair.getValue();
                } else if (pair.getKey().equals("value")) {
                    value = (Long) pair.getValue();
                } else if (pair.getKey().equals("name")) {
                    name = (String) pair.getValue();
                }
            }
            calculationTaskList.add(new CalculationTask(size,value,name));
        }

        JSONArray computingCenterJA = (JSONArray) jo.get("computingCenter");

        computingCenterList = new ArrayList<>();

        itr2 = computingCenterJA.iterator();

        while (itr2.hasNext()) {
            itr1 = ((Map) itr2.next()).entrySet().iterator();
            Long computingPower = null;
            String name = null;
            while (itr1.hasNext()) {
                Map.Entry pair = itr1.next();
                if (pair.getKey().equals( "computingPower")) {
                    computingPower = (Long) pair.getValue();
                } else if (pair.getKey().equals("name")) {
                    name = (String) pair.getValue();
                }
            }
            computingCenterList.add(new ComputingCenter(computingPower,name));
        }

    }
    /**
     * Method that gets the calculationTaskList.
     * @return List<CalculationTask>
     */
    public List<CalculationTask> getCalculationTaskList() {
        return calculationTaskList;
    }
    /**
     * Method that gets the ComputingCenterList.
     * @return List<ComputingCenter>
     */
    public List<ComputingCenter> getComputingCenterList() {
        return computingCenterList;
    }
}
