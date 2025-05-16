package respository;



import model.Movie;
import org.springframework.stereotype.Repository;
import utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieDAOImpl implements MovieDAO {

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        String sql = "{CALL get_all_movies()}";

        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement stmt = conn.prepareCall(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getLong("id"));
                movie.setTile(rs.getString("title"));
                movie.setDirector(rs.getString("director"));
                movie.setGenre(rs.getString("genre"));
                movie.setDescription(rs.getString("description"));
                movie.setDuration(rs.getInt("duration"));
                movie.setLanguage(rs.getString("language"));

                movies.add(movie);
            }
            ConnectionDB.closeConnection(conn,stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }
}
