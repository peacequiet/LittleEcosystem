import java.util.*;

public class Ecosystem {
    protected HashSet<Autotroph> autotrophs = new HashSet<Autotroph>();
    protected HashSet<Consumer> consumers = new HashSet<Consumer>();

    public Ecosystem()
    {

    }

    public HashSet<Autotroph> getAutotrophs()
    {
        return this.autotrophs;
    }

    public HashSet<Consumer> getConsumers()
    {
        return this.consumers;
    }

    public void addAutotrophs(Autotroph pop )
    {
        this.autotrophs.add(pop);
    }

    public void addConsumers(Consumer pop)
    {
        this.consumers.add(pop);
    }
}
