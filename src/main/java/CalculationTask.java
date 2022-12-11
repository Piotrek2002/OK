/**
 * A class that represents a CalculationTask.
 */
public class CalculationTask {

    private Long size;

    private Long value;

    private String name;


    /**
     * Constructor that instantiates size, value and name for an item.
     * @param size
     * @param value
     * @param name
     */
    public CalculationTask(Long size, Long value, String name) {
        this.size = size;
        this.value = value;
        this.name = name;
    }

    /**
     * Method that gets the size of a CalculationTask.
     * @return Long
     */
    public Long getSize() {
        return size;
    }

    /**
     * Method that gets the value of a CalculationTask.
     * @return Long
     */
    public Long getValue() {
        return value;
    }

    /**
     * Method that gets the name of a CalculationTask.
     * @return String
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CalculationTask{" +
                "size=" + size +
                ", value=" + value +
                ", name='" + name + '\'' +
                '}';
    }
}
