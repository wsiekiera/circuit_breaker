package net.siekiera.cbclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @Autowired
    BookService bookService;
    @GetMapping("/")
    public String sample(){
        return bookService.slow();
    }

    @GetMapping("/slow")
    public String sample2() throws InterruptedException {
        Thread.sleep(1500);
        return "slow.";
    }


}
