package emg.cloud.bookshop.catalogservice.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String getGreetings() {
        return "Welcome to the Bookshop Catalog";
    }
}
