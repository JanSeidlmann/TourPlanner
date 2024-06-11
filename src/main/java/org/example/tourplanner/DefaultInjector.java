package org.example.tourplanner;

import java.util.HashMap;
import java.util.Map;

public class DefaultInjector {
    //map, die Klassen (als Schlüssel) den Instanzen der Klassen (als Werte) zuordnet.
    private static final Map<Class<? extends Injectable>, Injectable> services = new HashMap<>();

    //Methode zum registrieren einer Instanz als Service
    private static <T extends Injectable> void registerService(Class<T> serviceClass, T serviceInstance){
        services.put(serviceClass, serviceInstance);
    }

    public static<T extends Injectable> T getService(Class<T> serviceClass){
        //Überprüfen, ob die Instanz schon registriert ist
        if(!services.containsKey(serviceClass)){
            try{
                //wenn die Instanz nicht registriert ist
                T serviceInstance = serviceClass.getDeclaredConstructor().newInstance();
                //die neu erstellte Instanz wird registriert
                registerService(serviceClass, serviceInstance);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("couldnt create instance of: " + serviceClass.getName());
            }
        }
        //die registrierte Instanz zurückgeben
        return serviceClass.cast(services.get(serviceClass));
    }
}
