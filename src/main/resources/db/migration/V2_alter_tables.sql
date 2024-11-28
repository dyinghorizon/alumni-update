-- alter.sql
ALTER TABLE alumni
    ADD CONSTRAINT fk_alumni_student
        FOREIGN KEY (student_id) REFERENCES students(student_id);

ALTER TABLE alumni_education
    ADD CONSTRAINT fk_alumni_education_alumni
        FOREIGN KEY (alumni_id) REFERENCES alumni(id);

ALTER TABLE alumni_organisation
    ADD CONSTRAINT fk_alumni_organisation_alumni
        FOREIGN KEY (alumni_id) REFERENCES alumni(id);

ALTER TABLE alumni_organisation
    ADD CONSTRAINT fk_alumni_organisation_organisation
        FOREIGN KEY (organisation_id) REFERENCES organizations(id);