// TODO: Create a set to hold all the populations for a given run
public class RunModel {
    public static void main(String[] args) throws Exception {
        Consumer plankton 
            = new Consumer("Zooplankton", 1, 100, 1, .5, .4);
        Autotroph phytoplankton 
            = new Autotroph("Phytoplankton", 2, 10000, 139.68, 50);

        Manager.createTrophicLink(plankton, phytoplankton);

        Ecosystem ecosystem = new Ecosystem();

        ecosystem.addAutotrophs(phytoplankton);
        ecosystem.addConsumers(plankton);

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
