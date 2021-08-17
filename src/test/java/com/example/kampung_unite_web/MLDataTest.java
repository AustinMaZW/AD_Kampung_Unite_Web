package com.example.kampung_unite_web;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.kampung_unite_web.model.CombinedPurchaseList;
import com.example.kampung_unite_web.model.GroceryItem;
import com.example.kampung_unite_web.model.GroceryList;
import com.example.kampung_unite_web.model.GroupPlan;
import com.example.kampung_unite_web.model.HitcherDetail;
import com.example.kampung_unite_web.model.Product;
import com.example.kampung_unite_web.model.UserDetail;
import com.example.kampung_unite_web.model.enums.GLStatus;
import com.example.kampung_unite_web.model.enums.GroupPlanStatus;
import com.example.kampung_unite_web.model.enums.HitchBuyRole;
import com.example.kampung_unite_web.repo.CPLRepository;
import com.example.kampung_unite_web.repo.GroceryItemRepository;
import com.example.kampung_unite_web.repo.GroceryListRepository;
import com.example.kampung_unite_web.repo.GroupPlanRepository;
import com.example.kampung_unite_web.repo.HitcherDetailRepository;
import com.example.kampung_unite_web.repo.ProductRepository;
import com.example.kampung_unite_web.repo.UserDetailRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = KampungUniteWebApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MLDataTest {

	@Autowired
	private ProductRepository prepo;
	@Autowired
	private CPLRepository crepo;
	@Autowired
	private GroceryListRepository glrepo;
	@Autowired
	private GroupPlanRepository grepo;
	@Autowired
	private HitcherDetailRepository hrepo;
	@Autowired
	private UserDetailRepository urepo;
	@Autowired
	private GroceryItemRepository girepo;

	@Test()
	@Order(0)
	public void CreateUser() {
		UserDetail[] usrs = { new UserDetail("austin", "123", "Ziwang", "Ma", "Hitcher", "123", "123"),
				new UserDetail("YZ", "123", "YaZhen", "Chua", "Hitcher", "123", "123"),
				new UserDetail("LJ", "123", "YeeJean", "Lim", "Hitcher", "123", "123"),
				new UserDetail("NG", "123", "CheZaw", "NguMay", "Hitcher", "123", "123"),
				new UserDetail("CK", "123", "ChorKian", "Tang", "Hitcher", "123", "123"),
				new UserDetail("Yue", "123", "PengCheng", "Yue", "Hitcher", "123", "123"),
				new UserDetail("Tin", "123", "Ngu", "Tin", "Buyer", "123", "123"),
				new UserDetail("Cher", "123", "CherWah", "Tan", "Buyer", "123", "123") };
		Arrays.stream(usrs).forEach(x -> urepo.save(x));
	}

	@Test()
	@Order(1)
	public void CreateProduct() {
		Product[] products = { new Product("apple"), new Product("banana"), new Product("hunmberger"),
				new Product("coca cola"), new Product("pepsi"), new Product("KFC"), new Product("sheet"),
				new Product("ice cream"), new Product("mango") };
		Arrays.stream(products).forEach(x -> prepo.save(x));
	}

	@Test()
	@Order(2)
	public void CreateGroupPlan() {
		// street, city, county, state, country, or postalcode
		LocalDate[] pickUp = { LocalDate.of(2021, 10, 1), LocalDate.of(2021, 10, 10), LocalDate.of(2021, 10, 21),
				LocalDate.of(2021, 10, 15), LocalDate.of(2021, 10, 8), LocalDate.of(2021, 10, 25) };
		LocalDate[] shipping = { LocalDate.of(2021, 9, 1), LocalDate.of(2021, 9, 10), LocalDate.of(2021, 9, 21),
				LocalDate.of(2021, 9, 15), LocalDate.of(2021, 9, 8), LocalDate.of(2021, 9, 25) };
		String[] address = { "220 Prince Edward Road, Singapore, Singapore",
				"2024 BUKIT BATOK STREET, Singapore, Singapore", "3016 BEDOK NORTH AVENUE, Singapore, Singapore",
				"6 Cairnhill Rise, Singapore, Singapore", "Choa Chu Kang Road, Singapore, Singapore",
				"Industrial Park Lorong 8 Toa Payoh , Singapore, Singapore" };
		for (int i = 0; i < address.length; i++) {
			grepo.save(new GroupPlan(String.format("Many_Shits_%s", i), shipping[i], address[i], pickUp[i],
					GroupPlanStatus.AVAILABLE));
		}
//		GroupPlan[] groupPlans = {
//				new GroupPlan("Many_Shits_1", shipping[0], "220 Prince Edward Road, Singapore, Singapore", pickUp[0],
//						GroupPlanStatus.AVAILABLE),
//				new GroupPlan("Many_Shits_2", shipping[1], "2024 BUKIT BATOK STREET, Singapore, Singapore", pickUp[1],
//						GroupPlanStatus.AVAILABLE),
//				new GroupPlan("Many_Shits_3", shipping[2], "3016 BEDOK NORTH AVENUE, Singapore, Singapore", pickUp[2],
//						GroupPlanStatus.AVAILABLE),
//				new GroupPlan("Many_Shits_4", shipping[3], "6 Cairnhill Rise, Singapore, Singapore", pickUp[3],
//						GroupPlanStatus.AVAILABLE),
//				new GroupPlan("Many_Shits_5", shipping[4], "950 Old Choa Chu Kang Road, Singapore, Singapore",
//						pickUp[4], GroupPlanStatus.AVAILABLE),
//				new GroupPlan("Many_Shits_6", shipping[5], "Industrial Park Lorong 8 Toa Payoh , Singapore, Singapore",
//						pickUp[5], GroupPlanStatus.AVAILABLE) };
//		Arrays.stream(groupPlans).forEach(x -> grepo.save(x));
	}

	@Test()
	@Order(3)
	public void CreateHitcherDetail() {
		LocalDateTime[] pickUp = { LocalDateTime.of(2021, 10, 24, 0, 0, 0), LocalDateTime.of(2021, 10, 22, 0, 0, 0),
				LocalDateTime.of(2021, 10, 21, 0, 0, 0), LocalDateTime.of(2021, 10, 19, 0, 0, 0),
				LocalDateTime.of(2021, 11, 8, 0, 0, 0), LocalDateTime.of(2021, 11, 25, 0, 0, 0) };
		String[] address = { "220 Prince Edward Road, Singapore, Singapore",
				"2024 BUKIT BATOK STREET, Singapore, Singapore", "3016 BEDOK NORTH AVENUE, Singapore, Singapore",
				"6 Cairnhill Rise, Singapore, Singapore", "950 Old Choa Chu Kang Road, Singapore, Singapore",
				"Industrial Park Lorong 8 Toa Payoh , Singapore, Singapore" };
		IntStream.range(0, address.length).forEach(x -> hrepo.save(new HitcherDetail(pickUp[x], address[x])));
	}

	@Test()
	@Order(4)
	public void CreateGroceryList() {
		List<UserDetail> usrs = urepo.findAll();
		List<UserDetail> buyers = new ArrayList<>();
		List<UserDetail> Hitchers = new ArrayList<>();
		usrs.stream().forEach(x -> {
			if (x.getRole().equals("Hitcher")) {
				Hitchers.add(x);
			} else {
				buyers.add(x);
			}
		});
		List<GroupPlan> plans = grepo.findAll();
		List<HitcherDetail> hitcherDetails = hrepo.findAll();

		for (int i = 0; i < hitcherDetails.size(); i++) {
			glrepo.save(new GroceryList(GLStatus.ACCEPTED, buyers.get(i % 2), plans.get(i), null, HitchBuyRole.BUYER));
			glrepo.save(new GroceryList(GLStatus.ACCEPTED, Hitchers.get(i), plans.get(i), null, HitchBuyRole.HITCHER));
		}
	}

	@Test()
	@Order(5)
	public void CreateGroceryItem() {
		List<Product> products = prepo.findAll();
		List<GroceryList> gls = glrepo.findAll();
		for (int i = 0; i < products.size(); i++) {
			for (int j = 0; j < gls.size(); j++) {
				if (gls.get(j).getRole() == HitchBuyRole.HITCHER) {
					GroceryItem gi = new GroceryItem(i, 20, products.get(i), gls.get(j));
					girepo.save(gi);
				} else {
					GroceryItem gi = new GroceryItem(i, 20, products.get(i), gls.get(j));
					girepo.save(gi);
				}
			}
		}
	}

	@Test
	@Order(6)
	public void CreateCombineList() {
		List<GroupPlan> plans = grepo.findAll();
		List<Product> products = prepo.findAll();
		for (int i = 0; i < plans.size(); i++) {
			for (int j = 0; j < products.size(); j++) {
				crepo.save(new CombinedPurchaseList(20, 20, 1, plans.get(i), products.get(j)));
			}
		}

	}
}
