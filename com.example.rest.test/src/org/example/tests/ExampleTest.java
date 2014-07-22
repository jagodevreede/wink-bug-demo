package org.example.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

public class ExampleTest extends TestCase {

    private final BundleContext context = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
    private final Set<String> notStoppedBundles = new HashSet<>();
    
    public ExampleTest() {
        notStoppedBundles.add("junit.osgi");
        notStoppedBundles.add("org.apache.felix.framework");
        notStoppedBundles.add("com.example.rest.test");
        notStoppedBundles.add("org.apache.felix.gogo.command");
        notStoppedBundles.add("org.apache.felix.gogo.runtime");
        notStoppedBundles.add("org.apache.felix.gogo.shell");
        notStoppedBundles.add("");
        notStoppedBundles.add("");
    }

    public void testExample() throws Exception {
    	for (int i = 0; i <= 100; i++) {
    	    System.out.println("Start run #" + i);
    	    TimeUnit.SECONDS.sleep(5);
    		testUrl("example1");
    		testUrl("example2");
    		testUrl("example3");
    		testUrl("example4");
    		testUrl("example5");
    		testUrl("example6");
    		testUrl("example7");
    		testUrl("example8");
    		testUrl("example9");
    		System.out.println("Stopping bundles");
    		Bundle[] bundles = context.getBundles();
    		List<Bundle> randomBundles = new ArrayList<>(Arrays.asList(bundles));
            Collections.shuffle(randomBundles, new Random(i));
            for (Bundle b : bundles) {
    		    if (!notStoppedBundles.contains(b.getSymbolicName())) {
    		        b.stop();
    		    }
    		}
            Collections.shuffle(randomBundles, new Random(i));
            TimeUnit.SECONDS.sleep(2);
    	    System.out.println("Starting bundles");
    	    for (Bundle b : randomBundles) {
    	        System.out.println(b.getSymbolicName());
    	        b.start();
            }
    	}
    }
    
    private void testUrl(String url) throws IOException {
    	HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet("http://localhost:8080/rest/" + url);
        HttpResponse response = httpclient.execute(httpget);
        int statusCode = response.getStatusLine().getStatusCode();
        assertEquals("url status code: " + url, 200, statusCode);
    }
}
