public class Consumer extends Population {
    private double metabolicRate;

    public Consumer(int index, double size, double metabolicRate,
        double reproductionRate, double deathRate)
    {
        super(index, size, reproductionRate, deathRate);
        this.metabolicRate = metabolicRate;
    }


    public void metabolize(Population food)
    {
        double popMetabolism = metabolicRate * size;
        food.setSize(food.getSize() - popMetabolism); 
    }
}
