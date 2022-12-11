import java.util.Iterator;
import java.util.LinkedList;

public class MultipleKnapsack {

    private LinkedList<Knapsack> knapsacks;
    private LinkedList<KnapsackItem> items;
    private Long value;

    /**
     * Method that gets all neighbors a current solution to the Multiple Knapsack Problem has.
     * @param knapsacks
     * @param items
     * @return LinkedList<MultipleKnapsack>
     */
    public LinkedList<MultipleKnapsack> getNeighbors(LinkedList<Knapsack> knapsacks, LinkedList<KnapsackItem> items) {
        LinkedList<MultipleKnapsack> knapsackNeighbors = new LinkedList<>();

        for (int gKnapsack = 0; gKnapsack < knapsacks.size(); gKnapsack++) {
            for (int gItem = 0; gItem < knapsacks.get(gKnapsack).getItems().size(); gItem++) {
                for (int lKnapsack = 0; lKnapsack < knapsacks.size(); lKnapsack++) {
                    for (int lItem = 0; lItem < knapsacks.get(lKnapsack).getItems().size(); lItem++) {
                        Knapsack globalKnapsack = knapsacks.get(gKnapsack);
                        Knapsack localKnapsack = knapsacks.get(lKnapsack);

                        if(!globalKnapsack.equals(localKnapsack)) {
                            LinkedList<KnapsackItem> globalItems = globalKnapsack.getItems();
                            LinkedList<KnapsackItem> localItems = localKnapsack.getItems();
                            if(globalItems.get(gItem).getWeight() <= localItems.get(lItem).getWeight() + localKnapsack.getCap()) {

                                MultipleKnapsack neighbor = new MultipleKnapsack();
                                LinkedList<Knapsack> currentKnapsack = new LinkedList<>();
                                LinkedList<KnapsackItem> currentItems = new LinkedList<>(items);
                                for(Knapsack knapsack : knapsacks) {
                                    if(knapsack.equals(localKnapsack)) {
                                        Knapsack local = new Knapsack(knapsack);
                                        local.setCap(localKnapsack.getCap() + localItems.get(lItem).getWeight() - globalItems.get(gItem).getWeight());
                                        local.getItems().set(lItem, globalItems.get(gItem));
                                        currentKnapsack.add(local);
                                    } else if(knapsack.equals(globalKnapsack)) {
                                        Knapsack global = new Knapsack(knapsack);
                                        global.setCap(global.getCap() + global.getItems().get(gItem).getWeight());
                                        global.getItems().remove(gItem);
                                        currentKnapsack.add(global);
                                    } else {
                                        currentKnapsack.add(new Knapsack(knapsack));
                                    }
                                }

                                neighbor.setKnapsacks(currentKnapsack);
                                neighbor.setItems(currentItems);
                                neighbor.shuffleItemsInKnapsacks();
                                neighbor.greedyMultipleKnapsack(currentItems);
                                neighbor.calculateValue();
                                knapsackNeighbors.add(neighbor);
                            }
                        }
                    }
                }
            }
        }

        return knapsackNeighbors;
    }

    /**
     * Method that tries to find neighbors for a solution that have a better outcome than the solution itself.
     * @param knapsacks
     * @return MultipleKnapsack
     */
    public MultipleKnapsack neighborSearch(MultipleKnapsack knapsacks) {
        LinkedList<MultipleKnapsack> neighbors = getNeighbors(knapsacks.getKnapsacks(), knapsacks.getItems());
        for (MultipleKnapsack neighbor : neighbors) {
            if(neighbor.getValue() > knapsacks.getValue()) {
                knapsacks = neighborSearch(neighbor);
            }
        }

        return knapsacks;
    }

