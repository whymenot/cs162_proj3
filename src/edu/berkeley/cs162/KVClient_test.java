package edu.berkeley.cs162;

import static org.junit.Assert.*;
import org.junit.Test;

public final class KVClient_test {
	@Test
	public void constructor() {
		String server = "tempServer";
		int port = 2222;
		
		KVClient kvc = new KVClient(server,port);
		assertTrue(kvc.getServer().equals(server)
				&& kvc.getPort()==port);
	}
	
}