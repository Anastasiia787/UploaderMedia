package tech.itpark.uploader.manager;

import org.apache.tika.Tika;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import tech.itpark.uploader.domain.Media;
import tech.itpark.uploader.exception.MediaUploadException;
import tech.itpark.uploader.exception.UnsupportedContentTypeException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.UUID;

@Component
public class MediaManager {
  private final Path mediaPath = Path.of("./static");
  private final Tika tika = new Tika();
  private final Map<String, String> extensions = Map.of(
          "image/jpeg", ".jpg",
          "image/png", ".png",
          "audio", "mp3"
  );

  public MediaManager() {
    try {
      Files.createDirectories(mediaPath);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Media save(MultipartFile file) {
    try {
      String name = UUID.randomUUID().toString();
      String contentType = tika.detect(file.getInputStream());
      String extension = extensions.get(contentType);
      if (extension == null) {
        throw new UnsupportedContentTypeException();
      }

      String fullname = name + extension;
      file.transferTo(mediaPath.resolve(fullname));
      return new Media(fullname);
    } catch (IOException e) {
      throw new MediaUploadException(e);
    }
  }
}
