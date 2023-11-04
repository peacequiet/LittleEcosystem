public class Consumer extends Population {
    private double metabolicRate;
    private  final double trophicRatio = 0.1; //TODO: Implement trophic ratio to regulate birth and death rates

    public Consumer(String name, int index, double size, double metabolicRate,
        double reproductionRate, double deathRate)
    {
        super(name, index, size, reproductionRate, deathRate);
        this.metabolicRate = metabolicRate;
        // this.trophicRatio = trophicRatio;
    }

    public double getTrophicRatio() {
        return trophicRatio;
    }

    // gets how much to eat then deducts that amount from food pop
    public void metabolize(Population food)
    {
        double popMetabolism = metabolicRate * size;
        food.setSize(food.getSize() - popMetabolism); 
    }
}
