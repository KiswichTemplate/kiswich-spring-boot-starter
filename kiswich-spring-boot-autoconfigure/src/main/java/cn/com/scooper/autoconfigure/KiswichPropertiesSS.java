package cn.com.scooper.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

//@ConfigurationProperties(prefix = KiswichProperties.PREFIX)
@Data
public class KiswichPropertiesSS {
    public static final String PREFIX = "scooper.default";
    private Cache cache = new Cache();
    private Retrofit retrofit = new Retrofit();
    private Executor executor = new Executor();
    private Swagger swagger = new Swagger();


    @Data
    public static class Cache{
        private boolean enable = true;
        private String spec = "maximumSize=500,expireAfterAccess=600s";
    }

    @Data
    public static class Retrofit{
        private boolean enable = true;
        private String dateFormat = "yyyy-MM-dd HH:mm:ss";
    }

    @Data
    public static class Executor{
        private boolean enable = true;
        private Integer corePoolSize = 50;
        private Integer maxPoolSize = 100;
        private Integer keepAliveSeconds = 60;
    }

    @Data
    public static class Swagger{
        private boolean enable = true;
    }


}
