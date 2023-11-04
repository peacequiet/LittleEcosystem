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
    // TODO: Traverse every population in ecosystem    
    public static void runReproduction(Population population, boolean successfulFeeding)
    {
        if (successfulFeeding)
        {
            population.setSize(population.getSize() + population.getReproductionRate());
        }
    }

    // decreases population based on rate
    // TODO: Traverse every population in ecosystem
    public static void runDeath(Consumer consumer)
    {
        consumer.setSize(consumer.getSize() - consumer.getDeathRate());
    }

    // Updates world and prints statistics to console 
    public static void updateWorld(Ecosystem ecosystem)
    {
        boolean successfulFeeding;

        for (Consumer consumer : ecosystem.getConsumers())
        {
            // Separate into its own method
            successfulFeeding = Manager.runMetabolism(consumer);
            runReproduction(consumer, successfulFeeding);
            runDeath(consumer);
            
            System.out.println();
            System.out.println("" + consumer.getName() + ": ");
            System.out.printf("%s%d%n%s%.2f%s%n",
                    "Index: ", consumer.getIndex(), 
                            "Size: ", consumer.getSize(), 
                            " Million");
        }

        for (Autotroph autotroph : ecosystem.getAutotrophs())
        {
            System.out.println("" + autotroph.getName() + ": ");
            System.out.printf("%s%d%n%s%.2f%s%n", 
                    "Index: ",  autotroph.getIndex(), 
                            "Size: ", autotroph.getSize(), 
                            " Million");
        }
        
        System.out.println("-----------------------------------");
    }
}
