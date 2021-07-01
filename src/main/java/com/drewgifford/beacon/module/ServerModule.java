package com.drewgifford.beacon.module;

import com.drewgifford.beacon.entry.BeaconCollection;
import org.bukkit.Bukkit;
import org.bukkit.Server;

public class ServerModule extends BeaconModule {

    public ServerModule() {
        super("server", "Server", "Provides information on the Server", "1.0");
    }


    @Override
    public BeaconModule update(){
        BeaconCollection serverColl = this.getCollection();

        Server server = Bukkit.getServer();

        serverColl.add("ip", server.getIp(), "server.ip");


        return this;
    }

}
