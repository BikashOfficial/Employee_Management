package org.myproject.em_project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Long id;
    private String name;
    private String phone;
    private String email;

    // public String getName(){
    //     return name;
    // }
    // public void setName(String name){
    //     this.name = name;
    // } 
    
}
