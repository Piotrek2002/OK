import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class KnapsackProblemCreator {


    private MultipleKnapsack result;

    /**
     * Method that create knapsackProblem and generate solution.
     */
    public void createAndSolveKnapsackProblem() throws IOException, ParseException {
        JSONReader jsonReader=new JSONReader("example.json");

        MultipleKnapsack knapsacks = new MultipleKnapsack();
        jsonReader.getComputingCenterList().forEach(computingCenter -> {
            knapsacks.addKnapsack(new Knapsack(computingCenter.getComputingPower(), computingCenter.getName()));
        });

        LinkedList<KnapsackItem> items = new LinkedList<>();
        jsonReader.getCalculationTaskList().forEach(calculationTask -> {
            items.add(new KnapsackItem(calculationTask.getSize(),calculationTask.getValue(),calculationTask.getName()));
        });


        knapsacks.greedyMultipleKnapsack(items);
        knapsacks.calculateValue();
        result = knapsacks.neighborSearch(knapsacks);
        printResult();
    }

    /**
     * Method that print result.
     */
    public void printResult(){
        result.getKnapsacks().sort(Comparator.comparing(Knapsack::getName));
        int iterator;
        for (Knapsack knapsack : result.getKnapsacks()) {
            iterator=1;
            System.out.println("Computing center"
                    + "\nName: " + knapsack.getName()
                    + "\nComputing power: " + knapsack.getStartWeight()
                    + "\nRemaining computing power: " + knapsack.getCap() + "\n");
            for(KnapsackItem item : knapsack.getItems()) {
                System.out.println("Calculation task "+iterator
                        + "\nName: " + item.getName()
                        + "\nValue: " + item.getValue()
                        + "\nComputing power needed: " + item.getWeight()+"\n");
                iterator++;
            }
            System.out.println("---------------------------");
        }

        System.out.println("Total value: " + result.getValue());
    }
}
