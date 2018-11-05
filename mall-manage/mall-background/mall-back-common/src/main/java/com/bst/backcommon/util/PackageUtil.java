package com.bst.backcommon.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * 获取指定包下所有 classes
 *
 * @author wanna
 * @link https://stackoverflow.com/questions/520328/can-you-find-all-classes-in-a-package-using-reflection
 * @date 2018-6-14
 */
public class PackageUtil {


    /**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     *
     * @param packageName The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static Class[] getClasses(String packageName) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);

        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }

        List<Class> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }

        return classes.toArray(new Class[classes.size()]);
    }

    /**
     * Recursive method used to find all classes in a given directory and subDirs.
     *
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {

        if (!directory.exists()) {
            return Collections.emptyList();
        }

        File[] files = directory.listFiles();

        if (Objects.isNull(files)) {
            return Collections.emptyList();
        }

        List<Class> classes = new ArrayList<>();
        for (File file : files) {
            if (file.isDirectory()) {
                classes.addAll(findClasses(file, packageName + Constant.POINT + file.getName()));
            } else if (file.getName().endsWith(Constant.CLASS)) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
