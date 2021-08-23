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
@Table(name="bill")
public class Bill {
	private static final long serialVersionIUD=1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private long id;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name ="buyer_id")
	private User buyer;
	
	@Column(name ="buyDate")
	private Date buyDate;
	
	@Column(name = "priceTotal")
	private long priceTotal;
	
	@Column(name ="discountPersent")
	private Integer discountPersent;
	
	@Column(name = "status")
	private String status;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bill")
	@Column(name = "bill_Products")
	private List<BillProduct> billProducts;

	@Column(name = "pay")
	private String pay;
}
