package spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class MemberDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Member selectByEmail(String email) {
		List<Member> members = jdbcTemplate.query("select * from MEMBER where EMAIL = ?", 
				new MemberRowMapper(), email);
		Member member = null;
		if (!members.isEmpty()) {
			member = members.get(0);
		}
			
		return member;
	}
	
	public void insert(Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		// GeneratedKeyHolder는 자동 생성된 키값을 구해주는 KeyHolder 구현 클래스이다.
		jdbcTemplate.update(
			new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con) 
				throws SQLException {
					PreparedStatement statement = con.prepareStatement(
						"insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE) " + 
						"values(?, ?, ?, ?)",
						new String[] {"ID"}); // 두 번째 파라미터는 자동 생성되는 키 칼럼 목록을 지정한다.
					statement.setString(1, member.getEmail());
					statement.setString(2, member.getPassword());
					statement.setString(3, member.getName());
					statement.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
				
					return statement;
			}
		}, keyHolder); // update 메서드는 statement를 실행한 후 자동 생성된 키 값을 keyHolder에 보관한다.
		Number keyValue = keyHolder.getKey(); 
		member.setId(keyValue.longValue());
	}
	
	public void update(Member member) {
		jdbcTemplate.update("update MEMBER set NAME = ?, PASSWORD = ? where EMAIL = ?",
				member.getName(), member.getPassword(), member.getEmail());
	}
	
	public List<Member> selectAll() {
		List<Member> members = jdbcTemplate.query("select * from MEMBER", 
				new MemberRowMapper());
		
		return members;
	}
	
	public int count() {
		Integer count = jdbcTemplate.queryForObject( 
				// 쿼리 실행 결과 행이 한 개인 경우에 사용할 수 있는 메서드, 두 개 이상이면 예외 발생
				// 두 번째 파라미터는 칼럼을 읽어올 때 사용할 타입 지정
				"select count(*) from MEMBER", Integer.class);
		
		return count;
	}
}
