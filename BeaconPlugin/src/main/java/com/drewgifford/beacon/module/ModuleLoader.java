package com.drewgifford.beacon.module;

import com.drewgifford.beacon.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.lang.Class;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarFile;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class ModuleLoader {

    private List<BeaconModule> modules;

    public ModuleLoader(){
        this.modules = new ArrayList<BeaconModule>();
    }

    private String modulesFolderPath = "plugins" + File.separator + "Beacon" + File.separator + "modules";

    public void reloadModules() throws Exception {

        ChatUtil.sendMessage(null, "Reloading all modules...");

        for(BeaconModule mod : modules){
            mod.setEnabled(false);
            modules.remove(mod);
        }

        File modulesFolder = new File(modulesFolderPath);



        List<BeaconModule> validModules = new ArrayList<BeaconModule>();

        if(!modulesFolder.exists()){
            throw new Exception("Modules folder does not exist");
        }

        for (final File module : modulesFolder.listFiles()){
            BeaconModule mod = loadSingleModule(module);
            if(mod == null){
                continue;
            } else {
                validModules.add(mod);
            }

        }

        modules = validModules;


    }

    public BeaconModule loadSingleModule(File module){
        BeaconModule mod;
        try {
            mod = loadModuleFromFile(module);

        } catch (Exception e){
            //TODO : Specific checks
            ChatUtil.sendMessage(null, "ERROR Loading " + module.getName());
            e.printStackTrace();
            mod = null;
        }

        if (mod != null) {
            ChatUtil.sendMessage(null, mod.getName() + " Module successfully loaded");
            mod.setEnabled(true);
            return mod;
        }
        ChatUtil.sendMessage(null, module.getName() + "Failed to load.");
        return null;
    }


    public void updateModules(){

        for(BeaconModule module : modules){

            try {
                module.update();
            } catch(Exception e){
                //TODO : Change to error message
                ChatUtil.sendMessage("Module " + module.getName() + " failed to update provided information. The module has been disabled.");
                module.setEnabled(false);


            }

        }

    }


    public BeaconModule loadModuleFromFile(File module) throws Exception {

        if(!module.getName().toLowerCase().endsWith(".jar")){
            return null;
        }

        final Class<? extends BeaconModule> moduleClass = findClassInFile(module, BeaconModule.class);

        if (moduleClass == null){
            // TODO: Severe warning
            ChatUtil.sendMessage(null, "No BeaconModule class found in " + module.getName());
            return null;
        }

        BeaconModule mod = moduleClass.getConstructor().newInstance();

        return mod;
    }



    // CREDIT TO PLACEHOLDERAPI TEAM FOR THIS CODE. LEGENDS!
    private <T> Class<?extends T> findClassInFile(File file, Class<T> clazz) throws IOException, ClassNotFoundException{

        if (!file.exists()){
            return null;
        }
        // Code taken from PlaceholderAPI by extendedclip

        final URL jar = file.toURI().toURL();
        final URLClassLoader loader = new URLClassLoader(new URL[]{jar}, clazz.getClassLoader());
        final List<String> matches = new ArrayList<>();
        final List<Class<? extends T>> classes = new ArrayList<>();

        try (final JarInputStream stream = new JarInputStream(jar.openStream())) {
            JarEntry entry;
            while ((entry = stream.getNextJarEntry()) != null) {
                final String name = entry.getName();
                if (name.isEmpty() || !name.endsWith(".class")) {
                    continue;
                }

                matches.add(name.substring(0, name.lastIndexOf('.')).replace('/', '.'));
            }

            for (final String match : matches) {
                try {
                    final Class<?> loaded = loader.loadClass(match);
                    if (clazz.isAssignableFrom(loaded)) {
                        classes.add(loaded.asSubclass(clazz));
                    }
                } catch (final NoClassDefFoundError ignored) {
                }
            }
        }
        if (classes.isEmpty()) {
            loader.close();
            return null;
        }
        return classes.get(0);

    }

    public List<BeaconModule> getModules(){
        return this.modules;
    }

}
