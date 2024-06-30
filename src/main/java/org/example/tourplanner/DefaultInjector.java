package org.example.tourplanner;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
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
                log.error("Couldn't create instance of: " + serviceClass.getName() + ", error: " + e);
                throw new RuntimeException("Couldn't create instance of: " + serviceClass.getName());
            }
        }
        //die registrierte Instanz zurückgeben
        return serviceClass.cast(services.get(serviceClass));
    }
}
