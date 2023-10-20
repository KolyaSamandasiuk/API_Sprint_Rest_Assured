package api.clients;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {
    Config config = readConf();

    static Config readConf() {
        return ConfigFactory.load("application.conf");
    }

    String KEY = readConf().getString("key");
    String TOKEN = readConf().getString("token");
    String INVALID_TOKEN = readConf().getString("invalidToken");
}