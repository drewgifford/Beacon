package com.drewgifford.beacon.entry;

public class CollectionObject {

    private Object obj;
    private String permission;

    public CollectionObject(Object obj, String permission){
        this.obj = obj;
        this.permission = permission;
    }

    public Object getObject(){
        return this.obj;
    }
    public String getPermission(){
        return this.permission;
    }

    public CollectionObject setObject(Object obj){
        this.obj = obj;
        return this;
    }
    public CollectionObject setPermission(String permission){
        this.permission = permission;
        return this;
    }
}
