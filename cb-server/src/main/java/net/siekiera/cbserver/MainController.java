package net.siekiera.cbserver;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/a")
    ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/b")
    ResponseEntity<String> delayed() throws InterruptedException {
        Thread.sleep(4000);
        return ResponseEntity.ok("Hello delayed");
    }

    @GetMapping("/c")
    ResponseEntity<String> error() {
        return ResponseEntity.internalServerError().build();
    }
}
