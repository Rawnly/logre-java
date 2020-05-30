import enums.Severity;
import kong.unirest.Config;
import kong.unirest.HttpRequestWithBody;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.Getter;
import lombok.Setter;
import models.LogError;
import models.LogPayload;
import models.ProxyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class LogreClient {
    private static final String Endpoint = "https://logre.io/api/boxes/{boxId}/logs";

    @Getter
    private final String boxId;

    @Getter
    private final String authToken;

    @Setter
    @Getter
    private ProxyConfig proxy;

    private final Class<?> aClass;
    private final Logger log;

    public LogreClient(String boxId, String authToken, Class<?> aClass) {
        this.boxId = boxId;
        this.authToken = authToken;
        this.aClass = aClass;
        this.log = LoggerFactory.getLogger(aClass);
        this.proxy = null;
    }


    public LogreClient(String boxId, Class<?> aClass) {
        this.boxId = boxId;
        this.aClass = aClass;
        this.log = LoggerFactory.getLogger(aClass);
        this.authToken = null;
        this.proxy = null;
    }

    private HttpRequestWithBody prepareRequest() {
        Config config = Unirest.config();

        Optional.ofNullable(authToken).ifPresent(token -> {
            config.addDefaultHeader("Authorization", token);
        });

        Optional.ofNullable(proxy).ifPresent(p -> {
            if (p.getUsername() == null || p.getPassword() == null)  {
                config.proxy(p.getHost(), p.getPort());
            } else {
                config.proxy(p.getHost(), p.getPort(), p.getUsername(), p.getPassword());
            }
        });

        return Unirest
                .post(Endpoint)
                .routeParam("boxId", boxId)
                .header("Content-Type", "application/json");
    }


    private HttpResponse Log(String message, String severity) {
        String msg = String.format("%s.class - %s", aClass.getName(), message);

        return prepareRequest()
                .body(new LogPayload(msg, severity))
                .asEmpty()
                .ifFailure(LogError.class, httpResponse -> {
                    HttpResponse<LogError> response = (HttpResponse<LogError>) httpResponse;
                    LogError error = response.getBody();

                    log.error("{}", error);
                });
    }

    public HttpResponse log(String message, Severity severity) {
        return Log(message, severity.value);
    }
    public HttpResponse message(String message) {
        return prepareRequest()
                .body(message)
                .asEmpty();
    }

    public HttpResponse info(String message) {
        log.info(message);
        return log(message, Severity.INFO);
    }

    public HttpResponse debug(String message) {
        log.debug(message);
        return log(message, Severity.DEBUG);
    }

    public HttpResponse warn(String message) {
        log.warn(message);
        return log(message, Severity.WARNING);
    }

    public HttpResponse error(String message) {
        log.error(message);
        return log(message, Severity.ERROR);
    }

    public HttpResponse fatal(String message) {
        log.trace(message);
        return log(message, Severity.FATAL);
    }
}