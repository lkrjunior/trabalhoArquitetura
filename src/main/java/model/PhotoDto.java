package model;

import java.util.Date;

public class PhotoDto
{
    private Long id;
    private String link;
    private Person person;
    private byte[] photoBytes;

    private String photoString;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink()
    {
        return link;
    }

    public void setLink(String link)
    {
        this.link = link;
    }

    public Person getPerson()
    {
        return person;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }

    public byte[] getPhotoBytes()
    {
        return photoBytes;
    }

    public void setPhotoBytes(byte[] photoBytes)
    {
        this.photoBytes = photoBytes;
    }

    public String getPhotoString()
    {
        return photoString;
    }

    public void setPhotoString(String photoString)
    {
        this.photoString = photoString;
    }
}
