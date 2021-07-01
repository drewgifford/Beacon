package com.drewgifford.beacon.module;

import com.drewgifford.beacon.entry.BeaconCollection;
import com.google.gson.JsonObject;
import org.bukkit.Location;

public abstract class BeaconModule {

    private BeaconCollection collection;
    String moduleId;
    String moduleName;
    String moduleDescription;
    String moduleVersion;
    boolean enabled;

    public BeaconModule(String moduleId, String moduleName, String moduleDescription, String moduleVersion) {
        this.collection = new BeaconCollection();
        this.moduleId = moduleId;
        this.moduleName = moduleName;
        this.moduleDescription = moduleDescription;
        this.moduleVersion = moduleVersion;
        this.enabled = true;
    }

    public BeaconModule update(){

        return this;
    }

    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }

    public boolean isEnabled(){
        return this.enabled;
    }

    public String getId(){
        return this.moduleId;
    }
    public String getName(){
        return this.moduleName;
    }
    public String getVersion(){
        return this.moduleVersion;
    }
    public String getDescription(){
        return this.moduleDescription;
    }

    public BeaconCollection getCollection(){
        return this.collection;
    }
    public void setCollection(BeaconCollection collection){
        this.collection = collection;
    }
}
