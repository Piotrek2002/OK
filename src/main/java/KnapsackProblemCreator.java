import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

public class KnapsackProblemCreator {


    private MultipleKnapsack result;

    /**
     * Method that create knapsackProblem and generate solution.
     */
    public void createAndSolveKnapsackProblem() throws IOException, ParseException {
        JSONReader jsonReader = new JSONReader("example.json");

        MultipleKnapsack knapsacks = new MultipleKnapsack();
        jsonReader.getComputingCenterList().forEach(computingCenter -> {
            knapsacks.addKnapsack(new Knapsack(computingCenter.getComputingPower(), computingCenter.getName()));
        });

        LinkedList<KnapsackItem> items = new LinkedList<>();
        jsonReader.getCalculationTaskList().forEach(calculationTask -> {
            items.add(new KnapsackItem(calculationTask.getSize(), calculationTask.getValue(), calculationTask.getName()));
        });


        knapsacks.greedyMultipleKnapsack(items);
        knapsacks.calculateValue();
        result = knapsacks.neighborSearch(knapsacks);
        printResult();
    }

    public void createAndCheckKnapsackProblemTime() {
        long start = 0;
        long stop = 0;
        int[] knapsackTab = {10, 100, 500, 1000};
        int[] itemTab = {10, 50, 100, 250, 500, 1000, 2500, 5000, 10000};
        MultipleKnapsack knapsacks = new MultipleKnapsack();
        LinkedList<KnapsackItem> items = new LinkedList<>();
        for (int knapsackCount : knapsackTab) {
            for (int itemCount : itemTab) {
                start = System.nanoTime();
                Random random = new Random();
                for (int i = 0; i < knapsackCount; i++) {
                    knapsacks.addKnapsack(new Knapsack(random.nextLong(100) + 1, "s" + i));
                }
                for (int i = 0; i < itemCount; i++) {
                    items.add(new KnapsackItem(random.nextLong(10) + 1, random.nextLong(10) + 1, "t" + i));
                }
                knapsacks.greedyMultipleKnapsack(items);
                stop = System.nanoTime();
                System.out.println("knapsackCount: " + knapsackCount + " itemCount: " + itemCount + " time: " + (stop - start));
            }
        }
    }

    /**
     * Method that print result.
     */
    public void printResult() {
        result.getKnapsacks().sort(Comparator.comparing(Knapsack::getName));
        int iterator;
        for (Knapsack knapsack : result.getKnapsacks()) {
            iterator = 1;
            System.out.println("Computing center"
                    + "\nName: " + knapsack.getName()
                    + "\nComputing power: " + knapsack.getStartWeight()
                    + "\nRemaining computing power: " + knapsack.getCap() + "\n");
            for (KnapsackItem item : knapsack.getItems()) {
                System.out.println("Calculation task " + iterator
                        + "\nName: " + item.getName()
                        + "\nValue: " + item.getValue()
                        + "\nComputing power needed: " + item.getWeight() + "\n");
                iterator++;
            }
            System.out.println("---------------------------");
        }

        System.out.println("Total value: " + result.getValue());
    }
}
