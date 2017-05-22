package org.projectrails;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Updater for Project Rails
 */
public class Rail_Updater {
    private static final String repo = "Project-Rails/ProjectRails";
    private static final String BRANCH = "master";
    public static int check() {
        Attributes a = getManifest(Rail_Updater.class).getAttributes("GitCommitHash");
        String hash = a.getValue("GitCommitHash");
        try {
           HttpURLConnection connection = (HttpURLConnection) new URL("https://api.github.com/repos/" + repo + "/compare/" + BRANCH + "..." + hash).openConnection();
           connection.connect();
           if (connection.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) return -2; // Unknown commit
           BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
           JsonObject obj = (JsonObject) new JsonParser().parse(reader);
           String status = obj.get("status").getAsString();
           switch (status) {
               case "identical":
                   return 0;
               case "behind":
                   return obj.get("behind_by").getAsInt();
               default:
                   return -1;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return -3;
        }   
    }
    
    public static Manifest getManifest(Class<?> clz) {
        String resource = "/" + clz.getName().replace(".", "/") + ".class";
        String fullPath = clz.getResource(resource).toString();
        String archivePath = fullPath.substring(0, fullPath.length() - resource.length());

        try {
            InputStream input = new URL(archivePath + "/META-INF/MANIFEST.MF").openStream();
            return new Manifest(input);
        } catch (Exception e) {
            throw new RuntimeException("Loading MANIFEST for class " + clz + " failed!", e);
        }
    }
}
