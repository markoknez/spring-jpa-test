package com.example.springjpa.module.customtypes

import com.example.springjpa.module.ComplexId
import org.hibernate.type.descriptor.WrapperOptions
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor

class ComplexIdJavaTypeDescriptor : AbstractTypeDescriptor<ComplexId>(ComplexId::class.java) {
    lateinit var type: Class<*>

    private val mainConstructor by lazy {
        type.constructors
            .filter { it.parameterCount == 2 }
            .single { String::class.java.isAssignableFrom(it.parameterTypes[0]) && Int::class.java.isAssignableFrom(it.parameterTypes[1]) }
    }

    protected fun complexIdConstructor(clientUid: String, id: Int): ComplexId {
        return mainConstructor.newInstance(clientUid, id) as ComplexId
    }

    override fun toString(value: ComplexId?): String {
        return when (value) {
            null -> "null"
            else -> value.id.toString()
        }
    }

    override fun fromString(string: String?): ComplexId? {
        return when (string) {
            null -> null
            else -> return complexIdConstructor("this should not be used", string.toInt())
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <X : Any?> unwrap(value: ComplexId?, type: Class<X>?, options: WrapperOptions?): X? {
        return when {
            value == null -> null
            Integer::class.java.isAssignableFrom(type) -> value.id as X
            else -> throw unknownUnwrap(type)
        }
    }

    override fun <X : Any?> wrap(value: X, options: WrapperOptions?): ComplexId? {
        return when {
            value == null -> null
            value is Int -> complexIdConstructor("type-descriptor", value)
            else -> throw unknownWrap(value!!::class.java)
        }
    }
}
