package com.neilsen.dto;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;


import com.neilson.read.GenricDao;

public class BarCodeDto {
	private static final MultiMap<Integer, BarCodeDto> barcodeMap = new MultiValueMap<Integer, BarCodeDto>();
	Integer productId;
	Integer codeHash;
	Long code;

	public BarCodeDto(final Integer productId, final Integer codeHash,
			final Long code) {
		super();
		this.productId = productId;
		this.codeHash = codeHash;
		this.code = code;
	}

	public static void loadBarcodeData(final GenricDao genricDao) {
		final String qury = "Select * from imdb.Barcode";
		final String fileName = "Barcode";
		final ResultSet rs = genricDao.getResult(qury);
		final Stream<String>  st= genricDao.getStream(fileName);
		try {
			while (rs.next()) {
				final BarCodeDto barCodeDto = new BarCodeDto(
						rs.getInt("Product_Id"), rs.getInt("CodeHash"),
						rs.getLong("Code"));
				barcodeMap.put(rs.getInt("Product_Id"), barCodeDto);

			}
			System.out.println("Stream returned!");

			st.skip(1).map((line)->{
			String[] obj =line.split(",");
			System.out.println(line);
			final BarCodeDto barCodeDto = new BarCodeDto(Integer.parseInt(obj[2]), Integer.parseInt(obj[1]), Long.parseLong(obj[0]));
			barcodeMap.put(Integer.parseInt(obj[2]), barCodeDto);
			return barCodeDto;
			}).forEach(System.out::println);
			
			rs.close();
		} catch (final SQLException e) {
			e.printStackTrace();
		}

	}

	public static MultiMap<Integer, BarCodeDto> getBarcodemap() {
		return barcodeMap;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(final Integer productId) {
		this.productId = productId;
	}

	public Integer getCodeHash() {
		return codeHash;
	}

	public void setCodeHash(final Integer codeHash) {
		this.codeHash = codeHash;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(final Long code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "BarCodeDto [productId=" + productId + ", codeHash=" + codeHash
				+ ", code=" + code + "]";
	}

}
