package BloggingPlatformApi.Controller;

import BloggingPlatformApi.Model.Blog;
import BloggingPlatformApi.Repository.BlogRepository;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;


@RestController
@RequestMapping("posts")
public class BlogController {

   private BlogRepository blogRepository;


   public BlogController(BlogRepository blogRepository) {
      this.blogRepository = blogRepository;
   }

   @GetMapping
   public ResponseEntity<?> getAllBlogs(){
      return ResponseEntity.ok(this.blogRepository.findAll());
   }

   @PostMapping
   public ResponseEntity<?> postBlog(@RequestBody Blog blog){
      blog.setCreateAt(LocalDateTime.now());
      Blog savedBlog = blogRepository.save(blog);

      URI location = ServletUriComponentsBuilder
              .fromCurrentRequest()
              .path("/{id}")
              .buildAndExpand(savedBlog.getId())
              .toUri();

      return ResponseEntity.created(location).body(savedBlog);
   }

   @GetMapping("/{id}")
   public ResponseEntity<?> getPostByID(@PathVariable Long id){
      return blogRepository.findById(id)
              .map(blog -> ResponseEntity.ok(blog))
              .orElseGet((() -> ResponseEntity.notFound().build()));
   }

   @PutMapping
   public ResponseEntity<?> putPostbyID(@RequestBody Blog blogDetails){
      return blogRepository.findById(blogDetails.getId())
              .map(blog -> {
                 blog.setTitle(blogDetails.getTitle());
                 blog.setContent(blogDetails.getContent());
                 blog.setCategory(blogDetails.getCategory());
                 blog.setTags(blogDetails.getTags());
                 blog.setUpdatedAt(LocalDateTime.now());
                 Blog updatedBlog = blogRepository.save(blog);
                 return ResponseEntity.ok(updatedBlog);
              }).orElseGet(() -> ResponseEntity.notFound().build());
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<?> deletePostByID(@PathVariable Long id){
      return blogRepository.findById(id)
              .map(blog -> {
                 blogRepository.delete(blog);
                 return ResponseEntity.noContent().build();
              }).orElseGet(() -> ResponseEntity.notFound().build());
   }

}
