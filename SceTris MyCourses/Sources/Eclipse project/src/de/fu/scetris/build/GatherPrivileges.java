/* CollectPrivileges.java / 12:46:49 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.build;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.CheckPrivilege;

/**
 *
 */
public class GatherPrivileges {
    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     * 
     * @param directory
     *            The base directory
     * @param packageName
     *            The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    private static List<Class<?>> findClasses(final File directory, final String packageName) {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                String $className = packageName + '.'
                        + file.getName().substring(0, file.getName().length() - 6);
                try {
                    classes.add(Class.forName($className));
                } catch (ClassNotFoundException $exc) {
                    System.err.format("Class \"%s\" not found!", $className);
                }
            }
        }
        return classes;
    }

    /**
     * Scans all classes accessible from the context class loader which belong to the given package
     * and subpackages.
     * 
     * @param packageName
     *            The base package
     * @return The classes
     */
    private static Class<?>[] getClasses(final String packageName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
        Enumeration<URL> resources;
        try {
            resources = classLoader.getResources(path);
        } catch (IOException $exc) {
            System.err.format("Could not load resources from \"%s\"\n");
            return new Class<?>[0];
        }
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }

    public static void main(final String... $args) {
        boolean $xml = false;
        final Set<String> $allPrivileges = new TreeSet<String>();
        final Collection<Class<?>> $allClasses = new HashSet<Class<?>>();
        for (String $name : $args) {
            if ($name.equals("-xml")) {
                $xml = true;
                continue;
            }
            try {
                $allClasses.add(Class.forName($name));
            } catch (ClassNotFoundException $exc) {
                $allClasses.addAll(Arrays.asList(getClasses($name)));
            }
        }

        for (Class<?> $c : $allClasses) {
            List<Class<?>> $classes = new LinkedList<Class<?>>(Arrays.asList($c.getClasses()));
            $classes.add(0, $c);
            for (Class<?> $class : $classes) {
                for (Method $m : $class.getMethods()) {
                    Action $action = $m.getAnnotation(Action.class);
                    if ($action != null) {
                        String[] $privileges = $action.requiresPrivilege();
                        for (String $privilege : $privileges) {
                            $allPrivileges.add($privilege);
                        }
                    }
                    CheckPrivilege $check = $m.getAnnotation(CheckPrivilege.class);
                    if ($check != null) {
                        String[] $privileges = $check.value();
                        for (String $privilege : $privileges) {
                            $allPrivileges.add($privilege);
                        }
                    }
                }
            }
        }
        if ($xml) {
            System.out.println("<?xml version=\"1.0\" ?>\n<privileges>");
            for (String $privilege : $allPrivileges) {
                System.out.format("\t<privilege>%s</privilege>\n",
                    $privilege.replace("<", "&lt;").replace("&", "&amp;"));
            }
            System.out.println("</privileges>");
        } else {
            for (String $privilege : $allPrivileges) {
                System.out.println($privilege);
            }
        }
    }
}
