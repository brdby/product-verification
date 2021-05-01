package ru.brdby.cinderella.data.form;

import lombok.Data;
import ru.brdby.cinderella.data.domain.Product;
import ru.brdby.cinderella.data.domain.User;

@Data
public class ProductForm {

	private String name;
	private String description;
	private boolean oneTimeProduct;

	private String storeName;
	private String storeAddress;

	public Product toProduct(String uuid, User user, String url, byte[] qrCode) {
		return Product.builder()
				.name(name)
				.description(description)
				.oneTimeProduct(oneTimeProduct)
				.user(user)
				.UUID(uuid)
				.url(url)
				.qrCode(qrCode)
				.storeName(storeName)
				.storeAddress(storeAddress).build();
	}

}
