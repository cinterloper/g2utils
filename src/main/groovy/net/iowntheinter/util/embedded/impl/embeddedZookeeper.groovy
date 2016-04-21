package net.iowntheinter.util.embedded.impl

import net.iowntheinter.util.embedded.embeddedComponent

/**
 * Created by grant on 4/11/16.
 * originally :
 * https://gist.github.com/fjavieralba/7930018
 * this must be started before vertx, on a seperate Thread
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.zookeeper.server.ServerConfig;
import org.apache.zookeeper.server.ZooKeeperServerMain;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;

public class embeddedZookeeper implements embeddedComponent {
    private ZooKeeperServerMain zooKeeperServer;
    public Thread ThreadHandle;
    public embeddedZookeeper(Properties zkProperties) throws FileNotFoundException, IOException {
        QuorumPeerConfig quorumConfiguration = new QuorumPeerConfig();
        try {

            quorumConfiguration.parseProperties(zkProperties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        zooKeeperServer = new ZooKeeperServerMain();
        final ServerConfig configuration = new ServerConfig();
        configuration.readFrom(quorumConfiguration);


        ThreadHandle = new Thread() {
            public void run() {
                try {
                    zooKeeperServer.runFromConfig(configuration);
                } catch (IOException e) {
                    System.out.println("ZooKeeper Failed");
                    e.printStackTrace(System.err);
                }
            }
        }.start();
    }

    @Override
    Thread getThreadHandle() {
        return ThreadHandle
    }
}