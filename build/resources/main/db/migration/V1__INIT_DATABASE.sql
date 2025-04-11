CREATE TABLE laboratory (
    id SERIAL PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE property (
      id SERIAL PRIMARY KEY,
      name VARCHAR(255) NOT NULL UNIQUE,
      address VARCHAR(255)
);

CREATE TABLE person (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    initial_date DATE NOT NULL,
    end_date DATE NOT NULL,
    description TEXT,
    laboratory_id INT,
    CONSTRAINT fk_person_laboratory FOREIGN KEY (laboratory_id) REFERENCES laboratory (id)
);

CREATE TABLE person_property (
     person_id INT NOT NULL,
     property_id INT NOT NULL,
     PRIMARY KEY (person_id, property_id),
     CONSTRAINT fk_pp_person FOREIGN KEY (person_id) REFERENCES person (id) ON DELETE CASCADE,
     CONSTRAINT fk_pp_property FOREIGN KEY (property_id) REFERENCES property (id) ON DELETE CASCADE
);
