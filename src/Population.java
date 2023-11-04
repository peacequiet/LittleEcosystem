import java.util.*;

// TODO: Utilize "name" field
public abstract class Population {
    protected String name;

    protected int index;

    protected double size;
    protected double reproductionRate;
    protected double deathRate;

    protected HashMap<Population, Boolean> trophicLinks = new HashMap<Population, Boolean>();

    public Population(String name, int index, double size, double reproductionRate, double deathRate)
    {
        this.name = name;
        this.index = index;
        this.size = size;
        this.reproductionRate = reproductionRate;
        this.deathRate = deathRate;
    }

    public String getName()
    {
        return this.name;
    }

    public int getIndex()
    {
        return this.index;
    }

    public double getSize()
    {
        return this.size;
    }

    public double getReproductionRate()
    {
        return this.reproductionRate;
    }

    public double getDeathRate()
    {
        return this.deathRate;
    }

    public HashMap<Population, Boolean> getTrophicLinks()
    {
        return this.trophicLinks;
    }

    public void setSize(double size)
    {
        this.size = size;
    }

    public void setLink(Population linkedPopulation, boolean direction)
    {
        trophicLinks.put(linkedPopulation, direction);
    }
}
