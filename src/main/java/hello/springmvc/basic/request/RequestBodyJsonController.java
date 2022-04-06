package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request,
                                  HttpServletResponse response) throws IOException {

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}", messageBody);

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());

        response.getWriter().write("requestBodyJsonV1 ok");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {

        log.info("messageBody = {}", messageBody);

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());

        return "requestBodyJsonV2 ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException {

        log.info("helloData = {}", helloData);
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());

        return "requestBodyJsonV3 ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) throws IOException {

        HelloData helloData = httpEntity.getBody();

        log.info("helloData = {}", helloData);
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());

        return "requestBodyJsonV4 ok";
    }

    /**
     * @RequestBody 요청
     *  JSON 요청 -> HTTP 메시지 컨버터 -> 객체
     * @ResponseBody 응답
     *  객체 -> HTTP 메시지 컨버터 -> JSON 응답
     */
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData) throws IOException {

        log.info("helloData = {}", helloData);
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());

        return helloData;
    }
}
