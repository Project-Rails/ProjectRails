package org.projectrails;

public class PluginLogger {
    private String name;
    public PluginLogger(String name) {
        this.name = name;
    }

    /**
     * Prints a info message to the console.
     * 
     * If you what to have backwards support use <br>
     * System.out.println("[YourPluginName]: message");
     */
    public void info(Object message) {
        System.out.println("[" + name + "]: " + message);
    }

    /**
     * Prints a error message to the console.
     * 
     * If you what to have backwards support use <br>
     * System.err.println("[YourPluginName]: message");
     */
    public void error(Object message) {
        System.err.println("[" + name + "]: " + message);
    }
}
