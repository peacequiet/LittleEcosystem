public class Manager {

    // runs metabolism
    public static void runMetabolism(Consumer consumer)
    {
        for (Population consumerFood : consumer.getTrophicLinks().keySet()) {
            if (consumer.getTrophicLinks().get(consumerFood) 
                && consumerFood.getSize() > 0)
            {
                consumer.metabolize(consumerFood);
            }
        }
    }

    public static double activateTrophicRatio(Consumer consumer)
    {
        double foodSize = 0;
        for (Population consumerFood : consumer.getTrophicLinks().keySet()) 
        {
            foodSize += consumerFood.getSize();   
        }

        // This expression increases as the ratio of consumer size to food size decreases, 
        // and decreases as said ratio increases.
        return consumer.getTrophicRatio() / (consumer.getSize() / foodSize);
    }

    // increases population based on rate
    // TODO: Traverse every population in ecosystem    
    public static void runReproduction(Population population, double activeTrophicRatio)
    {
        double activeReproRate = population.getReproductionRate() * (1 - activeTrophicRatio);
        population.setSize(population.getSize() + activeReproRate);
    }

    // decreases population based on rate
    // TODO: Traverse every population in ecosystem
    public static void runDeath(Consumer consumer, double activeTrophicRatio)
    {
        double activeDeathRate = consumer.getDeathRate() / (1 - activeTrophicRatio);
        consumer.setSize(consumer.getSize() - activeDeathRate);
    }

    public static void calcConsumerPop(Consumer consumer)
    {
        runMetabolism(consumer);
        runReproduction(consumer, activateTrophicRatio(consumer));
        runDeath(consumer, activateTrophicRatio(consumer));
    }

    // Updates world and prints statistics to console 
    public static void updateWorld(Ecosystem ecosystem)
    {
        for (Consumer consumer : ecosystem.getConsumers())
        {
            calcConsumerPop(consumer);
            
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
