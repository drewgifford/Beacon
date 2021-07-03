package com.drewgifford.beacon.module;

import com.drewgifford.beacon.entry.BeaconCollection;

public abstract class BeaconModule {

    private BeaconCollection collection;
    private String moduleId;
    private String moduleName;
    private String moduleDescription;
    private String moduleVersion;
    private String author;
    private String[] dependencies;
    boolean enabled;

    public BeaconModule(String moduleId, String moduleName, String moduleDescription, String moduleVersion, String author, String[] dependencies) {
        this.collection = new BeaconCollection();
        this.moduleId = moduleId;
        this.moduleName = moduleName;
        this.moduleDescription = moduleDescription;
        this.moduleVersion = moduleVersion;
        this.author = author;
        this.dependencies = dependencies;
        this.enabled = false;
    }

    public BeaconModule update() throws Exception{

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
    public String getAuthor() { return this.author; }
    public String[] getDependencies() { return this.dependencies; }

    public BeaconCollection getCollection(){
        return this.collection;
    }
    public void setCollection(BeaconCollection collection){
        this.collection = collection;
    }
}
