package com.neilsen.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import com.neilson.read.GenricDao;

public class BrandDto {
	private static final Map<Integer, BrandDto> brandMap = new HashMap<Integer, BrandDto>();
	Long brandId;
	String name;

	public BrandDto(final Long brandId, final String name) {
		super();
		this.brandId = brandId;
		this.name = name;
	}

	public static void loadBrandData(final GenricDao genricDao) {
		final String qury = "Select * from imdb.Brand";
		final String fileName = "Brand";
		final ResultSet rs = genricDao.getResult(qury);
		final Stream<String>  st= genricDao.getStream(fileName);
		try {
			while (rs.next()) {
				final BrandDto brandDto = new BrandDto(rs.getLong("Brand_Id"),
						rs.getString("Name"));
				brandMap.put(rs.getInt(1), brandDto);
			}
			
			st.skip(1).map((line)->{
			String[] obj =line.split(",");
			System.out.println(line);
			final BrandDto brandDto = new BrandDto(Long.parseLong(obj[0]), obj[1]);
			brandMap.put(Integer.parseInt(obj[0]), brandDto);
			return brandDto;
			}).forEach(System.out::println);
			
			
			rs.close();
		} catch (final SQLException e) {
			e.printStackTrace();
		}

	}

	public static Map<Integer, BrandDto> getBrandmap() {
		return brandMap;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(final Long brandId) {
		this.brandId = brandId;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "BrandDto [brandId=" + brandId + ", name=" + name + "]";
	}

}
