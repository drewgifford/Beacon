package com.drewgifford.beacon.module;

import com.drewgifford.beacon.entry.BeaconCollection;
import com.google.gson.JsonObject;

public class BeaconModule {

    private BeaconCollection collection;
    private String moduleId;

    public BeaconModule() {
        this.collection = new BeaconCollection();
    }

    public BeaconModule update(){

        return this;
    }

    public String getModuleId(){
        return this.moduleId;
    }

    public BeaconCollection getCollection(){
        return this.collection;
    }
    public void setCollection(BeaconCollection collection){
        this.collection = collection;
    }
}
