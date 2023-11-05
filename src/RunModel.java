// TODO: Create a set to hold all the populations for a given run
public class RunModel {
    public static void main(String[] args) throws Exception {
        Autotroph phytoplankton 
            = new Autotroph("Phytoplankton", 1, 20000, 139.68, 50);
        Consumer plankton 
            = new Consumer("Zooplankton", 2, 2000, 1, .5, .4);
        Consumer combJellies 
            = new Consumer("Comb Jellies", 3, 110, 9.3, .35, .2);

        Manager.createTrophicLink(plankton, phytoplankton);
        Manager.createTrophicLink(combJellies, plankton);

        Ecosystem ecosystem = new Ecosystem();

        ecosystem.addAutotrophs(phytoplankton);
        ecosystem.addConsumers(plankton);
        ecosystem.addConsumers(combJellies);

        displayWorld(ecosystem);
    }

    // Lets us print to console with delay
    private static void displayWorld(Ecosystem ecosystem) 
    {
        Thread updateThread = new Thread(() -> 
        {
            for (int generation = 0; generation < 100; generation++) 
            {
                System.out.println("Generation " + generation);
                System.out.println();
                Manager.updateWorld(ecosystem);
                try 
                {
                    Thread.sleep(1000);
                } catch (InterruptedException e) 
                {
                    e.printStackTrace();
                }
            }
        });

        updateThread.setDaemon(false); // Set it to non-daemon so that it keeps running
        updateThread.start();
    }
}
