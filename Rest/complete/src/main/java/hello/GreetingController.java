package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    private static String demoString = "Hello user";

//    @CrossOrigin(origins = "*")
//    @RequestMapping("/greeting")
//    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
//        return new Greeting(counter.incrementAndGet(),
//                            String.format(template, name));
//    }

    @CrossOrigin(origins = "*")
    @GetMapping("/greeting")
    public String getString() {
        return demoString;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/greeting")
    public void postString() {
        demoString = "Hello new user!";
    }
}
