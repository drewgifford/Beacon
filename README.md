# Beacon - Making Minecraft server data accessible to external applications

Provide a way for your server members to develop applications and access JSON-formatted data like player locations, inventories, pings, specific plugin data or server information without having to install individual plugins.

Beacon is built on Spigot, meaning it will be compatible on Spigot and its forks, although support is not provided on any platform but Spigot.

- Downloads (coming soon)
- Release Notes (coming soon)

## Requesting Data from Beacon
jQuery implementation
```javascript
    $.ajax({
		type: 'POST',
		url: "SERVER.IP:PORT",
		data: {"key": "KEY_PROVIDED_BY_SERVER_OWNER"},
		success: function(response){
		    // do stuff, data is in JSON
		},
		error: function(){
			// process error
		}
    });

```

## Modules

Beacon supports custom modules, which are JAR files that can allow for specific behavior and retrieval of data. To create a module, it's as simple as creating a Java project with a single class that extends `BeaconModule`. The exported jar is then placed into `/plugins/Beacon/modules/`.

Example module:
```java
public class ExampleModule extends BeaconModule {

    public ExampleModule() {
        super(
                "example", // Module ID
                "ExampleModule", // Module Name
                "Provides many key data points on the server", // Short Description
                "1.0", // Version
                "Drew Gifford", // Author
                new String[]{"ExamplePlugin", "Vault"}); // Plugin Dependencies
    }

    // This method is called when the module is "pinged" to provide updated data.
    // This can occur at a configurable interval.
    @Override
    public BeaconModule update() throws Exception{
        // All entries are stored in a BeaconCollection instance.
        // BeaconCollections can store any object value or other BeaconCollections.
        // You can easily nest BeaconCollections to create lists inside of lists.
        BeaconCollection coll = this.getCollection();

        Server server = Bukkit.getServer();
        
        // You can add entries to BeaconCollections and specify permissions needed to access them.
        serverColl.add("ip", server.getIp(), "server.ip");
        
        return this;
    }

}
```

### Adding Beacon as a Maven dependency
CURRENTLY A PLACEHOLDER
```xml
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
```
```xml
    <dependency>
        <groupId>com.drewgifford.beacon</groupId>
        <artifactId>Beacon</artifactId>
        <version>1.0-SNAPSHOT</version>
        <scope>provided</scope>
    </dependency>
```