package cz.cvut.fit.sp.chipin.base.member;

import cz.cvut.fit.sp.chipin.authentication.user.User;
import cz.cvut.fit.sp.chipin.base.group.Group;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Member {
    @EmbeddedId
    private MemberKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_entity_id", nullable = false)
    private User user;

    @ManyToOne
    @MapsId("groupId")
    @JoinColumn(name = "user_group_id", nullable = false)
    private Group group;
    @NotNull
    @Column
    private GroupRole role;

    @NotNull
    @Column
    private Float paid = 0f;

    @NotNull
    @Column
    private Float spent = 0f;

    @NotNull
    @Column
    @Setter(AccessLevel.NONE)
    private Float balance = 0f;

    public Member(User user, Group group, GroupRole role, Float paid, Float spent, Float balance) {
        id = new MemberKey(user.getId(), group.getId());
        this.user = user;
        this.group = group;
        this.role = role;
        this.paid = paid;
        this.spent = spent;
        this.balance = balance;
    }

    public void setPaid(Float paid) {
        this.paid = paid;
        this.balance = this.paid - this.spent;
    }

    public void decreasePaid(Float paid) {
        this.paid -= paid;
        this.balance = this.paid - this.spent;
    }

    public void setSpent(Float spent) {
        this.spent = spent;
        this.balance = this.paid - this.spent;
    }

    public void decreaseSpent(Float spent) {
        this.spent -= spent;
        this.balance = this.paid - this.spent;
    }
}
