package panacotari.demo.repositoy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import panacotari.demo.entity.Action;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RepositoryAction {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void insertInDataBase(List<Action> listWithActions) {
        String sql ="INSERT INTO ACTION (Name,Date,Open,High,Low,Close,Volume) VALUES (?,?,?,?,?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                Action action = listWithActions.get(i);
                preparedStatement.setString(1,action.getName());
                preparedStatement.setString(2,action.getDate());
                preparedStatement.setString(3,action.getOpen());
                preparedStatement.setString(4,action.getClose());
                preparedStatement.setString(5,action.getHigh());
                preparedStatement.setString(6,action.getLow());
                preparedStatement.setString(7,action.getVolume());
            }


            @Override
            public int getBatchSize() {
                return listWithActions.size();
            }
        });
    }
}
