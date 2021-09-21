# Consistency Rules

## Hospital System Integration

- CR1: All feature values for replicas of the same type must be equal.

## ReqIF specification, TOGAF architecture, BPMN process, UML classes

- CR2: Every accepted functional requirement must be associated to a function in the architecture
- CR3: Every information item must traceable to a classes in UML
- CR4: Each function must be traceable to a method in UML
- CR5: Every process in the architecture must be implemented by BPMN process and each actor involved in the process must correspond to a pool


##  BPMN processes, UML classes, DMN tables


- CR6: Every `Activity` of type `business_rule` in BPMN must be _defined_ by a corresponding `DecisionTable` in the DMN model.
- CR7: Every `DataObject` in BPMN must be _implemented_ by a corresponding `Class` or  an `Attribute` in the UML model.
- CR8: Every `Column` in DMN must must be _implemented_ by a corresponding `Attribute` in the UML model and the `columnTyp` should correspond to the `type` of the `Attribute`.
- CR9: `Column`s in DMN may be _associated_ with a `DataObject` in BPMN. In this case, being an `inputSideColumn` means that there must be a corresponing `consumedBy`-association between the respective `DataObject` and `Activity` in the BPMN model,
Respectively, being an `outputSideColumn` enforces a `producesBy`-association.
Moreover, this relation must be compatible with the implementation-relationship between `DataObject` and `Attribiutes` (CR7) such that each triple of (`DataObject`,`Attribute` and `Column`) is a ternary ``to-one'' relationship.

## UML classes, SQL schemas, Java code (known from basically every ORM-case study/model transformation exercise)


Each UML class mit stereotype must be realised by a respective Java class
If the class has an <<entity>> stereotype there must be a table with a corresponding type.
For each @Entity class there must be a CREATE TABLE statement
For each non transient Attribute a column with correct data types etc.
... and all the other ORM rules 


translate \texttt{EReference}s to \texttt{ForeignKey}s.
A many-to-one relation from a table/class $A$ to table/class $B$ is realized by adding a new numeric column to $A$ that has a foreign key to the primary key of $B$. @ManyToOne

one-to-may, reverse foreign key, @OneToMany @JoinColumn(name = “fk_back”)
Many-to-many relations, require to create a new \emph{junction} table with two columns pointing with foreign keys to the respective primary keys of $A$ and $B$.
@ManyToMany
    @JoinTable(name = “table_name”,
           joinColumns = { @JoinColumn(name = “fk_owner”) },
           inverseJoinColumns = { @JoinColumn(name = “fk_target”) })

birecetional relationships, @OneToMany(mappedBy = “otherAttribute”)

Single Table Inheritance (table per inheritance hierarchy)\footnote{\url{https://martinfowler.com/eaaCatalog/singleTableInheritance.html}}, Java annotation @Inheritance(strategy = InheritanceType.SINGLE_TABLE) @DiscriminatorColumn(name = type)
Class Table Inheritance (table per class)\footnote{\url{https://martinfowler.com/eaaCatalog/classTableInheritance.html}}, @Inheritance(strategy = InheritanceType.JOINED)
Concrete Table Inheritance (table per concrete class)\footnote{\url{https://martinfowler.com/eaaCatalog/concreteTableInheritance.html}}.  @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
