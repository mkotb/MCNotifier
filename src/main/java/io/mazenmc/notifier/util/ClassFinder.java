package io.mazenmc.notifier.util;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassFinder {

    public static <T> List<Class<? extends T>> find(String pkg, Class<T> filter, File f) throws Exception {
        List<Class<? extends T>> classes = new ArrayList<>();
        pkg = pkg.replaceAll("\\.", File.separator);

        JarFile jar = new JarFile(f);
        Enumeration<JarEntry> enumeration = jar.entries();
        JarEntry entry = null;

        while(enumeration.hasMoreElements() && (entry = enumeration.nextElement()) != null) {
            String name = entry.getName();
            Class<?> cls;

            if(name.endsWith(".class") && name.startsWith(pkg) && !(name.contains("$")) &&
                    (filter.isAssignableFrom(Class.forName(name.replaceAll(".class", "").replaceAll(File.separator, "."))) || filter.equals(Object.class))) {
                cls = Class.forName(name.replaceAll(".class", "").replaceAll(File.separator, "."));
                classes.add(cls.asSubclass(filter));
            }
        }

        return classes;
    }

    public static <T> List<Class<? extends T>> find(String pkg, Class<T> filter, Plugin f) throws Exception {
        return find(pkg, filter, new File(f.getClass().getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("%20", " ")));
    }

    public static List<Class<?>> find(String pkg, File f) throws Exception {
        return find(pkg, Object.class, f);
    }

    public static <T> List<Class<? extends T>> find(String pkg, Class<T> filter) throws Exception{
        List<Class<? extends T>> classes = new ArrayList<>();

        for(File f : Bukkit.getWorldContainer().listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".jar");
            }
        })) {
            classes.addAll(find(pkg, filter, f));
        }

        return classes;
    }

    public static List<Class<?>> find(String pkg) throws Exception {
        return find(pkg, Object.class);
    }
}
