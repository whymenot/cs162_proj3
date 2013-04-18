package edu.berkeley.cs162;

import static org.junit.Assert.*;

import org.junit.Test;

public class KVCache_test {

	@Test
	public void KVCacheTest() {
		KVCache cache = new KVCache(5,5);
		cache.put("k1", "v1");
		assertTrue(cache.put("k1", "v2"));
		assertTrue(cache.get("k1")=="v2");
		String s = "k1";
		String[] strs ={"a","k","a2","f","k2","f2","q","q2","e","m","h","i"};
		
		for(int i = 0; i<strs.length; i++){
			int num = Math.abs(strs[i].hashCode()) % 5;
			if(i==3){
				assertTrue(cache.get("a")=="a");
			}
			cache.put(strs[i], strs[i]);
			System.out.println(num);
		}
		assertTrue(cache.get("k")==null);
		for(int i = 0; i<strs.length; i++){
			if(strs[i]!="k"){
				assertTrue(cache.get(strs[i])==strs[i]);
				cache.del(strs[i]);
			}
			
		}
		for(int i = 0; i<strs.length; i++){
			assertTrue(cache.get(strs[i])==null);
		}
		
		//Test for XML

	}

}
