import java.util.LinkedList;

/**
 * A class that represents a Knapsack.
 */
public class Knapsack {

    private Long cap;
    private final Long startWeight;
    private final String name;
    private final LinkedList<KnapsackItem> items;

    /**
     * Constructor that cerates a new knapsack with a cap, name and a startWeight value.
     * @param cap
     * @param name
     */
    public Knapsack(Long cap, String name) {
        this.cap = cap;
        this.name = name;
        this.startWeight = cap;
        items = new LinkedList<>();
    }

    /**
     * Copy constructor which copies a knapsack object and creates a new identical one.
     * @param knapsack
     */
    public Knapsack(Knapsack knapsack) {
        this.cap = knapsack.getCap();
        this.startWeight = knapsack.getStartWeight();
        this.name = knapsack.getName();
        this.items = new LinkedList<>(knapsack.getItems());
    }

    /**
     * Adds an item into the item-list and updates the cap so it's up to date.
     * @param item
     */
    public void addItem(KnapsackItem item) {
        if(item != null) {
            items.add(item);
            cap = cap - item.getWeight();
        }
    }

    /**
     * Stes the cap to the initial value of the knapsack.
     */
    public void resetCap() {
        cap = startWeight;
    }

    /**
     * Sets the cap to the value provided to the method.
     * @param cap
     */
    public void setCap(Long cap) {
        this.cap = cap;
    }

    /**
     * Method that returns the knapsack's startWeight
     * @return Long
     */
    public Long getStartWeight() {
        return startWeight;
    }

    /**
     * Method that returns the knapsack's cap.
     * @return
     */
    public Long getCap() {
        return cap;
    }

    /**
     * Method that returns the knapsack's name.
     * @return Long
     */
    public String getName() {
        return name;
    }

    /**
     * Method that returns the items the knapsack is currently holding.
     * @return Long
     */
    public LinkedList<KnapsackItem> getItems() {
        return items;
    }
}
