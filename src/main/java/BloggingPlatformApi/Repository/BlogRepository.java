package BloggingPlatformApi.Repository;

import BloggingPlatformApi.Model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
