package com.gof.springcloud.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gof.springcloud.entity.Product;
import com.gof.springcloud.entity.ResultVo;
import com.gof.springcloud.service.ProductService;

import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 私募产品信息 前端控制器
 * </p>
 *
 * @author ss
 * @since 2021-05-04
 */
@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	@GetMapping
	@ApiOperation(value = "Get a product by key")
	public ResultVo<Product> getProduct(int key) {
		ResultVo<Product> resultVo = new ResultVo<Product>();
		Product product = productService.getById(key);
		if (null == product) resultVo.failure(404, "product not found");
		else resultVo.success(product);
		return resultVo;
	}

	@PostMapping
	@ApiOperation(value = "Create a product")
	public ResultVo<Product> createProduct(Product product) {
		product.setProductId(null);
		productService.save(product);
		ResultVo<Product> resultVo = new ResultVo<Product>();
		resultVo.success(product);
		return resultVo;
	}

}

