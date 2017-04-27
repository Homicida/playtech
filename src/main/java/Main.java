import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.base.MoreObjects;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * Created by Sander on 30.04.2016.
 */
public class Main{
    public static void main(String args[]) throws ExecutionException {
        LoadingCache<String, Data> dataCache = CacheBuilder.newBuilder()
            .maximumSize(50)
            .expireAfterWrite(30, TimeUnit.SECONDS)
            .build(new CacheLoader<String, Data>() {
                @Override
                public Data load(String dataID) throws Exception {
                    return getFromDatabase(dataID);
                }
            });

            System.out.println("Adding data to cache");
            System.out.println(dataCache.get("1"));
            System.out.println(dataCache.get("2"));
            System.out.println(dataCache.get("3"));

            System.out.println("Returning data from cache");
            System.out.println(dataCache.get("2"));
            System.out.println(dataCache.get("3"));
            System.out.println(dataCache.get("1"));
    }

    public static Data getFromDatabase(String dataID){
        Data s1 = new Data("1", "555");
        Data s2 = new Data("2", "cache");
        Data s3 = new Data("3", "test");

        Map<String, Data> database = new HashMap<String, Data>();
        database.put("1", s1);
        database.put("2", s2);
        database.put("3", s3);


        System.out.println("Database hit for "+dataID);
        return database.get(dataID);
    }
}

class Data<K, V>{
    private K key;
    private V value;

    public Data(K key, V value){
        synchronized (this) {
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public String toString(){
        return MoreObjects.toStringHelper(Data.class)
                .add("Key", key)
                .add("Value", value).toString();
    }
}