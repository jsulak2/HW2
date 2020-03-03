package sample;

import java.util.UUID;

public class Student
{
    public String name;
    public UUID id;
    public String age;
    public String major;
    public String gpa;

    public Student()
    {

    }

    public Student(String n, String a, String m, String g)
    {
        name = n;
        id = UUID.randomUUID();
        age = a;
        major = m;
        gpa = g;
    }

    public String getname(){
        return (this.name);
    }
    public String getid(){
        String idstr = id.toString();
        return idstr;
    }
    public String getmajor(){
        return (this.major);
    }
    public String getage(){
        return (this.age);
    }
    public String getgpa(){
        return (this.gpa);
    }
    @Override
    public String toString(){
        return (this.name + " " + this.age + " " + this.major + " " + this.gpa + " " + this.id );
    }
}
