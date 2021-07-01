package com.drewgifford.beacon.module;

import java.util.ArrayList;
import java.util.List;

public class ModuleLoader {

    public List<BeaconModule> modules;

    public ModuleLoader(){
        this.modules = new ArrayList<BeaconModule>();

        // Default modules
        modules.add(new PlayerModule());
        modules.add(new ServerModule());
    }
}
