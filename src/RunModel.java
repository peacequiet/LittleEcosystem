
public class RunModel {
    public static void main(String[] args) throws Exception {
        Autotroph phytoplankton 
            = new Autotroph("Phytoplankton", 1, 10000, 139.68, 50);
        Consumer plankton 
            = new Consumer("Zooplankton", 2, 1000, 1, .5, .4);
        Consumer combJellies 
            = new Consumer("Comb Jellies", 3, 110, 5.3, .35, .2);
        Consumer nettles
            = new Consumer("Sea Nettles", 4, 10, 3.7, .1, .09);

        Ecosystem ecosystem = new Ecosystem();

        Manager.createTrophicLink(plankton, phytoplankton);
        Manager.createTrophicLink(combJellies, plankton);
        Manager.createTrophicLink(nettles, plankton);
        Manager.createTrophicLink(nettles, combJellies);

        ecosystem.addAutotrophs(phytoplankton);
        ecosystem.addConsumers(plankton);
        ecosystem.addConsumers(combJellies);
        ecosystem.addConsumers(nettles);

        displayWorld(ecosystem);
    }

    // Lets us print to console with delay
    private static void displayWorld(Ecosystem ecosystem) 
    {
        Thread updateThread = new Thread(() -> 
        {
            for (int generation = 0; generation < 200; generation++) 
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
