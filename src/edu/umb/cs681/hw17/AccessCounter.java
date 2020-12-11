package edu.umb.cs681.hw17;

import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AccessCounter {
	private AccessCounter() {};
	//private static AccessCounter instance = null;
	private static AtomicReference<AccessCounter> instance = 
			new AtomicReference<AccessCounter>(null);
	private Map<java.nio.file.Path, AtomicInteger> map = new ConcurrentHashMap<java.nio.file.Path, AtomicInteger>();

	
	
	public static AccessCounter getInstance() {
		if (instance.get() == null)
			instance.set(new AccessCounter());
		return instance.get();
	}
	
	//increment
	public void increment(Path path) {
		map.putIfAbsent(path, new AtomicInteger(0));
		map.get(path).incrementAndGet();
	}
	
	//getCount
	public AtomicInteger getCount(Path path) {
		return map.computeIfAbsent(path, (Path k) -> {
			return new AtomicInteger(0);
		});
	}
	
	
	
	
	
}