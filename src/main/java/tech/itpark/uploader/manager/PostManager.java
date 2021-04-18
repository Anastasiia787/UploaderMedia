package tech.itpark.uploader.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import tech.itpark.uploader.domain.Post;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PostManager {
  // Spring, подставь мне готовый jdbcTemplate
  private final NamedParameterJdbcTemplate jdbcTemplate;
//  private final DataSource dataSource;

  public List<Post> getAll() {
    return jdbcTemplate.query(
        // language=SQL
        "SELECT id, content, media FROM posts",
        (rs, rowNum) -> new Post(
            rs.getLong("id"),
            rs.getString("content"),
            rs.getString("media")
        )
    );
  }

  public void save(Post post) {

    jdbcTemplate.update(

        "INSERT INTO posts(content, media) VALUES (:content, :media)", // named parameter - вместо ? подставляем псевдонимы :content

        Map.of(
            "content", post.getContent(),
            "media", post.getMedia()
        )
    );
  }

//  public List<Post> getAll() {
//    try (
//        Connection connection = dataSource.getConnection();
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("SELECT id, content, media...")
//    ) {
//      List<Post> result = new ArrayList<>();
//      while (resultSet.next()) {
//        result.add(new Post(
//            resultSet.getLong("id"),
//            resultSet.getString("content"),
//            resultSet.getString("media")
//        ));
//      }
//      return result;
//    } catch (SQLException e) {
//      throw new RuntimeException(e);
//    }
//  }
}
