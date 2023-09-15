package webDesign.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@Configuration
@ComponentScan({"webDesign.controller","webDesign.config"})
@EnableWebMvc
public class SpringMvcConfig {
}
