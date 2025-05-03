package next.support.context;

import java.sql.ResultSet;

public interface RowMapper {
    Object mapRow(ResultSet rs);
}
