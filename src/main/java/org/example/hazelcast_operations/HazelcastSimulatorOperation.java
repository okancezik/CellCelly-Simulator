package org.example.hazelcast_operations;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import org.example.util.configurations.Configuration;
import org.example.util.constants.StringConstants;

import java.util.Collection;

public class HazelcastSimulatorOperation {
    private static final ClientConfig config = Configuration.getConfig();
    private static final HazelcastInstance hazelcast = HazelcastClient.newHazelcastClient(config);

    private static IMap<Object, Object> map = hazelcast.getMap(StringConstants.mapName);

    public static Collection<Object> getAllMsisdn() {
        try {
            return map.keySet();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
