package zw.co.bancabc.commonutils.config;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.netflix.ribbon.StaticServerList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class LocalRibbonClientConfiguration {

    @Bean
    public ServerList<Server> ribbonServerList() {
        StaticServerList<Server> staticServerList = new StaticServerList<>((new Server("localhost", 8202))
                , new Server("localhost", 9084),
                new Server("localhost", 9085),
                new Server("localhost", 9086),
                new Server("localhost", 9087),
                new Server("localhost",9088));
        return staticServerList;
    }
}