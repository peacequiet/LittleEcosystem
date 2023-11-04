public class Manager {

    // runs metabolism
    public static boolean runMetabolism(Consumer consumer)
    {
        boolean check = false;

        for (Population consumerFood : consumer.getTrophicLinks().keySet()) {
            if (consumer.getTrophicLinks().get(consumerFood) 
                && consumerFood.getSize() > 0)
            {
                consumer.metabolize(consumerFood);
                check = true;
            }
        }

        return check; // lets us know whether this pop fed or not
    }

    // increases population based on rate    
    public static void runReproduction(Population population, boolean successfulFeeding)
    {
        if (successfulFeeding)
        {
            population.setSize(population.getSize() + population.getReproductionRate());
        }
    }

    // decreases population based on rate
    public static void runDeath(Consumer consumer)
    {
        consumer.setSize(consumer.getSize() - consumer.getDeathRate());
    }

    public static void updateWorld(Consumer plankton, Phytoplankton phytoplankton)
    {
        boolean successfulFeeding = Manager.runMetabolism(plankton);

        runReproduction(plankton, successfulFeeding);
        runDeath(plankton);

        System.out.println();
        System.out.println("Plankton: ");
        System.out.printf("%s%d%n%s%.2f%s%n",
                  "Index: ", plankton.getIndex(), 
                          "Size: ", plankton.getSize(), 
                          " Million");
        System.out.println("Phytoplankton: ");
        System.out.printf("%s%d%n%s%.2f%s%n", 
                 "Index: ",  phytoplankton.getIndex(), 
                         "Size: ", phytoplankton.getSize(), 
                         " Million");
        System.out.println("-----------------------------------");
    }
}
