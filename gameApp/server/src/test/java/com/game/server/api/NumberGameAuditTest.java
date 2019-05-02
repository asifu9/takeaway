package com.game.server.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.game.server.ServerApplication;
import com.game.server.entity.Game;
import com.game.server.entity.NumberGameAudit;
import com.game.server.entity.dto.AuditDTO;
import com.game.server.entity.dto.GameDTO;
import com.game.server.enums.GameStatus;
import com.game.server.enums.GameType;
import com.game.server.service.GameInstanceService;
import com.game.server.service.GameService;
import com.game.server.service.GameUtil;
import com.game.server.service.NumberGameAuditService;
//
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServerApplication.class)

//@WebAppConfiguration
// @IntegrationTest("server.port:0")
// @FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NumberGameAuditTest {

	@Autowired
	private NumberGameAuditService auditService;
	
	@Autowired
	private GameUtil gameUtil;
	
	@Autowired
	private GameInstanceService gameInstanceService;

	@Test
	public void createTest() throws Exception {
		AuditDTO audit = new AuditDTO(5,3,3,"tst",GameStatus.RUNNING,1,2,1,"test",0);
		NumberGameAudit p = gameUtil.convert(auditService.create( audit),NumberGameAudit.class);
		assertNotNull(p);
		gameInstanceService.deleteAll();
		auditService.deleteAll();
		List<NumberGameAudit> del = auditService.findByGameInstanceId(1);
		assertEquals(0, del.size());
	}

	
}
