package com.example.springjpa.module

import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.id.ResultSetIdentifierConsumer
import org.hibernate.usertype.EnhancedUserType
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import java.io.Serializable
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLType
import java.sql.Types
import javax.persistence.*

interface WellRepo : CrudRepository<Well, ComplexId>
interface ScenarioRepo: CrudRepository<Scenario, ComplexId>

@Entity
class Well(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Type(type = "com.example.springjpa.module.ComplexIdUserType")
    var id: ComplexId?,

    var name: String,

    @OneToMany(mappedBy = "well", fetch = FetchType.LAZY)
    var scenarios: Collection<Scenario> = emptyList()
)

@Entity
class Scenario(
    @Id
    @Type(type = "com.example.springjpa.module.ComplexIdUserType")
    var id: ComplexId,

    var name: String,

    @ManyToOne
    var well: Well?
)

class ComplexId(val clientUid: String, val id: Int) : Serializable



@Component
class ComplexIdUserType : EnhancedUserType, ResultSetIdentifierConsumer {
    override fun consumeIdentifier(resultSet: ResultSet): Serializable {
        return ComplexId("generated", resultSet.getInt(1))
    }

    override fun equals(x: Any?, y: Any?): Boolean {
        return when {
            x === null && y !== null -> false
            y === null && x !== null -> false
            x !is ComplexId || y !is ComplexId -> false
            x.clientUid != y.clientUid ||
                    x.id != y.id -> false

            else -> true
        }
    }

    override fun hashCode(x: Any?): Int {
        require(x is ComplexId?)
        if (x == null) return 31 * 7
        return 31 * 7 +
                31 * x.clientUid.hashCode() +
                31 * x.id.hashCode()
    }

    override fun sqlTypes(): IntArray {
        return intArrayOf(java.sql.Types.INTEGER)
    }

    override fun returnedClass(): Class<*> {
        return ComplexId::class.java
    }

    override fun nullSafeGet(rs: ResultSet?, names: Array<out String>?, session: SharedSessionContractImplementor?, owner: Any?): Any {
        requireNotNull(rs)
        requireNotNull(names)
        return ComplexId("marko", rs.getInt(names[0]))
    }

    override fun nullSafeSet(st: PreparedStatement?, value: Any?, index: Int, session: SharedSessionContractImplementor?) {
        requireNotNull(st)
        require(value is ComplexId?)
        if (value != null)
            st.setInt(index, value.id)
        else
            st.setNull(index, Types.INTEGER)
    }

    override fun deepCopy(value: Any): Any {
        return value
    }

    override fun isMutable(): Boolean {
        return false
    }

    override fun disassemble(value: Any): Serializable {
        require(value is ComplexId)
        return value
    }

    override fun assemble(cached: Serializable, owner: Any?): Any {
        return cached as ComplexId
    }

    override fun replace(original: Any, target: Any?, owner: Any?): Any {
        return original
    }

    override fun objectToSQLString(value: Any?): String {
        require(value is ComplexId)
        return value.id.toString()
    }

    override fun toXMLString(value: Any?): String {
        TODO("Not yet implemented")
    }

    override fun fromXMLString(xmlValue: String?): Any {
        TODO("Not yet implemented")
    }
}
