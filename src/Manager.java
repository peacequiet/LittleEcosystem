import java.util.Random;

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
        double minFoodSize = 0;
        for (Population consumerFood : consumer.getTrophicLinks().keySet()) 
        {
            if (consumerFood.getSize() <= minFoodSize || minFoodSize == 0)
            {
                minFoodSize = consumerFood.getSize();  
            }
        }

        // This expression increases as the ratio of consumer size to food size decreases, 
        // and decreases as said ratio increases.
        return consumer.getTrophicRatio() / (consumer.getSize() / minFoodSize);
    }

    // Consumer reproduction
    public static void runReproduction(Consumer consumer, double activeTrophicRatio)
    {
        consumer.setSize((consumer.getSize() + consumer.getReproductionRate()) 
            * (activeTrophicRatio));
    }

    // Autotroph reproduction
    public static void runReproduction(Autotroph autotroph)
    {
        autotroph.setSize(autotroph.getSize() + autotroph.getReproductionRate());
    }

    // Consumer death
    public static void runDeath(Consumer consumer, double activeTrophicRatio)
    {
        consumer.setSize((consumer.getSize() - consumer.getDeathRate()) 
            * (activeTrophicRatio));
    }

    // Autotroph death
    public static void runDeath(Autotroph autotroph)
    {
        autotroph.setSize(autotroph.getSize() - autotroph.getDeathRate());
    }

    public static boolean popCheck(Population population)
    {
        if (population.getSize() <= 0)
        {
            return false;
        }
        return true;
    }

    public static void calcConsumerPop(Consumer consumer)
    {
        if (popCheck(consumer))
        {
            runMetabolism(consumer);
            runReproduction(consumer, activateTrophicRatio(consumer));
            runDeath(consumer, activateTrophicRatio(consumer));
        }
        else
        {
            consumer.setSize(0);
        }
    }

    public static void calcAutotrophPop(Autotroph autotroph)
    {
        if (popCheck(autotroph))
        {
            runReproduction(autotroph);
            runDeath(autotroph);
            popCheck(autotroph);
        }
        else 
        {
            autotroph.setSize(0);
        }
    }

    // Initializes trophic link between two populations
    public static void createTrophicLink(Population consumer, Population target)
    {
        consumer.setLink(target, true);
    }

    // Updates world and prints statistics to console 
    public static void updateWorld(Ecosystem ecosystem)
    {
        for (Consumer consumer : ecosystem.getConsumers())
        {
            calcConsumerPop(consumer);
            if (!popCheck(consumer))
            {
                consumer.setSize(0);
            }
            System.out.println("" + consumer.getName() + ": ");
            System.out.printf("%s%d%n%s%.2f%s%n",
                    "Index: ", consumer.getIndex(), 
                            "Size: ", consumer.getSize(), 
                            " Million grams");
        }

        for (Autotroph autotroph : ecosystem.getAutotrophs())
        {
            calcAutotrophPop(autotroph);
            System.out.println("" + autotroph.getName() + ": ");
            System.out.printf("%s%d%n%s%.2f%s%n", 
                    "Index: ",  autotroph.getIndex(), 
                            "Size: ", autotroph.getSize(), 
                            " Million grams");
        }
        
        System.out.println("-----------------------------------");
    }
}
