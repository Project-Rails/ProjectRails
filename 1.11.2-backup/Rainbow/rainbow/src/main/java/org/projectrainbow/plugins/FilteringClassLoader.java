package org.projectrainbow.plugins;
import java.net.URL;public class FilteringClassLoader extends ClassLoader{
public FilteringClassLoader(ClassLoader delegate){
super(delegate);
}@Overrideprotected Class<?> findClass(String name) throws ClassNotFoundException{
if (isBadClass(name)){
throw new ClassNotFoundException(name);
}return super.findClass(name);
}@Overridepublic Class<?> loadClass(String name) throws ClassNotFoundException{
if (isBadClass(name)){
throw new ClassNotFoundException(name);
}return super.loadClass(name);
}@Overrideprotected Class<?> loadClass(String name, boolean b) throws ClassNotFoundException{
if (isBadClass(name)){
throw new ClassNotFoundException(name);
}return super.loadClass(name, b);
}@Overridepublic URL getResource(String s){
if (isBadResource(s)){
return null;
}return super.getResource(s);
}@Overrideprotected URL findResource(String s){
if (isBadResource(s)){
return null;
}return super.findResource(s);
}private boolean isBadClass(String name){
return name.startsWith("org.projectrainbow.") || name.startsWith("net.minecraft.") || name.startsWith("joebkt.") || !name.contains(".");
}private boolean isBadResource(String name){
return name.equals("plugin.properties");
}}