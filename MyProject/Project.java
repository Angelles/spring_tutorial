package MyProject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
class Project {
    // Define the model for a project.
    /* String name: name of the project
    * Long id: id of the project
    * Enum status: status of the project
     */
    private @Id @GeneratedValue Integer id;
    private String name;
    private Craft craft;


    // Project initializer
    Project(){}


    // Project object model
    Project(String name, Craft craft){
        this.name = name;
        this.craft = craft;
    }

    // Object methods - getters and setters
    // Getters
    public String getName(){
        return this.name;
    }
    public Integer getId(){ return this.id; }
    public Craft getCraft() {
        return this.craft;
    }

    // Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public void setCraft(Craft craft) {
        this.craft = craft;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Project p))
            return false;
        return Objects.equals(this.id, p.id) && Objects.equals(this.name, p.name)
                && Objects.equals(this.craft, p.craft);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.craft);
    }


    public String toString(){
        return "\nProject Name: " + getName() + "\nId: " + getId() + "\nCraft: " + getCraft();
    }
}


