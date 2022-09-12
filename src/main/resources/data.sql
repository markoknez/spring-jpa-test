insert into rig(name, description) VALUES ('rig1', 'desc1')
insert into rig(name, description) VALUES ('rig2', 'desc2')
insert into rig(name, description) VALUES ('rig3', 'desc3')

insert into well(name) VALUES('well1')

insert into scenario(name, rigId, wellId) VALUES('scenario1', 1, 1)
insert into scenario(name, rigId, wellId) VALUES('scenario2', 2, 1)
insert into scenario(name, rigId, wellId) VALUES('scenario3', 3, 1)

insert into general_lookup_type(name) Values('scentype')
insert into general_lookup_item(typeId, shortname) Values(1, 'item1')

insert into scenario_attribute(scenarioId, lookupId) VALUES(1, 1)
insert into comments(comment, scenarioId) Values('comment', 1)

