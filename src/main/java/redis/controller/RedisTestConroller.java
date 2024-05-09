package redis.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/redisTest")
public class RedisTestConroller {

    //装配
    @Resource
    RedisTemplate redisTemplate;

    //演示设置数据和获取数据
    @GetMapping("/t1")
    @ResponseBody
    public String t1() {
        redisTemplate.opsForValue().set("book", "天龙八部");
        String book = (String) redisTemplate.opsForValue().get("book");
        return book;
    }

    //编写方法演示操作list
    @GetMapping("t2")
    @ResponseBody
    public String t2() {
        //list
        redisTemplate.opsForList().leftPush("books", "笑傲江湖");
        redisTemplate.opsForList().leftPush("books", "hiJava");
        List books = redisTemplate.opsForList().range("books", 0, -1);
        return books.toString();
    }

    @GetMapping("t3")
    @ResponseBody
    public String t3() {
        //list
        return redisTemplate.keys("*").toString();
    }
}
