--insert into well (name) values ('marko')
--insert into test (alsoName) values ('with-scenario')
insert into rig(name, description) VALUES ('rig1', 'desc1')
insert into rig(name, description) VALUES ('rig2', 'desc2')
insert into rig(name, description) VALUES ('rig3', 'desc3')

insert into well(name) VALUES('well1')

insert into scenario(name, well_id, rig_Id) VALUES('scenario1', 1, 1)
insert into scenario(name, well_id, rig_Id) VALUES('scenario2', 1, 2)
insert into scenario(name, well_id, rig_Id) VALUES('scenario3', 1, 3)

insert into general_lookup_type(name) Values('scentype')
insert into general_lookup_item(typeId, shortname) Values(1, 'item1')

insert into scenario_attribute(scenario_Id, lookup_Id) VALUES(1, 1)
