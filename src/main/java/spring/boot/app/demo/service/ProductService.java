package spring.boot.app.demo.service;

import java.util.List;

public interface ProductService {
    List<String> getMostCommentedLimitedTo(int limit);
}
