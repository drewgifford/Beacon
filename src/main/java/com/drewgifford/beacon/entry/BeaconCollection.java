package com.drewgifford.beacon.entry;

import com.drewgifford.beacon.ChatUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BeaconCollection {

    HashMap<String, CollectionObject> map;

    public BeaconCollection(){
        this.map = new HashMap<String, CollectionObject>();
    }

    public BeaconCollection add(String key, Object entry, String permission){
        if (entry == null){
            return this;
        }

        this.map.put(key, new CollectionObject(entry, permission));

        return this;
    }

    public BeaconCollection addLocation(String key, Location entry, String permission){
        BeaconCollection locCollection = new BeaconCollection();

        if (entry == null){
            return this;
        }

        locCollection.add("x", entry.getX(), permission);
        locCollection.add("y", entry.getY(), permission);
        locCollection.add("z", entry.getZ(), permission);
        locCollection.add("pitch", entry.getPitch(), permission);
        locCollection.add("yaw", entry.getYaw(), permission);
        locCollection.add("world", entry.getWorld().getName(), permission);

        return this.add(key, locCollection, permission);
    }

    public BeaconCollection addVector(String key, Vector entry, String permission){

        if (entry == null){
            return this;
        }

        BeaconCollection vecCollection = new BeaconCollection();

        vecCollection.add("x", entry.getX(), permission);
        vecCollection.add("y", entry.getY(), permission);
        vecCollection.add("z", entry.getZ(), permission);
        vecCollection.add("magnitude", entry.length(), permission);

        return this.add(key, vecCollection, permission);

    }

    public void remove(String key){
        this.map.remove(key);
    }

    public void removeEntry(Object entry){
        this.map.values().remove(entry);
    }

    public JsonObject toJson(List<String> permissions){

        JsonObject json = new JsonObject();

        Iterator iterator = this.map.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry element = (Map.Entry) iterator.next();

            String key = (String) element.getKey();

            CollectionObject value = (CollectionObject) element.getValue();
            String permission = value.getPermission();

            if(!BeaconPermission.checkPermission(permissions, permission)){
                continue;
            }

            Object obj = value.getObject();



            if (obj instanceof BeaconCollection){
                BeaconCollection coll = (BeaconCollection) obj;
                obj = coll.toJson(permissions);
            }

            json.add(key, new Gson().toJsonTree(obj));
            iterator.remove();

        }

        return json;

    }




}
