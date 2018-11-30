package test;

import redis.clients.jedis.util.Sharded;
import redis.clients.jedis.JedisSentinelPool;

import java.util.List;

public class ShardedJedisSentinelPool extends Sharded<JedisSentinelPool, JedisSentinelPoolShardInfo>{
	public ShardedJedisSentinelPool(List<JedisSentinelPoolShardInfo> shards) {
		super(shards);
	}
}
