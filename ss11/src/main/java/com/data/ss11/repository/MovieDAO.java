package com.data.ss11.repository;
import com.data.ss11.model.bt10.Movie;
import com.data.ss11.util.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieDAO {

    public List<Movie> findAll() {
        List<Movie> list = new ArrayList<>();
        String sql = "{call get_all_movies()}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                Movie m = new Movie();
                m.setId(rs.getInt("id"));
                m.setTitle(rs.getString("title"));
                m.setDirector(rs.getString("director"));
                m.setReleaseDate(rs.getDate("release_date"));
                m.setGenre(rs.getString("genre"));
                m.setPoster(rs.getString("poster"));
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Movie findById(int id) {
        String sql = "{call find_movie_by_id(?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    Movie m = new Movie();
                    m.setId(rs.getInt("id"));
                    m.setTitle(rs.getString("title"));
                    m.setDirector(rs.getString("director"));
                    m.setReleaseDate(rs.getDate("release_date"));
                    m.setGenre(rs.getString("genre"));
                    m.setPoster(rs.getString("poster"));
                    return m;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insert(Movie movie) {
        String sql = "{call insert_movie(?, ?, ?, ?, ?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, movie.getTitle());
            cs.setString(2, movie.getDirector());
            cs.setDate(3, new java.sql.Date(movie.getReleaseDate().getTime()));
            cs.setString(4, movie.getGenre());
            cs.setString(5, movie.getPoster());
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Movie movie) {
        String sql = "{call update_movie(?, ?, ?, ?, ?, ?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, movie.getId());
            cs.setString(2, movie.getTitle());
            cs.setString(3, movie.getDirector());
            cs.setDate(4, new java.sql.Date(movie.getReleaseDate().getTime()));
            cs.setString(5, movie.getGenre());
            cs.setString(6, movie.getPoster());
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "{call delete_movie(?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}