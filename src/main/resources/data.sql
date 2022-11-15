insert into rig(name, description) VALUES ('rig1', 'desc1')
insert into rig(name, description) VALUES ('rig2', 'desc2')
insert into rig(name, description) VALUES ('rig3', 'desc3')

insert into well(name) VALUES('well-1')
insert into well(name) VALUES('well-2')

insert into scenario(name, rig_id, well_id) VALUES('scenario1', 1, 1)
insert into scenario(name, rig_id, well_id) VALUES('scenario2', 2, 1)
insert into scenario(name, rig_id, well_id) VALUES('scenario3', 3, 1)

insert into general_lookup_type(name) Values('scentype')
insert into general_lookup_item(typeId, shortname) Values(1, 'item1')
insert into general_lookup_item(typeId, shortname) Values(1, 'item2')
insert into general_lookup_item(typeId, shortname) Values(1, 'item3')

insert into scenario_attribute(scenario_Id, lookup_Id) VALUES(1, 1)

insert into comments(comment, scenario_id) VALUES('c1', 1)
insert into comments(comment, scenario_id) VALUES('c2', 1)



