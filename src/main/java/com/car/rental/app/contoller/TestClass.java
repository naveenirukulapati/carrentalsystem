package com.car.rental.app.contoller;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.management.MBeanServer;
import java.lang.management.ManagementFactory;
import com.sun.management.HotSpotDiagnosticMXBean;

public class TestClass {

	public static void main(String args[]) {
		
		Set<String> hashSet = new HashSet<String>();
		
		hashSet.add("York");
		hashSet.add("Peter");
		hashSet.add("Honey");
		hashSet.add("Irukulapati");
		hashSet.add("Naveen");
		hashSet.add("Kumar");
		hashSet.add("Siri");
		hashSet.add("Vishnu");
		
		//for(int i=0;i<set1.size();i++) {
			System.out.println("Values from HashSet: " + hashSet);
		//}
		
		TreeSet<String> treeset = new TreeSet<String>();
		
		treeset.add("York");
		treeset.add("Peter");
		treeset.add("Honey");
		treeset.add("Irukulapati");
		treeset.add("Naveen");
		treeset.add("Kumar");
		treeset.add("Siri");
		treeset.add("Vishnu");
		
		System.out.println("Order det is; " + treeset);
		
		HashMap<String, String> hashMap = new HashMap<String, String>();
		
		hashMap.put("York", "g");
		hashMap.put("Peter", "f");
		hashMap.put("Honey", "c");
		hashMap.put("Irukulapati", "a");
		hashMap.put("Naveen", "e");
		hashMap.put("Kumar", "a");
		hashMap.put("Siri", "h");
		hashMap.put("Vishnu", "d");
		
		System.out.println(hashMap);
		
		TreeMap<String, String> treeMap = new TreeMap<String, String>();
		
		treeMap.put("York", "g");
		treeMap.put("Peter", "f");
		treeMap.put("Honey", "c");
		treeMap.put("Irukulapati", "a");
		treeMap.put("Naveen", "e");
		treeMap.put("Kumar", "a");
		treeMap.put("Siri", "h");
		treeMap.put("Vishnu", "d");
		
		System.out.println(treeMap);
		
		ExecutorService executor = Executors.newWorkStealingPool();

		List<Callable<String>> callables = Arrays.asList(
		        () -> "task1",
		        () -> "task2",
		        () -> "task3");
		
		try {
			List<Future<String>> futures = executor.invokeAll(callables);
			for(Future<String> future : futures) {
				System.out.println("Task name: " + future.get());
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void dumpHeap(String filePath, boolean live) throws IOException {
	    MBeanServer server = ManagementFactory.getPlatformMBeanServer();
	    HotSpotDiagnosticMXBean mxBean = ManagementFactory.newPlatformMXBeanProxy(
	      server, "com.sun.management:type=HotSpotDiagnostic", HotSpotDiagnosticMXBean.class);
	    mxBean.dumpHeap(filePath, live);
	}
}
