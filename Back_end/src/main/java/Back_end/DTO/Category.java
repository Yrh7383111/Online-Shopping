package Back_end.DTO;


import javax.persistence.*;



@Entity
public class Category
{
    // Private
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageURL;

    @Column(name = "is_active")
    private boolean active = true;


    // Public
    public Category()
    {
        this.name = null;
        this.description = null;
        this.imageURL = null;
    }

    public Category(int id, String name, String description, String imageURL, boolean active)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
        this.active = active;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getImageURL()
    {
        return imageURL;
    }

    public void setImageURL(String imageURL)
    {
        this.imageURL = imageURL;
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    @Override
    public String toString()
    {
        return "Category [id=" + id + ", name=" + name + ", description=" + description + ", imageURL=" + imageURL + ", active=" + active + "]";
    }
}