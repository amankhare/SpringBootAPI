package springboot;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DaoFunctions {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional(readOnly=true)
	public List<DataSet> findUsers(String data,String domain) {
		List<DataSet> dogList = new ArrayList<>();
		jdbcTemplate.query("select * from centralrepo.data_set where domain='"+domain+"' AND type IN ('"+data.replace(",", "','")+"')",new ResultSetExtractor() {
            public List<DataSet> extractData(ResultSet rs) throws SQLException {
                while (rs.next()) {
                        String domain = rs.getString("domain");
                        String type = rs.getString("type");
                        boolean valid = rs.getBoolean("valid");
                        String value = rs.getString("value");
                        DataSet tempData=new DataSet();
                        tempData.setDomain(domain);
                        tempData.setType(type);
                        tempData.setValid(valid);
                        tempData.setValue(value);
                        dogList.add(tempData);
                }
                return dogList;
        }
		}
);
         return dogList;       

	}

}
