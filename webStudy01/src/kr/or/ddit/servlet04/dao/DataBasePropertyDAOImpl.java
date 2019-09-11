package kr.or.ddit.servlet04.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyDAOImpl implements IDataBasePropertyDAO {

   @Override
   public List<DataBasePropertyVO> selectDataBasePropertyList(Map<String, Object> dataMap) {

      Connection conn = null;
      Statement stmt = null;
      ResultSet rs = null;
      try {
         conn = ConnectionFactory.getConnection();
         stmt = conn.createStatement();
         StringBuffer sql = new StringBuffer();
         sql.append(" select PROPERTY_NAME,PROPERTY_VALUE ,DESCRIPTION");
         sql.append(" from database_properties");

         rs = stmt.executeQuery(sql.toString());
         ResultSetMetaData rsmd = rs.getMetaData();
         List<DataBasePropertyVO> list = new ArrayList<>();
         dataMap.put("list", list);
         int colcnt = rsmd.getColumnCount();
         String[] headers = new String[colcnt];
         for (int idx = 1; idx <= colcnt; idx++) {
            headers[idx - 1] = rsmd.getColumnName(idx);
         }
         dataMap.put("headers", headers);

         while (rs.next()) {
            DataBasePropertyVO vo = new DataBasePropertyVO();
            vo.setProperty_name(rs.getString(1));
            vo.setProperty_value(rs.getString("PROPERTY_VALUE"));
            vo.setDescription(rs.getString("DESCRIPTION"));
            list.add(vo);
         }
         return list;
      } catch (SQLException e) {
         throw new RuntimeException(e);
      } finally {
         try {
            if (rs != null)
               rs.close();
            if (stmt != null)
               stmt.close();
            if (conn != null)
               conn.close();
         } catch (SQLException e) {
            throw new RuntimeException(e);

         }
      }
   }

}