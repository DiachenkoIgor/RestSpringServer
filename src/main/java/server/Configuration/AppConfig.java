package server.Configuration;

        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.ComponentScan;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
        import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;

/**
 * Created by IgorPc on 11/20/2018.
 */
@Configuration
@ComponentScan(basePackages = "server")
public class AppConfig {

        @Bean
        public MappingJackson2HttpMessageConverter jacksonJSON(){
                return new MappingJackson2HttpMessageConverter();
        }

        @Bean
        public MappingJackson2XmlHttpMessageConverter jacksonXML(){
                return new MappingJackson2XmlHttpMessageConverter();
        }

}
