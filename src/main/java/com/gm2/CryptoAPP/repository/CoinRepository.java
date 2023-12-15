package com.gm2.CryptoAPP.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gm2.CryptoAPP.dto.CoinTransationDTO;
import com.gm2.CryptoAPP.entity.Coin;

import jakarta.persistence.EntityManager;

@Repository
public class CoinRepository {
	private JdbcTemplate jdbcTemplate;
	
	private static String INSERT = "insert into coin(name, datetime, price, quantity) values(?,?,?,?)";
	private static String SELECT_ALL = "select name, sum(quantity) as quantity from coin group by name";
	private static String SELECT_BY_NAME = "select * from coin where name = ?";
	private static String DELETE = "delete from coin where id = ?";
	private static String UPDATE = "update coin set name = ?, price = ?, quantity = ? where id = ?";
	
	public CoinRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@Autowired
	private EntityManager entityManager;
	
	public Coin insert(Coin coin) {
		Object[] attr =  new Object[] {
			coin.getName(),
			coin.getDatetime(),
			coin.getPrice(),
			coin.getQuantity()
		};
		jdbcTemplate.update(INSERT,attr);
		return coin;
	}
	
	public List<CoinTransationDTO> getAll(){
		return jdbcTemplate.query(SELECT_ALL, new RowMapper<CoinTransationDTO>(){
			@Override
			public CoinTransationDTO mapRow(ResultSet rs, int rowNum) throws SQLException{
				CoinTransationDTO coin = new CoinTransationDTO();
				coin.setName(rs.getString("name"));
				coin.setQuantity(rs.getBigDecimal("quantity"));
				return coin;
			}
		});
	}
	public List<Coin> getByName(String name){
		Object[] attr = new Object[] {name};
		return jdbcTemplate.query(SELECT_BY_NAME, new RowMapper<Coin>() {
			@Override
			public Coin mapRow(ResultSet rs, int rowNum) throws SQLException{
				Coin coin = new Coin();
				coin.setId(rs.getInt("id"));
				coin.setName(rs.getString("name"));
				coin.setPrice(rs.getBigDecimal("price"));
				coin.setQuantity(rs.getBigDecimal("quantity"));
				coin.setDatetime(rs.getTimestamp("datetime"));
				return coin;
			}
		},attr);
	}
	public int remove(int id) {
		return jdbcTemplate.update(DELETE, id);
	}
	public Coin update(Coin coin) {
		Object[] attr =  new Object[] {
				coin.getId(),
				coin.getName(),
				coin.getPrice(),
				coin.getQuantity()
			};
		jdbcTemplate.update(UPDATE, attr);
		return coin;
	}
}