    /**
     * Method that shuffles or packs the items so that there's space for other items to be added.
     */
    public void shuffleItemsInKnapsacks() {
        LinkedList<KnapsackItem> itemsInKnapsacks = new LinkedList<>();
        for (Knapsack knapsack : knapsacks) {
            itemsInKnapsacks.addAll(knapsack.getItems());
        }

        itemsInKnapsacks.sort((i1, i2) -> {
            if (i1.getWeight() > i2.getWeight()) {
                return -1;
            } else if (i1.getWeight() < i2.getWeight()) {
                return 1;
            } else {
                return 0;
            }
        });

        for(Knapsack knapsack : knapsacks) {
            knapsack.getItems().clear();
            knapsack.resetCap();
            for(Iterator<KnapsackItem> it = itemsInKnapsacks.iterator(); it.hasNext(); ) {
                KnapsackItem item = it.next();
                if(item.getWeight() <= knapsack.getCap()) {
                    knapsack.addItem(item);
                    it.remove();
                }
            }
        }
    }

    /**
     * Method that solves the Multiple Knapsack Problem by a greedy approach.
     * @param items
     */
    public void greedyMultipleKnapsack(LinkedList<KnapsackItem> items) {

        items.sort((i1, i2) -> {
            if (i1.getValueByWeight() > i2.getValueByWeight()) {
                return -1;
            } else if (i1.getValueByWeight() < i2.getValueByWeight()) {
                return 1;
            } else {
                return 0;
            }
        });

        Knapsack bestKnapsack;
        double bestWeightDifference;
        double currentWeightDifference;

        for (int i = 0; i < items.size(); i++) {
            if(!this.items.contains(items.get(i))) {
                this.items.add(items.get(i));
            }
            bestWeightDifference = Integer.MAX_VALUE;
            bestKnapsack = null;
            for (int j = 0; j < knapsacks.size(); j++) {
                if(knapsacks.get(j).getCap() >= items.get(i).getWeight()) {
                    currentWeightDifference = knapsacks.get(j).getCap() - items.get(i).getWeight();
                    if(currentWeightDifference < bestWeightDifference && currentWeightDifference > 0) {
                        bestWeightDifference = currentWeightDifference;
                        bestKnapsack = knapsacks.get(j);
                    }
                }
            }
            if(bestKnapsack != null) {
                bestKnapsack.addItem(items.get(i));
                this.items.remove(items.get(i));
            }
        }
    }

    /**
     * Method that calculates a MultipleKnapsack's total value.
     */
    public void calculateValue() {
        Long value = 0L;

        for(Knapsack knapsack : knapsacks) {
            for (KnapsackItem item : knapsack.getItems()) {
                value += item.getValue();
            }
        }

        this.value = value;
    }

    /**
     * Method that sets the items that are not in a knapsack already.
     * @param items
     */
    public void setItems(LinkedList<KnapsackItem> items) {
        this.items = items;
    }

    /**
     * Method that sets all of the knapsacks.
     * @param knapsacks
     */
    public void setKnapsacks(LinkedList<Knapsack> knapsacks) {
        this.knapsacks = knapsacks;
    }

    /**
     * Method that gets the total value of a MultipleKnapsack.
     * @return Long
     */
    public Long getValue() {
        return value;
    }

    /**
     * Constructor that instantiates knapsacks and items.
     */
    public MultipleKnapsack() {
        knapsacks = new LinkedList<>();
        items = new LinkedList<>();
    }

    /**
     * Method that gets all of the knapsacks in the MultipleKnapsack.
     * @return LinkedList<Knapsack>
     */
    public LinkedList<Knapsack> getKnapsacks() {
        return knapsacks;
    }

    /**
     * Method that gets all of the items that are not in a knapsack already.
     * @return LinkedList<KnapsackItem>
     */
    public LinkedList<KnapsackItem> getItems() {
        return items;
    }

    /**
     * Method that adds a knapsack into the MultipleKnapsack.
     * @param knapsack
     */
    public void addKnapsack(Knapsack knapsack) {
        knapsacks.add(knapsack);
    }

}
