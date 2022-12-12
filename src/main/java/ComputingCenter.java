/**
 * A class that represents a computingCenter.
 */
public class ComputingCenter {

    private final Long computingPower;

    private final String name;

    /**
     * Constructor that instantiates computingPower and name for an item.
     * @param computingPower
     * @param name
     */
    public ComputingCenter(Long computingPower, String name) {
        this.computingPower = computingPower;
        this.name = name;
    }

    /**
     * Method that gets the computingPower of a ComputingCenter.
     * @return Long
     */
    public Long getComputingPower() {
        return computingPower;
    }

    /**
     * Method that gets the name of a ComputingCenter.
     * @return String
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ComputingCenter{" +
                "computingPower=" + computingPower +
                ", name='" + name + '\'' +
                '}';
    }
}
