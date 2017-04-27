
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import org.junit.*;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by Sander on 1.05.2016.
 */
public class MainTest {
    Main main = new Main();
    @Test
    public void testContainsKeyAndValue() throws ExecutionException {
        LoadingCache<String, Data> testCache = CacheBuilder.newBuilder()
                .maximumSize(50)
                .expireAfterWrite(30, TimeUnit.SECONDS)
                .build(new CacheLoader<String, Data>() {
                    @Override
                    public Data load(String dataID) throws Exception {
                        return main.getFromDatabase(dataID);
                    }
                });
        String key = "3";

        Data testData = new Data(key, "test");

        System.out.println(testCache.get("3"));
        Data test2 = testCache.get(key);
        assertEquals(testData.toString(), test2.toString());

    }
}