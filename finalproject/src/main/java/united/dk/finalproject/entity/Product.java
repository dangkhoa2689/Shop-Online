package united.dk.finalproject.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {

	public Product(long id) {
	}

	private static final long serialVersionIUD = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private long price;

	@Column(name = "quantity")
	private long quantity;

	@Column(name = "description")
	private String description;

	@Column(name = "image")
	private String image;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "tradeMark_id")
	private TradeMark tradeMark;

	@ManyToOne
	@JoinColumn(name = "sex_id")
	private Sex sex;

	@ManyToOne
	@JoinColumn(name = "size_id")
	private Size size;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	@Column(name = "review")
	private List<Review> review;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	@Column(name = "comments")
	private List<Comment> comments;

}
