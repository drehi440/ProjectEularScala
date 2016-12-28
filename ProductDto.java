package com.neilsen.dto;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.neilson.read.GenricDao;

public class ProductDto {
  private static final Map<Integer, ProductDto> productMap = new HashMap<Integer, ProductDto>();

  private Long product_Id;
  private String isLDI;
  private String alert;
  private String pCode;
  private String packSize;
  private String description;
  private Integer category_Id;
  private Integer brand_Id;
  private Integer manufacturer_Id;
  private BrandDto brand;
  private List<BarCodeDto> barcode;
  private ManufacturerDto manufacturer;


  public ProductDto(final Long product_Id, final String isLDI, final String alert, final String pCode,
      final String packSize, final String description, final Integer category_Id, final Integer brand_Id,
      final Integer manufacturer_Id) {
    super();
    this.product_Id = product_Id;
    this.isLDI = isLDI;
    this.alert = alert;
    this.pCode = pCode;
    this.packSize = packSize;
    this.description = description;
    this.category_Id = category_Id;
    this.brand_Id = brand_Id;
    this.manufacturer_Id = manufacturer_Id;
  }

  public static void loadProductData(final GenricDao genricDao) {
    BrandDto.loadBrandData(genricDao);
    ManufacturerDto.loadManufacturerData(genricDao);
    BarCodeDto.loadBarcodeData(genricDao);
    final String qury = "Select * from imdb.Product";
    final String fileName = "Product";
    final Stream<String> st = genricDao.getStream(fileName);
    
    final ResultSet rs = genricDao.getResult(qury);
    try {
      while (rs.next()) {
        final BrandDto brandDto = BrandDto.getBrandmap().get(rs.getInt(8));
        final ManufacturerDto manufacturerDto = ManufacturerDto.getManifacturermap().get(rs.getInt(9));
        @SuppressWarnings("unchecked")
		final List<BarCodeDto> barCodeDtos = (List<BarCodeDto>) BarCodeDto.getBarcodemap().get(rs.getLong(1));

        final ProductDto productDto = new ProductDto(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4),
            rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
        productDto.setBrand(brandDto);
        productDto.setManufacturer(manufacturerDto);
        productDto.setBarcode(barCodeDtos);
        productMap.put(rs.getInt(1), productDto);
        System.out.println(productDto);
      }
      
      
    st.skip(1).map((line) ->{
    	String[] obj =line.split(",");
		System.out.println(line);
		final BrandDto brandDto = BrandDto.getBrandmap().get(Integer.parseInt(obj[7]));
		final ManufacturerDto manufacturerDto = ManufacturerDto.getManifacturermap().get(Integer.parseInt(obj[8]));
		@SuppressWarnings("unchecked")
		final List<BarCodeDto> barCodeDtos = (List<BarCodeDto>) BarCodeDto.getBarcodemap().get(Long.parseLong(obj[0]));

		
		
		final ProductDto productDto = new ProductDto(Long.parseLong(obj[0]), obj[1], obj[2], obj[3], obj[4], obj[5], Integer.parseInt(obj[6]), Integer.parseInt(obj[7]), Integer.parseInt(obj[8]));
        productDto.setBrand(brandDto);
        productDto.setManufacturer(manufacturerDto);
        productDto.setBarcode(barCodeDtos);
        productMap.put(Integer.parseInt(obj[0]), productDto);
//		System.out.println(productDto);
		return productDto;
			
    }).forEach(System.out::println);
      rs.close();
    } catch (final SQLException e) {
      e.printStackTrace();
    }
  }

  public BrandDto getBrand() {
    return brand;
  }

  public void setBrand(final BrandDto brand) {
    this.brand = brand;
  }

  public List<BarCodeDto> getBarcode() {
    return barcode;
  }

  public void setBarcode(final List<BarCodeDto> barcode) {
    this.barcode = barcode;
  }

  public ManufacturerDto getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(final ManufacturerDto manufacturer) {
    this.manufacturer = manufacturer;
  }

  public Long getProduct_Id() {
    return product_Id;
  }

  public void setProduct_Id(final Long product_Id) {
    this.product_Id = product_Id;
  }

  public String getIsLDI() {
    return isLDI;
  }

  public void setIsLDI(final String isLDI) {
    this.isLDI = isLDI;
  }

  public String getAlert() {
    return alert;
  }

  public void setAlert(final String alert) {
    this.alert = alert;
  }

  public String getpCode() {
    return pCode;
  }

  public void setpCode(final String pCode) {
    this.pCode = pCode;
  }

  public String getPackSize() {
    return packSize;
  }

  public void setPackSize(final String packSize) {
    this.packSize = packSize;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public Integer getCategory_Id() {
    return category_Id;
  }

  public void setCategory_Id(final Integer category_Id) {
    this.category_Id = category_Id;
  }

  public Integer getBrand_Id() {
    return brand_Id;
  }

  public void setBrand_Id(final Integer brand_Id) {
    this.brand_Id = brand_Id;
  }

  public Integer getManufacturer_Id() {
    return manufacturer_Id;
  }

  public void setManufacturer_Id(final Integer manufacturer_Id) {
    this.manufacturer_Id = manufacturer_Id;
  }

  @Override
  public String toString() {
    return "ProductDto [product_Id=" + product_Id + ", isLDI=" + isLDI + ", alert=" + alert + ", pCode=" + pCode
        + ", packSize=" + packSize + ", description=" + description + ", category_Id=" + category_Id + ", brand_Id="
        + brand_Id + ", manufacturer_Id=" + manufacturer_Id + ", brand=" + brand + ", barcode=" + barcode
        + ", manufacturer=" + manufacturer + "]";
  }


}
