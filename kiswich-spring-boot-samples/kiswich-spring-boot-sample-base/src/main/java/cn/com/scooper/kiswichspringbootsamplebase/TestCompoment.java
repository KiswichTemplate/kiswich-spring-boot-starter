package cn.com.scooper.kiswichspringbootsamplebase;

import cn.com.scooper.autoconfigure.config.PlatProperties;
import cn.com.scooper.autoconfigure.config.ProjectConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestCompoment implements CommandLineRunner {

    private final ProjectConfig projectConfig;
    private final PlatProperties platProperties;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(projectConfig.name);
    }
}
