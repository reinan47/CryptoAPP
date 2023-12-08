package repository;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entity.Coin;

@Repository
@EnableAutoConfiguration
public class CoinRepository {
	private JdbcTemplate jdbcTemplate;
	
	private String INSERT = "INSERT INTO COIN(NAME,DATETIME,PRICE,QUANTITY) VALUES(?,?,?,?)";
	
	public CoinRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
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
}
