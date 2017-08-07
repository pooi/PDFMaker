

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class PDFMaker {

	private static final String GET_TOP_ARTICLE_WITH_DAY
	= "select * from articleTbl WHERE day = ? order by heart DESC limit 0, 30;";
	private static final String GET_TOP_COLUMN_WITH_DAY
	= "select * from columnTbl WHERE day = ? order by heart DESC limit 0, 30;";
	private static final String GET_TOP_PHOTO_WITH_DAY
	= "select * from photoDetailTbl WHERE day = ? order by heart DESC limit 0, 30;";

	public static void main(String[] args) {
		
		String day = "0";
		
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
//		list.addAll(getArticle(day));
		list.addAll(getColumn(day));
//		list.addAll(getPhoto(day));
		
		Collections.shuffle(list);
		
		GeneratePDF g = new GeneratePDF(list, "Day_magazine_" + day + ".pdf");
		g.start();
		
		try{
			g.join();
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	public static ArrayList<HashMap<String, Object>> getArticle(String day){
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		PreparedStatement pstmt = null;
        Connection connect = null;
        try {
        	
            connect = DbConnectionPools.getPoolConnection();
            pstmt = connect.prepareStatement(GET_TOP_ARTICLE_WITH_DAY);
            pstmt.setString(1, day);
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	HashMap<String, Object> map = new HashMap<>();
            	map.put("id", rs.getString("id"));
            	map.put("userId", rs.getString("userId"));
            	map.put("layout", rs.getInt("layout"));
            	String picture = rs.getString("picture");
            	ArrayList<String> picList = new ArrayList<>();
            	for(String pic : picture.split(",")){
            		if("".equals(pic)){
            			continue;
            		}
            		picList.add(pic);
            	}
            	map.put("picture", picList);
            	map.put("title", rs.getString("title"));
            	map.put("content", rs.getString("content"));
            	String url = rs.getString("url");
            	ArrayList<String> urlList = new ArrayList<>();
            	for(String u : url.split(",")){
            		if("".equals(u)){
            			continue;
            		}
            		urlList.add(u);
            	}
            	map.put("url", urlList);
            	map.put("day", rs.getString("day"));
            	map.put("date", rs.getString("date"));
            	map.put("time", rs.getString("time"));
            	map.put("hit", rs.getInt("hit"));
            	map.put("heart", rs.getInt("heart"));
            	list.add(map);
            }
            
        } catch (Exception e) {
            System.out.println("ERROR!!!!" + e.toString());
        } finally {
            DbConnectionPools.closeResources(connect, pstmt);
        }
        return list;
	}
	
	public static ArrayList<HashMap<String, Object>> getColumn(String day){
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		PreparedStatement pstmt = null;
        Connection connect = null;
        try {
        	
            connect = DbConnectionPools.getPoolConnection();
            pstmt = connect.prepareStatement(GET_TOP_COLUMN_WITH_DAY);
            pstmt.setString(1, day);
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	HashMap<String, Object> map = new HashMap<>();
            	map.put("id", rs.getString("id"));
            	map.put("userId", rs.getString("userId"));
            	map.put("layout", rs.getInt("layout"));
            	String picture = rs.getString("picture");
            	ArrayList<String> picList = new ArrayList<>();
            	for(String pic : picture.split(",")){
            		if("".equals(pic)){
            			continue;
            		}
            		picList.add(pic);
            	}
            	map.put("picture", picList);
            	map.put("title", rs.getString("title"));
            	map.put("content", rs.getString("content"));
            	String url = rs.getString("url");
            	ArrayList<String> urlList = new ArrayList<>();
            	for(String u : url.split(",")){
            		if("".equals(u)){
            			continue;
            		}
            		urlList.add(u);
            	}
            	map.put("url", urlList);
            	map.put("day", rs.getString("day"));
            	map.put("date", rs.getString("date"));
            	map.put("time", rs.getString("time"));
            	map.put("hit", rs.getInt("hit"));
            	map.put("heart", rs.getInt("heart"));
            	list.add(map);
            }
            
        } catch (Exception e) {
            System.out.println("ERROR!!!!" + e.toString());
        } finally {
            DbConnectionPools.closeResources(connect, pstmt);
        }
        return list;
	}
	
	public static ArrayList<HashMap<String, Object>> getPhoto(String day){
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		PreparedStatement pstmt = null;
        Connection connect = null;
        try {
        	
            connect = DbConnectionPools.getPoolConnection();
            pstmt = connect.prepareStatement(GET_TOP_PHOTO_WITH_DAY);
            pstmt.setString(1, day);
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

            	HashMap<String, Object> map = new HashMap<>();
            	map.put("id", rs.getString("id"));
            	map.put("userId", rs.getString("userId"));
            	map.put("layout", -1);
            	String picture = rs.getString("photo");
            	ArrayList<String> picList = new ArrayList<>();
            	for(String pic : picture.split(",")){
            		if("".equals(pic)){
            			continue;
            		}
            		picList.add(pic);
            	}
            	map.put("picture", picList);
            	map.put("day", rs.getString("day"));
            	map.put("heart", rs.getInt("heart"));
            	list.add(map);
            }
            
        } catch (Exception e) {
            System.out.println("ERROR!!!!" + e.toString());
        } finally {
            DbConnectionPools.closeResources(connect, pstmt);
        }
        return list;
	}

}
