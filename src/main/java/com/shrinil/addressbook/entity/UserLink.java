package com.shrinil.addressbook.entity;

import com.shrinil.addressbook.model.UserLinkId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "USER_ACCESS")
@IdClass(UserLinkId.class)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserAccess{

    @Id
    @ManyToOne
    @JoinColumn(name = "USER_ID_1", nullable = false,
            foreignKey = @ForeignKey(
                    name="FK_USER_ID_1",
                    foreignKeyDefinition = "FOREIGN KEY (USER_ID_1) REFERENCES USER(USER_ID) ON DELETE CASCADE"))
    private User user1;

    @Id
    @ManyToOne
    @JoinColumn(name = "USER_ID_2", nullable = false,
            foreignKey = @ForeignKey(
                    name="FK_USER_ID_2",
                    foreignKeyDefinition = "FOREIGN KEY (USER_ID_2) REFERENCES USER(USER_ID) ON DELETE CASCADE"))
    private User user2;
}
