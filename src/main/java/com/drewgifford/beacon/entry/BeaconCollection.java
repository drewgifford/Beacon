package com.drewgifford.beacon.entry;

import com.drewgifford.beacon.ChatUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BeaconCollection {

    HashMap<String, CollectionObject> map;

    public BeaconCollection(){
        this.map = new HashMap<String, CollectionObject>();
    }

    public void add(String key, Object entry, String permission){
        this.map.put(key, new CollectionObject(entry, permission));
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
