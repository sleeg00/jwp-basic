package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import next.model.User;
import next.support.context.PreparedStatementSetter;
import next.support.context.RowMapper;

public class UserDao {
    public void insert(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getUserId());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getEmail());
            }
        };
        String sql = "sdaasd";
        jdbcTemplate.update(sql, pss);
    }


    public void update(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        PreparedStatementSetter pass = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getPassword());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getEmail());
                pstmt.setString(4, user.getUserId()); // WHERE 절의 기준
            }
        };
        RowMapper rowMapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs) {
                return null;
            }
        };


    }


    public List<User> findAll() throws SQLException {
        JdbcTemplate jdbcTemplate = new jdbcTemplate() {
            @Override
            public Object mapRow(ResultSet rs) throws SQLException {
                User user = new User(rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"));
                return user;
            }

            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                return;
            }
        };
        String sql = "SELECT userId, password, name, email FROM USERS";
        return (List<User>) jdbcTemplate.query(sql);

    }


    public User findByUserId(String userId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public Object mapRow(ResultSet rs) throws SQLException {
                User user = new User(rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"));
                return user;
            }

            @Override
            public String createQuery() {
                return null;
            }

            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, userId);
            }
        };
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
        return (User) jdbcTemplate.query(sql);
    }
}
