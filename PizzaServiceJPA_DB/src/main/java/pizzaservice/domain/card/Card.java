package pizzaservice.domain.card;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Created by lomak on 02.05.2016.
 */

    @Entity
    @Table(name = "bonus_card")
    public class Card implements Serializable {

        private static final Double BONUS_PERCENT = 0.1;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "card_id")
        private Integer id;
        @Column(name = "bonus_size")
        private Double bonusSize;

        public Card() {
        }

        public Card(Double bonusSize) {
            this.bonusSize = bonusSize;
        }

        public Card(Integer id, Double bonusSize) {
            this.id = id;
            this.bonusSize = bonusSize;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Double getBonusSize() {
            return bonusSize;
        }

        public Double calcDiscount() {
            return bonusSize * BONUS_PERCENT;
        }

        public void increaseBonusSize(Double bonusSize) {
            this.bonusSize += bonusSize;
        }

        @Override
        public String toString() {
            return "BonusCard{" + "id=" + id + ", bonusSize=" + getBonusSize() + '}';
        }

    }

