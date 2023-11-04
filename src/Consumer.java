public class Consumer extends Population {
    private double metabolicRate;
    private double trophicRatio; //TODO: Implement trophic ratio to regulate birth and death rates

    public Consumer(String name, int index, double size, double metabolicRate,
        double reproductionRate, double deathRate, double trophicRatio)
    {
        super(name, index, size, reproductionRate, deathRate);
        this.metabolicRate = metabolicRate;
        this.trophicRatio = trophicRatio;
    }

    // gets how much to eat then deducts that amount from food pop
    public void metabolize(Population food)
    {
        double popMetabolism = metabolicRate * size;
        food.setSize(food.getSize() - popMetabolism); 
    }
}
