package org.example.entities.abstracts;

public class PostEntity {
    private String type;
    private Object attributes;

    public PostEntity(String type, Object attributes){
        this.type = type;
        this.attributes = attributes;
    }

    public Object getAttributes() {
        return attributes;
    }

    public String getType(){
        return this.type;
    }
}
