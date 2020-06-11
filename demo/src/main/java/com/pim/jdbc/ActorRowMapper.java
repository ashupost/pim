package com.pim.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ActorRowMapper implements RowMapper<Actor> {

	public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Actor actor = new Actor();
		actor.setLoginid(rs.getString(1));
		actor.setPassword(rs.getString(2));
		actor.setEmail(rs.getString(3));
		return actor;
	}

}