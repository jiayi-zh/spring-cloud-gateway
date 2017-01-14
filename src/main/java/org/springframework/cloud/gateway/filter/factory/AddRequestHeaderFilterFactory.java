package org.springframework.cloud.gateway.filter.factory;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * @author Spencer Gibb
 */
public class AddRequestHeaderFilterFactory implements FilterFactory {

	@Override
	public GatewayFilter apply(String header, String[] args) {
		validate(args, 1);

		//TODO: caching can happen here
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest().mutate()
					.header(header, args[0])
					.build();

			return chain.filter(exchange.mutate().request(request).build());
		};
	}
}
