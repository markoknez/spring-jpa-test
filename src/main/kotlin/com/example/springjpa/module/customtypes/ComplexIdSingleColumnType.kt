package com.example.springjpa.module.customtypes

import com.example.springjpa.module.ComplexId
import org.hibernate.id.ResultSetIdentifierConsumer
import org.hibernate.type.AbstractSingleColumnStandardBasicType
import org.hibernate.type.descriptor.sql.IntegerTypeDescriptor
import org.hibernate.usertype.DynamicParameterizedType
import java.io.Serializable
import java.sql.ResultSet
import java.util.*


class ComplexIdSingleColumnType :
    AbstractSingleColumnStandardBasicType<ComplexId>(IntegerTypeDescriptor.INSTANCE, ComplexIdJavaTypeDescriptor()),
    ResultSetIdentifierConsumer,
    DynamicParameterizedType {

    protected lateinit var internalName: String

    override fun getName(): String {
        return internalName
    }

    override fun consumeIdentifier(resultSet: ResultSet): Serializable {
        return javaTypeDescriptor.wrap(resultSet.getInt(1), null)
    }

    override fun setParameterValues(parameters: Properties) {
        val returnedClass = Class.forName(parameters[DynamicParameterizedType.RETURNED_CLASS] as String)
        internalName = returnedClass.simpleName
        (javaTypeDescriptor as ComplexIdJavaTypeDescriptor).type = returnedClass
    }
}
