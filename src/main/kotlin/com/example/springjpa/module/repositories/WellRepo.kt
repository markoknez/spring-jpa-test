package com.example.springjpa.module.repositories

import com.example.springjpa.module.Well
import com.example.springjpa.module.WellId
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.Repository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.ResultSetExtractor
import org.springframework.stereotype.Component
import org.springframework.transaction.TransactionManager
import org.springframework.transaction.annotation.Transactional
import javax.sql.DataSource

interface WellRepo : CrudRepository<Well, WellId> {
    @Query(
        "select well from Well well " +
                "left join Scenario s on s.id = well.id " +
                "where well.name = :wellName"
    )
    fun wellLeftJoinWithScenarioOnId(wellName: String): List<Well>

    @Query(
        "select well.* from Well well " +
                "inner join test t on t.alsoName = well.well_name " +
                "where well.name = :name", nativeQuery = true
    )
    fun wellInnerJoinWithTestOnName(name: String): Collection<Well>

    @Query("select * from well where id = :#{#wellId.id}", nativeQuery = true)
    fun nativeQueryWithComplexId(wellId: WellId): Collection<Well>

    fun findWellsByName(name : String)
}

@Component
class JdbcTemplateTest(
    private val dataSource: DataSource,
//    private val jdbcTemplate: JdbcTemplate
) {
    fun getWell(wellId: WellId): Well? {
        return dataSource.connection.use {
            it.prepareStatement("select id, name from well where id = 1").use { preparedStatement ->
                preparedStatement.executeQuery().use { resultSet ->
                    if(!resultSet.next()) return null
                    return Well(resultSet.getString(2)).apply {
                        id = WellId("cid", resultSet.getInt(1))
                    }
                }
            }
        }
//        return jdbcTemplate.query("select id, name from well where id = 1", ResultSetExtractor { rs ->
//            if(!rs.next()) null
//            else {
//                Well(rs.getString(2)).apply {
//                    id = WellId("", rs.getInt(1))
//                }
//            }
//        })
    }
}
