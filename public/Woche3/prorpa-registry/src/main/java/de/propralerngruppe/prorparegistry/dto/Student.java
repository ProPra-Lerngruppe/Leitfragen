package de.propralerngruppe.prorparegistry.dto;

import java.util.Objects;

public class Student{
    public long matriculation;
    public String name;
    public String gitHubHandle;
    public String unikennung;

    public Student() {
    }

    public Student(long matriculation, String name, String gitHubHandle, String unikennung) {
        this.matriculation = matriculation;
        this.name = name;
        this.gitHubHandle = gitHubHandle;
        this.unikennung = unikennung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return matriculation == student.matriculation && name.equals(student.name) && gitHubHandle.equals(student.gitHubHandle) && unikennung.equals(student.unikennung);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matriculation, name, gitHubHandle, unikennung);
    }

    public void setMatriculation(long matriculation) {
        this.matriculation = matriculation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGitHubHandle(String gitHubHandle) {
        this.gitHubHandle = gitHubHandle;
    }

    public void setUnikennung(String unikennung) {
        this.unikennung = unikennung;
    }

    public long getMatriculation() {
        return matriculation;
    }

    public String getName() {
        return name;
    }

    public String getGitHubHandle() {
        return gitHubHandle;
    }

    public String getUnikennung() {
        return unikennung;
    }
}
