package org.projectrails;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.projectrainbow.Updater;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Project Rails updater
 */
public class Rail_Updater {
    private static final String BRANCH = "master";

    public static int check() {
        Attributes a = getManifest(Rail_Updater.class).getMainAttributes();
        String hash = a.getValue("GitCommitHash");
        if (hash.endsWith("-dirty")) hash = hash.replace("-dirty", "");

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(
                    "https://api.github.com/repos/Project-Rails/ProjectRails/compare/" + BRANCH + "..." + hash).openConnection();
            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) return -2; // Unknown commit

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            JsonObject obj = (JsonObject) new JsonParser().parse(reader);
            String status = obj.get("status").getAsString();

            if (status.equalsIgnoreCase("identical")) return 0;

            if (status.equalsIgnoreCase("behind")) return obj.get("behind_by").getAsInt();

            return -1;
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
            e.printStackTrace();
            System.out.println("Loading MANIFEST for class " + clz + " failed! See above error.");
            return null;
        }
    }

    public static int rainbowCheck() {
        String latest = Updater.getLatestVersion();
        if (!latest.startsWith("ERROR")) {
            try {
                int behind = (Integer.valueOf(latest) - Integer.valueOf(Rails.getRainbowVersion()));
                return behind;
            } catch (NumberFormatException e) {
                return -1;
            }
        } else return -2;
    }
}