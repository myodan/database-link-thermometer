package kr.djsch.krdhs.dlt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Student {

    @Id
    private long id;

    private long grade;

    private long group;

    private long number;

    private String name;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    @OrderBy("date DESC")
    private List<Temperature> temperatureList = new ArrayList<>();

}
