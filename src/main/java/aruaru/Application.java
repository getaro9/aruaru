package aruaru;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        String springProfilesActive = System.getProperty("spring.profiles.active");
        if (!StringUtils.equals(springProfilesActive, "product")
                && !StringUtils.equals(springProfilesActive, "develop")) {
            throw new UnsupportedOperationException(MessageFormat.format("JVMの起動時引数 -Dspring.profiles.active で develop か product を指定して下さい ( -Dspring.profiles.active={0} )。", springProfilesActive));
        }

        SpringApplication.run(Application.class, args);
    }

}
