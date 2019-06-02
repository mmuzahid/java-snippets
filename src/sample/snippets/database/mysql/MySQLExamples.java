package sample.snippets.database.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySQLExamples {

	private static final String SQL_INSERT_INTO_REQUEST_LOGS = "INSERT INTO post (`title`,`description`,`status`,`author`, `posted_on`) VALUES(?, ?, ?, ?, ?)";

	private static final String SQL_SELECT_REQUEST_LOGS = "SELECT `title`,`description`,`status`,`author`, `posted_on` FROM `post` where `author` = ?";

	private static final String SQL_DROP_TABLE_POST = "DROP TABLE IF EXISTS `post`";

	private static final String SQL_CREATE_TABLE_POST = "CREATE TABLE `post` (\r\n"
			+ "			  `title` VARCHAR(100) NOT NULL,\r\n" + "			  `description` VARCHAR(200) NOT NULL,\r\n"
			+ "			  `status` INT NOT NULL,\r\n" + "			  `author` VARCHAR(50),\r\n"
			+ "			  `posted_on` DATETIME NOT NULL\r\n" + "			);";

	private static Connection conn = null;

	public static void initConnection(String driver, String url, String username, String password) {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, username, password);
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void batchInsert(List<Post> posts) {
		try {
			PreparedStatement prepStat = conn.prepareStatement(SQL_INSERT_INTO_REQUEST_LOGS);

			for (Post post : posts) {
				prepStat.setString(1, post.getTitle());
				prepStat.setString(2, post.getDescription());
				prepStat.setInt(3, post.getStatus());
				prepStat.setString(4, post.getAuthor());
				prepStat.setTimestamp(5, new java.sql.Timestamp(post.getPostedOn().getTime()));
				prepStat.addBatch();
			}

			prepStat.executeBatch();
			prepStat.close();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static List<Post> selectPostByAuthor(String searchingAuthor) {
		List<Post> posts = new ArrayList<Post>();

		try {

			PreparedStatement prepStat = conn.prepareStatement(SQL_SELECT_REQUEST_LOGS);
			prepStat.setString(1, searchingAuthor);

			ResultSet rs = prepStat.executeQuery();

			while (rs.next()) {
				String title = rs.getString("title");
				String description = rs.getString("description");
				int status = rs.getInt("status");
				String author = rs.getString("author");
				Date postedOn = rs.getTimestamp("posted_on");
				Post post = new Post(title, description, status, author, postedOn);
				posts.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return posts;
	}

	public static void resetTables() {
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(SQL_DROP_TABLE_POST);
			preparedStatement.executeUpdate();
			conn.commit();
			preparedStatement = conn.prepareStatement(SQL_CREATE_TABLE_POST);
			preparedStatement.executeUpdate();
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String args[]) {
		initConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/mypostdb", "root", "root");
		resetTables();
		List<Post> posts = new ArrayList<Post>();
		posts.add(new Post("Test Title1", "Test description1", 1, "author1", new Date()));
		posts.add(new Post("Test Title2", "Test description2", 0, "author1", new Date()));
		posts.add(new Post("Test Title3", "Test description3", 1, "author1", new Date()));
		posts.add(new Post("Test Title4", "Test description4", 1, "author2", new Date()));
		posts.add(new Post("Test Title5", "Test description5", 1, "author2", new Date()));
		
		batchInsert(posts);
		
		List<Post> authorPosts = selectPostByAuthor("author1");
		System.out.println(authorPosts);
	}
	
	
}

class Post {
	private String title;
	private String description;
	private int status;
	private String author;
	private Date postedOn;

	public Post(String title, String description, int status, String author, Date postedOn) {
		this.title = title;
		this.description = description;
		this.status = status;
		this.author = author;
		this.postedOn = postedOn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}

	@Override
	public String toString() {
		return "Post [title=" + title + ", description=" + description + ", status=" + status + ", author=" + author
				+ ", postedOn=" + postedOn + "]";
	}
	
}
