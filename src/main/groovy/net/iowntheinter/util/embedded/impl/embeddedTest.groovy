package net.iowntheinter.util.embedded.impl

import net.iowntheinter.util.embedded.embeddedComponent


/**
 * Created by grant on 4/19/16.
 */
class embeddedTest implements embeddedComponent {
    private main;
    public Thread ThreadHandle;

    public embeddedTest(Properties props) throws FileNotFoundException, IOException {


        ThreadHandle = new Thread() {
            public void run() {
                try {
                    System.sleep(10000000)
                } catch (IOException e) {
                    System.out.println("sleeper Failed");
                    e.printStackTrace(System.err);
                }
            }
        }.start();
    }


    @Override
    Thread getThreadHandle() {
        return null
    }
}
