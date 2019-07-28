package model;

import java.util.HashSet;
import java.util.Set;

public class PersonDto
{
    private Long id;
    private String cpf;
    private String name;
    private Set<Photo> photos = new HashSet<>();

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getCpf()
    {
        return cpf;
    }

    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Set<Photo> getPhotos()
    {
        return photos;
    }

    public void setPhotos(Set<Photo> photos)
    {
        this.photos = photos;
    }
}
