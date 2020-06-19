package com.pim.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ActorRowMapper implements RowMapper<Actor> {

	public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Actor actor = new Actor();
		actor.setId(rs.getString(1));
		actor.setLast_name(rs.getString(2));
		actor.setFirst_name(rs.getString(3));
		return actor;
	}

}