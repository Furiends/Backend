package furiends.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

//@Configuration
////public class CorsConfig {
////
////    // 当前跨域请求最大有效时长。这里默认1天
////    private static final long MAX_AGE = 24 * 60 * 60;
////
////    @Bean
////    public CorsFilter corsFilter() {
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        CorsConfiguration corsConfiguration = new CorsConfiguration();
////        corsConfiguration.addAllowedOrigin("*"); // 1 设置访问源地址
////        corsConfiguration.addAllowedHeader("*"); // 2 设置访问源请求头
////        corsConfiguration.addAllowedMethod("*"); // 3 设置访问源请求方法
////        corsConfiguration.setMaxAge(MAX_AGE);
////        source.registerCorsConfiguration("/**", corsConfiguration); // 4 对接口配置跨域设置
////        return new CorsFilter(source);
////    }
////}
@Configuration
public class CorsConfig {

    private CorsConfiguration buildconfig(){
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
        source.registerCorsConfiguration("/**",buildconfig());
        return new CorsFilter(source);
    }
}



