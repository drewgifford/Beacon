package com.drewgifford.beacon;

import com.drewgifford.beacon.entry.BeaconCollection;
import com.drewgifford.beacon.module.BeaconModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import spark.Spark;

import java.util.ArrayList;
import java.util.List;

public class BeaconServer {

    BeaconPlugin plugin;

    public BeaconServer(BeaconPlugin plugin){
        this.plugin = plugin;
    }

    public void initialize() throws Exception{
        // This is the reason the JAR is so big... thanks Obama!
        Spark.awaitStop();

        //TODO: remove?
        enableCors();

        Spark.post("/", (req, res) -> {
            try {

                String key = "" + req.queryParams("key");

                //TODO: remove
                List<String> permissions = new ArrayList<String>();
                permissions.add("*");

                List<BeaconModule> modules = BeaconPlugin.moduleLoader.getModules();

                BeaconCollection finalCollection = new BeaconCollection();

                for(BeaconModule module : modules){
                    finalCollection.add(module.getId(), module.getCollection(), module.getId());
                }

                JsonObject obj = finalCollection.toJson(permissions);



                String response = obj.toString();

                return response;
            } catch (Exception e){
                e.printStackTrace();
                return "Internal Server Error";
            }
        });

        ChatUtil.sendMessage(null, "Web server initialized!");


    }


    public void enableCors(){
        // Trans rights are human rights
        Spark.options("/*",
                (request, response) -> {

                    String accessControlRequestHeaders = request
                            .headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers",
                                accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = request
                            .headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods",
                                accessControlRequestMethod);
                    }

                    return "OK";
                });

        Spark.before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
    }




}
