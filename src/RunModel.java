public class RunModel {
    public static void main(String[] args) throws Exception {
        Consumer plankton = new Consumer(
            1, 100, 0.2, .05, .07);
        Phytoplankton phytoplankton = new Phytoplankton(2, 10000, 0, 0);
        plankton.setLink(phytoplankton, true);
        phytoplankton.setLink(plankton, false);

        displayWorld(plankton, phytoplankton);
    }

    private static void displayWorld(Consumer plankton, Phytoplankton phytoplankton) 
    {
        Thread updateThread = new Thread(() -> 
        {
            for (int generation = 0; generation < 10; generation++) 
            {
                Manager.updateWorld(plankton, phytoplankton);
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
