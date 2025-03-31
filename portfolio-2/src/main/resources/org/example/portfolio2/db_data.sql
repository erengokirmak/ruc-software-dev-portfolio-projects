/* Insert the basic programmes*/
insert into programme values
                          ('HumTek'),
                          ('NatBach');

/* Insert subject Modules */
insert into subject_module values ('Computer Science'),
                                  ('Informatik'),
                                  ('Astronomy');

/* Insert basic activities for NatBach */
insert into activity values ('BP1 NatBach', 15, true),
                            ('BP2 NatBach', 15, true),
                            ('BP3 NatBach', 15, true),
                            ('Bachelorproject NatBach', 10, true),
                            ('BK1 Empirical Data', 5, false),
                            ('BK2 Experimental Methods', 10, false),
                            ('BK3 Theory of Natural Science', 5, false),
                            ('Logic and Discrete Mathematics', 10, false);

/* Insert basic activities for HumTek */
insert into activity values ('BP1 HumTek', 15, true),
                            ('BP2 HumTek', 15, true),
                            ('BP3 HumTek', 15, true),
                            ('Bachelorproject HumTek', 15, true),
                            ('Design og Konstruktion I+Workshop', 10 , false),
                            ('Design og Konstruktion II+Workshop' , 5, true),
                            ('Subjektivitet, Teknologi og Samfund I', 10, true),
                            ('Teknologiske systemer og artefakter I', 5, false),
                            ('Videnskabsteori', 10, false);

/* Connect NatBach to its courses  */
insert into programme_activity values ('NatBach', 'BP1 NatBach'),
                                      ('NatBach', 'BP2 NatBach'),
                                      ('NatBach', 'BP3 NatBach'),
                                      ('NatBach', 'Bachelorproject NatBach'),
                                      ('NatBach', 'BK1 Empirical Data'),
                                      ('NatBach', 'BK2 Experimental Methods'),
                                      ('NatBach', 'BK3 Theory of Natural Science'),
                                      ('NatBach', 'Logic and Discrete Mathematics');

/* Connect HumTek to its courses */
insert into programme_activity values ('HumTek', 'BP1 HumTek'),
                                      ('HumTek', 'BP2 HumTek'),
                                      ('HumTek', 'BP3 HumTek'),
                                      ('HumTek', 'Bachelorproject HumTek'),
                                      ('HumTek', 'Design og Konstruktion I+Workshop'),
                                      ('HumTek', 'Design og Konstruktion II+Workshop'),
                                      ('HumTek', 'Subjektivitet, Teknologi og Samfund I'),
                                      ('HumTek', 'Teknologiske systemer og artefakter I'),
                                      ('HumTek', 'Videnskabsteori');


/* Insert activities for Computer Science subject module */
insert into activity values ('Essential Computing', 5, false),
                            ('Software Development', 10, false),
                            ('Interactive Digital Systems', 5, false),
                            ('Subject module project in Computer Science', 15, true);

/* Insert activities for Informatik subject module */
insert into activity values ('Organisatorisk forandring og IT', 10, false),
                            ('BANDIT', 10, false),
                            ('Web baserede IT-Systemer', 5, false),
                            ('Subject module project in Informatik', 15, true);

/* Insert activities for the Astronomy subject Module */
insert into activity values ('Venus studies', 10, false),
                            ('Mars studies', 5, false),
                            ('Orbits and trajectories', 10, false),
                            ('Subject module project in Astronomy', 15, true);

/* Connect Computer Science to its activities */
insert into submodule_activity values ('Computer Science', 'Essential Computing'),
                                      ('Computer Science', 'Software Development'),
                                      ('Computer Science', 'Interactive Digital Systems'),
                                      ('Computer Science', 'Subject module project in Computer Science');

/* Connect Informatik to its activities */
insert into submodule_activity values ('Informatik', 'Organisatorisk forandring og IT'),
                                      ('Informatik', 'BANDIT'),
                                      ('Informatik', 'Web baserede IT-Systemer'),
                                      ('Informatik', 'Subject module project in Informatik');

/* Connect Astronomy to its activities */
insert into submodule_activity values ('Astronomy', 'Venus studies'),
                                      ('Astronomy', 'Mars studies'),
                                      ('Astronomy', 'Orbits and trajectories'),
                                      ('Astronomy', 'Subject module project in Astronomy');


/* Insert one student to use in the application */
insert into student values ('Eren', 'NatBach');

/* Insert some registered courses */
insert into student_activity values ('Eren', 'BP1 NatBach'),
                                  ('Eren', 'BP2 NatBach'),
                                  ('Eren', 'BP3 NatBach'),
                                  ('Eren', 'Logic and Discrete Mathematics')