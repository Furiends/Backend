package furiends.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    private CorsConfiguration buildConfig(){
        CorsConfiguration configuration = new CorsConfiguration();
        //设置请求头，*代表所有
        configuration.addAllowedHeader("*");
        //设置请求方式，这里是允许所有
        configuration.addAllowedMethod("*");
        //设置请求地址，允许所有
        configuration.addAllowedOriginPattern("*");
        //设置跨域请求的时候是否使用同一个session
        configuration.setAllowCredentials(true);
        return configuration;
    }

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",buildConfig());
        return new CorsFilter(source);
    }
}



