package test;

import redis.clients.jedis.util.ShardInfo;
import redis.clients.jedis.JedisSentinelPool;
import java.util.Set;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class JedisSentinelPoolShardInfo extends ShardInfo<JedisSentinelPool>{
	private String masterName;
	private Set<String> sentinels;
	
	public JedisSentinelPoolShardInfo(String masterName, Set<String> sentinels) {
		this.masterName = masterName;
		this.sentinels = sentinels;
	}

	@Override
	protected JedisSentinelPool createResource() {
		return new JedisSentinelPool(this.masterName, this.sentinels, new GenericObjectPoolConfig());
	}

	@Override
	public String getName() {
		return "shard-" + this.masterName;
	}

}
