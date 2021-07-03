package com.drewgifford.beacon;

import com.drewgifford.beacon.entry.BeaconCollection;
import com.drewgifford.beacon.module.BeaconModule;
import org.bukkit.Bukkit;
import org.bukkit.Server;

public class ServerModule extends BeaconModule {

    public ServerModule() {
        super("server", "Server", "Provides information on the Server", "1.0", "Toadally", new String[]{});
    }


    @Override
    public BeaconModule update() throws Exception{
        BeaconCollection serverColl = this.getCollection();

        Server server = Bukkit.getServer();

        serverColl.add("ip", server.getIp(), "server.ip");


        return this;
    }

}
