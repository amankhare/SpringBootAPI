package springboot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class DaoDataFunctions {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional(readOnly=true)
	public List<DataSet> findUsers(String data,String domain) {
		String appender="";
		for(int i=0;i<data.split(",").length;i++)
			appender+="?,";
		List<DataSet> outputList = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs;
		String query="select * from centralrepo.data_set where domain=? AND type IN (".concat(appender.substring(0, appender.length()-1).concat(")"));
		//StringBuilder query = new StringBuilder();
		//query.append("select * from centralrepo.data_set where domain=? AND type IN (").append(generateCSV("?",
		//data.split(",").length)).append(")");
		try {
			con =  jdbcTemplate.getDataSource().getConnection();
			ps =  con.prepareStatement(query);
			System.out.println(data);
			ps.setString(1, domain);
			int count=2;
			for(String params:data.split(","))
			{
				ps.setString(count++, params);
			}
			System.out.println(ps.toString());
			rs=ps.executeQuery();
			while (rs.next()) {
                domain = rs.getString("domain");
                String type = rs.getString("type");
                boolean valid = rs.getBoolean("valid");
                String value = rs.getString("value");
                DataSet tempData=new DataSet();
                tempData.setDomain(domain);
                tempData.setType(type);
                tempData.setValid(valid);
                tempData.setValue(value);
                outputList.add(tempData);
        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*jdbcTemplate.query(ps.toString(),new ResultSetExtractor() {
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
                        outputList.add(tempData);
                }
                return outputList;
        }
		}
);*/
         return outputList;       

	}
	
	
	@Transactional(readOnly=true)
	public int updateData(DataSet data) {
		Connection con = null;
		PreparedStatement ps = null;
		int aa=0;
		String query="update centralrepo.data_set set domain=?,Type=?,Valid=?,Value=? where id=? ";
		try {
			con =  jdbcTemplate.getDataSource().getConnection();
			ps =  con.prepareStatement(query.toString());
			ps.setString(1, data.getDomain());
     		ps.setString(2,data.getType());
     		ps.setBoolean(3,data.isValid());
     		ps.setString(4,data.getValue());
     		ps.setLong(5, data.getId());
			aa=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aa;

}
}