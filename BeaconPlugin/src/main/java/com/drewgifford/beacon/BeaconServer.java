package com.drewgifford.beacon;

import spark.Spark;

public class BeaconServer {

    BeaconPlugin plugin;

    public BeaconServer(BeaconPlugin plugin){
        this.plugin = plugin;
    }

    public void initialize() throws Exception{
        // This is the reason the JAR is so big... thanks Obama!
        Spark.stop();


        Spark.port(8080);
        //TODO: remove?
        enableCors();

        Spark.post("/", (req, res) -> {
            try {
                /*PlayerModule module = new PlayerModule();

                List<String> permissions = new ArrayList<String>();
                permissions.add("player.inventory.*");
                permissions.add("player.world");
                permissions.add("player.location");

                JsonObject obj = module.update().getCollection().toJson(permissions);
                String response = obj.toString();*/
                String response = "test!";


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
