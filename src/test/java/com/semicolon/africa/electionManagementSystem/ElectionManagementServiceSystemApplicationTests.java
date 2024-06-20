package com.semicolon.africa.electionManagementSystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
class ElectionManagementServiceSystemApplicationTests {

	@Test
	@Sql({"/database/data.sql"})
	void contextLoads() {
	}

}
