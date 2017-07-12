package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

/**
 * Created by Andreea.Dima on 7/12/2017.
 */
@Table(name="employees")
public class Employee {
    @Column(name="FIRST_NAME")
    private String firstName;
    @Column(name="LAST_NAME")
    private String lastName;
    @Column(name="EMAIL")
    private  String email;
    @Column(name="PHONE")
    private String phone;
    @Id(name="ADDRESS_ID")
    private int address_id;
}
