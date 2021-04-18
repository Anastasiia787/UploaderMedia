package tech.itpark.uploader.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tech.itpark.uploader.domain.Media;
import tech.itpark.uploader.manager.MediaManager;

@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
public class MediaController {
  private final MediaManager manager;

  // TODO: https://pastebin.com/7GAzCbqK
  // Multipart works with POST
  // имя параметра (file) должно совпадать с именем в requests.http (там тоже name=file)
  @PostMapping
  public Media save(@RequestParam MultipartFile file) { // пришлёт multipart file
    return manager.save(file);
  }
}
