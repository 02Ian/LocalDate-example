package com.seveneleven.esl.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Configuration
public class OpenApiConfig {

    public static final String JWT = "JWT";
    public static final String AUTHORIZATION = "Authorization";

    public static final String API_KEY = "api_key";
    public static final String X_SUBSCRIPTION_KEY = "X-Subscription-Key";

    private static final String HTTPS = "https://";

    private static final String HTTP = "http://";

    private static final String CURRENT_SPEC_VERSION = "V1";

    @Value("${open-api-spec.host}")
    private String HOST;

    @Value("${open-api-spec.base-path}")
    private String BASEPATH;

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .components(getComponents())
                .info(getApiInfo())
                .servers(getServers())
                .security(getSecurity());
    }

    private List<SecurityRequirement> getSecurity() {
        List<SecurityRequirement> securityRequirements = new ArrayList<>();
        SecurityRequirement securityRequirement = new SecurityRequirement();
        securityRequirement.addList(API_KEY); // Mandatory
//        securityRequirement.addList(JWT);     // Optional : If the service is AD registered only then add the JWT security requirement
        securityRequirements.add(securityRequirement);
        return securityRequirements;
    }

    private List<Server> getServers() {
        List<Server> servers = new ArrayList<>();
        Server server = new Server();
        server.setUrl((HOST.contains("localhost") ? HTTP : HTTPS) + HOST + (BASEPATH == null ? "" : BASEPATH));
        servers.add(server);
        return servers;
    }

    private Info getApiInfo() {
        return new Info()
                .title("Store Users Data Service API") // mandatory
                .description("This is StoreUsers Data Service under Enterprise Service Library Initiatives by 7-Eleven") // mandatory
                .version(CURRENT_SPEC_VERSION);         //  mandatory
    }

    private Components getComponents() {
        Components components = new Components();
        // Optional : If the service is AD registered only then add the JWT security schema

//        components.addSecuritySchemes(JWT, new SecurityScheme()
//                .type(SecurityScheme.Type.APIKEY)
//                .name(AUTHORIZATION)
//                .in(SecurityScheme.In.HEADER));


        // Mandatory :
        // Add the API_KEY security schema for apigee subscription key
        components.addSecuritySchemes(API_KEY, new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .name(X_SUBSCRIPTION_KEY)
                .in(SecurityScheme.In.HEADER));
        return components;
    }

}

