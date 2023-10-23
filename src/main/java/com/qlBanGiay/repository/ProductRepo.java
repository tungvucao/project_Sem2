package com.qlBanGiay.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qlBanGiay.entities.Product;
@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{
	//Search Product by Name
	@Query("SELECT p FROM Product p WHERE p.proName LIKE %?1%")
	List<Product> findProByName(String keyword);
	
	//All Product Have CateStatus True
	@Query(nativeQuery = true, value = "SELECT * FROM Product p \r\n"
				+ "INNER JOIN category c ON c.cate_id = p.category_id\r\n"
				+ "WHERE c.cate_status = 1")
	Page<Product> allProHaveCateTrue(Pageable pageNo);
	@Query(nativeQuery = true, value = "SELECT * FROM Product p \r\n"
			+ "INNER JOIN category c ON c.cate_id = p.category_id\r\n"
			+ "WHERE c.cate_status = 1 AND p.pro_name LIKE %?1%")
	List<Product> findProTrueByName(String keyword);
	
	//Sale Product
	@Query(nativeQuery = true, value = "SELECT TOP 5 * FROM Product p\r\n"
			+ "INNER JOIN category c ON c.cate_id = p.category_id\r\n"
			+ "WHERE ((p.price - p.sale_price)/p.price)*100 > 50 AND p.sale_price != 0 AND c.cate_status = 1")
	List<Product> findProSale50();
	@Query(nativeQuery = true, value = "SELECT * FROM Product p\r\n"
			+ "INNER JOIN category c ON c.cate_id = p.category_id\r\n"
			+ "WHERE ((p.price - p.sale_price)/p.price)*100 > 50 AND p.sale_price != 0 AND c.cate_status = 1")
	Page<Product> findAllProSale50(Pageable pageNo);
	@Query(nativeQuery = true, value = "SELECT * FROM Product p\r\n"
			+ "INNER JOIN category c ON c.cate_id = p.category_id\r\n"
			+ "WHERE ((p.price - p.sale_price)/p.price)*100 > 50 AND p.sale_price != 0 AND c.cate_status = 1 AND p.pro_name LIKE %?1%")
	List<Product> findProSale50ByName(String keyword);
	
	//New Product
	@Query(nativeQuery = true, value = "SELECT Top 10 * FROM Product p \r\n"
			+ "INNER JOIN category c ON c.cate_id = p.category_id\r\n"
			+ "WHERE c.cate_status = 1 ORDER BY p.publish DESC")
	List<Product> findNewProduct();
	
	//All Product by Category Id
	@Query(nativeQuery = true, value = "SELECT * FROM Product p \r\n"
			+ "INNER JOIN category c ON c.cate_id = p.category_id\r\n"
			+ "WHERE p.category_id = ?1 AND p.product_id != ?2 AND c.cate_status = 1")
	List<Product> findProByCateId(Integer cateId,Integer id); // Trừ Sản Phẩm đang xem chi tiết
	@Query(nativeQuery = true, value = "SELECT * FROM Product p \r\n"
			+ "INNER JOIN category c ON c.cate_id = p.category_id\r\n"
			+ "WHERE p.category_id = ?1 AND c.cate_status = 1\r\n"
			+ "")
	Page<Product> findAllProByCateId(Integer cateId,Pageable pageNo);
	@Query(nativeQuery = true, value = "SELECT * FROM Product p \r\n"
			+ "INNER JOIN category c ON c.cate_id = p.category_id\r\n"
			+ "WHERE p.category_id = ?1 AND p.pro_name LIKE %?2% AND c.cate_status = 1")
	List<Product> findProByNameAndCateId(Integer cateId,String keyword);

	
	
	
	
}
