/**
 * A class that represents a KnapsackItem.
 */
public class KnapsackItem {

    private Long weight;
    private Long value;
    private double valueByWeight;
    private String name;

    /**
     * Constructor that instantiates weight, value and name for an item.
     * @param weight
     * @param value
     * @param name
     */
    public KnapsackItem(Long weight, Long value, String name) {
        this.weight = weight;
        this.value = value;
        valueByWeight = (double) value / (double) weight;
        this.name = name;
    }

    /**
     * Method that gets the value / weight value from an item.
     * @return double
     */
    public double getValueByWeight() {
        return valueByWeight;
    }

    /**
     * Method that returns the weight an item has.
     * @return Long
     */
    public Long getWeight() {
        return weight;
    }

    /**
     * Method that gets the value an item has.
     * @return Long
     */
    public Long getValue() {
        return value;
    }

    /**
     * Method that sets the name of an item.
     * @return String
     */
    public String getName() {
        return name;
    }
}