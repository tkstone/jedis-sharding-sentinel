package test;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisSentinelPool;

public class ShardedJedisSentinelPoolTest {

	public static void main(String[] args) {
		// To set sentinel infos
		Set<String> sentinels = new HashSet<>();
		HostAndPort sentinel1 = new HostAndPort("127.0.0.1", 5000);
		HostAndPort sentinel2 = new HostAndPort("127.0.0.1", 5001);
		HostAndPort sentinel3 = new HostAndPort("127.0.0.1", 5002);
		sentinels.add(sentinel1.toString());
		sentinels.add(sentinel2.toString());
		sentinels.add(sentinel3.toString());
		// To build ShardInfo
		List<JedisSentinelPoolShardInfo> shards = new ArrayList<>();		
		shards.add(new JedisSentinelPoolShardInfo("M1", sentinels));
		shards.add(new JedisSentinelPoolShardInfo("M2", sentinels));
		shards.add(new JedisSentinelPoolShardInfo("M3", sentinels));
		// To build ShardedJdedisSentinelPool
		ShardedJedisSentinelPool client = new ShardedJedisSentinelPool(shards);
		// To set cache data
		String key = "key1";
		String value = "value1";
		JedisSentinelPool jedisSentinelPool = client.getShard(key);
		jedisSentinelPool.getResource().set(key, value);
	}

}
