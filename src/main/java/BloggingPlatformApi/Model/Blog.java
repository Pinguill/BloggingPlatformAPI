package BloggingPlatformApi.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Blog {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String title;
   private String content;
   private String category;

   @ElementCollection
   private List<String> tags;

   private LocalDateTime createAt;
   private LocalDateTime updatedAt;

   public Blog() {
   }

   public Blog(Long id, String title, String content, String category, List<String> tags, LocalDateTime createAt, LocalDateTime updatedAt) {
      this.id = id;
      this.title = title;
      this.content = content;
      this.category = category;
      this.tags = tags;
      this.createAt = createAt;
      this.updatedAt = updatedAt;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public String getCategory() {
      return category;
   }

   public void setCategory(String category) {
      this.category = category;
   }

   public List<String> getTags() {
      return tags;
   }

   public void setTags(List<String> tags) {
      this.tags = tags;
   }

   public LocalDateTime getCreateAt() {
      return createAt;
   }

   public void setCreateAt(LocalDateTime createAt) {
      this.createAt = createAt;
   }

   public LocalDateTime getUpdatedAt() {
      return updatedAt;
   }

   public void setUpdatedAt(LocalDateTime updatedAt) {
      this.updatedAt = updatedAt;
   }
}
