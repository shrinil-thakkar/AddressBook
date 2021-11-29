package com.shrinil.addressbook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "USER_DISPLAY_PICTURE")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDisplayPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DP_ID")
    private Integer dpId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false,
            foreignKey = @ForeignKey(
                    name="FK_USER_DP",
                    foreignKeyDefinition = "FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID) ON DELETE CASCADE"
            ))
    private User user;

    @Lob
    @JsonIgnore
    @Column(name = "DISPLAY_PICTURE")
    private Byte[] displayPicture;
}
