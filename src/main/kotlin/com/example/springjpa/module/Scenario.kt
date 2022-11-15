package com.example.springjpa.module


import org.hibernate.Hibernate
import org.hibernate.annotations.Type
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "scenario")
data class Scenario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Type(type = "com.example.springjpa.module.customtypes.ComplexIdSingleColumnType")
    var id: ScenarioId? = null,
    var name: String
) {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rigId")
    var rig: Rig? = null


    @Type(type = "com.example.springjpa.module.customtypes.ComplexIdSingleColumnType")
    var wellId: WellId? = null

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "scenario_attribute", joinColumns = [JoinColumn(name = "scenario_Id")])
    @Column(name = "lookup_Id")
    @Type(type = "com.example.springjpa.module.customtypes.ComplexIdSingleColumnType")
    var attributes: MutableSet<ScenarioAttributeValueId> = mutableSetOf()

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Comments", joinColumns = [JoinColumn(name = "scenario_Id")])
    @AttributeOverrides(
        value = [
            AttributeOverride(
                name = "comment",
                column = Column(name = "comment")
            ),
            AttributeOverride(
                name = "date",
                column = Column(name = "comment")
            )]
    )
    var comments: MutableSet<Comment> = mutableSetOf()

}
@Table(name = "comments")
data class Comment(
    @Type(type = "com.example.springjpa.module.customtypes.ComplexIdSingleColumnType")
    @Column(name = "id")
    var commentId: CommentId? = null,
    var comment: String? = null,
    var created: Date? = null
)

