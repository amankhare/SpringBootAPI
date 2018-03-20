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
public class DaoApiFunctions {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional(readOnly=true)
	public List<ApiSet> findSet(String domain,String api_name) {
		{
			String appender="";
			String query="";
			List<ApiSet> outputList = new ArrayList<>();
			if(!api_name.equals(""))
			{	
				for(int i=0;i<api_name.split(",").length;i++)
					appender+="?,";
				query="select * from centralrepo.api_set where DOMAIN=? AND API_NAME IN (".concat(appender.substring(0, appender.length()-1).concat(")"));
			}
			else
				query="select * from centralrepo.api_set where DOMAIN=?";
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs;
			try {
				con =  jdbcTemplate.getDataSource().getConnection();
				System.out.println(query);
				ps =  con.prepareStatement(query);
				ps.setString(1, domain);
				int count=2;
				if(!api_name.equals(""))
				{	
					for(String params:api_name.split(","))
					{
						System.out.println("reached........");
						ps.setString(count++, params);
					}
				}
				System.out.println(ps.toString());
				rs=ps.executeQuery();
				while (rs.next()) {
					domain = rs.getString("DOMAIN");
					long api_index = rs.getLong("API_INDEX");
					String api_info = rs.getString("API_INFO");
					api_name = rs.getString("API_NAME");
					ApiSet tempData=new ApiSet();
					tempData.setAPI_INDEX(api_index);
					tempData.setAPI_INFO(api_info);
					tempData.setAPI_NAME(api_name);
					tempData.setDOMAIN(domain);
					outputList.add(tempData);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return outputList;
		}

	}
}