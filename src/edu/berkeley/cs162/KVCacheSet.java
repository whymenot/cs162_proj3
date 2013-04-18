package edu.berkeley.cs162;

public class KVCacheSet{

	private int maxSize;
	private Map <String, KVCacheEntry> entries;
	private LinkedList <String> queue;
	private WriteLock lock;

	public KVCacheSet(int size){
		this.maxSize = size;
		this.entries = new HashMap<String, KVCacheEntry>();
		this.queue = new LinkedList<String>();
		this.lock = new WriteLock();
	}



	private boolean put (String key, String value){
		if entries.contains(key){
			if (entries.get(key).getValue() == value){
				return false;//may need to update ref bit here?
			}
			else{
				KVCacheEntry curr = entries.get(toRemove);
				curr.setData(value);
				return true;
			}
		}
		else if(this.maxSize == entries.size()){//the set is full
			//evict a key based on second-chance replacement policy
			String toRemove = queue.removeFirst();
			while(entries.get(toRemove).getRefBit() == true){
				queue.add(toRemove);
				entries.get(toRemove).setRefBit(false);
				toRemove = queue.removeFirst();
			}
			entries.remove(toRemove);
			entries.put(key, new KVCacheEntry(value));
			return true;//not sure if eviction counts as override
		}
		else{
			entries.put(key, new KVCacheEntry(value));
			return false;
		}
	}

    public String get(String key){
			if(entries.containsKey(key)){
				CacheEntry entry = entries.get(key);
				entry.setRefBit(true);
				return entry.getData();
			}
			else{
				return null;
			}
	}

	public void del(String key){
		entries.remove(key);
		queue.remove(key);
	}
	
	public WriteLock getWriteLock(){
		return this.lock;
	}
}