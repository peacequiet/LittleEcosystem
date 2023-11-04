import java.util.*;

public class Ecosystem {
    protected HashSet<Population> ecosystem = new HashSet<Population>();

    public Ecosystem()
    {

    }

    public void addPopulation(Population pop)
    {
        this.ecosystem.add(pop);
    }
}
