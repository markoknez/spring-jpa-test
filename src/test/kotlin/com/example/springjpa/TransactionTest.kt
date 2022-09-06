//package com.example.springjpa
//
//import com.example.springjpa.module.Well
//import com.example.springjpa.module.WellId
//import com.example.springjpa.module.repositories.JdbcTemplateTest
//import com.example.springjpa.module.repositories.WellRepo
//import org.h2.tools.Server
//import org.hibernate.jpa.internal.PersistenceUnitUtilImpl
//import org.junit.jupiter.api.BeforeAll
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.data.jpa.provider.HibernateUtils
//import org.springframework.orm.jpa.EntityManagerHolder
//import org.springframework.transaction.annotation.Transactional
//import javax.persistence.EntityManager
//import javax.persistence.PersistenceUnitUtil
//import javax.persistence.PersistenceUtil
//
//@SpringBootTest
//class TransactionTest {
//    companion object {
//        @BeforeAll
//        @JvmStatic
//        fun before() {
//            Server.createTcpServer("-tcpPort", "9092").start()
//        }
//    }
//
//    @Autowired
//    private lateinit var entityManager: EntityManager
//
//    @Autowired
//    private lateinit var wellRepo: WellRepo
//
//    @Autowired
//    private lateinit var jdbcTemplateTest: JdbcTemplateTest
//
//    @Test
//    @Transactional
//    fun canUseJdbcInSameTransaction() {
//        val well = Well("new well")
//        wellRepo.save(well)
//
////        val emWell = entityManager.createQuery("select well from Well well where well.id = :wellId").run {
////            setParameter("wellId", well.id)
////            singleResult as Well?
////        }
////        val againWell = wellRepo.findAll().single()
//
//        well.name = "changed"
//        entityManager.flush()
//
//
//        val well2 = jdbcTemplateTest.getWell(well.id!!)
//        assert(well2?.name == "changed")
//    }
//}
