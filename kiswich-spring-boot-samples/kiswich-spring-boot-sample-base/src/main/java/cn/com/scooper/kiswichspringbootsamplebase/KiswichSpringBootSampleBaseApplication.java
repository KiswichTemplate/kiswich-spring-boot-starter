package cn.com.scooper.kiswichspringbootsamplebase;

import cn.com.scooper.autoconfigure.config.ProjectConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ProjectConfig.class)
public class KiswichSpringBootSampleBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(KiswichSpringBootSampleBaseApplication.class, args);
    }


}
