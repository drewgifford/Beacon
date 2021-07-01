package com.drewgifford.beacon;

import com.drewgifford.beacon.module.PlayerModule;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpServer;
import spark.Spark;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class BeaconServer {

    BeaconPlugin plugin;

    public BeaconServer(BeaconPlugin plugin){
        this.plugin = plugin;
    }

    public void initialize() throws Exception{
        // This is the reason the JAR is so big... thanks Obama!
        Spark.port(8080);
        //TODO: remove?
        enableCors();

        Spark.post("/", (req, res) -> {
            try {
                PlayerModule module = new PlayerModule();

                List<String> permissions = new ArrayList<String>();
                permissions.add("players.inventory.*");

                JsonObject obj = module.update().getCollection().toJson(permissions);
                String response = obj.toString();


                System.out.println(req.body());
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
